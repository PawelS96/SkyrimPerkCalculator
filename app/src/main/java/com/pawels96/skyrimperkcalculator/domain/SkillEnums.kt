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
import com.pawels96.skyrimperkcalculator.domain.skills_adamant.*

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

            ADAMANT -> return when (this) {
                SKILL_LOCKPICKING -> Ada_Lockpicking.values()
                SKILL_ALCHEMY -> Ada_Alchemy.values()
                SKILL_HEAVY_ARMOR -> Ada_HeavyArmor.values()
                SKILL_ILLUSION -> Ada_Illusion.values()
                SKILL_CONJURATION -> Ada_Conjuration.values()
                SKILL_DESTRUCTION -> Ada_Destruction.values()
                SKILL_BLOCK -> Ada_Block.values()
                SKILL_SNEAK -> Ada_Sneak.values()
                SKILL_SPEECH -> Ada_Speech.values()
                SKILL_ARCHERY -> Ada_Archery.values()
                SKILL_SMITHING -> Ada_Smithing.values()
                SKILL_ALTERATION -> Ada_Alteration.values()
                SKILL_ENCHANTING -> Ada_Enchanting.values()
                SKILL_ONE_HANDED -> Ada_OneHanded.values()
                SKILL_PICKPOCKET -> Ada_Pickpocket.values()
                SKILL_TWO_HANDED -> Ada_TwoHanded.values()
                SKILL_LIGHT_ARMOR -> Ada_LightArmor.values()
                SKILL_RESTORATION -> Ada_Restoration.values()
            } as Array<IPerk>
        }
    }
}