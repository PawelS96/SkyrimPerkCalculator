package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeBuildRepository : BuildRepository {

    private var id = 1L
    private val builds = MutableStateFlow<List<Build>>(emptyList())

    override suspend fun insert(build: Build): Build {
        val buildWithId = build.copy(id = id++)
        val updatedList = builds.value.toMutableList()
        updatedList.add(buildWithId)
        builds.value = updatedList
        return buildWithId
    }

    override suspend fun delete(id: Long): Boolean {
        val updatedList = builds.value.toMutableList()
        val didDelete = updatedList.removeIf { it.id == id }
        builds.value = updatedList
        return didDelete
    }

    override suspend fun update(build: Build): Boolean {
        val index = builds.value.indexOfFirst { it.id == build.id }
        if (index == -1) {
            return false
        }
        val updatedList = builds.value.toMutableList()
        updatedList[index] = build.copy()
        builds.value = updatedList
        return true
    }

    override suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean {
        return builds.value.none { it.name == name && it.system == perkSystem }
    }

    override fun observeById(id: Long): Flow<Build?> {
        return builds.map { it.find { b -> b.id == id } }
    }

    override fun observeByPerkSystem(perkSystem: PerkSystem): Flow<List<Build>> {
        return builds.map { it.filter { b -> b.system == perkSystem } }
    }

    override suspend fun getByPerkSystem(perkSystem: PerkSystem): List<Build> {
        return builds.value.filter { b -> b.system == perkSystem }
    }
}