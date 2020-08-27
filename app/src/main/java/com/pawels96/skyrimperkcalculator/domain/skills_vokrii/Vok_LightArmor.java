package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_LightArmor implements IPerk {

    VOK_LAR_LIGHT_ARMOR_MASTERY (0.55f, 0.95f, 0),
    VOK_LAR_AGILITY (0.7f, 0.7f, 20),
    VOK_LAR_IRON_FIST (0.3f, 0.7f, 20, 50, 80),
    VOK_LAR_LIGHT_ARMOR_FIT (0.5f, 0.75f, 30),
    VOK_LAR_KEEN_SENSES (0.55f, 0.55f, 40),
    VOK_LAR_WINDRUNNER (0.675f, 0.375f, 40),
    VOK_LAR_LIGHT_ARMOR_TRAINING (0.45f, 0.4f, 50),
    VOK_LAR_FLURRY_OF_BLOWS (0.2f, 0.35f, 60, 90),
    VOK_LAR_WARDANCER (0.625f, 0.225f, 60),
    VOK_LAR_MATCHING_LIGHT_SET (0.35f, 0.2f, 70),
    VOK_LAR_EVASIVE_SPRINT (0.55f, 0.075f, 80),
    VOK_LAR_TOUGH_HIDE (0.475f, 0.15f, 80),
    VOK_LAR_KI_STRIKE (0.3f, 0.1f, 90),
    VOK_LAR_UNTOUCHABLE (0.75f, 0.05f, 100);

    Vok_LightArmor(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }
