package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_OneHanded implements IPerk {

    ORD_ONH_ONE_HANDED_MASTERY                 (0.5f ,    0.95f,   20,40),
    ORD_ONH_DISCIPLINED_FIGHTER                (0.2f ,    0.8f,    20),
    ORD_ONH_BITE_MARKS                         (0.75f,    0.8f,    30,50,70),
    ORD_ONH_BLEED_LIKE_A_LAMB                  (0.35f,    0.7f,   30,50,70),
    ORD_ONH_CLASH_OF_CHAMPIONS                 (0.65f,    0.7f,   30,50,70),
    ORD_ONH_DENTING_BLOWS                      (0.5f ,    0.8f,    30,50,70) ,
    ORD_ONH_RAVAGE                             (0.9f,     0.85f,   30,50) ,
    ORD_ONH_CROSS_CUT                          (0.6f,     0.6f,    40,60) ,
    ORD_ONH_FURIOUS_STRENGTH                   (0.05f,    0.85f,   40) ,
    ORD_ONH_MANGLE                             (0.3f,     0.6f,    40,60) ,
    ORD_ONH_SAVAGE                             (0.75f,    0.65f,   40,60) ,
    ORD_ONH_SMITE                              (0.45f,    0.65f,   40,60) ,
    ORD_ONH_FALLING_SWORD                      (0.55f,    0.5f,   50) ,
    ORD_ONH_OVERRUN                            (0.05f,    0.5f,    50) ,
    ORD_ONH_RISE_KINSMEN                       (0.45f,    0.55f,    50) ,
    ORD_ONH_ROGUES_PARRY                       (0.12f,    0.65f,   50) ,
    ORD_ONH_SHIELDBITER                        (0.35f,    0.5f,    50) ,
    ORD_ONH_TWIN_FANG                          (0.7f,     0.45f,   50) ,
    ORD_ONH_MAN_OWAR                           (0.9f,    0.5f,    60) ,
    ORD_ONH_SWAYING_COBRA                      (0.7f ,    0.35f,   60) ,
    ORD_ONH_TOLL_THE_BELL                      (0.45f,    0.4f ,   60) ,
    ORD_ONH_WINDSWEPT                          (0.6f ,    0.4f,    60) ,
    ORD_ONH_WOLFSTOOTH                         (0.3f,     0.4f ,   60) ,
    ORD_ONH_DEATH_ADDER                        (0.7f ,    0.25f,   70) ,
    ORD_ONH_GO_FOR_THE_THROAT                  (0.3f,     0.25f,   70) ,
    ORD_ONH_INTO_THE_DUST                      (0.55f,    0.25f,   70) ,
    ORD_ONH_METEOR_STORM                       (0.45f,    0.3f ,   70) ,
    ORD_ONH_THUNDERING_BLOW                    (0.2f ,    0.55f,   70) ,
    ORD_ONH_APEX_PREDATOR                      (0.35f,    0.15f,   80) ,
    ORD_ONH_COILING_PYTHON                     (0.65f,    0.1f ,   80) ,
    ORD_ONH_JUDGMENT                           (0.55f ,   0.15f,    80) ,
    ORD_ONH_SKULL_CRACK                        (0.45f,    0.2f ,   80) ,
    ORD_ONH_AFTERSHOCK                         (0.2f ,    0.35f,   90) ,
    ORD_ONH_UNLEASH_THE_BEAST                  (0.875f ,  0.3f ,   90),
    ORD_ONH_WANDERING_WARRIOR                  (0.45f,    0.05f,   100);

   //TODO update skill levels

    Ord_OneHanded(float x, float y, int... skill) {
        info = new PerkInfo(skill, x, y);
    }

    private PerkInfo info;

    @Override
    public PerkInfo getPerkInfo() {
        return info;
    }
}
