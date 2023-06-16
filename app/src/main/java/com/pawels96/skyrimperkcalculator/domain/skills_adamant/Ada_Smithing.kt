package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Smithing(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_SMT_CRAFTSMAN            (0.4f,   0.95f,  10, 50),
    ADA_SMT_BLACKSMITH           (0.25f,  0.75f,  30),
    ADA_SMT_ARMORER              (0.1f,   0.55f,  70),
    ADA_SMT_FORGEMASTER          (0.25f,  0.4f,   90),
    ADA_SMT_BASIC_SMITHING       (0.65f,  0.75f,  20),
    ADA_SMT_JOURNEYMAN_SMITHING  (0.8f,   0.625f, 40),
    ADA_SMT_RARE_SMITHING        (0.675f, 0.55f,  60),
    ADA_SMT_EXOTIC_SMITHING      (0.5f,   0.5f,   80),
    ADA_SMT_INTERMEDIATE_SMITHING(0.925f, 0.5f,   60),
    ADA_SMT_ADVANCED_SMITHING    (0.675f, 0.4f,   80),
    ADA_SMT_MYTHIC_SMITHING      (0.45f,  0.35f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}