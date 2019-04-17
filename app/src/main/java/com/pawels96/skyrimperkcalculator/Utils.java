package com.pawelsobieszczuk.skyrimperkcalculatorforordinator;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.Build;

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

    public static String getFragmentTag(int viewPagerID, int position) {
        return "android:switcher:" + viewPagerID + ":" + position;
    }

    public static HashMap<String, Build> listToMap(List<Build> list) {

        HashMap<String, Build> map = new HashMap<>();

        for (Build b : list)
            map.put(b.getName(), b);

        return map;
    }

    public static void enableViewPagerLoop(final ViewPager v, final int pages){

        v.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int previousState, currentState;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                int currentPage = v.getCurrentItem();
                if (currentPage == pages - 1 || currentPage == 0) {
                    previousState = currentState;
                    currentState = state;
                    if (previousState == 1 && currentState == 0) {
                        v.setCurrentItem(currentPage == 0 ? pages - 1 : 0);
                    }
                }
            }
        });


    }
}
