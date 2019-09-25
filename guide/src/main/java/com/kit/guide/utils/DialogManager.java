package com.kit.guide.utils;

import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class DialogManager {
    private static List<DialogFragment> list = new ArrayList<>();

    /**
     * 将一个dialog放入管理器中
     * @param dialogFragment
     */
    public static void putDialog(DialogFragment dialogFragment){
        list.add(dialogFragment);
    }

    /**
     * 移除管理器中的上一个dialog
     */
    public static void removePreDialog(){
        if (list.size() == 0){
            return;
        }
        list.get(list.size()-1).dismiss();
        list.remove(list.size()-1);
    }

    /**
     * 判断管理器中是否存在内容
     * @return
     */
    public static boolean isEnptyManager() {
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将当前dialog移出管理器
     * @param dialogFragment
     */
    public static void removeCurrentDialog(DialogFragment dialogFragment){
        list.remove(dialogFragment);
        //Log.e("日志","管理器中的dialog个数为："+list.size());
    }

    /**
     * 清空管理器中的内容
     */
    public static void removeAllDialog(){
        list.clear();
    }

    /**
     * 判断是否存在该dialog
     * @param dialogFragment
     * @return
     */
    public static boolean isExistCurrentDialog(DialogFragment dialogFragment){
        return list.contains(dialogFragment);
    }
}
