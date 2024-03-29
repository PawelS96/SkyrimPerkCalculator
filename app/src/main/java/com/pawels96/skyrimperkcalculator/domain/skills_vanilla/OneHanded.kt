package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class OneHanded(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_ONH_ARMSMAN              (0.5f,     0.95f,    0, 20, 40, 60, 80),
    VAN_ONH_BLADESMAN            (0.75f,    0.7f,     30, 60, 90),
    VAN_ONH_BONE_BREAKER         (0.65f,    0.6f,     30, 60, 90),
    VAN_ONH_DUAL_FLURRY          (0.9f,     0.8f,     30, 50),
    VAN_ONH_DUAL_SAVAGERY        (0.75f,    0.25f,    70),
    VAN_ONH_FIGHTING_STANCE      (0.5f,     0.75f,    20) ,
    VAN_ONH_CRITICAL_CHARGE      (0.6f,     0.4f,     50) ,
    VAN_ONH_SAVAGE_STRIKE        (0.4f,     0.4f,     50) ,
    VAN_ONH_PARALYZING_STRIKE    (0.5f,     0.1f,     100) ,
    VAN_ONH_HACK_AND_SLASH       (0.3f,     0.6f,     30, 60, 90);

    override val perkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_ONH_ARMSMAN -> listOf(
                VAN_ONH_DUAL_FLURRY,
                VAN_ONH_BLADESMAN,
                VAN_ONH_BONE_BREAKER,
                VAN_ONH_FIGHTING_STANCE,
                VAN_ONH_HACK_AND_SLASH
            )
            VAN_ONH_DUAL_FLURRY -> listOf(VAN_ONH_DUAL_SAVAGERY)
            VAN_ONH_FIGHTING_STANCE -> listOf(VAN_ONH_CRITICAL_CHARGE, VAN_ONH_SAVAGE_STRIKE)
            VAN_ONH_CRITICAL_CHARGE -> listOf(VAN_ONH_PARALYZING_STRIKE)
            VAN_ONH_SAVAGE_STRIKE -> listOf(VAN_ONH_PARALYZING_STRIKE)
            else -> emptyList()
        }
}