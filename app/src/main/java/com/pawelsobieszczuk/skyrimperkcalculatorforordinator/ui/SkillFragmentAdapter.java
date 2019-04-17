package com.pawelsobieszczuk.skyrimperkcalculatorforordinator.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillEnum;

import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.ui.SkillTreeFragment.newInstance;

public class SkillFragmentAdapter

    extends FragmentPagerAdapter {

        public SkillFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return newInstance(SkillEnum.values()[position]);
        }

        @Override
        public int getCount() {
            return SkillEnum.values().length;
        }
}
