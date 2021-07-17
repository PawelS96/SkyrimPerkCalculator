package com.pawels96.skyrimperkcalculator

import android.content.Context
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.data.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy
import com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BuildRepositoryTest {

    private lateinit var db: AppDatabase
    private lateinit var repo: BuildRepository

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        db = AppDatabase.getInMemoryDatabase(context)
        repo = BuildRepository(db.buildDAO())
    }

    @Test
    fun should_insert_and_return_identical_object() {

        val build = Build.create(PerkSystem.VANILLA, 1)
        build.getSkill(EMainSkill.SKILL_ALCHEMY)[Alchemy.VAN_ALC_PURITY]!!.nextState()
        repo.insert(build)

        val fromDb = repo.getByPerkSystem(PerkSystem.VANILLA, false)[0]
        assertEquals(build, fromDb)
    }

    @Test
    fun should_return_false_when_name_is_taken() {

        val ps = PerkSystem.VANILLA
        val nname = "test"

        val build = Build.create(ps, 1).apply { name = nname }
        repo.insert(build)
        assertFalse(repo.isNameAvailable(nname, ps))
    }

    @Test
    fun should_return_default_build() {
        assertEquals(DEFAULT_BUILD_NAME, repo.getByIdOrDefault(500L, PerkSystem.VANILLA).name)
    }

    @Test
    fun should_return_default_builds() {
        assertEquals(0, repo.count())
        val fromDB = PerkSystem.values().map { repo.getByPerkSystem(it, true) }.toList().flatten()
        assertEquals(PerkSystem.values().toList(), fromDB.map { it.system }.toList())
        assertEquals(PerkSystem.values().size, repo.count())
    }

    @Test
    fun should_change_perk_system_and_return_correct_build() {
        val ps = PerkSystem.VOKRII
        val build = Build.create(ps).apply {
            name = "test"
            vampirePerkSystem = VampirePerkSystem.VANILLA
        }

        repo.insert(build)
        val fromDb = repo.getByPerkSystem(ps, false)[0]

        fromDb.vampirePerkSystem = VampirePerkSystem.SACROSANCT
        repo.update(fromDb)

        val fromDb2 = repo.getByPerkSystem(ps, false)[0]
        assertEquals(VampirePerkSystem.SACROSANCT, fromDb2.vampirePerkSystem)
    }

    @Test
    fun should_set_new_name_and_make_old_name_available_for_use() {

        val oldName = "test"
        val ps = PerkSystem.VANILLA
        repo.insert(Build.create(ps).apply { name = oldName })

        val fromDb = repo.getByPerkSystem(ps, false)[0]
        fromDb.name = "newName"
        val updated = repo.update(fromDb)
        assert(updated)

        val fromDb2 = repo.getByPerkSystem(ps, false)[0]
        assertEquals("newName", fromDb2.name)
        assertEquals(true, repo.isNameAvailable(oldName, ps))
    }

}