package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentBuildDescriptionBinding
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.common.setButtonColors
import com.pawels96.skyrimperkcalculator.presentation.common.showKeyboard
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.runBlocking

class BuildDescriptionDialog : BaseDialog() {

    private val binding by viewBinding(FragmentBuildDescriptionBinding::inflate)

    private var buildId: Long = 0

    private val model: BuildListViewModel by lazy {
        ViewModelProvider(
            requireParentFragment(),
            Injector.provideListVmFactory()
        )[BuildListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildId = requireArguments().getLong(ARG_BUILD_ID)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val build = runBlocking { model.getBuildById(buildId) }
        binding.buildDesc.text = build?.description

        val dialog = getBuilder().setTitle(build?.name)
            .setCancelable(true)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.edit), null)
            .setNegativeButton(getString(R.string.close)) { _, _ ->
                binding.root.hideKeyboard()
                dismiss()
            }
            .create()

        dialog.setOnShowListener {
            dialog.setButtonColors(requireContext())
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