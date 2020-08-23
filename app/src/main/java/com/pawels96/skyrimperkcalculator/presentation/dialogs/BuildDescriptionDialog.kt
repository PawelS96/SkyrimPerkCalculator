package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.system.Os.close
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.hideKeyboard
import com.pawels96.skyrimperkcalculator.presentation.showKeyboard
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildDescriptionDialog(val build: Build) : BaseDialog() {

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        root = View.inflate(context, R.layout.popup_build_description, null)
        val tv = root!!.findViewById<TextView>(R.id.build_desc)
        tv.text = build.description

        val dialog = getBuilder().setTitle(build.name)
                .setCancelable(true)
                .setView(root)
                .setPositiveButton(getString(R.string.edit), null)
                .setNegativeButton(getString(R.string.close)) { dialog, which -> dismiss() }
                .create()

        dialog.setOnShowListener {

            val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {

                root!!.findViewById<TextView>(R.id.build_desc).visibility = View.GONE
                val edit = root!!.findViewById<EditText>(R.id.build_desc_edit)

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

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILD_DESC"
    }
}