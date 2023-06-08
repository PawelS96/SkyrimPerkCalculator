package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Pickpocket(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_PCK_PICKPOCKET_MASTERY         (0.5f,   0.95f,  0, 20),
    ORD_PCK_BLOOD_MONEY                (0.75f,  0.85f,  20),
    ORD_PCK_TRAINED_RABBIT             (0.25f,  0.9f,   20),
    ORD_PCK_CUTPURSE                   (0.35f,  0.8f,   30),
    ORD_PCK_THIEFS_EYE                 (0.52f,  0.7f,   40),
    ORD_PCK_BROTHERHOOD_COCKTAIL       (0.1f,   0.72f,  40),
    ORD_PCK_DEATHS_EMPEROR             (0.8f,   0.6f,   40, 70),
    ORD_PCK_ON_THE_RUN                 (0.25f,  0.6f,   40),
    ORD_PCK_LAWLESS_WORLD              (0.4f,   0.55f,  50),
    ORD_PCK_THIEFS_LUCK                (0.6f,   0.5f,   50),
    ORD_PCK_STALK_THE_PREY             (0.3f,   0.4f,   60),
    ORD_PCK_DOOMED_TO_PLUNDER          (0.7f,   0.35f,  70),
    ORD_PCK_TRICKSTER                  (0.15f,  0.45f,  70),
    ORD_PCK_CRIME_WAVE                 (0.5f,   0.3f,   80),
    ORD_PCK_YOU_SAW_NOTHING            (0.25f,  0.25f,  80),
    ORD_PCK_MUTINY                     (0.67f,  0.15f,  90),
    ORD_PCK_ROBBED_BLIND               (0.3f,   0.15f,  90),
    ORD_PCK_DRAGON_HOARD               (0.45f,  0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}
