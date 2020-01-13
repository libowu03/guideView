package com.kit.calendar.bean;

/**
 * 节气信息
 * @author libowu
 */
public class SolaTerms {
    /**
     * 节气名称
     */
    private String name;
    private String date;

    public SolaTerms(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
