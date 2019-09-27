package com.kit.pagerCard;

/**
 * 存放pagerCard的基本属性，比如图片大小，字体大小，红点背景等
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

    public PagerCardAttribute(int imageHeight, int imageWidth, int redPointTextSize, int redPointTextColor, int redPointSizeWidth, int redPointSizeHeight, int titleTextSize,
                              int titleTextColor, int unSeIndicatorColor, int seIndicatorColor, int unSeIndicatorWidth, int seIndicatorHeight,int imgTyoe,int imgCorner,boolean needIndicator,boolean canScrollVertically) {
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
