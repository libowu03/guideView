package com.kit.pagerCard.bean;

import android.graphics.drawable.Drawable;

/**
 * 存放pagerCard的基本属性，比如图片大小，字体大小，红点背景等
 * @author libowu
 * @date 2019/09/27
 */
public class PagerCardAttribute {
    //图片高度
    private int imageHeight;
    //图片宽度
    private int imageWidth;
    //右上角字体大小
    private int redPointTextSize;
    //右上角字体颜色
    private int redPointTextColor;
    //右上角红点宽度
    private int redPointSizeWidth;
    //右上角红点高度
    private int redPointSizeHeight;
    //标题文字大小
    private int titleTextSize;
    //标题文字颜色
    private int titleTextColor;
    //指示器未选中颜色
    private int unSeIndicatorColor;
    //指示器选中颜色
    private int seIndicatorColor;
    //指示器宽度
    private int unSeIndicatorWidth;
    //指示器高度
    private int seIndicatorHeight;
    //图片显示类型，包括圆形，圆角矩形，矩形
    private int imgType;
    //图片圆角弧度，针对圆角矩形有效
    private int imgCorner;
    //是否需要显示指示器
    private boolean needIndicator;
    //显示内容是否可以垂直滑动
    private boolean canScrollVertically;
    //宫格分割线高度
    private int itemDecorationWeight;
    //宫格分割线颜色
    private int itemDecorationColor;
    //每个宫格的左边margin
    private int itemMarginLeft;
    //每个宫格的右边margin
    private int itemMarginRight;
    //每个宫格的上边margin
    private int itemMarginTop;
    //每个宫格的下边margin
    private int itemMarginBottom;
    //每个宫格的margin
    private int itemMargin;
    //每个宫格的padding
    private int itemPadding;
    //每个宫格的左边padding
    private int itemPaddingLeft;
    //每个宫格的上边padding
    private int itemPaddingTop;
    //每个宫格的右边padding
    private int itemPaddingRight;
    //每个宫格的下边padding
    private int itemPaddingBottom;
    //每个宫格的背景色
    private int itemBackgrounColor;
    //每个九宫格的背景图片
    private Drawable itemBackgrounResource;
    //支持无限循环
    private boolean enableInfinite;
    //自动播放时间间隔
    private int playDuration;
    //红点背景色
    private int redBackGroundColor;


    public PagerCardAttribute(int imageHeight, int imageWidth, int redPointTextSize, int redPointTextColor, int redPointSizeWidth, int redPointSizeHeight, int titleTextSize,
                              int titleTextColor, int unSeIndicatorColor, int seIndicatorColor, int unSeIndicatorWidth, int seIndicatorHeight,int imgTyoe,int imgCorner,
                              boolean needIndicator,boolean canScrollVertically,int itemDecorationColor,int itemDecorationWeight,
                              int itemMarginLeft,int itemMarginRight,int itemMarginTop,int itemMarginBottom,int itemMargin,int itemPadding,int itemPaddingLeft,int itemPaddingTop,int itemPaddingRight,int itemPaddingBottom,int itemBackgrounColor,Drawable itemBackgrounResource,
                              boolean enableInfinite,int playDuration,int redBackGroundColor) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.redPointTextSize = redPointTextSize;
        this.redPointTextColor = redPointTextColor;
        this.redPointSizeWidth = redPointSizeWidth;
        this.redPointSizeHeight = redPointSizeHeight;
        this.titleTextSize = titleTextSize;
        this.titleTextColor = titleTextColor;
        this.unSeIndicatorColor = unSeIndicatorColor;
        this.seIndicatorColor = seIndicatorColor;
        this.unSeIndicatorWidth = unSeIndicatorWidth;
        this.seIndicatorHeight = seIndicatorHeight;
        this.imgType = imgTyoe;
        this.imgCorner = imgCorner;
        this.needIndicator = needIndicator;
        this.canScrollVertically = canScrollVertically;
        this.itemDecorationColor = itemDecorationColor;
        this.itemDecorationWeight = itemDecorationWeight;

        this.itemMarginLeft = itemMarginLeft;
        this.itemMarginRight = itemMarginRight;
        this.itemMarginTop = itemMarginTop;
        this.itemMarginBottom = itemMarginBottom;
        this.itemMargin = itemMargin;
        this.itemPadding = itemPadding;
        this.itemPaddingLeft = itemPaddingLeft;
        this.itemPaddingTop = itemPaddingTop;
        this.itemPaddingRight = itemPaddingRight;
        this.itemPaddingBottom = itemPaddingBottom;
        this.itemBackgrounColor = itemBackgrounColor;
        this.itemBackgrounResource = itemBackgrounResource;
        this.enableInfinite = enableInfinite;
        this.playDuration = playDuration;
        this.redBackGroundColor = redBackGroundColor;
    }

    public int getRedBackGroundColor() {
        return redBackGroundColor;
    }

    public void setRedBackGroundColor(int redBackGroundColor) {
        this.redBackGroundColor = redBackGroundColor;
    }

    public int getItemBackgrounColor() {
        return itemBackgrounColor;
    }

    public void setItemBackgrounColor(int itemBackgrounColor) {
        this.itemBackgrounColor = itemBackgrounColor;
    }

    public Drawable getItemBackgrounResource() {
        return itemBackgrounResource;
    }

    public void setItemBackgrounResource(Drawable itemBackgrounResource) {
        this.itemBackgrounResource = itemBackgrounResource;
    }

    public int getItemPadding() {
        return itemPadding;
    }

    public void setItemPadding(int itemPadding) {
        this.itemPadding = itemPadding;
    }

    public int getItemPaddingLeft() {
        return itemPaddingLeft;
    }

    public void setItemPaddingLeft(int itemPaddingLeft) {
        this.itemPaddingLeft = itemPaddingLeft;
    }

    public int getItemPaddingTop() {
        return itemPaddingTop;
    }

    public void setItemPaddingTop(int itemPaddingTop) {
        this.itemPaddingTop = itemPaddingTop;
    }

    public int getItemPaddingRight() {
        return itemPaddingRight;
    }

    public void setItemPaddingRight(int itemPaddingRight) {
        this.itemPaddingRight = itemPaddingRight;
    }

    public int getItemPaddingBottom() {
        return itemPaddingBottom;
    }

    public void setItemPaddingBottom(int itemPaddingBottom) {
        this.itemPaddingBottom = itemPaddingBottom;
    }

    public int getItemMarginLeft() {
        return itemMarginLeft;
    }

    public void setItemMarginLeft(int itemMarginLeft) {
        this.itemMarginLeft = itemMarginLeft;
    }

    public int getItemMarginRight() {
        return itemMarginRight;
    }

    public void setItemMarginRight(int itemMarginRight) {
        this.itemMarginRight = itemMarginRight;
    }

    public int getItemMarginTop() {
        return itemMarginTop;
    }

    public void setItemMarginTop(int itemMarginTop) {
        this.itemMarginTop = itemMarginTop;
    }

    public int getItemMarginBottom() {
        return itemMarginBottom;
    }

    public void setItemMarginBottom(int itemMarginBottom) {
        this.itemMarginBottom = itemMarginBottom;
    }

    public int getItemMargin() {
        return itemMargin;
    }

    public void setItemMargin(int itemMargin) {
        this.itemMargin = itemMargin;
    }

    public int getItemDecorationWeight() {
        return itemDecorationWeight;
    }

    public void setItemDecorationWeight(int itemDecorationWeight) {
        this.itemDecorationWeight = itemDecorationWeight;
    }

    public int getItemDecorationColor() {
        return itemDecorationColor;
    }

    public void setItemDecorationColor(int itemDecorationColor) {
        this.itemDecorationColor = itemDecorationColor;
    }

    public boolean isCanScrollVertically() {
        return canScrollVertically;
    }

    public void setCanScrollVertically(boolean canScrollVertically) {
        this.canScrollVertically = canScrollVertically;
    }

    public boolean isNeedIndicator() {
        return needIndicator;
    }

    public void setNeedIndicator(boolean needIndicator) {
        this.needIndicator = needIndicator;
    }

    public int getImgCorner() {
        return imgCorner;
    }

    public void setImgCorner(int imgCorner) {
        this.imgCorner = imgCorner;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getRedPointTextSize() {
        return redPointTextSize;
    }

    public void setRedPointTextSize(int redPointTextSize) {
        this.redPointTextSize = redPointTextSize;
    }

    public int getRedPointTextColor() {
        return redPointTextColor;
    }



    public void setRedPointTextColor(int redPointTextColor) {
        this.redPointTextColor = redPointTextColor;
    }

    public int getRedPointSizeWidth() {
        return redPointSizeWidth;
    }

    public void setRedPointSizeWidth(int redPointSizeWidth) {
        this.redPointSizeWidth = redPointSizeWidth;
    }

    public int getRedPointSizeHeight() {
        return redPointSizeHeight;
    }

    public void setRedPointSizeHeight(int redPointSizeHeight) {
        this.redPointSizeHeight = redPointSizeHeight;
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public int getUnSeIndicatorColor() {
        return unSeIndicatorColor;
    }

    public void setUnSeIndicatorColor(int unSeIndicatorColor) {
        this.unSeIndicatorColor = unSeIndicatorColor;
    }

    public int getSeIndicatorColor() {
        return seIndicatorColor;
    }

    public void setSeIndicatorColor(int seIndicatorColor) {
        this.seIndicatorColor = seIndicatorColor;
    }

    public int getUnSeIndicatorWidth() {
        return unSeIndicatorWidth;
    }

    public void setUnSeIndicatorWidth(int unSeIndicatorWidth) {
        this.unSeIndicatorWidth = unSeIndicatorWidth;
    }

    public int getSeIndicatorHeight() {
        return seIndicatorHeight;
    }

    public void setSeIndicatorHeight(int seIndicatorHeight) {
        this.seIndicatorHeight = seIndicatorHeight;
    }
}
