package com.pawels96.skyrimperkcalculator.enums.skills_vokrii;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Vok_Archery implements IPerk {

    VOK_ARC_ARCHERY_MASTERY (0.5f, 0.95f, 0, 20, 40, 60, 80),
    VOK_ARC_FAR_SHOT (0.175f, 0.65f, 20, 50),
    VOK_ARC_POINT_BLANK_SHOT (0.4f, 0.625f, 20, 50),
    VOK_ARC_EAGLE_EYE (0.725f, 0.65f, 30),
    VOK_ARC_IMPALING_SHOT (0.1f, 0.45f, 40),
    VOK_ARC_BREACHING_SHOT (0.4f, 0.45f, 40),
    VOK_ARC_STEADY_AIM (0.775f, 0.425f, 40, 70),
    VOK_ARC_HUNTERS_DISCIPLINE (0.55f, 0.375f, 50),
    VOK_ARC_POWER_SHOT (0.25f, 0.375f, 50),
    VOK_ARC_LIONS_ARROW (0.9f, 0.35f, 60, 90),
    VOK_ARC_RANGER (0.2625f, 0.275f, 60),
    VOK_ARC_QUICK_SHOT (0.3f, 0.175f, 70),
    VOK_ARC_ARROW_TO_THE_KNEE (0.15f, 0.2f, 80),
    VOK_ARC_GORE (0.365f, 0.275f, 80),
    VOK_ARC_HUNTERS_FOCUS (0.65f, 0.2f, 90),
    VOK_ARC_PINNING_SHOT (0.375f, 0.05f, 100);

    Vok_Archery(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }
