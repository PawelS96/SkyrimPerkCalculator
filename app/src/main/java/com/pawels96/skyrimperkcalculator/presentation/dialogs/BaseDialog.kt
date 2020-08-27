package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.pawels96.skyrimperkcalculator.R

abstract class BaseDialog : DialogFragment() {

    abstract fun getDialogTag(): String

    fun show(fm: FragmentManager) = show(fm, getDialogTag())

    protected fun getBuilder(): AlertDialog.Builder {

        return if (Build.VERSION.SDK_INT < 21)
            CustomDialogBuilder(context, R.style.CustomDialogThemeApi21)
        else
            CustomDialogBuilder(context)
    }
}