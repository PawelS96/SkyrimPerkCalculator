package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Alteration implements IPerk {

    ORD_ALT_ALTERATION_MASTERY                       (0.5f,    0.95f,    20,40),
    ORD_ALT_ALTERATION_DUAL_CASTING                  (0.25f,   0.9f,     20),
    ORD_ALT_MAGE_ARMOR                               (0.7f,    0.9f,     20,40,60),
    ORD_ALT_GEOMANCER                                (0.2f,    0.75f,    30),
    ORD_ALT_PHILOSOPHERS_STONE                       (0.375f,  0.65f,    30),
    ORD_ALT_VANCIAN_MAGIC                            (0.7f,    0.575f,   30),
    ORD_ALT_WILD_SHRINES                             (0.8f,    0.65f,    30),
    ORD_ALT_ALTER_SELF_RESISTANCES                   (0.5f,    0.5f,     40),
    ORD_ALT_DISTORTED_SHAPE                          (0.9f,    0.85f,    40),
    ORD_ALT_SPELLBLADE                               (0.6f,    0.65f,    40),
    ORD_ALT_COMMAND_LOCK                             (0.3f,    0.425f,   50),
    ORD_ALT_ENERGY_SHIELD                            (0.85f,   0.75f,    50),
    ORD_ALT_INTUITIVE_MAGIC                          (0.8f,    0.4f,     50,75),
    ORD_ALT_QUADRATIC_WIZARD                         (0.7f,    0.325f,   50),
    ORD_ALT_ALTER_SELF_ATTRIBUTES                    (0.5f,    0.3f,     60),
    ORD_ALT_HOME_MYTHAL                              (0.35f,   0.25f,    60),
    ORD_ALT_WELLOCS_DORMANT                          (0.9f,    0.5f,     60),
    ORD_ALT_DUNGEON_MASTER                           (0.75f,   0.225f,   70),
    ORD_ALT_ENERGY_ROIL                              (0.6f,    0.2f,     70),
    ORD_ALT_THE_MONARCH                              (0.9f,    0.25f,    70),
    ORD_ALT_AURIFICATION                             (0.15f,   0.15f,    80),
    ORD_ALT_DIMENSION_DOOR                           (0.35f,   0.1f,     80),
    ORD_ALT_THRONE_OF_NIRN                           (0.1f,    0.4f,     80),
    ORD_ALT_EMERGENCY_TELEPORT                       (0.2f,    0.05f,    90),
    ORD_ALT_NULLIFIER                                (0.5f,    0.05f,    90),
    ORD_ALT_REND_RESISTANCES                         (0.65f,   0.1f,     90),
    ORD_ALT_ARCANE_THESIS                            (0.85f,   0.05f,    100);

   //TODO update skill levels

    Ord_Alteration(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
