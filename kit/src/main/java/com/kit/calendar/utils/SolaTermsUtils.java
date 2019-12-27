package com.kit.calendar.utils;

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
    public static String getLiChun(int year){
        double c = 0;
        if (year < 2000 && year >= 1900){
            c = 4.6295;
        }else if (year >=2000 && year < 2100){
            c = 3.87;
        }else if (year >= 2100 && year < 2200){
            c = 4.15;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));
        return "020"+result;
    }


    /**
     * 谷雨计算公式
     * @param year
     * @return
     */
    public static String getYuShui(int year){
        double c = 18.73;
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getJingZhe(int year){
        double c = 5.63;
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "030"+result;
    }


    /**
     * 春分计算公式
     * @param year
     * @return
     */
    public static String getChunFen(int year){
        double c = 20.646;
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getQingMing(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 5.59;
        }else if (year >=2000 && year < 2100){
            c = 4.81;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));


        return "040"+result;
    }

    /**
     * 谷雨计算公式
     * @param year
     * @return
     */
    public static String getGuYu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 20.888;
        }else if (year >=2000 && year < 2100){
            c = 20.1;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "04"+result;
    }


    /**
     * 获取立夏公式
     * @param year
     * @return
     */
    public static String getLiXia(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.318;
        }else if (year >=2000 && year < 2100){
            c = 5.52;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getXiaoMan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 21.86;
        }else if (year >=2000 && year < 2100){
            c = 21.04;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getManZhong(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.5;
        }else if (year >=2000 && year < 2100){
            c = 5.678;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getXiaZhi(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 22.20;
        }else if (year >=2000 && year < 2100){
            c = 21.37;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getXiaoShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 7.928;
        }else if (year >=2000 && year < 2100){
            c = 7.108;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getDaShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.65;
        }else if (year >=2000 && year < 2100){
            c = 22.83;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getLiQiu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.35;
        }else if (year >=2000 && year < 2100){
            c = 7.5;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getChuShu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.95;
        }else if (year >=2000 && year < 2100){
            c = 23.13;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum)/4));

        return "08"+result;
    }

    /**
     * 获取白露
     * @param year
     * @return
     */
    public static String getBaiLu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.44;
        }else if (year >=2000 && year < 2100){
            c = 7.646;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getQiuFen(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.822;
        }else if (year >=2000 && year < 2100){
            c = 23.042;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getHanLu(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 9.098;
        }else if (year >=2000 && year < 2100){
            c = 8.318;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getShuangJiang(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 24.218;
        }else if (year >=2000 && year < 2100){
            c = 23.438;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getLiDong(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 8.218;
        }else if (year >=2000 && year < 2100){
            c = 7.438;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getXiaoXue(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 23.08;
        }else if (year >=2000 && year < 2100){
            c = 22.36;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getDaXue(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 7.9;
        }else if (year >=2000 && year < 2100){
            c = 7.18;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getDongZhi(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 22.60;
        }else if (year >=2000 && year < 2100){
            c = 21.94;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getXiaoHan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 6.11;
        }else if (year >=2000 && year < 2100){
            c = 5.4055;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
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
    public static String getDaHan(int year){
        double c = 20.646;
        if (year < 2000 && year >= 1900){
            c = 20.84;
        }else if (year >=2000 && year < 2100){
            c = 20.12;
        }
        //末尾两位数
        int yearFootNum = Integer.parseInt( String.valueOf((year/100.0)).split("\\.")[1] );
        int result = (int)((yearFootNum*0.2422+c)-( (yearFootNum-1)/4));
        if (year == 2089){
            result++;
        }
        return "01"+result;
    }

    public static void main(String[] args){
        System.out.println(getXiaoHan(1988));
    }
}
