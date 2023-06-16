package com.pawels96.skyrimperkcalculator.presentation.common

import android.annotation.SuppressLint
import android.content.Context
import com.pawels96.skyrimperkcalculator.domain.IPerk
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.PerkSystem

fun PerkSystem.getName(context: Context): String {
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
        e.printStackTrace()
        defaultValue
    }
}
