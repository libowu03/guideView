package com.kit.calendar.bean;

import android.app.Application;
import android.util.Log;

import com.kit.calendar.utils.CalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateInfo {
    private int day;
    private int month;
    private int year;
    private String festival;
    private String lunarCalendar;
    private boolean isCurrentMonth;
    private boolean isHoliday;
    private int[] lunar;
    private int week;
    private Festival festivalInfo;

    public DateInfo(int day, int month, int year, String festival, String lunarCalendar, boolean isCurrentMonth,boolean isHoliday,int[] lunar,int week) {
        this.day = day;
        this.month = month;
        this.festival = festival;
        this.lunarCalendar = lunarCalendar;
        this.isCurrentMonth = isCurrentMonth;
        this.isHoliday = isHoliday;
        this.lunar = lunar;
        this.week = week;
        this.year = year;
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

    /**
     * 获取周的中文
     * @return
     */
    public String[] getWeekCn() {
        if (CalendarUtils.weekCn != null && CalendarUtils.weekCn.get(week) != null){
            return CalendarUtils.weekCn.get(week).split(",");
        }
        return null;
    }

    /**
     * 获取节日
     * @param application 用于读取assets下的内容
     * @param month 月份
     * @param day 日
     * @param lunarMonth 农历月份
     * @param lunarDay 农历日
     * @return 节日的对象
     */
    public Festival getFesitval(Application application,int month, int day, int lunarMonth, int lunarDay){
        if (festivalInfo != null){
            return festivalInfo;
        }
        String monthStr = null;
        String dayStr;
        String lunarStr = null;
        String lunarMonthStr = null;
        if (month < 10){
            monthStr = "0"+month;
        }else {
            monthStr = String.valueOf(month);
        }
        if (day < 10){
            dayStr = "0"+day;
        }else {
            dayStr = String.valueOf(day);
        }
        if (lunarDay < 10){
            lunarStr = "0"+lunarDay;
        }else {
            lunarStr = String.valueOf(lunarDay);
        }
        if (lunarMonth < 10){
            lunarMonthStr = "0"+lunarMonth;
        }else {
            lunarMonthStr = String.valueOf(lunarMonth);
        }

        HashMap<String,String> festivalMap = CalendarUtils.getFestivalMap(application);
        if (festivalMap == null){
            return null;
        }else {
            //获取农历节日
            Festival festival = new Festival();
            String lunarFestival = festivalMap.get("N"+lunarMonthStr+lunarStr);
            if (lunarFestival != null){
                String[] lunarFestivalResult = lunarFestival.split(",");
                festival.setLunarFestival(lunarFestivalResult);
            }
            //获取要显示的节日
            String commentFestival = festivalMap.get("H"+monthStr+dayStr);
            if (commentFestival != null){
                String[] commentFestivalResult =  commentFestival.split(",");
                festival.setImportantFestival(commentFestivalResult);
            }
            //获取其他节日，即存在的节日，但存在感不是很强的节日
            String otherFestival = festivalMap.get("L"+monthStr+dayStr);
            if (otherFestival != null){
                String[] otherFestivalResult =  otherFestival.split(",");
                festival.setOtherFestival(otherFestivalResult);
            }
            festivalInfo = festival;
            return festival;
        }
    }

    /**
     * 获取calendar
     * @return
     */
    public Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getDateStr());
        } catch (ParseException e) {
            return null;
        }
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取时间戳
     * @return
     */
    public long getTimeMillis(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getDateStr());
        } catch (ParseException e) {
            return -1;
        }
        return date.getTime();
    }

    /**
     * 获取date
     * @return
     */
    public Date getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(getDateStr());
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 获取yyyy-MM-dd格式的时间
     * @return
     */
    private String getDateStr(){
        String month;
        String day;
        if (this.month < 10){
            month = "0"+this.month;
        }else {
            month = String.valueOf(this.month);
        }
        if (this.day < 10){
            day = "0"+this.day;
        }else {
            day = String.valueOf(this.day);
        }
        return year + "-" + month + "-" + day;
    }
}
