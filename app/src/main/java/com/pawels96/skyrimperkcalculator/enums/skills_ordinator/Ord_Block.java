package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Block implements IPerk {

    ORD_BLC_BLOCK_MASTERY                  (0.5f,    0.95f ,   0,20),
    ORD_BLC_TIMED_BLOCK                    (0.25f,   0.825f,   20,50),
    ORD_BLC_DEFLECT_ARROWS                 (0.675f,  0.75f ,   30) ,
    ORD_BLC_POKE_THE_DRAGON                (0.1f,    0.7f ,    30),
    ORD_BLC_APOCALYPSE_PROOF               (0.125f,  0.5f ,    40,70),
    ORD_BLC_POWER_BASH                     (0.9f,    0.75f ,   40),
    ORD_BLC_QUICK_REFLEXES                 (0.5f,    0.7f ,    40),
    ORD_BLC_DOMINION                       (0.55f,   0.55f ,   50),
    ORD_BLC_SKULL_RATTLERB                 (0.875f,  0.5f ,    50),
    ORD_BLC_DRAGON_TAIL                    (0.425f,  0.3f ,    60),
    ORD_BLC_TIMING_STREAK                  (0.175f,  0.3f ,    60),
    ORD_BLC_BLOCK_RUNNER                   (0.475f,  0.4f ,    70),
    ORD_BLC_MOCKING_BLOW                   (0.825f,  0.3f ,    70),
    ORD_BLC_BREAK_THEIR_TEETH              (0.3f,    0.225f,   80),
    ORD_BLC_CAST_ASIDE                     (0.675f,  0.25f ,   80),
    ORD_BLC_DELIVERANCE                    (0.35f,   0.15f ,   90),
    ORD_BLC_HOLD_THE_LINE                  (0.6f,    0.35f ,   90),
    ORD_BLC_UNSTOPPABLE_FORCE              (0.55f,   0.175f,   90),
    ORD_BLC_DRAGON_SCALES                  (0.45f,   0.075f,   100);

    Ord_Block(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
