package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Enchanting(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ENC_ENCHANTING_MASTERY           (0.5f,    0.95f,  0, 20),
    ORD_ENC_LAST_WORD                    (0.3f,    0.85f,  20, 50, 80),
    ORD_ENC_SOUL_SIPHON                  (0.8f,    0.8f,   20),
    ORD_ENC_STAFF_CHANNELER              (0.4f,    0.75f,  30),
    ORD_ENC_THUNDERSTRUCK                (0.75f,   0.65f,  30),
    ORD_ENC_GEM_DUST                     (0.6f,    0.7f,   40),
    ORD_ENC_SECRETKEEPER                 (0.2f,    0.65f,  40),
    ORD_ENC_SPELLSCRIBE                  (0.85f,   0.575f, 40),
    ORD_ENC_FLAME_OF_MAGNUS              (0.1f,    0.45f,  50),
    ORD_ENC_PRESERVER                    (0.5f,    0.55f,  50),
    ORD_ENC_MIGHT_AND_MAGIC              (0.875f,  0.45f,  60),
    ORD_ENC_REGALIA                      (0.625f,  0.475f, 60),
    ORD_ENC_STAFF_RECHARGE               (0.35f,   0.425f, 60),
    ORD_ENC_ATTUNEMENT                   (0.5f,    0.35f,  70),
    ORD_ENC_CHARGE_TAP                   (0.3f,    0.3f,   70),
    ORD_ENC_POWER_ECHOES                 (0.75f,   0.35f,  70),
    ORD_ENC_HEART_OF_THE_SUN             (0.4f,    0.2f,   80),
    ORD_ENC_TWIN_ENCHANTMENT             (0.6f,    0.25f,  80),
    ORD_ENC_ARCANE_NEXUS                 (0.6f,    0.15f,  90),
    ORD_ENC_YOU_SHALL_NOT_PASS           (0.15f,   0.2f,   90),
    ORD_ENC_MIRACLE                      (0.55f,   0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ENC_ENCHANTING_MASTERY -> listOf(
                ORD_ENC_LAST_WORD,
                ORD_ENC_STAFF_CHANNELER,
                ORD_ENC_GEM_DUST,
                ORD_ENC_SOUL_SIPHON
            )
            ORD_ENC_LAST_WORD -> listOf(ORD_ENC_SECRETKEEPER)
            ORD_ENC_SOUL_SIPHON -> listOf(ORD_ENC_THUNDERSTRUCK, ORD_ENC_SPELLSCRIBE)
            ORD_ENC_STAFF_CHANNELER -> listOf(ORD_ENC_SECRETKEEPER, ORD_ENC_STAFF_RECHARGE)
            ORD_ENC_GEM_DUST -> listOf(ORD_ENC_PRESERVER, ORD_ENC_REGALIA)
            ORD_ENC_SECRETKEEPER -> listOf(ORD_ENC_FLAME_OF_MAGNUS, ORD_ENC_STAFF_RECHARGE)
            ORD_ENC_SPELLSCRIBE -> listOf(ORD_ENC_MIGHT_AND_MAGIC, ORD_ENC_POWER_ECHOES)
            ORD_ENC_FLAME_OF_MAGNUS -> listOf(ORD_ENC_YOU_SHALL_NOT_PASS)
            ORD_ENC_REGALIA -> listOf(ORD_ENC_ATTUNEMENT, ORD_ENC_TWIN_ENCHANTMENT)
            ORD_ENC_STAFF_RECHARGE -> listOf(ORD_ENC_CHARGE_TAP, ORD_ENC_ATTUNEMENT)
            ORD_ENC_ATTUNEMENT -> listOf(ORD_ENC_HEART_OF_THE_SUN)
            ORD_ENC_CHARGE_TAP -> listOf(ORD_ENC_YOU_SHALL_NOT_PASS, ORD_ENC_HEART_OF_THE_SUN)
            ORD_ENC_TWIN_ENCHANTMENT -> listOf(ORD_ENC_ARCANE_NEXUS)
            ORD_ENC_ARCANE_NEXUS -> listOf(ORD_ENC_MIRACLE)
            else -> emptyList()
        }
}
