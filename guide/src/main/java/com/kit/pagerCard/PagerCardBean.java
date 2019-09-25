package com.kit.pagerCard;

/**
 * pagerCard的数据内容，如果需要自定义，请继承自此类
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

    public PagerCardBean(String name, String img, boolean showRedPoint,String action) {
        this.name = name;
        this.img = img;
        this.showRedPoint = showRedPoint;
    }

    public PagerCardBean(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isShowRedPoint() {
        return showRedPoint;
    }

    public void setShowRedPoint(boolean showRedPoint) {
        this.showRedPoint = showRedPoint;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
