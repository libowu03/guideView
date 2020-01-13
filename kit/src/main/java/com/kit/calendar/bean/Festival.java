package com.kit.calendar.bean;

/**
 * 节日信息
 * @author libowu
 */
public class Festival {
    /**
     * 农历节日
     */
    public String[] lunarFestival;
    /**
     * 要显示的节日
     */
    public String[] importantFestival;
    /**
     * 有但不显示的节日
     */
    public String[] otherFestival;
    /**
     * 节气
     */
    public SolaTerms solaTerms;

    public SolaTerms getSolaTerms() {
        return solaTerms;
    }

    public void setSolaTerms(SolaTerms solaTerms) {
        this.solaTerms = solaTerms;
    }

    public String[] getLunarFestival() {
        return lunarFestival;
    }

    public void setLunarFestival(String[] lunarFestival) {
        this.lunarFestival = lunarFestival;
    }

    public String[] getImportantFestival() {
        return importantFestival;
    }

    public void setImportantFestival(String[] importantFestival) {
        this.importantFestival = importantFestival;
    }

    public String[] getOtherFestival() {
        return otherFestival;
    }

    public void setOtherFestival(String[] otherFestival) {
        this.otherFestival = otherFestival;
    }
}
