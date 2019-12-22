package com.kit.ActivityUiFrame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<Fragment> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment){
        if (list == null){
            list = new ArrayList<>();
        }
        list.add(fragment);
    }

    public void build(){
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int i) {
        if (list != null){
            list.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }
}
