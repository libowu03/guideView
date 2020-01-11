package com.kit.calendar.bean;

import android.graphics.Color;

public class CalendarAttribute {
    /**
     * 日期的文字大小
     */
    private int dateDayTextSize = 0;
    /**
     * 日期下面的节日或农历文字大小
     */
    private int dateFestivalTextSize = 0;
    /**
     * 非当前月份日期的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
     */
    private int notCurrentMonthDayTextColor = 0;
    /**
     * 非当前月份农历或节日的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
     */
    private int notCurrentMonthFestivalTextColor = 0;
    /**
     * 当前月份日期的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
     */
    private int currentMonthDayTextColor = 0;
    /**
     * 非当前月份农历或节日的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
     */
    private int currentMonthFestivalTextColor = 0;
    /**
     * 日历的顶部周一至周日的字体颜色
     */
    private int headWeekTextColor = 0;
    /**
     * 日历顶部周一至周六的字体大小
     */
    private int headWeekTextSize = 0;
    /**
     * dateItem的view id
     */
    private int dateItemLayout = 0;
    /**
     * 节假日提示文字大小
     */
    private int holidayTipTextSize = 8;
    /**
     * 节假日文字颜色
     */
    private int holidayTipTextColor  = Color.RED;
    /**
     * 默认选中日期的字体颜色
     */
    private int selectTodayDayTextColor  = Color.WHITE;
    /**
     * 默认选中日期的节日字体颜色
     */
    private int selectTodayFestivalTextColor  = Color.WHITE;
    /**
     * 是否允许日期点击
     */
    private boolean enableItemClick  = true;
    /**
     * 上班文字提示颜色
     */
    private int workDayTipTextColor;
    /**
     * 周栏布局
     */
    private int weekBarLayout;

    public CalendarAttribute(int dateDayTextSize,
                             int dateFestivalTextSize,
                             int notCurrentMonthDayTextColor,
                             int notCurrentMonthFestivalTextColor,
                             int currentMonthDayTextColor,
                             int currentMonthFestivalTextColor,
                             int headWeekTextColor,
                             int headWeekTextSize,
                             int dateItemLayout,
                             int holidayTipTextSize,
                             int holidayTipTextColor,
                             int selectTodayDayTextColor,
                             int selectTodayFestivalTextColor,
                             boolean enableItemClick,
                             int wrokDayTipTextColor,
                             int weekBarLayout) {
        this.dateDayTextSize = dateDayTextSize;
        this.dateFestivalTextSize = dateFestivalTextSize;
        this.notCurrentMonthDayTextColor = notCurrentMonthDayTextColor;
        this.notCurrentMonthFestivalTextColor = notCurrentMonthFestivalTextColor;
        this.currentMonthDayTextColor = currentMonthDayTextColor;
        this.currentMonthFestivalTextColor = currentMonthFestivalTextColor;
        this.headWeekTextColor = headWeekTextColor;
        this.headWeekTextSize = headWeekTextSize;
        this.dateItemLayout = dateItemLayout;
        this.holidayTipTextSize = holidayTipTextSize;
        this.holidayTipTextColor = holidayTipTextColor;
        this.selectTodayDayTextColor = selectTodayDayTextColor;
        this.selectTodayFestivalTextColor = selectTodayFestivalTextColor;
        this.enableItemClick = enableItemClick;
        this.workDayTipTextColor = wrokDayTipTextColor;
        this.weekBarLayout = weekBarLayout;
    }

    public int getWeekBarLayout() {
        return weekBarLayout;
    }

    public void setWeekBarLayout(int weekBarLayout) {
        this.weekBarLayout = weekBarLayout;
    }

    public int getWorkDayTipTextColor() {
        return workDayTipTextColor;
    }

    public void setWorkDayTipTextColor(int workDayTipTextColor) {
        this.workDayTipTextColor = workDayTipTextColor;
    }

    public int getDateDayTextSize() {
        return dateDayTextSize;
    }

    public void setDateDayTextSize(int dateDayTextSize) {
        this.dateDayTextSize = dateDayTextSize;
    }

    public int getDateFestivalTextSize() {
        return dateFestivalTextSize;
    }

    public void setDateFestivalTextSize(int dateFestivalTextSize) {
        this.dateFestivalTextSize = dateFestivalTextSize;
    }

    public int getNotCurrentMonthDayTextColor() {
        return notCurrentMonthDayTextColor;
    }

    public void setNotCurrentMonthDayTextColor(int notCurrentMonthDayTextColor) {
        this.notCurrentMonthDayTextColor = notCurrentMonthDayTextColor;
    }

    public int getNotCurrentMonthFestivalTextColor() {
        return notCurrentMonthFestivalTextColor;
    }

    public void setNotCurrentMonthFestivalTextColor(int notCurrentMonthFestivalTextColor) {
        this.notCurrentMonthFestivalTextColor = notCurrentMonthFestivalTextColor;
    }

    public int getCurrentMonthDayTextColor() {
        return currentMonthDayTextColor;
    }

    public void setCurrentMonthDayTextColor(int currentMonthDayTextColor) {
        this.currentMonthDayTextColor = currentMonthDayTextColor;
    }

    public int getCurrentMonthFestivalTextColor() {
        return currentMonthFestivalTextColor;
    }

    public void setCurrentMonthFestivalTextColor(int currentMonthFestivalTextColor) {
        this.currentMonthFestivalTextColor = currentMonthFestivalTextColor;
    }

    public int getHeadWeekTextColor() {
        return headWeekTextColor;
    }

    public void setHeadWeekTextColor(int headWeekTextColor) {
        this.headWeekTextColor = headWeekTextColor;
    }

    public int getHeadWeekTextSize() {
        return headWeekTextSize;
    }

    public void setHeadWeekTextSize(int headWeekTextSize) {
        this.headWeekTextSize = headWeekTextSize;
    }

    public int getDateItemLayout() {
        return dateItemLayout;
    }

    public void setDateItemLayout(int dateItemLayout) {
        this.dateItemLayout = dateItemLayout;
    }

    public int getHolidayTipTextSize() {
        return holidayTipTextSize;
    }

    public void setHolidayTipTextSize(int holidayTipTextSize) {
        this.holidayTipTextSize = holidayTipTextSize;
    }

    public int getHolidayTipTextColor() {
        return holidayTipTextColor;
    }

    public void setHolidayTipTextColor(int holidayTipTextColor) {
        this.holidayTipTextColor = holidayTipTextColor;
    }

    public int getSelectTodayDayTextColor() {
        return selectTodayDayTextColor;
    }

    public void setSelectTodayDayTextColor(int selectTodayDayTextColor) {
        this.selectTodayDayTextColor = selectTodayDayTextColor;
    }

    public int getSelectTodayFestivalTextColor() {
        return selectTodayFestivalTextColor;
    }

    public void setSelectTodayFestivalTextColor(int selectTodayFestivalTextColor) {
        this.selectTodayFestivalTextColor = selectTodayFestivalTextColor;
    }

    public boolean isEnableItemClick() {
        return enableItemClick;
    }

    public void setEnableItemClick(boolean enableItemClick) {
        this.enableItemClick = enableItemClick;
    }
}
