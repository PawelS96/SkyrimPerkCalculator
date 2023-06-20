package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Smithing(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_SMT_SMITHING_MASTERY           (0.5f,    0.95f,    0, 20),
    ORD_SMT_DWARVEN_AUTOCANNON         (0.4f,    0.78f,    20, 50),
    ORD_SMT_ADVANCED_WORKSHOP          (0.75f,   0.85f,    30),
    ORD_SMT_MERIC_SMITHING             (0.3f,    0.9f,     30, 40),
    ORD_SMT_ARCANE_BLACKSMITH          (0.6f,    0.6f,     40),
    ORD_SMT_ELECTROBOLT                (0.35f,   0.5f,     40, 70),
    ORD_SMT_RECYCLE_MATERIALS          (0.72f,   0.5f,     40),
    ORD_SMT_REMOTE_CONTROL             (0.3f,    0.675f,   50),
    ORD_SMT_EXPERT_SMITHING            (0.15f,   0.6f,     50, 60),
    ORD_SMT_SANDSTONE_SHEATH           (0.65f,   0.35f,    60),
    ORD_SMT_SMITHING_SPECIALIZATION    (0.85f,   0.3f,     60),
    ORD_SMT_EXOTIC_SMITHING            (0.1f,    0.3f,     70, 80),
    ORD_SMT_FIRING_LINE                (0.25f,   0.4f,     70),
    ORD_SMT_IRON_LORE                  (0.9f,    0.2f,     80),
    ORD_SMT_SPIN_UP                    (0.5f,    0.3f,     80),
    ORD_SMT_PLANAR_SMITHING            (0.3f,    0.2f,     90, 100),
    ORD_SMT_FUEL_THE_INFERNO           (0.7f,    0.2f,     90),
    ORD_SMT_HEART_OF_CREATION          (0.55f,   0.1f,     100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_SMT_SMITHING_MASTERY -> listOf(
                ORD_SMT_MERIC_SMITHING,
                ORD_SMT_DWARVEN_AUTOCANNON,
                ORD_SMT_ARCANE_BLACKSMITH,
                ORD_SMT_ADVANCED_WORKSHOP
            )
            ORD_SMT_DWARVEN_AUTOCANNON -> listOf(ORD_SMT_REMOTE_CONTROL, ORD_SMT_ELECTROBOLT)
            ORD_SMT_ADVANCED_WORKSHOP -> listOf(
                ORD_SMT_RECYCLE_MATERIALS,
                ORD_SMT_SMITHING_SPECIALIZATION
            )
            ORD_SMT_MERIC_SMITHING -> listOf(ORD_SMT_EXPERT_SMITHING)
            ORD_SMT_ELECTROBOLT -> listOf(ORD_SMT_FIRING_LINE, ORD_SMT_SPIN_UP)
            ORD_SMT_RECYCLE_MATERIALS -> listOf(ORD_SMT_SANDSTONE_SHEATH, ORD_SMT_FUEL_THE_INFERNO)
            ORD_SMT_EXPERT_SMITHING -> listOf(ORD_SMT_EXOTIC_SMITHING)
            ORD_SMT_SMITHING_SPECIALIZATION -> listOf(ORD_SMT_IRON_LORE, ORD_SMT_FUEL_THE_INFERNO)
            ORD_SMT_EXOTIC_SMITHING -> listOf(ORD_SMT_PLANAR_SMITHING)
            ORD_SMT_PLANAR_SMITHING -> listOf(ORD_SMT_HEART_OF_CREATION)
            ORD_SMT_FUEL_THE_INFERNO -> listOf(ORD_SMT_HEART_OF_CREATION)
            else -> emptyList()
        }
}
