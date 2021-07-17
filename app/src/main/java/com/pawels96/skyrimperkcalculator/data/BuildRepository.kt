package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

class BuildRepository(private val dao: BuildDAO) {

    fun insert(builds: List<Build>): Boolean {
        val entities = builds.map { BuildMapper.toEntity(it) }
        return dao.insert(entities).isNotEmpty()
    }

    fun insert(build: Build): Build? {
        val id = dao.insert(BuildMapper.toEntity(build))
        if (id == -1L) {
            return null
        }
        val savedBuild = dao.getByID(id) ?: return null
        return BuildMapper.fromEntity(savedBuild)
    }

    fun delete(buildId: Long): Boolean = dao.delete(buildId) > 0

    fun update(build: Build): Boolean = dao.update(BuildMapper.toEntity(build)) > 0

    fun getByPerkSystem(perkSystem: PerkSystem, insertIfEmpty: Boolean): List<Build> {

        var builds = dao.getAllByPerkSystem(perkSystem)

        if (builds.isEmpty() && insertIfEmpty) {
            insert(Build.create(perkSystem))
            builds = dao.getAllByPerkSystem(perkSystem)
        }

        return builds.map { BuildMapper.fromEntity(it) }
    }

    fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean {
        return dao.getByName(name, perkSystem) == null
    }

    fun getByIdOrDefault(id: Long, perkSystem: PerkSystem): Build {
        val byId = dao.getByID(id) ?: dao.getFirst(perkSystem)

        if (byId == null) {
            insert(Build.create(perkSystem))
            val first = dao.getFirst(perkSystem)
            return BuildMapper.fromEntity(first!!)
        }

        return BuildMapper.fromEntity(byId)
    }

    fun count() = dao.count()

    fun getById(id: Long): Build? {
        val build = dao.getByID(id) ?: return null
        return BuildMapper.fromEntity(build)
    }

    fun populate() {
        PerkSystem.values().forEach {
            if (dao.countByPerkSystem(it) == 0)
                dao.insert(BuildMapper.toEntity(Build.create(it)))
        }
    }
}
