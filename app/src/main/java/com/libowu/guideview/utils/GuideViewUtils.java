package com.libowu.guideview.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.libowu.guideview.exception.GuideViewException;

public class GuideViewUtils {
    //将px转换成dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        //获取状态栏高度
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 获取 标题栏高度
     * @param activity
     * @return
     */
    public int getActionBarHeight(Activity activity){
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
            return actionBarHeight;
        }else {
            try{
                throw new GuideViewException("获取actionBar高度度失败，可能是您的主题中不包含actionBar");
            }catch (Exception e){
                Log.e("[guideView]errorLog","reason:"+e.getLocalizedMessage());
            }
            return -1;
        }
    }

    /**
     * 获取view的图片
     * @param v
     * @return
     */
    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(screenshot);
        //我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        canvas.translate(-v.getScrollX(), -v.getScrollY());
        // 将 view 画到画布上
        v.draw(canvas);
        return screenshot;
    }

    /**
     * 获取实例对象
     * @return
     */
    public static GuideViewUtils getInstance(){
        return new GuideViewUtils();
    }
}
