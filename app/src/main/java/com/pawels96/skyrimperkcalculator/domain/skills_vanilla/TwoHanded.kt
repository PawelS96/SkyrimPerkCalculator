package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class TwoHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_TWH_BARBARIAN                (0.5f,     0.95f,   0, 20, 40, 60, 80),
    VAN_TWH_CHAMPIONS_STANCE         (0.5f,     0.75f,   20),
    VAN_TWH_DEVASTATING_BLOW         (0.625f,   0.5f,    50),
    VAN_TWH_GREAT_CRITICAL_CHARGE    (0.375f,   0.5f,    50),
    VAN_TWH_SWEEP                    (0.5f,     0.25f,   70),
    VAN_TWH_WARMASTER                (0.5f,     0.05f,   100),
    VAN_TWH_DEEP_WOUNDS              (0.75f,    0.625f,  30, 60, 90),
    VAN_TWH_LIMBSPLITTER             (0.25f,    0.625f,  30, 60, 90),
    VAN_TWH_SKULLCRUSHER             (0.9f,     0.7f,    30, 60, 90);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_TWH_BARBARIAN -> listOf(
                VAN_TWH_CHAMPIONS_STANCE,
                VAN_TWH_SKULLCRUSHER,
                VAN_TWH_DEEP_WOUNDS,
                VAN_TWH_LIMBSPLITTER
            )
            VAN_TWH_CHAMPIONS_STANCE -> listOf(
                VAN_TWH_DEVASTATING_BLOW,
                VAN_TWH_GREAT_CRITICAL_CHARGE
            )
            VAN_TWH_DEVASTATING_BLOW -> listOf(VAN_TWH_SWEEP)
            VAN_TWH_GREAT_CRITICAL_CHARGE -> listOf(VAN_TWH_SWEEP)
            VAN_TWH_SWEEP -> listOf(VAN_TWH_WARMASTER)
            else -> emptyList()
        }
}