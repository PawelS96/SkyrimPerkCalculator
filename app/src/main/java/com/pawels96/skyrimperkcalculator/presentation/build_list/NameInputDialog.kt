package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentNameInputBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.common.setButtonColors
import com.pawels96.skyrimperkcalculator.presentation.common.toast
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NameInputDialog : BaseDialog() {

    private val binding by viewBinding(FragmentNameInputBinding::inflate)

    private var buildId: Long? = null
    private var build: Build? = null

    private val model: BuildListViewModel by lazy {
        ViewModelProvider(
            requireParentFragment(),
            Injector.provideListVmFactory()
        )[BuildListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(ARG_BUILD_ID)) {
            val id = requireArguments().getLong(ARG_BUILD_ID)
            buildId = id
            build = runBlocking { model.getBuildById(id) }
        }

        observeEvents()
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

        binding.nameEdit.maxLines = 1
        binding.nameEdit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onConfirm()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
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

        dialog.setOnShowListener {
            // crash here
            dialog.setButtonColors(requireContext())
            dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener { onConfirm() }
            binding.nameEdit.requestFocus()
        }

        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }

    private fun onConfirm() {
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

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.events.onEach { event ->
                    event.messageID?.let { message ->
                        requireActivity().toast(message)
                    }

                    if (event.success) {
                        binding.root.hideKeyboard()
                        dismiss()
                    }
                }.collect()
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

