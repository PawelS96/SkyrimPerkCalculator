package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build

fun Build.toEntity(): BuildEntity {

    val mainSkillsMap = skills.map { skillEntry ->
        skillEntry.key.toString() to skillEntry.value.perks.map { perkEntry ->
            perkEntry.key.toString() to perkEntry.value.state
        }.toMap().toMutableMap()
    }.toMap()

    val vampireMap = vampireSkill.map { perkSystemEntry ->
        perkSystemEntry.key to perkSystemEntry.value.perks.map { perkEntry ->
            perkEntry.key.toString() to perkEntry.value.state
        }.toMap().toMutableMap()
    }.toMap()

    val werewolfMap = werewolfSkill.map { perkSystemEntry ->
        perkSystemEntry.key to perkSystemEntry.value.perks.map { perkEntry ->
            perkEntry.key.toString() to perkEntry.value.state
        }.toMap().toMutableMap()
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
        e.value.perks.forEach { e2 ->
            val skillKey = e.key.toString()
            val perkKey = e2.key.toString()
            e2.value.state = mainSkills[skillKey]?.get(perkKey) ?: 0
        }
    }

    build.vampireSkill.entries.forEach { e ->
        e.value.perks.forEach { e2 ->
            val perkSystemKey = e.key
            val perkKey = e2.key.toString()
            e2.value.state = vampireSkills[perkSystemKey]?.get(perkKey) ?: 0
        }
    }

    build.werewolfSkill.entries.forEach { e ->
        e.value.perks.forEach { e2 ->
            val perkSystemKey = e.key
            val perkKey = e2.key.toString()
            e2.value.state = werewolfSkills[perkSystemKey]?.get(perkKey) ?: 0
        }
    }

    return build
}
