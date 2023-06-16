package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Block(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_BLC_GLADIATOR          (0.5f, 0.95f,  10, 50),
    ADA_BLC_DEFENSIVE_MANEUVERS(0.5f, 0.6f,   30),
    ADA_BLC_DISCIPLINE         (0.3f, 0.8f,   20),
    ADA_BLC_DEFLECTION         (0.3f, 0.4f,   40),
    ADA_BLC_DETERMINATION      (0.1f, 0.225f, 70),
    ADA_BLC_DELIVERANCE        (0.3f, 0.05f,  90),
    ADA_BLC_DEADLY_BASH        (0.7f, 0.8f,   20, 80),
    ADA_BLC_STUNNING_STRIKE    (0.7f, 0.4f,   40),
    ADA_BLC_DISORIENTING_BLOW  (0.9f, 0.225f, 60),
    ADA_BLC_BATTERING_RAM      (0.7f, 0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}