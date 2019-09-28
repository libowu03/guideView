package com.kit.utils;

import android.util.Log;

/**
 * 日志帮助类
 * @author libowu
 * @date 2019/09/28
 */
public class LogUtils {
    public static boolean isDebug;
    public static void e(String title,String message){
        if (isDebug){
            Log.e(title,message);
        }
    }

    public static void i(String title,String message){
        if (isDebug){
            Log.i(title,message);
        }
    }

    public static void w(String title,String message){
        if (isDebug){
            Log.w(title,message);
        }
    }
}
