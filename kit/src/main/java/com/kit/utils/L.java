package com.kit.utils;

import android.util.Log;

import static com.kit.calendar.bean.CalendarConstants.OPEN_DEBUG;


/**
 * 日志帮助类
 * @author libowu
 * @date 2019/09/28
 */
public class L {
    public static void e(String title,String message){
        if (OPEN_DEBUG){
            Log.e(title,message);
        }
        Log.e(title,message);
    }

    public static void e(String title,String message,Throwable throwable){
        if (OPEN_DEBUG){
            Log.e(title,message,throwable);
        }
    }

    public static void i(String title,String message){
        if (OPEN_DEBUG){
            Log.i(title,message);
        }
    }

    public static void i(String title,String message,Throwable throwable){
        if (OPEN_DEBUG){
            Log.i(title,message,throwable);
        }
    }

    public static void d(String title,String message){
        if (OPEN_DEBUG){
            Log.d(title,message);
        }
    }

    public static void d(String title,String message,Throwable throwable){
        if (OPEN_DEBUG){
            Log.d(title,message,throwable);
        }
    }

    public static void println(String title,String message){
        if (OPEN_DEBUG){
            Log.d(title,message);
        }
    }

    public static void println(String title,String message,Throwable throwable){
        if (OPEN_DEBUG){
            Log.d(title,message,throwable);
        }
    }

    public static void v(String title,String message){
        if (OPEN_DEBUG){
            Log.v(title,message);
        }
    }
    public static void v(String title,String message,Throwable throwable){
        if (OPEN_DEBUG){
            Log.v(title,message,throwable);
        }
    }

    public static void w(String title,String message){
        if (OPEN_DEBUG){
            Log.w(title,message);
        }
    }
}
