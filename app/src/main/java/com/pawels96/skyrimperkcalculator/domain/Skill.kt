package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Gro_Lycanthropy
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Lycanthropy
import com.pawels96.skyrimperkcalculator.domain.vampirism.Sac_Vampirism
import com.pawels96.skyrimperkcalculator.domain.vampirism.Vampirism
import java.io.Serializable

interface ISkill : Serializable {

    val type: SkillType

    fun getPerks(system: IPerkSystem): Array<IPerk>
}

class SpecialSkill(iskill: ESpecialSkill, perkSystem: IPerkSystem, perks: Map<IPerk, Perk>) :
    Skill(iskill, perkSystem, perks) {

    private val vanillaVampirePerkTable =
        IntRange(1, Vampirism.values().sumOf { maxPerks }).associateWith {
            when (it) {
                0 -> 0
                1 -> 5
                2 -> 6
                3 -> 9
                else -> it * 2 + 2
            }
        }

    private val vanillaWerewolfPerkTable =
        IntRange(1, Lycanthropy.values().sumOf { maxPerks }).associateWith {
            when (it) {
                0 -> 0
                2 -> 6
                else -> it * 2 + 3
            }
        }

    private val sacrosanctPerkTable =
        IntRange(1, Sac_Vampirism.values().sumOf { maxPerks }).associateWith {
            when (it) {
                0 -> 0
                1 -> 4
                else -> it + 4
            }
        }

    private val growlPerkTable =
        IntRange(1, Gro_Lycanthropy.values().sumOf { maxPerks }).associateWith {
            when (it) {
                0 -> 0
                else -> it + 4
            }
        }

    override val requiredSkillLevel: Int
        get() {

            val perkTable = when (iskill as ESpecialSkill) {
                ESpecialSkill.SKILL_VAMPIRISM -> when (perkSystem) {
                    VampirePerkSystem.VANILLA -> vanillaVampirePerkTable
                    VampirePerkSystem.SACROSANCT -> sacrosanctPerkTable
                    else -> vanillaVampirePerkTable
                }

                ESpecialSkill.SKILL_LYCANTHROPY -> when (perkSystem) {
                    WerewolfPerkSystem.VANILLA -> vanillaWerewolfPerkTable
                    WerewolfPerkSystem.GROWL -> growlPerkTable
                    else -> vanillaWerewolfPerkTable
                }
            }

            return IntRange(0, selectedPerksCount).reduce { acc, i -> acc + (perkTable[i] ?: 0) }
        }

    override fun equals(other: Any?): Boolean {
        if (other !is SpecialSkill)
            return false
        return super.equals(other)
    }
}

object SkillFactory {

    private fun buildPerkTree(skillEnum: ISkill, perkSystem: IPerkSystem): Map<IPerk, Perk> {
        val perks = skillEnum.getPerks(perkSystem).associateWith { Perk(it) }

        perks.entries.forEach { entry ->
            entry.key.childPerks.forEach { child ->
                entry.value.connectChild(perks[child]!!)
            }
        }

        return perks
    }

    fun create(skillEnum: ISkill, perkSystem: IPerkSystem): Skill {

        val perks = buildPerkTree(skillEnum, perkSystem)

        return if (skillEnum is ESpecialSkill)
            SpecialSkill(skillEnum, perkSystem, perks)
        else
            Skill(skillEnum, perkSystem, perks)
    }
}

open class Skill(val iskill: ISkill, val perkSystem: IPerkSystem, val perks: Map<IPerk, Perk>) {

    val maxPerks = perks.values.sumOf { it.maxState }

    val type: SkillType
        get() = iskill.type

    val selectedPerksCount: Int
        get() = perks.values.sumOf { it.state }

    open val requiredSkillLevel: Int
        get() = perks.values.filter { it.isSelected }.maxOfOrNull { it.skillLevel } ?: 0

    val perkList: List<Perk>
        get() = perks.values.toList()

    operator fun get(name: IPerk): Perk? {
        return perks[name]
    }

    fun getCoordinates(): Map<IPerk, FPoint> {
        return perks.map { it.key to FPoint(it.value.perk.perkInfo.x, it.value.perk.perkInfo.y) }
            .toMap()
    }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is Skill && iskill == other.iskill && perkSystem == other.perkSystem && perks == other.perks)
    }
}