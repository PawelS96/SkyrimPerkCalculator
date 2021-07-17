package com.pawels96.skyrimperkcalculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.data.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var repo: BuildRepository
    private lateinit var prefs: Preferences
    private lateinit var model: BuildsViewModel

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = AppDatabase.getInMemoryDatabase(context)
        repo = BuildRepository(db.buildDAO())
        val sp = context.getSharedPreferences("skyrim_test_prefs", Context.MODE_PRIVATE)
        sp.edit().clear().commit()
        prefs = Preferences(sp)
        model = BuildsViewModel(repo, prefs)
    }

    @Test
    fun should_load_default_vanilla_build_on_first_launch() {
        assertEquals(1, model.buildList.value?.size)
        assertEquals(Utils.DEFAULT_BUILD_NAME, model.buildList.value?.first()?.name)
    }

    @Test
    fun should_set_created_build_as_current() {
        val name = "new"
        model.createBuild(name, false)
        val current = model.currentBuild.value
        assertEquals(name, current?.name)
        assertTrue(model.buildList.value?.contains(current) == true)
    }

    @Test
    fun should_load_last_selected_build_on_next_launch() {
        model.changePerkSystem(PerkSystem.ORDINATOR)
        model.createBuild("new", false)
        val buildId = model.currentBuild.value?.id
        val nextLaunchModel = BuildsViewModel(repo, prefs)
        val build = nextLaunchModel.currentBuild.value
        assertEquals(buildId, build?.id)
        assertEquals(PerkSystem.ORDINATOR, build?.system)
    }

    @Test
    fun should_load_first_available_build_when_last_selected_is_invalid() {
        val name = "new"
        model.createBuild(name, false)
        prefs.selectedBuildId = 100

        val nextLaunchModel = BuildsViewModel(repo, prefs)
        val selectedBuild = nextLaunchModel.currentBuild.value
        val allBuilds = nextLaunchModel.buildList.value
        assertEquals(true, allBuilds?.contains(selectedBuild) == true)
    }

    @Test
    fun should_emit_error_when_trying_to_create_with_duplicated_name() {
        model.createBuild("name", false)
        model.createBuild("name", false)
        assertEquals(false, model.events.value?.getContentIfNotHandled()?.success)
    }

    @Test
    fun should_insert_with_duplicated_name_in_different_perk_system() {
        model.changePerkSystem(PerkSystem.VANILLA)
        model.createBuild("name", false)
        model.changePerkSystem(PerkSystem.ORDINATOR)
        model.createBuild("name", false)
        assertEquals(true, model.events.value?.getContentIfNotHandled()?.success)
    }

    @Test
    fun should_emit_error_when_renaming_with_duplicated_name() {
        model.createBuild("name", false)
        model.createBuild("name2", false)
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name2")
        assertEquals(false, model.events.value?.getContentIfNotHandled()?.success)
    }

    @Test
    fun should_emit_success_when_renaming_with_same_name() {
        model.createBuild("name", false)
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name")
        assertEquals(true, model.events.value?.getContentIfNotHandled()?.success)
    }

    @Test
    fun should_emit_success_when_renaming_with_new_unique_name() {
        model.createBuild("name", false)
        val buildToRename = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToRename!!, "name2")
        assertEquals(true, model.events.value?.getContentIfNotHandled()?.success)
    }

    @Test
    fun should_persist_changes_after_exit() = runBlocking {
        model.createBuild("name0", false)
        model.createBuild("name", false)

        val buildToEdit = model.buildList.value?.find { it.name == "name" }
        model.renameBuild(buildToEdit!!, "name2")
        model.updateDescription(buildToEdit!!, "new description")
        model.changePerkState(EMainSkill.SKILL_ALCHEMY, Alchemy.VAN_ALC_ALCHEMIST, 2)

        val nextLaunchModel = BuildsViewModel(repo, prefs)
        val allBuilds = nextLaunchModel.buildList.value
        val currentBuild = nextLaunchModel.currentBuild.value!!

        assertEquals(1, allBuilds?.filter { it.name == "name2" }?.size)
        assertEquals(true, allBuilds?.none { it.name == "name" })
        assertEquals(buildToEdit.id, currentBuild.id)
        assertEquals(buildToEdit.description, currentBuild.description)
        assertEquals(
            2,
            currentBuild.getSkill(EMainSkill.SKILL_ALCHEMY)[Alchemy.VAN_ALC_ALCHEMIST]?.state
        )
    }

    @Test
    fun should_not_allow_deleting_the_only_existing_build() {
        assertEquals(1, model.buildList.value?.size)
        assertFalse(model.canDeleteBuild())
        model.createBuild("name0", false)
        assertTrue(model.canDeleteBuild())
    }

    @Test
    fun should_select_previous_build_after_deleting_current() {
        model.createBuild("name0", false)
        model.createBuild("name1", false)
        model.deleteBuild(model.currentBuild.value!!.id)
        assertEquals("name0", model.currentBuild.value!!.name)
    }

    @Test
    fun should_select_next_build_after_deleting_current() {
        model.createBuild("name0", false)
        model.createBuild("name1", false)
        model.selectBuild(model.buildList.value!!.first())
        model.deleteBuild(model.currentBuild.value!!.id)
        assertEquals("name0", model.currentBuild.value!!.name)
    }
}