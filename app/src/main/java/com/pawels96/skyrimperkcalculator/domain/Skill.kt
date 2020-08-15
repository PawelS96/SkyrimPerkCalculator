package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.domain.enums.SkillEnum
import java.util.*

interface ISkill
interface IMainSkill : ISkill
interface ISpecialSkill : ISkill

class Skill(val skillEnum: SkillEnum) {

    var perks: Map<IPerk, Perk> = HashMap()

    set(value) {
        field = value
        maxPerks = perks.values.sumBy { it.maxState }
    }
    private var maxPerks = 0

    val type: SkillType
        get() = skillEnum.skillType

    val selectedPerksCount: Int
        get() {
            var count = 0
            for (p in perks.values) {
                count += p.state
            }
            return count
        }

    val requiredSkillLevel: Int
        get() {
            var level = 0
            for (p in perks.values) {
                if (!p.isSelected) continue
                val perkSkillLevel = p.skillLevel
                if (perkSkillLevel > level) level = perkSkillLevel
            }
            return level
        }

    val perksCount: String
        get() = "$selectedPerksCount/$maxPerks"

    val childrenList: List<Perk?>
        get() {
            val list = ArrayList<Perk?>()
            for (s in perks.keys) list.add(perks[s])
            return list
        }

    operator fun get(name: IPerk): Perk? {
        return perks[name]
    }

    fun getCoordinates() : Map<IPerk, FPoint> {
        return perks.map { it.key to FPoint(it.value.perk.perkInfo.x, it.value.perk.perkInfo.y) }.toMap()
    }

    companion object {
        fun buildSkill(skillEnum: SkillEnum, perkSystem: PerkSystem): Skill {

            val skill = Skill(skillEnum)
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
            skill.perks = perks

            return skill
        }
    }

}