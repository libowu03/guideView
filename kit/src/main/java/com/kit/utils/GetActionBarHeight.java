package com.kit.utils;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class GetActionBarHeight {

    public static int getStatusBarHeight(){
        return Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
       try{
           if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
               ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
               p.setMargins(l, t, r, b);
               v.requestLayout();
           }
       }catch (Exception e){
           Log.e("错误日志","位置：GetActionBarHeight.java");
       }
    }

    //无需设置顶部距离，直接在内部设置好
    public static void setMarginsNoneTop (View v) {
        try{
            if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                p.setMargins(0, getStatusBarHeight(), 0, 0);
                v.requestLayout();
            }
        }catch (Exception e){
            Log.e("错误日志","位置：GetActionBarHeight.java");
        }
    }
}
