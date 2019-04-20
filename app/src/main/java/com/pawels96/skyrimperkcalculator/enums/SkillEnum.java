package com.pawels96.skyrimperkcalculator.enums;

import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Alchemy;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Alteration;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Archery;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Block;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Conjuration;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Destruction;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Enchanting;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_HeavyArmor;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Illusion;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_LightArmor;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Lockpicking;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_OneHanded;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Pickpocket;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Restoration;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Smithing;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Sneak;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Speech;
import com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_TwoHanded;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Alchemy;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Alteration;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Archery;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Block;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Conjuration;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Destruction;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Enchanting;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.HeavyArmor;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Illusion;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.LightArmor;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Lockpicking;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.OneHanded;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Pickpocket;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Restoration;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Smithing;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Sneak;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Speech;
import com.pawels96.skyrimperkcalculator.enums.skills_vanilla.TwoHanded;

import java.io.Serializable;
import java.util.HashMap;

import static com.pawels96.skyrimperkcalculator.enums.SkillType.*;

import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Alchemy.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Alteration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Archery.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Block.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Conjuration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Destruction.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Enchanting.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Illusion.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_HeavyArmor.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_LightArmor.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Lockpicking.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_OneHanded.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Pickpocket.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Restoration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Smithing.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Sneak.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_Speech.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_ordinator.Ord_TwoHanded.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Alchemy.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Alteration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Archery.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Block.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Conjuration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Destruction.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Enchanting.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.HeavyArmor.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Illusion.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.LightArmor.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Lockpicking.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.OneHanded.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Pickpocket.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Restoration.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Smithing.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Sneak.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.Speech.*;
import static com.pawels96.skyrimperkcalculator.enums.skills_vanilla.TwoHanded.*;

public enum SkillEnum implements Serializable {

    SKILL_LOCKPICKING(STEALTH),
    SKILL_PICKPOCKET(STEALTH),
    SKILL_SNEAK(STEALTH),
    SKILL_ALCHEMY(STEALTH),
    SKILL_LIGHT_ARMOR(STEALTH),
    SKILL_SPEECH(STEALTH),
    SKILL_ARCHERY(COMBAT),
    SKILL_ONE_HANDED(COMBAT),
    SKILL_TWO_HANDED(COMBAT),
    SKILL_HEAVY_ARMOR(COMBAT),
    SKILL_BLOCK(COMBAT),
    SKILL_SMITHING(COMBAT),
    SKILL_ALTERATION(MAGIC),
    SKILL_DESTRUCTION(MAGIC),
    SKILL_RESTORATION(MAGIC),
    SKILL_CONJURATION(MAGIC),
    SKILL_ENCHANTING(MAGIC),
    SKILL_ILLUSION(MAGIC);

    private SkillType type;

    SkillEnum(SkillType type) {
        this.type = type;
    }

    public SkillType getType() {
        return type;
    }

    public IPerk[] getPerks(PerkSystem perkSystem) {

        switch (perkSystem) {

            case ORDINATOR:

                switch (this) {

                    case SKILL_LOCKPICKING:
                        return Ord_Lockpicking.values();
                    case SKILL_ALCHEMY:
                        return Ord_Alchemy.values();
                    case SKILL_HEAVY_ARMOR:
                        return Ord_HeavyArmor.values();
                    case SKILL_ILLUSION:
                        return Ord_Illusion.values();
                    case SKILL_CONJURATION:
                        return Ord_Conjuration.values();
                    case SKILL_DESTRUCTION:
                        return Ord_Destruction.values();
                    case SKILL_BLOCK:
                        return Ord_Block.values();
                    case SKILL_SNEAK:
                        return Ord_Sneak.values();
                    case SKILL_SPEECH:
                        return Ord_Speech.values();
                    case SKILL_ARCHERY:
                        return Ord_Archery.values();
                    case SKILL_SMITHING:
                        return Ord_Smithing.values();
                    case SKILL_ALTERATION:
                        return Ord_Alteration.values();
                    case SKILL_ENCHANTING:
                        return Ord_Enchanting.values();
                    case SKILL_ONE_HANDED:
                        return Ord_OneHanded.values();
                    case SKILL_PICKPOCKET:
                        return Ord_Pickpocket.values();
                    case SKILL_TWO_HANDED:
                        return Ord_TwoHanded.values();
                    case SKILL_LIGHT_ARMOR:
                        return Ord_LightArmor.values();
                    case SKILL_RESTORATION:
                        return Ord_Restoration.values();
                    default:
                        return Ord_Lockpicking.values();
                }

            case VANILLA:

                switch (this) {

                    case SKILL_LOCKPICKING:
                        return Lockpicking.values();
                    case SKILL_ALCHEMY:
                        return Alchemy.values();
                    case SKILL_HEAVY_ARMOR:
                        return HeavyArmor.values();
                    case SKILL_ILLUSION:
                        return Illusion.values();
                    case SKILL_CONJURATION:
                        return Conjuration.values();
                    case SKILL_DESTRUCTION:
                        return Destruction.values();
                    case SKILL_BLOCK:
                        return Block.values();
                    case SKILL_SNEAK:
                        return Sneak.values();
                    case SKILL_SPEECH:
                        return Speech.values();
                    case SKILL_ARCHERY:
                        return Archery.values();
                    case SKILL_SMITHING:
                        return Smithing.values();
                    case SKILL_ALTERATION:
                        return Alteration.values();
                    case SKILL_ENCHANTING:
                        return Enchanting.values();
                    case SKILL_ONE_HANDED:
                        return OneHanded.values();
                    case SKILL_PICKPOCKET:
                        return Pickpocket.values();
                    case SKILL_TWO_HANDED:
                        return TwoHanded.values();
                    case SKILL_LIGHT_ARMOR:
                        return LightArmor.values();
                    case SKILL_RESTORATION:
                        return Restoration.values();
                    default:
                        return Lockpicking.values();
                }

            default:
                return Lockpicking.values();
        }
    }

    public HashMap<IPerk, IPerk[]> getConnectionsMap(PerkSystem perkSystem) {

        switch (perkSystem) {

            case ORDINATOR:
                return getOrdinatorConnections();
            case VANILLA:
                return getVanillaConnections();
        }

        return null;
    }

    private HashMap<IPerk, IPerk[]> getOrdinatorConnections() {

        HashMap<IPerk, IPerk[]> map = new HashMap<>();

        switch (this) {

            case SKILL_HEAVY_ARMOR:

                map.put(ORD_HAR_MASTERY, new IPerk[]{ORD_HAR_CUSHIONED, ORD_HAR_HEAVY_ARMOR_FIT, ORD_HAR_BATTLE_WEARY});
                map.put(ORD_HAR_BREAK_UPON_ME, new IPerk[]{ORD_HAR_RISE_ABOVE, ORD_HAR_REAP_THE_WHIRLWIND});
                map.put(ORD_HAR_BATTLE_WEARY, new IPerk[]{ORD_HAR_BREAK_UPON_ME, ORD_HAR_BORN_TO_FIGHT});
                map.put(ORD_HAR_RISE_ABOVE, new IPerk[]{ORD_HAR_PRIMAL_FEAR});
                map.put(ORD_HAR_REAP_THE_WHIRLWIND, new IPerk[]{ORD_HAR_SOVEREIGN});
                map.put(ORD_HAR_SOVEREIGN, new IPerk[]{ORD_HAR_FACE_OF_THE_MOUNTAIN});
                map.put(ORD_HAR_BORN_TO_FIGHT, new IPerk[]{ORD_HAR_LEAD_THE_TEMPEST});
                map.put(ORD_HAR_HEAVY_ARMOR_FIT, new IPerk[]{ORD_HAR_FACE_OF_DEATH, ORD_HAR_DEFIANCE, ORD_HAR_RALLYING_STANDARD});
                map.put(ORD_HAR_FACE_OF_DEATH, new IPerk[]{ORD_HAR_LEAD_THE_TEMPEST});
                map.put(ORD_HAR_DEFIANCE, new IPerk[]{ORD_HAR_REVEL_IN_BATTLE, ORD_HAR_BEDROCK, ORD_HAR_NEVER_KNEEL});
                map.put(ORD_HAR_BEDROCK, new IPerk[]{ORD_HAR_LEAD_THE_TEMPEST});
                map.put(ORD_HAR_RALLYING_STANDARD, new IPerk[]{ORD_HAR_NEVER_KNEEL, ORD_HAR_WARBRINGER});
                map.put(ORD_HAR_NEVER_KNEEL, new IPerk[]{ORD_HAR_OUT_OF_THE_INFERNO});
                map.put(ORD_HAR_OUT_OF_THE_INFERNO, new IPerk[]{ORD_HAR_IMMORTAL});
                map.put(ORD_HAR_WARBRINGER, new IPerk[]{ORD_HAR_DOOMBRINGER, ORD_HAR_IMMORTAL});
                break;

            case SKILL_LOCKPICKING:

                map.put(ORD_LCK_MASTERY, new IPerk[]{ORD_LCK_GAME_OF_FATE, ORD_LCK_LOCKDOWN, ORD_LCK_ROBBERS_EYE, ORD_LCK_BEAR_TRAPS, ORD_LCK_WAX_KEY});
                map.put(ORD_LCK_LOCKDOWN, new IPerk[]{ORD_LCK_HOTWIRE});
                map.put(ORD_LCK_HOTWIRE, new IPerk[]{ORD_LCK_PERCUSSIVE_MAINTENANCE});
                map.put(ORD_LCK_ROBBERS_EYE, new IPerk[]{ORD_LCK_NOSE_FOR_TREASURE, ORD_LCK_GONE_IN_FIFTEEN_SECONDS});
                map.put(ORD_LCK_GONE_IN_FIFTEEN_SECONDS, new IPerk[]{ORD_LCK_DUNGEONEER, ORD_LCK_GOLDEN_TOUCH});
                map.put(ORD_LCK_NOSE_FOR_TREASURE, new IPerk[]{ORD_LCK_DUNGEONEER});
                map.put(ORD_LCK_DUNGEONEER, new IPerk[]{ORD_LCK_TREASURE_HUNTER});
                map.put(ORD_LCK_BEAR_TRAPS, new IPerk[]{ORD_LCK_LOCKJAW});
                map.put(ORD_LCK_LOCKJAW, new IPerk[]{ORD_LCK_BIG_GAME_HUNTER, ORD_LCK_BAIT});
                map.put(ORD_LCK_BIG_GAME_HUNTER, new IPerk[]{ORD_LCK_BUSHWHACK});
                map.put(ORD_LCK_BUSHWHACK, new IPerk[]{ORD_LCK_DRAGONS_TEETH});
                map.put(ORD_LCK_BAIT, new IPerk[]{ORD_LCK_BUSHWHACK, ORD_LCK_THE_REVENGE});
                map.put(ORD_LCK_THE_REVENGE, new IPerk[]{ORD_LCK_DRAGONS_TEETH});
                map.put(ORD_LCK_WAX_KEY, new IPerk[]{ORD_LCK_LOCKSMITH});
                map.put(ORD_LCK_LOCKSMITH, new IPerk[]{ORD_LCK_SEEN_THIS_BEFORE});
                map.put(ORD_LCK_GOLDEN_TOUCH, new IPerk[]{ORD_LCK_TREASURE_HUNTER});
                break;

            case SKILL_ALCHEMY:

                map.put(ORD_ALC_MASTERY, new IPerk[]{ORD_ALC_ELEMENTAL_OIL, ORD_ALC_ADVANCED_LAB, ORD_ALC_EXPERIMENTER, ORD_ALC_STIMULANTS, ORD_ALC_POISONER, ORD_ALC_PHYSICIAN});
                map.put(ORD_ALC_ELEMENTAL_OIL, new IPerk[]{ORD_ALC_THE_ALCHEMISTS_COOKBOOK});
                map.put(ORD_ALC_THE_ALCHEMISTS_COOKBOOK, new IPerk[]{ORD_ALC_WALKING_DISASTER});
                map.put(ORD_ALC_ADVANCED_LAB, new IPerk[]{ORD_ALC_LAB_SKEEVER});
                map.put(ORD_ALC_LAB_SKEEVER, new IPerk[]{ORD_ALC_DOUBLE_TOIL, ORD_ALC_GREEN_THUMB});
                map.put(ORD_ALC_EXPERIMENTER, new IPerk[]{ORD_ALC_GREEN_THUMB});
                map.put(ORD_ALC_GREEN_THUMB, new IPerk[]{ORD_ALC_PURE_MIXTURE});
                map.put(ORD_ALC_POISONER, new IPerk[]{ORD_ALC_BOTTOMLESS_CUP, ORD_ALC_ALKAHEST});
                map.put(ORD_ALC_BOTTOMLESS_CUP, new IPerk[]{ORD_ALC_AMPLIFY_LETHALITY});
                map.put(ORD_ALC_ALKAHEST, new IPerk[]{ORD_ALC_AMPLIFY_LETHALITY});
                map.put(ORD_ALC_AMPLIFY_LETHALITY, new IPerk[]{ORD_ALC_WORLD_SERPENT});
                map.put(ORD_ALC_STIMULANTS, new IPerk[]{ORD_ALC_CRIMSON_HAZE});
                map.put(ORD_ALC_CRIMSON_HAZE, new IPerk[]{ORD_ALC_MAENAD, ORD_ALC_WITCHMASTER});
                map.put(ORD_ALC_WITCHMASTER, new IPerk[]{ORD_ALC_CHYMICAL_WEDDING, ORD_ALC_WORLD_SERPENT});
                map.put(ORD_ALC_CHYMICAL_WEDDING, new IPerk[]{ORD_ALC_THAT_WHICH});
                map.put(ORD_ALC_WORLD_SERPENT, new IPerk[]{ORD_ALC_THAT_WHICH});
                break;

            case SKILL_ILLUSION:

                map.put(ORD_ILU_ILLUSION_MASTERY, new IPerk[]{ORD_ILU_ILLUSION_DUAL_CASTING, ORD_ILU_NIGHT_EYE, ORD_ILU_ENTICE_BARTER, ORD_ILU_IMPOSING_PRESENCE, ORD_ILU_COMMANDING_PRESENCE, ORD_ILU_DREAM_THIEF, ORD_ILU_QUIET_BEFORE_THE_STORM});
                map.put(ORD_ILU_DREAM_THIEF, new IPerk[]{ORD_ILU_KINDRED_MAGE});
                map.put(ORD_ILU_COMMANDING_PRESENCE, new IPerk[]{ORD_ILU_CROWN_OF_THE_FALSE_KING});
                map.put(ORD_ILU_ENTICE_BARTER, new IPerk[]{ORD_ILU_NEMESIS});
                map.put(ORD_ILU_IMPOSING_PRESENCE, new IPerk[]{ORD_ILU_NEMESIS, ORD_ILU_WILTING, ORD_ILU_SHADOW_REFUGE});
                map.put(ORD_ILU_NIGHT_EYE, new IPerk[]{ORD_ILU_GHOST_OF_THE_TENTH_EYE});
                map.put(ORD_ILU_CROWN_OF_THE_FALSE_KING, new IPerk[]{ORD_ILU_IMPERIOUS_SPLENDOR});
                map.put(ORD_ILU_KINDRED_MAGE, new IPerk[]{ORD_ILU_FICKLE_FATE, ORD_ILU_DREAM_CHARM});
                map.put(ORD_ILU_WILTING, new IPerk[]{ORD_ILU_NEVERWORLD, ORD_ILU_TERROR, ORD_ILU_THE_REAPER_COMES});
                map.put(ORD_ILU_FICKLE_FATE, new IPerk[]{ORD_ILU_MASTER_OF_THE_MIND});
                map.put(ORD_ILU_NEMESIS, new IPerk[]{ORD_ILU_BLIND_GUARDIAN});
                map.put(ORD_ILU_TERROR, new IPerk[]{ORD_ILU_SOULCRUSHER, ORD_ILU_PANDEMONIUM});
                map.put(ORD_ILU_IMPERIOUS_SPLENDOR, new IPerk[]{ORD_ILU_PROTECT_YOUR_GOD});
                map.put(ORD_ILU_PANDEMONIUM, new IPerk[]{ORD_ILU_NIGHTFALL});
                map.put(ORD_ILU_DREAM_CHARM, new IPerk[]{ORD_ILU_DREAM_GEAS});
                map.put(ORD_ILU_THE_REAPER_COMES, new IPerk[]{ORD_ILU_LAMB_TO_THE_SLAUGHTER});
                map.put(ORD_ILU_LAMB_TO_THE_SLAUGHTER, new IPerk[]{ORD_ILU_HEAVY_WEIGHS_THE_TAPESTRY});
                map.put(ORD_ILU_BLIND_GUARDIAN, new IPerk[]{ORD_ILU_WRAITHWALKER});
                map.put(ORD_ILU_HEAVY_WEIGHS_THE_TAPESTRY, new IPerk[]{ORD_ILU_WRAITHWALKER});
                break;

            case SKILL_CONJURATION:

                map.put(ORD_CON_CONJURATION_MASTERY, new IPerk[]{ORD_CON_CONJURATION_DUAL_CASTING, ORD_CON_PLANEMELD, ORD_CON_RAVENOUS_DEAD, ORD_CON_BONE_COLLECTOR, ORD_CON_MYSTIC_BINDING, ORD_CON_RAT_KING, ORD_CON_FEED_THE_MONSTER});
                map.put(ORD_CON_BONE_COLLECTOR, new IPerk[]{ORD_CON_DEAD_TIDE});
                map.put(ORD_CON_MYSTIC_BINDING, new IPerk[]{ORD_CON_SOUL_RAIDER});
                map.put(ORD_CON_DEAD_TIDE, new IPerk[]{ORD_CON_REAP_AND_SOW, ORD_CON_BARROW_LORD});
                map.put(ORD_CON_PLANEMELD, new IPerk[]{ORD_CON_SIGNED_IN_BLOOD, ORD_CON_ATROMANCY});
                map.put(ORD_CON_RAVENOUS_DEAD, new IPerk[]{ORD_CON_EDGE_OF_OBLIVION, ORD_CON_PRESERVATION});
                map.put(ORD_CON_SOUL_RAIDER, new IPerk[]{ORD_CON_REND_FROM_THIS_WORLD});
                map.put(ORD_CON_ATROMANCY, new IPerk[]{ORD_CON_PACT_MAGIC, ORD_CON_EDGE_OF_OBLIVION, ORD_CON_ELEMENTAL_POTENCY});
                map.put(ORD_CON_BARROW_LORD, new IPerk[]{ORD_CON_SKELETON_MAGES});
                map.put(ORD_CON_PRESERVATION, new IPerk[]{ORD_CON_UNDEAD_CROWN, ORD_CON_A_PLAGUE_UPON_THEE});
                map.put(ORD_CON_REAP_AND_SOW, new IPerk[]{ORD_CON_SKELETON_MAGES});
                map.put(ORD_CON_REND_FROM_THIS_WORLD, new IPerk[]{ORD_CON_VOID_BURN});
                map.put(ORD_CON_PACT_MAGIC, new IPerk[]{ORD_CON_MAELSTROM});
                map.put(ORD_CON_SKELETON_MAGES, new IPerk[]{ORD_CON_FIRE_RITUAL, ORD_CON_CONJURE_ALTAR});
                map.put(ORD_CON_VOID_BURN, new IPerk[]{ORD_CON_HOLLOW_BINDING});
                map.put(ORD_CON_A_PLAGUE_UPON_THEE, new IPerk[]{ORD_CON_CORPSE_GAS});
                map.put(ORD_CON_CONJURE_ALTAR, new IPerk[]{ORD_CON_PUPPET_MASTER});
                map.put(ORD_CON_FIRE_RITUAL, new IPerk[]{ORD_CON_KING_OF_BONES});
                map.put(ORD_CON_HOLLOW_BINDING, new IPerk[]{ORD_CON_DARK_WHISPERS, ORD_CON_COVENANT_OF_COLDHARBOUR});
                map.put(ORD_CON_CORPSE_GAS, new IPerk[]{ORD_CON_NECROMASTER});
                map.put(ORD_CON_DARK_WHISPERS, new IPerk[]{ORD_CON_BRAND_OF_THE_NECROMANCER});
                map.put(ORD_CON_ELEMENTAL_POTENCY, new IPerk[]{ORD_CON_SUMMON_RESIST, ORD_CON_UNLEASH_HELL});
                map.put(ORD_CON_NECROMASTER, new IPerk[]{ORD_CON_MARCH_OF_OBLIVION, ORD_CON_SHOCKED_TO_LIFE});
                map.put(ORD_CON_SUMMON_RESIST, new IPerk[]{ORD_CON_MARCH_OF_OBLIVION});
                break;

            case SKILL_DESTRUCTION:

                map.put(ORD_DES_DESTRUCTION_MASTERY, new IPerk[]{ORD_DES_DESTRUCTION_DUAL_CASTING, ORD_DES_FORCE_OF_NATURE, ORD_DES_COMBUSTION, ORD_DES_MERCILESS_COLD, ORD_DES_IONIZED_PATH, ORD_DES_RUNECASTER, ORD_DES_WAR_OF_THE_ELEMENTS, ORD_DES_HARSH_LESSON});
                map.put(ORD_DES_COMBUSTION, new IPerk[]{ORD_DES_SCARRING_BURNS});
                map.put(ORD_DES_IONIZED_PATH, new IPerk[]{ORD_DES_STATIC_FIELD});
                map.put(ORD_DES_MERCILESS_COLD, new IPerk[]{ORD_DES_FROSTFALL});
                map.put(ORD_DES_FORCE_OF_NATURE, new IPerk[]{ORD_DES_ELEMENTAL_SPECIALIZATION, ORD_DES_ROBE_OF_THE_MAGI});
                map.put(ORD_DES_FROSTFALL, new IPerk[]{ORD_DES_SHATTER, ORD_DES_ICED_EARTH, ORD_DES_CRYSTALIZE});
                map.put(ORD_DES_SCARRING_BURNS, new IPerk[]{ORD_DES_FLASH_FIRE, ORD_DES_PYROMANCER_ASCENSION, ORD_DES_CONFLAGRATION});
                map.put(ORD_DES_STATIC_FIELD, new IPerk[]{ORD_DES_MAGNETIZE, ORD_DES_NOVA_CHARGE, ORD_DES_ARC_BURN});
                map.put(ORD_DES_ARC_BURN, new IPerk[]{ORD_DES_ELECTROCONVULSIONS});
                map.put(ORD_DES_CONFLAGRATION, new IPerk[]{ORD_DES_SCORCHED_EARTH});
                map.put(ORD_DES_CRYSTALIZE, new IPerk[]{ORD_DES_HYPOTHERMIA});
                map.put(ORD_DES_RUNECASTER, new IPerk[]{ORD_DES_ANCIENT_SEALS});
                map.put(ORD_DES_FLASH_FIRE, new IPerk[]{ORD_DES_SCORCHED_EARTH});
                map.put(ORD_DES_MAGNETIZE, new IPerk[]{ORD_DES_ELECTROCONVULSIONS});
                map.put(ORD_DES_SHATTER, new IPerk[]{ORD_DES_HYPOTHERMIA});
                map.put(ORD_DES_ELECTROCONVULSIONS, new IPerk[]{ORD_DES_SHOW_THEM_ALL, ORD_DES_STORMBLAST});
                map.put(ORD_DES_HYPOTHERMIA, new IPerk[]{ORD_DES_EXHAUST, ORD_DES_WINTERS_MAJESTY});
                map.put(ORD_DES_SCORCHED_EARTH, new IPerk[]{ORD_DES_WORLD_IN_FLAMES, ORD_DES_OUTBURST});
                map.put(ORD_DES_EXHAUST, new IPerk[]{ORD_DES_GLACIAL_PRISON});
                map.put(ORD_DES_SHOW_THEM_ALL, new IPerk[]{ORD_DES_ABSOLUTE_POWER});
                map.put(ORD_DES_WORLD_IN_FLAMES, new IPerk[]{ORD_DES_CATACLYSM});
                map.put(ORD_DES_OUTBURST, new IPerk[]{ORD_DES_CATACLYSM});
                map.put(ORD_DES_STORMBLAST, new IPerk[]{ORD_DES_ABSOLUTE_POWER});
                map.put(ORD_DES_WINTERS_MAJESTY, new IPerk[]{ORD_DES_GLACIAL_PRISON});
                break;

            case SKILL_SMITHING:

                map.put(ORD_SMT_SMITHING_MASTERY, new IPerk[]{ORD_SMT_MERIC_SMITHING, ORD_SMT_DWARVEN_AUTOCANNON, ORD_SMT_ARCANE_BLACKSMITH, ORD_SMT_ADVANCED_WORKSHOP});
                map.put(ORD_SMT_DWARVEN_AUTOCANNON, new IPerk[]{ORD_SMT_REMOTE_CONTROL, ORD_SMT_ELECTROBOLT});
                map.put(ORD_SMT_ADVANCED_WORKSHOP, new IPerk[]{ORD_SMT_RECYCLE_MATERIALS, ORD_SMT_SMITHING_SPECIALIZATION});
                map.put(ORD_SMT_MERIC_SMITHING, new IPerk[]{ORD_SMT_EXPERT_SMITHING});
                map.put(ORD_SMT_ELECTROBOLT, new IPerk[]{ORD_SMT_FIRING_LINE, ORD_SMT_SPIN_UP});
                map.put(ORD_SMT_RECYCLE_MATERIALS, new IPerk[]{ORD_SMT_SANDSTONE_SHEATH, ORD_SMT_FUEL_THE_INFERNO});
                map.put(ORD_SMT_EXPERT_SMITHING, new IPerk[]{ORD_SMT_EXOTIC_SMITHING});
                map.put(ORD_SMT_SMITHING_SPECIALIZATION, new IPerk[]{ORD_SMT_IRON_LORE, ORD_SMT_FUEL_THE_INFERNO});
                map.put(ORD_SMT_EXOTIC_SMITHING, new IPerk[]{ORD_SMT_PLANAR_SMITHING});
                map.put(ORD_SMT_PLANAR_SMITHING, new IPerk[]{ORD_SMT_HEART_OF_CREATION});
                map.put(ORD_SMT_FUEL_THE_INFERNO, new IPerk[]{ORD_SMT_HEART_OF_CREATION});
                break;

            case SKILL_ALTERATION:

                map.put(ORD_ALT_ALTERATION_MASTERY, new IPerk[]{ORD_ALT_ALTERATION_DUAL_CASTING, ORD_ALT_GEOMANCER, ORD_ALT_PHILOSOPHERS_STONE, ORD_ALT_ALTER_SELF_RESISTANCES, ORD_ALT_SPELLBLADE, ORD_ALT_VANCIAN_MAGIC, ORD_ALT_WILD_SHRINES, ORD_ALT_MAGE_ARMOR});
                map.put(ORD_ALT_MAGE_ARMOR, new IPerk[]{ORD_ALT_DISTORTED_SHAPE, ORD_ALT_ENERGY_SHIELD});
                map.put(ORD_ALT_GEOMANCER, new IPerk[]{ORD_ALT_THRONE_OF_NIRN});
                map.put(ORD_ALT_PHILOSOPHERS_STONE, new IPerk[]{ORD_ALT_COMMAND_LOCK});
                map.put(ORD_ALT_VANCIAN_MAGIC, new IPerk[]{ORD_ALT_QUADRATIC_WIZARD});
                map.put(ORD_ALT_WILD_SHRINES, new IPerk[]{ORD_ALT_WELLOCS_DORMANT, ORD_ALT_INTUITIVE_MAGIC});
                map.put(ORD_ALT_ALTER_SELF_RESISTANCES, new IPerk[]{ORD_ALT_ALTER_SELF_ATTRIBUTES, ORD_ALT_HOME_MYTHAL});
                map.put(ORD_ALT_SPELLBLADE, new IPerk[]{ORD_ALT_ENERGY_ROIL});
                map.put(ORD_ALT_COMMAND_LOCK, new IPerk[]{ORD_ALT_HOME_MYTHAL, ORD_ALT_AURIFICATION});
                map.put(ORD_ALT_INTUITIVE_MAGIC, new IPerk[]{ORD_ALT_THE_MONARCH, ORD_ALT_ARCANE_THESIS});
                map.put(ORD_ALT_QUADRATIC_WIZARD, new IPerk[]{ORD_ALT_DUNGEON_MASTER});
                map.put(ORD_ALT_ALTER_SELF_ATTRIBUTES, new IPerk[]{ORD_ALT_NULLIFIER});
                map.put(ORD_ALT_HOME_MYTHAL, new IPerk[]{ORD_ALT_DIMENSION_DOOR, ORD_ALT_EMERGENCY_TELEPORT});
                map.put(ORD_ALT_DUNGEON_MASTER, new IPerk[]{ORD_ALT_ARCANE_THESIS});
                map.put(ORD_ALT_ENERGY_ROIL, new IPerk[]{ORD_ALT_REND_RESISTANCES});
                break;

            case SKILL_ARCHERY:

                map.put(ORD_ARC_ARCHERY_MASTERY, new IPerk[]{ORD_ARC_LONG_SHOT, ORD_ARC_CRIPPLING_SHOT, ORD_ARC_CLEAN_KILL, ORD_ARC_STEADY_HAND, ORD_ARC_WINGSTRIKE});
                map.put(ORD_ARC_CLEAN_KILL, new IPerk[]{ORD_ARC_SNIPE, ORD_ARC_QUICK_SHOT, ORD_ARC_RANGER});
                map.put(ORD_ARC_LONG_SHOT, new IPerk[]{ORD_ARC_THREAD_THE_NEEDLE});
                map.put(ORD_ARC_STEADY_HAND, new IPerk[]{ORD_ARC_HUNTERS_DISCIPLINE});
                map.put(ORD_ARC_CRIPPLING_SHOT, new IPerk[]{ORD_ARC_PINNING_SHOT});
                map.put(ORD_ARC_HUNTERS_DISCIPLINE, new IPerk[]{ORD_ARC_HUNT_TOGETHER, ORD_ARC_TRICK_ARROWS});
                map.put(ORD_ARC_THREAD_THE_NEEDLE, new IPerk[]{ORD_ARC_AMBUSH_PREDATOR});
                map.put(ORD_ARC_PINNING_SHOT, new IPerk[]{ORD_ARC_BEAK_AND_TALON});
                map.put(ORD_ARC_RANGER, new IPerk[]{ORD_ARC_FOCUS_ON_THE_PREY});
                map.put(ORD_ARC_FOCUS_ON_THE_PREY, new IPerk[]{ORD_ARC_LIONS_ARROW});
                map.put(ORD_ARC_QUICK_SHOT, new IPerk[]{ORD_ARC_HAWKEYE, ORD_ARC_HAILSTORM});
                map.put(ORD_ARC_SNIPE, new IPerk[]{ORD_ARC_HAWKEYE});
                map.put(ORD_ARC_AMBUSH_PREDATOR, new IPerk[]{ORD_ARC_THREE_CROWS});
                map.put(ORD_ARC_BEAK_AND_TALON, new IPerk[]{ORD_ARC_PERFECT_AIM});
                map.put(ORD_ARC_THREE_CROWS, new IPerk[]{ORD_ARC_PERFECT_AIM});
                break;

            case SKILL_BLOCK:

                map.put(ORD_BLC_BLOCK_MASTERY, new IPerk[]{ORD_BLC_TIMED_BLOCK, ORD_BLC_QUICK_REFLEXES, ORD_BLC_DEFLECT_ARROWS, ORD_BLC_POWER_BASH});
                map.put(ORD_BLC_TIMED_BLOCK, new IPerk[]{ORD_BLC_BLOCK_RUNNER, ORD_BLC_POKE_THE_DRAGON});
                map.put(ORD_BLC_DEFLECT_ARROWS, new IPerk[]{ORD_BLC_DOMINION});
                map.put(ORD_BLC_POKE_THE_DRAGON, new IPerk[]{ORD_BLC_APOCALYPSE_PROOF});
                map.put(ORD_BLC_APOCALYPSE_PROOF, new IPerk[]{ORD_BLC_TIMING_STREAK, ORD_BLC_DRAGON_TAIL});
                map.put(ORD_BLC_POWER_BASH, new IPerk[]{ORD_BLC_SKULL_RATTLERB});
                map.put(ORD_BLC_SKULL_RATTLERB, new IPerk[]{ORD_BLC_MOCKING_BLOW, ORD_BLC_CAST_ASIDE});
                map.put(ORD_BLC_DRAGON_TAIL, new IPerk[]{ORD_BLC_DRAGON_SCALES});
                map.put(ORD_BLC_TIMING_STREAK, new IPerk[]{ORD_BLC_BREAK_THEIR_TEETH});
                map.put(ORD_BLC_BLOCK_RUNNER, new IPerk[]{ORD_BLC_HOLD_THE_LINE});
                map.put(ORD_BLC_BREAK_THEIR_TEETH, new IPerk[]{ORD_BLC_DELIVERANCE});
                map.put(ORD_BLC_CAST_ASIDE, new IPerk[]{ORD_BLC_UNSTOPPABLE_FORCE});
                map.put(ORD_BLC_DELIVERANCE, new IPerk[]{ORD_BLC_DRAGON_SCALES});
                break;

            case SKILL_ENCHANTING:

                map.put(ORD_ENC_ENCHANTING_MASTERY, new IPerk[]{ORD_ENC_LAST_WORD, ORD_ENC_STAFF_CHANNELER, ORD_ENC_GEM_DUST, ORD_ENC_SOUL_SIPHON});
                map.put(ORD_ENC_LAST_WORD, new IPerk[]{ORD_ENC_SECRETKEEPER});
                map.put(ORD_ENC_SOUL_SIPHON, new IPerk[]{ORD_ENC_THUNDERSTRUCK, ORD_ENC_SPELLSCRIBE});
                map.put(ORD_ENC_STAFF_CHANNELER, new IPerk[]{ORD_ENC_SECRETKEEPER, ORD_ENC_STAFF_RECHARGE});
                map.put(ORD_ENC_GEM_DUST, new IPerk[]{ORD_ENC_PRESERVER, ORD_ENC_REGALIA});
                map.put(ORD_ENC_SECRETKEEPER, new IPerk[]{ORD_ENC_FLAME_OF_MAGNUS, ORD_ENC_STAFF_RECHARGE});
                map.put(ORD_ENC_SPELLSCRIBE, new IPerk[]{ORD_ENC_MIGHT_AND_MAGIC, ORD_ENC_POWER_ECHOES});
                map.put(ORD_ENC_FLAME_OF_MAGNUS, new IPerk[]{ORD_ENC_YOU_SHALL_NOT_PASS});
                map.put(ORD_ENC_REGALIA, new IPerk[]{ORD_ENC_ATTUNEMENT, ORD_ENC_TWIN_ENCHANTMENT});
                map.put(ORD_ENC_STAFF_RECHARGE, new IPerk[]{ORD_ENC_CHARGE_TAP, ORD_ENC_ATTUNEMENT});
                map.put(ORD_ENC_ATTUNEMENT, new IPerk[]{ORD_ENC_HEART_OF_THE_SUN});
                map.put(ORD_ENC_CHARGE_TAP, new IPerk[]{ORD_ENC_YOU_SHALL_NOT_PASS, ORD_ENC_HEART_OF_THE_SUN});
                map.put(ORD_ENC_TWIN_ENCHANTMENT, new IPerk[]{ORD_ENC_ARCANE_NEXUS});
                map.put(ORD_ENC_ARCANE_NEXUS, new IPerk[]{ORD_ENC_MIRACLE});
                break;

            case SKILL_LIGHT_ARMOR:

                map.put(ORD_LAR_LIGHT_ARMOR_MASTERY, new IPerk[]{ORD_LAR_ANNOYING_MOSQUITOES, ORD_LAR_IRON_FIST, ORD_LAR_LIGHT_ARMOR_FIT, ORD_LAR_AS_A_LEAF});
                map.put(ORD_LAR_IRON_FIST, new IPerk[]{ORD_LAR_SWEEPING_WIND});
                map.put(ORD_LAR_LIGHT_ARMOR_FIT, new IPerk[]{ORD_LAR_UNHINDERED, ORD_LAR_INITIATIVE, ORD_LAR_EVASIVE_LEAP, ORD_LAR_KEEN_SENSES});
                map.put(ORD_LAR_INITIATIVE, new IPerk[]{ORD_LAR_LIGHTNING_STRIKE, ORD_LAR_FIGHT_OR_FLIGHT, ORD_LAR_WINDRUNNER});
                map.put(ORD_LAR_SWEEPING_WIND, new IPerk[]{ORD_LAR_RUSHING_TIDE, ORD_LAR_UNHINDERED});
                map.put(ORD_LAR_RUSHING_TIDE, new IPerk[]{ORD_LAR_HISSING_DRAGON, ORD_LAR_BREAKING_WAVES});
                map.put(ORD_LAR_UNHINDERED, new IPerk[]{ORD_LAR_INTO_THE_MAELSTROM});
                map.put(ORD_LAR_WINDRUNNER, new IPerk[]{ORD_LAR_WARDANCER});
                map.put(ORD_LAR_EVASIVE_LEAP, new IPerk[]{ORD_LAR_WILD_AND_FREE});
                map.put(ORD_LAR_FIGHT_OR_FLIGHT, new IPerk[]{ORD_LAR_SURVIVAL_INSTINCT});
                map.put(ORD_LAR_WARDANCER, new IPerk[]{ORD_LAR_GLANCING_BLOWS, ORD_LAR_SPELLDANCER});
                map.put(ORD_LAR_SURVIVAL_INSTINCT, new IPerk[]{ORD_LAR_TEMPTING_FATE});
                map.put(ORD_LAR_GLANCING_BLOWS, new IPerk[]{ORD_LAR_TEMPTING_FATE});
                break;

            case SKILL_ONE_HANDED:

                map.put(ORD_ONH_ONE_HANDED_MASTERY, new IPerk[]{ORD_ONH_FURIOUS_STRENGTH, ORD_ONH_DISCIPLINED_FIGHTER, ORD_ONH_BLEED_LIKE_A_LAMB, ORD_ONH_DENTING_BLOWS, ORD_ONH_CLASH_OF_CHAMPIONS, ORD_ONH_BITE_MARKS, ORD_ONH_RAVAGE});
                map.put(ORD_ONH_DISCIPLINED_FIGHTER, new IPerk[]{ORD_ONH_ROGUES_PARRY, ORD_ONH_THUNDERING_BLOW});
                map.put(ORD_ONH_BITE_MARKS, new IPerk[]{ORD_ONH_SAVAGE});
                map.put(ORD_ONH_BLEED_LIKE_A_LAMB, new IPerk[]{ORD_ONH_MANGLE});
                map.put(ORD_ONH_CLASH_OF_CHAMPIONS, new IPerk[]{ORD_ONH_CROSS_CUT});
                map.put(ORD_ONH_DENTING_BLOWS, new IPerk[]{ORD_ONH_SMITE});
                map.put(ORD_ONH_RAVAGE, new IPerk[]{ORD_ONH_MAN_OWAR});
                map.put(ORD_ONH_CROSS_CUT, new IPerk[]{ORD_ONH_FALLING_SWORD});
                map.put(ORD_ONH_FURIOUS_STRENGTH, new IPerk[]{ORD_ONH_OVERRUN});
                map.put(ORD_ONH_MANGLE, new IPerk[]{ORD_ONH_SHIELDBITER});
                map.put(ORD_ONH_SAVAGE, new IPerk[]{ORD_ONH_TWIN_FANG});
                map.put(ORD_ONH_SMITE, new IPerk[]{ORD_ONH_RISE_KINSMEN});
                map.put(ORD_ONH_FALLING_SWORD, new IPerk[]{ORD_ONH_WINDSWEPT});
                map.put(ORD_ONH_RISE_KINSMEN, new IPerk[]{ORD_ONH_TOLL_THE_BELL});
                map.put(ORD_ONH_SHIELDBITER, new IPerk[]{ORD_ONH_WOLFSTOOTH});
                map.put(ORD_ONH_TWIN_FANG, new IPerk[]{ORD_ONH_SWAYING_COBRA});
                map.put(ORD_ONH_MAN_OWAR, new IPerk[]{ORD_ONH_UNLEASH_THE_BEAST});
                map.put(ORD_ONH_SWAYING_COBRA, new IPerk[]{ORD_ONH_DEATH_ADDER});
                map.put(ORD_ONH_TOLL_THE_BELL, new IPerk[]{ORD_ONH_METEOR_STORM});
                map.put(ORD_ONH_WINDSWEPT, new IPerk[]{ORD_ONH_INTO_THE_DUST});
                map.put(ORD_ONH_WOLFSTOOTH, new IPerk[]{ORD_ONH_GO_FOR_THE_THROAT});
                map.put(ORD_ONH_DEATH_ADDER, new IPerk[]{ORD_ONH_COILING_PYTHON});
                map.put(ORD_ONH_GO_FOR_THE_THROAT, new IPerk[]{ORD_ONH_APEX_PREDATOR});
                map.put(ORD_ONH_INTO_THE_DUST, new IPerk[]{ORD_ONH_JUDGMENT});
                map.put(ORD_ONH_METEOR_STORM, new IPerk[]{ORD_ONH_SKULL_CRACK});
                map.put(ORD_ONH_THUNDERING_BLOW, new IPerk[]{ORD_ONH_AFTERSHOCK});
                map.put(ORD_ONH_APEX_PREDATOR, new IPerk[]{ORD_ONH_WANDERING_WARRIOR});
                map.put(ORD_ONH_COILING_PYTHON, new IPerk[]{ORD_ONH_WANDERING_WARRIOR});
                map.put(ORD_ONH_JUDGMENT, new IPerk[]{ORD_ONH_WANDERING_WARRIOR});
                map.put(ORD_ONH_SKULL_CRACK, new IPerk[]{ORD_ONH_WANDERING_WARRIOR});
                break;

            case SKILL_PICKPOCKET:

                map.put(ORD_PCK_PICKPOCKET_MASTERY, new IPerk[]{ORD_PCK_TRAINED_RABBIT, ORD_PCK_CUTPURSE, ORD_PCK_THIEFS_EYE, ORD_PCK_BLOOD_MONEY, ORD_PCK_DEATHS_EMPEROR});
                map.put(ORD_PCK_CUTPURSE, new IPerk[]{ORD_PCK_BROTHERHOOD_COCKTAIL, ORD_PCK_ON_THE_RUN, ORD_PCK_LAWLESS_WORLD});
                map.put(ORD_PCK_THIEFS_EYE, new IPerk[]{ORD_PCK_THIEFS_LUCK});
                map.put(ORD_PCK_BROTHERHOOD_COCKTAIL, new IPerk[]{ORD_PCK_TRICKSTER});
                map.put(ORD_PCK_DEATHS_EMPEROR, new IPerk[]{ORD_PCK_DOOMED_TO_PLUNDER});
                map.put(ORD_PCK_ON_THE_RUN, new IPerk[]{ORD_PCK_STALK_THE_PREY});
                map.put(ORD_PCK_LAWLESS_WORLD, new IPerk[]{ORD_PCK_STALK_THE_PREY});
                map.put(ORD_PCK_THIEFS_LUCK, new IPerk[]{ORD_PCK_CRIME_WAVE});
                map.put(ORD_PCK_STALK_THE_PREY, new IPerk[]{ORD_PCK_YOU_SAW_NOTHING, ORD_PCK_DRAGON_HOARD});
                map.put(ORD_PCK_DOOMED_TO_PLUNDER, new IPerk[]{ORD_PCK_DRAGON_HOARD, ORD_PCK_MUTINY});
                map.put(ORD_PCK_CRIME_WAVE, new IPerk[]{ORD_PCK_DRAGON_HOARD});
                map.put(ORD_PCK_YOU_SAW_NOTHING, new IPerk[]{ORD_PCK_ROBBED_BLIND});
                break;

            case SKILL_RESTORATION:

                map.put(ORD_RST_RESTORATION_MASTERY, new IPerk[]{ORD_RST_TOME_OF_MANY_PAGES, ORD_RST_EDGEWALKER, ORD_RST_DESCENDING_LIGHT, ORD_RST_SPIRIT_TUTORS, ORD_RST_RESTORATION_DUAL_CASTING});
                map.put(ORD_RST_DESCENDING_LIGHT, new IPerk[]{ORD_RST_VIGILANT, ORD_RST_WARRIORS_FLAME, ORD_RST_HALLOWED_BURIAL, ORD_RST_OVERFLOWING_CUP});
                map.put(ORD_RST_EDGEWALKER, new IPerk[]{ORD_RST_RESPITE, ORD_RST_FALSE_LIGHT, ORD_RST_NECROMANTICON});
                map.put(ORD_RST_HALLOWED_BURIAL, new IPerk[]{ORD_RST_EXORCIST});
                map.put(ORD_RST_SPIRIT_TUTORS, new IPerk[]{ORD_RST_SACRED_GUARDIAN, ORD_RST_PILGRIM, ORD_RST_WHEEL_OF_LIFE});
                map.put(ORD_RST_VIGILANT, new IPerk[]{ORD_RST_ANTIMAGIC_FIELD, ORD_RST_FORBIDDEN_SANCTUARY});
                map.put(ORD_RST_NECROMANTICON, new IPerk[]{ORD_RST_CHALICE_OF_TEARS, ORD_RST_PLAGUE_DOCTOR});
                map.put(ORD_RST_EXORCIST, new IPerk[]{ORD_RST_CRUSADERS_FIRE});
                map.put(ORD_RST_FALSE_LIGHT, new IPerk[]{ORD_RST_LIGHTWIELDER});
                map.put(ORD_RST_OVERFLOWING_CUP, new IPerk[]{ORD_RST_WHEEL_OF_LIFE, ORD_RST_UNDER_MY_WINGS});
                map.put(ORD_RST_PILGRIM, new IPerk[]{ORD_RST_GODS_AND_MORTALS});
                map.put(ORD_RST_WARRIORS_FLAME, new IPerk[]{ORD_RST_FORBIDDEN_SANCTUARY, ORD_RST_SACRED_FLAME, ORD_RST_ASHES_TO_ASHES});
                map.put(ORD_RST_ASHES_TO_ASHES, new IPerk[]{ORD_RST_BATTLE_CLERIC});
                map.put(ORD_RST_FORBIDDEN_SANCTUARY, new IPerk[]{ORD_RST_BASTION_WARD});
                map.put(ORD_RST_LIGHTWIELDER, new IPerk[]{ORD_RST_IN_THY_NAME});
                map.put(ORD_RST_SACRED_FLAME, new IPerk[]{ORD_RST_BATTLE_CLERIC});
                map.put(ORD_RST_BASTION_WARD, new IPerk[]{ORD_RST_MAGE_WARD});
                map.put(ORD_RST_WHEEL_OF_LIFE, new IPerk[]{ORD_RST_ENDURING_IDEAL});
                map.put(ORD_RST_BATTLE_CLERIC, new IPerk[]{ORD_RST_ETERNAL_FLAME, ORD_RST_APOTHEOSIS});
                break;

            case SKILL_SNEAK:

                map.put(ORD_SNK_SNEAK_MASTERY, new IPerk[]{ORD_SNK_SPOT_DETECTION, ORD_SNK_TRIPWIRE, ORD_SNK_SILENT_ROLL, ORD_SNK_FOG_OF_WAR, ORD_SNK_INFILTRATOR, ORD_SNK_SNEAK_ATTACK, ORD_SNK_DEMOLITION_JOB});
                map.put(ORD_SNK_SNEAK_ATTACK, new IPerk[]{ORD_SNK_PROBLEM_SOLVER, ORD_SNK_ASSASSINS_BLADE});
                map.put(ORD_SNK_TRIPWIRE, new IPerk[]{ORD_SNK_WHIPLASH});
                map.put(ORD_SNK_FOG_OF_WAR, new IPerk[]{ORD_SNK_RIGHT_BEHIND_YOU});
                map.put(ORD_SNK_SILENT_ROLL, new IPerk[]{ORD_SNK_DYNAMIC_ENTRY});
                map.put(ORD_SNK_SPOT_DETECTION, new IPerk[]{ORD_SNK_LIGHT_FOOT});
                map.put(ORD_SNK_ASSASSINS_BLADE, new IPerk[]{ORD_SNK_BACKSTAB});
                map.put(ORD_SNK_DYNAMIC_ENTRY, new IPerk[]{ORD_SNK_DODGE_ROLL});
                map.put(ORD_SNK_INFILTRATOR, new IPerk[]{ORD_SNK_RIGHT_BEHIND_YOU});
                map.put(ORD_SNK_WHIPLASH, new IPerk[]{ORD_SNK_BACKUP_PLAN});
                map.put(ORD_SNK_RIGHT_BEHIND_YOU, new IPerk[]{ORD_SNK_DISENGAGE, ORD_SNK_BEHIND_ENEMY_LINES, ORD_SNK_SMOKESCREEN, ORD_SNK_CLEAN_ESCAPE});
                map.put(ORD_SNK_DODGE_ROLL, new IPerk[]{ORD_SNK_BACKUP_PLAN, ORD_SNK_GREASED_LIGHTNING, ORD_SNK_BEHIND_ENEMY_LINES});
                map.put(ORD_SNK_SMOKESCREEN, new IPerk[]{ORD_SNK_PARTYSTARTER});
                map.put(ORD_SNK_BEHIND_ENEMY_LINES, new IPerk[]{ORD_SNK_SHADOW_WARRIOR});
                map.put(ORD_SNK_PROBLEM_SOLVER, new IPerk[]{ORD_SNK_LAUGHING_GHOST, ORD_SNK_CLOAK_AND_DAGGER});
                break;

            case SKILL_SPEECH:

                map.put(ORD_SPC_SPEECH_MASTERY, new IPerk[]{ORD_SPC_BRIBERY, ORD_SPC_KINSHIP, ORD_SPC_PERFORMER, ORD_SPC_AND_THE_UNIVERSE_LISTENS, ORD_SPC_SPEAK_WITH_ANIMALS});
                map.put(ORD_SPC_AND_THE_UNIVERSE_LISTENS, new IPerk[]{ORD_SPC_WINDBORNE});
                map.put(ORD_SPC_PERFORMER, new IPerk[]{ORD_SPC_IRRESISTIBLE_DANCE, ORD_SPC_SERENADE});
                map.put(ORD_SPC_KINSHIP, new IPerk[]{ORD_SPC_SALESMAN, ORD_SPC_BUSINESS_RELATION});
                map.put(ORD_SPC_IRRESISTIBLE_DANCE, new IPerk[]{ORD_SPC_GOLDEN_FIDDLE, ORD_SPC_ENCORE, ORD_SPC_LORD_OF_THE_DANCE});
                map.put(ORD_SPC_BUSINESS_RELATION, new IPerk[]{ORD_SPC_INVESTOR});
                map.put(ORD_SPC_ENCORE, new IPerk[]{ORD_SPC_EARTHQUAKE_DRUM});
                map.put(ORD_SPC_GOLDEN_FIDDLE, new IPerk[]{ORD_SPC_EARTHQUAKE_DRUM});
                map.put(ORD_SPC_WINDBORNE, new IPerk[]{ORD_SPC_FORCE_REDOUBLED, ORD_SPC_HURRICANE_FORCE});
                map.put(ORD_SPC_FORCE_REDOUBLED, new IPerk[]{ORD_SPC_THUUM_OF_WAR, ORD_SPC_MERCILESS_STORM});
                map.put(ORD_SPC_SALESMAN, new IPerk[]{ORD_SPC_INVESTOR});
                map.put(ORD_SPC_SPEAK_WITH_ANIMALS, new IPerk[]{ORD_SPC_HORN_OF_SOVNGARDE, ORD_SPC_GIFT_OF_KYNARETH});
                map.put(ORD_SPC_EARTHQUAKE_DRUM, new IPerk[]{ORD_SPC_WITCHING_RHYTHM});
                map.put(ORD_SPC_INVESTOR, new IPerk[]{ORD_SPC_FENCE});
                map.put(ORD_SPC_FENCE, new IPerk[]{ORD_SPC_TRADE_PRINCE});
                map.put(ORD_SPC_MERCILESS_STORM, new IPerk[]{ORD_SPC_DOVAHZULAAN});
                map.put(ORD_SPC_WITCHING_RHYTHM, new IPerk[]{ORD_SPC_WAR_DRUMMER});
                break;

            case SKILL_TWO_HANDED:

                map.put(ORD_TWH_TWO_HANDED_MASTERY, new IPerk[]{ORD_TWH_TRAINED_FIGHTER, ORD_TWH_BLEED_LIKE_A_DOG, ORD_TWH_CRUSHING_BLOWS, ORD_TWH_CLASH_OF_HEROES, ORD_TWH_FEROCIOUS_STRENGTH});
                map.put(ORD_TWH_TRAINED_FIGHTER, new IPerk[]{ORD_TWH_DEATH_OR_GLORY});
                map.put(ORD_TWH_BLEED_LIKE_A_DOG, new IPerk[]{ORD_TWH_RIVE});
                map.put(ORD_TWH_CLASH_OF_HEROES, new IPerk[]{ORD_TWH_MAUL});
                map.put(ORD_TWH_CRUSHING_BLOWS, new IPerk[]{ORD_TWH_BATTER});
                map.put(ORD_TWH_BATTER, new IPerk[]{ORD_TWH_AVALANCHE});
                map.put(ORD_TWH_FEROCIOUS_STRENGTH, new IPerk[]{ORD_TWH_TRAMPLE});
                map.put(ORD_TWH_MAUL, new IPerk[]{ORD_TWH_BREACH_THE_WALL});
                map.put(ORD_TWH_RIVE, new IPerk[]{ORD_TWH_EXECUTE});
                map.put(ORD_TWH_AVALANCHE, new IPerk[]{ORD_TWH_THE_PENDULUM});
                map.put(ORD_TWH_BREACH_THE_WALL, new IPerk[]{ORD_TWH_SUBJUGATE});
                map.put(ORD_TWH_EXECUTE, new IPerk[]{ORD_TWH_DECIMATE});
                map.put(ORD_TWH_TRAMPLE, new IPerk[]{ORD_TWH_MASSACRE, ORD_TWH_BEAR_HIDE});
                map.put(ORD_TWH_DEATH_OR_GLORY, new IPerk[]{ORD_TWH_WOLFKIN});
                map.put(ORD_TWH_DECIMATE, new IPerk[]{ORD_TWH_BISECT});
                map.put(ORD_TWH_SUBJUGATE, new IPerk[]{ORD_TWH_HUMILIATE});
                map.put(ORD_TWH_THE_PENDULUM, new IPerk[]{ORD_TWH_GRAND_SLAM});
                map.put(ORD_TWH_BISECT, new IPerk[]{ORD_TWH_RAMS_HEAD});
                map.put(ORD_TWH_GRAND_SLAM, new IPerk[]{ORD_TWH_DEADFALL});
                map.put(ORD_TWH_HUMILIATE, new IPerk[]{ORD_TWH_OVERTHROW});
                map.put(ORD_TWH_MASSACRE, new IPerk[]{ORD_TWH_VOICE_OF_RAGE_AND_RUIN, ORD_TWH_ENTER_THE_ARENA});
                map.put(ORD_TWH_DEADFALL, new IPerk[]{ORD_TWH_SLAYER_OF_A_THOUSAND});
                map.put(ORD_TWH_OVERTHROW, new IPerk[]{ORD_TWH_SLAYER_OF_A_THOUSAND});
                map.put(ORD_TWH_RAMS_HEAD, new IPerk[]{ORD_TWH_SLAYER_OF_A_THOUSAND});
                break;
        }

        return map;
    }

    private HashMap<IPerk, IPerk[]> getVanillaConnections(){

        HashMap<IPerk, IPerk[]> map = new HashMap<>();

        switch (this) {

            case SKILL_HEAVY_ARMOR:

               map.put(VAN_HAR_JUGGERNAUT             , new IPerk[]{VAN_HAR_FISTS_OF_STEEL, VAN_HAR_WELL_FITTED});
               map.put(VAN_HAR_FISTS_OF_STEEL         , new IPerk[]{VAN_HAR_CUSHIONED});
               map.put(VAN_HAR_CUSHIONED              , new IPerk[]{VAN_HAR_CONDITIONING});
               map.put(VAN_HAR_WELL_FITTED            , new IPerk[]{VAN_HAR_TOWER_OF_STRENGTH});
               map.put(VAN_HAR_TOWER_OF_STRENGTH      , new IPerk[]{VAN_HAR_MATCHING_SET});
               map.put(VAN_HAR_MATCHING_SET           , new IPerk[]{VAN_HAR_REFLECT_BLOWS});

               break;

            case SKILL_LOCKPICKING:

               map.put(VAN_LCK_NOVICE_LOCKS           ,new IPerk[]{VAN_LCK_APPRENTICE_LOCKS});
               map.put(VAN_LCK_APPRENTICE_LOCKS       ,new IPerk[]{VAN_LCK_ADEPT_LOCKS, VAN_LCK_QUICK_HANDS});
               map.put(VAN_LCK_QUICK_HANDS            ,new IPerk[]{VAN_LCK_WAX_KEY});
               map.put(VAN_LCK_ADEPT_LOCKS            ,new IPerk[]{VAN_LCK_EXPERT_LOCKS, VAN_LCK_GOLDEN_TOUCH});
               map.put(VAN_LCK_EXPERT_LOCKS           ,new IPerk[]{VAN_LCK_MASTER_LOCKS, VAN_LCK_LOCKSMITH});
               map.put(VAN_LCK_GOLDEN_TOUCH           ,new IPerk[]{VAN_LCK_TREASURE_HUNTER});
               map.put(VAN_LCK_LOCKSMITH              ,new IPerk[]{VAN_LCK_UNBREAKABLE});

               break;

            case SKILL_ALCHEMY:

               map.put(VAN_ALC_ALCHEMIST              ,new IPerk[]{VAN_ALC_PHYSICIAN});
               map.put(VAN_ALC_PHYSICIAN              ,new IPerk[]{VAN_ALC_BENEFACTOR, VAN_ALC_POISONER});
               map.put(VAN_ALC_BENEFACTOR             ,new IPerk[]{VAN_ALC_EXPERIMENTER});
               map.put(VAN_ALC_EXPERIMENTER           ,new IPerk[]{VAN_ALC_SNAKEBLOOD});
               map.put(VAN_ALC_POISONER               ,new IPerk[]{VAN_ALC_CONCENTRATED_POISON});
               map.put(VAN_ALC_CONCENTRATED_POISON    ,new IPerk[]{VAN_ALC_GREEN_THUMB, VAN_ALC_SNAKEBLOOD});
               map.put(VAN_ALC_SNAKEBLOOD             ,new IPerk[]{VAN_ALC_PURITY});

               break;

            case SKILL_ILLUSION:

               map.put(VAN_ILU_NOVICE_ILLUSION        ,new IPerk[]{VAN_ILU_ILLUSION_DUAL_CASTING, VAN_ILU_APPRENTICE_ILLUSION, VAN_ILU_HYPNOTIC_GAZE, VAN_ILU_ANIMAGE});
               map.put(VAN_ILU_ANIMAGE                ,new IPerk[]{VAN_ILU_KINDRED_MAGE});
               map.put(VAN_ILU_KINDRED_MAGE           ,new IPerk[]{VAN_ILU_QUIET_CASTING});
               map.put(VAN_ILU_QUIET_CASTING          ,new IPerk[]{VAN_ILU_MASTER_OF_THE_MIND});
               map.put(VAN_ILU_APPRENTICE_ILLUSION    ,new IPerk[]{VAN_ILU_ADEPT_ILLUSION});
               map.put(VAN_ILU_ADEPT_ILLUSION         ,new IPerk[]{VAN_ILU_EXPERT_ILLUSION});
               map.put(VAN_ILU_EXPERT_ILLUSION        ,new IPerk[]{VAN_ILU_MASTER_ILLUSION});
               map.put(VAN_ILU_HYPNOTIC_GAZE          ,new IPerk[]{VAN_ILU_ASPECT_OF_TERROR});
               map.put(VAN_ILU_ASPECT_OF_TERROR       ,new IPerk[]{VAN_ILU_RAGE});
               map.put(VAN_ILU_RAGE                   ,new IPerk[]{VAN_ILU_MASTER_OF_THE_MIND});

               break;

            case SKILL_CONJURATION:

               map.put(VAN_CON_NOVICE_CONJURATION     ,new IPerk[]{VAN_CON_NECROMANCY, VAN_CON_MYSTIC_BINDING, VAN_CON_APPRENTICE_CONJURATION, VAN_CON_SUMMONER, VAN_CON_CONJURATION_DUAL_CASTING});
               map.put(VAN_CON_APPRENTICE_CONJURATION ,new IPerk[]{VAN_CON_ADEPT_CONJURATION});
               map.put(VAN_CON_ADEPT_CONJURATION      ,new IPerk[]{VAN_CON_EXPERT_CONJURATION});
               map.put(VAN_CON_EXPERT_CONJURATION     ,new IPerk[]{VAN_CON_MASTER_CONJURATION});
               map.put(VAN_CON_MYSTIC_BINDING         ,new IPerk[]{VAN_CON_SOUL_STEALER});
               map.put(VAN_CON_SOUL_STEALER           ,new IPerk[]{VAN_CON_OBLIVION_BINDING});
               map.put(VAN_CON_NECROMANCY             ,new IPerk[]{VAN_CON_DARK_SOULS});
               map.put(VAN_CON_DARK_SOULS             ,new IPerk[]{VAN_CON_TWIN_SOULS});
               map.put(VAN_CON_SUMMONER               ,new IPerk[]{VAN_CON_ATROMANCY});
               map.put(VAN_CON_ATROMANCY              ,new IPerk[]{VAN_CON_ELEMENTAL_POTENCY});
               map.put(VAN_CON_ELEMENTAL_POTENCY      ,new IPerk[]{VAN_CON_TWIN_SOULS});

               break;

            case SKILL_DESTRUCTION:

               map.put(VAN_DES_NOVICE_DESTRUCTION       ,new IPerk[]{VAN_DES_DESTRUCTION_DUAL_CASTING, VAN_DES_AUGMENTED_SHOCK, VAN_DES_AUGMENTED_FROST, VAN_DES_AUGMENTED_FLAMES, VAN_DES_APPRENTICE_DESTRUCTION});
               map.put(VAN_DES_APPRENTICE_DESTRUCTION   ,new IPerk[]{VAN_DES_RUNE_MASTER, VAN_DES_ADEPT_DESTRUCTION});
               map.put(VAN_DES_ADEPT_DESTRUCTION        ,new IPerk[]{VAN_DES_EXPERT_DESTRUCTION});
               map.put(VAN_DES_EXPERT_DESTRUCTION       ,new IPerk[]{VAN_DES_MASTER_DESTRUCTION});
               map.put(VAN_DES_AUGMENTED_FLAMES         ,new IPerk[]{VAN_DES_INTENSE_FLAMES});
               map.put(VAN_DES_AUGMENTED_FROST          ,new IPerk[]{VAN_DES_DEEP_FREEZE});
               map.put(VAN_DES_AUGMENTED_SHOCK          ,new IPerk[]{VAN_DES_DISINTEGRATE});
               map.put(VAN_DES_DESTRUCTION_DUAL_CASTING ,new IPerk[]{VAN_DES_IMPACT});

               break;

            case SKILL_SMITHING:

               map.put(VAN_SMT_STEEL_SMITHING         ,new IPerk[]{VAN_SMT_ARCANE_BLACKSMITH, VAN_SMT_ELVEN_SMITHING, VAN_SMT_DWARVEN_SMITHING });
               map.put(VAN_SMT_DWARVEN_SMITHING       ,new IPerk[]{VAN_SMT_ORCISH_SMITHING });
               map.put(VAN_SMT_ORCISH_SMITHING        ,new IPerk[]{VAN_SMT_EBONY_SMITHING });
               map.put(VAN_SMT_EBONY_SMITHING         ,new IPerk[]{VAN_SMT_DAEDRIC_SMITHING });
               map.put(VAN_SMT_DAEDRIC_SMITHING       ,new IPerk[]{VAN_SMT_DRAGON_ARMOR});
               map.put(VAN_SMT_ELVEN_SMITHING         ,new IPerk[]{VAN_SMT_ADVANCED_ARMORS});
               map.put(VAN_SMT_ADVANCED_ARMORS        ,new IPerk[]{VAN_SMT_GLASS_SMITHING});
               map.put(VAN_SMT_GLASS_SMITHING         ,new IPerk[]{VAN_SMT_DRAGON_ARMOR});

               break;

            case SKILL_ALTERATION:

               map.put(VAN_ALT_NOVICE_ALTERATION      ,new IPerk[]{VAN_ALT_ALTERATION_DUAL_CASTING, VAN_ALT_APPRENTICE_ALTERATION});
               map.put(VAN_ALT_APPRENTICE_ALTERATION  ,new IPerk[]{VAN_ALT_MAGIC_RESISTANCE, VAN_ALT_ADEPT_ALTERATION, VAN_ALT_MAGE_ARMOR});
               map.put(VAN_ALT_ADEPT_ALTERATION       ,new IPerk[]{VAN_ALT_STABILITY, VAN_ALT_EXPERT_ALTERATION});
               map.put(VAN_ALT_EXPERT_ALTERATION      ,new IPerk[]{VAN_ALT_MASTER_ALTERATION, VAN_ALT_ATRONACH});

               break;

            case SKILL_ARCHERY:

               map.put(VAN_ARC_OVERDRAW               ,new IPerk[]{VAN_ARC_CRITICAL_SHOT, VAN_ARC_EAGLE_EYE});
               map.put(VAN_ARC_CRITICAL_SHOT          ,new IPerk[]{VAN_ARC_HUNTERS_DISCIPLINE});
               map.put(VAN_ARC_HUNTERS_DISCIPLINE     ,new IPerk[]{VAN_ARC_RANGER});
               map.put(VAN_ARC_RANGER                 ,new IPerk[]{VAN_ARC_BULLSEYE});
               map.put(VAN_ARC_EAGLE_EYE              ,new IPerk[]{VAN_ARC_POWER_SHOT, VAN_ARC_STEADY_HAND});
               map.put(VAN_ARC_POWER_SHOT             ,new IPerk[]{VAN_ARC_QUICK_SHOT});
               map.put(VAN_ARC_QUICK_SHOT             ,new IPerk[]{VAN_ARC_BULLSEYE});

               break;

            case SKILL_BLOCK:

               map.put(VAN_BLC_SHIELD_WALL            ,new IPerk[]{VAN_BLC_POWER_BASH, VAN_BLC_DEFLECT_ARROWS, VAN_BLC_QUICK_REFLEXES});
               map.put(VAN_BLC_DEFLECT_ARROWS         ,new IPerk[]{VAN_BLC_ELEMENTAL_PROTECTION});
               map.put(VAN_BLC_ELEMENTAL_PROTECTION   ,new IPerk[]{VAN_BLC_BLOCK_RUNNER});
               map.put(VAN_BLC_BLOCK_RUNNER           ,new IPerk[]{VAN_BLC_SHIELD_CHARGE});
               map.put(VAN_BLC_POWER_BASH             ,new IPerk[]{VAN_BLC_DEADLY_BASH});
               map.put(VAN_BLC_DEADLY_BASH            ,new IPerk[]{VAN_BLC_DISARMING_BASH});
               map.put(VAN_BLC_DISARMING_BASH         ,new IPerk[]{VAN_BLC_SHIELD_CHARGE});

               break;

            case SKILL_ENCHANTING:

               map.put(VAN_ENC_ENCHANTER              ,new IPerk[]{VAN_ENC_SOUL_SQUEEZER, VAN_ENC_INSIGHTFUL_ENCHANTER, VAN_ENC_FIRE_ENCHANTER});
               map.put(VAN_ENC_FIRE_ENCHANTER         ,new IPerk[]{VAN_ENC_FROST_ENCHANTER});
               map.put(VAN_ENC_FROST_ENCHANTER        ,new IPerk[]{VAN_ENC_STORM_ENCHANTER});
               map.put(VAN_ENC_STORM_ENCHANTER        ,new IPerk[]{VAN_ENC_EXTRA_EFFECT});
               map.put(VAN_ENC_INSIGHTFUL_ENCHANTER   ,new IPerk[]{VAN_ENC_CORPUS_ENCHANTER});
               map.put(VAN_ENC_CORPUS_ENCHANTER       ,new IPerk[]{VAN_ENC_EXTRA_EFFECT});
               map.put(VAN_ENC_SOUL_SQUEEZER          ,new IPerk[]{VAN_ENC_SOUL_SIPHON});

               break;

            case SKILL_LIGHT_ARMOR:

               map.put(VAN_LAR_AGILE_DEFENDER  ,new IPerk[]{VAN_LAR_CUSTOM_FIT});
               map.put(VAN_LAR_CUSTOM_FIT      ,new IPerk[]{VAN_LAR_UNHINDERED, VAN_LAR_MATCHING_SET});
               map.put(VAN_LAR_MATCHING_SET    ,new IPerk[]{VAN_LAR_DEFT_MOVEMENT});
               map.put(VAN_LAR_UNHINDERED      ,new IPerk[]{VAN_LAR_WIND_WALKER});
               map.put(VAN_LAR_WIND_WALKER     ,new IPerk[]{VAN_LAR_DEFT_MOVEMENT});

               break;

            case SKILL_ONE_HANDED:

               map.put(VAN_ONH_ARMSMAN             ,new IPerk[]{VAN_ONH_DUAL_FLURRY, VAN_ONH_BLADESMAN, VAN_ONH_BONE_BREAKER, VAN_ONH_FIGHTING_STANCE, VAN_ONH_HACK_AND_SLASH});
               map.put(VAN_ONH_DUAL_FLURRY         ,new IPerk[]{VAN_ONH_DUAL_SAVAGERY});
               map.put(VAN_ONH_FIGHTING_STANCE     ,new IPerk[]{VAN_ONH_CRITICAL_CHARGE, VAN_ONH_SAVAGE_STRIKE});
               map.put(VAN_ONH_CRITICAL_CHARGE     ,new IPerk[]{VAN_ONH_PARALYZING_STRIKE});
               map.put(VAN_ONH_SAVAGE_STRIKE       ,new IPerk[]{VAN_ONH_PARALYZING_STRIKE});

               break;

            case SKILL_PICKPOCKET:

               map.put(VAN_PCK_LIGHT_FINGERS      ,new IPerk[]{VAN_PCK_NIGHT_THIEF});
               map.put(VAN_PCK_NIGHT_THIEF        ,new IPerk[]{VAN_PCK_POISONED, VAN_PCK_EXTRA_POCKETS, VAN_PCK_CUTPURSE});
               map.put(VAN_PCK_CUTPURSE           ,new IPerk[]{VAN_PCK_KEYMASTER, VAN_PCK_MISDIRECTION});
               map.put(VAN_PCK_MISDIRECTION       ,new IPerk[]{VAN_PCK_PERFECT_TOUCH});

               break;

            case SKILL_RESTORATION:

               map.put(VAN_RST_NOVICE_RESTORATION          ,new IPerk[]{VAN_RST_APPRENTICE_RESTORATION, VAN_RST_RECOVERY, VAN_RST_RESTORATION_DUAL_CASTING, VAN_RST_REGENERATION, VAN_RST_RESPITE, VAN_RST_WARD_ABSORB});
               map.put(VAN_RST_APPRENTICE_RESTORATION      ,new IPerk[]{VAN_RST_ADEPT_RESTORATION});
               map.put(VAN_RST_ADEPT_RESTORATION           ,new IPerk[]{VAN_RST_EXPERT_RESTORATION});
               map.put(VAN_RST_EXPERT_RESTORATION          ,new IPerk[]{VAN_RST_MASTER_RESTORATION});
               map.put(VAN_RST_RECOVERY                    ,new IPerk[]{VAN_RST_AVOID_DEATH});
               map.put(VAN_RST_REGENERATION                ,new IPerk[]{VAN_RST_NECROMAGE});

               break;

            case SKILL_SNEAK:

               map.put(VAN_SNK_STEALTH               ,new IPerk[]{VAN_SNK_MUFFLED_MOVEMENT, VAN_SNK_BACKSTAB});
               map.put(VAN_SNK_BACKSTAB              ,new IPerk[]{VAN_SNK_DEADLY_AIM});
               map.put(VAN_SNK_DEADLY_AIM            ,new IPerk[]{VAN_SNK_ASSASSINS_BLADE});
               map.put(VAN_SNK_MUFFLED_MOVEMENT      ,new IPerk[]{VAN_SNK_LIGHT_FOOT});
               map.put(VAN_SNK_LIGHT_FOOT            ,new IPerk[]{VAN_SNK_SILENT_ROLL});
               map.put(VAN_SNK_SILENT_ROLL           ,new IPerk[]{VAN_SNK_SILENCE});
               map.put(VAN_SNK_SILENCE               ,new IPerk[]{VAN_SNK_SHADOW_WARRIOR});

               break;

            case SKILL_SPEECH:

               map.put(VAN_SPC_HAGGLING        ,new IPerk[]{VAN_SPC_BRIBERY, VAN_SPC_ALLURE});
               map.put(VAN_SPC_ALLURE          ,new IPerk[]{VAN_SPC_MERCHANT});
               map.put(VAN_SPC_MERCHANT        ,new IPerk[]{VAN_SPC_INVESTOR});
               map.put(VAN_SPC_INVESTOR        ,new IPerk[]{VAN_SPC_FENCE});
               map.put(VAN_SPC_FENCE           ,new IPerk[]{VAN_SPC_MASTER_TRADER});
               map.put(VAN_SPC_BRIBERY         ,new IPerk[]{VAN_SPC_PERSUASION});
               map.put(VAN_SPC_PERSUASION      ,new IPerk[]{VAN_SPC_INTIMIDATION});

               break;

            case SKILL_TWO_HANDED:

               map.put(VAN_TWH_BARBARIAN              ,new IPerk[]{VAN_TWH_CHAMPIONS_STANCE, VAN_TWH_SKULLCRUSHER, VAN_TWH_DEEP_WOUNDS, VAN_TWH_LIMBSPLITTER});
               map.put(VAN_TWH_CHAMPIONS_STANCE       ,new IPerk[]{VAN_TWH_DEVASTATING_BLOW, VAN_TWH_GREAT_CRITICAL_CHARGE});
               map.put(VAN_TWH_DEVASTATING_BLOW       ,new IPerk[]{VAN_TWH_SWEEP});
               map.put(VAN_TWH_GREAT_CRITICAL_CHARGE  ,new IPerk[]{VAN_TWH_SWEEP});
               map.put(VAN_TWH_SWEEP                  ,new IPerk[]{VAN_TWH_WARMASTER});

               break;
        }

        return map;
    }
}