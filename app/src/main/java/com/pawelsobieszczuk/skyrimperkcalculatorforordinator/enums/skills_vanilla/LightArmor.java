package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.skills_vanilla;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.PerkInfo;

public enum LightArmor implements IPerk {

    VAN_LAR_AGILE_DEFENDER             (0.55f,  0.95f,   0, 20, 40, 60, 80),
    VAN_LAR_CUSTOM_FIT                 (0.5f,   0.7f,    30),
    VAN_LAR_MATCHING_SET               (0.65f,  0.2f,    70),
    VAN_LAR_UNHINDERED                 (0.3f,   0.45f,   50),
    VAN_LAR_WIND_WALKER                (0.35f,  0.25f,   60),
    VAN_LAR_DEFT_MOVEMENT              (0.5f,   0.1f,    100);

    LightArmor(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}