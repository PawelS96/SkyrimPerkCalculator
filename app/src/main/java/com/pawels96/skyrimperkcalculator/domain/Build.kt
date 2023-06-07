package com.pawels96.skyrimperkcalculator.domain

import com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME

data class Build(
        val id: Long,
        val system: PerkSystem,
        var name: String,
        var description: String,
        var skills: Map<EMainSkill, Skill>,
        var vampirePerkSystem: VampirePerkSystem,
        var werewolfPerkSystem: WerewolfPerkSystem,
        var vampireSkill: Map<VampirePerkSystem, SpecialSkill>,
        var werewolfSkill: Map<WerewolfPerkSystem, SpecialSkill>
) {

    fun getSkill(id: ISkill): Skill {
        return if (id is EMainSkill)
            skills[id]!!
        else return when (id as ESpecialSkill) {
            ESpecialSkill.SKILL_VAMPIRISM -> vampireSkill[vampirePerkSystem]!!
            ESpecialSkill.SKILL_LYCANTHROPY -> werewolfSkill[werewolfPerkSystem]!!
        }
    }

    fun getAllSkills(): List<Skill> {
        return skills.values + getSkill(ESpecialSkill.SKILL_VAMPIRISM) + getSkill(ESpecialSkill.SKILL_LYCANTHROPY)
    }

    fun getPerkDistribution(): Map<SkillType, Int> {
        return SkillType.values().associate { type ->
            type to skills.values
                    .filter { skill -> skill.type == type }
                    .sumBy { skill -> skill.selectedPerksCount }
        }
    }

    fun getSelectedPerksCount(): Int = skills.values.sumBy { it.selectedPerksCount }

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

        fun create(
            perkSystem: PerkSystem,
            id: Long = 0,
            name: String = DEFAULT_BUILD_NAME
        ): Build {

            val skills =
                EMainSkill.values().associateWith { s -> SkillFactory.create(s, perkSystem) }
            val vampireSkill = VampirePerkSystem.values()
                .associateWith {
                    SkillFactory.create(ESpecialSkill.SKILL_VAMPIRISM, it) as SpecialSkill
                }
            val werewolfSkill = WerewolfPerkSystem.values()
                .associateWith {
                    SkillFactory.create(ESpecialSkill.SKILL_LYCANTHROPY, it) as SpecialSkill
                }

            return Build(
                    id,
                    perkSystem,
                    name,
                    "",
                    skills,
                    VampirePerkSystem.VANILLA,
                    WerewolfPerkSystem.VANILLA,
                    vampireSkill,
                    werewolfSkill
            )
        }

        fun clone(objToClone: Build): Build {
            val build = create(objToClone.system)

            for (s in EMainSkill.values()) {
                for (p in objToClone.getSkill(s).perks.keys) {
                    val state = objToClone.getSkill(s)[p]!!.state
                    build.getSkill(s)[p]!!.state = state
                }
            }

            build.description = objToClone.description
            build.werewolfPerkSystem = objToClone.werewolfPerkSystem
            build.vampirePerkSystem = objToClone.vampirePerkSystem

            for (s in VampirePerkSystem.values()) {
                for (e in objToClone.vampireSkill[s]!!.perks.entries) {
                    build.vampireSkill[s]?.get(e.key)?.state = e.value.state
                }
            }

            for (s in WerewolfPerkSystem.values()) {
                for (e in objToClone.werewolfSkill[s]!!.perks.entries) {
                    build.werewolfSkill[s]?.get(e.key)?.state = e.value.state
                }
            }

            return build
        }
    }

}