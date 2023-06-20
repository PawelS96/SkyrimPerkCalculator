package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Illusion(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ILU_ILLUSION_MASTERY          (0.5f,    0.95f,  0, 20),
    ORD_ILU_DREAM_THIEF               (0.85f,   0.78f,  20),
    ORD_ILU_ILLUSION_DUAL_CASTING     (0.2f,    0.88f,  20),
    ORD_ILU_COMMANDING_PRESENCE       (0.7f,    0.65f,  30),
    ORD_ILU_ENTICE_BARTER             (0.15f,   0.6f,   30),
    ORD_ILU_IMPOSING_PRESENCE         (0.45f,   0.72f,  30),
    ORD_ILU_NIGHT_EYE                 (0.3f,    0.8f,   30),
    ORD_ILU_CROWN_OF_THE_FALSE_KING   (0.6f,    0.45f,  40),
    ORD_ILU_KINDRED_MAGE              (0.9f,    0.6f,   40),
    ORD_ILU_QUIET_BEFORE_THE_STORM    (0.8f,    0.88f,  40, 70),
    ORD_ILU_WILTING                   (0.4f,    0.6f,   40),
    ORD_ILU_FICKLE_FATE               (0.8f,    0.5f,   50),
    ORD_ILU_NEMESIS                   (0.1f,    0.45f,  50),
    ORD_ILU_TERROR                    (0.38f,   0.4f,   50),
    ORD_ILU_IMPERIOUS_SPLENDOR        (0.65f,   0.3f,   60),
    ORD_ILU_MASTER_OF_THE_MIND        (0.78f,   0.4f,   60),
    ORD_ILU_PANDEMONIUM               (0.35f,   0.25f,  60),
    ORD_ILU_DREAM_CHARM               (0.9f,    0.3f,   70),
    ORD_ILU_NEVERWORLD                (0.25f,   0.45f,  70),
    ORD_ILU_SHADOW_REFUGE             (0.55f,   0.575f, 70),
    ORD_ILU_THE_REAPER_COMES          (0.5f,    0.35f,  70),
    ORD_ILU_LAMB_TO_THE_SLAUGHTER     (0.52f,   0.2f,   80),
    ORD_ILU_PROTECT_YOUR_GOD          (0.7f,    0.15f,  80),
    ORD_ILU_SOULCRUSHER               (0.22f,   0.33f,  80),
    ORD_ILU_BLIND_GUARDIAN            (0.1f,    0.25f,  90),
    ORD_ILU_DREAM_GEAS                (0.85f,   0.1f,   90),
    ORD_ILU_GHOST_OF_THE_TENTH_EYE    (0.15f,   0.72f,  90),
    ORD_ILU_HEAVY_WEIGHS_THE_TAPESTRY (0.45f,   0.1f,   90),
    ORD_ILU_NIGHTFALL                 (0.22f,   0.18f,  90),
    ORD_ILU_WRAITHWALKER              (0.2f,    0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ILU_ILLUSION_MASTERY -> listOf(
                ORD_ILU_ILLUSION_DUAL_CASTING,
                ORD_ILU_NIGHT_EYE,
                ORD_ILU_ENTICE_BARTER,
                ORD_ILU_IMPOSING_PRESENCE,
                ORD_ILU_COMMANDING_PRESENCE,
                ORD_ILU_DREAM_THIEF,
                ORD_ILU_QUIET_BEFORE_THE_STORM
            )
            ORD_ILU_DREAM_THIEF -> listOf(ORD_ILU_KINDRED_MAGE)
            ORD_ILU_COMMANDING_PRESENCE -> listOf(ORD_ILU_CROWN_OF_THE_FALSE_KING)
            ORD_ILU_ENTICE_BARTER -> listOf(ORD_ILU_NEMESIS)
            ORD_ILU_IMPOSING_PRESENCE -> listOf(
                ORD_ILU_NEMESIS,
                ORD_ILU_WILTING,
                ORD_ILU_SHADOW_REFUGE
            )
            ORD_ILU_NIGHT_EYE -> listOf(ORD_ILU_GHOST_OF_THE_TENTH_EYE)
            ORD_ILU_CROWN_OF_THE_FALSE_KING -> listOf(ORD_ILU_IMPERIOUS_SPLENDOR)
            ORD_ILU_KINDRED_MAGE -> listOf(ORD_ILU_FICKLE_FATE, ORD_ILU_DREAM_CHARM)
            ORD_ILU_WILTING -> listOf(ORD_ILU_NEVERWORLD, ORD_ILU_TERROR, ORD_ILU_THE_REAPER_COMES)
            ORD_ILU_FICKLE_FATE -> listOf(ORD_ILU_MASTER_OF_THE_MIND)
            ORD_ILU_NEMESIS -> listOf(ORD_ILU_BLIND_GUARDIAN)
            ORD_ILU_TERROR -> listOf(ORD_ILU_SOULCRUSHER, ORD_ILU_PANDEMONIUM)
            ORD_ILU_IMPERIOUS_SPLENDOR -> listOf(ORD_ILU_PROTECT_YOUR_GOD)
            ORD_ILU_PANDEMONIUM -> listOf(ORD_ILU_NIGHTFALL)
            ORD_ILU_DREAM_CHARM -> listOf(ORD_ILU_DREAM_GEAS)
            ORD_ILU_THE_REAPER_COMES -> listOf(ORD_ILU_LAMB_TO_THE_SLAUGHTER)
            ORD_ILU_LAMB_TO_THE_SLAUGHTER -> listOf(ORD_ILU_HEAVY_WEIGHS_THE_TAPESTRY)
            ORD_ILU_BLIND_GUARDIAN -> listOf(ORD_ILU_WRAITHWALKER)
            ORD_ILU_HEAVY_WEIGHS_THE_TAPESTRY -> listOf(ORD_ILU_WRAITHWALKER)
            else -> emptyList()
        }
}
