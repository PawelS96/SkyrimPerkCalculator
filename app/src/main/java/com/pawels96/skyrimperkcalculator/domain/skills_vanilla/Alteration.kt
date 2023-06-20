package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Alteration(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ALT_NOVICE_ALTERATION             (0.5f,     0.95f,    0),
    VAN_ALT_ALTERATION_DUAL_CASTING       (0.35f,    0.75f,    20),
    VAN_ALT_APPRENTICE_ALTERATION         (0.525f,   0.675f,   25),
    VAN_ALT_MAGIC_RESISTANCE              (0.7f,     0.45f,    30, 50, 70),
    VAN_ALT_ADEPT_ALTERATION              (0.525f,   0.375f,   50),
    VAN_ALT_EXPERT_ALTERATION             (0.65f,    0.25f,    75),
    VAN_ALT_ATRONACH                      (0.3f,     0.1f,     100),
    VAN_ALT_MASTER_ALTERATION             (0.8f,     0.15f,    100),
    VAN_ALT_STABILITY                     (0.375f,   0.25f,    70),
    VAN_ALT_MAGE_ARMOR                    (0.35f,    0.45f,    30, 50, 70);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ALT_NOVICE_ALTERATION -> listOf(
                VAN_ALT_ALTERATION_DUAL_CASTING,
                VAN_ALT_APPRENTICE_ALTERATION
            )
            VAN_ALT_APPRENTICE_ALTERATION -> listOf(
                VAN_ALT_MAGIC_RESISTANCE,
                VAN_ALT_ADEPT_ALTERATION,
                VAN_ALT_MAGE_ARMOR
            )
            VAN_ALT_ADEPT_ALTERATION -> listOf(VAN_ALT_STABILITY, VAN_ALT_EXPERT_ALTERATION)
            VAN_ALT_EXPERT_ALTERATION -> listOf(VAN_ALT_MASTER_ALTERATION, VAN_ALT_ATRONACH)
            else -> emptyList()
        }
}