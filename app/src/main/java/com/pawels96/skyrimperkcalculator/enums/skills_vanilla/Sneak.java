package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.skills_vanilla;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.PerkInfo;

public enum Sneak implements IPerk {

    VAN_SNK_STEALTH                    (0.5f,    0.95f,   0, 20, 40, 60, 80),
    VAN_SNK_BACKSTAB                   (0.75f,   0.75f,   30),
    VAN_SNK_DEADLY_AIM                 (0.75f,   0.55f,   40),
    VAN_SNK_ASSASSINS_BLADE            (0.6f,    0.45f,   50),
    VAN_SNK_MUFFLED_MOVEMENT           (0.25f,   0.75f,   30),
    VAN_SNK_LIGHT_FOOT                 (0.3f,    0.5f,    40),
    VAN_SNK_SILENT_ROLL                (0.4f,    0.4f,    50),
    VAN_SNK_SILENCE                    (0.55f,   0.3f,    70),
    VAN_SNK_SHADOW_WARRIOR             (0.75f,   0.2f,    100);

    Sneak(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}