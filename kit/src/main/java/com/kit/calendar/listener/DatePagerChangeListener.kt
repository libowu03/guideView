package com.kit.calendar.listener

import com.kit.calendar.bean.DateInfo
import java.time.Year

interface DatePagerChangeListener {
    fun onDatePagerChange(year: Int,month:Int,dateList:MutableList<DateInfo>,pagerIndex:Int)
}