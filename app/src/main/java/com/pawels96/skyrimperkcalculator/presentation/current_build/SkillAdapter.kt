package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.ListItemSkillBinding
import com.pawels96.skyrimperkcalculator.domain.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.common.Bounceable
import com.pawels96.skyrimperkcalculator.presentation.common.AnimatedHolder
import com.pawels96.skyrimperkcalculator.presentation.common.colored
import com.pawels96.skyrimperkcalculator.presentation.common.getName

class SkillAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<SkillAdapter.SkillHolder>() {

    private val items: MutableList<Skill> = mutableListOf()

    fun display(items: List<Skill>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = R.layout.list_item_skill

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSkillBinding.inflate(inflater, parent, false)
        return SkillHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillHolder, position: Int) {
        holder.bind(items[position], position) { onClick(it) }
    }

    class SkillHolder(val binding: ListItemSkillBinding) :
        RecyclerView.ViewHolder(binding.root),
        AnimatedHolder by Bounceable(binding.root) {

        fun bind(skill: Skill, index: Int, onClick: (Int) -> Unit) {
            val context = binding.root.context
            val perks = skill.selectedPerksCount
            val color = ContextCompat.getColor(context, skill.getColorRes())
            binding.skillName.text = skill.iskill.getName(context).colored(color)
            binding.activePerks.text = if (perks > 0) perks.toString().colored(color) else ""
            binding.root.setOnClickListener { onClick(index) }
        }

        private fun Skill.getColorRes(): Int {

            return when (type) {
                SkillType.COMBAT -> R.color.colorCombat
                SkillType.MAGIC -> R.color.colorMagic
                SkillType.STEALTH -> R.color.colorStealth
                SkillType.SPECIAL -> when (iskill) {
                    ESpecialSkill.SKILL_LYCANTHROPY -> R.color.colorWerewolf
                    ESpecialSkill.SKILL_VAMPIRISM -> R.color.colorVampire
                    else -> R.color.colorFont
                }
            }
        }
    }
}