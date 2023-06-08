package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Conjuration(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_CON_CONJURATION_MASTERY      (0.5f,   0.95f,  0),
    VOK_CON_CONJURATION_DUAL_CASTING (0.2f,   0.85f,  20),
    VOK_CON_MYSTIC_BINDING           (0.9f,   0.85f,  20),
    VOK_CON_ATROMANCY                (0.25f,  0.7f,   30),
    VOK_CON_NECROMANCY               (0.7f,   0.7f,   30),
    VOK_CON_SOUL_STEALER             (0.85f,  0.65f,  30),
    VOK_CON_OBLIVION_BINDING         (0.9f,   0.5f,   40),
    VOK_CON_RIFT_SUMMONER            (0.5f,   0.55f,  40, 70),
    VOK_CON_GHOUL_FRENZY             (0.7f,   0.45f,  50),
    VOK_CON_OBLIVION_STONE           (0.225f, 0.45f,  50, 80),
    VOK_CON_HOLLOW_BINDING           (0.825f, 0.35f,  60),
    VOK_CON_GRAND_CONJURER           (0.475f, 0.35f,  60, 90),
    VOK_CON_BLOOD_ZOMBIE             (0.725f, 0.25f,  70),
    VOK_CON_ELEMENTAL_POTENCY        (0.25f,  0.25f,  70),
    VOK_CON_VOID_BRAND               (0.875f, 0.175f, 80),
    VOK_CON_ELEMENTAL_CONFLUX        (0.25f,  0.15f,  90),
    VOK_CON_NECROMASTER              (0.75f,  0.15f,  90),
    VOK_CON_TWIN_SOULS               (0.5f,   0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}