package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.DialogListBuildsBinding
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.BuildAdapter

class BuildsDialog : BaseDialog() {

    private var _binding: DialogListBuildsBinding? = null
    private val binding get() = _binding!!

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    private lateinit var buildAdapter: BuildAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogListBuildsBinding.inflate(LayoutInflater.from(context))

        val spinnerItems = PerkSystem.values().map { Utils.getPerkSystemName(it, context) }

        binding.spinner.apply {
            adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
            setSelection(PerkSystem.values().indexOf(model.currentBuild.value!!.system))
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                private var firstSelection = true

                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

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

            override fun onRename(build: Build) {
                showSaveDialog(true, build)
            }

            override fun showDescription(build: Build) {
                BuildDescriptionDialog(build).show(childFragmentManager)
            }

            override fun onDelete(build: Build) {
                DeleteDialog(build).show(childFragmentManager)
            }
        }

        buildAdapter = BuildAdapter(requireContext(), adapterCallback)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = buildAdapter
        }

        val dialog = getBuilder().setView(binding.root)
                .setPositiveButton(R.string.new_build, null)
                .create()

        dialog.setOnShowListener {
            dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener { showSaveDialog(false) }
        }

        return dialog
    }

    private fun showSaveDialog(rename: Boolean, build: Build? = null) {
        val action = if (rename) NameInputDialog.Action.Rename(build!!) else NameInputDialog.Action.Create
        NameInputDialog(action).show(childFragmentManager)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.buildList.observe(this, Observer {
            val index = it.indexOfFirst { b -> b.name == model.currentBuild.value!!.name }
            buildAdapter.display(it, index, model.multiplier)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

