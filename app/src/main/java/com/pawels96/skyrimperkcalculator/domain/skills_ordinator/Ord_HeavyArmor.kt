package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_HeavyArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_HAR_MASTERY             (0.5f,    0.95f,   0, 20),
    ORD_HAR_CUSHIONED           (0.45f,   0.8f,    20, 50),
    ORD_HAR_BATTLE_WEARY        (0.3f,    0.85f,   30),
    ORD_HAR_HEAVY_ARMOR_FIT     (0.7f,    0.85f,   30),
    ORD_HAR_BREAK_UPON_ME       (0.15f,   0.75f,   60),
    ORD_HAR_RISE_ABOVE          (0.1f,    0.45f,   80),
    ORD_HAR_PRIMAL_FEAR         (0.1f,    0.35f,   90),
    ORD_HAR_REAP_THE_WHIRLWIND  (0.25f,   0.5f,    70),
    ORD_HAR_BORN_TO_FIGHT       (0.4f,    0.55f,   50),
    ORD_HAR_FACE_OF_DEATH       (0.5f,    0.7f,    40),
    ORD_HAR_SOVEREIGN           (0.25f,   0.25f,   90),
    ORD_HAR_FACE_OF_THE_MOUNTAIN(0.3f,    0.15f,   100),
    ORD_HAR_LEAD_THE_TEMPEST    (0.5f,    0.3f,    70),
    ORD_HAR_BEDROCK             (0.6f,    0.55f,   60),
    ORD_HAR_DEFIANCE            (0.7f,    0.7f,    40),
    ORD_HAR_RALLYING_STANDARD   (0.85f,   0.75f,   40),
    ORD_HAR_NEVER_KNEEL         (0.7f,    0.45f,   60),
    ORD_HAR_REVEL_IN_BATTLE     (0.6f,    0.4f,    50),
    ORD_HAR_OUT_OF_THE_INFERNO  (0.75f,   0.35f,   80),
    ORD_HAR_WARBRINGER          (0.85f,   0.5f,    80),
    ORD_HAR_DOOMBRINGER         (0.9f,    0.25f,   90),
    ORD_HAR_IMMORTAL            (0.8f,    0.2f,    90);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_HAR_MASTERY -> listOf(
                ORD_HAR_CUSHIONED,
                ORD_HAR_HEAVY_ARMOR_FIT,
                ORD_HAR_BATTLE_WEARY
            )
            ORD_HAR_BREAK_UPON_ME -> listOf(ORD_HAR_RISE_ABOVE, ORD_HAR_REAP_THE_WHIRLWIND)
            ORD_HAR_BATTLE_WEARY -> listOf(ORD_HAR_BREAK_UPON_ME, ORD_HAR_BORN_TO_FIGHT)
            ORD_HAR_RISE_ABOVE -> listOf(ORD_HAR_PRIMAL_FEAR)
            ORD_HAR_REAP_THE_WHIRLWIND -> listOf(ORD_HAR_SOVEREIGN)
            ORD_HAR_SOVEREIGN -> listOf(ORD_HAR_FACE_OF_THE_MOUNTAIN)
            ORD_HAR_BORN_TO_FIGHT -> listOf(ORD_HAR_LEAD_THE_TEMPEST)
            ORD_HAR_HEAVY_ARMOR_FIT -> listOf(
                ORD_HAR_FACE_OF_DEATH,
                ORD_HAR_DEFIANCE,
                ORD_HAR_RALLYING_STANDARD
            )
            ORD_HAR_FACE_OF_DEATH -> listOf(ORD_HAR_LEAD_THE_TEMPEST)
            ORD_HAR_DEFIANCE -> listOf(
                ORD_HAR_REVEL_IN_BATTLE,
                ORD_HAR_BEDROCK,
                ORD_HAR_NEVER_KNEEL
            )
            ORD_HAR_BEDROCK -> listOf(ORD_HAR_LEAD_THE_TEMPEST)
            ORD_HAR_RALLYING_STANDARD -> listOf(ORD_HAR_NEVER_KNEEL, ORD_HAR_WARBRINGER)
            ORD_HAR_NEVER_KNEEL -> listOf(ORD_HAR_OUT_OF_THE_INFERNO)
            ORD_HAR_OUT_OF_THE_INFERNO -> listOf(ORD_HAR_IMMORTAL)
            ORD_HAR_WARBRINGER -> listOf(ORD_HAR_DOOMBRINGER, ORD_HAR_IMMORTAL)
            else -> emptyList()
        }
}
