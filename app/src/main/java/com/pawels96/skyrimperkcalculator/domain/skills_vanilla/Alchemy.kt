package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Alchemy(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ALC_ALCHEMIST                   (0.25f,     0.95f,   0, 20, 40, 60, 80),
    VAN_ALC_PHYSICIAN                   (0.75f,     0.85f,   20),
    VAN_ALC_BENEFACTOR                  (0.65f,     0.65f,   30),
    VAN_ALC_EXPERIMENTER                (0.6f,      0.5f,    50, 70, 90),
    VAN_ALC_POISONER                    (0.3f,      0.65f,   30),
    VAN_ALC_CONCENTRATED_POISON         (0.35f,     0.45f,   60),
    VAN_ALC_GREEN_THUMB                 (0.4f,      0.25f,   70),
    VAN_ALC_SNAKEBLOOD                  (0.6f,      0.2f,    80),
    VAN_ALC_PURITY                      (0.525f,    0.075f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ALC_ALCHEMIST -> listOf(VAN_ALC_PHYSICIAN)
            VAN_ALC_PHYSICIAN -> listOf(VAN_ALC_BENEFACTOR, VAN_ALC_POISONER)
            VAN_ALC_BENEFACTOR -> listOf(VAN_ALC_EXPERIMENTER)
            VAN_ALC_EXPERIMENTER -> listOf(VAN_ALC_SNAKEBLOOD)
            VAN_ALC_POISONER -> listOf(VAN_ALC_CONCENTRATED_POISON)
            VAN_ALC_CONCENTRATED_POISON -> listOf(VAN_ALC_GREEN_THUMB, VAN_ALC_SNAKEBLOOD)
            VAN_ALC_SNAKEBLOOD -> listOf(VAN_ALC_PURITY)
            else -> emptyList()
        }
}