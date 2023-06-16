package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Speech(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_SPC_MERCHANT           (0.5f,  0.95f, 10, 50),
    ADA_SPC_SUPPLY_AND_DEMAND  (0.8f,  0.75f, 30, 60),
    ADA_SPC_INVESTOR           (0.6f,  0.25f, 80),
    ADA_SPC_ENTREPRENEUR       (0.7f,  0.05f, 100),
    ADA_SPC_SILVER_TONGUE      (0.6f,  0.6f,  20),
    ADA_SPC_BLACK_MARKET       (0.85f, 0.55f, 40),
    ADA_SPC_DEEP_BREATH        (0.15f, 0.75f, 30, 70),
    ADA_SPC_ANCIENT_VOICE      (0.25f, 0.5f,  40),
    ADA_SPC_STORMCROWN         (0.15f, 0.25f, 60, 90),
    ADA_SPC_DRAGON_OF_THE_NORTH(0.25f, 0.05f, 80);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}