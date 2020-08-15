package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildDescriptionDialog(val build: Build) : BaseDialog() {
    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = CustomDialogBuilder(context)
        root = View.inflate(context, R.layout.popup_build_description, null)

        val tv = root!!.findViewById<TextView>(R.id.build_desc)

        if (build.description != null) tv.text = build.description

        builder.setTitle(build.name)
                .setCancelable(true)
                .setView(root)
                .setPositiveButton(getString(R.string.edit),null)
                .setNegativeButton("Close"){dialog, which ->  dismiss() }


        val dialog = builder.create()

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
                   hideKeyboard()
                   dismiss()
               }

               positiveButton.setText(R.string.save)
               edit.showKeyboard()
           }
       }

        return dialog
    }

    fun EditText.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(root!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun getDialogTag(): String  = TAG

    companion object {
        const val TAG : String = "BUILD_DESC"
    }
}