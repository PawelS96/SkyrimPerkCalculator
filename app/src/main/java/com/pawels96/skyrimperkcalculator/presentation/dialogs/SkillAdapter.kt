package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.presentation.BuildAdapter
import com.pawels96.skyrimperkcalculator.presentation.Utils.getSkillName

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
        return Holder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("sizeee", position.toString())

        val hol = holder as Holder
        val skill = skills[position]
        val perks = skill.selectedPerksCount
        hol.name.text = getSkillName(skill.iskill, context)
        hol.perks.text = if (perks > 0) perks.toString() else ""
        hol.root.setOnClickListener { onItemClick(position) }
    }

    private class Holder(val root: View) : RecyclerView.ViewHolder(root) {
        val name: TextView = root.findViewById(R.id.skill_name)
        val perks: TextView = root.findViewById(R.id.active_perks)
    }
}