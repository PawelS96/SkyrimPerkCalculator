package com.pawels96.skyrimperkcalculator.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.pawels96.skyrimperkcalculator.R;

public class CustomDialogBuilder extends AlertDialog.Builder {

    public CustomDialogBuilder(Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public CustomDialogBuilder(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public AlertDialog create() {
        AlertDialog dialog = super.create();
        if (dialog.getWindow() == null) return dialog;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

}