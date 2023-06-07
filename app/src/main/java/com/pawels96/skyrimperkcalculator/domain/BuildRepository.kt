package com.pawels96.skyrimperkcalculator.domain

interface BuildRepository {
    suspend fun insert(build: Build): Build?

    suspend fun delete(id: Long): Boolean

    suspend fun update(build: Build): Boolean

    suspend fun getByPerkSystem(system: PerkSystem): List<Build>

    suspend fun isNameAvailable(name: String, perkSystem: PerkSystem): Boolean

    suspend fun getById(id: Long): Build?
}

suspend fun BuildRepository.populate() {
    PerkSystem.values().forEach {
        if (getByPerkSystem(it).isEmpty()) {
            insert(Build.create(it))
        }
    }
}