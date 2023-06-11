package com.pawels96.skyrimperkcalculator.presentation.build_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class BuildListViewModel(
    private val repo: BuildRepository,
    private val prefs: Preferences
) : ViewModel() {

    private val _events = Channel<BuildListEvent>()
    val events: Flow<BuildListEvent> = _events.receiveAsFlow()

    private val builds: List<Build> get() = buildList.value.map { it.build }

    val currentPerkSystem: PerkSystem get() = prefs.selectedPerkSystem

    val buildList: StateFlow<List<BuildListItem>> = prefs.selectedPerkSystemFlow
        .flatMapLatest { perkSystem ->
            combine(
                prefs.selectedBuildIdFlow,
                repo.observeByPerkSystem(perkSystem)
            ) { selectedId, builds ->
                builds
                    .sortedBy { it.name.lowercase() }
                    .map { build ->
                        BuildListItem(
                            build = build,
                            isSelected = build.id == selectedId,
                            level = build.getRequiredLevel(prefs.perkMultiplier)
                        )
                    }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun updateDescription(build: Build, description: String) = viewModelScope.launch {
        repo.update(build.apply { this.description = description })
    }

    fun canDeleteBuild(): Boolean {
        return buildList.value.size > 1
    }

    fun deleteBuild(buildId: Long) = viewModelScope.launch {

        if (buildList.value.size == 1) {
            dispatchEvent(BuildListEvent.BuildDeleted(false, R.string.msg_cant_delete))
            return@launch
        }

        val currentBuild = repo.observeById(prefs.selectedBuildId).first()
        val deletingCurrent = buildId == currentBuild?.id
        val index = builds.indexOfFirst { it.id == buildId }
        val buildToSelectIndex = if (index == 0) 1 else index - 1
        val buildToSelectId = builds[buildToSelectIndex].id
        val didDelete = repo.delete(buildId)

        if (didDelete && deletingCurrent) {
            val buildToSelect = builds.firstOrNull { it.id == buildToSelectId } ?: builds.first()
            selectBuild(buildToSelect)
        }

        val message = if (didDelete) R.id.delete else R.string.msg_error
        dispatchEvent(BuildListEvent.BuildDeleted(didDelete, message))
    }

    fun createBuild(buildName: String, copyCurrent: Boolean) = viewModelScope.launch {
        if (buildName.trim().isEmpty()) {
            dispatchEvent(BuildListEvent.BuildSaved(false, R.string.msg_name_empty))
            return@launch
        }

        if (!repo.isNameAvailable(buildName, currentPerkSystem)) {
            dispatchEvent(BuildListEvent.BuildSaved(false, R.string.msg_name_in_use))
            return@launch
        }

        val currentBuild = repo.observeById(prefs.selectedBuildId).first()
        val newBuild = if (copyCurrent)
            Build.clone(currentBuild!!)
        else
            Build.create(currentPerkSystem)

        newBuild.name = buildName

        val savedBuild = repo.insert(newBuild)
        val success = savedBuild != null
        val message = if (success) R.string.msg_build_saved else R.string.msg_error
        dispatchEvent(BuildListEvent.BuildSaved(success, message))

        if (savedBuild != null) {
            selectBuild(savedBuild)
        }
    }

    fun selectBuild(build: Build) {
        prefs.selectedBuildId = build.id
    }

    fun renameBuild(build: Build, newName: String) = viewModelScope.launch {

        if (newName.trim().isEmpty()) {
            dispatchEvent(BuildListEvent.BuildRenamed(false, R.string.msg_name_empty))
            return@launch
        }

        if (build.name == newName) {
            dispatchEvent(BuildListEvent.BuildRenamed(true, null))
            return@launch
        }

        if (!repo.isNameAvailable(newName, currentPerkSystem)) {
            dispatchEvent(BuildListEvent.BuildSaved(false, R.string.msg_name_in_use))
            return@launch
        }

        build.name = newName
        val success = repo.update(build)
        val message = if (success) R.string.msg_name_changed else R.string.msg_error

        dispatchEvent(BuildListEvent.BuildSaved(success, message))
    }

    fun changePerkSystem(perkSystem: PerkSystem) = viewModelScope.launch {
        val build = repo.getByPerkSystem(perkSystem).firstOrNull()
        build?.let { selectBuild(it) }
        prefs.selectedPerkSystem = perkSystem
    }

    private fun dispatchEvent(event: BuildListEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    suspend fun getBuildById(id: Long): Build? {
        return repo.observeById(id).first()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val repo: BuildRepository,
        private val prefs: Preferences
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BuildListViewModel(repo, prefs) as T
        }
    }
}