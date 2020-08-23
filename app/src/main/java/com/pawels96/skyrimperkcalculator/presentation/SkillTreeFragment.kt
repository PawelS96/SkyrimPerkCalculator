package com.pawels96.skyrimperkcalculator.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.*
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BaseDialog
import com.pawels96.skyrimperkcalculator.presentation.dialogs.CustomDialogBuilder
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.views.GraphView
import com.pawels96.skyrimperkcalculator.presentation.views.GraphView.OnNodeClickedListener

//TODO refactor

class SkillTreeFragment : Fragment() {
    private var graphView: GraphView? = null
    private var activePerks: TextView? = null
    private var reqSkill: TextView? = null
    private var skillEnum: ISkill? = null

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillEnum = arguments!!.getSerializable(ARG_1) as ISkill
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_skilltree, container, false)
        graphView = rootView.findViewById(R.id.graph)
        activePerks = rootView.findViewById(R.id.skillPerks)
        reqSkill = rootView.findViewById(R.id.skillLevel)
        graphView?.setListener(listener)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.currentBuild.observe(viewLifecycleOwner, Observer { displayBuild(it) })
    }

    private fun displayBuild(build: Build) {

        val skill = build.getSkill(skillEnum!!)
        val perksText = getString(R.string.active_perks) + ": ${skill.selectedPerksCount}/${skill.maxPerks}"
        val requirementLabelRes = if (skill is SpecialSkill) R.string.required_kills else R.string.required_skill
        val skillText = getString(requirementLabelRes) + ": " + skill.requiredSkillLevel
        graphView!!.display(skill)
        activePerks!!.text = perksText
        reqSkill!!.text = skillText
    }

    fun cancelHold() {
        graphView!!.cancelHold()
    }

    private val listener: OnNodeClickedListener = object : OnNodeClickedListener {
        override fun onNodeClicked(perk: Perk) { model.changePerkState(skillEnum!!, perk.perk) }
        override fun onNodeHolding(perk: Perk) { showPerkDescription(perk) }
    }

    private fun showPerkDescription(perk: Perk) {
       PerkInfoDialog(skillEnum!!, perk).show(childFragmentManager)
    }

    companion object {
        const val ARG_1 = "arg1"

        fun create(skill: ISkill): SkillTreeFragment {
            val fragment = SkillTreeFragment()
            val args = Bundle()
            args.putSerializable(ARG_1, skill)
            fragment.arguments = args
            return fragment
        }
    }
}

class PerkInfoDialog(private val skill: ISkill, private val perk: Perk) : BaseDialog() {

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }
    private lateinit var perkStateTV: TextView

    private fun updateStateInfo(perk: Perk) {
        perkStateTV.text = "${perk.state}/${perk.maxState}"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = getBuilder()
        root = View.inflate(context, R.layout.popup_perk_description, null)
        val perkDesc = root!!.findViewById<TextView>(R.id.perk_desc)
        val perkSkill = root!!.findViewById<TextView>(R.id.perk_skill)
        perkStateTV = root!!.findViewById(R.id.perkLevelText)

        updateStateInfo(perk)

        if (perk.perk is SpecialSkillPerk) {
            perkSkill.visibility = View.INVISIBLE
        } else {
            val skillText = getString(R.string.required_skill) + ": " + perk.allSkillLevels
            perkSkill.text = skillText
        }

        perkDesc.text = Utils.getPerkDescription(activity, perk.perk)

        root!!.findViewById<ImageButton>(R.id.decreaseState).setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state - 1)
        }

        root!!.findViewById<ImageButton>(R.id.increaseState).setOnClickListener {
            model.changePerkState(skill, perk.perk, perk.state + 1)
        }

        return builder
                .setTitle(Utils.getPerkName(activity, perk.perk))
                .setView(root)
                .setCancelable(true)
                .create()
                .apply { window?.setGravity(Gravity.BOTTOM) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.currentBuild.observe(viewLifecycleOwner, Observer {
            updateStateInfo(it.getSkill(skill)[perk.perk]!!)
        })
    }

    override fun getDialogTag(): String = TAG

    companion object {
        const val TAG: String = "PerkInfoDialog"
    }
}