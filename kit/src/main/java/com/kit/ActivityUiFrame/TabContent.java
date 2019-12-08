package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;

public class TabContent {
    private Fragment fragment;
    private String tabName;
    private Object tabIcon;
    private Object tabSelectIcon;
    //默认图片，这个主要针对需要加载网络图片最为icon的tab
    private int defaultTabIcon;

    public TabContent(Fragment fragment, String tabName) {
        this.fragment = fragment;
        this.tabName = tabName;
    }

    public Fragment getFragment() {
        return fragment;
    }


    public String getTabName() {
        return tabName;
    }


    public Object getTabIcon() {
        return tabIcon;
    }

    public TabContent setTabIcon(Object tabIcon) {
        this.tabIcon = tabIcon;
        return this;
    }

    public int getDefaultTabIcon() {
        return defaultTabIcon;
    }

    public TabContent setDefaultTabIcon(int defaultTabIcon) {
        this.defaultTabIcon = defaultTabIcon;
        return this;
    }
}
