package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Gro_Lycanthropy
import com.pawels96.skyrimperkcalculator.domain.lycanthropy.Lycanthropy
import com.pawels96.skyrimperkcalculator.domain.vampirism.Sac_Vampirism
import com.pawels96.skyrimperkcalculator.domain.vampirism.Vampirism
import java.io.Serializable
import java.util.*

interface ISkill : Serializable {
    val type: SkillType

    fun getPerks(system: IPerkSystem): Array<IPerk>

    fun getConnectionsMap(system: IPerkSystem): Map<IPerk, Array<IPerk>>
}

class SpecialSkill(iskill: ESpecialSkill, perkSystem: IPerkSystem, perks: Map<IPerk, Perk>) : Skill(iskill, perkSystem, perks) {

    private val vanillaVampirePerkTable = IntRange(1, Vampirism.values().sumBy { maxPerks }).map {
        it to when (it) {
            0 -> 0
            1 -> 5
            2 -> 6
            3 -> 9
            else -> it * 2 + 2
        }
    }.toMap()

    private val vanillaWerewolfPerkTable = IntRange(1, Lycanthropy.values().sumBy { maxPerks }).map {
        it to when (it) {
            0 -> 0
            2 -> 6
            else -> it * 2 + 3
        }
    }.toMap()

    private val sacrosanctPerkTable = IntRange(1, Sac_Vampirism.values().sumBy { maxPerks }).map {
        it to when (it) {
            0 -> 0
            1 -> 4
            else -> it + 4
        }
    }.toMap()

    private val growlPerkTable = IntRange(1, Gro_Lycanthropy.values().sumBy { maxPerks }).map {
        it to when (it) {
            0 -> 0
            else -> it + 4
        }
    }.toMap()

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
        val perks: MutableMap<IPerk, Perk> = HashMap()

        for (element in skillEnum.getPerks(perkSystem)) {
            val perk = Perk(element)
            perks[perk.perk] = perk
        }

        for (start in skillEnum.getConnectionsMap(perkSystem).keys) {
            for (end in skillEnum.getConnectionsMap(perkSystem)[start]!!) {
                Perk.connectPerks(perks[start]!!, perks[end]!!)
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

    val maxPerks = perks.values.sumBy { it.maxState }

    val type: SkillType
        get() = iskill.type

    val selectedPerksCount: Int
        get() {
            var count = 0
            for (p in perks.values) {
                count += p.state
            }
            return count
        }

    open val requiredSkillLevel: Int
        get() {
            var level = 0
            for (p in perks.values) {
                if (!p.isSelected) continue
                val perkSkillLevel = p.skillLevel
                if (perkSkillLevel > level) level = perkSkillLevel
            }
            return level
        }

    val perkList: List<Perk>
        get() = perks.values.toList()

    operator fun get(name: IPerk): Perk? {
        return perks[name]
    }

    fun getCoordinates(): Map<IPerk, FPoint> {
        return perks.map { it.key to FPoint(it.value.perk.perkInfo.x, it.value.perk.perkInfo.y) }.toMap()
    }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is Skill && iskill == other.iskill && perkSystem == other.perkSystem && perks == other.perks)
    }
}