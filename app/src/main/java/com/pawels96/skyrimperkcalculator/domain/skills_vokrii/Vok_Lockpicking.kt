package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Vok_Lockpicking(x: Float, y: Float, vararg skill: Int) : IPerk {

    VOK_LCK_LOCKPICKING_MASTERY (0.45f,  0.95f,  0),
    VOK_LCK_LOOTER              (0.35f,  0.7f,   20),
    VOK_LCK_QUICK_HANDS         (0.7f,   0.65f,  30),
    VOK_LCK_LOCKDOWN            (0.5f,   0.55f,  40),
    VOK_LCK_WAX_KEY             (0.75f,  0.4f,   50),
    VOK_LCK_HOTWIRE             (0.475f, 0.3f,   60),
    VOK_LCK_TREASURE_HUNTER     (0.325f, 0.4f,   60),
    VOK_LCK_DUNGEON_MASTER      (0.6f,   0.345f, 70),
    VOK_LCK_LUCKY_GUESS         (0.775f, 0.3f,   80),
    VOK_LCK_ARCHAEOLOGIST       (0.2f,   0.325f, 90),
    VOK_LCK_OVERDRIVE           (0.35f,  0.2f,   90),
    VOK_LCK_LOCKMASTER          (0.8f,   0.15f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            VOK_LCK_LOCKPICKING_MASTERY -> listOf(
                VOK_LCK_LOOTER,
                VOK_LCK_LOCKDOWN,
                VOK_LCK_QUICK_HANDS
            )
            VOK_LCK_LOOTER -> listOf(VOK_LCK_TREASURE_HUNTER)
            VOK_LCK_LOCKDOWN -> listOf(VOK_LCK_HOTWIRE)
            VOK_LCK_QUICK_HANDS -> listOf(VOK_LCK_DUNGEON_MASTER, VOK_LCK_WAX_KEY)
            VOK_LCK_TREASURE_HUNTER -> listOf(VOK_LCK_ARCHAEOLOGIST)
            VOK_LCK_HOTWIRE -> listOf(VOK_LCK_OVERDRIVE)
            VOK_LCK_WAX_KEY -> listOf(VOK_LCK_LUCKY_GUESS)
            VOK_LCK_LUCKY_GUESS -> listOf(VOK_LCK_LOCKMASTER)
            else -> emptyList()
        }
}