package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class LightArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_LAR_AGILE_DEFENDER             (0.55f,  0.95f,   0, 20, 40, 60, 80),
    VAN_LAR_CUSTOM_FIT                 (0.5f,   0.7f,    30),
    VAN_LAR_MATCHING_SET               (0.65f,  0.2f,    70),
    VAN_LAR_UNHINDERED                 (0.3f,   0.45f,   50),
    VAN_LAR_WIND_WALKER                (0.35f,  0.25f,   60),
    VAN_LAR_DEFT_MOVEMENT              (0.5f,   0.1f,    100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_LAR_AGILE_DEFENDER -> listOf(VAN_LAR_CUSTOM_FIT)
            VAN_LAR_CUSTOM_FIT -> listOf(VAN_LAR_UNHINDERED, VAN_LAR_MATCHING_SET)
            VAN_LAR_MATCHING_SET -> listOf(VAN_LAR_DEFT_MOVEMENT)
            VAN_LAR_UNHINDERED -> listOf(VAN_LAR_WIND_WALKER)
            VAN_LAR_WIND_WALKER -> listOf(VAN_LAR_DEFT_MOVEMENT)
            else -> emptyList()
        }
}