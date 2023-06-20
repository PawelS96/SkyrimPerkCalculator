package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Speech(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_SPC_SPEECH_MASTERY     (0.5f,   0.95f,  0),
    VOK_SPC_TONAL_HARMONY      (0.7f,   0.65f,  20),
    VOK_SPC_BRIBERY            (0.8f,   0.825f, 30),
    VOK_SPC_KINSHIP            (0.15f,  0.75f,  30),
    VOK_SPC_SPEAK_WITH_ANIMALS (0.475f, 0.6f,   40),
    VOK_SPC_WORDS_OF_POWER     (0.75f,  0.45f,  40, 70),
    VOK_SPC_ELOQUENT           (0.9f,   0.6f,   50),
    VOK_SPC_SALESMAN           (0.3f,   0.35f,  50),
    VOK_SPC_INVESTOR           (0.15f,  0.325f, 60),
    VOK_SPC_SKALD              (0.875f, 0.35f,  60),
    VOK_SPC_BEASTMASTER        (0.55f,  0.325f, 70),
    VOK_SPC_FENCE              (0.275f, 0.225f, 80),
    VOK_SPC_PRIVATE_STOCK      (0.1f,   0.2f,   80),
    VOK_SPC_MASTER_TRADER      (0.2f,   0.05f,  90),
    VOK_SPC_DOVAHZULAAN        (0.7f,   0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_SPC_SPEECH_MASTERY -> listOf(
                VOK_SPC_KINSHIP,
                VOK_SPC_SPEAK_WITH_ANIMALS,
                VOK_SPC_TONAL_HARMONY,
                VOK_SPC_BRIBERY
            )
            VOK_SPC_BRIBERY -> listOf(VOK_SPC_ELOQUENT)
            VOK_SPC_KINSHIP -> listOf(VOK_SPC_SALESMAN)
            VOK_SPC_SALESMAN -> listOf(VOK_SPC_FENCE, VOK_SPC_INVESTOR)
            VOK_SPC_INVESTOR -> listOf(VOK_SPC_PRIVATE_STOCK)
            VOK_SPC_PRIVATE_STOCK -> listOf(VOK_SPC_MASTER_TRADER)
            VOK_SPC_SPEAK_WITH_ANIMALS -> listOf(VOK_SPC_BEASTMASTER)
            VOK_SPC_TONAL_HARMONY -> listOf(VOK_SPC_WORDS_OF_POWER)
            VOK_SPC_WORDS_OF_POWER -> listOf(VOK_SPC_SKALD, VOK_SPC_DOVAHZULAAN)
            else -> emptyList()
        }
}