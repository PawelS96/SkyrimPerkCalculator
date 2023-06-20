package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Restoration(x: Float, y: Float, vararg skill: Int) : IPerk {
    VOK_RST_RESTORATION_MASTERY      (0.5f,   0.95f,  0),
    VOK_RST_RESTORATION_DUAL_CASTING (0.8f,   0.875f, 20),
    VOK_RST_MERCY                    (0.35f,  0.65f,  20),
    VOK_RST_INSPIRE                  (0.6f,   0.6f,   30, 60),
    VOK_RST_INNER_LIGHT              (0.8f,   0.55f,  30, 60),
    VOK_RST_RESPITE                  (0.325f, 0.375f, 40),
    VOK_RST_VIGILANT_WARD            (0.45f,  0.525f, 40, 70),
    VOK_RST_HARM                     (0.18f,  0.425f, 50),
    VOK_RST_SUNS_JUDGMENT            (0.175f, 0.275f, 60, 90),
    VOK_RST_WARD_ABSORB              (0.5f,   0.25f,  60),
    VOK_RST_NECROMAGE                (0.65f,  0.175f, 70),
    VOK_RST_REBUKE_UNDEAD            (0.1f,   0.15f,  70),
    VOK_RST_BLESSED                  (0.825f, 0.15f,  80),
    VOK_RST_MAGE_WARD                (0.475f, 0.1f,   80),
    VOK_RST_ETERNAL_FLAME            (0.35f,  0.05f,  90),
    VOK_RST_INTERVENTION             (0.9f,   0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_RST_RESTORATION_MASTERY -> listOf(
                VOK_RST_MERCY,
                VOK_RST_RESTORATION_DUAL_CASTING,
                VOK_RST_INSPIRE,
                VOK_RST_INNER_LIGHT,
                VOK_RST_VIGILANT_WARD
            )
            VOK_RST_MERCY -> listOf(VOK_RST_HARM, VOK_RST_RESPITE)
            VOK_RST_HARM -> listOf(VOK_RST_SUNS_JUDGMENT)
            VOK_RST_SUNS_JUDGMENT -> listOf(VOK_RST_REBUKE_UNDEAD)
            VOK_RST_RESPITE -> listOf(VOK_RST_ETERNAL_FLAME)
            VOK_RST_VIGILANT_WARD -> listOf(VOK_RST_WARD_ABSORB)
            VOK_RST_WARD_ABSORB -> listOf(VOK_RST_MAGE_WARD)
            VOK_RST_INSPIRE -> listOf(VOK_RST_NECROMAGE)
            VOK_RST_INNER_LIGHT -> listOf(VOK_RST_BLESSED)
            VOK_RST_BLESSED -> listOf(VOK_RST_INTERVENTION)
            else -> emptyList()
        }
}