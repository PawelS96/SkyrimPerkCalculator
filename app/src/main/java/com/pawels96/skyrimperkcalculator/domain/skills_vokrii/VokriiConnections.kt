package com.pawels96.skyrimperkcalculator.domain.skills_vokrii

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill
import java.util.HashMap
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Alchemy.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Alteration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Archery.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Block.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Conjuration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Destruction.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Enchanting.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Illusion.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_HeavyArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_LightArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Lockpicking.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_OneHanded.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Pickpocket.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Restoration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Smithing.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Sneak.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Speech.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_TwoHanded.*


 fun EMainSkill.getVokriiConnections(): Map<IPerk, Array<IPerk>>{
    val map: MutableMap<IPerk, Array<IPerk>> = HashMap()
    when (this) {
        EMainSkill.SKILL_HEAVY_ARMOR -> {
            map[VOK_HAR_HEAVY_ARMOR_MASTERY] = arrayOf(VOK_HAR_BATTLE_FATIGUE, VOK_HAR_CUSHIONED, VOK_HAR_HEAVY_ARMOR_FIT)
            map[VOK_HAR_BATTLE_FATIGUE] = arrayOf(VOK_HAR_OFF_BALANCE)
            map[VOK_HAR_HEAVY_ARMOR_FIT] = arrayOf(VOK_HAR_HEAVY_ARMOR_TRAINING, VOK_HAR_FACE_OF_DEATH)
            map[VOK_HAR_HEAVY_ARMOR_TRAINING] = arrayOf(VOK_HAR_TOWER_OF_STRENGTH, VOK_HAR_MATCHING_HEAVY_SET)
            map[VOK_HAR_TOWER_OF_STRENGTH] = arrayOf(VOK_HAR_ELEMENTAL_DEFENSE)
            map[VOK_HAR_ELEMENTAL_DEFENSE] = arrayOf(VOK_HAR_GLANCING_BLOWS)
            map[VOK_HAR_OFF_BALANCE] = arrayOf(VOK_HAR_REAP_THE_WHIRLWIND)
            map[VOK_HAR_REAP_THE_WHIRLWIND] = arrayOf(VOK_HAR_FACE_OF_THE_MOUNTAIN)
        }
        EMainSkill.SKILL_LOCKPICKING -> {
            map[VOK_LCK_LOCKPICKING_MASTERY] = arrayOf(VOK_LCK_LOOTER, VOK_LCK_LOCKDOWN, VOK_LCK_QUICK_HANDS)
            map[VOK_LCK_LOOTER] = arrayOf(VOK_LCK_TREASURE_HUNTER)
            map[VOK_LCK_LOCKDOWN] = arrayOf(VOK_LCK_HOTWIRE)
            map[VOK_LCK_QUICK_HANDS] = arrayOf(VOK_LCK_DUNGEON_MASTER, VOK_LCK_WAX_KEY)
            map[VOK_LCK_TREASURE_HUNTER] = arrayOf(VOK_LCK_ARCHAEOLOGIST)
            map[VOK_LCK_HOTWIRE] = arrayOf(VOK_LCK_OVERDRIVE)
            map[VOK_LCK_WAX_KEY] = arrayOf(VOK_LCK_LUCKY_GUESS)
            map[VOK_LCK_LUCKY_GUESS] = arrayOf(VOK_LCK_LOCKMASTER)
        }
        EMainSkill.SKILL_ALCHEMY -> {
            map[VOK_ALC_ALCHEMY_MASTERY] = arrayOf(VOK_ALC_BENEFACTOR, VOK_ALC_POISONER, VOK_ALC_PHYSICIAN)
            map[VOK_ALC_BENEFACTOR] = arrayOf(VOK_ALC_SLOW_METABOLISM, VOK_ALC_EXPERIMENTER, VOK_ALC_STIMULANTS)
            map[VOK_ALC_POISONER] = arrayOf(VOK_ALC_CONCENTRATED_POISON)
            map[VOK_ALC_EXPERIMENTER] = arrayOf(VOK_ALC_GREEN_THUMB)
            map[VOK_ALC_STIMULANTS] = arrayOf(VOK_ALC_ADRENALINE)
            map[VOK_ALC_GREEN_THUMB] = arrayOf(VOK_ALC_PURITY, VOK_ALC_DOUBLE_TOIL_AND_TROUBLE)
            map[VOK_ALC_CONCENTRATED_POISON] = arrayOf(VOK_ALC_PURITY, VOK_ALC_ALKAHEST)
            map[VOK_ALC_ALKAHEST] = arrayOf(VOK_ALC_PLAGUE_DOCTOR, VOK_ALC_GOURMET)
        }
        EMainSkill.SKILL_ILLUSION -> {
            map[VOK_ILU_ILLUSION_MASTERY] = arrayOf(VOK_ILU_ILLUSION_DUAL_CASTING, VOK_ILU_NEVERWORLD, VOK_ILU_QUIET_CASTING, VOK_ILU_ANIMAGE)
            map[VOK_ILU_ANIMAGE] = arrayOf(VOK_ILU_MASTER_OF_THE_MIND)
            map[VOK_ILU_NEVERWORLD] = arrayOf(VOK_ILU_LAMB_TO_THE_SLAUGHTER, VOK_ILU_TERROR)
            map[VOK_ILU_TERROR] = arrayOf(VOK_ILU_PARALYZING_FEAR, VOK_ILU_IRON_MAIDEN)
            map[VOK_ILU_IRON_MAIDEN] = arrayOf(VOK_ILU_ENRAGE, VOK_ILU_SPLENDOR)
            map[VOK_ILU_SPLENDOR] = arrayOf(VOK_ILU_SPIRIT_OF_WAR, VOK_ILU_MIND_THRALL)
            map[VOK_ILU_QUIET_CASTING] = arrayOf(VOK_ILU_BLUR)
        }
        EMainSkill.SKILL_CONJURATION -> {
            map[VOK_CON_CONJURATION_MASTERY] = arrayOf(VOK_CON_CONJURATION_DUAL_CASTING, VOK_CON_ATROMANCY, VOK_CON_NECROMANCY, VOK_CON_MYSTIC_BINDING)
            map[VOK_CON_ATROMANCY] = arrayOf(VOK_CON_OBLIVION_STONE, VOK_CON_RIFT_SUMMONER)
            map[VOK_CON_NECROMANCY] = arrayOf(VOK_CON_RIFT_SUMMONER, VOK_CON_GHOUL_FRENZY)
            map[VOK_CON_MYSTIC_BINDING] = arrayOf(VOK_CON_SOUL_STEALER)
            map[VOK_CON_SOUL_STEALER] = arrayOf(VOK_CON_OBLIVION_BINDING)
            map[VOK_CON_OBLIVION_BINDING] = arrayOf(VOK_CON_HOLLOW_BINDING)
            map[VOK_CON_HOLLOW_BINDING] = arrayOf(VOK_CON_VOID_BRAND)
            map[VOK_CON_GHOUL_FRENZY] = arrayOf(VOK_CON_BLOOD_ZOMBIE)
            map[VOK_CON_BLOOD_ZOMBIE] = arrayOf(VOK_CON_NECROMASTER, VOK_CON_TWIN_SOULS)
            map[VOK_CON_RIFT_SUMMONER] = arrayOf(VOK_CON_GRAND_CONJURER)
            map[VOK_CON_OBLIVION_STONE] = arrayOf(VOK_CON_ELEMENTAL_POTENCY)
            map[VOK_CON_ELEMENTAL_POTENCY] = arrayOf(VOK_CON_TWIN_SOULS, VOK_CON_ELEMENTAL_CONFLUX)
        }
        EMainSkill.SKILL_DESTRUCTION -> {
            map[VOK_DES_DESTRUCTION_MASTERY] = arrayOf(VOK_DES_DESTRUCTION_DUAL_CASTING, VOK_DES_AUGMENTED_FLAMES, VOK_DES_AUGMENTED_FROST, VOK_DES_AUGMENTED_SHOCK, VOK_DES_RUNE_MASTER)
            map[VOK_DES_DESTRUCTION_DUAL_CASTING] = arrayOf(VOK_DES_IMPACT)
            map[VOK_DES_AUGMENTED_FLAMES] = arrayOf(VOK_DES_DEVOURING_FLAMES)
            map[VOK_DES_DEVOURING_FLAMES] = arrayOf(VOK_DES_SCORCHED_EARTH)
            map[VOK_DES_SCORCHED_EARTH] = arrayOf(VOK_DES_MAGES_FURY)
            map[VOK_DES_AUGMENTED_FROST] = arrayOf(VOK_DES_CHILLING_FROST)
            map[VOK_DES_CHILLING_FROST] = arrayOf(VOK_DES_WINTERS_GRASP)
            map[VOK_DES_WINTERS_GRASP] = arrayOf(VOK_DES_MAGES_FURY)
            map[VOK_DES_AUGMENTED_SHOCK] = arrayOf(VOK_DES_DEAFENING_SHOCK)
            map[VOK_DES_DEAFENING_SHOCK] = arrayOf(VOK_DES_CRACKLING_SPHERE)
            map[VOK_DES_CRACKLING_SPHERE] = arrayOf(VOK_DES_MAGES_FURY)
            map[VOK_DES_RUNE_MASTER] = arrayOf(VOK_DES_HETHOTHS_DISJUNCTION)
            map[VOK_DES_HETHOTHS_DISJUNCTION] = arrayOf(VOK_DES_ELEMENTAL_BARRIER, VOK_DES_ELEMENTAL_SHIELD)
            map[VOK_DES_ELEMENTAL_BARRIER] = arrayOf(VOK_DES_EYE_OF_THE_STORM)
        }
        EMainSkill.SKILL_SMITHING -> {
            map[VOK_SMT_BASIC_SMITHING] = arrayOf(VOK_SMT_DWARVEN_SMITHING, VOK_SMT_ARMOR_PADDING, VOK_SMT_ARCANE_BLACKSMITH, VOK_SMT_MERIC_SMITHING)
            map[VOK_SMT_DWARVEN_SMITHING] = arrayOf(VOK_SMT_ENGRAVED_SMITHING)
            map[VOK_SMT_ENGRAVED_SMITHING] = arrayOf(VOK_SMT_EXOTIC_SMITHING, VOK_SMT_HIGH_YIELD_MINING)
            map[VOK_SMT_EXOTIC_SMITHING] = arrayOf(VOK_SMT_DAEDRIC_SMITHING)
            map[VOK_SMT_DAEDRIC_SMITHING] = arrayOf(VOK_SMT_DRAGON_SMITHING)
            map[VOK_SMT_ARMOR_PADDING] = arrayOf(VOK_SMT_LAYERED_PLATES)
            map[VOK_SMT_LAYERED_PLATES] = arrayOf(VOK_SMT_CONCEALED_ENCHANTMENTS)
            map[VOK_SMT_MERIC_SMITHING] = arrayOf(VOK_SMT_PRIMAL_SMITHING)
            map[VOK_SMT_PRIMAL_SMITHING] = arrayOf(VOK_SMT_HIGH_YIELD_MINING, VOK_SMT_CRYSTALLINE_SMITHING)
            map[VOK_SMT_CRYSTALLINE_SMITHING] = arrayOf(VOK_SMT_DRAGON_SMITHING)
        }
        EMainSkill.SKILL_ALTERATION -> {
            map[VOK_ALT_ALTERATION_MASTERY] = arrayOf(VOK_ALT_TELEKINETIC_FORCE, VOK_ALT_ALTERATION_DUAL_CASTING, VOK_ALT_MAGE_ARMOR, VOK_ALT_MAGIC_RESISTANCE, VOK_ALT_BATTLEMAGE)
            map[VOK_ALT_BATTLEMAGE] = arrayOf(VOK_ALT_RUNIC_WEAPON)
            map[VOK_ALT_MAGIC_RESISTANCE] = arrayOf(VOK_ALT_ALTER_SELF)
            map[VOK_ALT_ALTER_SELF] = arrayOf(VOK_ALT_ATRONACH, VOK_ALT_ARCANE_GUIDANCE)
            map[VOK_ALT_MAGE_ARMOR] = arrayOf(VOK_ALT_OCATOS_PREPARATION, VOK_ALT_STABILITY, VOK_ALT_SORCERERS_ROBES)
            map[VOK_ALT_STABILITY] = arrayOf(VOK_ALT_INITIATE, VOK_ALT_RITUALIST)
            map[VOK_ALT_SORCERERS_ROBES] = arrayOf(VOK_ALT_FORCE_OF_WILL)
            map[VOK_ALT_TELEKINETIC_FORCE] = arrayOf(VOK_ALT_TELEKINETIC_PRODIGY)
            map[VOK_ALT_ARCANE_GUIDANCE] = arrayOf(VOK_ALT_HETHOTHS_ESCAPE)
        }
        EMainSkill.SKILL_ARCHERY -> {
            map[VOK_ARC_ARCHERY_MASTERY] = arrayOf(VOK_ARC_FAR_SHOT, VOK_ARC_POINT_BLANK_SHOT, VOK_ARC_EAGLE_EYE)
            map[VOK_ARC_FAR_SHOT] = arrayOf(VOK_ARC_IMPALING_SHOT)
            map[VOK_ARC_IMPALING_SHOT] = arrayOf(VOK_ARC_POWER_SHOT, VOK_ARC_ARROW_TO_THE_KNEE)
            map[VOK_ARC_POWER_SHOT] = arrayOf(VOK_ARC_RANGER)
            map[VOK_ARC_ARROW_TO_THE_KNEE] = arrayOf(VOK_ARC_PINNING_SHOT)
            map[VOK_ARC_POINT_BLANK_SHOT] = arrayOf(VOK_ARC_BREACHING_SHOT)
            map[VOK_ARC_BREACHING_SHOT] = arrayOf(VOK_ARC_POWER_SHOT, VOK_ARC_GORE)
            map[VOK_ARC_GORE] = arrayOf(VOK_ARC_PINNING_SHOT)
            map[VOK_ARC_EAGLE_EYE] = arrayOf(VOK_ARC_HUNTERS_DISCIPLINE, VOK_ARC_STEADY_AIM)
            map[VOK_ARC_STEADY_AIM] = arrayOf(VOK_ARC_LIONS_ARROW)
            map[VOK_ARC_HUNTERS_DISCIPLINE] = arrayOf(VOK_ARC_HUNTERS_FOCUS)
            map[VOK_ARC_RANGER] = arrayOf(VOK_ARC_QUICK_SHOT)
        }
        EMainSkill.SKILL_BLOCK -> {
            map[VOK_BLC_BLOCK_MASTERY] = arrayOf(VOK_BLC_WEAPON_BLOCK, VOK_BLC_UNWAVERING_DEFENSE, VOK_BLC_DEFLECT_ARROWS, VOK_BLC_QUICK_REFLEXES, VOK_BLC_POWER_BASH)
            map[VOK_BLC_DEFLECT_ARROWS] = arrayOf(VOK_BLC_ELEMENTAL_PROTECTION, VOK_BLC_BLOCK_RUNNER)
            map[VOK_BLC_BLOCK_RUNNER] = arrayOf(VOK_BLC_SHIELD_CHARGE)
            map[VOK_BLC_SHIELD_CHARGE] = arrayOf(VOK_BLC_STONEHEART)
            map[VOK_BLC_UNWAVERING_DEFENSE] = arrayOf(VOK_BLC_POKE_THE_DRAGON)
            map[VOK_BLC_POKE_THE_DRAGON] = arrayOf(VOK_BLC_STONEHEART)
            map[VOK_BLC_POWER_BASH] = arrayOf(VOK_BLC_TORCH_BASH, VOK_BLC_DEADLY_BASH)
            map[VOK_BLC_DEADLY_BASH] = arrayOf(VOK_BLC_MOCKING_BLOW, VOK_BLC_DRAGON_TAIL)
            map[VOK_BLC_DRAGON_TAIL] = arrayOf(VOK_BLC_STONEHEART)
        }
        EMainSkill.SKILL_ENCHANTING -> {
            map[VOK_ENC_ENCHANTING_MASTERY] = arrayOf(VOK_ENC_SOUL_SQUEEZER, VOK_ENC_POWER_STONE, VOK_ENC_WEAPON_ENCHANTER, VOK_ENC_ARMOR_ENCHANTER, VOK_ENC_SCROLL_SAGE)
            map[VOK_ENC_SCROLL_SAGE] = arrayOf(VOK_ENC_SCROLL_HUNTER)
            map[VOK_ENC_SCROLL_HUNTER] = arrayOf(VOK_ENC_SPIDER_HUNTER)
            map[VOK_ENC_ARMOR_ENCHANTER] = arrayOf(VOK_ENC_REGALIA_ENCHANTER)
            map[VOK_ENC_REGALIA_ENCHANTER] = arrayOf(VOK_ENC_EXTRA_EFFECT)
            map[VOK_ENC_WEAPON_ENCHANTER] = arrayOf(VOK_ENC_SOUL_ENCHANTER)
            map[VOK_ENC_SOUL_ENCHANTER] = arrayOf(VOK_ENC_EXTRA_EFFECT)
            map[VOK_ENC_POWER_STONE] = arrayOf(VOK_ENC_MANA_STONE)
            map[VOK_ENC_MANA_STONE] = arrayOf(VOK_ENC_DEFENSIVE_RUNES, VOK_ENC_STAFF_RECHARGE)
            map[VOK_ENC_SOUL_SQUEEZER] = arrayOf(VOK_ENC_SOUL_SIPHON)
            map[VOK_ENC_SOUL_SIPHON] = arrayOf(VOK_ENC_THUNDERSTRUCK)
        }
        EMainSkill.SKILL_LIGHT_ARMOR -> {
            map[VOK_LAR_LIGHT_ARMOR_MASTERY] = arrayOf(VOK_LAR_IRON_FIST, VOK_LAR_LIGHT_ARMOR_FIT, VOK_LAR_AGILITY)
            map[VOK_LAR_IRON_FIST] = arrayOf(VOK_LAR_FLURRY_OF_BLOWS)
            map[VOK_LAR_LIGHT_ARMOR_FIT] = arrayOf(VOK_LAR_LIGHT_ARMOR_TRAINING, VOK_LAR_KEEN_SENSES)
            map[VOK_LAR_AGILITY] = arrayOf(VOK_LAR_WINDRUNNER)
            map[VOK_LAR_WINDRUNNER] = arrayOf(VOK_LAR_WARDANCER)
            map[VOK_LAR_WARDANCER] = arrayOf(VOK_LAR_EVASIVE_SPRINT, VOK_LAR_UNTOUCHABLE)
            map[VOK_LAR_LIGHT_ARMOR_TRAINING] = arrayOf(VOK_LAR_MATCHING_LIGHT_SET, VOK_LAR_TOUGH_HIDE)
            map[VOK_LAR_FLURRY_OF_BLOWS] = arrayOf(VOK_LAR_KI_STRIKE)
        }
        EMainSkill.SKILL_ONE_HANDED -> {
            map[VOK_ONH_ONE_HANDED_MASTERY] = arrayOf(VOK_ONH_DUAL_FLURRY, VOK_ONH_OVERPOWERING_ASSAULT, VOK_ONH_GRIEVOUS_WOUNDS, VOK_ONH_DENTING_BLOWS, VOK_ONH_FANGS, VOK_ONH_DISCIPLINED_FIGHTER)
            map[VOK_ONH_DUAL_FLURRY] = arrayOf(VOK_ONH_DUAL_SAVAGERY)
            map[VOK_ONH_DUAL_SAVAGERY] = arrayOf(VOK_ONH_BLADEDANCER)
            map[VOK_ONH_OVERPOWERING_ASSAULT] = arrayOf(VOK_ONH_EXECUTE)
            map[VOK_ONH_EXECUTE] = arrayOf(VOK_ONH_VICTORY_RUSH)
            map[VOK_ONH_GRIEVOUS_WOUNDS] = arrayOf(VOK_ONH_SHIELDBITER)
            map[VOK_ONH_SHIELDBITER] = arrayOf(VOK_ONH_VICTORY_RUSH)
            map[VOK_ONH_DENTING_BLOWS] = arrayOf(VOK_ONH_DISRUPTING_STRIKE)
            map[VOK_ONH_DISRUPTING_STRIKE] = arrayOf(VOK_ONH_VICTORY_RUSH)
            map[VOK_ONH_FANGS] = arrayOf(VOK_ONH_SPITTING_COBRA)
            map[VOK_ONH_SPITTING_COBRA] = arrayOf(VOK_ONH_VICTORY_RUSH)
            map[VOK_ONH_FURIOUS_STRENGTH] = arrayOf(VOK_ONH_VALOROUS_CHARGE, VOK_ONH_CRATER_MAKER)
            map[VOK_ONH_CRATER_MAKER] = arrayOf(VOK_ONH_DISARMING_SLASH)
            map[VOK_ONH_DISCIPLINED_FIGHTER] = arrayOf(VOK_ONH_FURIOUS_STRENGTH)
        }
        EMainSkill.SKILL_PICKPOCKET -> {
            map[VOK_PCK_PICKPOCKET_MASTERY] = arrayOf(VOK_PCK_PAYDAY, VOK_PCK_CUTPURSE, VOK_PCK_EXTRA_POCKETS)
            map[VOK_PCK_CUTPURSE] = arrayOf(VOK_PCK_OBLIVIOUS, VOK_PCK_DEATHS_EMPEROR)
            map[VOK_PCK_PAYDAY] = arrayOf(VOK_PCK_CONSPICUOUS_WEALTH)
            map[VOK_PCK_CONSPICUOUS_WEALTH] = arrayOf(VOK_PCK_MASTER_THIEF)
            map[VOK_PCK_OBLIVIOUS] = arrayOf(VOK_PCK_POISONED, VOK_PCK_LAWLESS_TIMES)
            map[VOK_PCK_DEATHS_EMPEROR] = arrayOf(VOK_PCK_POISONED)
            map[VOK_PCK_POISONED] = arrayOf(VOK_PCK_TRICKSTER)
            map[VOK_PCK_TRICKSTER] = arrayOf(VOK_PCK_PERFECT_TOUCH)
        }
        EMainSkill.SKILL_RESTORATION -> {
            map[VOK_RST_RESTORATION_MASTERY] = arrayOf(VOK_RST_MERCY, VOK_RST_RESTORATION_DUAL_CASTING, VOK_RST_INSPIRE, VOK_RST_INNER_LIGHT, VOK_RST_VIGILANT_WARD)
            map[VOK_RST_MERCY] = arrayOf(VOK_RST_HARM, VOK_RST_RESPITE)
            map[VOK_RST_HARM] = arrayOf(VOK_RST_SUNS_JUDGMENT)
            map[VOK_RST_SUNS_JUDGMENT] = arrayOf(VOK_RST_REBUKE_UNDEAD)
            map[VOK_RST_RESPITE] = arrayOf(VOK_RST_ETERNAL_FLAME)
            map[VOK_RST_VIGILANT_WARD] = arrayOf(VOK_RST_WARD_ABSORB)
            map[VOK_RST_WARD_ABSORB] = arrayOf(VOK_RST_MAGE_WARD)
            map[VOK_RST_INSPIRE] = arrayOf(VOK_RST_NECROMAGE)
            map[VOK_RST_INNER_LIGHT] = arrayOf(VOK_RST_BLESSED)
            map[VOK_RST_BLESSED] = arrayOf(VOK_RST_INTERVENTION)
        }
        EMainSkill.SKILL_SNEAK -> {
            map[VOK_SNK_SNEAK_MASTERY] = arrayOf(VOK_SNK_SILENT_ROLL, VOK_SNK_SNEAK_ATTACK, VOK_SNK_SILENT_MOVEMENT, VOK_SNK_SHADOWCASTER)
            map[VOK_SNK_SILENT_ROLL] = arrayOf(VOK_SNK_DODGE_ROLL)
            map[VOK_SNK_DODGE_ROLL] = arrayOf(VOK_SNK_SHADOW_WARRIOR)
            map[VOK_SNK_SNEAK_ATTACK] = arrayOf(VOK_SNK_DEADLY_AIM, VOK_SNK_ASSASSINS_BLADE)
            map[VOK_SNK_ASSASSINS_BLADE] = arrayOf(VOK_SNK_BACKSTAB)
            map[VOK_SNK_BACKSTAB] = arrayOf(VOK_SNK_CLOAK_AND_DAGGER)
            map[VOK_SNK_CLOAK_AND_DAGGER] = arrayOf(VOK_SNK_SHADOW_WARRIOR)
            map[VOK_SNK_SILENT_MOVEMENT] = arrayOf(VOK_SNK_LIGHT_FOOT, VOK_SNK_BLIND_SPOT)
            map[VOK_SNK_BLIND_SPOT] = arrayOf(VOK_SNK_FOG_OF_WAR, VOK_SNK_ESCAPE_ARTIST)
        }
        EMainSkill.SKILL_SPEECH -> {
            map[VOK_SPC_SPEECH_MASTERY] = arrayOf(VOK_SPC_KINSHIP, VOK_SPC_SPEAK_WITH_ANIMALS, VOK_SPC_TONAL_HARMONY, VOK_SPC_BRIBERY)
            map[VOK_SPC_BRIBERY] = arrayOf(VOK_SPC_ELOQUENT)
            map[VOK_SPC_KINSHIP] = arrayOf(VOK_SPC_SALESMAN)
            map[VOK_SPC_SALESMAN] = arrayOf(VOK_SPC_FENCE, VOK_SPC_INVESTOR)
            map[VOK_SPC_INVESTOR] = arrayOf(VOK_SPC_PRIVATE_STOCK)
            map[VOK_SPC_PRIVATE_STOCK] = arrayOf(VOK_SPC_MASTER_TRADER)
            map[VOK_SPC_SPEAK_WITH_ANIMALS] = arrayOf(VOK_SPC_BEASTMASTER)
            map[VOK_SPC_TONAL_HARMONY] = arrayOf(VOK_SPC_WORDS_OF_POWER)
            map[VOK_SPC_WORDS_OF_POWER] = arrayOf(VOK_SPC_SKALD, VOK_SPC_DOVAHZULAAN)
        }
        EMainSkill.SKILL_TWO_HANDED -> {
            map[VOK_TWH_TWO_HANDED_MASTERY] = arrayOf(VOK_TWH_DEATH_OR_GLORY, VOK_TWH_OVERBEARING_ASSAULT, VOK_TWH_MORTAL_WOUNDS, VOK_TWH_CRUSHING_BLOWS, VOK_TWH_BRUTAL_FIGHTER)
            map[VOK_TWH_DEATH_OR_GLORY] = arrayOf(VOK_TWH_BERSERKER)
            map[VOK_TWH_BERSERKER] = arrayOf(VOK_TWH_BEAR_HIDE)
            map[VOK_TWH_OVERBEARING_ASSAULT] = arrayOf(VOK_TWH_COUP_DE_GRACE)
            map[VOK_TWH_MORTAL_WOUNDS] = arrayOf(VOK_TWH_HOOK_BLADE)
            map[VOK_TWH_HOOK_BLADE] = arrayOf(VOK_TWH_CROWD_PLEASER)
            map[VOK_TWH_CRUSHING_BLOWS] = arrayOf(VOK_TWH_SHATTERING_STRIKE)
            map[VOK_TWH_SHATTERING_STRIKE] = arrayOf(VOK_TWH_CROWD_PLEASER)
            map[VOK_TWH_BRUTAL_FIGHTER] = arrayOf(VOK_TWH_FEROCIOUS_STRENGTH)
            map[VOK_TWH_FEROCIOUS_STRENGTH] = arrayOf(VOK_TWH_VICIOUS_CHARGE, VOK_TWH_WARMASTER)
            map[VOK_TWH_WARMASTER] = arrayOf(VOK_TWH_SWEEP)
            map[VOK_TWH_VICIOUS_CHARGE] = arrayOf(VOK_TWH_ROLLING_CHARGE)
            map[VOK_TWH_COUP_DE_GRACE] = arrayOf(VOK_TWH_CROWD_PLEASER)
        }
    }
    return map
}