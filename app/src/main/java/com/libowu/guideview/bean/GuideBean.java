package com.libowu.guideview.bean;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

import com.libowu.guideview.utils.GuideViewUtils;

public class GuideBean{
    private Rect rect;
    private int img;
    private Bitmap bitmap;
    private Bitmap viewBitmap;
    private int marginTop,marginBottom,marginLeft,marginRight;

    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     */
    public GuideBean(int img, Activity act, View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        rect.top = rect.top - GuideViewUtils.getStatusBarHeight(act) - GuideViewUtils.getInstance().getActionBarHeight(act);
        rect.bottom = rect.bottom - GuideViewUtils.getStatusBarHeight(act) - GuideViewUtils.getInstance().getActionBarHeight(act);
        this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.rect = rect;
        this.viewBitmap = GuideViewUtils.loadBitmapFromView(view);
    }

    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     * @param actionBarHeight 标题栏高度，这个构造方法使用与没有actionBar或actionBar是自定义的的界面
     */
    public GuideBean(int img, Activity act, View view, int actionBarHeight) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        rect.top = rect.top - GuideViewUtils.getStatusBarHeight(act) - actionBarHeight;
        rect.bottom = rect.bottom - GuideViewUtils.getStatusBarHeight(act) - actionBarHeight;
        this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.rect = rect;
        this.viewBitmap = GuideViewUtils.loadBitmapFromView(view);
    }

    /**
     * 如果想设置说明图片与说明控件之间的距离，可以使用这个构造方法。默认情况是上边距为0，说明图片位于说明控件的中间位置
     * @param img 控件说明图片
     * @param act 活动
     * @param view 想要被说明的控件
     * @param actionBarHeight 标题栏高度(适合没有actionbar的界面或自定义标题栏的界面)
     * @param marginLeft 左边距
     * @param marginTop 上边距
     * @param marginRight 右边距
     * @param marginBottom 下边距
     */
    public GuideBean(int img,Activity act,View view,int actionBarHeight,int marginLeft,int marginTop,int marginRight,int marginBottom){
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        rect.top = rect.top - GuideViewUtils.getStatusBarHeight(act) - actionBarHeight;
        rect.bottom = rect.bottom - GuideViewUtils.getStatusBarHeight(act) - actionBarHeight;
        this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.rect = rect;
        this.viewBitmap = GuideViewUtils.loadBitmapFromView(view);
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.marginRight = marginTop;
        this.marginBottom = marginBottom;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public Bitmap getViewBitmap() {
        return viewBitmap;
    }

    public void setViewBitmap(Bitmap viewBitmap) {
        this.viewBitmap = viewBitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
