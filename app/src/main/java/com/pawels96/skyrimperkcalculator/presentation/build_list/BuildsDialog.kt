package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.DialogListBuildsBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.common.Utils
import com.pawels96.skyrimperkcalculator.presentation.common.configureEffects
import com.pawels96.skyrimperkcalculator.presentation.common.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.common.setButtonColors
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import kotlinx.coroutines.launch

class BuildsDialog : BaseDialog() {

    private val binding by viewBinding(DialogListBuildsBinding::inflate)

    private val model: BuildListViewModel by lazy {
        ViewModelProvider(
            this,
            Injector.provideListVmFactory()
        )[BuildListViewModel::class.java]
    }

    private lateinit var buildAdapter: BuildAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val spinnerItems = PerkSystem.values().map { Utils.getPerkSystemName(it, context) }

        binding.spinner.apply {
            adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
            setSelection(PerkSystem.values().indexOf(model.currentPerkSystem))
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                private var firstSelection = true

                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (firstSelection)
                        firstSelection = false
                    else
                        model.changePerkSystem(PerkSystem.values()[position])
                }
            }
        }

        val adapterCallback = object : BuildAdapter.BuildAdapterCallback {
            override fun onClick(build: Build) {
                model.selectBuild(build)
                dismiss()
            }

            override fun onContextMenuClick(build: Build, view: View) {
                showContextMenu(build, view)
            }
        }

        buildAdapter = BuildAdapter(requireContext(), adapterCallback)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            configureEffects(overscrollMagnitude = 0.01f, flingMagnitude = 0.01f) {}
            adapter = buildAdapter
        }

        val dialog = getBuilder().setView(binding.root)
            .setPositiveButton(R.string.new_build, null)
            .setNegativeButton(R.string.close) { _, _ -> dismiss() }
            .create()

        dialog.setOnShowListener {
            dialog.setButtonColors(requireContext())
            dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener { showCreateBuildDialog() }
        }

        return dialog
    }

    private fun showContextMenu(build: Build, view: View) {
        val popup = PopupMenu(context, view)
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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.buildList.collect { buildAdapter.display(it) }
            }
        }
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

