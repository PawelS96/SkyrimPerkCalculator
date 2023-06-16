package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_LightArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_LAR_SCOUT      (0.5f,  0.95f, 10, 50),
    ADA_LAR_SPECIALIST (0.65f, 0.7f,  30),
    ADA_LAR_UNHINDERED (0.6f,  0.5f,  40),
    ADA_LAR_CUSTOM_FIT (0.65f, 0.3f,  70),
    ADA_LAR_ENDURANCE  (0.6f,  0.1f,  80),
    ADA_LAR_AGILITY    (0.35f, 0.7f,  20, 60),
    ADA_LAR_ATHLETICS  (0.4f,  0.5f,  40, 90),
    ADA_LAR_ADRENALINE (0.35f, 0.3f,  60),
    ADA_LAR_SECOND_WIND(0.4f,  0.1f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}