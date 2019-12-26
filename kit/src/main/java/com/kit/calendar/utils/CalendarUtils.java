package com.kit.calendar.utils;

import android.content.pm.PackageManager;

import com.kit.calendar.bean.DateInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CalendarUtils {
    public static final int[] commonYearMonthDayNum = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    public static final int[] leapYearMonthDayNum = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
    public static final HashMap<Integer,String> lunarCn = new HashMap<>();
    public static final HashMap<Integer,String> weekCn = new HashMap<>();
    private int year_ganZhi;
    private int month_ganZhi;
    private int day_ganZhi;

    static {
        lunarCn.put(1,"初一");
        lunarCn.put(2,"初二");
        lunarCn.put(3,"初三");
        lunarCn.put(4,"初四");
        lunarCn.put(5,"初五");
        lunarCn.put(6,"初六");
        lunarCn.put(7,"初七");
        lunarCn.put(8,"初八");
        lunarCn.put(9,"初九");
        lunarCn.put(10,"初十");
        lunarCn.put(11,"十一");
        lunarCn.put(12,"十二");
        lunarCn.put(13,"十三");
        lunarCn.put(14,"十四");
        lunarCn.put(15,"十五");
        lunarCn.put(16,"十六");
        lunarCn.put(17,"十七");
        lunarCn.put(18,"十八");
        lunarCn.put(19,"十九");
        lunarCn.put(20,"二十");
        lunarCn.put(21,"廿一");
        lunarCn.put(22,"廿二");
        lunarCn.put(23,"廿三");
        lunarCn.put(24,"廿四");
        lunarCn.put(25,"廿五");
        lunarCn.put(26,"廿六");
        lunarCn.put(27,"廿七");
        lunarCn.put(28,"廿八");
        lunarCn.put(29,"廿九");
        lunarCn.put(30,"三十");
        weekCn.put(0,"星期日,周日,七");
        weekCn.put(1,"星期一,周一,一");
        weekCn.put(2,"星期二,周二,二");
        weekCn.put(3,"星期三,周三,三");
        weekCn.put(4,"星期四,周四,四");
        weekCn.put(5,"星期五,周五,五");
        weekCn.put(6,"星期六,周六,六");
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
      List<DateInfo> list = getDayOfMonthList(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1);
        for (int i=0;i<list.size();i++){
            if (i%7 == 0){
                System.out.println();
            }
            if (list.get(i).getDay() < 10){
                System.out.print(" "+list.get(i).getDay()+" ");
            }else {
                System.out.print(list.get(i).getDay()+" ");
            }
        }
    }

    /**
     * 获取42宫格的天数，头部为上个月的天数，中间的为本月天数，尾部为下个月的天数
     * @return 42宫格天数
     */
    public static List<DateInfo> getDayOfMonthList(int year, int month){
        if (month > 12 || month <= 0 || year < 0){
            return null;
        }
        boolean isCurrentYear;

        Calendar cal = Calendar.getInstance();
        String strDate;
        //上一个月的天数
        int preMonthDay;
        //本月的天数
        int currentMonthDay;

        //获取本月的天数，平年则使用平年的天数数组，闰年则使用闰年的
        if (isCommonYear(year)){
            currentMonthDay = commonYearMonthDayNum[month - 1];
        }else {
            currentMonthDay = leapYearMonthDayNum[month - 1];
        }

        //获取上一个月的天数，如果当前月份为1月份，则上一个月的月份为12月份，天数为12,
        if (month == 1){
            //获取上一年的最后一天日期，12月份固定31天
            strDate = ""+(year-1)+"-12-31";
            preMonthDay = 31;
            isCurrentYear = false;
        }else {
            isCurrentYear = true;
            if (isCommonYear(year)){
                preMonthDay = commonYearMonthDayNum[ month-2 ];
            }else {
                preMonthDay = leapYearMonthDayNum[ month-2 ];
            }
            strDate = ""+year+"-"+(month-1)+"-"+preMonthDay+"";
        }

        //获取上一个月最后一天的星期数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            System.out.println("输入的日期格式不合理！");
        }
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("星期为："+w);

        List<DateInfo> list = new ArrayList<>();
        int week = 0;
        for (int i=w-1;i>=0;i--){
            //int day, int month, int year, String festival, String lunarCalendar, boolean isCurrentMonth, boolean usCurrentYear
            week++;
            if (isCurrentYear){
                list.add(new DateInfo(preMonthDay-i,month-1,year,"","",false,false,LunarCalendar.solarToLunar(year,month-1,preMonthDay-i),week));
            }else {
                list.add(new DateInfo(preMonthDay-i,12,year-1,"","",false,false,LunarCalendar.solarToLunar(year-1,12,preMonthDay-i),week));
            }
            if (week == 6){
                week = -1;
            }
        }

        for (int i=1;i<=currentMonthDay;i++){
            week++;
            list.add(new DateInfo(i,month,year,"","",true,false,LunarCalendar.solarToLunar(year,month,i),week));
            if (week == 6){
                week = -1;
            }

        }

        int nextLength = 42 - list.size();
        for (int i=1;i<=nextLength;i++){
            week++;
            //如果是12月份的，这尾部则是下一年一月份的内容
            if (month == 12){
                list.add(new DateInfo(i,1,year+1,"","",false,false,LunarCalendar.solarToLunar(year+1,1,i),week));
            }else {
                list.add(new DateInfo(i,month+1,year,"","",false,false,LunarCalendar.solarToLunar(year,month+1,i),week));
            }
            if (week == 6){
                week = -1;
            }
        }
        return list;
    }


    /**
     * 获取节日信息
     * @param year 年份
     * @param month 月份
     * @param day 日
     * @param lunar 农历
     * @param includeUnNecessary 是否需要非必须的节日
     * @return
     */
    public static String getFestival(int year,int month,int day,int lunar,boolean includeUnNecessary){
        return null;
    }


    /**
     * 是否是平年
     * Common year：平年
     * Leap Year ：闰年
     * 计算公式：非整百年份除以4，整百年份除以400不能整除的即为平年
     * @param year 需要判断的年份
     * @return 是否是平年，true：平年，false：闰年
     */
    public static boolean isCommonYear(int year){
        if (year%100 ==0){
            if (year%400 == 0){
                return false;
            }else {
                return true;
            }
        }else {
            if (year%4 == 0){
                return false;
            }else {
                return true;
            }
        }
    }




    private static int[] lunar_info = {0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
            0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
            0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
            0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
            0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
            0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
            0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
            0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
            0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
            0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
            0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
            0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
            0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
            0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
            0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
            0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
            0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
            0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
            0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    /**
     * 记录天干的信息
     */
    private String[] gan_info = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
            "壬", "癸"};
    private String[] zhi_info = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"};

    /**
     * 获取农历某年的总天数
     *
     * @param year
     * @return
     */
    private int daysOfYear(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunar_info[year - 1900] & i) == 0 ? 0 : 1;
        }
        //获取闰月的天数
        int daysOfLeapMonth;
        if ((lunar_info[year - 1900] & 0xf) != 0) {
            daysOfLeapMonth = (lunar_info[year - 1900] & 0x10000) == 0 ? 29 : 30;
        } else {
            daysOfLeapMonth = 0;
        }
        return sum + daysOfLeapMonth;
    }

    /**
     * 初始化年月日对应的天干地支
     * @param year
     * @param month
     * @param day
     */
    public void initGanZhi(int year, int month, int day) {
        //获取现在的时间
        Calendar calendar_now = Calendar.getInstance();
        calendar_now.set(year, month - 1, day);
        long date_now = calendar_now.getTime().getTime();
        //获取1900-01-31的时间
        Calendar calendar_ago = Calendar.getInstance();
        calendar_ago.set(1900, 0 ,31);
        long date_ago = calendar_ago.getTime().getTime();
        //86400000 = 24 * 60 * 60 * 1000
        long days_distance = (date_now - date_ago) / 86400000L;
        float remainder = (date_now - date_ago) % 86400000L;
        //余数大于0算一天
        if (remainder > 0) {
            days_distance += 1;
        }
        //都是从甲子开始算起以1900-01-31为起点
        //1899-12-21是农历1899年腊月甲子日  40：相差1900-01-31有40天
        day_ganZhi = (int)days_distance + 40;
        //1898-10-01是农历甲子月  14：相差1900-01-31有14个月
        month_ganZhi = 14;
        int daysOfYear = 0;
        int i;
        for (i = 1900; i < 2050 && days_distance > 0; i++) {
            daysOfYear = daysOfYear(i);
            days_distance -= daysOfYear;
            month_ganZhi += 12;
        }
        if (days_distance < 0) {
            days_distance += daysOfYear;
            i--;
            month_ganZhi -= 12;
        }
        //农历年份
        int myYear = i;
        //1864年是甲子年
        year_ganZhi = myYear - 1864;
        //哪个月是闰月
        int leap = lunar_info[myYear - 1900] & 0xf;
        boolean isLeap = false;
        int daysOfLeapMonth = 0;
        for (i = 1; i < 13 && days_distance > 0; i++) {
            //闰月
            if (leap > 0 && i == (leap + 1) && !isLeap) {
                isLeap = true;
                if ((lunar_info[myYear - 1900] & 0xf) != 0) {
                    daysOfLeapMonth = (lunar_info[myYear - 1900] & 0x10000) == 0 ? 29 : 30;
                } else {
                    daysOfLeapMonth = 0;
                }
                --i;
            } else {
                daysOfLeapMonth = (lunar_info[myYear - 1900] & (0x10000 >> i)) == 0 ? 29 : 30;
            }
            //设置非闰月
            if (isLeap && i == (leap + 1)) {
                isLeap = false;
            }
            days_distance -= daysOfLeapMonth;
            if (!isLeap) {
                month_ganZhi++;
            }
        }
        if (days_distance == 0 && leap > 0 && i == leap + 1 && !isLeap) {
            --month_ganZhi;
        }
        if (days_distance < 0) {
            --month_ganZhi;
        }
    }

    /**
     * 将年月日转化为天干地支的显示方法
     * @param index
     * @return
     */
    private String ganZhi(int index) {
        return gan_info[index % 10] + zhi_info[index % 12];
    }

    /**
     * 获取天干地支
     * @return
     */
    public String getGanZhi() {
        return "农历" + ganZhi(year_ganZhi) + "年 " + ganZhi(month_ganZhi) + "月 " + ganZhi(day_ganZhi) + "日";
    }

}
