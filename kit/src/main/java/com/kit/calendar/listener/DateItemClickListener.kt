package com.kit.calendar.listener

import android.view.View
import com.kit.calendar.bean.DateInfo

interface DateItemClickListener {
    fun onDateItemClickListener(currentView:View,dateItem:DateInfo,dateList:MutableList<DateInfo>,index:Int)
}