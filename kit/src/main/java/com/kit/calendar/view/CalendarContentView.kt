package com.kit.calendar.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.utils.CalendarUtils
import com.kit.guide.R
import com.kit.guide.utils.GuideViewUtils
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*

class CalendarContentView : LinearLayout {

    private var dateViewItem: MutableList<View> ?= null
    private var dateList: MutableList<DateInfo> ?= null
    private var cal: Calendar? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context?, nothing: AttributeSet?) : this(context, nothing, 0)

    constructor(context: Context?, nothing: AttributeSet?, def: Int?) : super(context, nothing, 0) {
        cal = Calendar.getInstance()
        initView()
        initListener()
    }

    private fun initListener() {

    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.calendar_view_content,this,true)

        dateList = CalendarUtils.getDayOfMonthList(cal!!.get(Calendar.YEAR), cal!!.get(Calendar.MONTH) + 1)
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineOne, view, 0, dateList, index)
            } else {
              /*  var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineOne.addView(view)
                dateViewItem?.add(view)*/
            }
        }
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineTwo, view, 7, dateList, index)
            } else {
               /* var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineTwo.addView(view)
                dateViewItem?.add(view)*/
            }
        }
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineThree, view, 14, dateList, index)
            } else {
                /*var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineThree.addView(view)
                dateViewItem?.add(view)*/
            }
        }
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineFour, view, 21, dateList, index)
            } else {
               /* var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineFour.addView(view)
                dateViewItem?.add(view)*/
            }
        }
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineFive, view, 28, dateList, index)
            } else {
               /* var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineFive.addView(view)
                dateViewItem?.add(view)*/
            }

        }
        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineSix, view, 35, dateList, index)
            } else {
               /* var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineSix.addView(view)
                dateViewItem?.add(view)*/
            }
        }
    }

    /**
     * 初始化时设置默认数据
     */
    private fun setDateData(parentView: LinearLayout, view: View, startIndex: Int, dateList: MutableList<DateInfo>?, index: Int) {
        dateViewItem?.add(view)
        //Log.e("日志","获取的农历为："+ dateList?.get(index)?.lunar!![2])
        var day = view.findViewById<TextView>(R.id.calendarDay)
        var festival = view.findViewById<TextView>(R.id.calendarFestivalOrLunar)
        day.setText("${dateList?.get(startIndex + index)?.day}")
        festival.setText("${dateList?.get(startIndex+index)?.lunar?._date}")

        if (/*dateDayTextSize != 16*/true) {
            day.setTextSize(/*GuideViewUtils.px2dip(context, dateDayTextSize.toFloat()).toFloat()*/16f)
        }else{
            day.setTextSize(/*dateDayTextSize.toFloat()*/16f)
        }
        if (true) {
            festival.setTextSize(/*GuideViewUtils.px2dip(context, dateFestivalTextSize.toFloat()).toFloat()*/10f)
        }else{
            festival.setTextSize(/*dateFestivalTextSize.toFloat()*/16f)
        }

        //设置字体颜色
        if (!dateList?.get(startIndex + index)?.isCurrentMonth!!) {
            day.setTextColor(/*notCurrentMonthDayTextColor!!*/Color.RED)
            festival.setTextColor(/*notCurrentMonthFestivalTextColor!!*/Color.RED)
        } else {
            day.setTextColor(/*currentMonthDayTextColor!!*/Color.GREEN)
            festival.setTextColor(/*currentMonthFestivalTextColor!!*/Color.GREEN)
            //是今天，则设置选中状态
//            Log.e("日志","状态："+(dateList?.get(startIndex+index).year == cal?.get(Calendar.YEAR))+","+(dateList?.get(startIndex+index).month == (cal?.get(Calendar.MONTH)!!))+","+(dateList?.get(startIndex+index).day == cal?.get(Calendar.DAY_OF_MONTH)))
            if (dateList?.get(startIndex+index).year == cal?.get(Calendar.YEAR) && dateList?.get(startIndex+index).month == (cal?.get(Calendar.MONTH)!! +1) && dateList?.get(startIndex+index).day == cal?.get(Calendar.DAY_OF_MONTH)){
                day.setTextColor(/*selectTodayDayTextColor!!*/Color.GREEN)
                festival.setTextColor(/*selectTodayFestivalTextColor!!*/Color.GREEN)
            }
        }

        if (dateList.get(startIndex+index).isHoliday == CalendarView.Holiday.HOLIDAY){
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("休")
            holiday.setTextColor(/*holidayTipTextColor*/Color.BLACK)
            holiday.setTextSize(/*holidayTipTextSize.toFloat()*/8f)
            holiday.visibility = View.VISIBLE
        }else if (dateList.get(startIndex+index).isHoliday == CalendarView.Holiday.WORK){
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("班")
            holiday.setTextColor(/*holidayTipTextColor*/Color.BLUE)
            holiday.setTextSize(/*holidayTipTextSize.toFloat()*/8f)
            holiday.visibility = View.VISIBLE

        }else{
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("班")
            holiday.visibility = View.GONE
        }
        view.setOnClickListener(OnClickListener {
           /* if (!enableItemClick){
                return@OnClickListener
            }
            if (oldDateItem == view){
                return@OnClickListener
            }
            festival.setTextColor(Color.WHITE)
            day.setTextColor(Color.WHITE)
            if (oldDateItem != null && getTodayDateView() != oldDateItem){
                if (dateList.get(dateViewItem!!.indexOf(oldDateItem!!)).isCurrentMonth){
                    oldDateItem!!.findViewById<TextView>(R.id.calendarFestivalOrLunar).setTextColor(currentMonthFestivalTextColor)
                    oldDateItem!!.findViewById<TextView>(R.id.calendarDay).setTextColor(currentMonthDayTextColor)
                }else{
                    oldDateItem!!.findViewById<TextView>(R.id.calendarFestivalOrLunar).setTextColor(notCurrentMonthFestivalTextColor)
                    oldDateItem!!.findViewById<TextView>(R.id.calendarDay).setTextColor(notCurrentMonthDayTextColor)
                }
            }
            dateItemClickListener?.dateItemClickListener(startIndex + index, view, dateList?.get(startIndex + index))
            oldDateItem = view
            setDefaultCalendarFootInfo(dateList.get(startIndex+index))*/
        })

        setFestival(startIndex+index,dateList,festival)
        parentView.addView(view)
    }

    /**
     * 设置节日或节气
     */
    fun setFestival(index:Int,dateList:MutableList<DateInfo>?,festival:TextView){
        var item = dateList?.get(index)
        festival.setText(dateList?.get(index)?.lunar?._date)
        var festivalResult = item?.getFesitval()
        if (festivalResult != null){
            var g = Gson()
            if (festivalResult.getImportantFestival() != null){
                //是否存在简称，有则优先显示简称
                if (festivalResult.getImportantFestival()[0].contains("-")){
                    festival.setText(festivalResult.getImportantFestival()[0].split("-")[0])
                }else{
                    festival.setText(festivalResult.getImportantFestival()[0])
                }
            }
            if (festivalResult.getLunarFestival() != null){
                //是否存在简称，有则优先显示简称
                if (festivalResult.getLunarFestival()[0].contains("-")){
                    festival.setText(festivalResult.getLunarFestival()[0].split("-")[0])
                }else{
                    festival.setText(festivalResult.getLunarFestival()[0])
                }
            }
            if (festivalResult.getSolaTerms() != null){
                festival.setText(festivalResult.solaTerms.name)
            }

        }
    }
}