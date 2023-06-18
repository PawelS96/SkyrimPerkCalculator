package com.pawels96.skyrimperkcalculator.presentation.common

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.pawels96.skyrimperkcalculator.R

fun Activity.toast(txt: String) = Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()

fun Activity.toast(resId: Int) = toast(getString(resId))

fun EditText.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun String.colored(color: Int): SpannableString {
    return SpannableString(this).apply {
        setSpan(ForegroundColorSpan(color), 0, this.length, 0)
    }
}

fun AlertDialog.setButtonColors(context: Context) {
    val textColor = ContextCompat.getColor(context, R.color.colorFont)
    getButton(Dialog.BUTTON_NEGATIVE)?.setTextColor(textColor)
    getButton(Dialog.BUTTON_POSITIVE)?.setTextColor(textColor)
}

fun ViewPager.getFragmentTag(index: Int): String {
    return "android:switcher:$id:$index"
}

fun View.setTransparentBackground() {
    (parent as? View)?.let {
        it.backgroundTintMode = PorterDuff.Mode.CLEAR
        it.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        it.setBackgroundColor(Color.TRANSPARENT)
    }
}