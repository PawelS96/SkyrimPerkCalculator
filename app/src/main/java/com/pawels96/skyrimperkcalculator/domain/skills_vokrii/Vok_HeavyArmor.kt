package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_HeavyArmor(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_HAR_HEAVY_ARMOR_MASTERY  (0.5f,   0.95f,  0),
    VOK_HAR_CUSHIONED            (0.4f,   0.6f,   20, 50),
    VOK_HAR_BATTLE_FATIGUE       (0.2f,   0.7f,   30),
    VOK_HAR_HEAVY_ARMOR_FIT      (0.725f, 0.7f,   30),
    VOK_HAR_FACE_OF_DEATH        (0.9f,   0.6f,   40),
    VOK_HAR_IMMOVABLE_OBJECT     (0.1f,   0.5f,   40),
    VOK_HAR_HEAVY_ARMOR_TRAINING (0.75f,  0.45f,  50),
    VOK_HAR_REAP_THE_WHIRLWIND   (0.2f,   0.3f,   60),
    VOK_HAR_TOWER_OF_STRENGTH    (0.625f, 0.25f,  60),
    VOK_HAR_MATCHING_HEAVY_SET   (0.8f,   0.3f,   70),
    VOK_HAR_ELEMENTAL_DEFENSE    (0.65f,  0.15f,  80),
    VOK_HAR_GLANCING_BLOWS       (0.75f,  0.075f, 90),
    VOK_HAR_FACE_OF_THE_MOUNTAIN (0.225f, 0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}