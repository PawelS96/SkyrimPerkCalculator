package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.domain.*
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.enums.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.skills_ordinator.Ord_Alchemy
import com.pawels96.skyrimperkcalculator.domain.vampirism.Vampirism
import junit.framework.Assert.assertEquals
import org.junit.Test

class SpecialSKillsTest {

    @Test
    fun testVampireRequiredKills(){

        val skill = SkillFactory.create(ESpecialSkill.SKILL_VAMPIRISM, VampirePerkSystem.VANILLA)
        skill.perks.forEach { (_, u) ->  u.state = 1}
        assertEquals(11, skill.selectedPerksCount)
        assertEquals( 156, skill.requiredSkillLevel)
    }

    @Test
    fun testWerewolfRequiredKills(){

        val skill = SkillFactory.create(ESpecialSkill.SKILL_LYCANTHROPY, WerewolfPerkSystem.VANILLA)
        skill.perks.forEach { (_, u) ->  u.state = u.maxState}
        assertEquals(11, skill.selectedPerksCount)
        assertEquals( 164, skill.requiredSkillLevel)
    }


}