package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Restoration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_RES_HEALER            (0.5f,  0.95f,  10, 50),
    ADA_RES_RECOVERY          (0.5f,  0.5f,   20, 70),
    ADA_RES_RESPITE           (0.4f,  0.35f,  40),
    ADA_RES_REPOSE            (0.6f,  0.35f,  60),
    ADA_RES_RESOLVE           (0.5f,  0.2f,   80),
    ADA_RES_RENEWAL           (0.4f,  0.05f,  100),
    ADA_RES_ILLUMINATION      (0.95f, 0.75f,  40),
    ADA_RES_PILGRIM           (0.05f, 0.75f,  30, 60),
    ADA_RES_EMPOWERED_WARD    (0.65f, 0.6f,   20),
    ADA_RES_RADIANT_WARD      (0.75f, 0.375f, 40),
    ADA_RES_PRISMATIC_WARD    (0.9f,  0.275f, 70),
    ADA_RES_DIVINE_GLORY      (0.3f,  0.775f, 30, 70),
    ADA_RES_POWER_OF_THE_LIGHT(0.2f,  0.675f, 60, 90),
    ADA_RES_DAWNS_WRATH       (0.7f,  0.775f, 40, 80),
    ADA_RES_BURNING_LIGHT     (0.8f,  0.675f, 60),
    ADA_RES_AFFLICTION        (0.35f, 0.6f,   30, 70),
    ADA_RES_PLAGUE            (0.25f, 0.375f, 60, 90),
    ADA_RES_SCOURGE           (0.1f,  0.275f, 80, 100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_RES_HEALER -> listOf(
                ADA_RES_PILGRIM,
                ADA_RES_DIVINE_GLORY,
                ADA_RES_AFFLICTION,
                ADA_RES_RECOVERY,
                ADA_RES_EMPOWERED_WARD,
                ADA_RES_DAWNS_WRATH,
                ADA_RES_ILLUMINATION
            )
            ADA_RES_DIVINE_GLORY -> listOf(ADA_RES_POWER_OF_THE_LIGHT)
            ADA_RES_AFFLICTION -> listOf(ADA_RES_PLAGUE)
            ADA_RES_PLAGUE -> listOf(ADA_RES_SCOURGE)
            ADA_RES_RECOVERY -> listOf(ADA_RES_RESPITE, ADA_RES_REPOSE)
            ADA_RES_RESPITE -> listOf(ADA_RES_RESOLVE)
            ADA_RES_REPOSE -> listOf(ADA_RES_RESOLVE)
            ADA_RES_RESOLVE -> listOf(ADA_RES_RENEWAL)
            ADA_RES_EMPOWERED_WARD -> listOf(ADA_RES_RADIANT_WARD)
            ADA_RES_RADIANT_WARD -> listOf(ADA_RES_PRISMATIC_WARD)
            ADA_RES_DAWNS_WRATH -> listOf(ADA_RES_BURNING_LIGHT)
            else -> emptyList()
        }
}