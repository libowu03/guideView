package com.kit.pagerCard.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.kit.pagerCard.bean.PagerCardAttribute;
import com.kit.pagerCard.view.PagerCardView;

/**
 * 一个自定义的viewpager，目的是适应recyclerview的高度，防止一个fragment把整个屏幕沾满
 * @author libowu
 * @date 2019/09/27
 */
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

    /**
     * 不进行分页显示
     */
    private boolean isNotClassPager = false;

    /**
     * 获取属性参数
     */
    private PagerCardAttribute attribute;
    private PagerCardView pagerCardView;

    public SelfViewPagerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isNotClassPager){
            if (getChildCount() != 0){
                View child = getChildAt(0);
                RecyclerView list = (RecyclerView) getChildAt(0);
                if (row == -1){
                    row = list.getAdapter().getItemCount()/col;
                    //Log.e("日志","row为-1，row为-1时的行数为："+row);
                }
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                if (list != null && list.getChildCount() != 0){
                    View firstChild = list.getChildAt(0);
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(firstChild.getHeight()*row + attribute.getItemMargin()*(row*2), MeasureSpec.EXACTLY);
                    //PagerCardTempData.pagerCardHeight = heightMeasureSpec;
                }else {
                    //heightMeasureSpec = PagerCardTempData.pagerCardHeight;
                }

                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }else {
                //heightMeasureSpec = MeasureSpec.makeMeasureSpec(PagerCardTempData.pagerCardHeight, MeasureSpec.EXACTLY);
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 设置行数，列数，条目总数，用于计算viewpager高度
     * @param row
     * @param col
     * @param contentListSize
     */
    protected void setRow(int row,int col,int contentListSize,boolean isNotClassPager,PagerCardAttribute attribute,PagerCardView pagerCardView){
        this.row = row;
        this.col = col;
        this.contentListSize = contentListSize;
        //this.isNotClassPager = isNotClassPager;
        this.isNotClassPager = false;
        this.attribute = attribute;
        this.pagerCardView = pagerCardView;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
