package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_Restoration implements IPerk {

    VOK_RST_RESTORATION_MASTERY (0.5f, 0.95f, 0, 25, 50, 75, 100),
    VOK_RST_RESTORATION_DUAL_CASTING (0.8f, 0.875f, 20),
    VOK_RST_MERCY (0.35f, 0.65f, 20),
    VOK_RST_INSPIRE (0.6f, 0.6f, 30, 60),
    VOK_RST_INNER_LIGHT (0.8f, 0.55f, 30, 60),
    VOK_RST_RESPITE (0.325f, 0.375f, 40),
    VOK_RST_VIGILANT_WARD(0.45f, 0.525f, 40, 70),
    VOK_RST_HARM (0.18f, 0.425f, 50),
    VOK_RST_SUNS_JUDGMENT (0.175f, 0.275f, 60, 90),
    VOK_RST_WARD_ABSORB (0.5f, 0.25f, 60),
    VOK_RST_NECROMAGE (0.65f, 0.175f, 70),
    VOK_RST_REBUKE_UNDEAD (0.1f, 0.15f, 70),
    VOK_RST_BLESSED (0.825f, 0.15f, 80),
    VOK_RST_MAGE_WARD (0.475f, 0.1f, 80),
    VOK_RST_ETERNAL_FLAME (0.35f, 0.05f, 90),
    VOK_RST_INTERVENTION (0.9f, 0.1f, 100);

    Vok_Restoration(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }