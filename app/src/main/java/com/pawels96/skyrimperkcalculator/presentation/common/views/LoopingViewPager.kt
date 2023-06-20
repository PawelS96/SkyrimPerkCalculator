package com.pawels96.skyrimperkcalculator.presentation.common.views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;

/**
 * ViewPager which allows for moving between the first and last item
 */

public class LoopingViewPager extends ViewPager {
    public LoopingViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public interface MovementListener {
        void onActionDown();
    }

    private MovementListener listener;

    public void setListener(MovementListener listener) {
        this.listener = listener;
    }

    public void enableLoop(final int pages) {

        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int previousState, currentState;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) { }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == 1)
                    listener.onActionDown();

                int currentPage = getCurrentItem();
                if (currentPage == pages - 1 || currentPage == 0) {
                    previousState = currentState;
                    currentState = state;
                    if (previousState == 1 && currentState == 0) {
                        setCurrentItem(currentPage == 0 ? pages - 1 : 0, false);
                    }
                }
            }
        });
    }
}
