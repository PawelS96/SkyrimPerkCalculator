package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Enchanting(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ENC_ARTIFICER           (0.5f,   0.95f,  10, 50),
    ADA_ENC_SEEKER              (0.225f, 0.875f, 20, 70),
    ADA_ENC_SCRIBE              (0.325f, 0.7f,   40, 90),
    ADA_ENC_RESONANCE           (0.775f, 0.875f, 30),
    ADA_ENC_SOUL_SIPHON         (0.825f, 0.675f, 60),
    ADA_ENC_CONDUIT             (0.675f, 0.75f,  40),
    ADA_ENC_CHANNELER           (0.675f, 0.6f,   70),
    ADA_ENC_JEWELRY_ENCHANTER   (0.375f, 0.45f,  30),
    ADA_ENC_ARMOR_ENCHANTER     (0.625f, 0.45f,  40),
    ADA_ENC_CORPUS_ENCHANTER    (0.325f, 0.35f,  60),
    ADA_ENC_ELEMENTAL_ENCHANTER (0.675f, 0.35f,  70),
    ADA_ENC_INSIGHTFUL_ENCHANTER(0.5f,   0.25f,  80),
    ADA_ENC_TWIN_SECRETS        (0.5f,   0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}