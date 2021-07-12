package com.pawels96.skyrimperkcalculator.presentation.build_list

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.DialogListBuildsBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.configureEffects
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.viewBinding
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildsDialog : BaseDialog() {

    private val binding by viewBinding(DialogListBuildsBinding::inflate)

    private val model: BuildsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            Injector.provideVmFactory()
        )[BuildsViewModel::class.java]
    }

    private lateinit var buildAdapter: BuildAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val spinnerItems = PerkSystem.values().map { Utils.getPerkSystemName(it, context) }

        binding.spinner.apply {
            adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
            setSelection(PerkSystem.values().indexOf(model.currentBuild.value!!.system))
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
            configureEffects(overscrollMagnitude = 0.05f, flingMagnitude = 0.05f) {}
            adapter = buildAdapter
        }

        val dialog = getBuilder().setView(binding.root)
            .setPositiveButton(R.string.new_build, null)
            .create()

        dialog.setOnShowListener {
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

        model.buildList.observe(this, {
            val index = it.indexOfFirst { b -> b.name == model.currentBuild.value!!.name }
            buildAdapter.display(it, index, model.multiplier)
        })
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

