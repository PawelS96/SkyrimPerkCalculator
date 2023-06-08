package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Enchanting(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_ENC_ENCHANTING_MASTERY (0.5f,   0.95f,  0),
    VOK_ENC_POWER_STONE        (0.275f, 0.7f,   20, 50, 80),
    VOK_ENC_SOUL_SQUEEZER      (0.2f,   0.8f,   20),
    VOK_ENC_WEAPON_ENCHANTER   (0.4f,   0.6f,   30),
    VOK_ENC_ARMOR_ENCHANTER    (0.625f, 0.575f, 30),
    VOK_ENC_SCROLL_SAGE        (0.8f,   0.7f,   40, 70, 100),
    VOK_ENC_SOUL_SIPHON        (0.1f,   0.65f,  40),
    VOK_ENC_MANA_STONE         (0.3f,   0.45f,  50),
    VOK_ENC_SCROLL_HUNTER      (0.875f, 0.4f,   50),
    VOK_ENC_SOUL_ENCHANTER     (0.4f,   0.325f, 60),
    VOK_ENC_THUNDERSTRUCK      (0.15f,  0.5f,   60),
    VOK_ENC_DEFENSIVE_RUNES    (0.15f,  0.35f,  70),
    VOK_ENC_REGALIA_ENCHANTER  (0.575f, 0.275f, 70),
    VOK_ENC_STAFF_RECHARGE     (0.225f, 0.275f, 80),
    VOK_ENC_SPIDER_HUNTER      (0.725f, 0.15f,  90),
    VOK_ENC_EXTRA_EFFECT       (0.45f,  0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}