package com.pawels96.skyrimperkcalculator.ui;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pawels96.skyrimperkcalculator.DatabaseHelper;
import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.models.Build;
import com.pawels96.skyrimperkcalculator.models.BuildViewModel;
import com.pawels96.skyrimperkcalculator.models.Perk;
import com.pawels96.skyrimperkcalculator.models.Skill;

import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.DEFAULT_BUILD_NAME;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_BUILD_SELECTED;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_MULTIPLIER;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_NAME;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_PERK_SYSTEM;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_SKILL_SELECTED;
import static com.pawels96.skyrimperkcalculator.Utils.getFragmentTag;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.ORDINATOR;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.VANILLA;


public class MainActivity extends AppCompatActivity
        implements SkillTreeFragment.OnFragmentInteractionListener {

    private Build build;
    private TextView allPerks, reqLevel;
    private LoopingViewPager viewPager;
    private SharedPreferences sp;
    private DatabaseHelper helper;
    private BuildAdapter adapter;
    private BuildViewModel model;
    private InputMethodManager imm;
    private float multiplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        multiplier = sp.getFloat(PREFS_MULTIPLIER, 1f);
        String lastBuild = sp.getString(PREFS_BUILD_SELECTED, DEFAULT_BUILD_NAME);
        String perkSystemString = sp.getString(PREFS_PERK_SYSTEM, "VANILLA");
        PerkSystem perkSystem = null;
        for (PerkSystem p : PerkSystem.values())
            if (p.toString().equals(perkSystemString)) {
                perkSystem = p;
                break;
            }

        helper = new DatabaseHelper(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        model = ViewModelProviders.of(this).get(BuildViewModel.class);
        model.setHelper(helper);
        model.setSystem(perkSystem);

        build = model.getBuild(lastBuild);

        viewPager = findViewById(R.id.viewPager);
        final TabLayout tabLayout = findViewById(R.id.tabs);

        SkillFragmentAdapter skillFragmentAdapter = new SkillFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(skillFragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setCurrentItem(sp.getInt(PREFS_SKILL_SELECTED, 0));
        skillFragmentAdapter.notifyDataSetChanged();
        viewPager.enableLoop(18);
        viewPager.setListener(new LoopingViewPager.MovementListener() {
            @Override
            public void onActionDown() {
                getFragment(viewPager.getCurrentItem()).cancelHold();
            }
        });

        allPerks = findViewById(R.id.allPerks);
        reqLevel = findViewById(R.id.reqLevel);

        updateBuildInfo();

        Button save = findViewById(R.id.skillsButton);
        Button load = findViewById(R.id.loadButton);
        Button options = findViewById(R.id.optionsButton);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.skillsButton:
                       // showSavePopup(new Build(build.getPerkSystem()), false);
                        showSkillsPopup();
                        break;
                    case R.id.loadButton:
                        showBuildList();
                        break;
                    case R.id.optionsButton:
                        showOptionsPopup();
                        break;
                }
            }
        };
        save.setOnClickListener(listener);
        load.setOnClickListener(listener);
        options.setOnClickListener(listener);
    }




    private void showSkillsPopup(){

            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

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

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    viewPager.setCurrentItem(position, false);
                    listDialog.dismiss();
                }
            });

            listDialog.show();



    }

    @Override
    protected void onPause() {
        super.onPause();

        helper.updateBuild(build);

        sp.edit().putInt(PREFS_SKILL_SELECTED, viewPager.getCurrentItem()).apply();
        sp.edit().putString(PREFS_BUILD_SELECTED, build.getName()).apply();
        sp.edit().putString(PREFS_PERK_SYSTEM, build.getPerkSystem().toString()).apply();
    }

    public Build getBuild() {
        return build;
    }

    private SkillTreeFragment getFragment(int id) {
        return (SkillTreeFragment) getSupportFragmentManager().findFragmentByTag(getFragmentTag(R.id.viewPager, id));
    }

    private void updateBuildInfo() {
        String allPerksText = getString(R.string.all_active_perks) + ": " + Integer.toString(build.getSelectedPerksCount());
        allPerks.setText(allPerksText);
        String requiredLevelText = getString(R.string.required_lvl) + ": " + Integer.toString(build.getRequiredLevel(multiplier));
        reqLevel.setText(requiredLevelText);
    }

    private void showSavePopup(final Build buildToSave, final boolean rename) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View customView = inflater.inflate(R.layout.popup_save, null);

        final EditText et = customView.findViewById(R.id.build_name);
        final TextView tv = customView.findViewById(R.id.textView);
        final CheckBox copyCurrent = customView.findViewById(R.id.checkbox);

        if (rename) {
            et.setText(buildToSave.getName());
            et.setSelection(et.getText().length());
            tv.setVisibility(View.GONE);
            copyCurrent.setVisibility(View.GONE);
        }

        dialogBuilder.setTitle(getResources().getString(R.string.msg_name_your_build));
        dialogBuilder.setPositiveButton(getString(R.string.save), null);
        dialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hideKeyboard(customView);
                dialog.dismiss();
            }
        });

        dialogBuilder.setView(customView);
        final AlertDialog dialog = dialogBuilder.create();


        dialog.setOnShowListener((new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface d) {

                Button b = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String name = et.getText().toString().trim();

                        if (name.equals(""))
                            showMessage(R.string.msg_name_empty);

                        else {

                            if (helper.isNameAvailable(name, build.getPerkSystem())) {

                                if (rename) {
                                    String oldName = buildToSave.getName();
                                    helper.renameBuild(buildToSave, name);
                                    buildToSave.setName(name);
                                    if (oldName.equals(adapter.getCurrentBuildName()))
                                        adapter.setCurrentBuildName(name);
                                    showMessage(R.string.msg_name_changed);
                                    adapter.notifyDataSetChanged();

                                } else {
                                    boolean copy = copyCurrent.isChecked();
                                    Build newBuild = copy ? Build.clone(build) : buildToSave;

                                    newBuild.setName(name);
                                    helper.updateBuild(build);
                                    build = newBuild;
                                    boolean success = helper.saveBuild(newBuild);
                                    if (success) {
                                        model.addToMap(newBuild);
                                        refreshFragments();
                                        showMessage(R.string.msg_build_saved);
                                    }
                                    else showMessage(R.string.msg_error);
                                }
                                hideKeyboard(view);
                                dialog.dismiss();

                            } else
                                showMessage(R.string.msg_name_in_use);
                        }
                    }
                });
            }
        }));

        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.show();
    }

    private void hideKeyboard(View v) {
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void showDelete(final Build buildToDelete) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);

        dialogBuilder.setTitle(getString(R.string.delete_build));
        dialogBuilder.setCancelable(true);

        dialogBuilder.setPositiveButton(getString(R.string.delete),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        boolean current = buildToDelete == build;//adapter.getCurrentBuildName().equals(buildToDelete.getName());
                        boolean deleted = helper.deleteBuild(buildToDelete);

                        if (deleted) {
                            model.deleteFromMap(buildToDelete);
                            adapter.delete(buildToDelete);
                            if (current) {
                                build = (Build) adapter.getItem(0);
                                adapter.setCurrentBuildName(build.getName());
                            }
                            adapter.notifyDataSetChanged();
                            refreshFragments();
                            updateBuildInfo();
                            showMessage(R.string.msg_build_deleted);
                        } else showMessage(R.string.msg_error);
                    }
                });

        dialogBuilder.setNegativeButton(
                getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        dialogBuilder.create().show();
    }

    private void showBuildList() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.list_builds, null);

        final ListView lv = customView.findViewById(R.id.listView);

        adapter = new BuildAdapter(this, model.getBuilds(), new BuildAdapter.AdapterCallback() {

            @Override
            public void onRename(Build build) {
                showSavePopup(build, true);
            }

            @Override
            public void onDelete(Build build, boolean canDelete) {
                if (canDelete)
                    showDelete(build);
                else showMessage(R.string.msg_cant_delete);
            }

            @Override
            public void showDescription(Build build) {
                showDescriptionPopup(build);
            }
        });

        dialogBuilder.setView(customView);

        dialogBuilder.setPositiveButton(getString(R.string.new_build), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showSavePopup(new Build(build.getPerkSystem()), false);
            }
        });

        final Dialog listDialog = dialogBuilder.create();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                helper.updateBuild(MainActivity.this.build);
                MainActivity.this.build = (Build) lv.getAdapter().getItem(position);
                refreshFragments();

                listDialog.dismiss();
            }
        });

        lv.setPadding(0, 5, 0, 0);

        listDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                lv.setAdapter(adapter);
                adapter.setCurrentBuildName(build.getName());

                adapter.notifyDataSetChanged();
                lv.setSelection(adapter.getCurrentBuildIndex());

            }
        });

        listDialog.show();
    }

    private void refreshFragments() {

        updateBuildInfo();

        int currentFragmentID = viewPager.getCurrentItem();

        getFragment(currentFragmentID).refresh();

        try {
            getFragment(currentFragmentID - 1).refresh();
            getFragment(currentFragmentID + 1).refresh();
        } catch (NullPointerException e) {
        }
    }

    private void showDescriptionPopup(final Build build) {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View customView = inflater.inflate(R.layout.popup_build_description, null);

        final TextView tv = customView.findViewById(R.id.build_desc);

        if (build.getDescription() != null)
            tv.setText(build.getDescription());

        dialogBuilder.setTitle(build.getName());
        dialogBuilder.setCancelable(true);
        dialogBuilder.setView(customView);

        dialogBuilder.setPositiveButton(getString(R.string.edit),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        final AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        final View customView2 = inflater.inflate(R.layout.popup_edittext, null);

                        final EditText et = customView2.findViewById(R.id.build_desc);

                        dialogBuilder2.setView(customView2);
                        et.setText(build.getDescription());
                        et.setSelection(et.getText().length());

                        dialogBuilder2.setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.build.setDescription(et.getText().toString());
                                helper.updateBuild(MainActivity.this.build);
                                adapter.notifyDataSetChanged();
                                hideKeyboard(customView2);
                                dialog.dismiss();
                                showMessage(R.string.msg_saved);
                            }
                        });

                        dialogBuilder2.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hideKeyboard(customView2);
                                dialog.dismiss();
                            }
                        });

                        AlertDialog dialog1 = dialogBuilder2.create();

                        dialog1.getWindow().setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        dialog1.show();
                    }
                });

        dialogBuilder.create().show();
    }

    private void showOptionsPopup() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.popop_options, null);
        final TextView perksValue = customView.findViewById(R.id.perksValue);

        SeekBar perksSeekbar = customView.findViewById(R.id.perksSeekbar);
        perksSeekbar.setMax(49);

        final RadioButton radioVanilla = customView.findViewById(R.id.radio_vanilla);
        final RadioButton radioOrdinator = customView.findViewById(R.id.radio_ordinator);

        if (build.getPerkSystem() == ORDINATOR)
            radioOrdinator.setChecked(true);
        else if (build.getPerkSystem() == VANILLA)
            radioVanilla.setChecked(true);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                PerkSystem system = VANILLA;

                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.radio_ordinator:
                            system = ORDINATOR;
                            radioVanilla.setChecked(false);
                            break;

                        case R.id.radio_vanilla:
                            system = VANILLA;
                            radioOrdinator.setChecked(false);
                            break;
                    }

                    model.setSystem(system);
                    model.load(system);
                    build = model.getRandom();
                    refreshFragments();
                }
            }
        };

        radioOrdinator.setOnCheckedChangeListener(listener);
        radioVanilla.setOnCheckedChangeListener(listener);

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

        dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.setView(customView);
        dialogBuilder.create().show();
    }

    @Override
    public void onPerkChanged(SkillEnum skill, Perk perk, int state) {

        build.
                getSkill(skill).
                get(perk.getPerk()).
                setState(state);
        updateBuildInfo();
    }

    private void showMessage(int msg) {
        Toast.makeText(MainActivity.this, getString(msg), Toast.LENGTH_SHORT).show();
    }

}
