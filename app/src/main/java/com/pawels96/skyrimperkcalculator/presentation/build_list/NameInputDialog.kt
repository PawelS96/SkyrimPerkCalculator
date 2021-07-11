package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupSaveBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.toast
import com.pawels96.skyrimperkcalculator.presentation.viewBinding
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.AppEvent

class NameInputDialog : BaseDialog() {

    private val binding by viewBinding(PopupSaveBinding::inflate)

    private var buildId: Long? = null
    private var build: Build? = null

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.provideVmFactory()
        )[BuildsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(ARG_BUILD_ID)) {
            val id = requireArguments().getLong(ARG_BUILD_ID)
            buildId = id
            build = model.getBuildById(id)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        if (build != null) {
            binding.copyCurrentLabel.visibility = View.GONE
            binding.copyCurrentCheckbox.visibility = View.GONE

            binding.nameEdit.apply {
                setText(build?.name)
                setSelection(text.length)
            }
        }

        val dialog = getBuilder()
            .setView(binding.root)
            .setCancelable(true)
            .setTitle(resources.getString(R.string.msg_name_your_build))
            .setPositiveButton(getString(R.string.save), null)
            .setNegativeButton(getString(R.string.cancel)) { _: DialogInterface?, _: Int -> binding.root.hideKeyboard() }
            .create().apply {
                setCanceledOnTouchOutside(false)
            }

        dialog.setOnShowListener { di: DialogInterface ->
            dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener { v: View? ->
                val name = binding.nameEdit.text.toString().trim()
                val build = this.build
                if (build != null) {
                    model.renameBuild(build, name)
                } else {
                    model.createBuild(
                        name,
                        binding.copyCurrentCheckbox.isChecked
                    )
                }
            }
        }

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.events.observe(this) {

            when (val content = it.getContentIfNotHandled()) {

                is AppEvent.BuildRenamed, is AppEvent.BuildSaved -> {

                    content.messageID?.let { message ->
                        activity?.toast(message)
                    }

                    if (content.success) {
                        binding.root.hideKeyboard()
                        dismiss()
                    }
                }
            }
        }
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG = "NAME_INPUT"

        private const val ARG_BUILD_ID = "build_id"

        fun create(buildId: Long?): NameInputDialog {

            val args = Bundle().apply {
                if (buildId != null)
                    putLong(ARG_BUILD_ID, buildId)
            }

            return NameInputDialog().apply {
                arguments = args
            }
        }
    }
}

