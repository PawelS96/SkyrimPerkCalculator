package com.pawels96.skyrimperkcalculator.domain.skills_ordinator;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Ord_Destruction implements IPerk {

    ORD_DES_DESTRUCTION_MASTERY               (0.5f,    0.95f ,  0,20),
    ORD_DES_COMBUSTION                        (0.35f,   0.75f ,  20,50),
    ORD_DES_DESTRUCTION_DUAL_CASTING          (0.2f,    0.925f ,  20) ,
    ORD_DES_IONIZED_PATH                      (0.65f,   0.75f ,  20,50),
    ORD_DES_MERCILESS_COLD                    (0.5f,    0.675f ,   20,50),
    ORD_DES_FORCE_OF_NATURE                   (0.3f,    0.85f ,  30) ,
    ORD_DES_FROSTFALL                         (0.5f,    0.55f ,  30) ,
    ORD_DES_SCARRING_BURNS                    (0.27f,   0.65f ,  30) ,
    ORD_DES_STATIC_FIELD                      (0.75f,   0.63f ,  30) ,
    ORD_DES_ARC_BURN                          (0.83f,   0.54f ,  40) ,
    ORD_DES_CONFLAGRATION                     (0.35f,   0.53f ,  40) ,
    ORD_DES_CRYSTALIZE                        (0.6f,    0.4f ,   40) ,
    ORD_DES_HARSH_LESSON                      (0.8f,    0.925f ,  40) ,
    ORD_DES_RUNECASTER                        (0.9f,    0.65f ,  40) ,
    ORD_DES_FLASH_FIRE                        (0.1f,    0.53f ,  50) ,
    ORD_DES_MAGNETIZE                         (0.65f,   0.55f ,  50) ,
    ORD_DES_ROBE_OF_THE_MAGI                  (0.15f,   0.8f ,   50) ,
    ORD_DES_SHATTER                           (0.4f,    0.4f ,   50) ,
    ORD_DES_ICED_EARTH                        (0.5f,    0.35f ,  60) ,
    ORD_DES_NOVA_CHARGE                       (0.75f,   0.45f ,  60) ,
    ORD_DES_PYROMANCER_ASCENSION              (0.2f,    0.45f ,  60) ,
    ORD_DES_ANCIENT_SEALS                     (0.9f,    0.4f ,   70) ,
    ORD_DES_ELECTROCONVULSIONS                (0.75f,   0.35f ,  70) ,
    ORD_DES_HYPOTHERMIA                       (0.5f,    0.25f ,  70) ,
    ORD_DES_SCORCHED_EARTH                    (0.2f,    0.35f ,  70) ,
    ORD_DES_WAR_OF_THE_ELEMENTS               (0.85f,   0.8f ,   70) ,
    ORD_DES_ELEMENTAL_SPECIALIZATION          (0.15f,   0.7f ,   80) ,
    ORD_DES_EXHAUST                           (0.55f,   0.16f ,  80) ,
    ORD_DES_SHOW_THEM_ALL                     (0.85f,   0.25f ,  80) ,
    ORD_DES_WORLD_IN_FLAMES                   (0.1f,    0.25f ,  80) ,
    ORD_DES_OUTBURST                          (0.27f,   0.22f ,  90) ,
    ORD_DES_STORMBLAST                        (0.7f,    0.2f ,   90) ,
    ORD_DES_WINTERS_MAJESTY                   (0.4f,    0.12f ,  90) ,
    ORD_DES_ABSOLUTE_POWER                    (0.75f,   0.1f ,   100),
    ORD_DES_CATACLYSM                         (0.2f,    0.1f ,   100),
    ORD_DES_GLACIAL_PRISON                    (0.5f,    0.055f,  100);

    Ord_Destruction(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
