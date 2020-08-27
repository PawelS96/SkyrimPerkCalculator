package com.pawels96.skyrimperkcalculator.presentation

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupPerkDescriptionBinding
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.SpecialSkillPerk
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class PerkInfoDialog(private val skill: ISkill, private val perk: Perk) : BaseDialog() {

    private var _binding: PopupPerkDescriptionBinding? = null
    private val binding get() = _binding!!

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    @SuppressLint("SetTextI18n")
    private fun updateStateInfo(perk: Perk) {
        binding.perkLevelText.text = "${perk.state}/${perk.maxState}"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = PopupPerkDescriptionBinding.inflate(LayoutInflater.from(context))
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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("perkDialog", "activityCreated")

        model.currentBuild.observe(this, Observer {
            updateStateInfo(it.getSkill(skill)[perk.perk]!!)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "PerkInfoDialog"
    }
}