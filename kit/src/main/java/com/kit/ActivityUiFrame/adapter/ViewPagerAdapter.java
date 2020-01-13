package com.kit.ActivityUiFrame.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
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
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
