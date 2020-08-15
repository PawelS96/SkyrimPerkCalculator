package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.presentation.BuildAdapter
import com.pawels96.skyrimperkcalculator.presentation.toast
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel

class BuildsDialog : BaseDialog() {

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    private lateinit var adapter: BuildAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = getBuilder()
        root = View.inflate(context, R.layout.dialog_list_builds, null)
        val recycler: RecyclerView = root!!.findViewById(R.id.recycler)

        adapter = BuildAdapter(context)

        adapter.setCallback(object : BuildAdapter.BuildAdapterCallback {
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
        })

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        val dialog = dialogBuilder.setView(root)
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
            adapter.display(it, index, model.multiplier)
        })
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "BUILDS_DIALOG"
    }
}

