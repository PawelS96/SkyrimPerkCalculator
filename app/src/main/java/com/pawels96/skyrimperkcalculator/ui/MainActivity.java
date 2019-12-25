package com.pawels96.skyrimperkcalculator.ui;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pawels96.skyrimperkcalculator.Database;
import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.models.Build;
import com.pawels96.skyrimperkcalculator.models.Perk;
import com.pawels96.skyrimperkcalculator.models.Skill;

import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.DEFAULT_BUILD_NAME;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_BUILD_SELECTED;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_FIRST_LAUNCH;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_MULTIPLIER;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_NAME;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_PERK_SYSTEM;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_SKILL_SELECTED;
import static com.pawels96.skyrimperkcalculator.Utils.getFragmentTag;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.ORDINATOR;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.VANILLA;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.VOKRII;


public class MainActivity extends AppCompatActivity
        implements SkillTreeFragment.OnFragmentInteractionListener, BuildListDialog.BuildListCallback {

    private Build build;
    private TextView allPerks, reqLevel;
    private LoopingViewPager viewPager;
    private SharedPreferences sp;
    private Database database;
    private float multiplier;

    public Build getBuild() {
        return build;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        viewPager = findViewById(R.id.viewPager);
        final TabLayout tabLayout = findViewById(R.id.tabs);

        SkillFragmentAdapter skillFragmentAdapter = new SkillFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(skillFragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setCurrentItem(sp.getInt(PREFS_SKILL_SELECTED, 0));
        skillFragmentAdapter.notifyDataSetChanged();
        viewPager.enableLoop(SkillEnum.values().length);
        viewPager.setListener(() -> getFragment(viewPager.getCurrentItem()).cancelHold());

        allPerks = findViewById(R.id.allPerks);
        reqLevel = findViewById(R.id.reqLevel);

        Button save = findViewById(R.id.skillsButton);
        Button load = findViewById(R.id.loadButton);
        Button options = findViewById(R.id.optionsButton);

        View.OnClickListener listener = v -> {
            switch (v.getId()) {

                case R.id.skillsButton:
                    showSkillsPopup();
                    break;
                case R.id.loadButton:
                    showBuildList();
                    break;
                case R.id.optionsButton:
                    showOptionsPopup();
                    break;
            }
        };
        save.setOnClickListener(listener);
        load.setOnClickListener(listener);
        options.setOnClickListener(listener);

        multiplier = sp.getFloat(PREFS_MULTIPLIER, 1f);

        String lastBuildName = sp.getString(PREFS_BUILD_SELECTED, DEFAULT_BUILD_NAME);
        String perkSystemString = sp.getString(PREFS_PERK_SYSTEM, "VANILLA");
        PerkSystem perkSystem = PerkSystem.valueOf(perkSystemString);

        database = Database.create(this);

        build = database.getByName(lastBuildName, perkSystem);

        if (build == null) build = database.getAllBuilds(perkSystem).get(0);

        updateBuildInfo();

        boolean firstLaunch = sp.getBoolean(PREFS_FIRST_LAUNCH, true);

        if (firstLaunch)
            showTutorial();
    }

    private void showTutorial() {

        CustomDialogBuilder builder = new CustomDialogBuilder(this);
        builder.setMessage(getString(R.string.msg_tutorial));
        builder.setPositiveButton(getString(R.string.ok_alt), (dialog, which) -> {
            sp.edit().putBoolean(PREFS_FIRST_LAUNCH, false).apply();
            dialog.dismiss();
        });

        builder.create().show();
    }

    private void showSkillsPopup() {

        CustomDialogBuilder dialogBuilder = new CustomDialogBuilder(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.list_builds, null);

        final ListView lv = customView.findViewById(R.id.listView);

        List<Skill> skills = build.getSkills();

        final SkillAdapter skillAdapter = new SkillAdapter(this, skills);
        dialogBuilder.setView(customView);

        lv.setAdapter(skillAdapter);
        skillAdapter.notifyDataSetChanged();
        lv.setDividerHeight(1);
        lv.setSelection(viewPager.getCurrentItem());

        final Dialog listDialog = dialogBuilder.create();

        lv.setOnItemClickListener((parent, view, position, id) -> {
            viewPager.setCurrentItem(position, false);
            listDialog.dismiss();
        });

        listDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        database.updateBuild(build);

        sp.edit()
                .putInt(PREFS_SKILL_SELECTED, viewPager.getCurrentItem())
                .putString(PREFS_BUILD_SELECTED, build.getName())
                .putString(PREFS_PERK_SYSTEM, build.getPerkSystem().toString())
                .apply();
    }

    private SkillTreeFragment getFragment(int id) {
        return (SkillTreeFragment) getSupportFragmentManager().findFragmentByTag(getFragmentTag(R.id.viewPager, id));
    }

    private void updateBuildInfo() {
        String allPerksText = getString(R.string.all_active_perks) + ": " + build.getSelectedPerksCount();
        allPerks.setText(allPerksText);
        String requiredLevelText = getString(R.string.required_lvl) + ": " + build.getRequiredLevel(multiplier);
        reqLevel.setText(requiredLevelText);
    }

    private void showBuildList() {
        BuildListDialog dialog = BuildListDialog.create(build.getPerkSystem(), build.getName());
        dialog.show(getSupportFragmentManager(), BuildListDialog.class.getSimpleName());
    }

    private void refreshFragments(Build build) {

        updateBuildInfo();

        int currentFragmentID = viewPager.getCurrentItem();

        getFragment(currentFragmentID).displayBuild(build);

        try {
            getFragment(currentFragmentID + 1).displayBuild(build);
        } catch (NullPointerException ignored) {
        }

        try {
            getFragment(currentFragmentID - 1).displayBuild(build);
        } catch (NullPointerException ignored) {
        }
    }

    private void showOptionsPopup() {

        CustomDialogBuilder dialogBuilder = new CustomDialogBuilder(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.popop_options, null);
        final TextView perksValue = customView.findViewById(R.id.perksValue);

        SeekBar perksSeekbar = customView.findViewById(R.id.perksSeekbar);
        perksSeekbar.setMax(49);

        final RadioButton radioVanilla = customView.findViewById(R.id.radio_vanilla);
        final RadioButton radioOrdinator = customView.findViewById(R.id.radio_ordinator);
        final RadioButton radioVokrii = customView.findViewById(R.id.radio_vokrii);

        if (build.getPerkSystem() == ORDINATOR)
            radioOrdinator.setChecked(true);
        else if (build.getPerkSystem() == VANILLA)
            radioVanilla.setChecked(true);
        else if (build.getPerkSystem() == VOKRII)
            radioVokrii.setChecked(true);

        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {

            PerkSystem system = VANILLA;

            if (isChecked) {
                switch (buttonView.getId()) {
                    case R.id.radio_ordinator:
                        system = ORDINATOR;
                        radioVanilla.setChecked(false);
                        radioVokrii.setChecked(false);
                        break;

                    case R.id.radio_vanilla:
                        system = VANILLA;
                        radioOrdinator.setChecked(false);
                        radioVokrii.setChecked(false);
                        break;

                    case R.id.radio_vokrii:
                        system = VOKRII;
                        radioOrdinator.setChecked(false);
                        radioVanilla.setChecked(false);
                        break;
                }

                build = database.getAllBuilds(system).get(0);
                refreshFragments(build);
            }
        };

        radioOrdinator.setOnCheckedChangeListener(listener);
        radioVanilla.setOnCheckedChangeListener(listener);
        radioVokrii.setOnCheckedChangeListener(listener);

        int progress = (int) (multiplier * 10) + 1;
        perksSeekbar.setProgress(progress);
        perksValue.setText(": " + String.format("%.1f", multiplier).replace(",", "."));
        perksSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            float value;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = (float) progress / 10 + 0.1f;
                perksValue.setText(": " + String.format("%.1f", value).replace(",", "."));
                multiplier = value;
                updateBuildInfo();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sp.edit().putFloat(PREFS_MULTIPLIER, value).apply();
                multiplier = value;
            }
        });

        dialogBuilder.setPositiveButton("ok", (dialog, which) -> dialog.dismiss());
        dialogBuilder.setView(customView);
        dialogBuilder.create().show();
    }

    @Override
    public void onPerkChanged(SkillEnum skill, Perk perk, int state) {

        try {
            build.
                    getSkill(skill).
                    get(perk.getPerk()).
                    setState(state);

            new UpdateBuildAsync(database).execute(build);
            updateBuildInfo();

        } catch (NullPointerException ignored) {      }
    }

    @Override
    public void onSelected(Build selectedBuild) {
        build = selectedBuild;
        refreshFragments(build);
    }

    private static class UpdateBuildAsync extends AsyncTask<Build, Void, Void> {

        private Database db;

        UpdateBuildAsync(Database database) {
            db = database;
        }

        @Override
        protected Void doInBackground(Build... builds) {
            db.updateBuild(builds[0]);
            return null;
        }
    }

}
