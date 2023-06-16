package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Alteration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ALT_PHILOSOPHER (0.5f,  0.95f,  10, 50),
    ADA_ALT_MAGE_ROBES  (0.4f,  0.8f,   20, 60),
    ADA_ALT_MAGE_ARMOR  (0.4f,  0.35f,  30, 70),
    ADA_ALT_MEDITATION  (0.1f,  0.25f,  80),
    ADA_ALT_MAGICKA_WELL(0.25f, 0.15f,  100),
    ADA_ALT_BARRIER     (0.5f,  0.7f,   40),
    ADA_ALT_BASTION     (0.5f,  0.45f,  70),
    ADA_ALT_BALANCE     (0.6f,  0.8f,   30, 60),
    ADA_ALT_STABILITY   (0.6f,  0.35f,  40),
    ADA_ALT_SPELL_SHIELD(0.9f,  0.25f,  70),
    ADA_ALT_SPELL_SIP   (0.65f, 0.05f,  90);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}