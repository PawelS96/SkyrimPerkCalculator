package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Conjuration(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_CON_NOVICE_CONJURATION             (0.5f,   0.95f, 0),
    VAN_CON_APPRENTICE_CONJURATION         (0.85f,  0.65f, 25),
    VAN_CON_ADEPT_CONJURATION              (0.9f,   0.45f, 50),
    VAN_CON_EXPERT_CONJURATION             (0.85f,  0.25f, 75),
    VAN_CON_MASTER_CONJURATION             (0.75f,  0.1f,  100),
    VAN_CON_CONJURATION_DUAL_CASTING       (0.375f, 0.75f, 20),
    VAN_CON_MYSTIC_BINDING                 (0.55f,  0.7f,  20),
    VAN_CON_SOUL_STEALER                   (0.6f,   0.35f, 30),
    VAN_CON_OBLIVION_BINDING               (0.55f,  0.2f,  50),
    VAN_CON_NECROMANCY                     (0.3f,   0.4f,  40),
    VAN_CON_DARK_SOULS                     (0.3f,   0.25f, 70),
    VAN_CON_SUMMONER                       (0.15f,  0.65f, 30, 70),
    VAN_CON_ATROMANCY                      (0.1f,   0.4f,  40),
    VAN_CON_ELEMENTAL_POTENCY              (0.125f, 0.2f,  80),
    VAN_CON_TWIN_SOULS                     (0.35f,  0.1f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_CON_NOVICE_CONJURATION -> listOf(
                VAN_CON_NECROMANCY,
                VAN_CON_MYSTIC_BINDING,
                VAN_CON_APPRENTICE_CONJURATION,
                VAN_CON_SUMMONER,
                VAN_CON_CONJURATION_DUAL_CASTING
            )
            VAN_CON_APPRENTICE_CONJURATION -> listOf(VAN_CON_ADEPT_CONJURATION)
            VAN_CON_ADEPT_CONJURATION -> listOf(VAN_CON_EXPERT_CONJURATION)
            VAN_CON_EXPERT_CONJURATION -> listOf(VAN_CON_MASTER_CONJURATION)
            VAN_CON_MYSTIC_BINDING -> listOf(VAN_CON_SOUL_STEALER)
            VAN_CON_SOUL_STEALER -> listOf(VAN_CON_OBLIVION_BINDING)
            VAN_CON_NECROMANCY -> listOf(VAN_CON_DARK_SOULS)
            VAN_CON_DARK_SOULS -> listOf(VAN_CON_TWIN_SOULS)
            VAN_CON_SUMMONER -> listOf(VAN_CON_ATROMANCY)
            VAN_CON_ATROMANCY -> listOf(VAN_CON_ELEMENTAL_POTENCY)
            VAN_CON_ELEMENTAL_POTENCY -> listOf(VAN_CON_TWIN_SOULS)
            else -> emptyList()
        }
}