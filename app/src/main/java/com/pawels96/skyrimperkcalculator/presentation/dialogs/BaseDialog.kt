package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialog : DialogFragment(){

    abstract fun getDialogTag() : String

    fun show(fm: FragmentManager) = show(fm, getDialogTag())

    protected fun getBuilder() = CustomDialogBuilder(context)

    protected var root : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        root = null
    }
}