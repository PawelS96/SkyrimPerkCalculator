package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Lockpicking(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_LCK_MASTERY                     (0.5f,     0.95f,     0, 20),
    ORD_LCK_GAME_OF_FATE                (0.1f,     0.9f,      20),
    ORD_LCK_BEAR_TRAPS                  (0.6f,     0.85f,     20),
    ORD_LCK_LOCKDOWN                    (0.1f,     0.7f,      30),
    ORD_LCK_ROBBERS_EYE                 (0.35f,    0.7f,      30),
    ORD_LCK_LOCKSMITH                   (0.9f,     0.7f,      40),
    ORD_LCK_LOCKJAW                     (0.7f,     0.7f,      40),
    ORD_LCK_HOTWIRE                     (0.1f,     0.4f,      50),
    ORD_LCK_DUNGEONEER                  (0.3f,     0.4f,      60),
    ORD_LCK_NOSE_FOR_TREASURE           (0.2f,     0.5f,      50),
    ORD_LCK_BIG_GAME_HUNTER             (0.55f,    0.5f,      50),
    ORD_LCK_BAIT                        (0.8f,     0.45f,     70),
    ORD_LCK_BUSHWHACK                   (0.65f,    0.35f,     80),
    ORD_LCK_PERCUSSIVE_MAINTENANCE      (0.15f,    0.3f,      90),
    ORD_LCK_GONE_IN_FIFTEEN_SECONDS     (0.4f,     0.55f,     40),
    ORD_LCK_GOLDEN_TOUCH                (0.5f,     0.4f,      60),
    ORD_LCK_TREASURE_HUNTER             (0.4f,     0.275f,    90),
    ORD_LCK_THE_REVENGE                 (0.8f,     0.3f,      80),
    ORD_LCK_DRAGONS_TEETH               (0.65f,    0.2f,      100),
    ORD_LCK_WAX_KEY                     (0.9f,     0.9f,      30),
    ORD_LCK_SEEN_THIS_BEFORE            (0.9f,     0.55f,     100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_LCK_MASTERY -> listOf(
                ORD_LCK_GAME_OF_FATE,
                ORD_LCK_LOCKDOWN,
                ORD_LCK_ROBBERS_EYE,
                ORD_LCK_BEAR_TRAPS,
                ORD_LCK_WAX_KEY
            )
            ORD_LCK_LOCKDOWN -> listOf(ORD_LCK_HOTWIRE)
            ORD_LCK_HOTWIRE -> listOf(ORD_LCK_PERCUSSIVE_MAINTENANCE)
            ORD_LCK_ROBBERS_EYE -> listOf(
                ORD_LCK_NOSE_FOR_TREASURE,
                ORD_LCK_GONE_IN_FIFTEEN_SECONDS
            )
            ORD_LCK_GONE_IN_FIFTEEN_SECONDS -> listOf(ORD_LCK_DUNGEONEER, ORD_LCK_GOLDEN_TOUCH)
            ORD_LCK_NOSE_FOR_TREASURE -> listOf(ORD_LCK_DUNGEONEER)
            ORD_LCK_DUNGEONEER -> listOf(ORD_LCK_TREASURE_HUNTER)
            ORD_LCK_BEAR_TRAPS -> listOf(ORD_LCK_LOCKJAW)
            ORD_LCK_LOCKJAW -> listOf(ORD_LCK_BIG_GAME_HUNTER, ORD_LCK_BAIT)
            ORD_LCK_BIG_GAME_HUNTER -> listOf(ORD_LCK_BUSHWHACK)
            ORD_LCK_BUSHWHACK -> listOf(ORD_LCK_DRAGONS_TEETH)
            ORD_LCK_BAIT -> listOf(ORD_LCK_BUSHWHACK, ORD_LCK_THE_REVENGE)
            ORD_LCK_THE_REVENGE -> listOf(ORD_LCK_DRAGONS_TEETH)
            ORD_LCK_WAX_KEY -> listOf(ORD_LCK_LOCKSMITH)
            ORD_LCK_LOCKSMITH -> listOf(ORD_LCK_SEEN_THIS_BEFORE)
            ORD_LCK_GOLDEN_TOUCH -> listOf(ORD_LCK_TREASURE_HUNTER)
            else -> emptyList()
        }
}
