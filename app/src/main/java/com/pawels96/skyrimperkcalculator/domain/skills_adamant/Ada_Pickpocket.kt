package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Pickpocket(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_PCK_CUTPURSE       (0.35f,   0.95f, 10, 50),
    ADA_PCK_NIMBLE_FINGERS (0.45f,   0.75f, 20),
    ADA_PCK_PRACTICED_THIEF(0.55f,   0.5f,  40),
    ADA_PCK_SLEIGHT_OF_HAND(0.35f,   0.55f, 70),
    ADA_PCK_POISONED_FRUIT (0.65f,   0.65f, 30),
    ADA_PCK_FOOLS_GIFT     (0.725f,  0.4f,  60, 80),
    ADA_PCK_MISDIRECTION   (0.525f,  0.25f, 90),
    ADA_PCK_PERFECT_TOUCH  (0.625f,  0.05f, 100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}