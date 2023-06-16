package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ada_Lockpicking(x: Float, y: Float, vararg skill: Int) : IPerk {

    ADA_LCK_LOCKSMITH      (0.4f,   0.95f,  10, 50),
    ADA_LCK_GOLDEN_TOUCH   (0.5f,   0.725f, 20),
    ADA_LCK_QUICK_HANDS    (0.275f, 0.625f, 30),
    ADA_LCK_RARE_GEMS      (0.6f,   0.5f,   40),
    ADA_LCK_DEEP_POCKETS   (0.375f, 0.4f,   60, 90),
    ADA_LCK_DUNGEON_DELVER (0.675f, 0.3f,   70),
    ADA_LCK_TUMBLERBANE    (0.45f,  0.2f,   80),
    ADA_LCK_TREASURE_HUNTER(0.725f, 0.1f,   100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)
}