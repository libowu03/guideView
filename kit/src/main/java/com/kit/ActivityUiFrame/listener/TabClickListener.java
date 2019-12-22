package com.kit.ActivityUiFrame.listener;

import android.view.View;

import com.kit.ActivityUiFrame.bean.TabContent;

import java.util.List;

public interface TabClickListener {
    void onPageScrolled(int i, float v, int i1);
    void onPageSelected(int i, TabContent tabContent, View currentView, List<TabContent> tabContents);
    void onPageScrollStateChanged(int i);
}
