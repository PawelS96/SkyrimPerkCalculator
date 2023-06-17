package com.pawels96.skyrimperkcalculator.presentation.common.dialogs

import android.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialog : DialogFragment() {

    abstract fun getDialogTag(): String

    fun show(fm: FragmentManager) = show(fm, getDialogTag())

    protected fun getBuilder(): AlertDialog.Builder {
        return CustomDialogBuilder(context)
    }
}