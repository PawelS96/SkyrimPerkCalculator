package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.toast
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class NameInputDialog(
        private val action: Action) : BaseDialog() {

    sealed class Action {
        object Create : Action()
        class Rename(val build: Build) : Action()
    }

    private var imm: InputMethodManager? = null
    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = CustomDialogBuilder(context)
        root = View.inflate(context, R.layout.popup_save, null)

        val et = root!!.findViewById<EditText>(R.id.build_name)
        val tv = root!!.findViewById<TextView>(R.id.textView)
        val copyCurrent = root!!.findViewById<CheckBox>(R.id.checkbox)

        if (action is Action.Rename) {
            tv.visibility = View.GONE
            copyCurrent.visibility = View.GONE

            et?.setText(action.build.name)
            et?.setSelection(et.text.length)
        }

        dialogBuilder.setView(root)
                .setCancelable(true)
                .setTitle(resources.getString(R.string.msg_name_your_build))
                .setPositiveButton(getString(R.string.save), null)
                .setNegativeButton(getString(R.string.cancel)) { _: DialogInterface?, _: Int -> hideKeyboard() }

        val dialog = dialogBuilder.create()
        dialog.setOnShowListener { di: DialogInterface ->
            dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener { v: View? ->
                val name = et.text.toString().trim()

                when (action) {
                    is Action.Rename -> model.renameBuild(action.build, name)
                    is Action.Create -> model.createBuild(name, copyCurrent.isChecked)
                }
            }
        }
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.events.observe(viewLifecycleOwner, Observer {

            when (val content = it.getContentIfNotHandled()) {

                is BuildsViewModel.Event.BuildRenamed, is BuildsViewModel.Event.BuildSaved -> {

                    content.messageID?.let {message ->
                        activity?.toast(message)
                    }

                   if (content.success){
                       hideKeyboard()
                       dismiss()
                   }
                }
            }
        })
    }

    private fun hideKeyboard() {
        imm?.hideSoftInputFromWindow(root!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG = "NAME_INPUT"
    }
}

