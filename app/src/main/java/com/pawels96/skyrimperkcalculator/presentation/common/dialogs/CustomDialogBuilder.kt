package com.pawels96.skyrimperkcalculator.presentation.common.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.pawels96.skyrimperkcalculator.R

class CustomDialogBuilder(
    context: Context?,
    themeResId: Int = R.style.CustomDialogTheme
) : AlertDialog.Builder(context, themeResId) {
    override fun create(): AlertDialog {
        return super.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}
