package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Archery(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ARC_OVERDRAW                     (0.75f,  0.95f,  0, 20, 40, 60, 80),
    VAN_ARC_CRITICAL_SHOT                (0.7f,   0.6f,   30, 60, 90),
    VAN_ARC_HUNTERS_DISCIPLINE           (0.6f,   0.35f,  50),
    VAN_ARC_RANGER                       (0.55f,  0.25f,  60),
    VAN_ARC_EAGLE_EYE                    (0.4f,   0.75f,  30),
    VAN_ARC_POWER_SHOT                   (0.3f,   0.45f,  50),
    VAN_ARC_QUICK_SHOT                   (0.35f,  0.2f,   70),
    VAN_ARC_STEADY_HAND                  (0.5f,   0.65f,  40, 60),
    VAN_ARC_BULLSEYE                     (0.45f,  0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ARC_OVERDRAW -> listOf(VAN_ARC_CRITICAL_SHOT, VAN_ARC_EAGLE_EYE)
            VAN_ARC_CRITICAL_SHOT -> listOf(VAN_ARC_HUNTERS_DISCIPLINE)
            VAN_ARC_HUNTERS_DISCIPLINE -> listOf(VAN_ARC_RANGER)
            VAN_ARC_RANGER -> listOf(VAN_ARC_BULLSEYE)
            VAN_ARC_EAGLE_EYE -> listOf(VAN_ARC_POWER_SHOT, VAN_ARC_STEADY_HAND)
            VAN_ARC_POWER_SHOT -> listOf(VAN_ARC_QUICK_SHOT)
            VAN_ARC_QUICK_SHOT -> listOf(VAN_ARC_BULLSEYE)
            else -> emptyList()
        }
}