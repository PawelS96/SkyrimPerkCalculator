package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_Illusion implements IPerk {

    VOK_ILU_ILLUSION_MASTERY (0.5f, 0.95f, 0, 25, 50, 75, 100),
    VOK_ILU_ILLUSION_DUAL_CASTING (0.2f, 0.85f, 20),
    VOK_ILU_ANIMAGE (0.85f, 0.825f, 20, 50, 80),
    VOK_ILU_NEVERWORLD (0.5f, 0.65f, 30),
    VOK_ILU_TERROR (0.55f, 0.475f, 40),
    VOK_ILU_IRON_MAIDEN (0.525f, 0.3f, 50),
    VOK_ILU_QUIET_CASTING (0.7f, 0.6f, 50),
    VOK_ILU_LAMB_TO_THE_SLAUGHTER (0.2f, 0.45f, 60),
    VOK_ILU_SPLENDOR (0.55f, 0.2f, 60),
    VOK_ILU_MASTER_OF_THE_MIND (0.9f, 0.5f, 70, 100),
    VOK_ILU_PARALYZING_FEAR (0.35f, 0.325f, 70),
    VOK_ILU_ENRAGE (0.375f, 0.15f, 80),
    VOK_ILU_SPIRIT_OF_WAR (0.475f, 0.075f, 90),
    VOK_ILU_BLUR (0.725f, 0.3f, 90),
    VOK_ILU_MIND_THRALL (0.65f, 0.05f, 100);

    Vok_Illusion(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }