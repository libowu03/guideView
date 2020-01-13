package com.kit.calendar.listener

import android.view.View
import com.kit.calendar.bean.DateInfo

/**
 * 42宫格设置宫格内容时的监听器
 * @author libowu
 */
interface DateSetListener{
    fun onDateSetListener(view:View,dateItem : DateInfo, dateList:MutableList<DateInfo>,index:Int)
}