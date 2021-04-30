package com.pawels96.skyrimperkcalculator.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem

class Converters {

    private inline fun <reified T : Enum<T>> String.asEnumOrDefault(defaultValue: T? = null): T? =
            enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue

    @TypeConverter
    fun mainPerkSystemToString(system: PerkSystem) = system.toString()

    @TypeConverter
    fun vampirePerkSystemToString(system: VampirePerkSystem) = system.toString()

    @TypeConverter
    fun werewolfPerkSystemToString(system: WerewolfPerkSystem) = system.toString()

    @TypeConverter
    fun mainPerkSystemFromString(string: String): PerkSystem {
        return string.asEnumOrDefault(PerkSystem.VANILLA)!!
    }

    @TypeConverter
    fun vampirePerkSystemFromString(string: String): VampirePerkSystem {
        return string.asEnumOrDefault(VampirePerkSystem.VANILLA)!!
    }

    @TypeConverter
    fun werewolfPerkSystemFromString(string: String): WerewolfPerkSystem {
        return string.asEnumOrDefault(WerewolfPerkSystem.VANILLA)!!
    }

    @TypeConverter
    fun mapToString(map: Map<String, Map<String, Int>>): String = Gson().toJson(map)

    @TypeConverter
    fun mapFromString(string: String): Map<String, Map<String, Int>> {
        val token = object : TypeToken<Map<String, Map<String, Int>>>() {}.type
        return Gson().fromJson(string, token)
    }

    @TypeConverter
    fun vampirePerksMapToString(map: Map<VampirePerkSystem, Map<String, Int>>): String = Gson().toJson(map)

    @TypeConverter
    fun vampirePerksFromString(string: String): Map<VampirePerkSystem, Map<String, Int>> {
        val token = object : TypeToken<Map<VampirePerkSystem, Map<String, Int>>>() {}.type
        return Gson().fromJson(string, token)
    }

    @TypeConverter
    fun werewolfPerksMapToString(map: Map<WerewolfPerkSystem, Map<String, Int>>): String = Gson().toJson(map)

    @TypeConverter
    fun werewolfPerksFromString(string: String): Map<WerewolfPerkSystem, Map<String, Int>> {
        val token = object : TypeToken<Map<WerewolfPerkSystem, Map<String, Int>>>() {}.type
        return Gson().fromJson(string, token)
    }
}