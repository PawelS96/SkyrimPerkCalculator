package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Conjuration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_CON_SUMMONER          (0.5f,   0.95f,  10, 50),
    ADA_CON_DARK_OATH         (0.175f, 0.8f,   20, 60),
    ADA_CON_ARMOR_OF_SHADOWS  (0.1f,   0.6f,   30, 80),
    ADA_CON_RITUAL_OF_POWER   (0.125f, 0.35f,  40, 90),
    ADA_CON_DAEDRIC_PACT      (0.2f,   0.175f, 70),
    ADA_CON_UNDEAD_SERVANT    (0.325f, 0.725f, 20, 80),
    ADA_CON_DEATHLY_VIGOR     (0.275f, 0.55f,  30, 70),
    ADA_CON_CORPSE_PREPARATION(0.3f,   0.4f,   40),
    ADA_CON_NECROPOTENCE      (0.375f, 0.225f, 60, 90),
    ADA_CON_DOORS_OF_OBLIVION (0.425f, 0.05f,  100),
    ADA_CON_ARCANE_BINDING    (0.8f,   0.75f,  20),
    ADA_CON_CHAOS_BINDING     (0.875f, 0.55f,  30, 70),
    ADA_CON_MYSTIC_BINDING    (0.825f, 0.35f,  40, 80),
    ADA_CON_CURSE_BINDING     (0.75f,  0.15f,  90),
    ADA_CON_CULTIST           (0.5f,   0.5f,   30, 60);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_CON_SUMMONER -> listOf(
                ADA_CON_DARK_OATH,
                ADA_CON_UNDEAD_SERVANT,
                ADA_CON_CULTIST,
                ADA_CON_ARCANE_BINDING
            )
            ADA_CON_DARK_OATH -> listOf(ADA_CON_ARMOR_OF_SHADOWS)
            ADA_CON_ARMOR_OF_SHADOWS -> listOf(ADA_CON_RITUAL_OF_POWER)
            ADA_CON_RITUAL_OF_POWER -> listOf(ADA_CON_DAEDRIC_PACT)
            ADA_CON_DAEDRIC_PACT -> listOf(ADA_CON_DOORS_OF_OBLIVION)
            ADA_CON_UNDEAD_SERVANT -> listOf(ADA_CON_DEATHLY_VIGOR)
            ADA_CON_DEATHLY_VIGOR -> listOf(ADA_CON_CORPSE_PREPARATION)
            ADA_CON_CORPSE_PREPARATION -> listOf(ADA_CON_NECROPOTENCE)
            ADA_CON_NECROPOTENCE -> listOf(ADA_CON_DOORS_OF_OBLIVION)
            ADA_CON_ARCANE_BINDING -> listOf(ADA_CON_CHAOS_BINDING)
            ADA_CON_CHAOS_BINDING -> listOf(ADA_CON_MYSTIC_BINDING)
            ADA_CON_MYSTIC_BINDING -> listOf(ADA_CON_CURSE_BINDING)
            else -> emptyList()
        }
}