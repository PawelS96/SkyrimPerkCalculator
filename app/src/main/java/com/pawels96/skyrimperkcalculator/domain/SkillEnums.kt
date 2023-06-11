package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.domain.PerkSystem.*
import com.pawels96.skyrimperkcalculator.domain.SkillType.*
import com.pawels96.skyrimperkcalculator.domain.skills_ordinator.*
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.*
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.*
import com.pawels96.skyrimperkcalculator.domain.vampirism.Vampirism
import com.pawels96.skyrimperkcalculator.domain.vampirism.Vampirism.*
import com.pawels96.skyrimperkcalculator.domain.vampirism.Sac_Vampirism.*
import com.pawels96.skyrimperkcalculator.domain.vampirism.Sac_Vampirism
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Lycanthropy
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Lycanthropy.*
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Gro_Lycanthropy.*
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Gro_Lycanthropy

enum class ESpecialSkill : ISkill {
    SKILL_VAMPIRISM,
    SKILL_LYCANTHROPY;

    override val type: SkillType
        get() = SPECIAL

    @Suppress("UNCHECKED_CAST")
    override fun getPerks(system: IPerkSystem): Array<IPerk> {

        return when (this) {
            SKILL_VAMPIRISM -> when (system as VampirePerkSystem) {
                VampirePerkSystem.VANILLA -> Vampirism.values() as Array<IPerk>
                VampirePerkSystem.SACROSANCT -> Sac_Vampirism.values() as Array<IPerk>
            }
            SKILL_LYCANTHROPY -> when (system as WerewolfPerkSystem) {
                WerewolfPerkSystem.VANILLA -> Lycanthropy.values() as Array<IPerk>
                WerewolfPerkSystem.GROWL -> Gro_Lycanthropy.values() as Array<IPerk>
            }
        }
    }

    private fun vanillaVampireConnections(): Map<IPerk, Array<IPerk>> {

        val map = mutableMapOf<IPerk, Array<IPerk>>()

        map[VAN_VAM_POWER_OF_THE_GRAVE] = arrayOf(VAN_VAM_DETECT_ALL_CREATURES, VAN_VAM_VAMPIRIC_GRIP, VAN_VAM_UNEARTHLY_WILL, VAN_VAM_BLOOD_HEALING)
        map[VAN_VAM_DETECT_ALL_CREATURES] = arrayOf(VAN_VAM_MIST_FORM)
        map[VAN_VAM_MIST_FORM] = arrayOf(VAN_VAM_SUPERNATURAL_REFLEXES)
        map[VAN_VAM_UNEARTHLY_WILL] = arrayOf(VAN_VAM_POISON_TALONS)
        map[VAN_VAM_BLOOD_HEALING] = arrayOf(VAN_VAM_POISON_TALONS)
        map[VAN_VAM_POISON_TALONS] = arrayOf(VAN_VAM_NIGHT_CLOAK)
        map[VAN_VAM_VAMPIRIC_GRIP] = arrayOf(VAN_VAM_SUMMON_GARGOYLE)
        map[VAN_VAM_SUMMON_GARGOYLE] = arrayOf(VAN_VAM_CORPSE_CURSE)

        return map
    }

    private fun sacrosanctConnection(): Map<IPerk, Array<IPerk>> {

        val map = mutableMapOf<IPerk, Array<IPerk>>()

        map[SAC_VAM_POWER_IS_POWER] = arrayOf(SAC_VAM_ECHOLOCATION, SAC_VAM_FOUNTAIN_OF_YOUTH, SAC_VAM_WINGS_OF_THE_STRIX, SAC_VAM_UNEARTHLY_WILL, SAC_VAM_COURTS_CHEF, SAC_VAM_EXSANGUINATE, SAC_VAM_CHOKEHOLD)
        map[SAC_VAM_ECHOLOCATION] = arrayOf(SAC_VAM_MIST_FORM)
        map[SAC_VAM_MIST_FORM] = arrayOf(SAC_VAM_THE_REAPING)
        map[SAC_VAM_THE_REAPING] = arrayOf(SAC_VAM_TREMBLE)
        map[SAC_VAM_COURTS_CHEF] = arrayOf(SAC_VAM_WHITE_WOLF)
        map[SAC_VAM_WHITE_WOLF] = arrayOf(SAC_VAM_PSYCHIC_VAMPIRE)
        map[SAC_VAM_PSYCHIC_VAMPIRE] = arrayOf(SAC_VAM_EMBRACE_THE_BEAST, SAC_VAM_FOSTER_CHILDE)
        map[SAC_VAM_FOSTER_CHILDE] = arrayOf(SAC_VAM_AMARANTH)
        map[SAC_VAM_FOUNTAIN_OF_YOUTH] = arrayOf(SAC_VAM_LION_AMONG_SHEEP, SAC_VAM_ASTRAL_POISON)
        map[SAC_VAM_LION_AMONG_SHEEP] = arrayOf(SAC_VAM_SLASHER)
        map[SAC_VAM_SLASHER] = arrayOf(SAC_VAM_CELERITY, SAC_VAM_DRAGON_AT_MIDNIGHT)
        map[SAC_VAM_ASTRAL_POISON] = arrayOf(SAC_VAM_NIGHT_CLOAK)
        map[SAC_VAM_NIGHT_CLOAK] = arrayOf(SAC_VAM_DRAGON_AT_MIDNIGHT)
        map[SAC_VAM_UNEARTHLY_WILL] = arrayOf(SAC_VAM_ASTRAL_POISON, SAC_VAM_BLOOD_FROM_A_STONE)
        map[SAC_VAM_BLOOD_FROM_A_STONE] = arrayOf(SAC_VAM_LORD_OF_DESTRUCTION)
        map[SAC_VAM_LORD_OF_DESTRUCTION] = arrayOf(SAC_VAM_DRAGON_AT_MIDNIGHT, SAC_VAM_BLOOD_STORM)
        map[SAC_VAM_EXSANGUINATE] = arrayOf(SAC_VAM_STARVING_ARTIST)
        map[SAC_VAM_STARVING_ARTIST] = arrayOf(SAC_VAM_MAKE_THEM_BEAUTIFUL)
        map[SAC_VAM_MAKE_THEM_BEAUTIFUL] = arrayOf(SAC_VAM_AUSPEX, SAC_VAM_CURTAIN_CALL)
        map[SAC_VAM_CURTAIN_CALL] = arrayOf(SAC_VAM_LAMAES_PYRE)
        map[SAC_VAM_CHOKEHOLD] = arrayOf(SAC_VAM_CONJURE_GARGOYLE)
        map[SAC_VAM_CONJURE_GARGOYLE] = arrayOf(SAC_VAM_GUTWRENCH)
        map[SAC_VAM_GUTWRENCH] = arrayOf(SAC_VAM_MAELSTROM)

        return map
    }

    private fun vanillaWerewolfConnections(): Map<IPerk, Array<IPerk>> {

        val map = mutableMapOf<IPerk, Array<IPerk>>()

        map[VAN_LYC_BESTIAL_STRENGTH] = arrayOf(VAN_LYC_TOTEM_OF_ICE_BROTHERS, VAN_LYC_TOTEM_OF_THE_PREDATOR, VAN_LYC_TOTEM_OF_TERROR, VAN_LYC_ANIMAL_VIGOR)
        map[VAN_LYC_TOTEM_OF_ICE_BROTHERS] = arrayOf(VAN_LYC_TOTEM_OF_THE_MOON)
        map[VAN_LYC_ANIMAL_VIGOR] = arrayOf(VAN_LYC_GORGING)
        map[VAN_LYC_GORGING] = arrayOf(VAN_LYC_SAVAGE_FEEDING)

        return map
    }

    private fun growlConnections(): Map<IPerk, Array<IPerk>> {
        val map = mutableMapOf<IPerk, Array<IPerk>>()

        map[GRO_LYC_BESTIAL_STRENGTH] = arrayOf(GRO_LYC_BURY_THE_BEAST, GRO_LYC_LYCANTHROPIC_SPEED, GRO_LYC_ANIMAL_VIGOR, GRO_LYC_FERAL_INSTINCTS, GRO_LYC_GORGING)
        map[GRO_LYC_BURY_THE_BEAST] = arrayOf(GRO_LYC_WOLF_AMONG_MEN)
        map[GRO_LYC_LYCANTHROPIC_SPEED] = arrayOf(GRO_LYC_LYCANTHROPIC_REGENERATION)
        map[GRO_LYC_LYCANTHROPIC_REGENERATION] = arrayOf(GRO_LYC_SPREAD_THE_BEASTBLOOD)
        map[GRO_LYC_ANIMAL_VIGOR] = arrayOf(GRO_LYC_INFINITE_DURESS, GRO_LYC_SUPERNATURAL_STRENGTH, GRO_LYC_ROADKILL)
        map[GRO_LYC_INFINITE_DURESS] = arrayOf(GRO_LYC_RAMPAGE)
        map[GRO_LYC_ROADKILL] = arrayOf(GRO_LYC_RAMPAGE)
        map[GRO_LYC_SUPERNATURAL_STRENGTH] = arrayOf(GRO_LYC_RAMPAGE)
        map[GRO_LYC_RAMPAGE] = arrayOf(GRO_LYC_TOTEM_OF_ICE_BROTHERS, GRO_LYC_TOTEM_OF_TERROR, GRO_LYC_TOTEM_OF_THE_HUNT)
        map[GRO_LYC_FERAL_INSTINCTS] = arrayOf(GRO_LYC_IMPROVED_BLOODTHIRST)
        map[GRO_LYC_GORGING] = arrayOf(GRO_LYC_SAVAGE_FEEDING)
        map[GRO_LYC_IMPROVED_BLOODTHIRST] = arrayOf(GRO_LYC_SWIPE)

        return map
    }

    override fun getConnectionsMap(system: IPerkSystem): Map<IPerk, Array<IPerk>> {

        return when (this) {
            SKILL_VAMPIRISM -> when (system as VampirePerkSystem) {
                VampirePerkSystem.VANILLA -> vanillaVampireConnections()
                VampirePerkSystem.SACROSANCT -> sacrosanctConnection()
            }
            SKILL_LYCANTHROPY -> when (system as WerewolfPerkSystem) {
                WerewolfPerkSystem.VANILLA -> vanillaWerewolfConnections()
                WerewolfPerkSystem.GROWL -> growlConnections()
            }
        }
    }
}

enum class EMainSkill(private val skillType: SkillType) : ISkill {

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

    override val type: SkillType
        get() = skillType

    @Suppress("UNCHECKED_CAST")
    override fun getPerks(system: IPerkSystem): Array<IPerk> {
        when (system as PerkSystem) {
            ORDINATOR -> return when (this) {
                SKILL_LOCKPICKING -> Ord_Lockpicking.values()
                SKILL_ALCHEMY -> Ord_Alchemy.values()
                SKILL_HEAVY_ARMOR -> Ord_HeavyArmor.values()
                SKILL_ILLUSION -> Ord_Illusion.values()
                SKILL_CONJURATION -> Ord_Conjuration.values()
                SKILL_DESTRUCTION -> Ord_Destruction.values()
                SKILL_BLOCK -> Ord_Block.values()
                SKILL_SNEAK -> Ord_Sneak.values()
                SKILL_SPEECH -> Ord_Speech.values()
                SKILL_ARCHERY -> Ord_Archery.values()
                SKILL_SMITHING -> Ord_Smithing.values()
                SKILL_ALTERATION -> Ord_Alteration.values()
                SKILL_ENCHANTING -> Ord_Enchanting.values()
                SKILL_ONE_HANDED -> Ord_OneHanded.values()
                SKILL_PICKPOCKET -> Ord_Pickpocket.values()
                SKILL_TWO_HANDED -> Ord_TwoHanded.values()
                SKILL_LIGHT_ARMOR -> Ord_LightArmor.values()
                SKILL_RESTORATION -> Ord_Restoration.values()
            } as Array<IPerk>

            VANILLA -> return when (this) {
                SKILL_LOCKPICKING -> Lockpicking.values()
                SKILL_ALCHEMY -> Alchemy.values()
                SKILL_HEAVY_ARMOR -> HeavyArmor.values()
                SKILL_ILLUSION -> Illusion.values()
                SKILL_CONJURATION -> Conjuration.values()
                SKILL_DESTRUCTION -> Destruction.values()
                SKILL_BLOCK -> Block.values()
                SKILL_SNEAK -> Sneak.values()
                SKILL_SPEECH -> Speech.values()
                SKILL_ARCHERY -> Archery.values()
                SKILL_SMITHING -> Smithing.values()
                SKILL_ALTERATION -> Alteration.values()
                SKILL_ENCHANTING -> Enchanting.values()
                SKILL_ONE_HANDED -> OneHanded.values()
                SKILL_PICKPOCKET -> Pickpocket.values()
                SKILL_TWO_HANDED -> TwoHanded.values()
                SKILL_LIGHT_ARMOR -> LightArmor.values()
                SKILL_RESTORATION -> Restoration.values()
            } as Array<IPerk>

            VOKRII -> return when (this) {
                SKILL_LOCKPICKING -> Vok_Lockpicking.values()
                SKILL_ALCHEMY -> Vok_Alchemy.values()
                SKILL_HEAVY_ARMOR -> Vok_HeavyArmor.values()
                SKILL_ILLUSION -> Vok_Illusion.values()
                SKILL_CONJURATION -> Vok_Conjuration.values()
                SKILL_DESTRUCTION -> Vok_Destruction.values()
                SKILL_BLOCK -> Vok_Block.values()
                SKILL_SNEAK -> Vok_Sneak.values()
                SKILL_SPEECH -> Vok_Speech.values()
                SKILL_ARCHERY -> Vok_Archery.values()
                SKILL_SMITHING -> Vok_Smithing.values()
                SKILL_ALTERATION -> Vok_Alteration.values()
                SKILL_ENCHANTING -> Vok_Enchanting.values()
                SKILL_ONE_HANDED -> Vok_OneHanded.values()
                SKILL_PICKPOCKET -> Vok_Pickpocket.values()
                SKILL_TWO_HANDED -> Vok_TwoHanded.values()
                SKILL_LIGHT_ARMOR -> Vok_LightArmor.values()
                SKILL_RESTORATION -> Vok_Restoration.values()
            } as Array<IPerk>
        }
    }

    override fun getConnectionsMap(system: IPerkSystem): Map<IPerk, Array<IPerk>> {
        return when (system as PerkSystem) {
            ORDINATOR -> getOrdinatorConnections()
            VANILLA -> getVanillaConnections()
            VOKRII -> getVokriiConnections()
        }
    }
}