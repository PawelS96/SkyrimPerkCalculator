package com.pawels96.skyrimperkcalculator

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import com.pawels96.skyrimperkcalculator.data.DefaultBuildRepository
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BuildRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var repo: DefaultBuildRepository

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        db = AppDatabase.getInMemoryDatabase(context)
        repo = DefaultBuildRepository(db.buildDAO())
    }

    @Test
    fun insert_successfulInsert_shouldReturnIdenticalObject() = runTest {
        val build = Build.create(PerkSystem.VANILLA, 1)
        build.getSkill(EMainSkill.SKILL_ALCHEMY)[Alchemy.VAN_ALC_PURITY]!!.nextState()
        val savedBuild = repo.insert(build)
        assertEquals(build, savedBuild)
    }

    @Test
    fun getByPerkSystem_buildsExist_shouldReturnBuilds() = runTest {
        val build1 = Build.create(PerkSystem.VANILLA, 1)
        val build2 = Build.create(PerkSystem.VANILLA, 2)
        repo.insert(build1)
        repo.insert(build2)

        val fromDb = repo.getByPerkSystem(PerkSystem.VANILLA)
        assertEquals(listOf(build1, build2), fromDb)
    }

    @Test
    fun isNameAvailable_nameAvailable_shouldReturnTrue() = runTest {
        val ps = PerkSystem.VANILLA
        val build = Build.create(ps, 1, "name")
        repo.insert(build)
        assertTrue(repo.isNameAvailable("another name", ps))
    }

    @Test
    fun isNameAvailable_nameTaken_shouldReturnFalse() = runTest {
        val ps = PerkSystem.VANILLA
        val buildName = "name"
        val build = Build.create(ps, 1, buildName)
        repo.insert(build)
        assertFalse(repo.isNameAvailable(buildName, ps))
    }

    @Test
    fun isNameAvailable_nameTakenInDifferentPerkSystem_shouldReturnTrue() = runTest {
        val buildName = "name"
        val build = Build.create(PerkSystem.VANILLA, 1, buildName)
        repo.insert(build)
        assertTrue(repo.isNameAvailable(buildName, PerkSystem.ORDINATOR))
    }

    @Test
    fun update_setVampirePerkSystem_shouldUpdateBuild() = runTest {
        val ps = PerkSystem.VOKRII
        val build = Build.create(ps, 1, "name").apply {
            vampirePerkSystem = VampirePerkSystem.VANILLA
        }

        val savedBuild = repo.insert(build)!!
        savedBuild.vampirePerkSystem = VampirePerkSystem.SACROSANCT
        repo.update(savedBuild)

        val updatedBuild = repo.getById(build.id)!!
        assertEquals(VampirePerkSystem.SACROSANCT, updatedBuild.vampirePerkSystem)
    }

    @Test
    fun update_setName_shouldUpdateBuildAndMakeNameAvailableForUse() = runTest {
        val oldName = "name"
        val ps = PerkSystem.VANILLA
        val build = Build.create(ps, 1, oldName)
        val savedBuild = repo.insert(build)!!
        savedBuild.name = "newName"
        repo.update(savedBuild)

        val updatedBuild = repo.getById(build.id)!!
        assertEquals("newName", updatedBuild.name)
        assertEquals(true, repo.isNameAvailable(oldName, ps))
    }
}