package com.pawels96.skyrimperkcalculator.presentation.common;

import android.content.Context;

import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.IPerkSystem;
import com.pawels96.skyrimperkcalculator.domain.ISkill;

public class Utils {

    public static final String DEFAULT_BUILD_NAME = "New build";

    /**
     * Methods for getting perk names and descriptions from strings.xml.
     * <p>
     * Naming convention for XML resources:
     * resType_perkSystem_skillName_perkName
     * <p>
     * where:
     * resType is either p for perk names or d for perk descriptions
     * and the rest is the same as the names of perks in enums but in lowercase
     */

    public static String getPerkName(Context c, IPerk perk) {
        return getPerkInfo(c, perk, "p_");
    }

    public static String getPerkDescription(Context c, IPerk perk) {
        return getPerkInfo(c, perk, "d_");
    }

    private static String getPerkInfo(Context c, IPerk perk, String resPrefix) {

        String info = resPrefix + String.valueOf(perk).toLowerCase();
        return getString(info, info, c);
    }

    private static String getString(String resName, String defaultValue, Context c) {
        int id;

        try {
            id = c.getResources().getIdentifier(resName, "string", c.getPackageName());
            return c.getResources().getString(id);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static String getSkillName(ISkill s, Context c) {
        String name = String.valueOf(s).toLowerCase();
        return getString(name, name, c);
    }

    public static String getPerkSystemName(IPerkSystem system, Context c) {
        String asString = String.valueOf(system).toLowerCase();
        String resId = "s_" + asString;
        return getString(resId, asString, c);
    }

    public static String getFragmentTag(int viewPagerID, int position) {
        return "android:switcher:" + viewPagerID + ":" + position;
    }

}
