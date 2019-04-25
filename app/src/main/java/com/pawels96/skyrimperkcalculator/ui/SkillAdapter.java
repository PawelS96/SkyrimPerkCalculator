package com.pawels96.skyrimperkcalculator.ui;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import com.pawels96.skyrimperkcalculator.models.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.PREFS_MULTIPLIER;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_NAME;
import static com.pawels96.skyrimperkcalculator.Utils.getSkillName;

public class SkillAdapter extends BaseAdapter {

    private List<Skill> skills;
    private LayoutInflater inflater;

    private Context c;

    public SkillAdapter(Context c, List<Skill> skills) {

        this.c = c;
        this.skills = skills;

        for (Skill s : skills)
            Log.d(getSkillName(s.getSkillEnum(), c), Integer.toString(s.getSelectedPerksCount()))
;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public Object getItem(int position) {
        return skills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final Holder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_skill, parent, false);

            holder = new Holder();
            holder.name = view.findViewById(R.id.skill_name);
            holder.perks = view.findViewById(R.id.active_perks);

            view.setTag(holder);
        }
        else holder = (Holder) view.getTag();

        final Skill skill = skills.get(position);

        holder.name.setText(getSkillName(skill.getSkillEnum(), c));
        int perks = skill.getSelectedPerksCount();
        String perksString = perks > 0 ? Integer.toString(perks) : "";
        holder.perks.setText(perksString);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    private static class Holder {
        public TextView name;
        public TextView perks;
    }

}
