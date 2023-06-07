package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultBuildRepository(private val dao: BuildDAO): BuildRepository {

    @OptIn(DelicateCoroutinesApi::class)
    fun insert(builds: List<Build>) = GlobalScope.launch {
        val entities = builds.map { it.toEntity() }
        dao.insert(entities)
    }

    override suspend fun insert(build: Build): Build? {
        val id = dao.insert(build.toEntity())
        return dao.getByID(id)?.toDomain()
    }

    override suspend fun delete(id: Long): Boolean = dao.delete(id) > 0

    override suspend fun update(build: Build): Boolean = dao.update(build.toEntity()) > 0

    override suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean {
        return dao.getByName(name, perkSystem) == null
    }

    override suspend fun getByPerkSystem(system: PerkSystem): List<Build> {
        return dao.getAllByPerkSystem(system).map { it.toDomain() }
    }

    override suspend fun getById(id: Long): Build? {
       return dao.getByID(id)?.toDomain()
    }
}
