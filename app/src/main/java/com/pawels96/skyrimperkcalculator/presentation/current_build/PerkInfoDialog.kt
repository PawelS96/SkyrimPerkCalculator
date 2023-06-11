package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupPerkDescriptionBinding
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk
import com.pawels96.skyrimperkcalculator.presentation.common.Utils
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.launch

class PerkInfoDialog : BaseDialog() {

    private val binding by viewBinding(PopupPerkDescriptionBinding::inflate)

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
                        this@PerkInfoDialog.perk = perk
                        updateStateInfo(perk)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateStateInfo(perk: Perk) {
        binding.perkLevelText.text = "${perk.state}/${perk.maxState}"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        updateStateInfo(perk)

        if (perk.perk is SpecialSkillPerk) {
            binding.perkSkill.visibility = View.INVISIBLE
        } else {
            val skillText = getString(R.string.required_skill) + ": " + perk.allSkillLevels
            binding.perkSkill.text = skillText
        }

        binding.perkDesc.text = Utils.getPerkDescription(activity, perk.perk)

        binding.decreaseState.setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state - 1)
        }

        binding.increaseState.setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state + 1)
        }

        return getBuilder()
            .setView(binding.root)
            .setTitle(Utils.getPerkName(activity, perk.perk))
            .setCancelable(true)
            .create()
            .apply { window?.setGravity(Gravity.BOTTOM) }
    }

    override fun getDialogTag(): String = TAG

    companion object {

        const val TAG: String = "PerkInfoDialog"

        private const val ARG_SKILL = "skill"
        private const val ARG_PERK = "perk"

        fun create(skill: ISkill, perk: Perk) : PerkInfoDialog {
            return PerkInfoDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PERK, perk)
                    putSerializable(ARG_SKILL, skill)
                }
            }
        }
    }
}