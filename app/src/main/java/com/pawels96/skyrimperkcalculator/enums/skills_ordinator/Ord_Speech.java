package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Speech implements IPerk {

    ORD_SPC_SPEECH_MASTERY              (0.5f ,    0.95f,   0,20),
    ORD_SPC_AND_THE_UNIVERSE_LISTENS    (0.65f ,   0.8f,    20),
    ORD_SPC_PERFORMER                   (0.4f ,    0.775f,  20),
    ORD_SPC_BRIBERY                     (0.75f ,   0.9f,    30),
    ORD_SPC_KINSHIP                     (0.25f ,   0.75f,   30),
    ORD_SPC_IRRESISTIBLE_DANCE          (0.45f ,   0.5f,    30),
    ORD_SPC_SERENADE                    (0.325f ,  0.65f,   30),
    ORD_SPC_BUSINESS_RELATION           (0.1f ,    0.55f,   40),
    ORD_SPC_ENCORE                      (0.325f ,  0.375f,   40),
    ORD_SPC_GOLDEN_FIDDLE               (0.45f ,   0.35f,   40),
    ORD_SPC_WINDBORNE                   (0.7f ,    0.65f,   40),
    ORD_SPC_FORCE_REDOUBLED             (0.85f ,   0.5f,    50,80),
    ORD_SPC_SALESMAN                    (0.225f,   0.475f,  50),
    ORD_SPC_SPEAK_WITH_ANIMALS          (0.6f ,    0.55f,   50),
    ORD_SPC_EARTHQUAKE_DRUM             (0.4f ,    0.275f,  60),
    ORD_SPC_HURRICANE_FORCE             (0.75f ,   0.425f,  60),
    ORD_SPC_INVESTOR                    (0.2f ,    0.35f,   60),
    ORD_SPC_FENCE                       (0.225f,   0.2f,    70),
    ORD_SPC_HORN_OF_SOVNGARDE           (0.725f ,  0.325f,  70,100),
    ORD_SPC_THUUM_OF_WAR                (0.9f ,    0.375f,  70),
    ORD_SPC_GIFT_OF_KYNARETH            (0.625f ,  0.25f,   80),
    ORD_SPC_MERCILESS_STORM             (0.8f ,    0.2f,    80),
    ORD_SPC_WITCHING_RHYTHM             (0.4f ,    0.175f,  80),
    ORD_SPC_LORD_OF_THE_DANCE           (0.6f ,    0.1f,    90),
    ORD_SPC_WAR_DRUMMER                 (0.35f ,   0.075f,  90),
    ORD_SPC_TRADE_PRINCE                (0.175f,   0.05f,   90),
    ORD_SPC_DOVAHZULAAN                 (0.85f ,   0.075f,  100);

    Ord_Speech(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
