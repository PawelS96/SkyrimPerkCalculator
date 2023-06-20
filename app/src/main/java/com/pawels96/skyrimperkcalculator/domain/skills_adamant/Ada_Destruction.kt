package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Destruction(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_DES_ELEMENTALIST    (0.5f,   0.95f, 10, 50),
    ADA_DES_SPELL_SURGE     (0.75f,  0.9f,  20),
    ADA_DES_IMPACT          (0.9f,   0.75f, 40),
    ADA_DES_RUNE_MAGE       (0.25f,  0.9f,  30, 60),
    ADA_DES_AUGMENTED_FLAMES(0.3f,   0.7f,  30, 70),
    ADA_DES_FIREBRAND       (0.275f, 0.5f,  60, 90),
    ADA_DES_COMBUSTION      (0.25f,  0.35f, 80),
    ADA_DES_WILDFIRE        (0.175f, 0.2f,  100),
    ADA_DES_AUGMENTED_FROST (0.5f,   0.65f, 30, 70),
    ADA_DES_NORTH_WIND      (0.55f,  0.5f,  60, 90),
    ADA_DES_PERMAFROST      (0.45f,  0.35f, 80),
    ADA_DES_DEEP_FREEZE     (0.5f,   0.2f,  100),
    ADA_DES_AUGMENTED_SHOCK (0.7f,   0.7f,  30, 70),
    ADA_DES_UNSTABLE_CURRENT(0.725f, 0.45f, 60, 90),
    ADA_DES_STATIC_FIELD    (0.75f,  0.25f, 80),
    ADA_DES_POWER_SURGE     (0.8f,   0.1f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_DES_ELEMENTALIST -> listOf(
                ADA_DES_RUNE_MAGE,
                ADA_DES_AUGMENTED_FLAMES,
                ADA_DES_AUGMENTED_FROST,
                ADA_DES_AUGMENTED_SHOCK,
                ADA_DES_SPELL_SURGE
            )
            ADA_DES_AUGMENTED_FLAMES -> listOf(ADA_DES_FIREBRAND)
            ADA_DES_FIREBRAND -> listOf(ADA_DES_COMBUSTION)
            ADA_DES_COMBUSTION -> listOf(ADA_DES_WILDFIRE)
            ADA_DES_AUGMENTED_FROST -> listOf(ADA_DES_NORTH_WIND)
            ADA_DES_NORTH_WIND -> listOf(ADA_DES_PERMAFROST)
            ADA_DES_PERMAFROST -> listOf(ADA_DES_DEEP_FREEZE)
            ADA_DES_AUGMENTED_SHOCK -> listOf(ADA_DES_UNSTABLE_CURRENT)
            ADA_DES_UNSTABLE_CURRENT -> listOf(ADA_DES_STATIC_FIELD)
            ADA_DES_STATIC_FIELD -> listOf(ADA_DES_POWER_SURGE)
            ADA_DES_SPELL_SURGE -> listOf(ADA_DES_IMPACT)
            else -> emptyList()
        }
}