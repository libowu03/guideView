package com.kit.calendar.listener

import android.view.View
import com.kit.calendar.bean.DateInfo

interface DateSetListener{
    fun onDateSetListener(view:View,dateItem : DateInfo, dateList:MutableList<DateInfo>,index:Int)
}