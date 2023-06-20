package com.pawels96.skyrimperkcalculator.domain.skills_ordinator

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkInfo

enum class Ord_Destruction(x: Float, y: Float, vararg skill: Int) : IPerk {

    ORD_DES_DESTRUCTION_MASTERY               (0.5f,    0.95f,  0, 20),
    ORD_DES_COMBUSTION                        (0.35f,   0.75f,  20, 50),
    ORD_DES_DESTRUCTION_DUAL_CASTING          (0.2f,    0.925f, 20),
    ORD_DES_IONIZED_PATH                      (0.625f,  0.75f,  20, 50),
    ORD_DES_MERCILESS_COLD                    (0.5f,    0.675f, 20, 50),
    ORD_DES_FORCE_OF_NATURE                   (0.3f,    0.85f,  30),
    ORD_DES_FROSTFALL                         (0.5f,    0.55f,  30),
    ORD_DES_SCARRING_BURNS                    (0.27f,   0.65f,  30),
    ORD_DES_STATIC_FIELD                      (0.75f,   0.63f,  30),
    ORD_DES_ARC_BURN                          (0.83f,   0.54f,  40),
    ORD_DES_CONFLAGRATION                     (0.35f,   0.53f,  40),
    ORD_DES_CRYSTALIZE                        (0.6f,    0.4f,   40),
    ORD_DES_HARSH_LESSON                      (0.8f,    0.925f, 40),
    ORD_DES_RUNECASTER                        (0.9f,    0.75f,  40),
    ORD_DES_FLASH_FIRE                        (0.1f,    0.53f,  50),
    ORD_DES_MAGNETIZE                         (0.65f,   0.55f,  50),
    ORD_DES_ROBE_OF_THE_MAGI                  (0.15f,   0.8f,   50),
    ORD_DES_SHATTER                           (0.4f,    0.4f,   50),
    ORD_DES_ICED_EARTH                        (0.5f,    0.35f,  60),
    ORD_DES_NOVA_CHARGE                       (0.75f,   0.45f,  60),
    ORD_DES_PYROMANCER_ASCENSION              (0.2f,    0.45f,  60),
    ORD_DES_ANCIENT_SEALS                     (0.9f,    0.4f,   70),
    ORD_DES_ELECTROCONVULSIONS                (0.75f,   0.35f,  70),
    ORD_DES_HYPOTHERMIA                       (0.5f,    0.25f,  70),
    ORD_DES_SCORCHED_EARTH                    (0.2f,    0.35f,  70),
    ORD_DES_WAR_OF_THE_ELEMENTS               (0.85f,   0.85f,  70),
    ORD_DES_ELEMENTAL_SPECIALIZATION          (0.15f,   0.7f,   80),
    ORD_DES_EXHAUST                           (0.55f,   0.16f,  80),
    ORD_DES_SHOW_THEM_ALL                     (0.85f,   0.25f,  80),
    ORD_DES_WORLD_IN_FLAMES                   (0.1f,    0.25f,  80),
    ORD_DES_OUTBURST                          (0.27f,   0.22f,  90),
    ORD_DES_STORMBLAST                        (0.7f,    0.2f,   90),
    ORD_DES_WINTERS_MAJESTY                   (0.4f,    0.12f,  90),
    ORD_DES_RAW_POWER                         (0.775f,  0.7f,   30, 60, 90),
    ORD_DES_ABSOLUTE_POWER                    (0.75f,   0.1f,   100),
    ORD_DES_CATACLYSM                         (0.2f,    0.1f,   100),
    ORD_DES_GLACIAL_PRISON                    (0.5f,    0.055f, 100);

    override val perkInfo: PerkInfo = PerkInfo(skill, x, y)

    override val childPerks: List<IPerk>
        get() = when (this) {
            ORD_DES_DESTRUCTION_MASTERY -> listOf(
                ORD_DES_DESTRUCTION_DUAL_CASTING,
                ORD_DES_FORCE_OF_NATURE,
                ORD_DES_COMBUSTION,
                ORD_DES_MERCILESS_COLD,
                ORD_DES_IONIZED_PATH,
                ORD_DES_RUNECASTER,
                ORD_DES_WAR_OF_THE_ELEMENTS,
                ORD_DES_HARSH_LESSON,
                ORD_DES_RAW_POWER
            )
            ORD_DES_COMBUSTION -> listOf(ORD_DES_SCARRING_BURNS)
            ORD_DES_IONIZED_PATH -> listOf(ORD_DES_STATIC_FIELD)
            ORD_DES_MERCILESS_COLD -> listOf(ORD_DES_FROSTFALL)
            ORD_DES_FORCE_OF_NATURE -> listOf(
                ORD_DES_ELEMENTAL_SPECIALIZATION,
                ORD_DES_ROBE_OF_THE_MAGI
            )
            ORD_DES_FROSTFALL -> listOf(ORD_DES_SHATTER, ORD_DES_ICED_EARTH, ORD_DES_CRYSTALIZE)
            ORD_DES_SCARRING_BURNS -> listOf(
                ORD_DES_FLASH_FIRE,
                ORD_DES_PYROMANCER_ASCENSION,
                ORD_DES_CONFLAGRATION
            )
            ORD_DES_STATIC_FIELD -> listOf(ORD_DES_MAGNETIZE, ORD_DES_NOVA_CHARGE, ORD_DES_ARC_BURN)
            ORD_DES_ARC_BURN -> listOf(ORD_DES_ELECTROCONVULSIONS)
            ORD_DES_CONFLAGRATION -> listOf(ORD_DES_SCORCHED_EARTH)
            ORD_DES_CRYSTALIZE -> listOf(ORD_DES_HYPOTHERMIA)
            ORD_DES_RUNECASTER -> listOf(ORD_DES_ANCIENT_SEALS)
            ORD_DES_FLASH_FIRE -> listOf(ORD_DES_SCORCHED_EARTH)
            ORD_DES_MAGNETIZE -> listOf(ORD_DES_ELECTROCONVULSIONS)
            ORD_DES_SHATTER -> listOf(ORD_DES_HYPOTHERMIA)
            ORD_DES_ELECTROCONVULSIONS -> listOf(ORD_DES_SHOW_THEM_ALL, ORD_DES_STORMBLAST)
            ORD_DES_HYPOTHERMIA -> listOf(ORD_DES_EXHAUST, ORD_DES_WINTERS_MAJESTY)
            ORD_DES_SCORCHED_EARTH -> listOf(ORD_DES_WORLD_IN_FLAMES, ORD_DES_OUTBURST)
            ORD_DES_EXHAUST -> listOf(ORD_DES_GLACIAL_PRISON)
            ORD_DES_SHOW_THEM_ALL -> listOf(ORD_DES_ABSOLUTE_POWER)
            ORD_DES_WORLD_IN_FLAMES -> listOf(ORD_DES_CATACLYSM)
            ORD_DES_OUTBURST -> listOf(ORD_DES_CATACLYSM)
            ORD_DES_STORMBLAST -> listOf(ORD_DES_ABSOLUTE_POWER)
            ORD_DES_WINTERS_MAJESTY -> listOf(ORD_DES_GLACIAL_PRISON)
            else -> emptyList()
        }
}
