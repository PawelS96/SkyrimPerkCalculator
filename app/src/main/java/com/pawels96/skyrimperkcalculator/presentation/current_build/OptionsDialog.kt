package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.app.Dialog
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopopOptionsBinding
import com.pawels96.skyrimperkcalculator.domain.VampirePerkSystem
import com.pawels96.skyrimperkcalculator.domain.WerewolfPerkSystem
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.setButtonColors
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding

class OptionsDialog : BaseDialog() {

    private val binding by viewBinding(PopopOptionsBinding::inflate)

    private val model: CurrentBuildViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.providerCurrentBuildVmFactory()
        )[CurrentBuildViewModel::class.java]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

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

        return getBuilder()
            .setPositiveButton(R.string.close) { _, _ -> dismiss() }
            .setView(binding.root)
            .create().apply {
                setOnShowListener {
                    setButtonColors(requireContext())
                }
            }

    }

    private fun Float.format(): String {
        return String.format("%.1f", this).replace(",", ".")
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "OPTIONS_DIALOG"
    }
}