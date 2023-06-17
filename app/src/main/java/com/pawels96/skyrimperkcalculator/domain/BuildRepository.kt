package com.pawels96.skyrimperkcalculator.domain

import kotlinx.coroutines.flow.Flow

interface BuildRepository {
    suspend fun insert(build: Build): Build?

    suspend fun delete(id: Long): Boolean

    suspend fun update(build: Build): Boolean

    suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean

    suspend fun getByPerkSystem(perkSystem: PerkSystem): List<Build>

    suspend fun getCountByPerkSystem(perkSystem: PerkSystem): Int

    fun observeByPerkSystem(perkSystem: PerkSystem): Flow<List<Build>>

    fun observeById(id: Long): Flow<Build?>
}

suspend fun BuildRepository.populate() {
    PerkSystem.values().forEach {
        if (getCountByPerkSystem(it) == 0) {
            insert(Build.create(it))
        }
    }
}