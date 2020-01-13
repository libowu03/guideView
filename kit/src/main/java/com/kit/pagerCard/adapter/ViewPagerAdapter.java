package com.kit.pagerCard.adapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * viewpager的适配器
 * @author libowu
 * @date 2019/09/27
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<RecyclerView> list;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    public ViewPagerAdapter(List<RecyclerView> list){
        this.list = list;
    }

    public void setList(List<RecyclerView> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
