package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import com.pawels96.skyrimperkcalculator.R

class CustomDialogBuilder(context: Context?, themeResId: Int = R.style.CustomDialogTheme) : AlertDialog.Builder(context, themeResId) {
    override fun create(): AlertDialog {
        val dialog = super.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}
