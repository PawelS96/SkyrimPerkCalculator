package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Block(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_BLC_SHIELD_WALL                    (0.5f,    0.95f,    0, 20, 40, 60, 80),
    VAN_BLC_DEFLECT_ARROWS                 (0.15f,   0.75f,    30),
    VAN_BLC_ELEMENTAL_PROTECTION           (0.2f,    0.45f,    50),
    VAN_BLC_BLOCK_RUNNER                   (0.35f,   0.35f,    70),
    VAN_BLC_POWER_BASH                     (0.85f,   0.75f,    30),
    VAN_BLC_DEADLY_BASH                    (0.8f,    0.5f,     50),
    VAN_BLC_DISARMING_BASH                 (0.7f,    0.35f,    70),
    VAN_BLC_SHIELD_CHARGE                  (0.5f,    0.3f,     100),
    VAN_BLC_QUICK_REFLEXES                 (0.4f,    0.65f,    30);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}