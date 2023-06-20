package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Speech(x: Float, y: Float, vararg skill: Int) : IPerk {

    VAN_SPC_HAGGLING      (0.35f,   0.95f, 0, 20, 40, 60, 80),
    VAN_SPC_ALLURE        (0.375f,  0.7f,  30),
    VAN_SPC_MERCHANT      (0.3f,    0.5f,  50),
    VAN_SPC_INVESTOR      (0.25f,   0.35f, 70),
    VAN_SPC_FENCE         (0.2f,    0.2f,  90),
    VAN_SPC_MASTER_TRADER (0.55f,   0.15f, 100),
    VAN_SPC_BRIBERY       (0.6f,    0.7f,  30),
    VAN_SPC_PERSUASION    (0.7f,    0.5f,  50),
    VAN_SPC_INTIMIDATION  (0.75f,   0.35f, 70);

    override val perkInfo: PerkInfo= PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VAN_SPC_HAGGLING -> listOf(VAN_SPC_BRIBERY, VAN_SPC_ALLURE)
            VAN_SPC_ALLURE -> listOf(VAN_SPC_MERCHANT)
            VAN_SPC_MERCHANT -> listOf(VAN_SPC_INVESTOR)
            VAN_SPC_INVESTOR -> listOf(VAN_SPC_FENCE)
            VAN_SPC_FENCE -> listOf(VAN_SPC_MASTER_TRADER)
            VAN_SPC_BRIBERY -> listOf(VAN_SPC_PERSUASION)
            VAN_SPC_PERSUASION -> listOf(VAN_SPC_INTIMIDATION)
            else -> emptyList()
        }
}