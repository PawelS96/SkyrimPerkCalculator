package com.pawels96.skyrimperkcalculator.presentation.common.dialogs

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.pawels96.skyrimperkcalculator.R

abstract class BaseDialog : DialogFragment() {

    abstract fun getDialogTag(): String

    fun show(fm: FragmentManager) = show(fm, getDialogTag())

    protected fun getBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(requireContext(), R.style.CustomDialogTheme)
    }
}
