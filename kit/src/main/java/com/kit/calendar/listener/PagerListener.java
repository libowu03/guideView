package com.kit.calendar.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class PagerListener extends RecyclerView.OnScrollListener {
    private OnPageChangeListener onPageChangeListener;
    private PagerSnapHelper pagerSnapHelper;
    //防止同一Position多次触发
    private int oldPosition = -1;


    public PagerListener(PagerSnapHelper pagerSnapHelper,OnPageChangeListener onPageChangeListener){
        this.pagerSnapHelper = pagerSnapHelper;
        this.onPageChangeListener = onPageChangeListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (onPageChangeListener != null) {
            onPageChangeListener.onScrolled(recyclerView, dx, dy);
        }

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int position = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //获取当前选中的itemView
        View view = pagerSnapHelper.findSnapView(layoutManager);
        if (view != null) {
            //获取itemView的position
            position = layoutManager.getPosition(view);
        }
        if (onPageChangeListener != null) {
            onPageChangeListener.onScrollStateChanged(recyclerView, newState);
            //newState == RecyclerView.SCROLL_STATE_IDLE 当滚动停止时触发防止在滚动过程中不停触发
            if (/*newState == RecyclerView.SCROLL_STATE_IDLE && */oldPosition != position) {
                Log.e("日志","触发");
                oldPosition = position;
                onPageChangeListener.onPageSelected(position);
            }
        }

    }

    public interface OnPageChangeListener {
        void onScrollStateChanged(RecyclerView recyclerView, int newState);

        void onScrolled(RecyclerView recyclerView, int dx, int dy);

        void onPageSelected(int position);
    }
}
