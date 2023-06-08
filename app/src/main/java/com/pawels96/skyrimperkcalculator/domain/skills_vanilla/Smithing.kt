package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Smithing(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_SMT_STEEL_SMITHING                (0.35f,  0.95f,  0),
    VAN_SMT_ARCANE_BLACKSMITH             (0.35f,  0.75f,  60),
    VAN_SMT_DWARVEN_SMITHING              (0.6f,   0.7f,   30),
    VAN_SMT_ORCISH_SMITHING               (0.7f,   0.6f,   50),
    VAN_SMT_EBONY_SMITHING                (0.9f,   0.55f,  80),
    VAN_SMT_DAEDRIC_SMITHING              (0.7f,   0.475f, 90),
    VAN_SMT_ELVEN_SMITHING                (0.1f,   0.7f,   30),
    VAN_SMT_ADVANCED_ARMORS               (0.125f, 0.55f,  50),
    VAN_SMT_GLASS_SMITHING                (0.25f,  0.45f,  70),
    VAN_SMT_DRAGON_ARMOR                  (0.5f,   0.45f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}