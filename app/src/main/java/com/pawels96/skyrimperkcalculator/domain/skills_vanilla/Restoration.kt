package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Restoration(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_RST_NOVICE_RESTORATION            (0.5f,  0.95f, 0),
    VAN_RST_APPRENTICE_RESTORATION        (0.55f, 0.6f,  25),
    VAN_RST_ADEPT_RESTORATION             (0.5f,  0.4f,  50),
    VAN_RST_EXPERT_RESTORATION            (0.55f, 0.2f,  75),
    VAN_RST_MASTER_RESTORATION            (0.4f,  0.1f,  100),
    VAN_RST_RECOVERY                      (0.8f,  0.6f,  30, 60),
    VAN_RST_AVOID_DEATH                   (0.9f,  0.4f,  90),
    VAN_RST_REGENERATION                  (0.3f,  0.65f, 20),
    VAN_RST_NECROMAGE                     (0.15f, 0.3f,  70),
    VAN_RST_RESPITE                       (0.1f,  0.7f,  40),
    VAN_RST_RESTORATION_DUAL_CASTING      (0.75f, 0.8f,  20),
    VAN_RST_WARD_ABSORB                   (0.3f,  0.35f, 60);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}