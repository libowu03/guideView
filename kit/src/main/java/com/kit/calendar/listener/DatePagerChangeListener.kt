package com.kit.calendar.listener

import com.kit.calendar.bean.DateInfo
import java.time.Year

/**
 * 日期切换的监听器
 * @author libowu
 */
interface DatePagerChangeListener {
    fun onDatePagerChange(year: Int,month:Int,dateList:MutableList<DateInfo>,pagerIndex:Int)
}