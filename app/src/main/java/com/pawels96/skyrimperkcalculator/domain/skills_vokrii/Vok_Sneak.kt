package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Sneak(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_SNK_SNEAK_MASTERY    (0.5f,   0.95f,  0),
    VOK_SNK_SNEAK_ATTACK     (0.475f, 0.65f,  20),
    VOK_SNK_SILENT_MOVEMENT  (0.725f, 0.65f,  30),
    VOK_SNK_SILENT_ROLL      (0.2f,   0.55f,  30, 60),
    VOK_SNK_DEADLY_AIM       (0.35f,  0.45f,  40),
    VOK_SNK_SHADOWCASTER     (0.75f,  0.8f,   40),
    VOK_SNK_ASSASSINS_BLADE  (0.55f,  0.4f,   50),
    VOK_SNK_BLIND_SPOT       (0.65f,  0.325f, 50),
    VOK_SNK_DODGE_ROLL       (0.35f,  0.2f,   60),
    VOK_SNK_LIGHT_FOOT       (0.85f,  0.35f,  60),
    VOK_SNK_BACKSTAB         (0.525f, 0.275f, 70),
    VOK_SNK_CLOAK_AND_DAGGER (0.6f,   0.175f, 80),
    VOK_SNK_FOG_OF_WAR       (0.775f, 0.25f,  90),
    VOK_SNK_SHADOW_WARRIOR   (0.55f,  0.075f, 90),
    VOK_SNK_ESCAPE_ARTIST    (0.75f,  0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}