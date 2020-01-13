package com.kit.ActivityUiFrame.view;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MainUiViewPager extends ViewPager {
    private boolean canScorll;

    public MainUiViewPager(@NonNull Context context) {
        super(context);
    }

    public MainUiViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canScorll){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (canScorll){
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }
    }

    public boolean isCanScorll() {
        return canScorll;
    }

    public void setCanScorll(boolean canScorll) {
        this.canScorll = canScorll;
    }
}
