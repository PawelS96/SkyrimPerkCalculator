package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_Alchemy implements IPerk {

    VOK_ALC_ALCHEMY_MASTERY(0.25f, 0.95f, 0, 20, 40, 60, 80),
    VOK_ALC_PHYSICIAN(0.75f, 0.85f, 20),
    VOK_ALC_BENEFACTOR(0.35f, 0.7f, 30),
    VOK_ALC_POISONER(0.7f, 0.7f, 30),
    VOK_ALC_CONCENTRATED_POISON(0.625f, 0.45f, 40, 70, 100),
    VOK_ALC_EXPERIMENTER(0.4f, 0.5f, 50),
    VOK_ALC_STIMULANTS(0.25f, 0.475f, 50),
    VOK_ALC_GREEN_THUMB(0.4f, 0.35f, 60),
    VOK_ALC_SLOW_METABOLISM(0.525f, 0.6f, 60, 90),
    VOK_ALC_ALKAHEST(0.625f, 0.3f, 70),
    VOK_ALC_PURITY(0.5f, 0.25f, 70),
    VOK_ALC_ADRENALINE(0.3f, 0.3f, 80),
    VOK_ALC_PLAGUE_DOCTOR(0.55f, 0.15f, 80),
    VOK_ALC_GOURMET(0.65f, 0.1f, 90),
    VOK_ALC_DOUBLE_TOIL_AND_TROUBLE(0.45f, 0.05f, 100);

    Vok_Alchemy(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}