package com.pawels96.skyrimperkcalculator.domain.skills_adamant

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import java.util.HashMap
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Illusion.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Alchemy.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Block.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Alteration.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Archery.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Lockpicking.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_HeavyArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Conjuration.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Restoration.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Enchanting.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_LightArmor.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_OneHanded.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_TwoHanded.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Pickpocket.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Smithing.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Speech.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Destruction.*
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.Ada_Sneak.*

fun EMainSkill.getAdamantConnections(): Map<IPerk, Array<IPerk>> {
    val map: MutableMap<IPerk, Array<IPerk>> = HashMap()
    when (this) {
        EMainSkill.SKILL_HEAVY_ARMOR -> {
            map[ADA_HAR_DEFENDER] =
                arrayOf(ADA_HAR_CONDITIONING, ADA_HAR_FISTS_OF_STEEL, ADA_HAR_JUGGERNAUT)
            map[ADA_HAR_CONDITIONING] = arrayOf(ADA_HAR_IMMOVABLE)
            map[ADA_HAR_IMMOVABLE] = arrayOf(ADA_HAR_CONSTITUTION)
            map[ADA_HAR_CONSTITUTION] = arrayOf(ADA_HAR_INVINCIBLE)
            map[ADA_HAR_FISTS_OF_STEEL] =
                arrayOf(ADA_HAR_FISTS_OF_WAR, ADA_HAR_BRACE, ADA_HAR_FISTS_OF_FURY)
            map[ADA_HAR_FISTS_OF_WAR] = arrayOf(ADA_HAR_OVERWHELM)
            map[ADA_HAR_FISTS_OF_FURY] = arrayOf(ADA_HAR_KNOCKOUT_PUNCH)
            map[ADA_HAR_JUGGERNAUT] = arrayOf(ADA_HAR_UNSTOPPABLE)
            map[ADA_HAR_UNSTOPPABLE] = arrayOf(ADA_HAR_PERFECT_FIT)
            map[ADA_HAR_PERFECT_FIT] = arrayOf(ADA_HAR_DEFIANCE)
        }
        EMainSkill.SKILL_LOCKPICKING -> {
            map[ADA_LCK_LOCKSMITH] = arrayOf(ADA_LCK_GOLDEN_TOUCH)
            map[ADA_LCK_GOLDEN_TOUCH] = arrayOf(ADA_LCK_QUICK_HANDS, ADA_LCK_RARE_GEMS)
            map[ADA_LCK_RARE_GEMS] = arrayOf(ADA_LCK_DEEP_POCKETS, ADA_LCK_DUNGEON_DELVER)
            map[ADA_LCK_DUNGEON_DELVER] = arrayOf(ADA_LCK_TUMBLERBANE, ADA_LCK_TREASURE_HUNTER)
        }
        EMainSkill.SKILL_ALCHEMY -> {
            map[ADA_ALC_HERBALIST] =
                arrayOf(ADA_ALC_INTENSITY, ADA_ALC_EXPERIMENTER, ADA_ALC_CONCENTRATION)
            map[ADA_ALC_INTENSITY] = arrayOf(ADA_ALC_SOLVENCY)
            map[ADA_ALC_SOLVENCY] = arrayOf(ADA_ALC_CHEMIST)
            map[ADA_ALC_EXPERIMENTER] = arrayOf(ADA_ALC_GREEN_THUMB)
            map[ADA_ALC_CONCENTRATION] = arrayOf(ADA_ALC_POTENCY)
            map[ADA_ALC_POTENCY] = arrayOf(ADA_ALC_CHEMIST)
        }
        EMainSkill.SKILL_ILLUSION -> {
            map[ADA_ILU_ILLUSIONIST] = arrayOf(
                ADA_ILU_SERENITY,
                ADA_ILU_GUIDANCE,
                ADA_ILU_CAPTIVATING_PRESENCE,
                ADA_ILU_HOWL_OF_RAGE
            )
            map[ADA_ILU_SERENITY] = arrayOf(ADA_ILU_TRANQUILITY)
            map[ADA_ILU_TRANQUILITY] = arrayOf(ADA_ILU_STASIS)
            map[ADA_ILU_CAPTIVATING_PRESENCE] = arrayOf(ADA_ILU_INDOMITABLE_WILL)
            map[ADA_ILU_INDOMITABLE_WILL] = arrayOf(ADA_ILU_MASTER_OF_THE_MIND)
            map[ADA_ILU_HOWL_OF_RAGE] = arrayOf(ADA_ILU_ASPECT_OF_TERROR)
            map[ADA_ILU_ASPECT_OF_TERROR] = arrayOf(ADA_ILU_VOICE_OF_AUTHORITY)

        }
        EMainSkill.SKILL_CONJURATION -> {
            map[ADA_CON_SUMMONER] = arrayOf(
                ADA_CON_DARK_OATH,
                ADA_CON_UNDEAD_SERVANT,
                ADA_CON_CULTIST,
                ADA_CON_ARCANE_BINDING
            )
            map[ADA_CON_DARK_OATH] = arrayOf(ADA_CON_ARMOR_OF_SHADOWS)
            map[ADA_CON_ARMOR_OF_SHADOWS] = arrayOf(ADA_CON_RITUAL_OF_POWER)
            map[ADA_CON_RITUAL_OF_POWER] = arrayOf(ADA_CON_DAEDRIC_PACT)
            map[ADA_CON_DAEDRIC_PACT] = arrayOf(ADA_CON_DOORS_OF_OBLIVION)
            map[ADA_CON_UNDEAD_SERVANT] = arrayOf(ADA_CON_DEATHLY_VIGOR)
            map[ADA_CON_DEATHLY_VIGOR] = arrayOf(ADA_CON_CORPSE_PREPARATION)
            map[ADA_CON_CORPSE_PREPARATION] = arrayOf(ADA_CON_NECROPOTENCE)
            map[ADA_CON_NECROPOTENCE] = arrayOf(ADA_CON_DOORS_OF_OBLIVION)
            map[ADA_CON_ARCANE_BINDING] = arrayOf(ADA_CON_CHAOS_BINDING)
            map[ADA_CON_CHAOS_BINDING] = arrayOf(ADA_CON_MYSTIC_BINDING)
            map[ADA_CON_MYSTIC_BINDING] = arrayOf(ADA_CON_CURSE_BINDING)
        }
        EMainSkill.SKILL_DESTRUCTION -> {
            map[ADA_DES_ELEMENTALIST] = arrayOf(
                ADA_DES_RUNE_MAGE,
                ADA_DES_AUGMENTED_FLAMES,
                ADA_DES_AUGMENTED_FROST,
                ADA_DES_AUGMENTED_SHOCK,
                ADA_DES_SPELL_SURGE
            )
            map[ADA_DES_AUGMENTED_FLAMES] = arrayOf(ADA_DES_FIREBRAND)
            map[ADA_DES_FIREBRAND] = arrayOf(ADA_DES_COMBUSTION)
            map[ADA_DES_COMBUSTION] = arrayOf(ADA_DES_WILDFIRE)
            map[ADA_DES_AUGMENTED_FROST] = arrayOf(ADA_DES_NORTH_WIND)
            map[ADA_DES_NORTH_WIND] = arrayOf(ADA_DES_PERMAFROST)
            map[ADA_DES_PERMAFROST] = arrayOf(ADA_DES_DEEP_FREEZE)
            map[ADA_DES_AUGMENTED_SHOCK] = arrayOf(ADA_DES_UNSTABLE_CURRENT)
            map[ADA_DES_UNSTABLE_CURRENT] = arrayOf(ADA_DES_STATIC_FIELD)
            map[ADA_DES_STATIC_FIELD] = arrayOf(ADA_DES_POWER_SURGE)
            map[ADA_DES_SPELL_SURGE] = arrayOf(ADA_DES_IMPACT)
        }
        EMainSkill.SKILL_SMITHING -> {
            map[ADA_SMT_CRAFTSMAN] = arrayOf(ADA_SMT_BLACKSMITH, ADA_SMT_BASIC_SMITHING)
            map[ADA_SMT_BLACKSMITH] = arrayOf(ADA_SMT_ARMORER)
            map[ADA_SMT_ARMORER] = arrayOf(ADA_SMT_FORGEMASTER)
            map[ADA_SMT_BASIC_SMITHING] = arrayOf(ADA_SMT_JOURNEYMAN_SMITHING)
            map[ADA_SMT_JOURNEYMAN_SMITHING] =
                arrayOf(ADA_SMT_RARE_SMITHING, ADA_SMT_INTERMEDIATE_SMITHING)
            map[ADA_SMT_RARE_SMITHING] = arrayOf(ADA_SMT_EXOTIC_SMITHING)
            map[ADA_SMT_EXOTIC_SMITHING] = arrayOf(ADA_SMT_MYTHIC_SMITHING)
            map[ADA_SMT_INTERMEDIATE_SMITHING] = arrayOf(ADA_SMT_ADVANCED_SMITHING)
            map[ADA_SMT_ADVANCED_SMITHING] = arrayOf(ADA_SMT_MYTHIC_SMITHING)
        }
        EMainSkill.SKILL_ALTERATION -> {
            map[ADA_ALT_PHILOSOPHER] = arrayOf(ADA_ALT_MAGE_ROBES, ADA_ALT_BARRIER, ADA_ALT_BALANCE)
            map[ADA_ALT_MAGE_ROBES] = arrayOf(ADA_ALT_MAGE_ARMOR)
            map[ADA_ALT_MAGE_ARMOR] = arrayOf(ADA_ALT_MEDITATION, ADA_ALT_MAGICKA_WELL)
            map[ADA_ALT_BARRIER] = arrayOf(ADA_ALT_BASTION)
            map[ADA_ALT_BALANCE] = arrayOf(ADA_ALT_STABILITY)
            map[ADA_ALT_STABILITY] = arrayOf(ADA_ALT_SPELL_SIP, ADA_ALT_SPELL_SHIELD)
        }
        EMainSkill.SKILL_ARCHERY -> {
            map[ADA_ARC_MARKSMAN] = arrayOf(ADA_ARC_EAGLE_EYE, ADA_ARC_QUICK_DRAW)
            map[ADA_ARC_EAGLE_EYE] = arrayOf(ADA_ARC_GRIM_FOCUS)
            map[ADA_ARC_GRIM_FOCUS] = arrayOf(ADA_ARC_STEADY_HAND)
            map[ADA_ARC_STEADY_HAND] = arrayOf(ADA_ARC_BULLSEYE)
            map[ADA_ARC_QUICK_DRAW] = arrayOf(ADA_ARC_POWER_SHOT)
            map[ADA_ARC_POWER_SHOT] = arrayOf(ADA_ARC_RANGER)
            map[ADA_ARC_RANGER] = arrayOf(ADA_ARC_BULLSEYE)
        }
        EMainSkill.SKILL_BLOCK -> {
            map[ADA_BLC_GLADIATOR] =
                arrayOf(ADA_BLC_DISCIPLINE, ADA_BLC_DEFENSIVE_MANEUVERS, ADA_BLC_DEADLY_BASH)
            map[ADA_BLC_DISCIPLINE] = arrayOf(ADA_BLC_DEFLECTION)
            map[ADA_BLC_DEFLECTION] = arrayOf(ADA_BLC_DETERMINATION)
            map[ADA_BLC_DETERMINATION] = arrayOf(ADA_BLC_DELIVERANCE)
            map[ADA_BLC_DEADLY_BASH] = arrayOf(ADA_BLC_STUNNING_STRIKE)
            map[ADA_BLC_STUNNING_STRIKE] = arrayOf(ADA_BLC_DISORIENTING_BLOW)
            map[ADA_BLC_DISORIENTING_BLOW] = arrayOf(ADA_BLC_BATTERING_RAM)
        }
        EMainSkill.SKILL_ENCHANTING -> {
            map[ADA_ENC_ARTIFICER] = arrayOf(
                ADA_ENC_SEEKER,
                ADA_ENC_SCRIBE,
                ADA_ENC_JEWELRY_ENCHANTER,
                ADA_ENC_ARMOR_ENCHANTER,
                ADA_ENC_CONDUIT,
                ADA_ENC_RESONANCE
            )
            map[ADA_ENC_JEWELRY_ENCHANTER] = arrayOf(ADA_ENC_CORPUS_ENCHANTER)
            map[ADA_ENC_CORPUS_ENCHANTER] = arrayOf(ADA_ENC_INSIGHTFUL_ENCHANTER)
            map[ADA_ENC_INSIGHTFUL_ENCHANTER] = arrayOf(ADA_ENC_TWIN_SECRETS)
            map[ADA_ENC_ARMOR_ENCHANTER] = arrayOf(ADA_ENC_ELEMENTAL_ENCHANTER)
            map[ADA_ENC_ELEMENTAL_ENCHANTER] = arrayOf(ADA_ENC_INSIGHTFUL_ENCHANTER)
            map[ADA_ENC_CONDUIT] = arrayOf(ADA_ENC_CHANNELER)
            map[ADA_ENC_RESONANCE] = arrayOf(ADA_ENC_SOUL_SIPHON)
        }
        EMainSkill.SKILL_LIGHT_ARMOR -> {
            map[ADA_LAR_SCOUT] = arrayOf(ADA_LAR_AGILITY, ADA_LAR_SPECIALIST)
            map[ADA_LAR_AGILITY] = arrayOf(ADA_LAR_ATHLETICS)
            map[ADA_LAR_ATHLETICS] = arrayOf(ADA_LAR_ADRENALINE)
            map[ADA_LAR_ADRENALINE] = arrayOf(ADA_LAR_SECOND_WIND)
            map[ADA_LAR_SPECIALIST] = arrayOf(ADA_LAR_UNHINDERED)
            map[ADA_LAR_UNHINDERED] = arrayOf(ADA_LAR_CUSTOM_FIT)
            map[ADA_LAR_CUSTOM_FIT] = arrayOf(ADA_LAR_ENDURANCE)
        }
        EMainSkill.SKILL_ONE_HANDED -> {
            map[ADA_ONH_SKIRMISHER] = arrayOf(
                ADA_ONH_QUICK_SLASH,
                ADA_ONH_FIGHTERS_STANCE,
                ADA_ONH_HACK_AND_SLASH,
                ADA_ONH_ARMOR_BREAKER,
                ADA_ONH_DUAL_FOCUS
            )
            map[ADA_ONH_QUICK_SLASH] = arrayOf(ADA_ONH_PRECISE_CUTS)
            map[ADA_ONH_FIGHTERS_STANCE] =
                arrayOf(ADA_ONH_OVERRUN, ADA_ONH_FLOURISH, ADA_ONH_EXECUTE)
            map[ADA_ONH_OVERRUN] = arrayOf(ADA_ONH_ONSLAUGHT)
            map[ADA_ONH_EXECUTE] = arrayOf(ADA_ONH_ONSLAUGHT)
            map[ADA_ONH_HACK_AND_SLASH] = arrayOf(ADA_ONH_CARVE_AND_SPIT)
            map[ADA_ONH_ARMOR_BREAKER] = arrayOf(ADA_ONH_BELL_RINGER)
            map[ADA_ONH_DUAL_FOCUS] = arrayOf(ADA_ONH_DUAL_FRENZY)
            map[ADA_ONH_DUAL_FRENZY] = arrayOf(ADA_ONH_DUAL_FURY)
        }
        EMainSkill.SKILL_PICKPOCKET -> {
            map[ADA_PCK_CUTPURSE] = arrayOf(ADA_PCK_NIMBLE_FINGERS)
            map[ADA_PCK_NIMBLE_FINGERS] =
                arrayOf(ADA_PCK_SLEIGHT_OF_HAND, ADA_PCK_PRACTICED_THIEF, ADA_PCK_POISONED_FRUIT)
            map[ADA_PCK_PRACTICED_THIEF] = arrayOf(ADA_PCK_MISDIRECTION)
            map[ADA_PCK_MISDIRECTION] = arrayOf(ADA_PCK_PERFECT_TOUCH)
            map[ADA_PCK_POISONED_FRUIT] = arrayOf(ADA_PCK_FOOLS_GIFT)
        }
        EMainSkill.SKILL_RESTORATION -> {
            map[ADA_RES_HEALER] = arrayOf(
                ADA_RES_PILGRIM,
                ADA_RES_DIVINE_GLORY,
                ADA_RES_AFFLICTION,
                ADA_RES_RECOVERY,
                ADA_RES_EMPOWERED_WARD,
                ADA_RES_DAWNS_WRATH,
                ADA_RES_ILLUMINATION
            )
            map[ADA_RES_DIVINE_GLORY] = arrayOf(ADA_RES_POWER_OF_THE_LIGHT)
            map[ADA_RES_AFFLICTION] = arrayOf(ADA_RES_PLAGUE)
            map[ADA_RES_PLAGUE] = arrayOf(ADA_RES_SCOURGE)
            map[ADA_RES_RECOVERY] = arrayOf(ADA_RES_RESPITE, ADA_RES_REPOSE)
            map[ADA_RES_RESPITE] = arrayOf(ADA_RES_RESOLVE)
            map[ADA_RES_REPOSE] = arrayOf(ADA_RES_RESOLVE)
            map[ADA_RES_RESOLVE] = arrayOf(ADA_RES_RENEWAL)
            map[ADA_RES_EMPOWERED_WARD] = arrayOf(ADA_RES_RADIANT_WARD)
            map[ADA_RES_RADIANT_WARD] = arrayOf(ADA_RES_PRISMATIC_WARD)
            map[ADA_RES_DAWNS_WRATH] = arrayOf(ADA_RES_BURNING_LIGHT)
        }
        EMainSkill.SKILL_SNEAK -> {
            map[ADA_SNK_AGENT] = arrayOf(
                ADA_SNK_TRESPASSER,
                ADA_SNK_SILENT_CASTING,
                ADA_SNK_DEADLY_AIM,
                ADA_SNK_MERCILESS
            )
            map[ADA_SNK_TRESPASSER] = arrayOf(ADA_SNK_INFILTRATOR)
            map[ADA_SNK_INFILTRATOR] = arrayOf(ADA_SNK_HIDDEN_THREAT)
            map[ADA_SNK_HIDDEN_THREAT] = arrayOf(ADA_SNK_LIVING_SHADOW)
            map[ADA_SNK_SILENT_CASTING] = arrayOf(ADA_SNK_SHADOW_CASTING)
            map[ADA_SNK_MERCILESS] = arrayOf(ADA_SNK_BACKSTAB)
            map[ADA_SNK_BACKSTAB] = arrayOf(ADA_SNK_ASSASSINS_BLADE)
        }
        EMainSkill.SKILL_SPEECH -> {
            map[ADA_SPC_MERCHANT] =
                arrayOf(ADA_SPC_DEEP_BREATH, ADA_SPC_SILVER_TONGUE, ADA_SPC_SUPPLY_AND_DEMAND)
            map[ADA_SPC_DEEP_BREATH] = arrayOf(ADA_SPC_ANCIENT_VOICE)
            map[ADA_SPC_ANCIENT_VOICE] = arrayOf(ADA_SPC_STORMCROWN)
            map[ADA_SPC_STORMCROWN] = arrayOf(ADA_SPC_DRAGON_OF_THE_NORTH)
            map[ADA_SPC_SUPPLY_AND_DEMAND] = arrayOf(ADA_SPC_INVESTOR, ADA_SPC_BLACK_MARKET)
            map[ADA_SPC_INVESTOR] = arrayOf(ADA_SPC_ENTREPRENEUR)
        }
        EMainSkill.SKILL_TWO_HANDED -> {
            map[ADA_TWH_CHAMPION] = arrayOf(
                ADA_TWH_DEEP_CUTS,
                ADA_TWH_WARRIORS_STANCE,
                ADA_TWH_RIP_AND_TEAR,
                ADA_TWH_ARMOR_CRUSHER
            )
            map[ADA_TWH_DEEP_CUTS] = arrayOf(ADA_TWH_MORTAL_WOUND)
            map[ADA_TWH_WARRIORS_STANCE] =
                arrayOf(ADA_TWH_OVERPOWER, ADA_TWH_CLEAVE, ADA_TWH_MASSACRE)
            map[ADA_TWH_OVERPOWER] = arrayOf(ADA_TWH_RAMPAGE)
            map[ADA_TWH_MASSACRE] = arrayOf(ADA_TWH_RAMPAGE)
            map[ADA_TWH_RIP_AND_TEAR] = arrayOf(ADA_TWH_REND_AND_RAKE)
            map[ADA_TWH_ARMOR_CRUSHER] = arrayOf(ADA_TWH_SKULL_CRACKER)
        }
    }
    return map
}