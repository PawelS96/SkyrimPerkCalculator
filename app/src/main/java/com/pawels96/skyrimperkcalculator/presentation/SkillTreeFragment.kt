package com.pawels96.skyrimperkcalculator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentSkilltreeBinding
import com.pawels96.skyrimperkcalculator.domain.*
import com.pawels96.skyrimperkcalculator.presentation.dialogs.PerkInfoDialog
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.views.GraphView.OnNodeClickedListener

class SkillTreeFragment : Fragment(R.layout.fragment_skilltree) {

    private val binding by viewBinding(FragmentSkilltreeBinding::bind)

    private lateinit var displayedSkill: ISkill

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.provideVmFactory()
        )[BuildsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayedSkill = requireArguments().getSerializable(ARG_SKILL, ISkill::class.java) as ISkill
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.graph.listener = listener
        model.currentBuild.observe(viewLifecycleOwner) { displayBuild(it) }
    }

    private fun displayBuild(build: Build) {
        val skill = build.getSkill(displayedSkill)
        val perksText =
            getString(R.string.active_perks) + ": ${skill.selectedPerksCount}/${skill.maxPerks}"
        val requirementLabelRes =
            if (skill is SpecialSkill) R.string.required_kills else R.string.required_skill
        val skillText = getString(requirementLabelRes) + ": " + skill.requiredSkillLevel
        binding.graph.display(skill)
        binding.skillPerks.text = perksText
        binding.skillLevel.text = skillText
    }

    fun cancelHold() {
        binding.graph.cancelHold()
    }

    private val listener: OnNodeClickedListener = object : OnNodeClickedListener {
        override fun onNodeClicked(perk: Perk) {
            model.changePerkState(displayedSkill, perk.perk)
        }

        override fun onNodeHolding(perk: Perk) {
            showPerkDescription(perk)
        }
    }

    private fun showPerkDescription(perk: Perk) {
        PerkInfoDialog.create(displayedSkill, perk).show(childFragmentManager)
    }

    companion object {

        private const val ARG_SKILL = "arg_skill"

        fun create(skill: ISkill): SkillTreeFragment {
            return SkillTreeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_SKILL, skill)
                }
            }
        }
    }
}

