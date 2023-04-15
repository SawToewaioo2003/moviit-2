package com.two.mylibrary.viewpager;

import androidx.viewpager.widget.ViewPager;

public interface PageIndicator extends ViewPager.OnPageChangeListener {
    void setViewPager(ViewPager view);
    void setViewPager(ViewPager viewPager,int intitialPosition);
    void setCurrentItem(int item);
    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);
    void notifyDataSetChanged();
}
