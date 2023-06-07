package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

class FakeBuildRepository: BuildRepository {

    private var id = 1L
    private val builds = mutableListOf<Build>()

    override suspend fun insert(build: Build): Build? {
        val buildWithId = build.copy(id = id++)
        builds.add(buildWithId)
        return buildWithId
    }

    override suspend fun delete(id: Long): Boolean {
        return builds.removeIf { it.id == id }
    }

    override suspend fun update(build: Build): Boolean {
        val index = builds.indexOfFirst { it.id == build.id }
        if (index == -1) {
            return false
        }
        builds[index] = build.copy()
        return true
    }

    override suspend fun getByPerkSystem(system: PerkSystem): List<Build> {
        return builds.filter { it.system == system }.map { it.copy() }
    }

    override suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean {
        return builds.none { it.name == name && it.system == perkSystem }
    }

    override suspend fun getById(id: Long): Build? {
        return builds.firstOrNull { it.id == id }?.copy()
    }
}