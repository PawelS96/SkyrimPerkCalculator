package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_TwoHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_TWH_TWO_HANDED_MASTERY  (0.5f,   0.95f,  0),
    VOK_TWH_BRUTAL_FIGHTER      (0.8f,   0.8f,   20),
    VOK_TWH_OVERBEARING_ASSAULT (0.3f,   0.55f,  30, 60),
    VOK_TWH_MORTAL_WOUNDS       (0.45f,  0.5f,   30, 60),
    VOK_TWH_CRUSHING_BLOWS      (0.65f,  0.55f,  30, 60),
    VOK_TWH_DEATH_OR_GLORY      (0.1f,   0.75f,  40),
    VOK_TWH_FEROCIOUS_STRENGTH  (0.9f,   0.5f,   40),
    VOK_TWH_VICIOUS_CHARGE      (0.75f,  0.35f,  50),
    VOK_TWH_BERSERKER           (0.15f,  0.45f,  60),
    VOK_TWH_WARMASTER           (0.875f, 0.3f,   60),
    VOK_TWH_COUP_DE_GRACE       (0.325f, 0.225f, 70),
    VOK_TWH_HOOK_BLADE          (0.475f, 0.25f,  70),
    VOK_TWH_SHATTERING_STRIKE   (0.6f,   0.2f,   70),
    VOK_TWH_BEAR_HIDE           (0.15f,  0.2f,   80),
    VOK_TWH_ROLLING_CHARGE      (0.725f, 0.25f,  80),
    VOK_TWH_SWEEP               (0.75f,  0.125f, 90),
    VOK_TWH_CROWD_PLEASER       (0.45f,  0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_TWH_TWO_HANDED_MASTERY -> listOf(
                VOK_TWH_DEATH_OR_GLORY,
                VOK_TWH_OVERBEARING_ASSAULT,
                VOK_TWH_MORTAL_WOUNDS,
                VOK_TWH_CRUSHING_BLOWS,
                VOK_TWH_BRUTAL_FIGHTER
            )
            VOK_TWH_DEATH_OR_GLORY -> listOf(VOK_TWH_BERSERKER)
            VOK_TWH_BERSERKER -> listOf(VOK_TWH_BEAR_HIDE)
            VOK_TWH_OVERBEARING_ASSAULT -> listOf(VOK_TWH_COUP_DE_GRACE)
            VOK_TWH_MORTAL_WOUNDS -> listOf(VOK_TWH_HOOK_BLADE)
            VOK_TWH_HOOK_BLADE -> listOf(VOK_TWH_CROWD_PLEASER)
            VOK_TWH_CRUSHING_BLOWS -> listOf(VOK_TWH_SHATTERING_STRIKE)
            VOK_TWH_SHATTERING_STRIKE -> listOf(VOK_TWH_CROWD_PLEASER)
            VOK_TWH_BRUTAL_FIGHTER -> listOf(VOK_TWH_FEROCIOUS_STRENGTH)
            VOK_TWH_FEROCIOUS_STRENGTH -> listOf(VOK_TWH_VICIOUS_CHARGE, VOK_TWH_WARMASTER)
            VOK_TWH_WARMASTER -> listOf(VOK_TWH_SWEEP)
            VOK_TWH_VICIOUS_CHARGE -> listOf(VOK_TWH_ROLLING_CHARGE)
            VOK_TWH_COUP_DE_GRACE -> listOf(VOK_TWH_CROWD_PLEASER)
            else -> emptyList()
        }
}