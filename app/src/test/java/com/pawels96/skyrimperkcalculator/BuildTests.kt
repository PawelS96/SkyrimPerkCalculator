package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.data.toDomain
import com.pawels96.skyrimperkcalculator.data.toEntity
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.skills_ordinator.Ord_Alchemy
import junit.framework.Assert.assertEquals
import org.junit.Test

class BuildTests {

    @Test
    fun mapperTest(){
        val build = Build.create(PerkSystem.ORDINATOR)
        val entity = build.toEntity()
        val fromEntity = entity.toDomain()
        assertEquals(build, fromEntity)
    }

    @Test
    fun copyBuildTest(){
        // objects should be equal
        val build = Build.create(PerkSystem.ORDINATOR)
        val copy = Build.clone(build)
        assertEquals(build, copy)

        // using copy should not modify the original object
        copy.getSkill(EMainSkill.SKILL_ALCHEMY)[Ord_Alchemy.ORD_ALC_ADVANCED_LAB]?.nextState()
        assertEquals(0, build.getSelectedPerksCount())
    }
}