package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class HeavyArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_HAR_JUGGERNAUT         (0.5f,   0.95f,  0, 20, 40, 60, 80),
    VAN_HAR_FISTS_OF_STEEL     (0.3f,   0.75f,  30),
    VAN_HAR_CUSHIONED          (0.2f,   0.5f,   50),
    VAN_HAR_CONDITIONING       (0.2f,   0.25f,  70),
    VAN_HAR_WELL_FITTED        (0.7f,   0.75f,  30),
    VAN_HAR_TOWER_OF_STRENGTH  (0.75f,  0.45f,  50),
    VAN_HAR_MATCHING_SET       (0.8f,   0.3f,   70),
    VAN_HAR_REFLECT_BLOWS      (0.75f,  0.1f,   100);

    override val perkInfo: PerkInfo  = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_HAR_JUGGERNAUT -> listOf(VAN_HAR_FISTS_OF_STEEL, VAN_HAR_WELL_FITTED)
            VAN_HAR_FISTS_OF_STEEL -> listOf(VAN_HAR_CUSHIONED)
            VAN_HAR_CUSHIONED -> listOf(VAN_HAR_CONDITIONING)
            VAN_HAR_WELL_FITTED -> listOf(VAN_HAR_TOWER_OF_STRENGTH)
            VAN_HAR_TOWER_OF_STRENGTH -> listOf(VAN_HAR_MATCHING_SET)
            VAN_HAR_MATCHING_SET -> listOf(VAN_HAR_REFLECT_BLOWS)
            else -> emptyList()
        }
}