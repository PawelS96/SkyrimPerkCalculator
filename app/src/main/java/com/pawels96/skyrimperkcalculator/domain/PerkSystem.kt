package com.pawels96.skyrimperkcalculator.domain

interface IPerkSystem

enum class PerkSystem : IPerkSystem {
    VANILLA, ADAMANT, ORDINATOR, VOKRII
}

enum class VampirePerkSystem : IPerkSystem {
    VANILLA, SACROSANCT
}

enum class WerewolfPerkSystem : IPerkSystem {
    VANILLA, GROWL
}