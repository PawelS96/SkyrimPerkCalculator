package com.pawels96.skyrimperkcalculator.domain

import java.util.*

class Perk(val perk: IPerk) {

    @JvmField
    var children: MutableList<Perk> = ArrayList()
    var parents: MutableList<Perk> = ArrayList()

    var state = 0
    set(value) {field = value.coerceAtMost(maxState); onStateChanged() }

    val maxState: Int = perk.perkInfo.skillLevel.size

    private var stateIncreasing = true

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

    private fun hasParent(): Boolean = parents.isNotEmpty()

    fun hasChildren(): Boolean = children.isNotEmpty()

    private val isNoParentConnected: Boolean
        get() {
            for (n in parents) if (n.isSelected) return false
            return true
        }

    private fun onStateChanged() {
        if (state == 0) stateIncreasing = true else if (state == maxState) stateIncreasing = false
        if (isSelected) {
            if (!hasParent()) return
            if (isNoParentConnected) parents[0].state = 1
        } else {
            if (!hasChildren()) return
            for (n in children) {
                if (n.isNoParentConnected) n.state = 0
            }
        }
    }

    fun nextState() {
        if (stateIncreasing) state++ else state--
        onStateChanged()
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