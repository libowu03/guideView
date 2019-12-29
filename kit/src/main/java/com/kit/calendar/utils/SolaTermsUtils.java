package com.kit.calendar.utils;

import com.kit.calendar.CalendarView;
import com.kit.calendar.bean.SolaTerms;

/**
 * 二十四节气计算公式
 * @author libowu
 * @date 2019/12/28 00:01
 */
public class SolaTermsUtils {

    /**
     * 获取立春
     * 计算公式：Y*D+C]-L
     * 公式解读：年数的后2位乘0.2422加3.87取整数减闰年数。21世纪C值=3.87，22世纪C值=4.15。
     * 立春固定在2月份
     * @param year 年份
     * @return 立春时间，比如0203
     */
    private static String getLiChun(int year){
        double c = 0;
        if (year < 2000 && year >= 1900){
            c = 4.6295;
        }else if (year >=2000 && year < 2100){
            c = 3.87;
        }else if (year >= 2100 && year < 2200){
            c = 4.15;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));
        return "020"+result;
    }


    /**
     * 雨水计算公式
     * @param year
     * @return
     */
    private static String getYuShui(int year){
        double c = 18.73;
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));

        //2026年例外
        if (year == 2026){
            result--;
        }

        return "02"+result;

    }


    /**
     * 惊蛰计算公式
     * @param year
     * @return
     */
    private static String getJingZhe(int year){
        double c = 5.63;
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "030"+result;
    }


    /**
     * 春分计算公式
     * @param year
     * @return
     */
    private static String getChunFen(int year){
        double c = 20.646;
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 2084){
            result++;
        }

        return "03"+result;
    }

    /**
     * 清明计算公式
     * @param year
     * @return
     */
    private static String getQingMing(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 5.59;
        }else if (year >=2000 && year < 2100){
            c = 4.81;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));


        return "040"+result;
    }

    /**
     * 谷雨计算公式
     * @param year
     * @return
     */
    private static String getGuYu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 20.888;
        }else if (year >=2000 && year < 2100){
            c = 20.1;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "04"+result;
    }


    /**
     * 获取立夏公式
     * @param year
     * @return
     */
    private static String getLiXia(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.318;
        }else if (year >=2000 && year < 2100){
            c = 5.52;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 1911){
            result++;
        }

        return "050"+result;
    }


    /**
     * 小满计算公式
     * @param year
     * @return
     */
    private static String getXiaoMan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 21.86;
        }else if (year >=2000 && year < 2100){
            c = 21.04;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 2008){
            result++;
        }

        return "05"+result;
    }


    /**
     * 获取芒种
     * @param year
     * @return
     */
    private static String getManZhong(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.5;
        }else if (year >=2000 && year < 2100){
            c = 5.678;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 1902){
            result++;
        }

        return "060"+result;
    }

    /**
     * 夏至计算公式
     * @param year
     * @return
     */
    private static String getXiaZhi(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 22.20;
        }else if (year >=2000 && year < 2100){
            c = 21.37;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 1928){
            result++;
        }

        return "06"+result;
    }

    /**
     * 获取小暑公式
     * @param year
     * @return
     */
    private static String getXiaoShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 7.928;
        }else if (year >=2000 && year < 2100){
            c = 7.108;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 1925 || year == 2016){
            result++;
        }

        return "070"+result;
    }

    /**
     * 获取大暑公式
     * @param year
     * @return
     */
    private static String getDaShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.65;
        }else if (year >=2000 && year < 2100){
            c = 22.83;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 1922){
            result++;
        }

        return "07"+result;
    }

    /**
     * 获取立秋公式
     * @param year
     * @return
     */
    private static String getLiQiu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.35;
        }else if (year >=2000 && year < 2100){
            c = 7.5;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        //2026年例外
        if (year == 2002){
            result++;
        }

        return "080"+result;
    }

    /**
     * 获取处暑公式
     * @param year
     * @return
     */
    private static String getChuShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.95;
        }else if (year >=2000 && year < 2100){
            c = 23.13;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "08"+result;
    }

    /**
     * 获取白露
     * @param year
     * @return
     */
    private static String getBaiLu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.44;
        }else if (year >=2000 && year < 2100){
            c = 7.646;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 1927){
            result++;
        }
        return "090"+result;
    }

    /**
     * 获取秋分
     * @param year
     * @return
     */
    private static String getQiuFen(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.822;
        }else if (year >=2000 && year < 2100){
            c = 23.042;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 1942){
            result++;
        }
        return "09"+result;
    }

    /**
     * 获取寒露
     * @param year
     * @return
     */
    private static String getHanLu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 9.098;
        }else if (year >=2000 && year < 2100){
            c = 8.318;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        if (year == 1927){
            result++;
        }
        return "100"+result;
    }



    /**
     * 获取霜降
     * @param year
     * @return
     */
    private static String getShuangJiang(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 24.218;
        }else if (year >=2000 && year < 2100){
            c = 23.438;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 2089){
            result++;
        }
        return "10"+result;
    }

    /**
     * 获取立冬嘻嘻
     * @param year
     * @return
     */
    private static String getLiDong(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.218;
        }else if (year >=2000 && year < 2100){
            c = 7.438;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 2089){
            result++;
        }
        return "110"+result;
    }


    /**
     * 获取小雪
     * @param year
     * @return
     */
    private static String getXiaoXue(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.08;
        }else if (year >=2000 && year < 2100){
            c = 22.36;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 1978){
            result++;
        }
        return "11"+result;
    }

    /**
     * 获取大雪信息
     * @param year
     * @return
     */
    private static String getDaXue(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 7.9;
        }else if (year >=2000 && year < 2100){
            c = 7.18;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 1954){
            result++;
        }
        return "120"+result;
    }

    /**
     * 获取冬至信息
     * @param year
     * @return
     */
    private static String getDongZhi(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 22.60;
        }else if (year >=2000 && year < 2100){
            c = 21.94;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));
        if (year == 1918 || year == 2021){
            result--;
        }
        return "12"+result;
    }

    /**
     * 获取小寒信息
     * @param year
     * @return
     */
    private static String getXiaoHan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.11;
        }else if (year >=2000 && year < 2100){
            c = 5.4055;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));
        if (year == 1982){
            result++;
        }
        if (year == 2019){
            result--;
        }
        return "010"+result;
    }

    /**
     * 获取大寒信息
     * @param year
     * @return
     */
    private static String getDaHan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 20.84;
        }else if (year >=2000 && year < 2100){
            c = 20.12;
        }
        //末尾两位数
        int yearFootNum = getYearFoot(year);
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));
        if (year == 2082){
            result++;
        }
        return "01"+result;
    }



    /**
     * 获取二十四节气
     * @param lunarMonth
     * @param lunarDay
     * @return
     */
    public static SolaTerms getSolarTerms(int year, int lunarMonth, int lunarDay){
        String monthStr;
        String dayStr;
        if (lunarMonth < 10){
            monthStr = "0"+lunarMonth;
        }else {
            monthStr = String.valueOf(lunarMonth);
        }
        if (lunarDay < 10){
            dayStr = "0"+lunarDay;
        }else {
            dayStr = String.valueOf(lunarDay);
        }
        String dateStr = monthStr+dayStr;

        //获取2月节气
        if (lunarMonth == 2){
            if (dateStr.equals(getLiChun(year))){
                return new SolaTerms("立春","N"+getLiChun(year));
            }
            if (dateStr.equals(getYuShui(year))){
                return new SolaTerms("雨水","N"+getYuShui(year));
            }
        }else if (lunarMonth == 3){
            if (dateStr.equals(getJingZhe(year))){
                return new SolaTerms("惊蛰","N"+getJingZhe(year));
            }
            if (dateStr.equals(getChunFen(year))){
                return new SolaTerms("春分","N"+getChunFen(year));
            }
        }else if (lunarMonth == 4){
            if (dateStr.equals(getQingMing(year))){
                return new SolaTerms("清明","N"+getQingMing(year));
            }
            if (dateStr.equals(getGuYu(year))){
                return new SolaTerms("谷雨","N"+getGuYu(year));
            }
        }else if (lunarMonth == 5){
            if (dateStr.equals(getLiXia(year))){
                return new SolaTerms("立夏","N"+getLiXia(year));
            }
            if (dateStr.equals(getXiaoMan(year))){
                return new SolaTerms("小满","N"+getXiaoMan(year));
            }
        }else if (lunarMonth == 6){
            if (dateStr.equals(getManZhong(year))){
                return new SolaTerms("芒种","N"+getManZhong(year));
            }
            if (dateStr.equals(getXiaZhi(year))){
                return new SolaTerms("夏至","N"+getXiaZhi(year));
            }
        }else if (lunarMonth == 7){
            if (dateStr.equals(getXiaoShu(year))){
                return new SolaTerms("小暑","N"+getXiaoShu(year));
            }
            if (dateStr.equals(getDaShu(year))){
                return new SolaTerms("大暑","N"+getDaShu(year));
            }
        }else if (lunarMonth == 8){
            if (dateStr.equals(getLiChun(year))){
                return new SolaTerms("立春","N"+getLiChun(year));
            }
            if (dateStr.equals(getChuShu(year))){
                return new SolaTerms("处暑","N"+getChuShu(year));
            }
        }else if (lunarMonth == 9){
            if (dateStr.equals(getBaiLu(year))){
                return new SolaTerms("白露","N"+getBaiLu(year));
            }
            if (dateStr.equals(getQiuFen(year))){
                return new SolaTerms("秋分","N"+getQiuFen(year));
            }
        }else if (lunarMonth == 10){
            if (dateStr.equals(getBaiLu(year))){
                return new SolaTerms("白露","N"+getBaiLu(year));
            }
            if (dateStr.equals(getShuangJiang(year))){
                return new SolaTerms("霜降","N"+getShuangJiang(year));
            }
        }else if (lunarMonth == 11){
            if (dateStr.equals(getLiDong(year))){
                return new SolaTerms("立冬","N"+getLiDong(year));
            }
            if (dateStr.equals(getXiaoXue(year))){
                return new SolaTerms("小雪","N"+getXiaoXue(year));
            }
        }else if (lunarMonth == 12){
            if (dateStr.equals(getDaXue(year))){
                return new SolaTerms("大雪","N"+getDaXue(year));
            }
            if (dateStr.equals(getDongZhi(year))){
                return new SolaTerms("冬至","N"+getDongZhi(year));
            }
        }else if (lunarMonth == 1){
            if (dateStr.equals(getXiaoHan(year))){
                return new SolaTerms("小寒","N"+getXiaoHan(year));
            }
            if (dateStr.equals(getDaHan(year))){
                return new SolaTerms("大寒","N"+getDaHan(year));
            }
        }
        return null;
    }

    /**
     * 获取年份末尾数字
     * @param year
     * @return
     */
    private static int getYearFoot(int year){
        return (int)(((((year/100.0 - year/100)))*100)+0.99);
    }

    public static void main(String[] args){
        for (int year = 1900;year <= 2099; year++){
            System.out.println( "年份为：" + year +",末位：" + (int)(((((year/100.0 - year/100)))*100)+0.99) );
        }

        System.out.println( Integer.parseInt( String.valueOf((2020/100.0)).split("\\.")[1] ));
    }
}
