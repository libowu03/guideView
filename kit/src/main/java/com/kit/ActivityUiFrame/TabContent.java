package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;

public class TabContent {
    private Fragment fragment;
    private String tabName;
    private Object tabIcon;
    private Object tabSelectIcon;

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

    public Object getTabSelectIcon() {
        return tabSelectIcon;
    }

    public TabContent setTabSelectIcon(Object tabSelectIcon) {
        this.tabSelectIcon = tabSelectIcon;
        return this;
    }
}
