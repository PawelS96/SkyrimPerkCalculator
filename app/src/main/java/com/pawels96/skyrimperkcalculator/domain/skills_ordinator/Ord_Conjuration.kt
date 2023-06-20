package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Conjuration(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_CON_CONJURATION_MASTERY               (0.5f,    0.95f,  0, 20),
    ORD_CON_BONE_COLLECTOR                    (0.5f,    0.8f,   20),
    ORD_CON_CONJURATION_DUAL_CASTING          (0.2f,    0.88f,  20),
    ORD_CON_MYSTIC_BINDING                    (0.65f,   0.75f,  20),
    ORD_CON_DEAD_TIDE                         (0.55f,   0.65f,  30, 60),
    ORD_CON_PLANEMELD                         (0.2f,    0.78f,  30),
    ORD_CON_RAVENOUS_DEAD                     (0.35f,   0.75f,  30),
    ORD_CON_SOUL_RAIDER                       (0.8f,    0.7f,   30),
    ORD_CON_ATROMANCY                         (0.15f,   0.63f,  40),
    ORD_CON_BARROW_LORD                       (0.7f,    0.45f,  40),
    ORD_CON_PRESERVATION                      (0.35f,   0.6f,   40),
    ORD_CON_RAT_KING                          (0.8f,    0.88f,  40),
    ORD_CON_REAP_AND_SOW                      (0.45f,   0.5f,   40, 70),
    ORD_CON_REND_FROM_THIS_WORLD              (0.8f,    0.6f,   40),
    ORD_CON_SIGNED_IN_BLOOD                   (0.1f,    0.7f,   40),
    ORD_CON_EDGE_OF_OBLIVION                  (0.22f,   0.55f,  50),
    ORD_CON_PACT_MAGIC                        (0.07f,   0.5f,   50),
    ORD_CON_SKELETON_MAGES                    (0.5f,    0.3f,   50, 80),
    ORD_CON_UNDEAD_CROWN                      (0.33f,   0.4f,   50),
    ORD_CON_VOID_BURN                         (0.9f,    0.5f,   50),
    ORD_CON_A_PLAGUE_UPON_THEE                (0.22f,   0.45f,  60),
    ORD_CON_CONJURE_ALTAR                     (0.6f,    0.2f,   60),
    ORD_CON_FIRE_RITUAL                       (0.45f,   0.175f, 60),
    ORD_CON_HOLLOW_BINDING                    (0.9f,    0.35f,  60),
    ORD_CON_MAELSTROM                         (0.06f,   0.4f,   60),
    ORD_CON_CORPSE_GAS                        (0.25f,   0.3f,   70),
    ORD_CON_DARK_WHISPERS                     (0.75f,   0.25f,  70),
    ORD_CON_ELEMENTAL_POTENCY                 (0.1f,    0.25f,  70),
    ORD_CON_BRAND_OF_THE_NECROMANCER          (0.75f,   0.15f,  80),
    ORD_CON_FEED_THE_MONSTER                  (0.8f,    0.78f,  80),
    ORD_CON_NECROMASTER                       (0.3f,    0.2f,   80),
    ORD_CON_UNLEASH_HELL                      (0.1f,    0.1f,   80),
    ORD_CON_COVENANT_OF_COLDHARBOUR           (0.85f,   0.05f,  90),
    ORD_CON_PUPPET_MASTER                     (0.6f,    0.1f,   90),
    ORD_CON_SHOCKED_TO_LIFE                   (0.4f,    0.1f,   90),
    ORD_CON_SUMMON_RESIST                     (0.2f,    0.15f,  90),
    ORD_CON_KING_OF_BONES                     (0.5f,    0.05f,  100),
    ORD_CON_MARCH_OF_OBLIVION                 (0.25f,   0.05f,  100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_CON_CONJURATION_MASTERY -> listOf(
                ORD_CON_CONJURATION_DUAL_CASTING,
                ORD_CON_PLANEMELD,
                ORD_CON_RAVENOUS_DEAD,
                ORD_CON_BONE_COLLECTOR,
                ORD_CON_MYSTIC_BINDING,
                ORD_CON_RAT_KING,
                ORD_CON_FEED_THE_MONSTER
            )
            ORD_CON_BONE_COLLECTOR -> listOf(ORD_CON_DEAD_TIDE)
            ORD_CON_MYSTIC_BINDING -> listOf(ORD_CON_SOUL_RAIDER)
            ORD_CON_DEAD_TIDE -> listOf(ORD_CON_REAP_AND_SOW, ORD_CON_BARROW_LORD)
            ORD_CON_PLANEMELD -> listOf(ORD_CON_SIGNED_IN_BLOOD, ORD_CON_ATROMANCY)
            ORD_CON_RAVENOUS_DEAD -> listOf(ORD_CON_EDGE_OF_OBLIVION, ORD_CON_PRESERVATION)
            ORD_CON_SOUL_RAIDER -> listOf(ORD_CON_REND_FROM_THIS_WORLD)
            ORD_CON_ATROMANCY -> listOf(
                ORD_CON_PACT_MAGIC,
                ORD_CON_EDGE_OF_OBLIVION,
                ORD_CON_ELEMENTAL_POTENCY
            )
            ORD_CON_BARROW_LORD -> listOf(ORD_CON_SKELETON_MAGES)
            ORD_CON_PRESERVATION -> listOf(ORD_CON_UNDEAD_CROWN, ORD_CON_A_PLAGUE_UPON_THEE)
            ORD_CON_REAP_AND_SOW -> listOf(ORD_CON_SKELETON_MAGES)
            ORD_CON_REND_FROM_THIS_WORLD -> listOf(ORD_CON_VOID_BURN)
            ORD_CON_PACT_MAGIC -> listOf(ORD_CON_MAELSTROM)
            ORD_CON_SKELETON_MAGES -> listOf(ORD_CON_FIRE_RITUAL, ORD_CON_CONJURE_ALTAR)
            ORD_CON_VOID_BURN -> listOf(ORD_CON_HOLLOW_BINDING)
            ORD_CON_A_PLAGUE_UPON_THEE -> listOf(ORD_CON_CORPSE_GAS)
            ORD_CON_CONJURE_ALTAR -> listOf(ORD_CON_PUPPET_MASTER)
            ORD_CON_FIRE_RITUAL -> listOf(ORD_CON_KING_OF_BONES)
            ORD_CON_HOLLOW_BINDING -> listOf(ORD_CON_DARK_WHISPERS, ORD_CON_COVENANT_OF_COLDHARBOUR)
            ORD_CON_CORPSE_GAS -> listOf(ORD_CON_NECROMASTER)
            ORD_CON_DARK_WHISPERS -> listOf(ORD_CON_BRAND_OF_THE_NECROMANCER)
            ORD_CON_ELEMENTAL_POTENCY -> listOf(ORD_CON_SUMMON_RESIST, ORD_CON_UNLEASH_HELL)
            ORD_CON_NECROMASTER -> listOf(ORD_CON_MARCH_OF_OBLIVION, ORD_CON_SHOCKED_TO_LIFE)
            ORD_CON_SUMMON_RESIST -> listOf(ORD_CON_MARCH_OF_OBLIVION)
            else -> emptyList()
        }
}