package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Archery(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ARC_MARKSMAN   (0.75f,  0.95f,  10, 50),
    ADA_ARC_QUICK_DRAW (0.625f, 0.725f, 30, 70),
    ADA_ARC_POWER_SHOT (0.5f,   0.5f,   40),
    ADA_ARC_RANGER     (0.375f, 0.275f, 60),
    ADA_ARC_EAGLE_EYE  (0.425f, 0.775f, 20, 70),
    ADA_ARC_GRIM_FOCUS (0.3f,   0.55f,  40, 90),
    ADA_ARC_STEADY_HAND(0.175f, 0.325f, 80),
    ADA_ARC_BULLSEYE   (0.25f,  0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_ARC_MARKSMAN -> listOf(ADA_ARC_EAGLE_EYE, ADA_ARC_QUICK_DRAW)
            ADA_ARC_EAGLE_EYE -> listOf(ADA_ARC_GRIM_FOCUS)
            ADA_ARC_GRIM_FOCUS -> listOf(ADA_ARC_STEADY_HAND)
            ADA_ARC_STEADY_HAND -> listOf(ADA_ARC_BULLSEYE)
            ADA_ARC_QUICK_DRAW -> listOf(ADA_ARC_POWER_SHOT)
            ADA_ARC_POWER_SHOT -> listOf(ADA_ARC_RANGER)
            ADA_ARC_RANGER -> listOf(ADA_ARC_BULLSEYE)
            else -> emptyList()
        }
}