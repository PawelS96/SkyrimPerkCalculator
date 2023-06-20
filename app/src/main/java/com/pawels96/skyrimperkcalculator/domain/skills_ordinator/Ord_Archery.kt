package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Archery(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ARC_ARCHERY_MASTERY        (0.5f,  0.95f,  0, 20),
    ORD_ARC_CLEAN_KILL             (0.55f, 0.7f,   20),
    ORD_ARC_WINGSTRIKE             (0.8f,  0.9f,   20, 50),
    ORD_ARC_LONG_SHOT              (0.15f, 0.75f,  30, 60, 90),
    ORD_ARC_STEADY_HAND            (0.8f,  0.7f,   30, 60, 90),
    ORD_ARC_CRIPPLING_SHOT         (0.3f,  0.65f,  40),
    ORD_ARC_HUNTERS_DISCIPLINE     (0.85f, 0.45f,  40),
    ORD_ARC_THREAD_THE_NEEDLE      (0.1f,  0.55f,  40, 70),
    ORD_ARC_PINNING_SHOT           (0.25f, 0.45f,  50),
    ORD_ARC_RANGER                 (0.65f, 0.4f,   50),
    ORD_ARC_FOCUS_ON_THE_PREY      (0.6f,  0.3f,   50),
    ORD_ARC_QUICK_SHOT             (0.45f, 0.4f,   60),
    ORD_ARC_SNIPE                  (0.32f, 0.35f,  60),
    ORD_ARC_AMBUSH_PREDATOR        (0.1f,  0.3f,   70),
    ORD_ARC_BEAK_AND_TALON         (0.25f, 0.25f,  70),
    ORD_ARC_HAILSTORM              (0.5f,  0.2f,   80),
    ORD_ARC_HAWKEYE                (0.4f,  0.15f,  80),
    ORD_ARC_TRICK_ARROWS           (0.7f,  0.2f,   80),
    ORD_ARC_HUNT_TOGETHER          (0.8f,  0.1f,   90),
    ORD_ARC_LIONS_ARROW            (0.6f,  0.05f,  90),
    ORD_ARC_THREE_CROWS            (0.2f,  0.15f,  90),
    ORD_ARC_PERFECT_AIM            (0.3f,  0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ARC_ARCHERY_MASTERY -> listOf(
                ORD_ARC_LONG_SHOT,
                ORD_ARC_CRIPPLING_SHOT,
                ORD_ARC_CLEAN_KILL,
                ORD_ARC_STEADY_HAND,
                ORD_ARC_WINGSTRIKE
            )
            ORD_ARC_CLEAN_KILL -> listOf(ORD_ARC_SNIPE, ORD_ARC_QUICK_SHOT, ORD_ARC_RANGER)
            ORD_ARC_LONG_SHOT -> listOf(ORD_ARC_THREAD_THE_NEEDLE)
            ORD_ARC_STEADY_HAND -> listOf(ORD_ARC_HUNTERS_DISCIPLINE)
            ORD_ARC_CRIPPLING_SHOT -> listOf(ORD_ARC_PINNING_SHOT)
            ORD_ARC_HUNTERS_DISCIPLINE -> listOf(ORD_ARC_HUNT_TOGETHER, ORD_ARC_TRICK_ARROWS)
            ORD_ARC_THREAD_THE_NEEDLE -> listOf(ORD_ARC_AMBUSH_PREDATOR)
            ORD_ARC_PINNING_SHOT -> listOf(ORD_ARC_BEAK_AND_TALON)
            ORD_ARC_RANGER -> listOf(ORD_ARC_FOCUS_ON_THE_PREY)
            ORD_ARC_FOCUS_ON_THE_PREY -> listOf(ORD_ARC_LIONS_ARROW)
            ORD_ARC_QUICK_SHOT -> listOf(ORD_ARC_HAWKEYE, ORD_ARC_HAILSTORM)
            ORD_ARC_SNIPE -> listOf(ORD_ARC_HAWKEYE)
            ORD_ARC_AMBUSH_PREDATOR -> listOf(ORD_ARC_THREE_CROWS)
            ORD_ARC_BEAK_AND_TALON -> listOf(ORD_ARC_PERFECT_AIM)
            ORD_ARC_THREE_CROWS -> listOf(ORD_ARC_PERFECT_AIM)
            else -> emptyList()
        }
}
