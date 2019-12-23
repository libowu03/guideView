package com.kit.utils;

import android.content.pm.PackageManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestUtil {
    public static final int[] commonYearMonthDayNum = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    public static final int[] leapYearMonthDayNum = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
      /*  String strDate = "2016-12-31";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            System.out.println("输入的日期格式不合理！");
        }
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        List<Integer> list = new ArrayList<>();
        for (int i=w;i>=0;i--){
            list.add(31-i);
        }
        for (int i=1;i<=31;i++){
            list.add(i);
        }

        int nextLength = 42 - list.size();
        for (int i=1;i<=nextLength;i++){
            list.add(i);
        }
        for (int i=0;i<list.size();i++){
            if (i%7 == 0){
                System.out.println();
            }
            if (list.get(i) < 10){
                System.out.print(" "+list.get(i)+" ");
            }else {
                System.out.print(list.get(i)+" ");
            }
        }*/
      List<Integer> list = getDayOfMonthList(2019,3,1);
        for (int i=0;i<list.size();i++){
            if (i%7 == 0){
                System.out.println();
            }
            if (list.get(i) < 10){
                System.out.print(" "+list.get(i)+" ");
            }else {
                System.out.print(list.get(i)+" ");
            }
        }
        //System.out.println(w);
      /*  if (isCommonYear(2018)){
            System.out.println("是平年");
        }else {
            System.out.println("是闰年");
        }*/
    }

    /**
     * 获取42宫格的天数，头部为上个月的天数，中间的为本月天数，尾部为下个月的天数
     * @return 42宫格天数
     */
    public static List<Integer> getDayOfMonthList(int year,int month,int day){
        if (month > 12 || month <= 0 || year < 0){
            return null;
        }

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
        }else {
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


        List<Integer> list = new ArrayList<>();
        for (int i=w;i>=0;i--){
            list.add(preMonthDay-i);
        }

        for (int i=1;i<=currentMonthDay;i++){
            list.add(i);
        }

        int nextLength = 42 - list.size();
        for (int i=1;i<=nextLength;i++){
            list.add(i);
        }
        return list;
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

}
