package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Alteration(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_ALT_ALTERATION_MASTERY      (0.5f,   0.95f,  0),
    VOK_ALT_ALTERATION_DUAL_CASTING (0.3f,   0.85f,  20),
    VOK_ALT_MAGE_ARMOR              (0.375f, 0.6f,   20, 50, 80),
    VOK_ALT_MAGIC_RESISTANCE        (0.6f,   0.55f,  20, 50, 80),
    VOK_ALT_BATTLEMAGE              (0.75f,  0.6f,   30, 60),
    VOK_ALT_SORCERERS_ROBES         (0.425f, 0.4f,   40),
    VOK_ALT_STABILITY               (0.275f, 0.35f,  40),
    VOK_ALT_OCATOS_PREPARATION      (0.15f,  0.5f,   50),
    VOK_ALT_TELEKINETIC_FORCE       (0.55f,  0.3f,   50, 80),
    VOK_ALT_ALTER_SELF              (0.775f, 0.325f, 60, 90),
    VOK_ALT_INITIATE                (0.15f,  0.25f,  60),
    VOK_ALT_ARCANE_GUIDANCE         (0.8f,   0.15f,  70),
    VOK_ALT_ATRONACH                (0.7f,   0.225f, 80),
    VOK_ALT_FORCE_OF_WILL           (0.425f, 0.15f,  80),
    VOK_ALT_HETHOTHS_ESCAPE         (0.9f,   0.05f,  90),
    VOK_ALT_TELEKINETIC_PRODIGY     (0.6f,   0.1f,   90),
    VOK_ALT_RITUALIST               (0.275f, 0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}