package com.pawels96.skyrimperkcalculator

import android.content.Context
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import androidx.test.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.data.Repository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy
import com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    val context: Context = InstrumentationRegistry.getTargetContext()
    var db = AppDatabase.getInMemoryDatabase(context)
    var repo = Repository(db.buildDAO())

    @Before
    fun reset(){
        db = AppDatabase.getInMemoryDatabase(context)
        repo = Repository(db.buildDAO())
    }

    @Test
    fun insertAndGetBuildByPerkSystem(){

        val build = Build.create(PerkSystem.VANILLA, 1)
        build.getSkill(EMainSkill.SKILL_ALCHEMY)[Alchemy.VAN_ALC_PURITY]!!.nextState()
        repo.insert(build)

        val fromDb = repo.getByPerkSystem(PerkSystem.VANILLA, false)[0]
        assertEquals(build, fromDb)

        build.getSkill(EMainSkill.SKILL_ALCHEMY)[Alchemy.VAN_ALC_PURITY]!!.nextState()
        assertNotSame(build, fromDb)
    }

    @Test
    fun insertAndCheckNameAvailability(){

        val ps = PerkSystem.VANILLA
        val nname = "test"

        val build = Build.create(ps, 1).apply { name = nname }
        repo.insert(build)
        assertFalse(repo.isNameAvailable(nname,ps ))
    }

    @Test
    fun getByInvalidName_shouldInsertAndReturnDefaultBuild(){
        assertEquals(DEFAULT_BUILD_NAME, repo.getByNameOrDefault("invalid", PerkSystem.VANILLA).name)
    }

    @Test
    fun getFromEmptyDatabase_shouldInsertAndReturnDefaults(){
        assertEquals(0, repo.count())
        val fromDB = PerkSystem.values().map { repo.getByPerkSystem(it, true)  }.toList().flatten()
        assertEquals(PerkSystem.values().toList(), fromDB.map { it.system }.toList())
        assertEquals(PerkSystem.values().size, repo.count())
    }

    @Test
    fun changeVampirePerkSystemTest(){
        val ps = PerkSystem.VOKRII

        repo.insert(Build.create(ps).apply { name = "test"; vampirePerkSystem = VampirePerkSystem.VANILLA })
        var fromDb = repo.getByPerkSystem(ps, false)[0]

        fromDb.vampirePerkSystem = VampirePerkSystem.SACROSANCT
        repo.update(fromDb)

        var fromDb2 = repo.getByPerkSystem(ps, false)[0]
        assertEquals(VampirePerkSystem.SACROSANCT, fromDb2.vampirePerkSystem)
    }

    @Test
    fun rename(){

        val oldName = "test"
        val ps = PerkSystem.VANILLA
        repo.insert(Build.create(ps).apply { name = oldName })

        var fromDb = repo.getByPerkSystem(ps, false)[0]
        fromDb.name = "newName"
        val updated = repo.update(fromDb)
        assert(updated)

        val fromDb2 = repo.getByPerkSystem(ps, false)[0]
        assertEquals("newName", fromDb2.name)
        assertEquals(true, repo.isNameAvailable(oldName, ps ))
    }

}