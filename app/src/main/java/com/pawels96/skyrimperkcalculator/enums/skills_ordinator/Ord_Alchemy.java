package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Alchemy implements IPerk {

    ORD_ALC_MASTERY                 (0.3f, 0.95f, 20,40),
    ORD_ALC_STIMULANTS              (0.55f, 0.75f, 20),
    ORD_ALC_PHYSICIAN               (0.85f, 0.9f, 20),
    ORD_ALC_ADVANCED_LAB            (0.25f, 0.7f, 30),
    ORD_ALC_EXPERIMENTER            (0.4f, 0.75f, 30),
    ORD_ALC_POISONER                (0.75f, 0.75f, 30),
    ORD_ALC_CRIMSON_HAZE            (0.55f, 0.55f, 40),
    ORD_ALC_BOTTOMLESS_CUP          (0.7f, 0.5f, 40),
    ORD_ALC_ELEMENTAL_OIL           (0.1f, 0.6f, 40),
    ORD_ALC_ALKAHEST                (0.85f, 0.45f, 50),
    ORD_ALC_LAB_SKEEVER             (0.28f, 0.55f, 50),
    ORD_ALC_GREEN_THUMB             (0.42f, 0.5f, 60),
    ORD_ALC_MAENAD                  (0.6f, 0.375f, 60),
    ORD_ALC_THE_ALCHEMISTS_COOKBOOK (0.15f, 0.4f, 60),
    ORD_ALC_DOUBLE_TOIL             (0.28f, 0.3f, 70),
    ORD_ALC_PURE_MIXTURE            (0.42f, 0.375f, 70),
    ORD_ALC_AMPLIFY_LETHALITY       (0.675f, 0.28f, 80),
    ORD_ALC_WITCHMASTER             (0.5f, 0.3f, 80),
    ORD_ALC_CHYMICAL_WEDDING        (0.35f, 0.2f, 90),
    ORD_ALC_WALKING_DISASTER        (0.15f, 0.25f, 90),
    ORD_ALC_WORLD_SERPENT           (0.67f, 0.17f, 90),
    ORD_ALC_THAT_WHICH              (0.5f, 0.05f, 100);

    Ord_Alchemy(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
