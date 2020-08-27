package com.pawels96.skyrimperkcalculator.presentation

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.SkillType

class BuildAdapter(private val context: Context, private val callback: BuildAdapterCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Build>()
    private var selected: Int = 0

    private val stealth = context.resources.getColor(R.color.skillStealthBright)
    private val combat = context.resources.getColor(R.color.skillCombatBright)
    private val magic = context.resources.getColor(R.color.skillMagicBright)
    private val vampire = context.resources.getColor(R.color.skillVampireBright)
    private val werewolf = context.resources.getColor(R.color.skillWerewolfBright)

    private var multiplier = 0f

    interface BuildAdapterCallback {
        fun onRename(build: Build)
        fun onDelete(build: Build)
        fun showDescription(build: Build)
        fun onClick(build: Build)
    }

    fun display(items: List<Build>, selectedIndex: Int, multiplier: Float) {
        this.items.clear()
        this.items.addAll(items)
        this.multiplier = multiplier
        selected = selectedIndex
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val build = items[i]
        val holder = viewHolder as Holder

        holder.root.setOnClickListener { v: View? -> callback.onClick(build) }
        holder.name.text = if (selected == i) build.name.colored(magic) else build.name

        val skills = mutableMapOf(
                stealth to build.getPerkDistribution()[SkillType.STEALTH],
                combat to build.getPerkDistribution()[SkillType.COMBAT],
                magic to build.getPerkDistribution()[SkillType.MAGIC],
                vampire to build.vampireSkill[build.vampirePerkSystem]?.selectedPerksCount,
                werewolf to build.werewolfSkill[build.werewolfPerkSystem]?.selectedPerksCount
        )
                .filter { it.value!! > 0 }
                .map { "${it.value} ".colored(it.key) }

        val builder = SpannableStringBuilder(context.getString(R.string.perks) + ": ")

        if (skills.isNotEmpty()) {
            skills.forEach { builder.append(it) }
        }
        else builder.append("0")

        holder.perks.text = builder//, TextView.BufferType.SPANNABLE)

        val lvl = build.getRequiredLevel(multiplier).toString()
        val levelText = context.getString(R.string.level).toString() + ": " + lvl
        holder.level.text = levelText

        val description = build.description
        if (description.isEmpty()) holder.description.visibility = View.GONE else {
            holder.description.visibility = View.VISIBLE
            holder.description.text = description
        }

        holder.button.setOnClickListener { v: View? ->
            val popup = PopupMenu(context, holder.button)
            popup.menuInflater.inflate(R.menu.menu_list_item, popup.menu)

            if (items.size < 2) popup.menu.findItem(R.id.delete).isVisible = false
            popup.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.rename -> callback.onRename(build)
                    R.id.delete -> callback.onDelete(build)
                    R.id.description -> callback.showDescription(build)
                }
                true
            }
            popup.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Holder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int = R.layout.list_item_build

    override fun getItemCount(): Int = items.size

    private class Holder(var root: View) : RecyclerView.ViewHolder(root) {
        var button: Button = root.findViewById(R.id.context_menu)
        var name: TextView = root.findViewById(R.id.name_edit)
        var level: TextView = root.findViewById(R.id.level)
        var perks: TextView = root.findViewById(R.id.build_info)
        var description: TextView = root.findViewById(R.id.description)
    }
}