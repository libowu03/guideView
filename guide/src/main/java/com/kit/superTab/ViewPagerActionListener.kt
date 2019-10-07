package com.kit.superTab

import android.support.v4.view.ViewPager

open interface ViewPagerActionListener:ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {

    }

}