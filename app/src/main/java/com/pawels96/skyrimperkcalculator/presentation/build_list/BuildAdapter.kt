package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.ListItemBuildBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.common.Bounceable
import com.pawels96.skyrimperkcalculator.presentation.common.AnimatedHolder
import com.pawels96.skyrimperkcalculator.presentation.common.colored

class BuildAdapter(
    private val context: Context,
    private val callback: BuildAdapterCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<BuildListItem>()

    interface BuildAdapterCallback {
        fun onClick(build: Build)
        fun onContextMenuClick(build: Build, view: View)
    }

    fun display(items: List<BuildListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val holder = viewHolder as Holder
        holder.bind(items[i])
    }

    private fun getRequiredLevelText(item: BuildListItem): String {
        return context.getString(R.string.level) + ": " + item.level
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
    ) : RecyclerView.ViewHolder(binding.root), AnimatedHolder by Bounceable(binding.root) {

        fun bind(item: BuildListItem) {
            val build = item.build
            with(binding) {
                root.setOnClickListener { callback.onClick(build) }
                contextMenu.setOnClickListener { callback.onContextMenuClick(build, contextMenu) }
                nameEdit.text = if (item.isSelected) build.name.colored(
                    ContextCompat.getColor(
                        context,
                        R.color.colorAccent
                    )
                ) else build.name

                level.text = getRequiredLevelText(item)
                description.text = build.description
                description.setVisible(build.description.isNotEmpty())
            }

            displayPerks(build)
        }

        private fun displayPerks(build: Build) {
            val skillDistribution = build.getPerkDistribution()

            val combatPerkCount = skillDistribution[SkillType.COMBAT] ?: 0
            val magicPerkCount = skillDistribution[SkillType.MAGIC] ?: 0
            val stealthPerkCount = skillDistribution[SkillType.STEALTH] ?: 0
            val vampirismPerkCount =
                build.vampireSkill[build.vampirePerkSystem]?.selectedPerksCount ?: 0
            val lycanthropyPerkCount =
                build.werewolfSkill[build.werewolfPerkSystem]?.selectedPerksCount ?: 0

            with(binding.perkCounters) {
                counterCombat.apply {
                    setVisible(combatPerkCount > 0)
                    text = combatPerkCount.toString()
                }
                counterMagic.apply {
                    setVisible(magicPerkCount > 0)
                    text = magicPerkCount.toString()
                }
                counterStealth.apply {
                    setVisible(stealthPerkCount > 0)
                    text = stealthPerkCount.toString()
                }
                counterVampirism.apply {
                    setVisible(vampirismPerkCount > 0)
                    text = vampirismPerkCount.toString()
                }
                counterLycanthropy.apply {
                    setVisible(lycanthropyPerkCount > 0)
                    text = lycanthropyPerkCount.toString()
                }
            }
        }
    }

    private fun View.setVisible(visible: Boolean) {
        visibility = if (visible)
            View.VISIBLE
        else
            View.GONE
    }
}