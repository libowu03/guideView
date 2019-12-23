package com.kit.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.utils.CalendarUtils
import com.kit.calendar.utils.LunarCalendar
import com.kit.guide.R
import kotlinx.android.synthetic.main.calendar_head.view.*
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*

/**
 * 日历控件
 * @author libowu
 * @date 2019/12/22
 * 冬至撸代码，别有一番风味
 */
class CalendarView : LinearLayout, View.OnClickListener {
    private var dateViewItem : MutableList<View> ?= null
    private var currentYear:Int = 0
    private var currentMonth:Int = 0

    private var dateList: MutableList<DateInfo>?= null

    constructor(context:Context) : this(context,null)

    constructor(context: Context?, nothing: AttributeSet?) : this(context,nothing,0)

    constructor(context: Context?, nothing: AttributeSet?,def:Int?) : super(context,nothing,0) {
        initView()
        initListener()
    }

    private fun initListener() {
        calendarMonthNext.setOnClickListener(this)
        calendarMonthPre.setOnClickListener(this)
        calendarYearPre.setOnClickListener(this)
        calendarYearNext.setOnClickListener(this)
        calendarHeadBackToTodayTv.setOnClickListener(this)
    }


    /**
     * 初始化布局
     */
    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.calendar_view,this,true)
        calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head,this,false))

        //日历默认值(当前时间)
        var cal = Calendar.getInstance()
        currentMonth = cal.get(Calendar.MONTH)+1
        currentYear = cal.get(Calendar.YEAR)
        dateList = CalendarUtils.getDayOfMonthList(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1)
        dateViewItem = mutableListOf()

        //设置当前头部的日期
        calendarMonthTextTv.setText("${currentMonth}")
        calendarYearTextTv.setText("${currentYear}")

        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineOne,view,0,dateList,index)
        }
        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineTwo,view,7,dateList,index)
        }
        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineThree,view,14,dateList,index)
        }
        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineFour,view,21,dateList,index)
        }
        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineFive,view,28,dateList,index)
        }
        for(index in 0..6){
            var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false)
            setDateData(calendarLineSix,view,35,dateList,index)
        }
    }

    /**
     * 初始化时设置默认数据
     */
    private fun setDateData(parentView:LinearLayout,view:View,startIndex:Int,dateList:MutableList<DateInfo>?,index:Int){
        dateViewItem?.add(view)
        var day = view.findViewById<TextView>(R.id.calendarDay)
        var festival = view.findViewById<TextView>(R.id.calendarFestivalOrLunar)
        day.setText("${dateList?.get(startIndex+index)?.day}")
        festival.setText("${CalendarUtils.lunarCn.get(dateList?.get(index)?.lunar?.get(2))}")
        if (!dateList?.get(startIndex+index)?.isCurrentMonth!!){
            day.setTextColor(Color.parseColor("#cccccc"))
            festival.setTextColor(Color.parseColor("#cccccc"))
        }
        parentView.addView(view)
    }


    /**
     * 通过按钮触发改变日历界面数据
     */
    private fun setNewData(year:Int,month:Int){
        dateList = CalendarUtils.getDayOfMonthList(year,month)
        for (index in 0..41){
            var view = dateViewItem?.get(index)
            var day = view?.findViewById<TextView>(R.id.calendarDay)
            var festival = view?.findViewById<TextView>(R.id.calendarFestivalOrLunar)
            day?.setText("${dateList?.get(index)?.day}")

            if (!dateList?.get(index)?.isCurrentMonth!!){
                day?.setTextColor(context.resources.getColor(R.color.notCurrentMonthColor))
                festival?.setTextColor(context.resources.getColor(R.color.notCurrentMonthColor))
                festival?.setText("${CalendarUtils.lunarCn.get(dateList?.get(index)?.lunar?.get(2))}")
            }else{
                day?.setTextColor(context.resources.getColor(R.color.currentMonthColor))
                festival?.setTextColor(context.resources.getColor(R.color.currentMonthColor))
                festival?.setText("${CalendarUtils.lunarCn.get(dateList?.get(index)?.lunar?.get(2))}")
            }
        }

        //设置日期和时间
        calendarMonthTextTv.setText("${currentMonth}")
        calendarYearTextTv.setText("${currentYear}")
    }

    override fun onClick(p0: View?) {
        when(p0){
            calendarMonthNext -> {
                if (currentMonth == 12){
                    if (currentYear + 1> LunarCalendar.MAX_YEAR){
                        return
                    }
                    currentYear++
                    currentMonth = 1
                }else{
                    ++currentMonth
                }
                setNewData(currentYear,currentMonth)
            }
            calendarMonthPre -> {
                if (currentMonth == 1){
                    if (currentYear - 1 < LunarCalendar.MIN_YEAR){
                        return
                    }
                    currentYear--
                    currentMonth = 12
                }else{
                    --currentMonth
                }
                setNewData(currentYear,currentMonth)
            }
            calendarYearPre -> {
                if (currentYear - 1 < LunarCalendar.MIN_YEAR){
                    return
                }
                setNewData(--currentYear,currentMonth)
            }
            calendarYearNext -> {
                if (currentYear + 1 > LunarCalendar.MAX_YEAR){
                    return
                }
                setNewData(++currentYear,currentMonth)
            }
            calendarHeadBackToTodayTv -> {
                //日历默认值(当前时间)
                var cal = Calendar.getInstance()
                currentMonth = cal.get(Calendar.MONTH)+1
                currentYear = cal.get(Calendar.YEAR)
                dateList = CalendarUtils.getDayOfMonthList(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1)
                setNewData(++currentYear,currentMonth)
            }
        }
    }
}