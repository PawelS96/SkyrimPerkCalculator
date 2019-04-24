package com.pawels96.skyrimperkcalculator;

import android.content.Context;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.models.Build;

import java.util.HashMap;
import java.util.List;

public class Utils {

    public static final String PREFS_NAME = "prefs";
    public static final String PREFS_SKILL_SELECTED = "skill_selected";
    public static final String PREFS_MULTIPLIER = "perk_multiplier";
    public static final String PREFS_BUILD_SELECTED = "build_selected";
    public static final String DEFAULT_BUILD_NAME = "New build";
    public static final String PREFS_PERK_SYSTEM = "system";

    public static String getPerkName(Context c, IPerk perk){
        return getPerkInfo(c, perk, "p_");
    }

    public static String getPerkDescription(Context c, IPerk perk){
        return getPerkInfo(c, perk, "d_");
    }

    private static String getPerkInfo(Context c, IPerk perk, String resPrefix){

        String info = resPrefix + String.valueOf(perk).toLowerCase();
        int id;

        try {
            id = c.getResources().getIdentifier(info, "string", c.getPackageName());
            return c.getResources().getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return info;
        }
    }

    public static String getSkillName(SkillEnum s, Context c){

        String name = String.valueOf(s).toLowerCase();
        int id;

        try {
            id = c.getResources().getIdentifier(name, "string", c.getPackageName());
            return c.getResources().getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return name;
        }

    }

    public static String getFragmentTag(int viewPagerID, int position) {
        return "android:switcher:" + viewPagerID + ":" + position;
    }

    public static HashMap<String, Build> listToMap(List<Build> list) {

        HashMap<String, Build> map = new HashMap<>();

        for (Build b : list)
            map.put(b.getName(), b);

        return map;
    }

}
