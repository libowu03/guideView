package com.kit.guide.bean;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

/**
 * @author libowu
 * @date 2019/07/30
 * 保存引导的基本属性，比如说明图片，控件图片，控件位置等
 */
public class GuideBean {
    private Rect rect;
    private int img;
    private Bitmap bitmap;
    private Bitmap viewBitmap;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private boolean isSimpleShape;
    private int position;
    private byte shape;
    private boolean isSimpleRect = false;
    private String text;
    private int textAlign;
    private View targetView;

    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     */
    public GuideBean(final int img, final Activity act, final View view) {
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        this.targetView = view;
    }


    public boolean isSimpleRect() {
        return isSimpleRect;
    }

    public GuideBean setSimpleRect(boolean simpleRect) {
        isSimpleRect = simpleRect;
        return this;
    }

    public View getTargetView() {
        return targetView;
    }

    public int getTextAlign() {
        return textAlign;
    }

    public GuideBean setTextAlign(int textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    public String getText() {
        return text;
    }

    public GuideBean setText(String text) {
        this.text = text;
        return this;
    }

    public byte getShape() {
        return shape;
    }

    public GuideBean setShape(byte shape) {
        this.shape = shape;
        this.isSimpleShape = true;
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public GuideBean setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public GuideBean setPosition(int position) {
        this.position = position;
        return this;
    }

    public boolean isSimpleShape() {
        return isSimpleShape;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public GuideBean setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public GuideBean setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public GuideBean setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public Bitmap getViewBitmap() {
        return viewBitmap;
    }

    public GuideBean setViewBitmap(Bitmap viewBitmap) {
        this.viewBitmap = viewBitmap;
        return this;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public GuideBean setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public Rect getRect() {
        return rect;
    }

    public GuideBean setRect(Rect rect) {
        this.rect = rect;
        return this;
    }

    public int getImg() {
        return img;
    }

}
