package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Restoration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_RST_RESTORATION_MASTERY                (0.5f,    0.95f,   0, 20),
    ORD_RST_DESCENDING_LIGHT                   (0.5f,    0.75f,   20),
    ORD_RST_RESTORATION_DUAL_CASTING           (0.75f,   0.9f,    20),
    ORD_RST_EDGEWALKER                         (0.3f,    0.75f,   30),
    ORD_RST_HALLOWED_BURIAL                    (0.6f,    0.55f,   30, 60),
    ORD_RST_SPIRIT_TUTORS                      (0.75f,   0.725f,  30),
    ORD_RST_VIGILANT                           (0.425f,  0.575f,  30),
    ORD_RST_NECROMANTICON                      (0.15f,   0.65f,   40),
    ORD_RST_EXORCIST                           (0.6f,    0.45f,   40),
    ORD_RST_FALSE_LIGHT                        (0.1f,    0.5f,    40),
    ORD_RST_RESPITE                            (0.3f,    0.6f,    40),
    ORD_RST_ANTIMAGIC_FIELD                    (0.225f,  0.45f,   50),
    ORD_RST_OVERFLOWING_CUP                    (0.675f,  0.625f,  50),
    ORD_RST_PILGRIM                            (0.9f,    0.45f,   50),
    ORD_RST_WARRIORS_FLAME                     (0.5f,    0.5f,    50),
    ORD_RST_ASHES_TO_ASHES                     (0.55f,   0.25f,   60),
    ORD_RST_CRUSADERS_FIRE                     (0.6f,    0.35f,   60),
    ORD_RST_FORBIDDEN_SANCTUARY                (0.25f,   0.35f,   60),
    ORD_RST_LIGHTWIELDER                       (0.125f,  0.3f,    60),
    ORD_RST_SACRED_FLAME                       (0.4f,    0.3f,    60),
    ORD_RST_BASTION_WARD                       (0.25f,   0.25f,   70),
    ORD_RST_CHALICE_OF_TEARS                   (0.1f,    0.725f,  70),
    ORD_RST_PLAGUE_DOCTOR                      (0.2f,    0.825f,  70),
    ORD_RST_UNDER_MY_WINGS                     (0.7f,    0.3f,    70),
    ORD_RST_WHEEL_OF_LIFE                      (0.8f,    0.4f,    70),
    ORD_RST_BATTLE_CLERIC                      (0.5f,    0.15f,   80),
    ORD_RST_IN_THY_NAME                        (0.1f,    0.15f,   80),
    ORD_RST_MAGE_WARD                          (0.2f,    0.12f,   80),
    ORD_RST_SACRED_GUARDIAN                    (0.9f,    0.6f,    80),
    ORD_RST_ENDURING_IDEAL                     (0.8f,    0.2f,    90),
    ORD_RST_ETERNAL_FLAME                      (0.425f,  0.075f,  90),
    ORD_RST_GODS_AND_MORTALS                   (0.9f,    0.12f,   90),
    ORD_RST_TOME_OF_MANY_PAGES                 (0.3f,    0.9f,    90),
    ORD_RST_APOTHEOSIS                         (0.55f,   0.05f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_RST_RESTORATION_MASTERY -> listOf(
                ORD_RST_TOME_OF_MANY_PAGES,
                ORD_RST_EDGEWALKER,
                ORD_RST_DESCENDING_LIGHT,
                ORD_RST_SPIRIT_TUTORS,
                ORD_RST_RESTORATION_DUAL_CASTING
            )
            ORD_RST_DESCENDING_LIGHT -> listOf(
                ORD_RST_VIGILANT,
                ORD_RST_WARRIORS_FLAME,
                ORD_RST_HALLOWED_BURIAL,
                ORD_RST_OVERFLOWING_CUP
            )
            ORD_RST_EDGEWALKER -> listOf(
                ORD_RST_RESPITE,
                ORD_RST_FALSE_LIGHT,
                ORD_RST_NECROMANTICON
            )
            ORD_RST_HALLOWED_BURIAL -> listOf(ORD_RST_EXORCIST)
            ORD_RST_SPIRIT_TUTORS -> listOf(
                ORD_RST_SACRED_GUARDIAN,
                ORD_RST_PILGRIM,
                ORD_RST_WHEEL_OF_LIFE
            )
            ORD_RST_VIGILANT -> listOf(ORD_RST_ANTIMAGIC_FIELD, ORD_RST_FORBIDDEN_SANCTUARY)
            ORD_RST_NECROMANTICON -> listOf(ORD_RST_CHALICE_OF_TEARS, ORD_RST_PLAGUE_DOCTOR)
            ORD_RST_EXORCIST -> listOf(ORD_RST_CRUSADERS_FIRE)
            ORD_RST_FALSE_LIGHT -> listOf(ORD_RST_LIGHTWIELDER)
            ORD_RST_OVERFLOWING_CUP -> listOf(ORD_RST_WHEEL_OF_LIFE, ORD_RST_UNDER_MY_WINGS)
            ORD_RST_PILGRIM -> listOf(ORD_RST_GODS_AND_MORTALS)
            ORD_RST_WARRIORS_FLAME -> listOf(
                ORD_RST_FORBIDDEN_SANCTUARY,
                ORD_RST_SACRED_FLAME,
                ORD_RST_ASHES_TO_ASHES
            )
            ORD_RST_ASHES_TO_ASHES -> listOf(ORD_RST_BATTLE_CLERIC)
            ORD_RST_FORBIDDEN_SANCTUARY -> listOf(ORD_RST_BASTION_WARD)
            ORD_RST_LIGHTWIELDER -> listOf(ORD_RST_IN_THY_NAME)
            ORD_RST_SACRED_FLAME -> listOf(ORD_RST_BATTLE_CLERIC)
            ORD_RST_BASTION_WARD -> listOf(ORD_RST_MAGE_WARD)
            ORD_RST_WHEEL_OF_LIFE -> listOf(ORD_RST_ENDURING_IDEAL)
            ORD_RST_BATTLE_CLERIC -> listOf(ORD_RST_ETERNAL_FLAME, ORD_RST_APOTHEOSIS)
            else -> emptyList()
        }
}
