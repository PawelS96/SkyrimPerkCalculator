package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_LightArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_LAR_LIGHT_ARMOR_MASTERY      (0.6f,     0.95f,  0, 20),
    ORD_LAR_IRON_FIST                (0.35f,    0.75f,  20, 50, 80),
    ORD_LAR_ANNOYING_MOSQUITOES      (0.35f,    0.88f,  20, 50),
    ORD_LAR_AS_A_LEAF                (0.75f,    0.67f,  30),
    ORD_LAR_LIGHT_ARMOR_FIT          (0.5f,     0.7f,   30),
    ORD_LAR_INITIATIVE               (0.475f,   0.5f,   40, 70),
    ORD_LAR_KEEN_SENSES              (0.75f,    0.45f,  40),
    ORD_LAR_SWEEPING_WIND            (0.2f,     0.6f,   40),
    ORD_LAR_RUSHING_TIDE             (0.1f,     0.4f,   50),
    ORD_LAR_UNHINDERED               (0.25f,    0.5f,   50),
    ORD_LAR_WINDRUNNER               (0.525f,   0.35f,  50),
    ORD_LAR_EVASIVE_LEAP             (0.7f,     0.3f,   60),
    ORD_LAR_INTO_THE_MAELSTROM       (0.25f,    0.35f,  60),
    ORD_LAR_FIGHT_OR_FLIGHT          (0.35f,    0.3f,   70),
    ORD_LAR_HISSING_DRAGON           (0.225f,   0.25f,  70),
    ORD_LAR_WARDANCER                (0.5f,     0.2f,   70),
    ORD_LAR_SPELLDANCER              (0.55f,    0.075f, 80),
    ORD_LAR_SURVIVAL_INSTINCT        (0.3f,     0.175f, 80),
    ORD_LAR_WILD_AND_FREE            (0.65f,    0.175f, 80),
    ORD_LAR_BREAKING_WAVES           (0.15f,    0.1f,   90),
    ORD_LAR_GLANCING_BLOWS           (0.425f,   0.125f, 90),
    ORD_LAR_LIGHTNING_STRIKE         (0.325f,   0.425f, 90),
    ORD_LAR_TEMPTING_FATE            (0.375f,   0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x + 0.05f, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_LAR_LIGHT_ARMOR_MASTERY -> listOf(
                ORD_LAR_ANNOYING_MOSQUITOES,
                ORD_LAR_IRON_FIST,
                ORD_LAR_LIGHT_ARMOR_FIT,
                ORD_LAR_AS_A_LEAF
            )
            ORD_LAR_IRON_FIST -> listOf(ORD_LAR_SWEEPING_WIND)
            ORD_LAR_LIGHT_ARMOR_FIT -> listOf(
                ORD_LAR_UNHINDERED,
                ORD_LAR_INITIATIVE,
                ORD_LAR_EVASIVE_LEAP,
                ORD_LAR_KEEN_SENSES
            )
            ORD_LAR_INITIATIVE -> listOf(
                ORD_LAR_LIGHTNING_STRIKE,
                ORD_LAR_FIGHT_OR_FLIGHT,
                ORD_LAR_WINDRUNNER
            )
            ORD_LAR_SWEEPING_WIND -> listOf(ORD_LAR_RUSHING_TIDE, ORD_LAR_UNHINDERED)
            ORD_LAR_RUSHING_TIDE -> listOf(ORD_LAR_HISSING_DRAGON, ORD_LAR_BREAKING_WAVES)
            ORD_LAR_UNHINDERED -> listOf(ORD_LAR_INTO_THE_MAELSTROM)
            ORD_LAR_WINDRUNNER -> listOf(ORD_LAR_WARDANCER)
            ORD_LAR_EVASIVE_LEAP -> listOf(ORD_LAR_WILD_AND_FREE)
            ORD_LAR_FIGHT_OR_FLIGHT -> listOf(ORD_LAR_SURVIVAL_INSTINCT)
            ORD_LAR_WARDANCER -> listOf(ORD_LAR_GLANCING_BLOWS, ORD_LAR_SPELLDANCER)
            ORD_LAR_SURVIVAL_INSTINCT -> listOf(ORD_LAR_TEMPTING_FATE)
            ORD_LAR_GLANCING_BLOWS -> listOf(ORD_LAR_TEMPTING_FATE)
            else -> emptyList()
        }
}