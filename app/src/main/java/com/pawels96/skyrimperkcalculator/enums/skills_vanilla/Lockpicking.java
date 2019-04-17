package com.pawels96.skyrimperkcalculator.enums.skills_vanilla;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Lockpicking implements IPerk {

    VAN_LCK_NOVICE_LOCKS          (0.5f,    0.95f,  0),
    VAN_LCK_APPRENTICE_LOCKS      (0.625f,  0.7f,   20),
    VAN_LCK_QUICK_HANDS           (0.45f,   0.65f,  40),
    VAN_LCK_WAX_KEY               (0.3f,    0.6f,   50),
    VAN_LCK_ADEPT_LOCKS           (0.7f,    0.5f,   50),
    VAN_LCK_EXPERT_LOCKS          (0.75f,   0.3f,   75),
    VAN_LCK_GOLDEN_TOUCH          (0.55f,   0.45f,  60),
    VAN_LCK_TREASURE_HUNTER       (0.4f,    0.4f,   70),
    VAN_LCK_LOCKSMITH             (0.575f,   0.225f,   80),
    VAN_LCK_UNBREAKABLE           (0.45f,   0.15f,  100),
    VAN_LCK_MASTER_LOCKS          (0.775f,  0.1f,   100);

    Lockpicking(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}