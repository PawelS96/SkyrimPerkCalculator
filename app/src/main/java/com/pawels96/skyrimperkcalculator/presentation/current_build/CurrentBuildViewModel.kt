package com.pawels96.skyrimperkcalculator.presentation.current_build

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem
import com.pawels96.skyrimperkcalculator.domain.populate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentBuildViewModel(
    private val repo: BuildRepository,
    private val prefs: Preferences
) : ViewModel() {

    init {
        viewModelScope.launch {
            repo.populate()
        }
    }

    val currentBuild: StateFlow<Build?> = prefs.selectedBuildIdFlow
        .flatMapLatest { id ->
            var validId = id
            if (repo.observeById(id).first() == null) {
                val builds = repo.observeByPerkSystem(prefs.selectedPerkSystem).first()
                val first = builds.firstOrNull()
                if (first != null) {
                    prefs.selectedBuildId = first.id
                    validId = first.id
                }
            }
            return@flatMapLatest repo.observeById(validId)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    val requiredLevel: StateFlow<Int> = combine(
        prefs.perkMultiplierFlow,
        currentBuild
    ) { multiplier, build -> build?.getRequiredLevel(multiplier) ?: 1 }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        1
    )

    val allCurrentBuildSkills: List<Skill>
        get() = currentBuild.value?.getAllSkills() ?: emptyList()

    var perkMultiplier: Float
        set(value) {
            prefs.perkMultiplier = value
        }
        get() = prefs.perkMultiplier

    fun changePerkState(skill: ISkill, perk: IPerk, newState: Int? = null) = viewModelScope.launch {
        val build = repo.observeById(prefs.selectedBuildId).first() ?: return@launch
        val skillPerk = build.getSkill(skill)[perk] ?: return@launch

        if (newState != null) {
            if (newState in (0..skillPerk.maxState)) {
                skillPerk.state = newState
                repo.update(build)
            }
        } else {
            skillPerk.nextState()
            repo.update(build)
        }
    }

    fun changeVampirePerkSystem(perkSystem: VampirePerkSystem) = viewModelScope.launch {
        val updatedBuild = currentBuild.value?.copy() ?: return@launch
        updatedBuild.vampirePerkSystem = perkSystem
        repo.update(updatedBuild)
    }

    fun changeWerewolfPerkSystem(perkSystem: WerewolfPerkSystem) = viewModelScope.launch {
        val updatedBuild = currentBuild.value?.copy() ?: return@launch
        updatedBuild.werewolfPerkSystem = perkSystem
        repo.update(updatedBuild)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val repo: BuildRepository,
        private val prefs: Preferences
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CurrentBuildViewModel(repo, prefs) as T
        }
    }
}