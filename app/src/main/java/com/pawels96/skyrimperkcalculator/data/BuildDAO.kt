package com.pawels96.skyrimperkcalculator.data

import androidx.room.*
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildDAO {

    @Insert
    suspend fun insert(buildEntity: BuildEntity): Long

    @Update
    suspend fun update(buildEntity: BuildEntity): Int

    @Query("DELETE FROM buildentity WHERE id=:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM buildentity WHERE id=:id")
    suspend fun getByID(id: Long): BuildEntity?

    @Query("SELECT * FROM buildentity WHERE name=:name AND perkSystem=:perkSystem")
    suspend fun getByName(name: String, perkSystem: PerkSystem): BuildEntity?

    @Query("SELECT COUNT(*) FROM buildentity WHERE perkSystem=:perkSystem")
    suspend fun getCountByPerkSystem(perkSystem: PerkSystem): Int

    @Query("SELECT * FROM buildentity WHERE perkSystem=:perkSystem")
    fun observeByPerkSystem(perkSystem: PerkSystem): Flow<List<BuildEntity>>

    @Query("SELECT * FROM buildentity WHERE id=:id")
    fun observeById(id: Long): Flow<BuildEntity?>
}

