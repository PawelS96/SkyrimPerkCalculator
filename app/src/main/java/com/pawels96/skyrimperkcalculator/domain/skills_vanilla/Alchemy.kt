package com.pawels96.skyrimperkcalculator.domain.skills_vanilla;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Alchemy implements IPerk {

    VAN_ALC_ALCHEMIST                   (0.25f,     0.95f,   0, 20, 40, 60, 80),
    VAN_ALC_PHYSICIAN                   (0.75f,     0.85f,   20),
    VAN_ALC_BENEFACTOR                  (0.65f,     0.65f,   30),
    VAN_ALC_EXPERIMENTER                (0.6f,      0.5f,    50, 70, 90),
    VAN_ALC_POISONER                    (0.3f,      0.65f,   30),
    VAN_ALC_CONCENTRATED_POISON         (0.35f,     0.45f,   60),
    VAN_ALC_GREEN_THUMB                 (0.4f,      0.25f,   70),
    VAN_ALC_SNAKEBLOOD                  (0.6f,      0.2f,    80),
    VAN_ALC_PURITY                      (0.525f,    0.075f,  100);

    Alchemy(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}