package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class DeleteBuildDialog : BaseDialog() {

    private var buildId: Long? = null

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.provideVmFactory()
        )[BuildsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildId = requireArguments().getLong(ARG_BUILD_ID)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return getBuilder()
            .setTitle(R.string.delete_build)
            .setCancelable(true)
            .setPositiveButton(R.string.delete) { dialog, which ->
                buildId?.let {
                    model.deleteBuild(it)
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, which -> dismiss() }
            .create()
    }

    override fun getDialogTag(): String = TAG

    companion object {

        const val TAG: String = "DELETE_DIALOG"

        private const val ARG_BUILD_ID = "build_id"

        fun create(buildId: Long): DeleteBuildDialog {
            return DeleteBuildDialog().apply {
                arguments = Bundle().apply {
                    putLong(ARG_BUILD_ID, buildId)
                }
            }
        }
    }
}