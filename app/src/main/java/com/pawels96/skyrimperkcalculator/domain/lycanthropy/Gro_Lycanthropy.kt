package com.pawels96.skyrimperkcalculator.domain.lycanthropy

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk

enum class Gro_Lycanthropy(
    val x: Float,
    val y: Float,
    maxLevel: Int = 1
) : SpecialSkillPerk {

    GRO_LYC_BESTIAL_STRENGTH           (0.5f,   0.95f,  4),
    GRO_LYC_ANIMAL_VIGOR               (0.5f,   0.6f),
    GRO_LYC_RAMPAGE                    (0.5f,   0.2f),
    GRO_LYC_INFINITE_DURESS            (0.375f, 0.4f),
    GRO_LYC_SUPERNATURAL_STRENGTH      (0.5f,   0.475f),
    GRO_LYC_ROADKILL                   (0.625f, 0.4f),
    GRO_LYC_FERAL_INSTINCTS            (0.75f,  0.65f),
    GRO_LYC_IMPROVED_BLOODTHIRST       (0.7f,   0.275f, 2),
    GRO_LYC_SWIPE                      (0.9f,   0.2f),
    GRO_LYC_LYCANTHROPIC_SPEED         (0.25f,  0.65f),
    GRO_LYC_LYCANTHROPIC_REGENERATION  (0.3f,   0.275f, 2),
    GRO_LYC_SPREAD_THE_BEASTBLOOD      (0.1f,   0.2f),
    GRO_LYC_TOTEM_OF_ICE_BROTHERS      (0.3f,   0.125f, 2),
    GRO_LYC_TOTEM_OF_THE_HUNT          (0.5f,   0.05f,  2),
    GRO_LYC_TOTEM_OF_TERROR            (0.7f,   0.125f, 2),
    GRO_LYC_GORGING                    (0.9f,   0.75f),
    GRO_LYC_SAVAGE_FEEDING             (0.85f,  0.5f),
    GRO_LYC_BURY_THE_BEAST             (0.1f,   0.75f),
    GRO_LYC_WOLF_AMONG_MEN             (0.15f,  0.5f);

    override val perkInfo: PerkInfo = PerkInfo(IntArray(maxLevel), x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            GRO_LYC_BESTIAL_STRENGTH -> listOf(
                GRO_LYC_BURY_THE_BEAST,
                GRO_LYC_LYCANTHROPIC_SPEED,
                GRO_LYC_ANIMAL_VIGOR,
                GRO_LYC_FERAL_INSTINCTS,
                GRO_LYC_GORGING
            )
            GRO_LYC_BURY_THE_BEAST -> listOf(GRO_LYC_WOLF_AMONG_MEN)
            GRO_LYC_LYCANTHROPIC_SPEED -> listOf(GRO_LYC_LYCANTHROPIC_REGENERATION)
            GRO_LYC_LYCANTHROPIC_REGENERATION -> listOf(GRO_LYC_SPREAD_THE_BEASTBLOOD)
            GRO_LYC_ANIMAL_VIGOR -> listOf(
                GRO_LYC_INFINITE_DURESS,
                GRO_LYC_SUPERNATURAL_STRENGTH,
                GRO_LYC_ROADKILL
            )
            GRO_LYC_INFINITE_DURESS -> listOf(GRO_LYC_RAMPAGE)
            GRO_LYC_ROADKILL -> listOf(GRO_LYC_RAMPAGE)
            GRO_LYC_SUPERNATURAL_STRENGTH -> listOf(GRO_LYC_RAMPAGE)
            GRO_LYC_RAMPAGE -> listOf(
                GRO_LYC_TOTEM_OF_ICE_BROTHERS,
                GRO_LYC_TOTEM_OF_TERROR,
                GRO_LYC_TOTEM_OF_THE_HUNT
            )
            GRO_LYC_FERAL_INSTINCTS -> listOf(GRO_LYC_IMPROVED_BLOODTHIRST)
            GRO_LYC_GORGING -> listOf(GRO_LYC_SAVAGE_FEEDING)
            GRO_LYC_IMPROVED_BLOODTHIRST -> listOf(GRO_LYC_SWIPE)
            else -> emptyList()
        }
}

