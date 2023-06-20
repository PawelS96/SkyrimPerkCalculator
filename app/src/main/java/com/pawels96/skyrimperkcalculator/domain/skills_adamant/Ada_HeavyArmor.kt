package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_HeavyArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_HAR_DEFENDER      (0.5f,  0.95f,  10, 50),
    ADA_HAR_JUGGERNAUT    (0.75f, 0.725f, 30),
    ADA_HAR_UNSTOPPABLE   (0.85f, 0.55f,  40),
    ADA_HAR_PERFECT_FIT   (0.9f,  0.3f,   70),
    ADA_HAR_DEFIANCE      (0.8f,  0.05f,  80),
    ADA_HAR_CONDITIONING  (0.25f, 0.725f, 20, 60),
    ADA_HAR_IMMOVABLE     (0.15f, 0.55f,  40),
    ADA_HAR_CONSTITUTION  (0.1f,  0.3f,   60, 90),
    ADA_HAR_INVINCIBLE    (0.2f,  0.05f,  100),
    ADA_HAR_FISTS_OF_STEEL(0.5f,  0.725f, 20, 60),
    ADA_HAR_FISTS_OF_WAR  (0.35f, 0.625f, 30, 70),
    ADA_HAR_FISTS_OF_FURY (0.65f, 0.625f, 40, 90),
    ADA_HAR_BRACE         (0.5f,  0.5f,   70),
    ADA_HAR_OVERWHELM     (0.25f, 0.4f,   80),
    ADA_HAR_KNOCKOUT_PUNCH(0.75f, 0.4f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ADA_HAR_DEFENDER -> listOf(
                ADA_HAR_CONDITIONING,
                ADA_HAR_FISTS_OF_STEEL,
                ADA_HAR_JUGGERNAUT
            )
            ADA_HAR_CONDITIONING -> listOf(ADA_HAR_IMMOVABLE)
            ADA_HAR_IMMOVABLE -> listOf(ADA_HAR_CONSTITUTION)
            ADA_HAR_CONSTITUTION -> listOf(ADA_HAR_INVINCIBLE)
            ADA_HAR_FISTS_OF_STEEL -> listOf(
                ADA_HAR_FISTS_OF_WAR,
                ADA_HAR_BRACE,
                ADA_HAR_FISTS_OF_FURY
            )
            ADA_HAR_FISTS_OF_WAR -> listOf(ADA_HAR_OVERWHELM)
            ADA_HAR_FISTS_OF_FURY -> listOf(ADA_HAR_KNOCKOUT_PUNCH)
            ADA_HAR_JUGGERNAUT -> listOf(ADA_HAR_UNSTOPPABLE)
            ADA_HAR_UNSTOPPABLE -> listOf(ADA_HAR_PERFECT_FIT)
            ADA_HAR_PERFECT_FIT -> listOf(ADA_HAR_DEFIANCE)
            else -> emptyList()
        }
}