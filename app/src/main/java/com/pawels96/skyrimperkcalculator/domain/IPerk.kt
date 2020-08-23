package com.pawels96.skyrimperkcalculator.domain

import java.io.Serializable

/**
 * Interface implemented by all perk enums.
 * Returns an object which contains X and Y coordinates of a perk and required skill levels.
 */
interface IPerk : Serializable {
    val perkInfo: PerkInfo
}

/**
 * Implemented by perk enums belonging to special skills: Vampirism and Lycanthropy
 */

interface SpecialSkillPerk : IPerk
