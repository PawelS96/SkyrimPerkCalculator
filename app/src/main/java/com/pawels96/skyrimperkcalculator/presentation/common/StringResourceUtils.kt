package com.pawels96.skyrimperkcalculator.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.IPerkSystem
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem

fun IPerkSystem.getName(context: Context): String {
    val resName = "s_" + this.toString().lowercase()
    return context.getString(resName, resName)
}

fun ISkill.getName(context: Context): String {
    val resName = this.toString().lowercase()
    return context.getString(resName, resName)
}

fun IPerk.getName(context: Context): String {
    return getPerkString(context, "p_")
}

fun IPerk.getDescription(context: Context): String {
    return getPerkString(context, "d_")
}

private fun IPerk.getPerkString(context: Context, resPrefix: String): String {
    val resName = resPrefix + this.toString().lowercase()
    return context.getString(resName, resName)
}

@SuppressLint("DiscouragedApi")
private fun Context.getString(resName: String, defaultValue: String): String {
    return try {
        val id = resources.getIdentifier(resName, "string", packageName)
        resources.getString(id)
    } catch (e: Exception) {
        Log.e("resources", "Failed to get string: $resName")
        defaultValue
    }
}

fun Context.verifyStringResources() {
    val allPerkSystems = PerkSystem.values().toList() + VampirePerkSystem.values().toList() + WerewolfPerkSystem.values().toList()
    allPerkSystems.forEach { it.getName(this) }

    val allSkills = EMainSkill.values().toList() + ESpecialSkill.values().toList()
    allSkills.forEach { it.getName(this) }

    val allMainPerks = PerkSystem.values()
        .flatMap { system ->
            EMainSkill.values().flatMap { it.getPerks(system).toList() }
        }
    val allVampirePerks = VampirePerkSystem.values()
        .flatMap { system ->
            ESpecialSkill.SKILL_VAMPIRISM.getPerks(system).toList()
        }
    val allWerewolfPerks = WerewolfPerkSystem.values()
        .flatMap { system ->
            ESpecialSkill.SKILL_LYCANTHROPY.getPerks(system).toList()
        }

    val allPerks = allMainPerks + allVampirePerks + allWerewolfPerks
    allPerks.forEach {
        it.getName(this)
        it.getDescription(this)
    }
}
