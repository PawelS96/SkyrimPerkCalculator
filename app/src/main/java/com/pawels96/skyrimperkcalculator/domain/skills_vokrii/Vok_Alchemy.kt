package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Alchemy(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_ALC_ALCHEMY_MASTERY          (0.25f,  0.95f,  0),
    VOK_ALC_PHYSICIAN                (0.75f,  0.85f,  20),
    VOK_ALC_BENEFACTOR               (0.35f,  0.7f,   30),
    VOK_ALC_POISONER                 (0.7f,   0.7f,   30),
    VOK_ALC_CONCENTRATED_POISON      (0.625f, 0.45f,  40, 70, 100),
    VOK_ALC_EXPERIMENTER             (0.4f,   0.5f,   50),
    VOK_ALC_STIMULANTS               (0.25f,  0.475f, 50),
    VOK_ALC_GREEN_THUMB              (0.4f,   0.35f,  60),
    VOK_ALC_SLOW_METABOLISM          (0.525f, 0.6f,   60, 90),
    VOK_ALC_ALKAHEST                 (0.625f, 0.3f,   70),
    VOK_ALC_PURITY                   (0.5f,   0.25f,  70),
    VOK_ALC_ADRENALINE               (0.3f,   0.3f,   80),
    VOK_ALC_PLAGUE_DOCTOR            (0.55f,  0.15f,  80),
    VOK_ALC_GOURMET                  (0.65f,  0.1f,   90),
    VOK_ALC_DOUBLE_TOIL_AND_TROUBLE  (0.45f,  0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_ALC_ALCHEMY_MASTERY -> listOf(
                VOK_ALC_BENEFACTOR,
                VOK_ALC_POISONER,
                VOK_ALC_PHYSICIAN
            )
            VOK_ALC_BENEFACTOR -> listOf(
                VOK_ALC_SLOW_METABOLISM,
                VOK_ALC_EXPERIMENTER,
                VOK_ALC_STIMULANTS
            )
            VOK_ALC_POISONER -> listOf(VOK_ALC_CONCENTRATED_POISON)
            VOK_ALC_EXPERIMENTER -> listOf(VOK_ALC_GREEN_THUMB)
            VOK_ALC_STIMULANTS -> listOf(VOK_ALC_ADRENALINE)
            VOK_ALC_GREEN_THUMB -> listOf(VOK_ALC_PURITY, VOK_ALC_DOUBLE_TOIL_AND_TROUBLE)
            VOK_ALC_CONCENTRATED_POISON -> listOf(VOK_ALC_PURITY, VOK_ALC_ALKAHEST)
            VOK_ALC_ALKAHEST -> listOf(VOK_ALC_PLAGUE_DOCTOR, VOK_ALC_GOURMET)
            else -> emptyList()
        }
}