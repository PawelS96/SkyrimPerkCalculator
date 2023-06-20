package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_TwoHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_TWH_CHAMPION       (0.5f,   0.95f,  10, 50),
    ADA_TWH_WARRIORS_STANCE(0.5f,   0.7f,   30, 60),
    ADA_TWH_OVERPOWER      (0.4f,   0.35f,  40),
    ADA_TWH_MASSACRE       (0.6f,   0.35f,  80),
    ADA_TWH_RAMPAGE        (0.5f,   0.05f,  100),
    ADA_TWH_CLEAVE         (0.5f,   0.275f, 70),
    ADA_TWH_DEEP_CUTS      (0.3f,   0.6f,   20, 70),
    ADA_TWH_MORTAL_WOUND   (0.2f,   0.4f,   40, 90),
    ADA_TWH_RIP_AND_TEAR   (0.65f,  0.625f, 20, 70),
    ADA_TWH_REND_AND_RAKE  (0.75f,  0.4f,   40, 90),
    ADA_TWH_ARMOR_CRUSHER  (0.775f, 0.7f,   20, 70),
    ADA_TWH_SKULL_CRACKER  (0.875f, 0.475f, 40, 90);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_TWH_CHAMPION -> listOf(
                ADA_TWH_DEEP_CUTS,
                ADA_TWH_WARRIORS_STANCE,
                ADA_TWH_RIP_AND_TEAR,
                ADA_TWH_ARMOR_CRUSHER
            )
            ADA_TWH_DEEP_CUTS -> listOf(ADA_TWH_MORTAL_WOUND)
            ADA_TWH_WARRIORS_STANCE -> listOf(ADA_TWH_OVERPOWER, ADA_TWH_CLEAVE, ADA_TWH_MASSACRE)
            ADA_TWH_OVERPOWER -> listOf(ADA_TWH_RAMPAGE)
            ADA_TWH_MASSACRE -> listOf(ADA_TWH_RAMPAGE)
            ADA_TWH_RIP_AND_TEAR -> listOf(ADA_TWH_REND_AND_RAKE)
            ADA_TWH_ARMOR_CRUSHER -> listOf(ADA_TWH_SKULL_CRACKER)
            else -> emptyList()
        }
}