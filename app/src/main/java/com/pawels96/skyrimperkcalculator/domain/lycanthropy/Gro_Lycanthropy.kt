package com.pawels96.skyrimperkcalculator.domain.lycanthropy

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk

enum class Gro_Lycanthropy(val x: Float, val y: Float, val maxLevel: Int = 1) : SpecialSkillPerk {

    GRO_LYC_BESTIAL_STRENGTH(0.5f, 0.95f, 4),
    GRO_LYC_ANIMAL_VIGOR(0.5f, 0.6f),
    GRO_LYC_RAMPAGE(0.5f, 0.2f),
    GRO_LYC_INFINITE_DURESS(0.375f, 0.4f),
    GRO_LYC_SUPERNATURAL_STRENGTH(0.5f, 0.475f),
    GRO_LYC_ROADKILL(0.625f, 0.4f),
    GRO_LYC_FERAL_INSTINCTS(0.75f, 0.65f),
    GRO_LYC_IMPROVED_BLOODTHIRST(0.7f, 0.275f, 2),
    GRO_LYC_SWIPE(0.9f, 0.2f),
    GRO_LYC_LYCANTHROPIC_SPEED(0.25f, 0.65f),
    GRO_LYC_LYCANTHROPIC_REGENERATION(0.3f, 0.275f, 2),
    GRO_LYC_SPREAD_THE_BEASTBLOOD(0.1f, 0.2f),
    GRO_LYC_TOTEM_OF_ICE_BROTHERS(0.3f, 0.125f, 2),
    GRO_LYC_TOTEM_OF_THE_HUNT(0.5f, 0.05f, 2),
    GRO_LYC_TOTEM_OF_TERROR(0.7f, 0.125f, 2),
    GRO_LYC_GORGING(0.9f, 0.75f),
    GRO_LYC_SAVAGE_FEEDING(0.85f, 0.5f),
    GRO_LYC_BURY_THE_BEAST(0.1f, 0.75f),
    GRO_LYC_WOLF_AMONG_MEN(0.15f, 0.5f);

    override val perkInfo: PerkInfo get() = PerkInfo(IntArray(maxLevel), x, y)
}

