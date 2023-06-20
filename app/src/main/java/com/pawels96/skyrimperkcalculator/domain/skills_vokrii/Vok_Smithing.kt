package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Smithing(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_SMT_STEEL_SMITHING         (0.4f,   0.95f,  0),
    VOK_SMT_ARMOR_PADDING          (0.3f,   0.7f,   20),
    VOK_SMT_ELVEN_SMITHING         (0.7f,   0.75f,  30),
    VOK_SMT_DWARVEN_SMITHING       (0.125f, 0.75f,  30),
    VOK_SMT_ARCANE_BLACKSMITH      (0.55f,  0.6f,   40),
    VOK_SMT_ORCISH_SMITHING        (0.2f,   0.4f,   50),
    VOK_SMT_ADVANCED_ARMORS        (0.75f,  0.4f,   50),
    VOK_SMT_LAYERED_PLATES         (0.35f,  0.45f,  60),
    VOK_SMT_GLASS_SMITHING         (0.9f,   0.25f,  70),
    VOK_SMT_EBONY_SMITHING         (0.15f,  0.15f,  80),
    VOK_SMT_HIGH_YIELD_MINING      (0.5f,   0.2f,   80),
    VOK_SMT_CONCEALED_ENCHANTMENTS (0.5f,   0.35f,  90),
    VOK_SMT_DAEDRIC_SMITHING       (0.375f, 0.1f,   90),
    VOK_SMT_DRAGON_SMITHING        (0.625f, 0.075f, 100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_SMT_STEEL_SMITHING -> listOf(
                VOK_SMT_DWARVEN_SMITHING,
                VOK_SMT_ARMOR_PADDING,
                VOK_SMT_ARCANE_BLACKSMITH,
                VOK_SMT_ELVEN_SMITHING
            )
            VOK_SMT_DWARVEN_SMITHING -> listOf(VOK_SMT_ORCISH_SMITHING)
            VOK_SMT_ORCISH_SMITHING -> listOf(VOK_SMT_EBONY_SMITHING, VOK_SMT_HIGH_YIELD_MINING)
            VOK_SMT_EBONY_SMITHING -> listOf(VOK_SMT_DAEDRIC_SMITHING)
            VOK_SMT_DAEDRIC_SMITHING -> listOf(VOK_SMT_DRAGON_SMITHING)
            VOK_SMT_ARMOR_PADDING -> listOf(VOK_SMT_LAYERED_PLATES)
            VOK_SMT_LAYERED_PLATES -> listOf(VOK_SMT_CONCEALED_ENCHANTMENTS)
            VOK_SMT_ELVEN_SMITHING -> listOf(VOK_SMT_ADVANCED_ARMORS)
            VOK_SMT_ADVANCED_ARMORS -> listOf(VOK_SMT_HIGH_YIELD_MINING, VOK_SMT_GLASS_SMITHING)
            VOK_SMT_GLASS_SMITHING -> listOf(VOK_SMT_DRAGON_SMITHING)
            else -> emptyList()
        }
}