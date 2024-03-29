package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Pickpocket(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_PCK_LIGHT_FINGERS                 (0.4f ,  0.95f,   0, 20, 40, 60, 80),
    VAN_PCK_NIGHT_THIEF                   (0.5f,   0.75f,   30),
    VAN_PCK_CUTPURSE                      (0.6f,   0.45f,   40),
    VAN_PCK_KEYMASTER                     (0.45f,  0.35f,   60),
    VAN_PCK_MISDIRECTION                  (0.65f,  0.2f,    70),
    VAN_PCK_PERFECT_TOUCH                 (0.7f,   0.1f,    100),
    VAN_PCK_EXTRA_POCKETS                 (0.75f,  0.5f,    50),
    VAN_PCK_POISONED                      (0.4f,   0.5f ,   40);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_PCK_LIGHT_FINGERS -> listOf(VAN_PCK_NIGHT_THIEF)
            VAN_PCK_NIGHT_THIEF -> listOf(VAN_PCK_POISONED, VAN_PCK_EXTRA_POCKETS, VAN_PCK_CUTPURSE)
            VAN_PCK_CUTPURSE -> listOf(VAN_PCK_KEYMASTER, VAN_PCK_MISDIRECTION)
            VAN_PCK_MISDIRECTION -> listOf(VAN_PCK_PERFECT_TOUCH)
            else -> emptyList()
        }
}