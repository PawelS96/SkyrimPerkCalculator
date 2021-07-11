package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.ListItemBuildBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.colored

class BuildAdapter(
    private val context: Context,
    private val callback: BuildAdapterCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Build>()
    private var selected: Int = 0

    private val stealth = ContextCompat.getColor(context, R.color.skillStealthBright)
    private val combat = ContextCompat.getColor(context, R.color.skillCombatBright)
    private val magic = ContextCompat.getColor(context, R.color.skillMagicBright)
    private val vampire = ContextCompat.getColor(context, R.color.skillVampireBright)
    private val werewolf = ContextCompat.getColor(context, R.color.skillWerewolfBright)

    private var multiplier = 0f

    interface BuildAdapterCallback {
        fun onClick(build: Build)
        fun onContextMenuClick(build: Build, view: View)
    }

    fun display(items: List<Build>, selectedIndex: Int, multiplier: Float) {
        this.items.clear()
        this.items.addAll(items)
        this.multiplier = multiplier
        selected = selectedIndex
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val holder = viewHolder as Holder
        holder.bind(items[i], i == selected)
    }

    private fun getPerkCountText(build: Build): SpannableStringBuilder {
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
        } else builder.append("0")

        return builder
    }

    private fun getRequiredLevelText(build: Build): String {
        val lvl = build.getRequiredLevel(multiplier).toString()
        return context.getString(R.string.level) + ": " + lvl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBuildBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemViewType(position: Int): Int = R.layout.list_item_build

    override fun getItemCount(): Int = items.size

    private inner class Holder(
        val binding: ListItemBuildBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(build: Build, isSelected: Boolean) {

            with(binding) {
                root.setOnClickListener { callback.onClick(build) }
                contextMenu.setOnClickListener { callback.onContextMenuClick(build, contextMenu) }
                nameEdit.text = if (isSelected) build.name.colored(magic) else build.name
                buildInfo.text = getPerkCountText(build)
                level.text = getRequiredLevelText(build)
                description.text = build.description
                description.visibility = if (build.description.isEmpty())
                    View.GONE
                else
                    View.VISIBLE
            }
        }
    }
}