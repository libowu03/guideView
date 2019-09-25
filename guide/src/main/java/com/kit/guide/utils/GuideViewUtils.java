package com.kit.guide.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.WindowManager;

/**
 * @author libowu
 * @date 2019/07/30
 * guideview的帮助类
 */
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
    public static int getStatusBarHeight(Activity context){
        //判断存不存在状态栏
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if ((params.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN){
            return 0;
        }else {
            //获取状态栏高度
            int height = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                height = context.getResources().getDimensionPixelSize(resourceId);
            }
            return height;
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
        try{
            screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(screenshot);
            //我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
            canvas.translate(-v.getScrollX(), -v.getScrollY());
            // 将 view 画到画布上
            v.draw(canvas);
        }catch (Exception e){
            return null;
        }
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
