package com.pawels96.skyrimperkcalculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.build_list.BuildListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BuildListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var repo: BuildRepository
    private lateinit var prefs: Preferences

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        repo = FakeBuildRepository()
        val sp = context.getSharedPreferences("skyrim_test_prefs", Context.MODE_PRIVATE)
        sp.edit().clear().commit()
        prefs = Preferences(sp)
    }

    @Test
    fun should_load_builds_for_selected_perk_system_on_init() = runTest {
        prefs.selectedPerkSystem = PerkSystem.ORDINATOR
        repo.insert(Build.create(PerkSystem.ORDINATOR))
        repo.insert(Build.create(PerkSystem.ORDINATOR))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        assertEquals(2, model.buildList.value.size)
    }

    @Test
    fun should_reload_builds_after_changing_perk_system() = runTest {
        prefs.selectedPerkSystem = PerkSystem.ORDINATOR
        repo.insert(Build.create(PerkSystem.ORDINATOR))
        repo.insert(Build.create(PerkSystem.VOKRII))
        repo.insert(Build.create(PerkSystem.VOKRII))

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        model.changePerkSystem(PerkSystem.VOKRII)
        advanceUntilIdle()

        assertEquals(2, model.buildList.value.size)
        assertTrue(model.buildList.value.all { it.build.system == PerkSystem.VOKRII })
        assertEquals(PerkSystem.VOKRII, model.currentPerkSystem)
    }

    @Test
    fun should_set_created_build_as_current() = runTest {
        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        val name = "new"
        model.createBuild(name, false)
        advanceUntilIdle()
        val current = model.buildList.value.firstOrNull { it.isSelected }?.build

        assertEquals(name, current?.name)
        assertEquals(current?.id, prefs.selectedBuildId)
    }

    @Test
    fun should_emit_error_when_trying_to_create_with_duplicated_name() = runTest {
        val model = BuildListViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()

        assertFalse(model.events.drop(1).first().success)
    }

    @Test
    fun should_insert_with_duplicated_name_in_different_perk_system() = runTest {
        val model = BuildListViewModel(repo, prefs)
        advanceUntilIdle()
        model.changePerkSystem(PerkSystem.VANILLA)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        model.changePerkSystem(PerkSystem.ORDINATOR)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()

        assertTrue(model.events.first().success)
    }

    @Test
    fun should_emit_error_when_renaming_with_duplicated_name() = runTest {
        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        model.createBuild("name", false)
        model.createBuild("name2", false)
        advanceUntilIdle()
        val buildToRename = model.buildList.value.find { it.build.name == "name" }?.build
        model.renameBuild(buildToRename!!, "name2")
        advanceUntilIdle()

        assertFalse(model.events.drop(2).first().success)
    }

    @Test
    fun should_emit_success_when_renaming_with_same_name() = runTest {
        repo.insert(Build.create(PerkSystem.VANILLA, name = "name"))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        val buildToRename = model.buildList.value.find { it.build.name == "name" }?.build
        model.renameBuild(buildToRename!!, "name")
        advanceUntilIdle()

        assertTrue(model.events.first().success)
    }

    @Test
    fun should_emit_success_when_renaming_with_new_unique_name() = runTest {
        repo.insert(Build.create(PerkSystem.VANILLA, name = "name"))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        val buildToRename = model.buildList.value.find { it.build.name == "name" }?.build
        model.renameBuild(buildToRename!!, "name2")
        advanceUntilIdle()

        assertTrue(model.events.first().success)
    }

    @Test
    fun should_not_allow_deleting_the_only_existing_build() = runTest {
        repo.insert(Build.create(PerkSystem.VANILLA))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        assertEquals(1, model.buildList.value.size)
        assertFalse(model.canDeleteBuild())

        model.createBuild("name0", false)
        advanceUntilIdle()

        assertTrue(model.canDeleteBuild())
    }

    @Test
    fun should_select_previous_build_after_deleting_current() = runTest {
        repo.insert(Build.create(PerkSystem.VANILLA))
        repo.insert(Build.create(PerkSystem.VANILLA))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        val first = model.buildList.value[0].build
        val second = model.buildList.value[1].build
        model.selectBuild(second)
        advanceUntilIdle()
        model.deleteBuild(second.id)
        advanceUntilIdle()

        assertEquals(first.id, prefs.selectedBuildId)
        assertTrue(model.buildList.value.first { it.build.id == first.id }.isSelected)
    }

    @Test
    fun should_select_next_build_after_deleting_current() = runTest {
        repo.insert(Build.create(PerkSystem.VANILLA))
        repo.insert(Build.create(PerkSystem.VANILLA))
        advanceUntilIdle()

        val model = BuildListViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.buildList.collect()
        }
        advanceUntilIdle()

        val first = model.buildList.value[0].build
        val second = model.buildList.value[1].build
        model.selectBuild(first)
        advanceUntilIdle()
        model.deleteBuild(first.id)
        advanceUntilIdle()

        assertEquals(second.id, prefs.selectedBuildId)
        assertTrue(model.buildList.value.first { it.build.id == second.id }.isSelected)
    }
}
