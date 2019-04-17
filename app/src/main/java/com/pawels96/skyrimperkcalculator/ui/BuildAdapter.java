package com.pawels96.skyrimperkcalculator.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.enums.SkillType;
import com.pawels96.skyrimperkcalculator.models.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.PREFS_MULTIPLIER;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_NAME;

public class BuildAdapter extends BaseAdapter {

    private List<Build> builds;
    private LayoutInflater songInflater;

    private int stealth, combat, magic;
    private float multiplier;
    private AdapterCallback callback;
    private Context c;

    private String currentBuildName;

    public void setCurrentBuildName(String currentBuildName) {
        this.currentBuildName = currentBuildName;
    }

    public String getCurrentBuildName() {
        return currentBuildName;
    }

    public interface AdapterCallback {
        void onRename(Build build);
        void onDelete(Build build, boolean canDelete);
        void showDescription(Build build);
    }

    public void delete(Build build){
        builds.remove(build);
    }

    public void setBuilds(HashMap<String, Build> builds){
        this.builds = new ArrayList<>();
        this.builds.addAll(builds.values());
    }

    public BuildAdapter(Context c, HashMap<String, Build> builds, AdapterCallback callback) {
        setBuilds(builds);
        this.callback = callback;
        this.c = c;

        stealth = c.getResources().getColor(R.color.skillStealthBright);
        combat = c.getResources().getColor(R.color.skillCombatBright);
        magic = c.getResources().getColor(R.color.skillMagicBright);

        multiplier = c.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getFloat(PREFS_MULTIPLIER, 1f);

        songInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return builds.size();
    }

    @Override
    public Object getItem(int position) {
        return builds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final Holder holder;

        if (view == null) {
            view = songInflater.inflate(R.layout.list_item_build, parent, false);

            holder = new Holder();
            holder.name = view.findViewById(R.id.build_name);
            holder.button = view.findViewById(R.id.context_menu);
            holder.level = view.findViewById(R.id.level);
            holder.perks = view.findViewById(R.id.build_info);

            view.setTag(holder);
        }
        else holder = (Holder) view.getTag();

        final Build build = builds.get(position);


        if (build.getName().equals(currentBuildName)) {
            SpannableString str0 = new SpannableString(build.getName());
            str0.setSpan(new ForegroundColorSpan(magic), 0, str0.length(), 0);
            holder.name.setText(str0);
        }
        else holder.name.setText(build.getName());

        String lvl = Integer.toString(build.getRequiredLevel(multiplier));

        int magicPerks = build.getPerkDistribution().get(SkillType.MAGIC);
        int stealthPerks = build.getPerkDistribution().get(SkillType.STEALTH);
        int combatPerks = build.getPerkDistribution().get(SkillType.COMBAT);

        SpannableStringBuilder builder = new SpannableStringBuilder(c.getString(R.string.perks) + ": ");

        SpannableString str1 = new SpannableString(Integer.toString(combatPerks) + " ");
        str1.setSpan(new ForegroundColorSpan(combat), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2 = new SpannableString(Integer.toString(magicPerks) + " ");
        str2.setSpan(new ForegroundColorSpan(magic), 0, str2.length(), 0);
        builder.append(str2);

        SpannableString str3 = new SpannableString(Integer.toString(stealthPerks));
        str3.setSpan(new ForegroundColorSpan(stealth), 0, str3.length(), 0);
        builder.append(str3);

        holder.perks.setText(builder, TextView.BufferType.SPANNABLE);
        String levelText = c.getString(R.string.level) + ": " + lvl;
        holder.level.setText(levelText);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(c, holder.button);

                popup.getMenuInflater().inflate(R.menu.menu_list_item, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int menuId = item.getItemId();

                        switch (menuId) {
                            case R.id.rename:
                                callback.onRename(build);
                                break;

                            case R.id.delete:
                                callback.onDelete(build, builds.size() != 1);
                                break;

                            case R.id.description:
                                callback.showDescription(build);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    private static class Holder {
        public Button button;
        public TextView name;
        public TextView level;
        public TextView perks;
    }


}
