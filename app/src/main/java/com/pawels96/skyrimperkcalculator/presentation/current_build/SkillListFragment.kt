package com.pawels96.skyrimperkcalculator.presentation.current_build

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentSkillsBinding
import com.pawels96.skyrimperkcalculator.presentation.common.setTransparentBackground
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding

class SkillListFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentSkillsBinding::inflate)

    var onSelect: ((Int) -> Unit)? = null

    private val model: CurrentBuildViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.providerCurrentBuildVmFactory()
        )[CurrentBuildViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setTransparentBackground()

        val recycler = binding.skillList
        val skillAdapter = SkillAdapter { index ->
            onSelect?.invoke(index)
            dismiss()
        }

        skillAdapter.display(model.allCurrentBuildSkills)
        recycler.adapter = skillAdapter
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet = it.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    companion object {
        const val TAG: String = "SkillListDialog"
    }
}
