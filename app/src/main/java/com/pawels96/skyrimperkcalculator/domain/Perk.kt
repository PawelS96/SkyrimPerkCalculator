package com.pawels96.skyrimperkcalculator.domain

import java.io.Serializable

class Perk(val perk: IPerk) : Serializable {

    var children: MutableList<Perk> = mutableListOf()
    var parents: MutableList<Perk> = mutableListOf()

    var state = 0
        set(value) {
            field = value.coerceIn(0..maxState)
            onStateChanged()
        }

    val maxState: Int = perk.perkInfo.skillLevel.size

    var isStateIncreasing = true

    val isSelected: Boolean get() = state != 0

    val isMultiState: Boolean get() = maxState > 1

    val skillLevel: Int get() = perk.perkInfo.skillLevel[(state - 1).coerceAtLeast(0)]

    val allSkillLevels: String
        get() {
            val sb = StringBuilder()
            val levels: Int = perk.perkInfo.skillLevel.size
            for (i in 0 until levels) {
                sb.append(perk.perkInfo.skillLevel[i])
                if (i < levels - 1) sb.append(" / ")
            }
            return sb.toString()
        }

    val stateAsString: String get() = " ($state/$maxState)"

    val hasParent: Boolean get() = parents.isNotEmpty()

    val hasChildren: Boolean get() = children.isNotEmpty()

    private val isNoParentConnected: Boolean
        get() = parents.none { it.isSelected }

    private fun onStateChanged() {
        when (state) {
            0 -> isStateIncreasing = true
            maxState -> isStateIncreasing = false
        }

        if (isSelected) {
            if (hasParent && isNoParentConnected) {
                parents[0].state = 1
            }
        } else {
            for (child in children) {
                if (child.isNoParentConnected)  {
                    child.state = 0
                }
            }
        }
    }

    fun nextState() {
        if (isStateIncreasing) state++ else state--
    }

    companion object {
        fun connectPerks(parent: Perk, child: Perk) {
            parent.children.add(child)
            child.parents.add(parent)
        }

        @JvmStatic
        fun areNodesSelected(parent: Perk, child: Perk): Boolean {
            return parent.isSelected && child.isSelected
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other || (other is Perk && perk == other.perk && state == other.state)
    }
}