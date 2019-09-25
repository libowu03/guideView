package com.kit.pagerCard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class SelfViewPagerView extends ViewPager {
    /**
     * 条目列数
     */
    private int row;

    /**
     * 行数
     */
    private int col;

    /**
     * 条目的个数
     */
    private int contentListSize;

    public SelfViewPagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        //下面遍历所有child的高度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) //采用最大的view的高度。
                height = h;
        }

        if (getChildCount() != 0){
            View child = getChildAt(0);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            int w = child.getMeasuredWidth();
            if (contentListSize >= col*row){
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
            }else if (contentListSize < col*row ){
                //如果条目的数量没法填充完一个viewpager时，计算当前已经填充了几行数据
                //通过已填充的数据和当前recyclerview的高度计算出每行的高度，h就是recyclerview的总高度
                int yushu = contentListSize/col;
                if (contentListSize/(col*1.0) >0 ){
                    yushu++;
                }
                //计算每行的高度
                int singleHeight = h/yushu;
                //设置viewpager的高度
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(singleHeight*row, MeasureSpec.EXACTLY);
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }else {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    /**
     * 设置行数，列数，条目总数，用于计算viewpager高度
     * @param row
     * @param col
     * @param contentListSize
     */
    public void setRow(int row,int col,int contentListSize){
        this.row = row;
        this.col = col;
        this.contentListSize = contentListSize;
    }
}
