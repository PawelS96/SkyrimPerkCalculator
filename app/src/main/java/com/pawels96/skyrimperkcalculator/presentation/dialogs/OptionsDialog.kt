package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.databinding.PopopOptionsBinding
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class OptionsDialog : BaseDialog() {
    private lateinit var binding: PopopOptionsBinding

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = CustomDialogBuilder(context)
        binding = PopopOptionsBinding.inflate(LayoutInflater.from(context))

        when (model.currentBuild.value?.system){
            PerkSystem.VANILLA -> binding.radioVanilla.isChecked = true
            PerkSystem.ORDINATOR -> binding.radioOrdinator.isChecked = true
            PerkSystem.VOKRII -> binding.radioVokrii.isChecked = true
        }

        val radioClickListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            var system : PerkSystem = PerkSystem.VANILLA

            if (isChecked) {
                when (buttonView) {
                    binding.radioVanilla -> {
                        system = PerkSystem.VANILLA
                        binding.radioOrdinator.isChecked = false
                        binding.radioVokrii.isChecked = false
                    }
                    binding.radioOrdinator -> {
                        system = PerkSystem.ORDINATOR
                        binding.radioVanilla.isChecked = false
                        binding.radioVokrii.isChecked = false
                    }
                    binding.radioVokrii -> {
                        system = PerkSystem.VOKRII
                        binding.radioVanilla.isChecked = false
                        binding.radioOrdinator.isChecked = false
                    }
                }

                model.changePerkSystem(system)
            }
        }

        listOf(binding.radioOrdinator, binding.radioVanilla, binding.radioVokrii).forEach {
            it.setOnCheckedChangeListener(radioClickListener)
        }

        val seekBarProgress = model.multiplier * 10  + 1
        val perkCountText = ": ${formatFloat(model.multiplier)}"
        binding.perksSeekbar.progress = seekBarProgress.toInt()
        binding.perksValue.text = perkCountText

        binding.perksSeekbar.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress.toFloat() / 10 + 0.1f
                val perkText =  ": ${formatFloat(value)}"
                binding.perksValue.text = perkText
                model.multiplier = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {            }
        })

        return dialogBuilder
                .setPositiveButton("ok") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setView(binding.root)
                .create()
    }

    private fun formatFloat(float: Float): String{
       return String.format("%.1f", float).replace(",", ".")
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG : String  = "OPTIONS_DIALOG"
    }

}