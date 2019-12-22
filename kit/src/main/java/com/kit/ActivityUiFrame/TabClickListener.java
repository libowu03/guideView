package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

public interface TabClickListener {
    void onPageScrolled(int i, float v, int i1);
    void onPageSelected(int i,TabContent tabContent,View currentView, List<TabContent> tabContents);
    void onPageScrollStateChanged(int i);
}
