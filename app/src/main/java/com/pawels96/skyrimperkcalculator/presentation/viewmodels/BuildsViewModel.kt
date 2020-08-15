package com.pawels96.skyrimperkcalculator.presentation.viewmodels

import androidx.lifecycle.*
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.data.Database
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.enums.SkillEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BuildsViewModel(private val db: Database, private val prefs: Preferences) : ViewModel() {

    //TODO renaming doesnt update list

    private val _currentBuild = MutableLiveData<Build>()
    private val _currentBuildList = MutableLiveData<MutableList<Build>>()
    private var currentPerkSystem: PerkSystem = prefs.selectedPerkSystem
    private val _requiredLevel = MutableLiveData<Int>()

    val requiredLevel: LiveData<Int>
    get() = _requiredLevel

    val currentBuild: LiveData<Build>
        get() = _currentBuild

    val buildList: LiveData<MutableList<Build>>
        get() = _currentBuildList

    var multiplier: Float = prefs.perkMultiplier
        set(value) {
            field = value
            val level = _currentBuild.value!!.getRequiredLevel(multiplier)
            _requiredLevel.postValue(level)
        }

    private val builds: MutableList<Build>
        get() = _currentBuildList.value!!

    init {
        _currentBuild.value = db.getByName(prefs.selectedBuild, currentPerkSystem)
        _currentBuildList.value = db.getAllBuilds(currentPerkSystem)
    }

    override fun onCleared() {
        super.onCleared()

        prefs.apply {
            perkMultiplier = multiplier
            prefs.selectedBuild = _currentBuild.value!!.name
            prefs.selectedPerkSystem = currentPerkSystem
        }
    }

    fun changePerkState(skill: SkillEnum, perk: Perk, state: Int? = null) {

        _currentBuild.value!!.getSkill(skill)!![perk.perk]?.apply {
            if (state != null)
                this.state = state
            else
                this.nextState()
        }
        _currentBuild.postNotifyObserver()

        viewModelScope.launch(Dispatchers.IO + NonCancellable) {
            db.updateBuild(_currentBuild.value)
        }
    }

    fun updateDescription(build: Build, description : String){

        db.updateBuild(build.apply { this.description = description })
        builds.find { it.name == build.name }?.description = description
        _currentBuildList.postNotifyObserver()
    }

    fun deleteBuild(build: Build) {

        if (_currentBuildList.value?.size == 1) {
            _events.value = LiveEvent(Event.BuildDeleted(false, R.string.msg_cant_delete))
            return
        }

        val deletingCurrent = build.name == _currentBuild.value!!.name

        val deleted = db.deleteBuild(build)

        if (deleted) {
            val index = builds.indexOf(build)
            builds.removeAt(index)

            if (deletingCurrent)
            _currentBuild.value = builds[(index - 1).coerceAtLeast(0)]

            _currentBuildList.notifyObserver()
        }

        val message = if (deleted) R.id.delete else R.string.msg_error
        _events.value = LiveEvent(Event.BuildDeleted(deleted, message))
    }

    sealed class Event(val success: Boolean, val messageID: Int?) {
        class BuildSaved(success: Boolean, messageID: Int?) : Event(success, messageID)
        class BuildRenamed(success: Boolean, messageID: Int?) : Event(success, messageID)
        class BuildDeleted(success: Boolean, messageID: Int?) : Event(success, messageID)
    }

    private val _events = MutableLiveData<LiveEvent<Event>>()
    val events: LiveData<LiveEvent<Event>>
        get() = _events

    fun createBuild(buildName: String, copyCurrent: Boolean) {

        if (buildName.trim().isEmpty()) {
            _events.value = LiveEvent(Event.BuildSaved(false, R.string.msg_name_empty))
            return
        }

        if (db.isNameAvailable(buildName, currentPerkSystem)) {

            val newBuild = if (copyCurrent)
                Build.clone(_currentBuild.value!!)!!
            else
                Build(currentPerkSystem)

            newBuild.apply { name = buildName }

            val success = db.saveBuild(newBuild)
            val message = if (success) R.string.msg_build_saved else R.string.msg_error
            _events.value = LiveEvent(Event.BuildSaved(success, message))

            if (success) {
                _currentBuildList.value?.add(newBuild)
                _currentBuildList.postNotifyObserver()
                _currentBuild.value = newBuild
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

        if (db.isNameAvailable(newName, currentPerkSystem)) {

            val success = db.renameBuild(build, newName)
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

            val builds = db.getAllBuilds(perkSystem)

            withContext(Dispatchers.Main) {
                _currentBuildList.value = builds
                _currentBuild.value = builds[0]
            }
        }
    }

    class Factory(private val db: Database, private val prefs: Preferences) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BuildsViewModel(db, prefs) as T
        }
    }

    private fun MutableLiveData<*>.notifyObserver() {
        this.value = this.value
    }

    private fun <T> MutableLiveData<T>.postNotifyObserver() {
        postValue(this.value)
    }

}