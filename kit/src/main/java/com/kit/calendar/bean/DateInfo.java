package com.kit.calendar.bean;

import com.kit.calendar.utils.CalendarUtils;

public class DateInfo {
    private int day;
    private int month;
    private int year;
    private String festival;
    private String lunarCalendar;
    private boolean isCurrentMonth;
    private boolean isHoliday;
    private int[] lunar;
    private int week = 0;
    private String[] weekCn;

    public DateInfo(int day, int month, int year, String festival, String lunarCalendar, boolean isCurrentMonth,boolean isHoliday,int[] lunar,int week) {
        this.day = day;
        this.month = month;
        this.festival = festival;
        this.lunarCalendar = lunarCalendar;
        this.isCurrentMonth = isCurrentMonth;
        this.isHoliday = isHoliday;
        this.lunar = lunar;
        this.week = week;
    }

    public String[] getWeekCn() {
        if (CalendarUtils.weekCn != null && CalendarUtils.weekCn.get(week) != null){
            return CalendarUtils.weekCn.get(week).split(",");
        }
        return null;
    }


    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int[] getLunar() {
        return lunar;
    }

    public void setLunar(int[] lunar) {
        this.lunar = lunar;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public void setCurrentMonth(boolean currentMonth) {
        isCurrentMonth = currentMonth;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getLunarCalendar() {
        return lunarCalendar;
    }

    public void setLunarCalendar(String lunarCalendar) {
        this.lunarCalendar = lunarCalendar;
    }
}
