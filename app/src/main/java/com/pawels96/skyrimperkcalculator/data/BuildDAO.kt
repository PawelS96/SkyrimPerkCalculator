package com.pawels96.skyrimperkcalculator.data

import androidx.room.*
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

@Dao
interface BuildDAO {

    @Insert
    fun insert(buildEntity: BuildEntity): Long

    @Insert
    fun insert(entities: List<BuildEntity>): LongArray

    @Delete
    fun delete(buildEntity: BuildEntity): Int

    @Update
    fun update(buildEntity: BuildEntity): Int

    @Query("SELECT * FROM buildentity WHERE perkSystem=:perkSystem")
    fun getAllByPerkSystem(perkSystem: PerkSystem): List<BuildEntity>

    @Query("DELETE FROM buildentity WHERE id=:id")
    fun delete(id: Long): Int

    @Query("SELECT COUNT(*) FROM buildentity")
    fun count(): Int

    @Query("SELECT COUNT(*) FROM buildentity WHERE perkSystem=:perkSystem")
    fun countByPerkSystem(perkSystem: PerkSystem): Int

    @Query("SELECT * FROM buildentity WHERE id=:id")
    fun getByID(id: Long): BuildEntity?

    @Query("SELECT * FROM buildentity WHERE name=:name AND perkSystem=:perkSystem")
    fun getByName(name: String, perkSystem: PerkSystem): BuildEntity?

    @Query("DELETE FROM buildentity")
    fun clear()

    @Query("SELECT * FROM buildentity WHERE perkSystem=:perkSystem LIMIT 1")
    fun getFirst(perkSystem: PerkSystem): BuildEntity?
}

