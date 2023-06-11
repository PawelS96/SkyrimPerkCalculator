package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.databinding.DialogSkillsBinding
import com.pawels96.skyrimperkcalculator.presentation.common.configureEffects
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding

class SkillListDialog : BaseDialog() {

    private val binding by viewBinding(DialogSkillsBinding::inflate)

    lateinit var onSelect: (Int) -> Unit
    private var indexToScroll: Int = 0

    private val model: CurrentBuildViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.providerCurrentBuildVmFactory()
        )[CurrentBuildViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        indexToScroll = requireArguments().getInt(ARG_INDEX)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = getBuilder()
        val recycler = binding.skillList
        val listDialog: Dialog = builder.setView(binding.root).create()
        val skillAdapter = SkillAdapter { index ->
            onSelect(index)
            listDialog.dismiss()
        }

        listDialog.setOnShowListener {
            skillAdapter.display(model.allCurrentBuildSkills)
            recycler.scrollToPosition(indexToScroll)
            recycler.configureEffects {}
            recycler.adapter = skillAdapter
        }

        listDialog.window?.setGravity(Gravity.BOTTOM)

        return listDialog
    }

    override fun getDialogTag() = TAG

    companion object {

        const val TAG: String = "SkillListDialog"

        private const val ARG_INDEX = "index"

        fun create(indexToScroll: Int): SkillListDialog {
            return SkillListDialog().apply {
                arguments = Bundle().apply {
                    putInt(ARG_INDEX, indexToScroll)
                }
            }
        }
    }
}