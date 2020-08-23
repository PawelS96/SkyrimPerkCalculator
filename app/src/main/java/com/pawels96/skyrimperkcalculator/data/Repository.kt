package com.pawels96.skyrimperkcalculator.data

import android.util.Log
import androidx.room.Database
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlin.system.measureTimeMillis

class Repository(private val dao: BuildDAO) {

    fun insert(build: Build) = dao.insert(BuildMapper.toEntity(build)) > 0
    fun insert(builds: List<Build>) = dao.insert(builds.map { BuildMapper.toEntity(it) }).isNotEmpty()
    fun delete(build: Build) = dao.delete(build.id) > 0
    fun update(build: Build) = dao.update(BuildMapper.toEntity(build)) > 0

    fun getByPerkSystem(perkSystem: PerkSystem, insertIfEmpty : Boolean) : List<Build> {

        var builds = dao.getAllByPerkSystem(perkSystem)

        if (builds.isEmpty() && insertIfEmpty) {
            insert(Build.create(perkSystem))
            builds = dao.getAllByPerkSystem(perkSystem)
        }

        return builds.map { BuildMapper.fromEntity(it) }
    }

    fun isNameAvailable(name: String, perkSystem: PerkSystem) : Boolean {
       return dao.getByName(name,perkSystem) == null
    }

    fun getByNameOrDefault(name: String, perkSystem: PerkSystem) : Build{

        val byName = dao.getByName(name,perkSystem) ?: dao.getFirst(perkSystem)

        return if (byName == null){
            insert(Build.create(perkSystem))
            BuildMapper.fromEntity(dao.getFirst(perkSystem)!!)
        }
        else BuildMapper.fromEntity(byName)
    }

    fun count() = dao.count()

    fun populate() {
        PerkSystem.values().forEach {
            if (dao.countByPerkSystem(it) == 0)
                dao.insert(BuildMapper.toEntity(Build.create(it)))
        }
    }
}
