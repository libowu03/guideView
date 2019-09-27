package com.kit.pagerCard;

/**
 * 存放pagerCard的基本属性，比如图片大小，字体大小，红点背景等
 * @author libowu
 * @date 2019/09/27
 */
public class PagerCardAttribute {
    private int imageHeight;
    private int imageWidth;
    private int redPointTextSize;
    private int redPointTextColor;
    private int redPointSizeWidth;
    private int redPointSizeHeight;
    private int titleTextSize;
    private int titleTextColor;
    private int unSeIndicatorColor;
    private int seIndicatorColor;
    private int unSeIndicatorWidth;
    private int seIndicatorHeight;
    private int imgType;
    private int imgCorner;
    private boolean needIndicator;
    private boolean canScrollVertically;
    private int itemDecorationWeight;
    private int itemDecorationColor;
    private int itemMarginLeft;
    private int itemMarginRight;
    private int itemMarginTop;
    private int itemMarginBottom;
    private int itemMargin;
    private int itemPadding;
    private int itemPaddingLeft;
    private int itemPaddingTop;
    private int itemPaddingRight;
    private int itemPaddingBottom;

    public PagerCardAttribute(int imageHeight, int imageWidth, int redPointTextSize, int redPointTextColor, int redPointSizeWidth, int redPointSizeHeight, int titleTextSize,
                              int titleTextColor, int unSeIndicatorColor, int seIndicatorColor, int unSeIndicatorWidth, int seIndicatorHeight,int imgTyoe,int imgCorner,
                              boolean needIndicator,boolean canScrollVertically,int itemDecorationColor,int itemDecorationWeight,
                              int itemMarginLeft,int itemMarginRight,int itemMarginTop,int itemMarginBottom,int itemMargin,int itemPadding,int itemPaddingLeft,int itemPaddingTop,int itemPaddingRight,int itemPaddingBottom) {
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
