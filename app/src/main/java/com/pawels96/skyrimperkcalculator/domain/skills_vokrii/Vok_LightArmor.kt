package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_LightArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_LAR_LIGHT_ARMOR_MASTERY  (0.55f,  0.95f,  0),
    VOK_LAR_AGILITY              (0.7f,   0.7f,   20),
    VOK_LAR_IRON_FIST            (0.3f,   0.7f,   20, 50, 80),
    VOK_LAR_LIGHT_ARMOR_FIT      (0.5f,   0.75f,  30),
    VOK_LAR_KEEN_SENSES          (0.55f,  0.55f,  40),
    VOK_LAR_WINDRUNNER           (0.675f, 0.375f, 40),
    VOK_LAR_LIGHT_ARMOR_TRAINING (0.45f,  0.4f,   50),
    VOK_LAR_FLURRY_OF_BLOWS      (0.2f,   0.35f,  60, 90),
    VOK_LAR_WARDANCER            (0.625f, 0.225f, 60),
    VOK_LAR_MATCHING_LIGHT_SET   (0.35f,  0.2f,   70),
    VOK_LAR_EVASIVE_SPRINT       (0.55f,  0.075f, 80),
    VOK_LAR_TOUGH_HIDE           (0.475f, 0.15f,  80),
    VOK_LAR_KI_STRIKE            (0.3f,   0.1f,   90),
    VOK_LAR_UNTOUCHABLE          (0.75f,  0.05f,  100);

    override val perkInfo: PerkInfo  = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_LAR_LIGHT_ARMOR_MASTERY -> listOf(
                VOK_LAR_IRON_FIST,
                VOK_LAR_LIGHT_ARMOR_FIT,
                VOK_LAR_AGILITY
            )
            VOK_LAR_IRON_FIST -> listOf(VOK_LAR_FLURRY_OF_BLOWS)
            VOK_LAR_LIGHT_ARMOR_FIT -> listOf(VOK_LAR_LIGHT_ARMOR_TRAINING, VOK_LAR_KEEN_SENSES)
            VOK_LAR_AGILITY -> listOf(VOK_LAR_WINDRUNNER)
            VOK_LAR_WINDRUNNER -> listOf(VOK_LAR_WARDANCER)
            VOK_LAR_WARDANCER -> listOf(VOK_LAR_EVASIVE_SPRINT, VOK_LAR_UNTOUCHABLE)
            VOK_LAR_LIGHT_ARMOR_TRAINING -> listOf(VOK_LAR_MATCHING_LIGHT_SET, VOK_LAR_TOUGH_HIDE)
            VOK_LAR_FLURRY_OF_BLOWS -> listOf(VOK_LAR_KI_STRIKE)
            else -> emptyList()
        }
}