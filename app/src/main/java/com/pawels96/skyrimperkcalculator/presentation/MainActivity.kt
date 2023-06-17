package com.pawels96.skyrimperkcalculator.presentation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.pawels96.skyrimperkcalculator.Injector
import com.pawels96.skyrimperkcalculator.R
import com.pawels96.skyrimperkcalculator.TutorialDialog
import com.pawels96.skyrimperkcalculator.databinding.ActivityMainBinding
import com.pawels96.skyrimperkcalculator.domain.ISkill
import com.pawels96.skyrimperkcalculator.domain.EMainSkill
import com.pawels96.skyrimperkcalculator.domain.ESpecialSkill
import com.pawels96.skyrimperkcalculator.domain.SkillType
import com.pawels96.skyrimperkcalculator.presentation.build_list.BuildsDialog
import com.pawels96.skyrimperkcalculator.presentation.common.getFragmentTag
import com.pawels96.skyrimperkcalculator.presentation.common.getName
import com.pawels96.skyrimperkcalculator.presentation.common.viewBinding
import com.pawels96.skyrimperkcalculator.presentation.current_build.OptionsDialog
import com.pawels96.skyrimperkcalculator.presentation.current_build.SkillListDialog
import com.pawels96.skyrimperkcalculator.presentation.current_build.CurrentBuildViewModel
import com.pawels96.skyrimperkcalculator.presentation.current_build.SkillTreeFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val prefs = Injector.prefs
    private val model: CurrentBuildViewModel by lazy { ViewModelProvider(this, Injector.providerCurrentBuildVmFactory())[CurrentBuildViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) window.navigationBarColor = Color.BLACK

        setContentView(binding.root)

        binding.loadButton.setOnClickListener { showBuildList() }
        binding.skillsButton.setOnClickListener { showSkillList() }
        binding.optionsButton.setOnClickListener { showOptions() }

        val skillFragmentAdapter = SkillFragmentAdapter(supportFragmentManager)

        DISPLAYED_SKILLS.forEach { skill ->
            val skillName = skill.getName(this)
            val tab = binding.tabs.newTab().apply { text = skillName }
            binding.tabs.addTab(tab)
        }

        binding.viewPager.apply {
            adapter = skillFragmentAdapter
            addOnPageChangeListener(object : TabLayoutOnPageChangeListener(binding.tabs) {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateTabColor(position)
                }
            })
            enableLoop(DISPLAYED_SKILLS.size)
            setListener { getFragment(this.currentItem)?.cancelHold() }
            updateTabColor(prefs.selectedPage)
            currentItem = prefs.selectedPage
        }

        skillFragmentAdapter.notifyDataSetChanged()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.currentBuild.collect { build ->
                    build?.let { updateBuildInfo(it) }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.requiredLevel.collect { updateRequiredLevel(it) }
            }
        }

        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager))

        if (prefs.firstLaunch) {
            Handler(Looper.getMainLooper()).postDelayed({
                if (!isFinishing) {
                    prefs.firstLaunch = false
                    TutorialDialog().show(supportFragmentManager)
                }
            }, 1500)
        }

        observeAttachedFragments()
    }

    override fun onPause() {
        super.onPause()
        prefs.selectedPage = binding.viewPager.currentItem
    }

    private fun observeAttachedFragments() {
        supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            when (fragment) {
                is SkillListDialog -> fragment.onSelect = { index ->
                    binding.viewPager.setCurrentItem(index, false)
                }
            }
        }
    }

    private fun updateTabColor(position: Int) {
        val skill = DISPLAYED_SKILLS[position]
        val colorRes = when (skill.type) {
            SkillType.STEALTH -> R.color.skillStealthBright
            SkillType.COMBAT -> R.color.skillCombatBright
            SkillType.MAGIC -> R.color.skillMagicBright
            SkillType.SPECIAL -> when (skill) {
                ESpecialSkill.SKILL_VAMPIRISM -> R.color.skillVampireBright
                ESpecialSkill.SKILL_LYCANTHROPY -> R.color.skillWerewolfBright
                else -> R.color.colorAccent
            }
        }
        val selectedColor = ContextCompat.getColor(this, colorRes)
        val normalColor = ContextCompat.getColor(this, R.color.colorFontAlt)
        binding.tabs.setTabTextColors(normalColor, selectedColor)
    }

    private fun getFragment(index: Int): SkillTreeFragment? {
        val tag = binding.viewPager.getFragmentTag(index)
        return supportFragmentManager.findFragmentByTag(tag) as SkillTreeFragment?
    }

    private fun updateBuildInfo(build: com.pawels96.skyrimperkcalculator.domain.Build) {
        val vampPerks = build.getSkill(ESpecialSkill.SKILL_VAMPIRISM).selectedPerksCount
        val lycPerks = build.getSkill(ESpecialSkill.SKILL_LYCANTHROPY).selectedPerksCount
        val specialPerkSum = vampPerks + lycPerks
        val specialPerksText = if (specialPerkSum > 0) " (+$specialPerkSum)" else ""
        val allPerksText = getString(R.string.all_active_perks) + ": " + build.getSelectedPerksCount() + specialPerksText
        binding.allPerks.text = allPerksText
    }

    private fun updateRequiredLevel(level: Int) {
        val requiredLevelText = getString(R.string.required_lvl) + ": " + level
        binding.reqLevel.text = requiredLevelText
    }

    private fun showSkillList() = SkillListDialog().show(supportFragmentManager, SkillListDialog.TAG)

    private fun showBuildList() = BuildsDialog().show(supportFragmentManager)

    private fun showOptions() = OptionsDialog().show(supportFragmentManager)

    private class SkillFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = SkillTreeFragment.create(DISPLAYED_SKILLS[position])

        override fun getCount(): Int = DISPLAYED_SKILLS.size
    }

    companion object {
        val DISPLAYED_SKILLS: List<ISkill> = EMainSkill.values().toList() + ESpecialSkill.values().toList()
    }
}