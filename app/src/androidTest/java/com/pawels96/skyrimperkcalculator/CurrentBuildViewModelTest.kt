package com.pawels96.skyrimperkcalculator

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.Preferences
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.current_build.CurrentBuildViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentBuildViewModelTest {

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
        val model = CurrentBuildViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.currentBuild.collect()
        }

        advanceUntilIdle()
        assertEquals(Build.DEFAULT_NAME, model.currentBuild.value?.name)
    }

    @Test
    fun should_load_last_selected_build_on_next_launch() = runTest {
        prefs.selectedPerkSystem = PerkSystem.ORDINATOR
        val createdBuild = repo.insert(Build.create(PerkSystem.ORDINATOR))
        advanceUntilIdle()
        val model = CurrentBuildViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.currentBuild.collect()
        }
        advanceUntilIdle()
        val build = model.currentBuild.value
        assertEquals(createdBuild?.id, build?.id)
        assertEquals(PerkSystem.ORDINATOR, build?.system)
    }

    @Test
    fun should_load_first_available_build_when_last_selected_is_invalid() = runTest {
        prefs.selectedBuildId = 100
        val model = CurrentBuildViewModel(repo, prefs)
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            model.currentBuild.collect()
        }
        advanceUntilIdle()
        assertNotNull(model.currentBuild.value)
    }
}
