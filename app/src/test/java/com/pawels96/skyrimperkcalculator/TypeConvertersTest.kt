package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.data.Converters
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem
import junit.framework.Assert.assertEquals
import org.junit.Test

class TypeConvertersTest {

    private val conv = Converters()

    @Test
    fun convertPerkSystems(){

        val ps = PerkSystem.ORDINATOR
        val asString = conv.mainPerkSystemToString(ps)
        val fromString = conv.mainPerkSystemFromString(asString)
        assertEquals(ps, fromString)
        assertEquals(PerkSystem.VANILLA, conv.mainPerkSystemFromString("invalid"))

        val psV = VampirePerkSystem.SACROSANCT
        val asStringV = conv.vampirePerkSystemToString(psV)
        val fromStringV = conv.vampirePerkSystemFromString(asStringV)
        assertEquals(psV, fromStringV)

        val psW = WerewolfPerkSystem.VANILLA
        val asStringW = conv.werewolfPerkSystemToString(psW)
        val fromStringW = conv.werewolfPerkSystemFromString(asStringW)
        assertEquals(psW, fromStringW)
    }

    @Test
    fun convertMainSkillMap(){

        val map = mutableMapOf<String, Map<String, Int>>()

        map["k1"] = mapOf("v1" to 1, "v2" to 2, "v3" to 3)
        map["k2"] = mapOf("v11" to 1, "v22" to 2, "v33" to 3)

        val asString = conv.mapToString(map)
        val fromString = conv.mapFromString(asString)

        assertEquals(map, fromString)
    }

    @Test
    fun convertVampireSkillMap(){

        val map = mutableMapOf<VampirePerkSystem, Map<String, Int>>()

        map[VampirePerkSystem.SACROSANCT] = mapOf("v1" to 1, "v2" to 2, "v3" to 3)
        map[VampirePerkSystem.VANILLA] = mapOf("v11" to 1, "v22" to 2, "v33" to 3)

        val asString = conv.vampirePerksMapToString(map)
        val fromString = conv.vampirePerksFromString(asString)

        assertEquals(map, fromString)
    }

    @Test
    fun convertWerewolfSkillMap(){

        val map = mutableMapOf<WerewolfPerkSystem, Map<String, Int>>()

        map[WerewolfPerkSystem.VANILLA] = mapOf("v1" to 1, "v2" to 2, "v3" to 3)
        map[WerewolfPerkSystem.GROWL] = mapOf("v11" to 1, "v22" to 2, "v33" to 3)

        val asString = conv.werewolfPerksMapToString(map)
        val fromString = conv.werewolfPerksFromString(asString)

        assertEquals(map, fromString)
    }

}