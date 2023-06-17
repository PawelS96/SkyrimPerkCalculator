package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.BuildRepository
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DefaultBuildRepository(private val dao: BuildDAO) : BuildRepository {

    private var cachedBuilds = emptyList<Build>()

    override suspend fun insert(build: Build): Build? {
        val id = dao.insert(build.toEntity())
        return dao.getByID(id)?.toDomain()
    }

    override suspend fun delete(id: Long): Boolean = dao.delete(id) > 0

    override suspend fun update(build: Build): Boolean = dao.update(build.toEntity()) > 0

    override suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean {
        return dao.getByName(name, perkSystem) == null
    }

    override suspend fun getByPerkSystem(perkSystem: PerkSystem): List<Build> {
        return dao.observeByPerkSystem(perkSystem).first().map { it.toDomain() }
    }

    override fun observeByPerkSystem(perkSystem: PerkSystem): Flow<List<Build>> {
        val cached = cachedBuilds.filter { it.system == perkSystem }
        val databaseFlow = dao.observeByPerkSystem(perkSystem)
            .map { it.map { build -> build.toDomain() } }
            .onEach { cachedBuilds = it }

        return flow {
            if (cached.isNotEmpty()) {
                emit(cached)
            }
            emitAll(databaseFlow)
        }
    }

    override suspend fun getCountByPerkSystem(perkSystem: PerkSystem): Int {
        return dao.getCountByPerkSystem(perkSystem)
    }

    override fun observeById(id: Long): Flow<Build?> {
        return dao.observeById(id).map { it?.toDomain() }
    }
}
