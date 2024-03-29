package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_OneHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_ONH_ONE_HANDED_MASTERY                 (0.5f,     0.95f,   0, 20),
    ORD_ONH_DISCIPLINED_FIGHTER                (0.2f,     0.8f,    20),
    ORD_ONH_BITE_MARKS                         (0.75f,    0.8f,    30, 60, 90),
    ORD_ONH_BLEED_LIKE_A_LAMB                  (0.35f,    0.725f,  30, 60, 90),
    ORD_ONH_CLASH_OF_CHAMPIONS                 (0.65f,    0.7f,    30, 60, 90),
    ORD_ONH_DENTING_BLOWS                      (0.5f,     0.8f,    30, 60, 90),
    ORD_ONH_RAVAGE                             (0.9f,     0.85f,   30, 60),
    ORD_ONH_CROSS_CUT                          (0.6f,     0.6f,    40, 70),
    ORD_ONH_FURIOUS_STRENGTH                   (0.05f,    0.85f,   40),
    ORD_ONH_MANGLE                             (0.3f,     0.6f,    40, 70),
    ORD_ONH_SAVAGE                             (0.75f,    0.65f,   40, 70),
    ORD_ONH_SMITE                              (0.45f,    0.65f,   40, 70),
    ORD_ONH_FALLING_SWORD                      (0.55f,    0.5f,    50),
    ORD_ONH_OVERRUN                            (0.05f,    0.5f,    50),
    ORD_ONH_RISE_KINSMEN                       (0.45f,    0.55f,   50),
    ORD_ONH_ROGUES_PARRY                       (0.12f,    0.65f,   50),
    ORD_ONH_SHIELDBITER                        (0.35f,    0.5f,    50),
    ORD_ONH_TWIN_FANG                          (0.7f,     0.45f,   50),
    ORD_ONH_MAN_OWAR                           (0.9f,     0.5f,    60),
    ORD_ONH_SWAYING_COBRA                      (0.7f,     0.35f,   60),
    ORD_ONH_TOLL_THE_BELL                      (0.45f,    0.42f,   60),
    ORD_ONH_WINDSWEPT                          (0.6f,     0.4f,    60),
    ORD_ONH_WOLFSTOOTH                         (0.3f,     0.4f,    60),
    ORD_ONH_DEATH_ADDER                        (0.7f,     0.225f,  70),
    ORD_ONH_GO_FOR_THE_THROAT                  (0.3f,     0.25f,   70),
    ORD_ONH_INTO_THE_DUST                      (0.55f,    0.25f,   70),
    ORD_ONH_METEOR_STORM                       (0.45f,    0.3f,    70),
    ORD_ONH_THUNDERING_BLOW                    (0.2f,     0.55f,   70),
    ORD_ONH_APEX_PREDATOR                      (0.35f,    0.15f,   80),
    ORD_ONH_COILING_PYTHON                     (0.65f,    0.1f,    80),
    ORD_ONH_JUDGMENT                           (0.55f,    0.15f,   80),
    ORD_ONH_SKULL_CRACK                        (0.45f,    0.2f,    80),
    ORD_ONH_AFTERSHOCK                         (0.2f,     0.35f,   90),
    ORD_ONH_UNLEASH_THE_BEAST                  (0.875f,   0.3f,    90),
    ORD_ONH_WANDERING_WARRIOR                  (0.45f,    0.05f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_ONH_ONE_HANDED_MASTERY -> listOf(
                ORD_ONH_FURIOUS_STRENGTH,
                ORD_ONH_DISCIPLINED_FIGHTER,
                ORD_ONH_BLEED_LIKE_A_LAMB,
                ORD_ONH_DENTING_BLOWS,
                ORD_ONH_CLASH_OF_CHAMPIONS,
                ORD_ONH_BITE_MARKS,
                ORD_ONH_RAVAGE
            )
            ORD_ONH_DISCIPLINED_FIGHTER -> listOf(ORD_ONH_ROGUES_PARRY, ORD_ONH_THUNDERING_BLOW)
            ORD_ONH_BITE_MARKS -> listOf(ORD_ONH_SAVAGE)
            ORD_ONH_BLEED_LIKE_A_LAMB -> listOf(ORD_ONH_MANGLE)
            ORD_ONH_CLASH_OF_CHAMPIONS -> listOf(ORD_ONH_CROSS_CUT)
            ORD_ONH_DENTING_BLOWS -> listOf(ORD_ONH_SMITE)
            ORD_ONH_RAVAGE -> listOf(ORD_ONH_MAN_OWAR)
            ORD_ONH_CROSS_CUT -> listOf(ORD_ONH_FALLING_SWORD)
            ORD_ONH_FURIOUS_STRENGTH -> listOf(ORD_ONH_OVERRUN)
            ORD_ONH_MANGLE -> listOf(ORD_ONH_SHIELDBITER)
            ORD_ONH_SAVAGE -> listOf(ORD_ONH_TWIN_FANG)
            ORD_ONH_SMITE -> listOf(ORD_ONH_RISE_KINSMEN)
            ORD_ONH_FALLING_SWORD -> listOf(ORD_ONH_WINDSWEPT)
            ORD_ONH_RISE_KINSMEN -> listOf(ORD_ONH_TOLL_THE_BELL)
            ORD_ONH_SHIELDBITER -> listOf(ORD_ONH_WOLFSTOOTH)
            ORD_ONH_TWIN_FANG -> listOf(ORD_ONH_SWAYING_COBRA)
            ORD_ONH_MAN_OWAR -> listOf(ORD_ONH_UNLEASH_THE_BEAST)
            ORD_ONH_SWAYING_COBRA -> listOf(ORD_ONH_DEATH_ADDER)
            ORD_ONH_TOLL_THE_BELL -> listOf(ORD_ONH_METEOR_STORM)
            ORD_ONH_WINDSWEPT -> listOf(ORD_ONH_INTO_THE_DUST)
            ORD_ONH_WOLFSTOOTH -> listOf(ORD_ONH_GO_FOR_THE_THROAT)
            ORD_ONH_DEATH_ADDER -> listOf(ORD_ONH_COILING_PYTHON)
            ORD_ONH_GO_FOR_THE_THROAT -> listOf(ORD_ONH_APEX_PREDATOR)
            ORD_ONH_INTO_THE_DUST -> listOf(ORD_ONH_JUDGMENT)
            ORD_ONH_METEOR_STORM -> listOf(ORD_ONH_SKULL_CRACK)
            ORD_ONH_THUNDERING_BLOW -> listOf(ORD_ONH_AFTERSHOCK)
            ORD_ONH_APEX_PREDATOR -> listOf(ORD_ONH_WANDERING_WARRIOR)
            ORD_ONH_COILING_PYTHON -> listOf(ORD_ONH_WANDERING_WARRIOR)
            ORD_ONH_JUDGMENT -> listOf(ORD_ONH_WANDERING_WARRIOR)
            ORD_ONH_SKULL_CRACK -> listOf(ORD_ONH_WANDERING_WARRIOR)
            else -> emptyList()
        }
}
