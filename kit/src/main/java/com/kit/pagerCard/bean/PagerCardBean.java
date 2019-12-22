package com.kit.pagerCard.bean;

/**
 * pagerCard的数据内容，如果需要自定义，请继承自此类
 * @author libowu
 * @date 2019/09/27
 */
public class PagerCardBean {
    /**
     * 条目名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String img;
    /**
     * 是否显示红点
     */
    private boolean showRedPoint;
    /**
     * 点击后要执行的动作，这里可放具体的activity名称，也可放网页链接，具体跳转情况需要自己实现接口监听
     */
    private String action;
    /**
     * 右上角的文字
     */
    private String redPointText;

    public PagerCardBean(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public PagerCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getRedPointText() {
        return redPointText;
    }

    public PagerCardBean setRedPointText(String redPointText) {
        this.redPointText = redPointText;
        this.showRedPoint = false;
        return this;
    }

    public String getImg() {
        return img;
    }


    public boolean isShowRedPoint() {
        return showRedPoint;
    }

    public PagerCardBean setShowRedPoint(boolean showRedPoint) {
        this.showRedPoint = showRedPoint;
        redPointText = "";
        return this;
    }

    public String getAction() {
        return action;
    }

    public PagerCardBean setAction(String action) {
        this.action = action;
        return this;
    }
}
