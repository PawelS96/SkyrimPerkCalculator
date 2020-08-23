package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.Build

object BuildMapper {

    fun toEntity(build: Build): BuildEntity {

        val mainSkillsMap = build.skills.map { skillEntry ->
            skillEntry.key.toString() to skillEntry.value.perks.map { perkEntry ->
                perkEntry.key.toString() to perkEntry.value.state
            }.toMap()
        }.toMap()

        val vampireMap = build.vampireSkill.map { perkSystemEntry ->
            perkSystemEntry.key to perkSystemEntry.value.perks.map { perkEntry ->
                perkEntry.key.toString() to perkEntry.value.state
            }.toMap()
        }.toMap()

        val werewolfMap = build.werewolfSkill.map { perkSystemEntry ->
            perkSystemEntry.key to perkSystemEntry.value.perks.map { perkEntry ->
                perkEntry.key.toString() to perkEntry.value.state
            }.toMap()
        }.toMap()

        return BuildEntity(
                build.id,
                build.name,
                build.description,
                build.system,
                mainSkillsMap,
                build.vampirePerkSystem,
                vampireMap,
                build.werewolfPerkSystem,
                werewolfMap
        )
    }

    fun fromEntity(entity: BuildEntity): Build {

        val build = Build.create(entity.perkSystem, entity.id)

        build.apply {
            description = entity.description
            name = entity.name
            vampirePerkSystem = entity.vampirePerkSystem
            werewolfPerkSystem = entity.werewolfPerkSystem
        }

        build.skills.entries.forEach { e ->
            e.value.perks.forEach { e2 ->

                val skillKey = e.key.toString()
                val perkKey = e2.key.toString()

                e2.value.state = entity.mainSkills[skillKey]?.get(perkKey) ?: 0
            }
        }

        build.vampireSkill.entries.forEach { e ->
            e.value.perks.forEach { e2 ->

                val perkSystemKey = e.key
                val perkKey = e2.key.toString()

                e2.value.state = entity.vampireSkills[perkSystemKey]?.get(perkKey) ?: 0
            }
        }

        build.werewolfSkill.entries.forEach { e ->
            e.value.perks.forEach { e2 ->

                val perkSystemKey = e.key
                val perkKey = e2.key.toString()

                e2.value.state = entity.werewolfSkills[perkSystemKey]?.get(perkKey) ?: 0
            }
        }

        return build
    }
}