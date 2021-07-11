package com.pawels96.skyrimperkcalculator.presentation.skill_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.Utils.getSkillName
import com.pawels96.skyrimperkcalculator.presentation.colored

class SkillAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<SkillAdapter.SkillHolder>() {

    private val items: MutableList<Skill> = mutableListOf()

    fun display(items: List<Skill>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = R.layout.list_item_skill

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillHolder {
        return SkillHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: SkillHolder, position: Int) {
        val context = holder.root.context
        val skill = items[position]
        val perks = skill.selectedPerksCount
        val color = ContextCompat.getColor(context, skill.getColorRes())
        holder.name.text = getSkillName(skill.iskill, context).colored(color)
        holder.perks.text = if (perks > 0) perks.toString().colored(color) else ""
        holder.root.setOnClickListener { onClick(position) }
    }

    private fun Skill.getColorRes(): Int {

        return when (type) {
            SkillType.COMBAT -> R.color.skillCombatBright
            SkillType.MAGIC -> R.color.skillMagicBright
            SkillType.STEALTH -> R.color.skillStealthBright
            SkillType.SPECIAL -> when (iskill) {
                ESpecialSkill.SKILL_LYCANTHROPY -> R.color.skillWerewolfBright
                ESpecialSkill.SKILL_VAMPIRISM -> R.color.skillVampireBright
                else -> R.color.colorFont
            }
        }
    }

    class SkillHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.findViewById(R.id.skill_name)
        val perks: TextView = root.findViewById(R.id.active_perks)
    }
}