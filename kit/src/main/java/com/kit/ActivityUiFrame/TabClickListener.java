package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.List;

public interface TabClickListener {
    void onTabClickListener(TabContent tabContent, int position, View currentView, List<TabContent> tabContents);
}
