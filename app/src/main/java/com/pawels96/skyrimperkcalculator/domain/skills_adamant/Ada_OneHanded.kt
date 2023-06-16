package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_OneHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ONH_SKIRMISHER     (0.4f,   0.95f,  10, 50),
    ADA_ONH_FIGHTERS_STANCE(0.4f,   0.7f,   30, 60),
    ADA_ONH_OVERRUN        (0.3f,   0.35f,  40),
    ADA_ONH_EXECUTE        (0.5f,   0.35f,  80),
    ADA_ONH_ONSLAUGHT      (0.4f,   0.05f,  100),
    ADA_ONH_FLOURISH       (0.4f,   0.275f, 70),
    ADA_ONH_QUICK_SLASH    (0.2f,   0.6f,   20, 70),
    ADA_ONH_PRECISE_CUTS   (0.1f,   0.4f,   40, 90),
    ADA_ONH_HACK_AND_SLASH (0.525f, 0.625f, 20, 70),
    ADA_ONH_CARVE_AND_SPIT (0.6f,   0.4f,   40, 90),
    ADA_ONH_ARMOR_BREAKER  (0.675f, 0.575f, 20, 70),
    ADA_ONH_BELL_RINGER    (0.75f,  0.45f,  40, 90),
    ADA_ONH_DUAL_FOCUS     (0.9f,   0.8f,   20),
    ADA_ONH_DUAL_FRENZY    (0.875f, 0.55f,  40, 90),
    ADA_ONH_DUAL_FURY      (0.85f,  0.3f,   70);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}