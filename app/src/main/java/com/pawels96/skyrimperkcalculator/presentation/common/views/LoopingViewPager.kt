package com.pawels96.skyrimperkcalculator.presentation.common.views

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager

/**
 * ViewPager which allows for moving between the first and last item
 */
class LoopingViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    var onScrollStarted: (() -> Unit)? = null

    fun enableLoop(pages: Int) {
        addOnPageChangeListener(object : OnPageChangeListener {
            var previousState = 0
            var currentState = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) = Unit

            override fun onPageScrollStateChanged(state: Int) {
                if (state == 1) {
                    onScrollStarted?.invoke()
                }

                val currentPage = currentItem
                if (currentPage == pages - 1 || currentPage == 0) {
                    previousState = currentState
                    currentState = state
                    if (previousState == 1 && currentState == 0) {
                        setCurrentItem(if (currentPage == 0) pages - 1 else 0, false)
                    }
                }
            }
        })
    }
}