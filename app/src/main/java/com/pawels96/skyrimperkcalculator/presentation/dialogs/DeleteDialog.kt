package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class DeleteDialog(val build: Build) : BaseDialog() {

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return getBuilder()
                .setTitle(R.string.delete_build)
                .setCancelable(true)
                .setPositiveButton(R.string.delete) { dialog, which ->
                    model.deleteBuild(build)
                }
                .setNegativeButton(R.string.cancel) { dialog, which -> dismiss() }
                .create()
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "DELETE_DIALOG"
    }
}