package com.kit.calendar.bean;

public class Festival {
    public String[] lunarFestival;
    public String[] importantFestival;
    public String[] otherFestival;
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
