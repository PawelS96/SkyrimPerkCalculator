package com.pawels96.skyrimperkcalculator.domain.vampirism

import com.pawels96.skyrimperkcalculator.domain.PerkInfo
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk

enum class Vampirism (val x: Float, val y: Float): SpecialSkillPerk {

    VAN_VAM_POWER_OF_THE_GRAVE(0.5f, 0.95f),
    VAN_VAM_DETECT_ALL_CREATURES(0.25f, 0.75f),
    VAN_VAM_MIST_FORM(0.2f, 0.4f),
    VAN_VAM_SUPERNATURAL_REFLEXES(0.1f, 0.3f),
    VAN_VAM_BLOOD_HEALING(0.35f, 0.45f),
    VAN_VAM_UNEARTHLY_WILL(0.65f, 0.45f),
    VAN_VAM_POISON_TALONS(0.5f, 0.25f),
    VAN_VAM_NIGHT_CLOAK(0.5f, 0.1f),
    VAN_VAM_VAMPIRIC_GRIP(0.75f, 0.75f),
    VAN_VAM_SUMMON_GARGOYLE(0.8f, 0.4f),
    VAN_VAM_CORPSE_CURSE(0.9f, 0.3f);

    override val perkInfo: PerkInfo get() =  PerkInfo(IntArray(1), x, y)

}
