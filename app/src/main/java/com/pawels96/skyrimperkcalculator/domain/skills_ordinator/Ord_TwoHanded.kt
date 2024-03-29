package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_TwoHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_TWH_TWO_HANDED_MASTERY                  (0.5f,       0.95f,  0, 20),
    ORD_TWH_TRAINED_FIGHTER                     (0.1f,       0.8f,   20),
    ORD_TWH_BLEED_LIKE_A_DOG                    (0.3f,       0.75f,  30, 60, 90),
    ORD_TWH_CLASH_OF_HEROES                     (0.65f,      0.75f,  30, 60, 90),
    ORD_TWH_CRUSHING_BLOWS                      (0.5f,       0.8f,   30, 60, 90),
    ORD_TWH_BATTER                              (0.5f,       0.65f,  40, 70),
    ORD_TWH_FEROCIOUS_STRENGTH                  (0.8f,       0.8f,   40),
    ORD_TWH_MAUL                                (0.675f,     0.6f,   40, 70),
    ORD_TWH_RIVE                                (0.3f,       0.6f,   40, 70),
    ORD_TWH_AVALANCHE                           (0.5f,       0.5f,   50),
    ORD_TWH_BREACH_THE_WALL                     (0.65f,      0.45f,  50),
    ORD_TWH_EXECUTE                             (0.35f,      0.45f,  50),
    ORD_TWH_TRAMPLE                             (0.9f,       0.6f,   50),
    ORD_TWH_DEATH_OR_GLORY                      (0.1f,       0.4f,   60),
    ORD_TWH_DECIMATE                            (0.35f,      0.35f,  60),
    ORD_TWH_SUBJUGATE                           (0.675f,     0.35f,  60),
    ORD_TWH_THE_PENDULUM                        (0.5f,       0.4f,   60),
    ORD_TWH_BISECT                              (0.325f,     0.25f,  70),
    ORD_TWH_GRAND_SLAM                          (0.5f,       0.3f,   70),
    ORD_TWH_HUMILIATE                           (0.625f,     0.25f,  70),
    ORD_TWH_MASSACRE                            (0.8f,       0.35f,  70),
    ORD_TWH_DEADFALL                            (0.5f,       0.2f,   80),
    ORD_TWH_OVERTHROW                           (0.6f,       0.15f,  80),
    ORD_TWH_RAMS_HEAD                           (0.35f,      0.15f,  80),
    ORD_TWH_WOLFKIN                             (0.15f,      0.2f,   80),
    ORD_TWH_BEAR_HIDE                           (0.9f,       0.3f,   90),
    ORD_TWH_ENTER_THE_ARENA                     (0.825f,     0.2f,   90),
    ORD_TWH_VOICE_OF_RAGE_AND_RUIN              (0.725f,     0.1f,   90),
    ORD_TWH_SLAYER_OF_A_THOUSAND                (0.5f,       0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_TWH_TWO_HANDED_MASTERY -> listOf(
                ORD_TWH_TRAINED_FIGHTER,
                ORD_TWH_BLEED_LIKE_A_DOG,
                ORD_TWH_CRUSHING_BLOWS,
                ORD_TWH_CLASH_OF_HEROES,
                ORD_TWH_FEROCIOUS_STRENGTH
            )
            ORD_TWH_TRAINED_FIGHTER -> listOf(ORD_TWH_DEATH_OR_GLORY)
            ORD_TWH_BLEED_LIKE_A_DOG -> listOf(ORD_TWH_RIVE)
            ORD_TWH_CLASH_OF_HEROES -> listOf(ORD_TWH_MAUL)
            ORD_TWH_CRUSHING_BLOWS -> listOf(ORD_TWH_BATTER)
            ORD_TWH_BATTER -> listOf(ORD_TWH_AVALANCHE)
            ORD_TWH_FEROCIOUS_STRENGTH -> listOf(ORD_TWH_TRAMPLE)
            ORD_TWH_MAUL -> listOf(ORD_TWH_BREACH_THE_WALL)
            ORD_TWH_RIVE -> listOf(ORD_TWH_EXECUTE)
            ORD_TWH_AVALANCHE -> listOf(ORD_TWH_THE_PENDULUM)
            ORD_TWH_BREACH_THE_WALL -> listOf(ORD_TWH_SUBJUGATE)
            ORD_TWH_EXECUTE -> listOf(ORD_TWH_DECIMATE)
            ORD_TWH_TRAMPLE -> listOf(ORD_TWH_MASSACRE, ORD_TWH_BEAR_HIDE)
            ORD_TWH_DEATH_OR_GLORY -> listOf(ORD_TWH_WOLFKIN)
            ORD_TWH_DECIMATE -> listOf(ORD_TWH_BISECT)
            ORD_TWH_SUBJUGATE -> listOf(ORD_TWH_HUMILIATE)
            ORD_TWH_THE_PENDULUM -> listOf(ORD_TWH_GRAND_SLAM)
            ORD_TWH_BISECT -> listOf(ORD_TWH_RAMS_HEAD)
            ORD_TWH_GRAND_SLAM -> listOf(ORD_TWH_DEADFALL)
            ORD_TWH_HUMILIATE -> listOf(ORD_TWH_OVERTHROW)
            ORD_TWH_MASSACRE -> listOf(ORD_TWH_VOICE_OF_RAGE_AND_RUIN, ORD_TWH_ENTER_THE_ARENA)
            ORD_TWH_DEADFALL -> listOf(ORD_TWH_SLAYER_OF_A_THOUSAND)
            ORD_TWH_OVERTHROW -> listOf(ORD_TWH_SLAYER_OF_A_THOUSAND)
            ORD_TWH_RAMS_HEAD -> listOf(ORD_TWH_SLAYER_OF_A_THOUSAND)
            else -> emptyList()
        }
}
