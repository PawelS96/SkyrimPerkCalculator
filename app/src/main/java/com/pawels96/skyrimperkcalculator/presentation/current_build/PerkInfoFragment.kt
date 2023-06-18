package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentPerkDescriptionBinding
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk
import com.pawels96.skyrimperkcalculator.presentation.common.getDescription
import com.pawels96.skyrimperkcalculator.presentation.common.getName
import com.pawels96.skyrimperkcalculator.presentation.common.setTransparentBackground
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.launch

class PerkInfoFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentPerkDescriptionBinding::inflate)

    private lateinit var skill: ISkill
    private lateinit var perk: Perk

    private val model: CurrentBuildViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.providerCurrentBuildVmFactory()
        )[CurrentBuildViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        perk = requireArguments().getSerializable(ARG_PERK) as Perk
        skill = requireArguments().getSerializable(ARG_SKILL) as ISkill

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.currentBuild.collect { build ->
                    build?.let {
                        val perk = it.getSkill(skill)[perk.perk]!!
                        this@PerkInfoFragment.perk = perk
                        updateStateInfo(perk)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setWindowAnimations(R.style.BottomSheetFadeAnimation)
        view.setTransparentBackground()

        if (perk.perk is SpecialSkillPerk) {
            binding.perkSkill.visibility = View.GONE
        } else {
            val skillText = getString(R.string.required_skill) + ": " + perk.allSkillLevels
            binding.perkSkill.text = skillText
        }

        binding.perkName.text = perk.perk.getName(requireContext())
        binding.perkDesc.text = perk.perk.getDescription(requireContext())

        binding.decreaseState.setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state - 1)
        }

        binding.increaseState.setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state + 1)
        }
    }

    private fun updateStateInfo(perk: Perk) {
        val text = "${perk.state}/${perk.maxState}"
        binding.perkLevelText.text = text
    }

    companion object {

        const val TAG: String = "perk_info"

        private const val ARG_SKILL = "skill"
        private const val ARG_PERK = "perk"

        fun create(skill: ISkill, perk: Perk): PerkInfoFragment {
            return PerkInfoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PERK, perk)
                    putSerializable(ARG_SKILL, skill)
                }
            }
        }
    }
}