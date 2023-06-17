package com.pawels96.skyrimperkcalculator

import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.skills_vanilla.Alchemy
import org.junit.Assert
import org.junit.Test

class PerkTests {

    @Test
    fun `calling nextState should increment state to the max value and then decrement to zero`() {
        val perk = Perk(Alchemy.VAN_ALC_ALCHEMIST)
        Assert.assertEquals(0, perk.state)

        for (i in 1 .. perk.maxState) {
            perk.nextState()
            println("increment to ${perk.state}")
            Assert.assertEquals(i, perk.state)
        }

        for (i in perk.state - 1 downTo 0) {
            perk.nextState()
            println("decrement to ${perk.state}")
            Assert.assertEquals(i, perk.state)
        }
    }

    @Test
    fun `setting the state value directly should coerce the value in the valid range`() {
        val perk = Perk(Alchemy.VAN_ALC_ALCHEMIST)

        perk.state = perk.maxState + 1
        Assert.assertEquals(perk.maxState, perk.state)

        perk.state = -1
        Assert.assertEquals(0, perk.state)
    }
}