package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.skills_vanilla;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.PerkInfo;

public enum Speech implements IPerk {

    VAN_SPC_HAGGLING      (0.35f,    0.95f,    0,20,40,60,80),
    VAN_SPC_ALLURE        (0.375f,   0.7f,     30),
    VAN_SPC_MERCHANT      (0.3f,     0.5f,     50),
    VAN_SPC_INVESTOR      (0.25f,    0.35f,    70),
    VAN_SPC_FENCE         (0.2f,     0.2f,     90),
    VAN_SPC_MASTER_TRADER (0.55f,    0.15f,    100),
    VAN_SPC_BRIBERY       (0.6f,     0.7f,     30),
    VAN_SPC_PERSUASION    (0.7f,     0.5f,     50),
    VAN_SPC_INTIMIDATION  (0.75f,    0.35f,    70);

    Speech(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}