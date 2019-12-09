package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TabViewInfo {
    private ImageView tabIcon;
    private TextView tabName;
    private TabContent tabContent;

    public TabViewInfo(ImageView tabIcon, TextView tabName, TabContent tabContent) {
        this.tabIcon = tabIcon;
        this.tabName = tabName;
        this.tabContent = tabContent;
    }

    public ImageView getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(ImageView tabIcon) {
        this.tabIcon = tabIcon;
    }

    public TextView getTabName() {
        return tabName;
    }

    public void setTabName(TextView tabName) {
        this.tabName = tabName;
    }

    public TabContent getTabContent() {
        return tabContent;
    }

    public void setTabContent(TabContent tabContent) {
        this.tabContent = tabContent;
    }

}
