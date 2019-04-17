package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.skills_vanilla;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.PerkInfo;

public enum Alteration implements IPerk {

    VAN_ALT_NOVICE_ALTERATION             (0.5f,     0.95f,    0),
    VAN_ALT_ALTERATION_DUAL_CASTING       (0.35f,    0.75f,    20),
    VAN_ALT_APPRENTICE_ALTERATION         (0.525f,   0.675f,   25),
    VAN_ALT_MAGIC_RESISTANCE              (0.7f,     0.45f,    30, 50, 70),
    VAN_ALT_ADEPT_ALTERATION              (0.525f,   0.375f,   50),
    VAN_ALT_EXPERT_ALTERATION             (0.65f,    0.25f,    75),
    VAN_ALT_ATRONACH                      (0.3f,     0.1f,     100),
    VAN_ALT_MASTER_ALTERATION             (0.8f,     0.15f,    100),
    VAN_ALT_STABILITY                     (0.375f,   0.25f,    70),
    VAN_ALT_MAGE_ARMOR                    (0.35f,    0.45f,    30, 50, 70);


    Alteration(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}