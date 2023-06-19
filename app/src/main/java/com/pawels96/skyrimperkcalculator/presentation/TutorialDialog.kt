package com.pawels96.skyrimperkcalculator.presentation

import android.app.Dialog
import android.os.Bundle
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.setButtonColors

class TutorialDialog : BaseDialog() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return getBuilder()
            .setCancelable(true)
            .setMessage(getString(R.string.msg_tutorial))
            .setPositiveButton(getString(R.string.ok_alt)) { _, _ ->
                dismiss()
            }
            .create().apply {
                setOnShowListener {
                    setButtonColors(requireContext())
                }
            }
    }

    override fun getDialogTag(): String {
        return "tutorial"
    }
}