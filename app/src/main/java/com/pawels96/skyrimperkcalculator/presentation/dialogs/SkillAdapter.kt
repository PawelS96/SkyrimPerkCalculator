package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.domain.enums.ESpecialSkill
import com.pawels96.skyrimperkcalculator.presentation.BuildAdapter
import com.pawels96.skyrimperkcalculator.presentation.Utils.getSkillName
import com.pawels96.skyrimperkcalculator.presentation.colored

class SkillAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val skills: MutableList<Skill> = mutableListOf()

    fun display(_skills : List<Skill>){
        this.skills.clear()
        this.skills.addAll(_skills)
        notifyDataSetChanged()
    }

    lateinit var onItemClick : (Int) -> Unit

    override fun getItemCount(): Int = skills.size

    override fun getItemViewType(position: Int): Int = R.layout.list_item_skill

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("sizeee", position.toString())

        val hol = holder as SkillHolder
        val skill = skills[position]
        val perks = skill.selectedPerksCount
        val color = getColor(skill)
        hol.name.text = getSkillName(skill.iskill, context).colored(color)
        hol.perks.text = if (perks > 0) perks.toString().colored(color) else ""
        hol.root.setOnClickListener { onItemClick(position) }
    }

    private fun getColor(skill: Skill) : Int {

        val colorID =  when(skill.type){
            SkillType.COMBAT -> R.color.skillCombatBright
            SkillType.MAGIC -> R.color.skillMagicBright
            SkillType.STEALTH -> R.color.skillStealthBright
            SkillType.SPECIAL -> when(skill.iskill){
                ESpecialSkill.SKILL_LYCANTHROPY -> R.color.skillWerewolfBright
                ESpecialSkill.SKILL_VAMPIRISM -> R.color.skillVampireBright
                else -> R.color.colorFont
            }
        }

        return context.resources.getColor(colorID)
    }

    private class SkillHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.findViewById(R.id.skill_name)
        val perks: TextView = root.findViewById(R.id.active_perks)
    }
}