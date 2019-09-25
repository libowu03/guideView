package com.test.guide;

import com.kit.pagerCard.PagerCardBean;

public class TestBean extends PagerCardBean {
    private String info = "";

    public TestBean(String name, String img, boolean showRedPoint, String action) {
        super(name, img, showRedPoint, action);
    }

    public TestBean(String name, String img) {
        super(name, img);
    }

    public TestBean(String name, String img, String info) {
        super(name, img);
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
