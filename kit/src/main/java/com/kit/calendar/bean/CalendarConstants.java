package com.kit.calendar.bean;

/**
 * 公共变量
 */
public class CalendarConstants {
    /**
     *  日历组件里面的log标题
     */
    public final static String CALENDAR_L_TITLE = "calendarView";
    /**
     * 是否打开调试
     */
    public static boolean OPEN_DEBUG = false;
    /**
     * 当活动的年份距离最大或最小年份到达这个值时，重新载入年份
     */
    public static int RELOAD_NUM = 10;
    /**
     * 最小月份
     */
    public static int MIN_MONTH = 1;
    /**
     * 最大月份
     */
    public static int MAX_MONTH = 12;
    /**
     * 加载日历区间，默认是已当前年份为中心先前后各取100年
     */
    public static int YEAR_DURATION = 100;
}
