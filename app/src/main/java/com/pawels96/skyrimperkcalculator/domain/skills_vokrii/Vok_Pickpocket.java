package com.pawels96.skyrimperkcalculator.domain.skills_vokrii;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Vok_Pickpocket implements IPerk {

    VOK_PCK_PICKPOCKET_MASTERY (0.5f, 0.95f, 0),
    VOK_PCK_CUTPURSE (0.475f, 0.7f, 20),
    VOK_PCK_OBLIVIOUS (0.5f, 0.5f, 30),
    VOK_PCK_PAYDAY (0.25f, 0.625f, 30),
    VOK_PCK_DEATHS_EMPEROR (0.7f, 0.475f, 40, 70),
    VOK_PCK_POISONED (0.65f, 0.35f, 40),
    VOK_PCK_EXTRA_POCKETS (0.8f, 0.775f, 50),
    VOK_PCK_LAWLESS_TIMES (0.55f, 0.175f, 60, 90),
    VOK_PCK_TRICKSTER (0.675f, 0.225f, 70),
    VOK_PCK_CONSPICUOUS_WEALTH (0.275f, 0.3f, 80),
    VOK_PCK_MASTER_THIEF (0.4f, 0.1f, 90),
    VOK_PCK_PERFECT_TOUCH (0.725f, 0.1f, 100);

    Vok_Pickpocket(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
    }
