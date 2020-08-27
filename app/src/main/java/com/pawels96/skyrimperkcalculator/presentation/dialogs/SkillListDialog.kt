package com.pawels96.skyrimperkcalculator.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Skill
import com.pawels96.skyrimperkcalculator.presentation.addDivider

class SkillListDialog(val skills: List<Skill>, val scrollToIndex: Int, val onSelect: (Int) -> Unit) : BaseDialog(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = getBuilder()
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.dialog_skills, null)
        val recycler = root.findViewById<RecyclerView>(R.id.skillList)
        val skillAdapter = SkillAdapter(requireContext())
        val listDialog: Dialog = builder.setView(root).create()

        skillAdapter.onItemClick = {
            onSelect(it)
            listDialog.dismiss()
        }

        listDialog.setOnShowListener {
            skillAdapter.display(skills)
            recycler.scrollToPosition(scrollToIndex)
            recycler.addDivider(requireContext(), R.drawable.divider)
            recycler.adapter = skillAdapter
        }

        return listDialog
    }

    override fun getDialogTag() = TAG

    companion object {
        const val TAG: String = "SkillListDialog"
    }
}