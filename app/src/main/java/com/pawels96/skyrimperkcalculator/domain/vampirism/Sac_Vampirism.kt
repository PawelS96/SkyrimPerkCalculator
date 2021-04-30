package com.pawels96.skyrimperkcalculator.domain.vampirism

import com.pawels96.skyrimperkcalculator.domain.PerkInfo
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk

enum class Sac_Vampirism(val x: Float, val y: Float) : SpecialSkillPerk {

    SAC_VAM_POWER_IS_POWER(0.5f, 0.95f),
    SAC_VAM_WINGS_OF_THE_STRIX(0.5f, 0.7f),
    SAC_VAM_ASTRAL_POISON(0.5f, 0.5f),
    SAC_VAM_NIGHT_CLOAK(0.5f, 0.25f),
    SAC_VAM_DRAGON_AT_MIDNIGHT(0.5f, 0.15f),
    SAC_VAM_ECHOLOCATION(0.15f, 0.9f),
    SAC_VAM_MIST_FORM(0.1f, 0.775f),
    SAC_VAM_THE_REAPING(0.075f, 0.65f),
    SAC_VAM_TREMBLE(0.075f, 0.55f),
    SAC_VAM_COURTS_CHEF(0.25f, 0.725f),
    SAC_VAM_WHITE_WOLF(0.2f, 0.5f),
    SAC_VAM_PSYCHIC_VAMPIRE(0.2f, 0.4f),
    SAC_VAM_EMBRACE_THE_BEAST(0.075f, 0.3f),
    SAC_VAM_FOSTER_CHILDE(0.175f, 0.2f),
    SAC_VAM_AMARANTH(0.25f, 0.1f),
    SAC_VAM_FOUNTAIN_OF_YOUTH(0.4f, 0.8f),
    SAC_VAM_LION_AMONG_SHEEP(0.35f, 0.625f),
    SAC_VAM_SLASHER(0.325f, 0.325f),
    SAC_VAM_CELERITY(0.4f, 0.05f),
    SAC_VAM_UNEARTHLY_WILL(0.6f, 0.8f),
    SAC_VAM_BLOOD_FROM_A_STONE(0.65f, 0.625f),
    SAC_VAM_LORD_OF_DESTRUCTION(0.675f, 0.325f),
    SAC_VAM_BLOOD_STORM(0.6f, 0.05f),
    SAC_VAM_EXSANGUINATE(0.75f, 0.725f),
    SAC_VAM_STARVING_ARTIST(0.8f, 0.5f),
    SAC_VAM_MAKE_THEM_BEAUTIFUL(0.8f, 0.4f),
    SAC_VAM_AUSPEX(0.925f, 0.3f),
    SAC_VAM_CURTAIN_CALL(0.825f, 0.2f),
    SAC_VAM_LAMAES_PYRE(0.75f, 0.1f),
    SAC_VAM_CHOKEHOLD(0.85f, 0.9f),
    SAC_VAM_CONJURE_GARGOYLE(0.9f, 0.775f),
    SAC_VAM_GUTWRENCH(0.925f, 0.65f),
    SAC_VAM_MAELSTROM(0.925f, 0.55f);


    override val perkInfo: PerkInfo get() =  PerkInfo(IntArray(1), x, y)

}