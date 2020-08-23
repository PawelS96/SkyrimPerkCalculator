package com.pawels96.skyrimperkcalculator.presentation

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.databinding.ActivityMainBinding
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.enums.ESpecialSkill
import com.pawels96.skyrimperkcalculator.presentation.Utils.getSkillName
import com.pawels96.skyrimperkcalculator.presentation.dialogs.BuildsDialog
import com.pawels96.skyrimperkcalculator.presentation.dialogs.CustomDialogBuilder
import com.pawels96.skyrimperkcalculator.presentation.dialogs.OptionsDialog
import com.pawels96.skyrimperkcalculator.presentation.viewmodels.BuildsViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefs = Injector.prefs
    private val model: BuildsViewModel by lazy { ViewModelProvider(this, Injector.provideVmFactory())[BuildsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= 21) window.navigationBarColor = Color.BLACK

        binding.loadButton.setOnClickListener { showBuildList() }
        binding.skillsButton.setOnClickListener { showSkillsPopup() }
        binding.optionsButton.setOnClickListener { showOptions() }

        val skillFragmentAdapter = SkillFragmentAdapter(supportFragmentManager)

        DISPLAYED_SKILLS.forEach {
            val skillName = getSkillName(it, this)
            val tab = binding.tabs.newTab().apply { text = skillName }
            binding.tabs.addTab(tab)
        }

        binding.viewPager.apply {
            adapter = skillFragmentAdapter
            addOnPageChangeListener(TabLayoutOnPageChangeListener(binding.tabs))
            enableLoop(DISPLAYED_SKILLS.size)
            setListener { getFragment(this.currentItem)?.cancelHold() }
            currentItem = prefs.selectedPage
        }

        skillFragmentAdapter.notifyDataSetChanged()

        model.currentBuild.observe(this, Observer { updateBuildInfo(it) })
        model.requiredLevel.observe(this, Observer { updateRequiredLevel(it) })
        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager))

        if (prefs.firstLaunch)
            Handler().postDelayed({showTutorial()}, 1500)
    }

    private fun getFragment(id: Int): SkillTreeFragment? {
        return supportFragmentManager.findFragmentByTag(Utils.getFragmentTag(R.id.viewPager, id)) as SkillTreeFragment?
    }

    private fun updateBuildInfo(build: com.pawels96.skyrimperkcalculator.domain.Build) {
        val allPerksText = getString(R.string.all_active_perks).toString() + ": " + build.getSelectedPerksCount()
        binding.allPerks.text = allPerksText
        updateRequiredLevel(build.getRequiredLevel(model.multiplier))
    }

    private fun updateRequiredLevel(level: Int) {
        val requiredLevelText = getString(R.string.required_lvl).toString() + ": " + level
        binding.reqLevel.text = requiredLevelText
    }

    private fun showTutorial() {
        CustomDialogBuilder(this)
                .setMessage(getString(R.string.msg_tutorial))
                .setPositiveButton(getString(R.string.ok_alt)) { dialog: DialogInterface, which: Int ->
                    prefs.firstLaunch = false
                    dialog.dismiss()
                }
                .create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs.selectedPage = binding.viewPager.currentItem
    }

    private fun showSkillsPopup() {

        val dialogBuilder = CustomDialogBuilder(this)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = inflater.inflate(R.layout.list_builds, null)
        val lv = customView.findViewById<ListView>(R.id.listView)
        val skillAdapter = SkillAdapter(this, model.allCurrentBuildSkills)
        dialogBuilder.setView(customView)
        lv.adapter = skillAdapter
        skillAdapter.notifyDataSetChanged()
        lv.dividerHeight = 1
        lv.setSelection(binding.viewPager.currentItem)

        val listDialog: Dialog = dialogBuilder.create()

        lv.onItemClickListener = OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            binding.viewPager.setCurrentItem(position, false)
            listDialog.dismiss()
        }

        listDialog.show()
    }

    private fun showBuildList() =  BuildsDialog().show(supportFragmentManager)

    private fun showOptions() = OptionsDialog().show(supportFragmentManager)

    private class SkillFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = SkillTreeFragment.create(DISPLAYED_SKILLS[position])

        override fun getCount(): Int = DISPLAYED_SKILLS.size
    }

    companion object {
        val DISPLAYED_SKILLS : List<ISkill> = EMainSkill.values().toList() + ESpecialSkill.values().toList()
    }
}