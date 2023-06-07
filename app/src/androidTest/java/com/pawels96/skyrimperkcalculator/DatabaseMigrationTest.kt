package com.pawels96.skyrimperkcalculator

import androidx.test.platform.app.InstrumentationRegistry
import com.pawels96.skyrimperkcalculator.data.AppDatabase
import com.pawels96.skyrimperkcalculator.data.OldDatabase
import com.pawels96.skyrimperkcalculator.data.DefaultBuildRepository
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DatabaseMigrationTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var newDb = AppDatabase.getInMemoryDatabase(context)
    private val repo = DefaultBuildRepository(newDb.buildDAO())
    private val oldDb: OldDatabase = OldDatabase(context, repo)

    @Before
    fun bef() {
        oldDb.clear()
        oldDb.onDowngrade(oldDb.writableDatabase, 4, 3)
        newDb = AppDatabase.getInMemoryDatabase(context)
        newDb.clearAllTables()
    }

    private fun checkEquality_excludeID(build1: Build, build2: Build) {
        assertEquals(build1.name, build2.name)
        assertEquals(build1.description, build2.description)
        assertEquals(build1.system, build2.system)
        assertEquals(build1.skills, build2.skills)
        assertEquals(build1.werewolfSkill, build2.werewolfSkill)
        assertEquals(build1.werewolfPerkSystem, build2.werewolfPerkSystem)
        assertEquals(build1.vampireSkill, build2.vampireSkill)
        assertEquals(build1.vampirePerkSystem, build2.vampirePerkSystem)
    }

    @Test
    fun testMigration() = runTest {

        val builds = PerkSystem.values().mapIndexed { index, perkSystem -> Build.create(perkSystem) }
        builds.forEach { oldDb.saveBuild(it) }
        val fromOldDB = PerkSystem.values().map { oldDb.getAllBuilds(it) }.toMutableList().flatten()

        builds.zip(fromOldDB).forEach {
            checkEquality_excludeID(it.first, it.second)
        }

        oldDb.onUpgrade(oldDb.writableDatabase,3, 4)

        val fromNewDB = PerkSystem.values()
            .map { repo.getByPerkSystem(it) }
            .toMutableList()
            .flatten()

        assertEquals(fromOldDB.size, fromNewDB.size)

        fromOldDB.zip(fromNewDB).forEach {
            checkEquality_excludeID(it.first, it.second)
        }
    }
}