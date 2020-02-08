package com.pawels96.skyrimperkcalculator.enums.skills_vokrii;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Vok_Destruction implements IPerk {

    VOK_DES_DESTRUCTION_MASTERY(0.5f, 0.95f, 0, 25, 50, 75, 100),
    VOK_DES_DESTRUCTION_DUAL_CASTING(0.3f, 0.9f, 20),
    VOK_DES_AUGMENTED_FLAMES(0.225f, 0.65f, 30, 60),
    VOK_DES_AUGMENTED_FROST(0.45f, 0.575f, 30, 60),
    VOK_DES_AUGMENTED_SHOCK(0.65f, 0.625f, 30, 60),
    VOK_DES_RUNE_MASTER(0.85f, 0.675f, 40),
    VOK_DES_DEVOURING_FLAMES(0.15f, 0.4f, 50),
    VOK_DES_CHILLING_FROST(0.35f, 0.35f, 50),
    VOK_DES_DEAFENING_SHOCK(0.6f, 0.35f, 50),
    VOK_DES_HETHOTHS_DISJUNCTION(0.85f, 0.45f, 60),
    VOK_DES_IMPACT(0.1f, 0.75f, 60),
    VOK_DES_SCORCHED_EARTH(0.2f, 0.15f, 70),
    VOK_DES_WINTERS_GRASP(0.4f, 0.2f, 70),
    VOK_DES_CRACKLING_SPHERE(0.6f, 0.15f, 70),
    VOK_DES_ELEMENTAL_BARRIER(0.75f, 0.25f, 80),
    VOK_DES_ELEMENTAL_SHIELD(0.9f, 0.3f, 80),
    VOK_DES_MAGES_FURY(0.4f, 0.05f, 90),
    VOK_DES_EYE_OF_THE_STORM(0.8f, 0.1f, 100);

    Vok_Destruction(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
