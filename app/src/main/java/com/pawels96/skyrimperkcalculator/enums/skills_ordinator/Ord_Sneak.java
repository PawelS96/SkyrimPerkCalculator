package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Sneak implements IPerk {

    ORD_SNK_SNEAK_MASTERY             (0.5f ,    0.95f ,  0,20),
    ORD_SNK_SNEAK_ATTACK              (0.75f ,   0.7f ,   20),
    ORD_SNK_TRIPWIRE                  (0.2f ,    0.75f ,  20) ,
    ORD_SNK_DEMOLITION_JOB            (0.75f ,   0.9f ,   30),
    ORD_SNK_FOG_OF_WAR                (0.48f ,   0.75f ,  30),
    ORD_SNK_SILENT_ROLL               (0.35f ,   0.7f ,   30),
    ORD_SNK_SPOT_DETECTION            (0.15f ,   0.9f ,   30),
    ORD_SNK_ASSASSINS_BLADE           (0.9f ,    0.55f ,  40),
    ORD_SNK_DYNAMIC_ENTRY             (0.4f ,    0.55f ,  40),
    ORD_SNK_INFILTRATOR               (0.6f ,    0.65f ,  40),
    ORD_SNK_WHIPLASH                  (0.25f ,   0.6f ,   40),
    ORD_SNK_BACKSTAB                  (0.85f ,   0.45f ,  50,80),
    ORD_SNK_LIGHT_FOOT                (0.1f ,    0.65f ,  50),
    ORD_SNK_RIGHT_BEHIND_YOU          (0.55f ,   0.5f ,   50),
    ORD_SNK_DISENGAGE                 (0.45f ,   0.45f ,  60),
    ORD_SNK_DODGE_ROLL                (0.3f ,    0.4f ,   60),
    ORD_SNK_SMOKESCREEN               (0.58f ,   0.35f ,  60),
    ORD_SNK_BACKUP_PLAN               (0.15f ,   0.35f ,  70),
    ORD_SNK_CLEAN_ESCAPE              (0.65f ,   0.42f ,  70),
    ORD_SNK_BEHIND_ENEMY_LINES        (0.45f ,   0.25f ,  80),
    ORD_SNK_PARTYSTARTER              (0.58f ,   0.2f ,   80),
    ORD_SNK_PROBLEM_SOLVER            (0.75f ,   0.25f ,  80),
    ORD_SNK_CLOAK_AND_DAGGER          (0.9f ,    0.2f ,   90),
    ORD_SNK_GREASED_LIGHTNING         (0.25f ,   0.2f ,   90),
    ORD_SNK_SHADOW_WARRIOR            (0.5f ,    0.1f ,   90),
    ORD_SNK_LAUGHING_GHOST            (0.8f ,    0.1f ,   100);

    Ord_Sneak(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
