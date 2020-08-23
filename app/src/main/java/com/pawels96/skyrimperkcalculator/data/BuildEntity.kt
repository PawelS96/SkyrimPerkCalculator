package com.pawels96.skyrimperkcalculator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem

@Entity
data class BuildEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var name: String,
        var description: String,
        val perkSystem: PerkSystem,
        val mainSkills: Map<String, Map<String, Int>>,
        val vampirePerkSystem: VampirePerkSystem,
        val vampireSkills: Map<VampirePerkSystem, Map<String, Int>>,
        val werewolfPerkSystem: WerewolfPerkSystem,
        val werewolfSkills: Map<WerewolfPerkSystem, Map<String, Int>>
)