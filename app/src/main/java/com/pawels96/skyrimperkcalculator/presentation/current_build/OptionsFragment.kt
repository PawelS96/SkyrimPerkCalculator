package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.databinding.FragmentOptionsBinding
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding

class OptionsFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentOptionsBinding::inflate)

    private val model: CurrentBuildViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.providerCurrentBuildVmFactory()
        )[CurrentBuildViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (model.currentBuild.value?.vampirePerkSystem) {
            VampirePerkSystem.VANILLA -> binding.radioVampireVanilla.isChecked = true
            VampirePerkSystem.SACROSANCT -> binding.radioVampireSacrosanct.isChecked = true
            else -> Unit
        }

        val vampireRadioClickListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val system = when (buttonView) {
                        binding.radioVampireVanilla -> VampirePerkSystem.VANILLA
                        binding.radioVampireSacrosanct -> VampirePerkSystem.SACROSANCT
                        else -> VampirePerkSystem.VANILLA
                    }

                    model.changeVampirePerkSystem(system)
                }
            }

        listOf(binding.radioVampireVanilla, binding.radioVampireSacrosanct).forEach {
            it.setOnCheckedChangeListener(vampireRadioClickListener)
        }

        when (model.currentBuild.value?.werewolfPerkSystem) {
            WerewolfPerkSystem.VANILLA -> binding.radioWereVanilla.isChecked = true
            WerewolfPerkSystem.GROWL -> binding.radioWereGrowl.isChecked = true
            else -> Unit
        }

        val werewolfRadioClickListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    val system = when (buttonView) {
                        binding.radioWereVanilla -> WerewolfPerkSystem.VANILLA
                        binding.radioWereGrowl -> WerewolfPerkSystem.GROWL
                        else -> WerewolfPerkSystem.VANILLA
                    }

                    model.changeWerewolfPerkSystem(system)
                }
            }

        listOf(binding.radioWereVanilla, binding.radioWereGrowl).forEach {
            it.setOnCheckedChangeListener(werewolfRadioClickListener)
        }

        val multiplier = model.perkMultiplier
        val seekBarProgress = multiplier * 10 + 1
        val perkCountText = ": ${multiplier.format()}"
        binding.perksSeekbar.progress = seekBarProgress.toInt()
        binding.perksValue.text = perkCountText

        binding.perksSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress.toFloat() / 10 + 0.1f
                val perkText = ": ${value.format()}"
                binding.perksValue.text = perkText
                model.perkMultiplier = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun Float.format(): String {
        return String.format("%.1f", this).replace(",", ".")
    }

    companion object {
        const val TAG: String = "OPTIONS_FRAGMENT"
    }
}