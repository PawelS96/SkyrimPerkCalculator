package com.pawels96.skyrimperkcalculator.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.domain.Build
import com.pawels96.skyrimperkcalculator.domain.Perk
import com.pawels96.skyrimperkcalculator.domain.enums.SkillEnum
import com.pawels96.skyrimperkcalculator.presentation.dialogs.CustomDialogBuilder
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel
import com.pawels96.skyrimperkcalculator.presentation.views.GraphView
import com.pawels96.skyrimperkcalculator.presentation.views.GraphView.OnNodeClickedListener

class SkillTreeFragment : Fragment() {
    private var graphView: GraphView? = null
    private var activePerks: TextView? = null
    private var reqSkill: TextView? = null
    private var skillEnum: SkillEnum? = null

    private val model: BuildsViewModel by lazy { ViewModelProvider(requireActivity(), Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        skillEnum = arguments!!.getSerializable(ARG_1) as SkillEnum
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

        val skill = build.getSkill(skillEnum!!)!!
        val perksText = getString(R.string.active_perks) + ": " + skill.perksCount
        val skillText = getString(R.string.required_skill) + ": " + skill.requiredSkillLevel
        graphView!!.display(skill)
        activePerks!!.text = perksText
        reqSkill!!.text = skillText
    }

    fun cancelHold() {
        graphView!!.cancelHold()
    }

    private val listener: OnNodeClickedListener = object : OnNodeClickedListener {
        override fun onNodeClicked(perk: Perk) {
            model.changePerkState(skillEnum!!, perk)
        }

        override fun onNodeHolding(perk: Perk) {
            showPerkDescription(perk)
        }
    }

    private fun showPerkDescription(perk: Perk) {
        val dialogBuilder = CustomDialogBuilder(context)
        val customView = View.inflate(context, R.layout.popup_perk_description, null)
        dialogBuilder.setView(customView)
        val perkDesc = customView.findViewById<TextView>(R.id.perk_desc)
        val perkSkill = customView.findViewById<TextView>(R.id.perk_skill)

        val seekBar = customView.findViewById<SeekBar>(R.id.perkLevelSeekBar)

        val stateInfo = customView.findViewById<TextView>(R.id.stateInfo)
        stateInfo.text = perk.stateAsString

        seekBar.apply {
            max = perk.maxState
            progress = perk.state

            setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    perk.state = progress
                    stateInfo.text = perk.stateAsString
                    model.changePerkState(skillEnum!!, perk, progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

        }





        val skillText = getString(R.string.required_skill) + ": " + perk.allSkillLevels
        perkSkill.text = skillText
        perkDesc.text = Utils.getPerkDescription(activity, perk.perk)
        dialogBuilder.setTitle(Utils.getPerkName(activity, perk.perk))
        dialogBuilder.setCancelable(true)
        val dialog = dialogBuilder.create()
        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        dialog.show()
    }

    companion object {
        const val ARG_1 = "arg1"
        @JvmStatic
        fun newInstance(skill: SkillEnum?): SkillTreeFragment {
            val fragment = SkillTreeFragment()
            val args = Bundle()
            args.putSerializable(ARG_1, skill)
            fragment.arguments = args
            return fragment
        }
    }
}