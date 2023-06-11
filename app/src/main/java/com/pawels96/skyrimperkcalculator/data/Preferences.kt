package com.pawels96.skyrimperkcalculator.data

import android.content.SharedPreferences
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class Preferences(private val sp: SharedPreferences) {

    var selectedPerkSystem: PerkSystem
        set(value) {
            sp.edit().putString(KEY_PERK_SYSTEM, value.toString()).apply()
        }
        get() {
            val value = sp.getString(KEY_PERK_SYSTEM, PerkSystem.VANILLA.toString()).orEmpty()
            return parsePerkSystem(value)
        }

    val selectedPerkSystemFlow: Flow<PerkSystem>
        get() = sp.flow(KEY_PERK_SYSTEM) { selectedPerkSystem }

    private fun parsePerkSystem(string: String): PerkSystem {
        return try {
            PerkSystem.valueOf(string)
        } catch (e: IllegalArgumentException) {
            PerkSystem.VANILLA
        }
    }

    var selectedBuildId: Long
        set(value) {
            sp.edit().putLong(KEY_SELECTED_BUILD, value).apply()
        }
        get() = sp.getLong(KEY_SELECTED_BUILD, 1L)

    val selectedBuildIdFlow: Flow<Long>
        get() = sp.flow(KEY_SELECTED_BUILD) { selectedBuildId }

    var selectedPage: Int
        set(value) {
            sp.edit().putInt(KEY_SELECTED_PAGE, value).apply()
        }
        get() = sp.getInt(KEY_SELECTED_PAGE, 0)

    var firstLaunch: Boolean
        set(value) {
            sp.edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()
        }
        get() = sp.getBoolean(KEY_FIRST_LAUNCH, true)

    var perkMultiplier: Float
        set(value) {
            sp.edit().putFloat(KEY_PERK_MULTIPLIER, value).apply()
        }
        get() = sp.getFloat(KEY_PERK_MULTIPLIER, 1.0f)

    val perkMultiplierFlow: Flow<Float>
        get() = sp.flow(KEY_PERK_MULTIPLIER) { perkMultiplier }

    companion object {
        private const val KEY_PERK_SYSTEM = "system"
        private const val KEY_SELECTED_BUILD = "selected_build_id"
        private const val KEY_SELECTED_PAGE = "selected_page"
        private const val KEY_FIRST_LAUNCH = "firstLaunch"
        private const val KEY_PERK_MULTIPLIER = "perk_multiplier"
    }
}

private fun <T> SharedPreferences.flow(key: String, getValue: () -> T): Flow<T> {
    return callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, prefKey ->
            if (prefKey == key) {
                trySend(getValue())
            }
        }

        trySend(getValue())
        registerOnSharedPreferenceChangeListener(listener)
        awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
    }
}