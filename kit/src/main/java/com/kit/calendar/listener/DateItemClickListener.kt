package com.kit.calendar.listener

import android.view.View
import com.kit.calendar.bean.DateInfo

/**
 * 42宫格的宫格点击监听器
 * @author libowu
 */
interface DateItemClickListener {
    fun onDateItemClickListener(currentView:View,dateItem:DateInfo,dateList:MutableList<DateInfo>,index:Int,oldView:View?)
}