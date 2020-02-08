package com.pawels96.skyrimperkcalculator.enums.skills_ordinator;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.models.PerkInfo;

public enum Ord_Conjuration implements IPerk {

   ORD_CON_CONJURATION_MASTERY               (0.5f,    0.95f ,  0,20),
   ORD_CON_BONE_COLLECTOR                    (0.5f,    0.8f ,   20),
   ORD_CON_CONJURATION_DUAL_CASTING          (0.2f,    0.88f ,  20),
   ORD_CON_MYSTIC_BINDING                    (0.65f,   0.75f ,  20),
   ORD_CON_DEAD_TIDE                         (0.55f,   0.65f ,  30,60),
   ORD_CON_PLANEMELD                         (0.2f,    0.78f ,  30),
   ORD_CON_RAVENOUS_DEAD                     (0.35f,   0.75f ,  30),
   ORD_CON_SOUL_RAIDER                       (0.8f,    0.7f ,   30),
   ORD_CON_ATROMANCY                         (0.15f,   0.63f ,  40),
   ORD_CON_BARROW_LORD                       (0.7f,    0.45f ,  40),
   ORD_CON_PRESERVATION                      (0.35f,   0.6f ,   40),
   ORD_CON_RAT_KING                          (0.8f,    0.88f ,  40),
   ORD_CON_REAP_AND_SOW                      (0.45f,   0.5f ,   40,70),
   ORD_CON_REND_FROM_THIS_WORLD              (0.8f,    0.6f ,   40),
   ORD_CON_SIGNED_IN_BLOOD                   (0.1f,    0.7f ,   40),
   ORD_CON_EDGE_OF_OBLIVION                  (0.22f,   0.55f ,  50),
   ORD_CON_PACT_MAGIC                        (0.07f,   0.5f ,   50),
   ORD_CON_SKELETON_MAGES                    (0.5f,    0.3f ,   50,80),
   ORD_CON_UNDEAD_CROWN                      (0.33f,   0.4f ,   50),
   ORD_CON_VOID_BURN                         (0.9f,    0.5f ,   50),
   ORD_CON_A_PLAGUE_UPON_THEE                (0.22f,   0.45f ,  60),
   ORD_CON_CONJURE_ALTAR                     (0.6f,    0.2f ,   60),
   ORD_CON_FIRE_RITUAL                       (0.45f,   0.175f ,   60),
   ORD_CON_HOLLOW_BINDING                    (0.9f,    0.35f ,  60),
   ORD_CON_MAELSTROM                         (0.06f,   0.4f ,   60),
   ORD_CON_CORPSE_GAS                        (0.25f,   0.3f ,   70),
   ORD_CON_DARK_WHISPERS                     (0.75f,   0.25f ,  70),
   ORD_CON_ELEMENTAL_POTENCY                 (0.1f,    0.25f ,  70),
   ORD_CON_BRAND_OF_THE_NECROMANCER          (0.75f,   0.15f ,  80),
   ORD_CON_FEED_THE_MONSTER                  (0.8f,    0.78f ,  80),
   ORD_CON_NECROMASTER                       (0.3f,    0.2f ,   80),
   ORD_CON_UNLEASH_HELL                      (0.1f,    0.1f ,   80),
   ORD_CON_COVENANT_OF_COLDHARBOUR           (0.85f,   0.05f ,  90),
   ORD_CON_PUPPET_MASTER                     (0.6f,    0.1f ,   90),
   ORD_CON_SHOCKED_TO_LIFE                   (0.4f,    0.1f ,   90),
   ORD_CON_SUMMON_RESIST                     (0.2f,    0.15f ,  90),
   ORD_CON_KING_OF_BONES                     (0.5f,    0.05f ,  100),
   ORD_CON_MARCH_OF_OBLIVION                 (0.25f,   0.05f ,  100);

   Ord_Conjuration(float x, float y, int... skill) {
       info = new PerkInfo(skill, x, y);
    }

   private PerkInfo info;

   @Override
   public PerkInfo getPerkInfo() {
      return info;
   }
}
