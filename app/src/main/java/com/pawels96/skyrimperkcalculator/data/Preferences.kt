package com.pawels96.skyrimperkcalculator.data

import android.content.SharedPreferences
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.Utils

class Preferences(private val sp: SharedPreferences) {

    var selectedPerkSystem: PerkSystem
        set(value) {
            sp.edit().putString("system", value.toString()).apply()
        }
        get() {
            val savedString = sp.getString("system", PerkSystem.VANILLA.toString()).orEmpty()

            return try {
                PerkSystem.valueOf(savedString)
            } catch (e: IllegalArgumentException) {
                PerkSystem.VANILLA
            }
        }

    var selectedBuild: String
        set(value) {
            sp.edit().putString("build_selected", value).apply()
        }
        get() = sp.getString("build_selected", Utils.DEFAULT_BUILD_NAME) ?: Utils.DEFAULT_BUILD_NAME

    var selectedPage: Int
        set(value) {
            sp.edit().putInt("selected_page", value).apply()
        }
        get() = sp.getInt("selected_page", 0)

    var firstLaunch: Boolean
    set(value) {
        sp.edit().putBoolean("firstLaunch", value).apply()
    }
    get() = sp.getBoolean("firstLaunch", true)

    var perkMultiplier: Float
        set(value) {
            sp.edit().putFloat("perk_multiplier", value).apply()
        }
        get() = sp.getFloat("perk_multiplier", 1.0f)


}