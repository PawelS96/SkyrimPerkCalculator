package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Destruction(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_DES_NOVICE_DESTRUCTION          (0.4f,    0.95f,  0),
    VAN_DES_APPRENTICE_DESTRUCTION      (0.7f,    0.65f,  25),
    VAN_DES_ADEPT_DESTRUCTION           (0.7f,    0.4f,   50),
    VAN_DES_EXPERT_DESTRUCTION          (0.75f,   0.3f,   75),
    VAN_DES_MASTER_DESTRUCTION          (0.75f,   0.1f,   100),
    VAN_DES_RUNE_MASTER                 (0.8f,    0.55f,  40),
    VAN_DES_AUGMENTED_FLAMES            (0.1f,    0.6f,   30, 60),
    VAN_DES_INTENSE_FLAMES              (0.1f,    0.4f,   50),
    VAN_DES_AUGMENTED_FROST             (0.3f,    0.55f,  30, 60),
    VAN_DES_DEEP_FREEZE                 (0.3f,    0.35f,  60),
    VAN_DES_AUGMENTED_SHOCK             (0.5f,    0.5f,   30, 60),
    VAN_DES_DISINTEGRATE                (0.5f,    0.3f,   70),
    VAN_DES_DESTRUCTION_DUAL_CASTING    (0.8f,    0.8f,   20),
    VAN_DES_IMPACT                      (0.9f,    0.65f,  40);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_DES_NOVICE_DESTRUCTION -> listOf(
                VAN_DES_DESTRUCTION_DUAL_CASTING,
                VAN_DES_AUGMENTED_SHOCK,
                VAN_DES_AUGMENTED_FROST,
                VAN_DES_AUGMENTED_FLAMES,
                VAN_DES_APPRENTICE_DESTRUCTION
            )
            VAN_DES_APPRENTICE_DESTRUCTION -> listOf(VAN_DES_RUNE_MASTER, VAN_DES_ADEPT_DESTRUCTION)
            VAN_DES_ADEPT_DESTRUCTION -> listOf(VAN_DES_EXPERT_DESTRUCTION)
            VAN_DES_EXPERT_DESTRUCTION -> listOf(VAN_DES_MASTER_DESTRUCTION)
            VAN_DES_AUGMENTED_FLAMES -> listOf(VAN_DES_INTENSE_FLAMES)
            VAN_DES_AUGMENTED_FROST -> listOf(VAN_DES_DEEP_FREEZE)
            VAN_DES_AUGMENTED_SHOCK -> listOf(VAN_DES_DISINTEGRATE)
            VAN_DES_DESTRUCTION_DUAL_CASTING -> listOf(VAN_DES_IMPACT)
            else -> emptyList()
        }
}