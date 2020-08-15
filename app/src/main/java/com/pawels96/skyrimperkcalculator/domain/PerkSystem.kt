package com.pawels96.skyrimperkcalculator.domain

enum class PerkSystem {
    ORDINATOR, VANILLA, VOKRII
}

interface SpecialSkillPerkSystem

enum class VampirePerkSystem  : SpecialSkillPerkSystem{
    VANILLA
}

enum class WerewolfPerkSystem : SpecialSkillPerkSystem{
    VANILLA
}


enum class SpecialSkill {
    VAMPIRE, WEREWOLF;
}
