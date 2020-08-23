package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.PerkSystem
import com.pawels96.skyrimperkcalculator.presentation.Utils
import com.pawels96.skyrimperkcalculator.presentation.toast
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.BuildAdapter

class BuildsDialog : BaseDialog() {

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    private lateinit var adapter: BuildAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        root = View.inflate(context, R.layout.dialog_list_builds, null)
        val recycler: RecyclerView = root!!.findViewById(R.id.recycler)
        val spinner = root!!.findViewById<Spinner>(R.id.spinner)
        val spinnerItems = PerkSystem.values().map { Utils.getPerkSystemName(it, context) }
        spinner.adapter = ArrayAdapter(context, R.layout.spinner_item, spinnerItems)
        spinner.setSelection(PerkSystem.values().indexOf(model.currentBuild.value!!.system))
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            private var firstSelection = true

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (firstSelection)
                    firstSelection = false
                else
                    model.changePerkSystem(PerkSystem.values()[position])
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

        adapter = BuildAdapter(requireContext(), adapterCallback)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        val dialog = getBuilder().setView(root)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.buildList.observe(viewLifecycleOwner, Observer {
            val index = it.indexOfFirst { b -> b.name == model.currentBuild.value!!.name }
            activity?.toast(index.toString())
            adapter.display(it, index, model.multiplier)
        })
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

