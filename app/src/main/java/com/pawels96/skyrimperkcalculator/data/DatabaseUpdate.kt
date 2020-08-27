package com.pawels96.skyrimperkcalculator.data

import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill
import kotlin.math.min

typealias PerkMap = MutableMap<String, Int>

class DatabaseUpdater(val dao: BuildDAO) {

    fun updatePerks(perkSystem: PerkSystem) {

        val builds = dao.getAllByPerkSystem(perkSystem)

        builds.forEach {

            val skills = it.mainSkills

            EMainSkill.values().forEach {skill ->
                val perks = skill.getPerks(perkSystem)
                val savedPerks = skills[skill.toString()]
                perks.forEach { perk -> savedPerks?.updateMaxLevel(perk)  }
            }

            dao.update(it)
        }
    }

    private fun PerkMap.renamePerk(oldName: String, newName: String){
        val state = this[oldName] ?: 0
        this.remove(oldName)
        this[newName] = state
    }

    private fun PerkMap.updateMaxLevel(perk: IPerk){
        val name = perk.toString()
        val state = this[name] ?: 0
        this[name] = min(state, perk.maxState())
    }

    private fun IPerk.maxState() = perkInfo.skillLevel.size
}

/** UPDATE 1
 *
 *  ALTERATION
 *  runic weapon -> X
 *  battlemage lvl 1 -> 2
 *  magic resistance lvl 1 -> 3
 *
 *  DESTRUCTION
 *  eye of the storm -> X
 *  mages furry -> hellstorm
 *
 *  HEAVY ARMOR
 *  off balance -> Immovable Object
 *
 *
 *  SMITHING
 *
Basic Smithing -> Steel Smithing
Meric Smithing -> Elven Smithing
engraved smithing -> Orcish Smithing
primal smithing ->Advanced Armors
crystaline smithing -> Glass Smithing
exotic smithing -> Ebony Smithing

 *
 *
 *
 *
 *
 *
 *
 */
