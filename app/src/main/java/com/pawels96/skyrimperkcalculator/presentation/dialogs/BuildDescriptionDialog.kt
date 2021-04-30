package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.system.Os.close
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupBuildDescriptionBinding
import com.pawels96.skyrimperkcalculator.databinding.PopupSaveBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.showKeyboard
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildDescriptionDialog(val build: Build) : BaseDialog() {

    private var _binding: PopupBuildDescriptionBinding? = null
    private val binding get() = _binding!!

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = PopupBuildDescriptionBinding.inflate(LayoutInflater.from(context))
        binding.buildDesc.text = build.description

        val dialog = getBuilder().setTitle(build.name)
                .setCancelable(true)
                .setView(binding.root)
                .setPositiveButton(getString(R.string.edit), null)
                .setNegativeButton(getString(R.string.close)) { dialog, which ->
                    binding.root.hideKeyboard()
                    dismiss() }
                .create().apply {
                    setCanceledOnTouchOutside(false)
                }

        dialog.setOnShowListener {

            val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {

                binding.buildDesc.visibility = View.GONE
                val edit = binding.buildDescEdit

                edit.apply {
                    visibility = View.VISIBLE
                    setText(build.description)
                    requestFocus()
                    setSelection(edit.text.length)
                }

                positiveButton.setOnClickListener {
                    model.updateDescription(build, edit.text.toString().trim())
                    edit.hideKeyboard()
                    dismiss()
                }

                positiveButton.setText(R.string.save)
                edit.showKeyboard()
            }
        }

        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILD_DESC"
    }
}