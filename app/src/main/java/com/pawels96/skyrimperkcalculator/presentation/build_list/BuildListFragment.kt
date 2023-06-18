package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.FragmentBuildListBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.common.configureEffects
import com.pawels96.skyrimperkcalculator.presentation.common.getName
import com.pawels96.skyrimperkcalculator.presentation.common.setTransparentBackground
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.launch

class BuildListFragment : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentBuildListBinding::inflate)

    private val model: BuildListViewModel by lazy {
        ViewModelProvider(
            this,
            Injector.provideListVmFactory()
        )[BuildListViewModel::class.java]
    }

    private lateinit var buildAdapter: BuildAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.buildList.collect { list ->
                    // this list should never be empty except for the initial state
                    // skipping displaying the empty list makes displaying the list with values smoother
                    if (list.isNotEmpty()) {
                        buildAdapter.submitList(list)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.currentPerkSystem.collect { perkSystem ->
                    binding.picker.text = perkSystem.getName(requireContext())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setTransparentBackground()

        val adapterCallback = object : BuildAdapter.BuildAdapterCallback {
            override fun onClick(build: Build) {
                model.selectBuild(build)
                dismiss()
            }

            override fun onContextMenuClick(build: Build, view: View) {
                showContextMenu(build, view)
            }
        }

        binding.createBuild.setOnClickListener { showCreateBuildDialog() }
        binding.picker.setOnClickListener {
            val popup = PopupMenu(requireContext(), binding.picker)
            PerkSystem.values().forEach {
                popup.menu.add(NONE, it.ordinal, it.ordinal, it.getName(requireContext()))
            }
            popup.setOnMenuItemClickListener { item ->
                model.changePerkSystem(PerkSystem.values()[item.itemId])
                return@setOnMenuItemClickListener true
            }
            popup.show()
        }

        buildAdapter = BuildAdapter(requireContext(), adapterCallback)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            configureEffects(overscrollMagnitude = 0.01f, flingMagnitude = 0.01f) {}
            adapter = buildAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet =
                it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let { sheet ->
                val behavior = BottomSheetBehavior.from(sheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun showContextMenu(build: Build, view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(R.menu.menu_list_item, popup.menu)

        if (!model.canDeleteBuild()) popup.menu.findItem(R.id.delete).isVisible = false
        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.rename -> showRenameDialog(build)
                R.id.delete -> DeleteBuildDialog.create(build.id).show(childFragmentManager)
                R.id.description -> BuildDescriptionDialog.create(build.id)
                    .show(childFragmentManager)
            }
            true
        }
        popup.show()
    }

    private fun showCreateBuildDialog() {
        NameInputDialog.create(null).show(childFragmentManager)
    }

    private fun showRenameDialog(build: Build) {
        NameInputDialog.create(build.id).show(childFragmentManager)
    }

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

