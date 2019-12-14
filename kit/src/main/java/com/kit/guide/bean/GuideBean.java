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
    private int padding;

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
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.targetView = view;
    }

    /**
     * @param img 要展示的说明图片
     * @param simpleRect 要绘制的区域
     * @param act 活动界面
     */
    public GuideBean(int img, final Activity act,final Rect simpleRect) {
        if (act == null || act.isFinishing()){
            return;
        }
        this.rect = simpleRect;
        this.isSimpleRect = true;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
    }


    public boolean isSimpleRect() {
        return isSimpleRect;
    }

    public int getPadding() {
        return padding;
    }


    /**
     * 设置高亮区的padding，只针对高亮区为几何图形时有效
     * @param padding
     * @return
     */
    public GuideBean setPadding(int padding) {
        this.padding = padding;
        return this;
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

    public GuideBean setRect(Rect rect){
        this.rect = rect;
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


    /**
     * 设置集合图像的形状，可选项为：
     * 矩形{@link com.kit.guide.view.GuideView.Config.RECT}
     * 原型{@link com.kit.guide.view.GuideView.Config.CIRCLE}
     * 椭圆形{@link com.kit.guide.view.GuideView.Config.OVAL}
     * 圆角矩形{@link com.kit.guide.view.GuideView.Config.ROUNDED_RECT}
     * @param shape
     * @return
     */
    public GuideBean setShape(byte shape) {
        this.shape = shape;
        this.isSimpleShape = true;
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    /**
     * 设置右边距
     * @param marginRight
     * @return
     */
    public GuideBean setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }


    public int getPosition() {
        return position;
    }

    /**
     * 设置说明图片位于高亮区的那个方位，可选项为：
     * {@link com.kit.guide.view.GuideView.Config.LEFT},{@link com.kit.guide.view.GuideView.Config.RIGHT}
     * {@link com.kit.guide.view.GuideView.Config.TOP},{@link com.kit.guide.view.GuideView.Config.BOTTOM}
     * {@link com.kit.guide.view.GuideView.Config.LEFT_BOTTOM},{@link com.kit.guide.view.GuideView.Config.LEFT_TOP}
     * {@link com.kit.guide.view.GuideView.Config.RIGHT_TOP},{@link com.kit.guide.view.GuideView.Config.RIGHT_BOTTOM}
     * @param position
     * @return
     */
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

    /**
     * 设置说明图片的顶边距
     * @param marginTop
     * @return
     */
    public GuideBean setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    /**
     * 设置说明图片的底边距
     * @param marginBottom
     * @return
     */
    public GuideBean setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    /**
     * 设置说明图片的左边距
     * @param marginLeft
     * @return
     */
    public GuideBean setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public Bitmap getViewBitmap() {
        return viewBitmap;
    }

    /**
     * 调用者无需设置此属性，此属性在guideview的show方法中统一调用
     * 即使设置了，后面调用show方法时也会被覆盖掉。
     * @param viewBitmap
     * @return
     */
    public GuideBean setViewBitmap(Bitmap viewBitmap) {
        this.viewBitmap = viewBitmap;
        return this;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public Rect getRect() {
        return rect;
    }


}
