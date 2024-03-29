package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Enchanting(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ENC_ENCHANTER                    (0.4f,   0.95f, 0, 20, 40, 60, 80),
    VAN_ENC_FIRE_ENCHANTER               (0.2f,   0.65f, 30),
    VAN_ENC_FROST_ENCHANTER              (0.225f, 0.4f,  40),
    VAN_ENC_STORM_ENCHANTER              (0.3f,   0.25f, 50),
    VAN_ENC_INSIGHTFUL_ENCHANTER         (0.5f,   0.6f,  50),
    VAN_ENC_CORPUS_ENCHANTER             (0.6f,   0.4f,  70),
    VAN_ENC_EXTRA_EFFECT                 (0.55f,  0.1f,  100),
    VAN_ENC_SOUL_SQUEEZER                (0.8f,   0.65f, 20),
    VAN_ENC_SOUL_SIPHON                  (0.7f,   0.2f,  40);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ENC_ENCHANTER -> listOf(
                VAN_ENC_SOUL_SQUEEZER,
                VAN_ENC_INSIGHTFUL_ENCHANTER,
                VAN_ENC_FIRE_ENCHANTER
            )
            VAN_ENC_FIRE_ENCHANTER -> listOf(VAN_ENC_FROST_ENCHANTER)
            VAN_ENC_FROST_ENCHANTER -> listOf(VAN_ENC_STORM_ENCHANTER)
            VAN_ENC_STORM_ENCHANTER -> listOf(VAN_ENC_EXTRA_EFFECT)
            VAN_ENC_INSIGHTFUL_ENCHANTER -> listOf(VAN_ENC_CORPUS_ENCHANTER)
            VAN_ENC_CORPUS_ENCHANTER -> listOf(VAN_ENC_EXTRA_EFFECT)
            VAN_ENC_SOUL_SQUEEZER -> listOf(VAN_ENC_SOUL_SIPHON)
            else -> emptyList()
        }
}