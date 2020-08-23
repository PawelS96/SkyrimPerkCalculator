package com.pawels96.skyrimperkcalculator.domain.skills_vanilla

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill
import java.util.HashMap
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alteration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Archery.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Block.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Conjuration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Destruction.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Enchanting.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.HeavyArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Illusion.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.LightArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Lockpicking.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.OneHanded.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Pickpocket.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Restoration.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Smithing.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Sneak.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Speech.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.TwoHanded.*

fun EMainSkill.getVanillaConnections(): Map<IPerk, Array<IPerk>> {
    val map: MutableMap<IPerk, Array<IPerk>> = HashMap()

    when (this) {
        EMainSkill.SKILL_HEAVY_ARMOR -> {
            map[VAN_HAR_JUGGERNAUT] = arrayOf(VAN_HAR_FISTS_OF_STEEL, VAN_HAR_WELL_FITTED)
            map[VAN_HAR_FISTS_OF_STEEL] = arrayOf(VAN_HAR_CUSHIONED)
            map[VAN_HAR_CUSHIONED] = arrayOf(VAN_HAR_CONDITIONING)
            map[VAN_HAR_WELL_FITTED] = arrayOf(VAN_HAR_TOWER_OF_STRENGTH)
            map[VAN_HAR_TOWER_OF_STRENGTH] = arrayOf(VAN_HAR_MATCHING_SET)
            map[VAN_HAR_MATCHING_SET] = arrayOf(VAN_HAR_REFLECT_BLOWS)
        }
        EMainSkill.SKILL_LOCKPICKING -> {
            map[VAN_LCK_NOVICE_LOCKS] = arrayOf(VAN_LCK_APPRENTICE_LOCKS)
            map[VAN_LCK_APPRENTICE_LOCKS] = arrayOf(VAN_LCK_ADEPT_LOCKS, VAN_LCK_QUICK_HANDS)
            map[VAN_LCK_QUICK_HANDS] = arrayOf(VAN_LCK_WAX_KEY)
            map[VAN_LCK_ADEPT_LOCKS] = arrayOf(VAN_LCK_EXPERT_LOCKS, VAN_LCK_GOLDEN_TOUCH)
            map[VAN_LCK_EXPERT_LOCKS] = arrayOf(VAN_LCK_MASTER_LOCKS, VAN_LCK_LOCKSMITH)
            map[VAN_LCK_GOLDEN_TOUCH] = arrayOf(VAN_LCK_TREASURE_HUNTER)
            map[VAN_LCK_LOCKSMITH] = arrayOf(VAN_LCK_UNBREAKABLE)
        }
        EMainSkill.SKILL_ALCHEMY -> {
            map[VAN_ALC_ALCHEMIST] = arrayOf(VAN_ALC_PHYSICIAN)
            map[VAN_ALC_PHYSICIAN] = arrayOf(VAN_ALC_BENEFACTOR, VAN_ALC_POISONER)
            map[VAN_ALC_BENEFACTOR] = arrayOf(VAN_ALC_EXPERIMENTER)
            map[VAN_ALC_EXPERIMENTER] = arrayOf(VAN_ALC_SNAKEBLOOD)
            map[VAN_ALC_POISONER] = arrayOf(VAN_ALC_CONCENTRATED_POISON)
            map[VAN_ALC_CONCENTRATED_POISON] = arrayOf(VAN_ALC_GREEN_THUMB, VAN_ALC_SNAKEBLOOD)
            map[VAN_ALC_SNAKEBLOOD] = arrayOf(VAN_ALC_PURITY)
        }
        EMainSkill.SKILL_ILLUSION -> {
            map[VAN_ILU_NOVICE_ILLUSION] = arrayOf(VAN_ILU_ILLUSION_DUAL_CASTING, VAN_ILU_APPRENTICE_ILLUSION, VAN_ILU_HYPNOTIC_GAZE, VAN_ILU_ANIMAGE)
            map[VAN_ILU_ANIMAGE] = arrayOf(VAN_ILU_KINDRED_MAGE)
            map[VAN_ILU_KINDRED_MAGE] = arrayOf(VAN_ILU_QUIET_CASTING)
            map[VAN_ILU_QUIET_CASTING] = arrayOf(VAN_ILU_MASTER_OF_THE_MIND)
            map[VAN_ILU_APPRENTICE_ILLUSION] = arrayOf(VAN_ILU_ADEPT_ILLUSION)
            map[VAN_ILU_ADEPT_ILLUSION] = arrayOf(VAN_ILU_EXPERT_ILLUSION)
            map[VAN_ILU_EXPERT_ILLUSION] = arrayOf(VAN_ILU_MASTER_ILLUSION)
            map[VAN_ILU_HYPNOTIC_GAZE] = arrayOf(VAN_ILU_ASPECT_OF_TERROR)
            map[VAN_ILU_ASPECT_OF_TERROR] = arrayOf(VAN_ILU_RAGE)
            map[VAN_ILU_RAGE] = arrayOf(VAN_ILU_MASTER_OF_THE_MIND)
        }
        EMainSkill.SKILL_CONJURATION -> {
            map[VAN_CON_NOVICE_CONJURATION] = arrayOf(VAN_CON_NECROMANCY, VAN_CON_MYSTIC_BINDING, VAN_CON_APPRENTICE_CONJURATION, VAN_CON_SUMMONER, VAN_CON_CONJURATION_DUAL_CASTING)
            map[VAN_CON_APPRENTICE_CONJURATION] = arrayOf(VAN_CON_ADEPT_CONJURATION)
            map[VAN_CON_ADEPT_CONJURATION] = arrayOf(VAN_CON_EXPERT_CONJURATION)
            map[VAN_CON_EXPERT_CONJURATION] = arrayOf(VAN_CON_MASTER_CONJURATION)
            map[VAN_CON_MYSTIC_BINDING] = arrayOf(VAN_CON_SOUL_STEALER)
            map[VAN_CON_SOUL_STEALER] = arrayOf(VAN_CON_OBLIVION_BINDING)
            map[VAN_CON_NECROMANCY] = arrayOf(VAN_CON_DARK_SOULS)
            map[VAN_CON_DARK_SOULS] = arrayOf(VAN_CON_TWIN_SOULS)
            map[VAN_CON_SUMMONER] = arrayOf(VAN_CON_ATROMANCY)
            map[VAN_CON_ATROMANCY] = arrayOf(VAN_CON_ELEMENTAL_POTENCY)
            map[VAN_CON_ELEMENTAL_POTENCY] = arrayOf(VAN_CON_TWIN_SOULS)
        }
        EMainSkill.SKILL_DESTRUCTION -> {
            map[VAN_DES_NOVICE_DESTRUCTION] = arrayOf(VAN_DES_DESTRUCTION_DUAL_CASTING, VAN_DES_AUGMENTED_SHOCK, VAN_DES_AUGMENTED_FROST, VAN_DES_AUGMENTED_FLAMES, VAN_DES_APPRENTICE_DESTRUCTION)
            map[VAN_DES_APPRENTICE_DESTRUCTION] = arrayOf(VAN_DES_RUNE_MASTER, VAN_DES_ADEPT_DESTRUCTION)
            map[VAN_DES_ADEPT_DESTRUCTION] = arrayOf(VAN_DES_EXPERT_DESTRUCTION)
            map[VAN_DES_EXPERT_DESTRUCTION] = arrayOf(VAN_DES_MASTER_DESTRUCTION)
            map[VAN_DES_AUGMENTED_FLAMES] = arrayOf(VAN_DES_INTENSE_FLAMES)
            map[VAN_DES_AUGMENTED_FROST] = arrayOf(VAN_DES_DEEP_FREEZE)
            map[VAN_DES_AUGMENTED_SHOCK] = arrayOf(VAN_DES_DISINTEGRATE)
            map[VAN_DES_DESTRUCTION_DUAL_CASTING] = arrayOf(VAN_DES_IMPACT)
        }
        EMainSkill.SKILL_SMITHING -> {
            map[VAN_SMT_STEEL_SMITHING] = arrayOf(VAN_SMT_ARCANE_BLACKSMITH, VAN_SMT_ELVEN_SMITHING, VAN_SMT_DWARVEN_SMITHING)
            map[VAN_SMT_DWARVEN_SMITHING] = arrayOf(VAN_SMT_ORCISH_SMITHING)
            map[VAN_SMT_ORCISH_SMITHING] = arrayOf(VAN_SMT_EBONY_SMITHING)
            map[VAN_SMT_EBONY_SMITHING] = arrayOf(VAN_SMT_DAEDRIC_SMITHING)
            map[VAN_SMT_DAEDRIC_SMITHING] = arrayOf(VAN_SMT_DRAGON_ARMOR)
            map[VAN_SMT_ELVEN_SMITHING] = arrayOf(VAN_SMT_ADVANCED_ARMORS)
            map[VAN_SMT_ADVANCED_ARMORS] = arrayOf(VAN_SMT_GLASS_SMITHING)
            map[VAN_SMT_GLASS_SMITHING] = arrayOf(VAN_SMT_DRAGON_ARMOR)
        }
        EMainSkill.SKILL_ALTERATION -> {
            map[VAN_ALT_NOVICE_ALTERATION] = arrayOf(VAN_ALT_ALTERATION_DUAL_CASTING, VAN_ALT_APPRENTICE_ALTERATION)
            map[VAN_ALT_APPRENTICE_ALTERATION] = arrayOf(VAN_ALT_MAGIC_RESISTANCE, VAN_ALT_ADEPT_ALTERATION, VAN_ALT_MAGE_ARMOR)
            map[VAN_ALT_ADEPT_ALTERATION] = arrayOf(VAN_ALT_STABILITY, VAN_ALT_EXPERT_ALTERATION)
            map[VAN_ALT_EXPERT_ALTERATION] = arrayOf(VAN_ALT_MASTER_ALTERATION, VAN_ALT_ATRONACH)
        }
        EMainSkill.SKILL_ARCHERY -> {
            map[VAN_ARC_OVERDRAW] = arrayOf(VAN_ARC_CRITICAL_SHOT, VAN_ARC_EAGLE_EYE)
            map[VAN_ARC_CRITICAL_SHOT] = arrayOf(VAN_ARC_HUNTERS_DISCIPLINE)
            map[VAN_ARC_HUNTERS_DISCIPLINE] = arrayOf(VAN_ARC_RANGER)
            map[VAN_ARC_RANGER] = arrayOf(VAN_ARC_BULLSEYE)
            map[VAN_ARC_EAGLE_EYE] = arrayOf(VAN_ARC_POWER_SHOT, VAN_ARC_STEADY_HAND)
            map[VAN_ARC_POWER_SHOT] = arrayOf(VAN_ARC_QUICK_SHOT)
            map[VAN_ARC_QUICK_SHOT] = arrayOf(VAN_ARC_BULLSEYE)
        }
        EMainSkill.SKILL_BLOCK -> {
            map[VAN_BLC_SHIELD_WALL] = arrayOf(VAN_BLC_POWER_BASH, VAN_BLC_DEFLECT_ARROWS, VAN_BLC_QUICK_REFLEXES)
            map[VAN_BLC_DEFLECT_ARROWS] = arrayOf(VAN_BLC_ELEMENTAL_PROTECTION)
            map[VAN_BLC_ELEMENTAL_PROTECTION] = arrayOf(VAN_BLC_BLOCK_RUNNER)
            map[VAN_BLC_BLOCK_RUNNER] = arrayOf(VAN_BLC_SHIELD_CHARGE)
            map[VAN_BLC_POWER_BASH] = arrayOf(VAN_BLC_DEADLY_BASH)
            map[VAN_BLC_DEADLY_BASH] = arrayOf(VAN_BLC_DISARMING_BASH)
            map[VAN_BLC_DISARMING_BASH] = arrayOf(VAN_BLC_SHIELD_CHARGE)
        }
        EMainSkill.SKILL_ENCHANTING -> {
            map[VAN_ENC_ENCHANTER] = arrayOf(VAN_ENC_SOUL_SQUEEZER, VAN_ENC_INSIGHTFUL_ENCHANTER, VAN_ENC_FIRE_ENCHANTER)
            map[VAN_ENC_FIRE_ENCHANTER] = arrayOf(VAN_ENC_FROST_ENCHANTER)
            map[VAN_ENC_FROST_ENCHANTER] = arrayOf(VAN_ENC_STORM_ENCHANTER)
            map[VAN_ENC_STORM_ENCHANTER] = arrayOf(VAN_ENC_EXTRA_EFFECT)
            map[VAN_ENC_INSIGHTFUL_ENCHANTER] = arrayOf(VAN_ENC_CORPUS_ENCHANTER)
            map[VAN_ENC_CORPUS_ENCHANTER] = arrayOf(VAN_ENC_EXTRA_EFFECT)
            map[VAN_ENC_SOUL_SQUEEZER] = arrayOf(VAN_ENC_SOUL_SIPHON)
        }
        EMainSkill.SKILL_LIGHT_ARMOR -> {
            map[VAN_LAR_AGILE_DEFENDER] = arrayOf(VAN_LAR_CUSTOM_FIT)
            map[VAN_LAR_CUSTOM_FIT] = arrayOf(VAN_LAR_UNHINDERED, VAN_LAR_MATCHING_SET)
            map[VAN_LAR_MATCHING_SET] = arrayOf(VAN_LAR_DEFT_MOVEMENT)
            map[VAN_LAR_UNHINDERED] = arrayOf(VAN_LAR_WIND_WALKER)
            map[VAN_LAR_WIND_WALKER] = arrayOf(VAN_LAR_DEFT_MOVEMENT)
        }
        EMainSkill.SKILL_ONE_HANDED -> {
            map[VAN_ONH_ARMSMAN] = arrayOf(VAN_ONH_DUAL_FLURRY, VAN_ONH_BLADESMAN, VAN_ONH_BONE_BREAKER, VAN_ONH_FIGHTING_STANCE, VAN_ONH_HACK_AND_SLASH)
            map[VAN_ONH_DUAL_FLURRY] = arrayOf(VAN_ONH_DUAL_SAVAGERY)
            map[VAN_ONH_FIGHTING_STANCE] = arrayOf(VAN_ONH_CRITICAL_CHARGE, VAN_ONH_SAVAGE_STRIKE)
            map[VAN_ONH_CRITICAL_CHARGE] = arrayOf(VAN_ONH_PARALYZING_STRIKE)
            map[VAN_ONH_SAVAGE_STRIKE] = arrayOf(VAN_ONH_PARALYZING_STRIKE)
        }
        EMainSkill.SKILL_PICKPOCKET -> {
            map[VAN_PCK_LIGHT_FINGERS] = arrayOf(VAN_PCK_NIGHT_THIEF)
            map[VAN_PCK_NIGHT_THIEF] = arrayOf(VAN_PCK_POISONED, VAN_PCK_EXTRA_POCKETS, VAN_PCK_CUTPURSE)
            map[VAN_PCK_CUTPURSE] = arrayOf(VAN_PCK_KEYMASTER, VAN_PCK_MISDIRECTION)
            map[VAN_PCK_MISDIRECTION] = arrayOf(VAN_PCK_PERFECT_TOUCH)
        }
        EMainSkill.SKILL_RESTORATION -> {
            map[VAN_RST_NOVICE_RESTORATION] = arrayOf(VAN_RST_APPRENTICE_RESTORATION, VAN_RST_RECOVERY, VAN_RST_RESTORATION_DUAL_CASTING, VAN_RST_REGENERATION, VAN_RST_RESPITE, VAN_RST_WARD_ABSORB)
            map[VAN_RST_APPRENTICE_RESTORATION] = arrayOf(VAN_RST_ADEPT_RESTORATION)
            map[VAN_RST_ADEPT_RESTORATION] = arrayOf(VAN_RST_EXPERT_RESTORATION)
            map[VAN_RST_EXPERT_RESTORATION] = arrayOf(VAN_RST_MASTER_RESTORATION)
            map[VAN_RST_RECOVERY] = arrayOf(VAN_RST_AVOID_DEATH)
            map[VAN_RST_REGENERATION] = arrayOf(VAN_RST_NECROMAGE)
        }
        EMainSkill.SKILL_SNEAK -> {
            map[VAN_SNK_STEALTH] = arrayOf(VAN_SNK_MUFFLED_MOVEMENT, VAN_SNK_BACKSTAB)
            map[VAN_SNK_BACKSTAB] = arrayOf(VAN_SNK_DEADLY_AIM)
            map[VAN_SNK_DEADLY_AIM] = arrayOf(VAN_SNK_ASSASSINS_BLADE)
            map[VAN_SNK_MUFFLED_MOVEMENT] = arrayOf(VAN_SNK_LIGHT_FOOT)
            map[VAN_SNK_LIGHT_FOOT] = arrayOf(VAN_SNK_SILENT_ROLL)
            map[VAN_SNK_SILENT_ROLL] = arrayOf(VAN_SNK_SILENCE)
            map[VAN_SNK_SILENCE] = arrayOf(VAN_SNK_SHADOW_WARRIOR)
        }
        EMainSkill.SKILL_SPEECH -> {
            map[VAN_SPC_HAGGLING] = arrayOf(VAN_SPC_BRIBERY, VAN_SPC_ALLURE)
            map[VAN_SPC_ALLURE] = arrayOf(VAN_SPC_MERCHANT)
            map[VAN_SPC_MERCHANT] = arrayOf(VAN_SPC_INVESTOR)
            map[VAN_SPC_INVESTOR] = arrayOf(VAN_SPC_FENCE)
            map[VAN_SPC_FENCE] = arrayOf(VAN_SPC_MASTER_TRADER)
            map[VAN_SPC_BRIBERY] = arrayOf(VAN_SPC_PERSUASION)
            map[VAN_SPC_PERSUASION] = arrayOf(VAN_SPC_INTIMIDATION)
        }
        EMainSkill.SKILL_TWO_HANDED -> {
            map[VAN_TWH_BARBARIAN] = arrayOf(VAN_TWH_CHAMPIONS_STANCE, VAN_TWH_SKULLCRUSHER, VAN_TWH_DEEP_WOUNDS, VAN_TWH_LIMBSPLITTER)
            map[VAN_TWH_CHAMPIONS_STANCE] = arrayOf(VAN_TWH_DEVASTATING_BLOW, VAN_TWH_GREAT_CRITICAL_CHARGE)
            map[VAN_TWH_DEVASTATING_BLOW] = arrayOf(VAN_TWH_SWEEP)
            map[VAN_TWH_GREAT_CRITICAL_CHARGE] = arrayOf(VAN_TWH_SWEEP)
            map[VAN_TWH_SWEEP] = arrayOf(VAN_TWH_WARMASTER)
        }
    }
    return map
}