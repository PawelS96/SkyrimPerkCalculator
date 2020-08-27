package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.PopupSaveBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.toast
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class NameInputDialog(private val action: Action) : BaseDialog() {

    sealed class Action {
        object Create : Action()
        class Rename(val build: Build) : Action()
    }

    private var _binding: PopupSaveBinding? = null
    private val binding get() = _binding!!

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = PopupSaveBinding.inflate(LayoutInflater.from(context))

        if (action is Action.Rename) {
            binding.copyCurrentLabel.visibility = View.GONE
            binding.copyCurrentCheckbox.visibility = View.GONE

            binding.nameEdit.apply {
                setText(action.build.name)
                setSelection(text.length)
            }
        }

        val dialog =  getBuilder()
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

                when (action) {
                    is Action.Rename -> model.renameBuild(action.build, name)
                    is Action.Create -> model.createBuild(name, binding.copyCurrentCheckbox.isChecked)
                }
            }
        }

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.events.observe(this, Observer {

            when (val content = it.getContentIfNotHandled()) {

                is BuildsViewModel.Event.BuildRenamed, is BuildsViewModel.Event.BuildSaved -> {

                    content.messageID?.let {message ->
                        activity?.toast(message)
                    }

                   if (content.success){
                       binding.root.hideKeyboard()
                       dismiss()
                   }
                }
            }
        })
    }

    override fun getDialogTag(): String = TAG

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "NAME_INPUT"
    }
}

