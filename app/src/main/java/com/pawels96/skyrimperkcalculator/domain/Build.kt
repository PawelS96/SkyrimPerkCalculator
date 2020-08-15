package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME
import com.pawels96.skyrimperkcalculator.domain.enums.SkillEnum
import com.pawels96.skyrimperkcalculator.domain.Skill.Companion.buildSkill

class Build(val system: PerkSystem) {

    var description: String? = null
    var name: String = DEFAULT_BUILD_NAME

    var skills: Map<SkillEnum, Skill> = SkillEnum.values()
            .associate {s -> s to buildSkill(s, system) }

    fun getSkill(name: SkillEnum) = skills[name]

    fun getPerkDistribution(): Map<SkillType, Int>{
        return SkillType.values().associate {type ->
            type to skills.values
                    .filter { skill ->  skill.type == type }
                    .sumBy { skill -> skill.selectedPerksCount }
        }
    }

    fun getSelectedPerksCount() : Int = skills.values.sumBy { it.selectedPerksCount }

    fun getRequiredLevel(multiplier: Float): Int {

        var lvl = 1
        var perks = 0f

        while (perks < getSelectedPerksCount()) {
            perks += multiplier
            lvl++
        }
        return lvl
    }

    companion object {

        fun clone(objToClone: Build): Build? {
            val build = Build(objToClone.system)
            for (s in SkillEnum.values()) {
                for (p in objToClone.getSkill(s)!!.perks.keys) {
                    val state = objToClone.getSkill(s)!![p]!!.state
                    build.getSkill(s)!![p]!!.state = state
                }
            }
            return build
        }
    }

}