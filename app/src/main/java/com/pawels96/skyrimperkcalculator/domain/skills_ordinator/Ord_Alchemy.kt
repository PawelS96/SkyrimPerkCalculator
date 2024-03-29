package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Alchemy(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ALC_MASTERY                (0.3f,   0.95f,  0, 20),
    ORD_ALC_STIMULANTS             (0.55f,  0.725f, 20),
    ORD_ALC_PHYSICIAN              (0.85f,  0.9f,   20),
    ORD_ALC_ADVANCED_LAB           (0.25f,  0.7f,   30),
    ORD_ALC_EXPERIMENTER           (0.4f,   0.75f,  30),
    ORD_ALC_POISONER               (0.75f,  0.75f,  30),
    ORD_ALC_CRIMSON_HAZE           (0.55f,  0.55f,  40),
    ORD_ALC_BOTTOMLESS_CUP         (0.7f,   0.5f,   40),
    ORD_ALC_ELEMENTAL_OIL          (0.1f,   0.6f,   40),
    ORD_ALC_ALKAHEST               (0.85f,  0.45f,  50),
    ORD_ALC_LAB_SKEEVER            (0.28f,  0.55f,  50),
    ORD_ALC_GREEN_THUMB            (0.42f,  0.5f,   60),
    ORD_ALC_MAENAD                 (0.6f,   0.375f, 60),
    ORD_ALC_THE_ALCHEMISTS_COOKBOOK(0.15f,  0.4f,   60),
    ORD_ALC_DOUBLE_TOIL            (0.28f,  0.3f,   70),
    ORD_ALC_PURE_MIXTURE           (0.42f,  0.375f, 70),
    ORD_ALC_AMPLIFY_LETHALITY      (0.675f, 0.3f,   80),
    ORD_ALC_WITCHMASTER            (0.5f,   0.275f, 80),
    ORD_ALC_CHYMICAL_WEDDING       (0.35f,  0.2f,   90),
    ORD_ALC_WALKING_DISASTER       (0.15f,  0.25f,  90),
    ORD_ALC_WORLD_SERPENT          (0.67f,  0.17f,  90),
    ORD_ALC_THAT_WHICH             (0.5f,   0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ALC_MASTERY -> listOf(
                ORD_ALC_ELEMENTAL_OIL,
                ORD_ALC_ADVANCED_LAB,
                ORD_ALC_EXPERIMENTER,
                ORD_ALC_STIMULANTS,
                ORD_ALC_POISONER,
                ORD_ALC_PHYSICIAN
            )
            ORD_ALC_ELEMENTAL_OIL -> listOf(ORD_ALC_THE_ALCHEMISTS_COOKBOOK)
            ORD_ALC_THE_ALCHEMISTS_COOKBOOK -> listOf(ORD_ALC_WALKING_DISASTER)
            ORD_ALC_ADVANCED_LAB -> listOf(ORD_ALC_LAB_SKEEVER)
            ORD_ALC_LAB_SKEEVER -> listOf(ORD_ALC_DOUBLE_TOIL, ORD_ALC_GREEN_THUMB)
            ORD_ALC_EXPERIMENTER -> listOf(ORD_ALC_GREEN_THUMB)
            ORD_ALC_GREEN_THUMB -> listOf(ORD_ALC_PURE_MIXTURE)
            ORD_ALC_POISONER -> listOf(ORD_ALC_BOTTOMLESS_CUP, ORD_ALC_ALKAHEST)
            ORD_ALC_BOTTOMLESS_CUP -> listOf(ORD_ALC_AMPLIFY_LETHALITY)
            ORD_ALC_ALKAHEST -> listOf(ORD_ALC_AMPLIFY_LETHALITY)
            ORD_ALC_AMPLIFY_LETHALITY -> listOf(ORD_ALC_WORLD_SERPENT)
            ORD_ALC_STIMULANTS -> listOf(ORD_ALC_CRIMSON_HAZE)
            ORD_ALC_CRIMSON_HAZE -> listOf(ORD_ALC_MAENAD, ORD_ALC_WITCHMASTER)
            ORD_ALC_WITCHMASTER -> listOf(ORD_ALC_CHYMICAL_WEDDING, ORD_ALC_WORLD_SERPENT)
            ORD_ALC_CHYMICAL_WEDDING -> listOf(ORD_ALC_THAT_WHICH)
            ORD_ALC_WORLD_SERPENT -> listOf(ORD_ALC_THAT_WHICH)
            else -> emptyList()
        }
}