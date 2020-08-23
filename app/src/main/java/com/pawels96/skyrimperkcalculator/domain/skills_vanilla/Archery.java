package com.pawels96.skyrimperkcalculator.domain.skills_vanilla;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Archery implements IPerk {

    VAN_ARC_OVERDRAW                     (0.75f,  0.95f,  0, 20, 40, 60, 80),
    VAN_ARC_CRITICAL_SHOT                (0.7f,   0.6f,   30, 60, 90),
    VAN_ARC_HUNTERS_DISCIPLINE           (0.6f,   0.35f,  50),
    VAN_ARC_RANGER                       (0.55f,  0.25f,  60),
    VAN_ARC_EAGLE_EYE                    (0.4f,   0.75f,  30),
    VAN_ARC_POWER_SHOT                   (0.3f,   0.45f,  50),
    VAN_ARC_QUICK_SHOT                   (0.35f,  0.2f,   70),
    VAN_ARC_STEADY_HAND                  (0.5f,   0.65f,  40, 60),
    VAN_ARC_BULLSEYE                     (0.45f,  0.1f,   100);

    Archery(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}