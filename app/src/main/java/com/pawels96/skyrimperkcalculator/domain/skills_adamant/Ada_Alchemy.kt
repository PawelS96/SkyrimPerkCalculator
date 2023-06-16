package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Alchemy(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ALC_HERBALIST    (0.5f,  0.95f, 10, 50),
    ADA_ALC_INTENSITY    (0.1f,  0.8f,  30, 70),
    ADA_ALC_SOLVENCY     (0.35f, 0.4f,  60, 90),
    ADA_ALC_CONCENTRATION(0.9f,  0.8f,  30, 70),
    ADA_ALC_POTENCY      (0.65f, 0.4f,  60, 90),
    ADA_ALC_EXPERIMENTER (0.55f, 0.65f, 20),
    ADA_ALC_GREEN_THUMB  (0.45f, 0.55f, 40, 80),
    ADA_ALC_CHEMIST      (0.5f,  0.1f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}