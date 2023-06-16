package com.pawels96.skyrimperkcalculator.presentation.common;

public class Utils {

    public static final String DEFAULT_BUILD_NAME = "New build";

    public static String getFragmentTag(int viewPagerID, int position) {
        return "android:switcher:" + viewPagerID + ":" + position;
    }

}
