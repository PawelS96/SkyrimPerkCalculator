package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Sneak(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_SNK_SNEAK_MASTERY             (0.5f,    0.95f,  0, 20),
    ORD_SNK_SNEAK_ATTACK              (0.75f,   0.7f,   20),
    ORD_SNK_TRIPWIRE                  (0.2f,    0.75f,  20),
    ORD_SNK_DEMOLITION_JOB            (0.75f,   0.9f,   30),
    ORD_SNK_FOG_OF_WAR                (0.48f,   0.75f,  30),
    ORD_SNK_SILENT_ROLL               (0.35f,   0.7f,   30),
    ORD_SNK_SPOT_DETECTION            (0.15f,   0.9f,   30),
    ORD_SNK_ASSASSINS_BLADE           (0.9f,    0.55f,  40),
    ORD_SNK_DYNAMIC_ENTRY             (0.4f,    0.55f,  40),
    ORD_SNK_INFILTRATOR               (0.6f,    0.65f,  40),
    ORD_SNK_WHIPLASH                  (0.25f,   0.6f,   40),
    ORD_SNK_BACKSTAB                  (0.85f,   0.45f,  50, 80),
    ORD_SNK_LIGHT_FOOT                (0.1f,    0.65f,  50),
    ORD_SNK_RIGHT_BEHIND_YOU          (0.55f,   0.5f,   50),
    ORD_SNK_DISENGAGE                 (0.45f,   0.45f,  60),
    ORD_SNK_DODGE_ROLL                (0.3f,    0.4f,   60),
    ORD_SNK_SMOKESCREEN               (0.58f,   0.35f,  60),
    ORD_SNK_BACKUP_PLAN               (0.15f,   0.35f,  70),
    ORD_SNK_CLEAN_ESCAPE              (0.65f,   0.42f,  70),
    ORD_SNK_BEHIND_ENEMY_LINES        (0.45f,   0.25f,  80),
    ORD_SNK_PARTYSTARTER              (0.58f,   0.2f,   80),
    ORD_SNK_PROBLEM_SOLVER            (0.75f,   0.25f,  80),
    ORD_SNK_CLOAK_AND_DAGGER          (0.9f,    0.2f,   90),
    ORD_SNK_GREASED_LIGHTNING         (0.25f,   0.2f,   90),
    ORD_SNK_SHADOW_WARRIOR            (0.5f,    0.1f,   90),
    ORD_SNK_LAUGHING_GHOST            (0.8f,    0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_SNK_SNEAK_MASTERY -> listOf(
                ORD_SNK_SPOT_DETECTION,
                ORD_SNK_TRIPWIRE,
                ORD_SNK_SILENT_ROLL,
                ORD_SNK_FOG_OF_WAR,
                ORD_SNK_INFILTRATOR,
                ORD_SNK_SNEAK_ATTACK,
                ORD_SNK_DEMOLITION_JOB
            )
            ORD_SNK_SNEAK_ATTACK -> listOf(ORD_SNK_PROBLEM_SOLVER, ORD_SNK_ASSASSINS_BLADE)
            ORD_SNK_TRIPWIRE -> listOf(ORD_SNK_WHIPLASH)
            ORD_SNK_FOG_OF_WAR -> listOf(ORD_SNK_RIGHT_BEHIND_YOU)
            ORD_SNK_SILENT_ROLL -> listOf(ORD_SNK_DYNAMIC_ENTRY)
            ORD_SNK_SPOT_DETECTION -> listOf(ORD_SNK_LIGHT_FOOT)
            ORD_SNK_ASSASSINS_BLADE -> listOf(ORD_SNK_BACKSTAB)
            ORD_SNK_DYNAMIC_ENTRY -> listOf(ORD_SNK_DODGE_ROLL)
            ORD_SNK_INFILTRATOR -> listOf(ORD_SNK_RIGHT_BEHIND_YOU)
            ORD_SNK_WHIPLASH -> listOf(ORD_SNK_BACKUP_PLAN)
            ORD_SNK_RIGHT_BEHIND_YOU -> listOf(
                ORD_SNK_DISENGAGE,
                ORD_SNK_BEHIND_ENEMY_LINES,
                ORD_SNK_SMOKESCREEN,
                ORD_SNK_CLEAN_ESCAPE
            )
            ORD_SNK_DODGE_ROLL -> listOf(
                ORD_SNK_BACKUP_PLAN,
                ORD_SNK_GREASED_LIGHTNING,
                ORD_SNK_BEHIND_ENEMY_LINES
            )
            ORD_SNK_SMOKESCREEN -> listOf(ORD_SNK_PARTYSTARTER)
            ORD_SNK_BEHIND_ENEMY_LINES -> listOf(ORD_SNK_SHADOW_WARRIOR)
            ORD_SNK_PROBLEM_SOLVER -> listOf(ORD_SNK_LAUGHING_GHOST, ORD_SNK_CLOAK_AND_DAGGER)
            else -> emptyList()
        }
}
