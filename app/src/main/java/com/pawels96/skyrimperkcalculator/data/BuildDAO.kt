package com.pawels96.skyrimperkcalculator.data

import androidx.room.*
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

@Dao
interface BuildDAO {

    @Insert
    suspend fun insert(buildEntity: BuildEntity): Long

    @Insert
    suspend fun insert(entities: List<BuildEntity>): LongArray

    @Delete
    suspend fun delete(buildEntity: BuildEntity): Int

    @Update
    suspend fun update(buildEntity: BuildEntity): Int

    @Query("SELECT * FROM buildentity WHERE perkSystem=:perkSystem")
    suspend fun getAllByPerkSystem(perkSystem: PerkSystem): List<BuildEntity>

    @Query("DELETE FROM buildentity WHERE id=:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT COUNT(*) FROM buildentity")
    suspend fun count(): Int

    @Query("SELECT COUNT(*) FROM buildentity WHERE perkSystem=:perkSystem")
    suspend fun countByPerkSystem(perkSystem: PerkSystem): Int

    @Query("SELECT * FROM buildentity WHERE id=:id")
    suspend fun getByID(id: Long): BuildEntity?

    @Query("SELECT * FROM buildentity WHERE name=:name AND perkSystem=:perkSystem")
    suspend fun getByName(name: String, perkSystem: PerkSystem): BuildEntity?

    @Query("DELETE FROM buildentity")
    suspend fun clear()
}

