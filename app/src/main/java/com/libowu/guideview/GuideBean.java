package com.libowu.guideview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;

public class GuideBean{
    private Rect rect;
    private int img;
    private Bitmap bitmap;
    private Bitmap viewBitmap;

    public GuideBean(int img, Activity act, View view, ActionBar actionBar) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        rect.top = rect.top - ChangeDp.getStatusBarHeight(act) - ChangeDp.getInstance().getActionBarHeight(actionBar);
        rect.bottom = rect.bottom - ChangeDp.getStatusBarHeight(act) - ChangeDp.getInstance().getActionBarHeight(actionBar);
        Log.e("日志", ChangeDp.getStatusBarHeight(act) +","+ ChangeDp.getInstance().getActionBarHeight(actionBar));
        this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.rect = rect;
        this.viewBitmap = ChangeDp.loadBitmapFromView(view);
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
