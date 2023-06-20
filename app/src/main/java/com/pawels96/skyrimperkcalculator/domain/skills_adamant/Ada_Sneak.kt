package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Sneak(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_SNK_AGENT          (0.5f,   0.95f,  10, 50),
    ADA_SNK_SILENT_CASTING (0.3f,   0.7f,   20),
    ADA_SNK_SHADOW_CASTING (0.3f,   0.425f, 40, 70),
    ADA_SNK_DEADLY_AIM     (0.5f,   0.5f,   30, 60),
    ADA_SNK_MERCILESS      (0.7f,   0.7f,   20),
    ADA_SNK_BACKSTAB       (0.8f,   0.5f,   40),
    ADA_SNK_ASSASSINS_BLADE(0.7f,   0.3f,   70, 90),
    ADA_SNK_TRESPASSER     (0.1f,   0.7f,   30),
    ADA_SNK_INFILTRATOR    (0.1f,   0.4f,   60),
    ADA_SNK_HIDDEN_THREAT  (0.45f,  0.25f,  80),
    ADA_SNK_LIVING_SHADOW  (0.675f, 0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_SNK_AGENT -> listOf(
                ADA_SNK_TRESPASSER,
                ADA_SNK_SILENT_CASTING,
                ADA_SNK_DEADLY_AIM,
                ADA_SNK_MERCILESS
            )
            ADA_SNK_TRESPASSER -> listOf(ADA_SNK_INFILTRATOR)
            ADA_SNK_INFILTRATOR -> listOf(ADA_SNK_HIDDEN_THREAT)
            ADA_SNK_HIDDEN_THREAT -> listOf(ADA_SNK_LIVING_SHADOW)
            ADA_SNK_SILENT_CASTING -> listOf(ADA_SNK_SHADOW_CASTING)
            ADA_SNK_MERCILESS -> listOf(ADA_SNK_BACKSTAB)
            ADA_SNK_BACKSTAB -> listOf(ADA_SNK_ASSASSINS_BLADE)
            else -> emptyList()
        }
}
