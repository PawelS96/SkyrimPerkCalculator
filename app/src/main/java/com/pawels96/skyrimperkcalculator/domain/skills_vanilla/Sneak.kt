package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Sneak(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_SNK_STEALTH                    (0.5f,    0.95f,   0, 20, 40, 60, 80),
    VAN_SNK_BACKSTAB                   (0.75f,   0.75f,   30),
    VAN_SNK_DEADLY_AIM                 (0.75f,   0.55f,   40),
    VAN_SNK_ASSASSINS_BLADE            (0.6f,    0.45f,   50),
    VAN_SNK_MUFFLED_MOVEMENT           (0.25f,   0.75f,   30),
    VAN_SNK_LIGHT_FOOT                 (0.3f,    0.5f,    40),
    VAN_SNK_SILENT_ROLL                (0.4f,    0.4f,    50),
    VAN_SNK_SILENCE                    (0.55f,   0.3f,    70),
    VAN_SNK_SHADOW_WARRIOR             (0.75f,   0.2f,    100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_SNK_STEALTH -> listOf(VAN_SNK_MUFFLED_MOVEMENT, VAN_SNK_BACKSTAB)
            VAN_SNK_BACKSTAB -> listOf(VAN_SNK_DEADLY_AIM)
            VAN_SNK_DEADLY_AIM -> listOf(VAN_SNK_ASSASSINS_BLADE)
            VAN_SNK_MUFFLED_MOVEMENT -> listOf(VAN_SNK_LIGHT_FOOT)
            VAN_SNK_LIGHT_FOOT -> listOf(VAN_SNK_SILENT_ROLL)
            VAN_SNK_SILENT_ROLL -> listOf(VAN_SNK_SILENCE)
            VAN_SNK_SILENCE -> listOf(VAN_SNK_SHADOW_WARRIOR)
            else -> emptyList()
        }
}