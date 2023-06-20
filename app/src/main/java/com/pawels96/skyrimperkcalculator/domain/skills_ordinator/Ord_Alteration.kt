package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Alteration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ALT_ALTERATION_MASTERY       (0.5f,   0.95f,  0, 20),
    ORD_ALT_ALTERATION_DUAL_CASTING  (0.25f,  0.9f,   20),
    ORD_ALT_MAGE_ARMOR               (0.7f,   0.9f,   20, 50, 80),
    ORD_ALT_GEOMANCER                (0.2f,   0.75f,  30),
    ORD_ALT_PHILOSOPHERS_STONE       (0.375f, 0.65f,  30),
    ORD_ALT_VANCIAN_MAGIC            (0.7f,   0.575f, 30),
    ORD_ALT_WILD_SHRINES             (0.8f,   0.65f,  30),
    ORD_ALT_ALTER_SELF_RESISTANCES   (0.5f,   0.5f,   40),
    ORD_ALT_DISTORTED_SHAPE          (0.9f,   0.85f,  40),
    ORD_ALT_SPELLBLADE               (0.6f,   0.625f, 40),
    ORD_ALT_COMMAND_LOCK             (0.3f,   0.425f, 50),
    ORD_ALT_ENERGY_SHIELD            (0.85f,  0.75f,  50),
    ORD_ALT_INTUITIVE_MAGIC          (0.8f,   0.4f,   50, 80),
    ORD_ALT_QUADRATIC_WIZARD         (0.7f,   0.325f, 50),
    ORD_ALT_ALTER_SELF_ATTRIBUTES    (0.5f,   0.3f,   60),
    ORD_ALT_HOME_MYTHAL              (0.35f,  0.25f,  60),
    ORD_ALT_WELLOCS_DORMANT          (0.9f,   0.475f, 60),
    ORD_ALT_DUNGEON_MASTER           (0.75f,  0.225f, 70),
    ORD_ALT_ENERGY_ROIL              (0.6f,   0.2f,   70),
    ORD_ALT_THE_MONARCH              (0.9f,   0.25f,  70),
    ORD_ALT_AURIFICATION             (0.15f,  0.15f,  80),
    ORD_ALT_DIMENSION_DOOR           (0.35f,  0.1f,   80),
    ORD_ALT_THRONE_OF_NIRN           (0.1f,   0.4f,   80),
    ORD_ALT_EMERGENCY_TELEPORT       (0.2f,   0.05f,  90),
    ORD_ALT_NULLIFIER                (0.5f,   0.05f,  90),
    ORD_ALT_REND_RESISTANCES         (0.65f,  0.1f,   90),
    ORD_ALT_ARCANE_THESIS            (0.85f,  0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ALT_ALTERATION_MASTERY -> listOf(
                ORD_ALT_ALTERATION_DUAL_CASTING,
                ORD_ALT_GEOMANCER,
                ORD_ALT_PHILOSOPHERS_STONE,
                ORD_ALT_ALTER_SELF_RESISTANCES,
                ORD_ALT_SPELLBLADE,
                ORD_ALT_VANCIAN_MAGIC,
                ORD_ALT_WILD_SHRINES,
                ORD_ALT_MAGE_ARMOR
            )
            ORD_ALT_MAGE_ARMOR -> listOf(ORD_ALT_DISTORTED_SHAPE, ORD_ALT_ENERGY_SHIELD)
            ORD_ALT_GEOMANCER -> listOf(ORD_ALT_THRONE_OF_NIRN)
            ORD_ALT_PHILOSOPHERS_STONE -> listOf(ORD_ALT_COMMAND_LOCK)
            ORD_ALT_VANCIAN_MAGIC -> listOf(ORD_ALT_QUADRATIC_WIZARD)
            ORD_ALT_WILD_SHRINES -> listOf(ORD_ALT_WELLOCS_DORMANT, ORD_ALT_INTUITIVE_MAGIC)
            ORD_ALT_ALTER_SELF_RESISTANCES -> listOf(
                ORD_ALT_ALTER_SELF_ATTRIBUTES,
                ORD_ALT_HOME_MYTHAL
            )
            ORD_ALT_SPELLBLADE -> listOf(ORD_ALT_ENERGY_ROIL)
            ORD_ALT_COMMAND_LOCK -> listOf(ORD_ALT_HOME_MYTHAL, ORD_ALT_AURIFICATION)
            ORD_ALT_INTUITIVE_MAGIC -> listOf(ORD_ALT_THE_MONARCH, ORD_ALT_ARCANE_THESIS)
            ORD_ALT_QUADRATIC_WIZARD -> listOf(ORD_ALT_DUNGEON_MASTER)
            ORD_ALT_ALTER_SELF_ATTRIBUTES -> listOf(ORD_ALT_NULLIFIER)
            ORD_ALT_HOME_MYTHAL -> listOf(ORD_ALT_DIMENSION_DOOR, ORD_ALT_EMERGENCY_TELEPORT)
            ORD_ALT_DUNGEON_MASTER -> listOf(ORD_ALT_ARCANE_THESIS)
            ORD_ALT_ENERGY_ROIL -> listOf(ORD_ALT_REND_RESISTANCES)
            else -> emptyList()
        }
}