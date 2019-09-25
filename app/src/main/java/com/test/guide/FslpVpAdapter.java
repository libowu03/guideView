package com.test.guide;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FslpVpAdapter extends FragmentPagerAdapter {
    private String[] title;
    private List<Fragment> fragmentList;

    public FslpVpAdapter(FragmentManager fm, String[] title, List<Fragment> fragmentList) {
        super(fm);
        this.title = title;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

  /*  @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        BaZhaiNewOrderHelper baZhaiNewOrderHelper = new BaZhaiNewOrderHelper(container.getContext());
        if (!baZhaiNewOrderHelper.getPayJiajv()){
            super.destroyItem(container, position, object);
        }
    }*/
}
