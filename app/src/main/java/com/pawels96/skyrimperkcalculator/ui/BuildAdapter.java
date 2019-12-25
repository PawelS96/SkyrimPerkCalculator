package com.pawels96.skyrimperkcalculator.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.pawels96.skyrimperkcalculator.Utils.PREFS_MULTIPLIER;
import static com.pawels96.skyrimperkcalculator.Utils.PREFS_NAME;

public class BuildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Build> items;
    private Context context;

    public BuildAdapter(List<Build> items, Context context) {
        this.items = items;
        this.context = context;

        stealth = context.getResources().getColor(R.color.skillStealthBright);
        combat = context.getResources().getColor(R.color.skillCombatBright);
        magic = context.getResources().getColor(R.color.skillMagicBright);
        multiplier = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getFloat(PREFS_MULTIPLIER, 1f);
    }

    public Build getItem(int index){
        return items.get(index);
    }

    private int stealth, combat, magic;
    private float multiplier;
    private BuildAdapterCallback callback;

    private String currentBuildName;

    public void setCurrentBuildName(String currentBuildName) {
        this.currentBuildName = currentBuildName;
    }

    public String getCurrentBuildName() {
        return currentBuildName;
    }

    public interface BuildAdapterCallback {
        void onRename(Build build);

        void onDelete(Build build, boolean canDelete);

        void showDescription(Build build);

        void onClick(Build build);
    }

    public void setCallback(BuildAdapterCallback callback) {
        this.callback = callback;
    }

    public void delete(Build build) {
        notifyItemRemoved(items.indexOf(build));
        items.remove(build);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.list_item_build;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final Build build = items.get(i);
        final Holder holder = (Holder) viewHolder;

        holder.root.setOnClickListener(v -> callback.onClick(build));

        if (build.getName().equals(currentBuildName)) {
            SpannableString str0 = new SpannableString(build.getName());
            str0.setSpan(new ForegroundColorSpan(magic), 0, str0.length(), 0);
            holder.name.setText(str0);
        } else holder.name.setText(build.getName());

        String lvl = Integer.toString(build.getRequiredLevel(multiplier));

        int magicPerks = build.getPerkDistribution().get(SkillType.MAGIC);
        int stealthPerks = build.getPerkDistribution().get(SkillType.STEALTH);
        int combatPerks = build.getPerkDistribution().get(SkillType.COMBAT);

        SpannableStringBuilder builder = new SpannableStringBuilder(context.getString(R.string.perks) + ": ");

        SpannableString str1 = new SpannableString(combatPerks + " ");
        str1.setSpan(new ForegroundColorSpan(combat), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2 = new SpannableString(magicPerks + " ");
        str2.setSpan(new ForegroundColorSpan(magic), 0, str2.length(), 0);
        builder.append(str2);

        SpannableString str3 = new SpannableString(Integer.toString(stealthPerks));
        str3.setSpan(new ForegroundColorSpan(stealth), 0, str3.length(), 0);
        builder.append(str3);

        holder.perks.setText(builder, TextView.BufferType.SPANNABLE);
        String levelText = context.getString(R.string.level) + ": " + lvl;
        holder.level.setText(levelText);

        String description = build.getDescription();

        if (description == null || description.isEmpty())
            holder.description.setVisibility(View.GONE);
        else {
            holder.description.setVisibility(View.VISIBLE);

            holder.description.setText(description);
        }

        holder.button.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(context, holder.button);

            popup.getMenuInflater().inflate(R.menu.menu_list_item, popup.getMenu());

            if (items.size() < 2)
                popup.getMenu().findItem(R.id.delete).setVisible(false);

            popup.setOnMenuItemClickListener(item -> {

                int menuId = item.getItemId();

                switch (menuId) {
                    case R.id.rename:
                        callback.onRename(build);
                        break;

                    case R.id.delete:
                        callback.onDelete(build, items.size() != 1);
                        break;

                    case R.id.description:
                        callback.showDescription(build);
                        break;
                }
                return true;
            });
            popup.show();
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class Holder extends RecyclerView.ViewHolder {

        public Holder(View view) {
            super(view);

            root = view;
            name = view.findViewById(R.id.build_name);
            button = view.findViewById(R.id.context_menu);
            level = view.findViewById(R.id.level);
            perks = view.findViewById(R.id.build_info);
            description = view.findViewById(R.id.description);
        }

        View root;
        Button button;
        TextView name;
        TextView level;
        TextView perks;
        TextView description;
    }
}