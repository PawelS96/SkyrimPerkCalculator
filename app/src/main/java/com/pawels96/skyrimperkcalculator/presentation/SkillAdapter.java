package com.pawels96.skyrimperkcalculator.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pawels96.skyrimperkcalculator.R;
import com.pawels96.skyrimperkcalculator.domain.Skill;

import java.util.List;

import static com.pawels96.skyrimperkcalculator.presentation.Utils.getSkillName;

public class SkillAdapter extends BaseAdapter {

    //TODO replace with recycler adapter

    private List<Skill> skills;
    private LayoutInflater inflater;

    private Context c;

    public SkillAdapter(Context c, List<Skill> skills) {

        this.c = c;
        this.skills = skills;

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

        holder.name.setText(getSkillName(skill.getIskill(), c));
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
