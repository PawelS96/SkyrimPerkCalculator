package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Pickpocket(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_PCK_PICKPOCKET_MASTERY (0.5f,   0.95f,  0),
    VOK_PCK_CUTPURSE           (0.475f, 0.7f,   20),
    VOK_PCK_OBLIVIOUS          (0.5f,   0.5f,   30),
    VOK_PCK_PAYDAY             (0.25f,  0.625f, 30),
    VOK_PCK_DEATHS_EMPEROR     (0.7f,   0.475f, 40, 70),
    VOK_PCK_POISONED           (0.65f,  0.35f,  40),
    VOK_PCK_EXTRA_POCKETS      (0.8f,   0.775f, 50),
    VOK_PCK_LAWLESS_TIMES      (0.55f,  0.175f, 60, 90),
    VOK_PCK_TRICKSTER          (0.675f, 0.225f, 70),
    VOK_PCK_CONSPICUOUS_WEALTH (0.275f, 0.3f,   80),
    VOK_PCK_MASTER_THIEF       (0.4f,   0.1f,   90),
    VOK_PCK_PERFECT_TOUCH      (0.725f, 0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_PCK_PICKPOCKET_MASTERY -> listOf(
                VOK_PCK_PAYDAY,
                VOK_PCK_CUTPURSE,
                VOK_PCK_EXTRA_POCKETS
            )
            VOK_PCK_CUTPURSE -> listOf(VOK_PCK_OBLIVIOUS, VOK_PCK_DEATHS_EMPEROR)
            VOK_PCK_PAYDAY -> listOf(VOK_PCK_CONSPICUOUS_WEALTH)
            VOK_PCK_CONSPICUOUS_WEALTH -> listOf(VOK_PCK_MASTER_THIEF)
            VOK_PCK_OBLIVIOUS -> listOf(VOK_PCK_POISONED, VOK_PCK_LAWLESS_TIMES)
            VOK_PCK_DEATHS_EMPEROR -> listOf(VOK_PCK_POISONED)
            VOK_PCK_POISONED -> listOf(VOK_PCK_TRICKSTER)
            VOK_PCK_TRICKSTER -> listOf(VOK_PCK_PERFECT_TOUCH)
            else -> emptyList()
        }
}