package com.pawels96.skyrimperkcalculator.domain

import java.io.Serializable

/**
 * Interface implemented by all perk enums
 */
interface IPerk : Serializable {

    val perkInfo: PerkInfo

    val childPerks: List<IPerk>
}

/**
 * Implemented by perk enums belonging to skills with non-standard requirements for perks: Vampirism and Lycanthropy
 */
interface SpecialSkillPerk : IPerk
