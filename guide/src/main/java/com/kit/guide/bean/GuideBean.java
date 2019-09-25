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

    /**
     * 根据矩阵绘制高亮区
     * @param img 说明图片
     * @param rect 矩阵
     * @param text 自定义说明文字
     */
    public GuideBean(int img, Rect rect, Activity act, String text, int position, int textAlign){
        this.img = img;
        this.rect = rect;
        this.text = text;
        this.textAlign = textAlign;
        this.position = position;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        this.isSimpleRect = true;
    }

    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     * @param position 控件说明图片相对于控件的位置
     */
    public GuideBean(final int img, final Activity act, final View view , final int position) {
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        GuideBean.this.position = position;
        this.targetView = view;
    }

    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     *
     */
    public GuideBean(final int img, final Activity act, final View view, final boolean isSimpleShape, final byte shape) {
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        GuideBean.this.isSimpleShape = isSimpleShape;
        GuideBean.this.shape = shape;
        this.targetView = view;
    }


    /**
     *
     * @param img 要展示的说明图片
     * @param act 活动界面
     * @param view 先要出现高亮区的控件
     * @param isSimpleShape 是否是基本几何图形的高亮区
     * @param position 控件说明图片相对于控件的位置
     */
    public GuideBean(final int img, final Activity act, final View view, final boolean isSimpleShape, final byte shape, final int position) {
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        GuideBean.this.isSimpleShape = isSimpleShape;
        GuideBean.this.position = position;
        GuideBean.this.shape = shape;
        this.targetView = view;

    }

    /**
     * 如果想设置说明图片与说明控件之间的距离，可以使用这个构造方法。默认情况是上边距为0，说明图片位于说明控件的中间位置
     * @param img 控件说明图片
     * @param act 活动
     * @param view 想要被说明的控件
     * @param isSimpleShape 是否是基本几何图形的高亮区
     * @param position 控件说明图片相对于控件的位置
     * @param marginLeft 左边距
     * @param marginTop 上边距
     * @param marginBottom 下边距
     */
    public GuideBean(final int img, final Activity act, final View view, final boolean isSimpleShape, final byte shape, final int position, final int marginLeft, final int marginRight, final int marginTop, final int marginBottom){
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        GuideBean.this.marginLeft = marginLeft;
        GuideBean.this.marginTop = marginTop;
        GuideBean.this.marginBottom = marginBottom;
        GuideBean.this.isSimpleShape = isSimpleShape;
        GuideBean.this.position = position;
        GuideBean.this.marginRight = marginRight;
        GuideBean.this.shape = shape;
        this.targetView = view;
    }

    /**
     * 如果想设置说明图片与说明控件之间的距离，可以使用这个构造方法。默认情况是上边距为0，说明图片位于说明控件的中间位置
     * @param img 控件说明图片
     * @param act 活动
     * @param view 想要被说明的控件
     * @param position 控件说明图片相对于控件的位置
     * @param marginLeft 左边距
     * @param marginTop 上边距
     * @param marginBottom 下边距
     */
    public GuideBean(final int img, final Activity act, final View view, final int position, final int marginLeft, final int marginRight, final int marginTop, final int marginBottom){
        if (act == null || act.isFinishing()){
            return;
        }
        Rect rect = new Rect();
        GuideBean.this.img = img;
        bitmap = BitmapFactory.decodeResource(act.getResources(),img);
        GuideBean.this.rect = rect;
        GuideBean.this.marginLeft = marginLeft;
        GuideBean.this.marginTop = marginTop;
        GuideBean.this.marginBottom = marginBottom;
        GuideBean.this.position = position;
        GuideBean.this.marginRight = marginRight;
        this.targetView = view;
    }


    public boolean isSimpleRect() {
        return isSimpleRect;
    }

    public void setSimpleRect(boolean simpleRect) {
        isSimpleRect = simpleRect;
    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public int getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(int textAlign) {
        this.textAlign = textAlign;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getShape() {
        return shape;
    }

    public void setShape(byte shape) {
        this.shape = shape;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSimpleShape() {
        return isSimpleShape;
    }

    public void setSimpleShape(boolean simpleShape) {
        isSimpleShape = simpleShape;
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
