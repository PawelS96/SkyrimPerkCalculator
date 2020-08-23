package com.pawels96.skyrimperkcalculator.domain.skills_ordinator;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkInfo;

public enum Ord_Lockpicking implements IPerk {

    ORD_LCK_MASTERY                     (0.5f,     0.95f,     0, 20),
    ORD_LCK_GAME_OF_FATE                (0.1f,     0.9f,      20),
    ORD_LCK_BEAR_TRAPS                  (0.6f,     0.85f,     20),
    ORD_LCK_LOCKDOWN                    (0.1f,     0.7f,      30),
    ORD_LCK_ROBBERS_EYE                 (0.35f,    0.7f,      30),
    ORD_LCK_LOCKSMITH                   (0.9f,     0.7f,      40),
    ORD_LCK_LOCKJAW                     (0.7f,     0.7f,      40),
    ORD_LCK_HOTWIRE                     (0.1f,     0.4f,      50),
    ORD_LCK_DUNGEONEER                  (0.3f,     0.4f,      60),
    ORD_LCK_NOSE_FOR_TREASURE           (0.2f,     0.5f,      50),
    ORD_LCK_BIG_GAME_HUNTER             (0.55f,    0.5f,      50),
    ORD_LCK_BAIT                        (0.8f,     0.45f,     70),
    ORD_LCK_BUSHWHACK                   (0.65f,    0.35f,     80),
    ORD_LCK_PERCUSSIVE_MAINTENANCE      (0.15f,    0.3f,      90),
    ORD_LCK_GONE_IN_FIFTEEN_SECONDS     (0.4f,     0.55f,     40),
    ORD_LCK_GOLDEN_TOUCH                (0.5f,     0.4f,      60),
    ORD_LCK_TREASURE_HUNTER             (0.4f,     0.275f,    90),
    ORD_LCK_THE_REVENGE                 (0.8f,     0.3f,      80),
    ORD_LCK_DRAGONS_TEETH               (0.65f,    0.2f,      100),
    ORD_LCK_WAX_KEY                     (0.9f,     0.9f,      30),
    ORD_LCK_SEEN_THIS_BEFORE            (0.9f,     0.55f,     100);

    Ord_Lockpicking(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}


