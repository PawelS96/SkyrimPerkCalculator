package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Illusion(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ILU_NOVICE_ILLUSION            (0.5f,  0.95f, 0),
    VAN_ILU_ANIMAGE                    (0.9f,  0.75f, 20),
    VAN_ILU_KINDRED_MAGE               (0.7f,  0.5f,  40),
    VAN_ILU_QUIET_CASTING              (0.7f,  0.25f, 50),
    VAN_ILU_APPRENTICE_ILLUSION        (0.2f,  0.6f,  25),
    VAN_ILU_ADEPT_ILLUSION             (0.25f, 0.4f,  50),
    VAN_ILU_EXPERT_ILLUSION            (0.2f,  0.25f, 75),
    VAN_ILU_MASTER_ILLUSION            (0.35f, 0.1f,  100),
    VAN_ILU_HYPNOTIC_GAZE              (0.45f, 0.55f, 30),
    VAN_ILU_ASPECT_OF_TERROR           (0.4f,  0.3f,  50),
    VAN_ILU_RAGE                       (0.5f,  0.25f, 70),
    VAN_ILU_MASTER_OF_THE_MIND         (0.6f,  0.1f,  90),
    VAN_ILU_ILLUSION_DUAL_CASTING      (0.1f,  0.9f,  20);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ILU_NOVICE_ILLUSION -> listOf(
                VAN_ILU_ILLUSION_DUAL_CASTING,
                VAN_ILU_APPRENTICE_ILLUSION,
                VAN_ILU_HYPNOTIC_GAZE,
                VAN_ILU_ANIMAGE
            )
            VAN_ILU_ANIMAGE -> listOf(VAN_ILU_KINDRED_MAGE)
            VAN_ILU_KINDRED_MAGE -> listOf(VAN_ILU_QUIET_CASTING)
            VAN_ILU_QUIET_CASTING -> listOf(VAN_ILU_MASTER_OF_THE_MIND)
            VAN_ILU_APPRENTICE_ILLUSION -> listOf(VAN_ILU_ADEPT_ILLUSION)
            VAN_ILU_ADEPT_ILLUSION -> listOf(VAN_ILU_EXPERT_ILLUSION)
            VAN_ILU_EXPERT_ILLUSION -> listOf(VAN_ILU_MASTER_ILLUSION)
            VAN_ILU_HYPNOTIC_GAZE -> listOf(VAN_ILU_ASPECT_OF_TERROR)
            VAN_ILU_ASPECT_OF_TERROR -> listOf(VAN_ILU_RAGE)
            VAN_ILU_RAGE -> listOf(VAN_ILU_MASTER_OF_THE_MIND)
            else -> emptyList()
        }
}