package com.pawels96.skyrimperkcalculator.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BuildsViewModel(
    private val repo: BuildRepository,
    private val prefs: Preferences
) : ViewModel() {

    private val _currentBuild = MutableLiveData<Build>()
    val currentBuild: LiveData<Build> = _currentBuild

    private val _currentBuildList = MutableLiveData<MutableList<Build>>()
    val buildList: LiveData<MutableList<Build>> = _currentBuildList

    private val _requiredLevel = MutableLiveData<Int>()
    val requiredLevel: LiveData<Int> = _requiredLevel

    private val _events = Channel<AppEvent>()
    val events: Flow<AppEvent> = _events.receiveAsFlow()

    val allCurrentBuildSkills: List<Skill> get() = ArrayList(_currentBuild.value!!.getAllSkills())

    private val builds: MutableList<Build> get() = _currentBuildList.value!!
    private var currentPerkSystem: PerkSystem = prefs.selectedPerkSystem

    var multiplier: Float = prefs.perkMultiplier
        private set

    init {
        loadBuilds()
    }

    private fun loadBuilds() = viewModelScope.launch {
        repo.populate()
        val allBuilds = repo.getByPerkSystem(currentPerkSystem).sortedBy { it.name }.toMutableList()
        val currentBuild = allBuilds.find { it.id == prefs.selectedBuildId } ?: allBuilds.firstOrNull()
        _currentBuildList.value = allBuilds
        _currentBuild.value = currentBuild ?: Build.create(currentPerkSystem)
    }

    fun setPerkMultiplier(multiplier: Float) {
        this.multiplier = multiplier
        val level = _currentBuild.value!!.getRequiredLevel(multiplier)
        _requiredLevel.value = level
    }

    fun savePerkMultiplier() {
        prefs.perkMultiplier = multiplier
    }

    fun changePerkState(skill: ISkill, perk: IPerk, state: Int? = null) {

        _currentBuild.value!!.getSkill(skill)[perk]?.apply {
            if (state != null) {

                if (state in (0..this.maxState))
                    this.state = state
                else
                    return
            } else
                this.nextState()
        }

        val indexInList = builds.indexOfFirst { it.id == _currentBuild.value!!.id }
        builds[indexInList] = _currentBuild.value!!

        _currentBuild.postNotifyObserver()
        _currentBuildList.notifyObserver()

        viewModelScope.launch {
            repo.update(_currentBuild.value!!)
        }
    }

    fun updateDescription(build: Build, description: String) = viewModelScope.launch {
        repo.update(build.apply { this.description = description })
        builds.find { it.name == build.name }?.description = description
        _currentBuildList.postNotifyObserver()
    }

    fun canDeleteBuild() : Boolean {
        val count = buildList.value?.size ?: 0
        return count > 1
    }

    fun deleteBuild(buildId: Long) = viewModelScope.launch {

        if (_currentBuildList.value?.size == 1) {
            dispatchEvent(AppEvent.BuildDeleted(false, R.string.msg_cant_delete))
            return@launch
        }

        val deletingCurrent = buildId == _currentBuild.value!!.id

        val deleted = repo.delete(buildId)

        if (deleted) {
            val index = builds.indexOfFirst { it.id == buildId }
            builds.removeAt(index)

            if (deletingCurrent)
                selectBuild(builds[(index - 1).coerceAtLeast(0)])

            _currentBuildList.notifyObserver()
        }

        val message = if (deleted) R.id.delete else R.string.msg_error
        dispatchEvent(AppEvent.BuildDeleted(deleted, message))
    }

    fun createBuild(buildName: String, copyCurrent: Boolean) = viewModelScope.launch {
        if (buildName.trim().isEmpty()) {
            dispatchEvent(AppEvent.BuildSaved(false, R.string.msg_name_empty))
            return@launch
        }

        if (!repo.isNameAvailable(buildName, currentPerkSystem)) {
            dispatchEvent(AppEvent.BuildSaved(false, R.string.msg_name_in_use))
            return@launch
        }

        val newBuild = if (copyCurrent)
            Build.clone(_currentBuild.value!!)
        else
            Build.create(currentPerkSystem)

        newBuild.name = buildName

        val savedBuild = repo.insert(newBuild)
        val success = savedBuild != null
        val message = if (success) R.string.msg_build_saved else R.string.msg_error
        dispatchEvent(AppEvent.BuildSaved(success, message))

        if (savedBuild != null) {
            _currentBuildList.value?.add(savedBuild)
            _currentBuildList.postNotifyObserver()
            selectBuild(savedBuild)
        }
    }

    fun selectBuild(build: Build) {
        _currentBuild.value = build
        prefs.selectedBuildId = build.id
    }

    fun renameBuild(build: Build, newName: String) = viewModelScope.launch {

        if (newName.trim().isEmpty()) {
            dispatchEvent(AppEvent.BuildRenamed(false, R.string.msg_name_empty))
            return@launch
        }

        if (build.name == newName) {
            dispatchEvent(AppEvent.BuildRenamed(true, null))
            return@launch
        }

        if (!repo.isNameAvailable(newName, currentPerkSystem)) {
            dispatchEvent(AppEvent.BuildSaved(false, R.string.msg_name_in_use))
            return@launch
        }

        build.name = newName
        val success = repo.update(build)
        val message = if (success) R.string.msg_name_changed else R.string.msg_error
        if (success) {
            _currentBuildList.value?.find { it.id == build.id }?.name = newName
            _currentBuildList.postNotifyObserver()
        }

        dispatchEvent(AppEvent.BuildSaved(success, message))
    }

    fun changePerkSystem(perkSystem: PerkSystem) {
        currentPerkSystem = perkSystem
        prefs.selectedPerkSystem = currentPerkSystem

        viewModelScope.launch {
            val builds = repo.getByPerkSystem(perkSystem).sortedBy { it.name }.toMutableList()
            builds.firstOrNull()?.let { selectBuild(it) }
            _currentBuildList.postValue(builds)
        }
    }

    fun changeVampirePerkSystem(perkSystem: VampirePerkSystem) = viewModelScope.launch {
        _currentBuild.value!!.vampirePerkSystem = perkSystem
        repo.update(_currentBuild.value!!)
        _currentBuild.notifyObserver()
    }

    fun changeWerewolfPerkSystem(perkSystem: WerewolfPerkSystem) = viewModelScope.launch {
        _currentBuild.value!!.werewolfPerkSystem = perkSystem
        repo.update(_currentBuild.value!!)
        _currentBuild.notifyObserver()
    }

    suspend fun getBuildById(id: Long) : Build? {
        return repo.getById(id)
    }

    private fun dispatchEvent(event: AppEvent) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    class Factory(
        private val repo: BuildRepository,
        private val prefs: Preferences
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BuildsViewModel(repo, prefs) as T
        }
    }

    private fun MutableLiveData<*>.notifyObserver() {
        this.value = this.value
    }

    private fun <T> MutableLiveData<T>.postNotifyObserver() {
        postValue(this.value)
    }
}