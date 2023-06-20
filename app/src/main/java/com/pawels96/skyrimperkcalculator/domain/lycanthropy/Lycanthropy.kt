package com.pawels96.skyrimperkcalculator.domain.lycanthropy

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk

enum class Lycanthropy(
    val x: Float,
    val y: Float,
    maxLevel: Int = 1
) : SpecialSkillPerk {

    VAN_LYC_BESTIAL_STRENGTH       (0.5f,   0.95f, 4),
    VAN_LYC_TOTEM_OF_ICE_BROTHERS  (0.25f,  0.6f),
    VAN_LYC_TOTEM_OF_THE_MOON      (0.1f,   0.35f),
    VAN_LYC_TOTEM_OF_THE_PREDATOR  (0.4f,   0.2f),
    VAN_LYC_TOTEM_OF_TERROR        (0.6f,   0.3f),
    VAN_LYC_ANIMAL_VIGOR           (0.7f,   0.7f),
    VAN_LYC_GORGING                (0.875f, 0.6f),
    VAN_LYC_SAVAGE_FEEDING         (0.9f,   0.35f);

    override val perkInfo: PerkInfo = PerkInfo(IntArray(maxLevel), x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_LYC_BESTIAL_STRENGTH -> listOf(
                VAN_LYC_TOTEM_OF_ICE_BROTHERS,
                VAN_LYC_TOTEM_OF_THE_PREDATOR,
                VAN_LYC_TOTEM_OF_TERROR,
                VAN_LYC_ANIMAL_VIGOR
            )
            VAN_LYC_TOTEM_OF_ICE_BROTHERS -> listOf(VAN_LYC_TOTEM_OF_THE_MOON)
            VAN_LYC_ANIMAL_VIGOR -> listOf(VAN_LYC_GORGING)
            VAN_LYC_GORGING -> listOf(VAN_LYC_SAVAGE_FEEDING)
            else -> emptyList()
        }
}

