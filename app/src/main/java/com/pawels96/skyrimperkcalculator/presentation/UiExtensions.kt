package com.pawels96.skyrimperkcalculator.presentation

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

fun RecyclerView.addDivider(drawable: Int) {
    val deco = object : DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
                outRect.setEmpty()
            else
                super.getItemOffsets(outRect, view, parent, state)
        }
    }
    deco.setDrawable(ContextCompat.getDrawable(context, drawable)!!)
    this.addItemDecoration(deco)
}

fun String.colored(color: Int): SpannableString {
    return SpannableString(this).apply {
        setSpan(ForegroundColorSpan(color), 0, this.length, 0)
    }
}
