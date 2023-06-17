package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem

fun Build.toEntity(): BuildEntity {

    val mainSkillsMap: Map<String, Map<String, Int>> = skills.map { entry ->
        val perkStates = entry.value.perks.entries.associate {
            it.key.toString() to it.value.state
        }
        return@map entry.key.toString() to perkStates
    }.toMap()

    val vampireMap: Map<VampirePerkSystem, Map<String, Int>> = vampireSkill.map { entry ->
        val perkStates = entry.value.perks.entries.associate {
            it.key.toString() to it.value.state
        }
        return@map entry.key to perkStates
    }.toMap()

    val werewolfMap: Map<WerewolfPerkSystem, Map<String, Int>> = werewolfSkill.map { entry ->
        val perkStates = entry.value.perks.entries.associate {
            it.key.toString() to it.value.state
        }
        return@map entry.key to perkStates
    }.toMap()

    return BuildEntity(
        id,
        name,
        description,
        system,
        mainSkillsMap,
        vampirePerkSystem,
        vampireMap,
        werewolfPerkSystem,
        werewolfMap
    )
}

fun BuildEntity.toDomain(): Build {

    val build = Build.create(perkSystem, id).copy(
        description = description,
        name = name,
        vampirePerkSystem = vampirePerkSystem,
        werewolfPerkSystem = werewolfPerkSystem
    )

    build.skills.entries.forEach { e ->
        e.value.updatePerksRecursively { perk ->
            mainSkills[e.key.toString()]?.get(perk.toString()) ?: 0
        }
    }

    build.vampireSkill.entries.forEach { e ->
        e.value.updatePerksRecursively { perk ->
            vampireSkills[e.key]?.get(perk.toString()) ?: 0
        }
    }

    build.werewolfSkill.entries.forEach { e ->
        e.value.updatePerksRecursively { perk ->
            werewolfSkills[e.key]?.get(perk.toString()) ?: 0
        }
    }

    return build
}

private fun Skill.updatePerksRecursively(stateForPerk: (IPerk) -> Int) {
    val rootPerk = perkList.firstOrNull { !it.hasParent } ?: return

    fun updatePerk(perk: Perk) {
        val childStates = perk.children.associate { it.perk to stateForPerk(it.perk) }
        val sortedChildren = perk.children.sortedByDescending { childStates[it.perk] }
        perk.state = stateForPerk(perk.perk)
        sortedChildren.forEach { updatePerk(it) }
    }

    updatePerk(rootPerk)
}
