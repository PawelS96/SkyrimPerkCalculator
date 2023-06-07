package com.pawels96.skyrimperkcalculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

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
    fun should_load_default_vanilla_build_on_first_launch() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        assertEquals(1, model.buildList.value?.size)
        assertEquals(Utils.DEFAULT_BUILD_NAME, model.buildList.value?.first()?.name)
    }

    @Test
    fun should_set_created_build_as_current() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        val name = "new"
        model.createBuild(name, false)
        advanceUntilIdle()
        val current = model.currentBuild.value
        assertEquals(name, current?.name)
        assertTrue(model.buildList.value?.contains(current) == true)
    }

    @Test
    fun should_load_last_selected_build_on_next_launch() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.changePerkSystem(PerkSystem.ORDINATOR)
        advanceUntilIdle()
        model.createBuild("new", false)
        advanceUntilIdle()
        val buildId = model.currentBuild.value?.id
        val nextLaunchModel = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        val build = nextLaunchModel.currentBuild.value
        assertEquals(buildId, build?.id)
        assertEquals(PerkSystem.ORDINATOR, build?.system)
    }

    @Test
    fun should_load_first_available_build_when_last_selected_is_invalid() = runTest {
        prefs.selectedBuildId = 100
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()

        val selectedBuild = model.currentBuild.value!!
        val allBuilds = model.buildList.value!!

        assertEquals(true, allBuilds.contains(selectedBuild))
    }

    @Test
    fun should_emit_error_when_trying_to_create_with_duplicated_name() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        assertFalse(model.events.drop(1).first().success)
    }

    @Test
    fun should_insert_with_duplicated_name_in_different_perk_system() = runTest {
        val model = BuildsViewModel(repo, prefs)
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
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("name", false)
        model.createBuild("name2", false)
        advanceUntilIdle()
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name2")
        advanceUntilIdle()
        assertFalse(model.events.drop(2).first().success)
    }

    @Test
    fun should_emit_success_when_renaming_with_same_name() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name")
        advanceUntilIdle()
        assertTrue(model.events.first().success)
    }

    @Test
    fun should_emit_success_when_renaming_with_new_unique_name() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("name", false)
        advanceUntilIdle()
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name2")
        advanceUntilIdle()
        assertTrue(model.events.first().success)
    }

    @Test
    fun should_not_allow_deleting_the_only_existing_build() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        assertEquals(1, model.buildList.value?.size)
        assertFalse(model.canDeleteBuild())
        model.createBuild("name0", false)
        advanceUntilIdle()
        assertTrue(model.canDeleteBuild())
    }

    @Test
    fun should_select_previous_build_after_deleting_current() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("a", false)
        advanceUntilIdle()
        val first = model.buildList.value!![0]
        val second = model.buildList.value!![1]
        model.selectBuild(second)
        advanceUntilIdle()
        model.deleteBuild(second.id)
        advanceUntilIdle()
        assertEquals(first.id, model.currentBuild.value!!.id)
    }

    @Test
    fun should_select_next_build_after_deleting_current() = runTest {
        val model = BuildsViewModel(repo, prefs)
        advanceUntilIdle()
        model.createBuild("a", false)
        advanceUntilIdle()
        val first = model.buildList.value!![0]
        val second = model.buildList.value!![1]
        model.selectBuild(first)
        advanceUntilIdle()
        model.deleteBuild(first.id)
        advanceUntilIdle()
        assertEquals(second.id, model.currentBuild.value!!.id)
    }
}
