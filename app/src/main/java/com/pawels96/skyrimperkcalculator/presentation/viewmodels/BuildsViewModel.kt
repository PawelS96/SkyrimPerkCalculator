package com.pawels96.skyrimperkcalculator.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.data.Repository
import com.pawels96.skyrimperkcalculator.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BuildsViewModel(private val repo: Repository, private val prefs: Preferences) : ViewModel() {

    private val _currentBuild = MutableLiveData<Build>()
    private val _currentBuildList = MutableLiveData<MutableList<Build>>()
    private val _requiredLevel = MutableLiveData<Int>()
    private val _events = MutableLiveData<LiveEvent<Event>>()

    val requiredLevel: LiveData<Int> get() = _requiredLevel
    val currentBuild: LiveData<Build>  get() = _currentBuild
    val buildList: LiveData<MutableList<Build>> get() = _currentBuildList
    val events: LiveData<LiveEvent<Event>> get() = _events
    val allCurrentBuildSkills: List<Skill> get() = ArrayList(_currentBuild.value!!.getAllSkills())

    private val builds: MutableList<Build> get() = _currentBuildList.value!!
    private var currentPerkSystem: PerkSystem = prefs.selectedPerkSystem.also { Log.d("prefs", it.toString()) }

    var multiplier: Float = prefs.perkMultiplier
        set(value) {
            field = value
            val level = _currentBuild.value!!.getRequiredLevel(multiplier)
            _requiredLevel.postValue(level)
        }

    init {
        _currentBuild.value = repo.getByNameOrDefault(prefs.selectedBuild, currentPerkSystem)
        _currentBuildList.value = repo.getByPerkSystem(currentPerkSystem, true).toMutableList()
    }

    override fun onCleared() {
        super.onCleared()

        Log.d("BuildsVM", "onCleared")

        prefs.apply {
            perkMultiplier = multiplier
            prefs.selectedBuild = _currentBuild.value!!.name
            prefs.selectedPerkSystem = currentPerkSystem
        }
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

        viewModelScope.launch(Dispatchers.IO + NonCancellable) {
            repo.update(_currentBuild.value!!)
        }
    }

    fun updateDescription(build: Build, description: String) {

        repo.update(build.apply { this.description = description })
        builds.find { it.name == build.name }?.description = description
        _currentBuildList.postNotifyObserver()
    }

    fun deleteBuild(build: Build) {

        if (_currentBuildList.value?.size == 1) {
            _events.value = LiveEvent(Event.BuildDeleted(false, R.string.msg_cant_delete))
            return
        }

        val deletingCurrent = build.id == _currentBuild.value!!.id

        val deleted = repo.delete(build)

        if (deleted) {
            val index = builds.indexOfFirst { it.id == build.id }
            builds.removeAt(index)

            if (deletingCurrent)
                _currentBuild.value = builds[(index - 1).coerceAtLeast(0)]

            _currentBuildList.notifyObserver()
        }

        val message = if (deleted) R.id.delete else R.string.msg_error
        _events.value = LiveEvent(Event.BuildDeleted(deleted, message))
    }

    fun createBuild(buildName: String, copyCurrent: Boolean) {

        if (buildName.trim().isEmpty()) {
            _events.value = LiveEvent(Event.BuildSaved(false, R.string.msg_name_empty))
            return
        }

        if (repo.isNameAvailable(buildName, currentPerkSystem)) {

            val newBuild = if (copyCurrent)
                Build.clone(_currentBuild.value!!)
            else
                Build.create(currentPerkSystem)

            newBuild.apply { name = buildName }

            val success = repo.insert(newBuild)
            val message = if (success) R.string.msg_build_saved else R.string.msg_error
            _events.value = LiveEvent(Event.BuildSaved(success, message))

            if (success) {

                val fromDb = repo.getByNameOrDefault(buildName, currentPerkSystem)
                _currentBuildList.value?.add(fromDb)
                _currentBuildList.postNotifyObserver()
                _currentBuild.value = fromDb
            }

        } else _events.value = LiveEvent(Event.BuildSaved(false, R.string.msg_name_in_use))
    }

    fun selectBuild(build: Build) {
        _currentBuild.value = build
    }

    fun renameBuild(build: Build, newName: String) {

        val currentName = build.name
        val renamingCurrentBuild = build.name == _currentBuild.value?.name

        if (newName.trim().isEmpty()) {
            _events.value = LiveEvent(Event.BuildRenamed(false, R.string.msg_name_empty))
            return
        }

        if (build.name == newName) {
            _events.value = LiveEvent(Event.BuildRenamed(true, null))
            return
        }

        if (repo.isNameAvailable(newName, currentPerkSystem)) {

            val success = repo.update(build)
            val message = if (success) R.string.msg_name_changed else R.string.msg_error
            if (success) {
                val b = builds.find { it.name == currentName }
                b?.name = newName

                if (renamingCurrentBuild)
                    _currentBuild.postValue(b)

                _currentBuildList.postNotifyObserver()
            }

            _events.value = LiveEvent(Event.BuildSaved(success, message))

        } else _events.value = LiveEvent(Event.BuildSaved(false, R.string.msg_name_in_use))
    }

    fun changePerkSystem(perkSystem: PerkSystem) {

        viewModelScope.launch(Dispatchers.IO) {

            currentPerkSystem = perkSystem

            val builds = repo.getByPerkSystem(perkSystem, true).toMutableList()

            withContext(Dispatchers.Main) {
                _currentBuild.value = builds[0]
                _currentBuildList.value = builds
            }
        }
    }

    fun changeVampirePerkSystem(perkSystem: VampirePerkSystem) {
        _currentBuild.value!!.vampirePerkSystem = perkSystem
        repo.update(_currentBuild.value!!)
        _currentBuild.notifyObserver()
    }

    fun changeWerewolfPerkSystem(perkSystem: WerewolfPerkSystem) {
        _currentBuild.value!!.werewolfPerkSystem = perkSystem
        repo.update(_currentBuild.value!!)
        _currentBuild.notifyObserver()
    }

    sealed class Event(val success: Boolean, val messageID: Int?) {
        class BuildSaved(success: Boolean, messageID: Int?) : Event(success, messageID)
        class BuildRenamed(success: Boolean, messageID: Int?) : Event(success, messageID)
        class BuildDeleted(success: Boolean, messageID: Int?) : Event(success, messageID)
    }

    class Factory(private val repo: Repository, private val prefs: Preferences) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = BuildsViewModel(repo, prefs) as T
    }

    private fun MutableLiveData<*>.notifyObserver() {
        this.value = this.value
    }

    private fun <T> MutableLiveData<T>.postNotifyObserver() {
        postValue(this.value)
    }
}