package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Illusion(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_ILU_ILLUSIONIST         (0.5f,  0.95f, 10, 50),
    ADA_ILU_CAPTIVATING_PRESENCE(0.65f, 0.55f, 20, 60),
    ADA_ILU_INDOMITABLE_WILL    (0.45f, 0.3f,  40, 80),
    ADA_ILU_MASTER_OF_THE_MIND  (0.5f,  0.1f,  60, 100),
    ADA_ILU_GUIDANCE            (0.35f, 0.55f, 30),
    ADA_ILU_SERENITY            (0.1f,  0.6f,  30),
    ADA_ILU_TRANQUILITY         (0.2f,  0.4f,  60),
    ADA_ILU_STASIS              (0.25f, 0.2f,  80),
    ADA_ILU_HOWL_OF_RAGE        (0.9f,  0.6f,  40),
    ADA_ILU_ASPECT_OF_TERROR    (0.8f,  0.4f,  70),
    ADA_ILU_VOICE_OF_AUTHORITY  (0.75f, 0.2f,  90);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}