package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupBuildDescriptionBinding
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.showKeyboard
import com.pawels96.skyrimperkcalculator.presentation.viewBinding
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildDescriptionDialog : BaseDialog() {

    private val binding by viewBinding(PopupBuildDescriptionBinding::inflate)

    private var buildId: Long = 0

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.provideVmFactory()
        )[BuildsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildId = requireArguments().getLong(ARG_BUILD_ID)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val build = model.getBuildById(buildId)
        binding.buildDesc.text = build?.description

        val dialog = getBuilder().setTitle(build?.name)
            .setCancelable(true)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.edit), null)
            .setNegativeButton(getString(R.string.close)) { dialog, which ->
                binding.root.hideKeyboard()
                dismiss()
            }
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
                    setText(build?.description)
                    setSelection(edit.text.length)
                    requestFocus()
                }

                positiveButton.setOnClickListener {
                    if (build != null) {
                        model.updateDescription(build, edit.text.toString().trim())
                    }
                    edit.hideKeyboard()
                    dismiss()
                }

                positiveButton.setText(R.string.save)
                edit.showKeyboard()
            }
        }

        return dialog
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILD_DESC"

        private const val ARG_BUILD_ID = "build_id"

        fun create(buildId: Long): BuildDescriptionDialog {
            return BuildDescriptionDialog().apply {
                arguments = Bundle().apply {
                    putLong(ARG_BUILD_ID, buildId)
                }
            }
        }
    }
}