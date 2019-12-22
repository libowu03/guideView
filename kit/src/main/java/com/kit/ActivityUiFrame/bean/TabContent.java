package com.kit.ActivityUiFrame.bean;

import android.support.v4.app.Fragment;
import android.view.View;

public class TabContent {
    private Fragment fragment;
    private String tabName;
    private Object tabIcon;
    private Object tabSelectIcon;
    private View customView;

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

    public TabContent setCustomView(View customView){
        this.customView = customView;
        return this;
    }

    public View getCustomView(){
        return customView;
    }
}
