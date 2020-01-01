package com.kit.calendar.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.listener.DateItemClickListener
import com.kit.calendar.utils.CalendarUtils
import com.kit.guide.R
import com.kit.guide.utils.GuideViewUtils
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*

class CalendarContentView : LinearLayout {

    private var dateViewItem: MutableList<View> ?= null
    private var dateList: MutableList<DateInfo> ?= null
    private var cal: Calendar? = null
    private var date : String ?= null
    var clickListener:DateItemClickListener ?= null

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
        dateViewItem = mutableListOf()

        for (index in 0..6) {
            if (true) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineOne, view,index)
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
                setDateData(calendarLineTwo, view,6+index)
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
                setDateData(calendarLineThree, view,12+index)
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
                setDateData(calendarLineFour, view,18+index)
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
                setDateData(calendarLineFive, view,24+index)
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
                setDateData(calendarLineSix, view,30+index)
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
    private fun setDateData(parentView: LinearLayout, view: View,index:Int) {
        dateViewItem?.add(view)
        //Log.e("日志","获取的农历为："+ dateList?.get(index)?.lunar!![2])
        parentView.addView(view)
    }

    /**
     * 设置日期
     */
    fun setDate(date:String){
        dateViewItem?.let {
            this.date = date
            var dateResult = date.split("-")
            dateList = CalendarUtils.getDayOfMonthList(dateResult[0].toInt(), dateResult[1].toInt())
            for (item in dateViewItem!!.withIndex()){
                var day = item.value.findViewById<TextView>(R.id.calendarDay)
                var festival = item.value.findViewById<TextView>(R.id.calendarFestivalOrLunar)
                day.setText("${dateList?.get(item.index)?.day}")
                festival.setText("${dateList?.get(item.index)?.lunar?._date}")

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
                if (!dateList?.get(item.index)?.isCurrentMonth!!) {
                    day.setTextColor(/*notCurrentMonthDayTextColor!!*/Color.RED)
                    festival.setTextColor(/*notCurrentMonthFestivalTextColor!!*/Color.RED)
                } else {
                    day.setTextColor(/*currentMonthDayTextColor!!*/Color.GREEN)
                    festival.setTextColor(/*currentMonthFestivalTextColor!!*/Color.GREEN)
                    //是今天，则设置选中状态
                    if (dateList?.get(item.index)!!.year == cal?.get(Calendar.YEAR) && dateList?.get(item.index)!!.month == (cal?.get(Calendar.MONTH)!! +1) && dateList!!.get(item.index).day == cal?.get(Calendar.DAY_OF_MONTH)){
                        day.setTextColor(/*selectTodayDayTextColor!!*/Color.GREEN)
                        festival.setTextColor(/*selectTodayFestivalTextColor!!*/Color.GREEN)
                    }
                }

                if (dateList!!.get(item.index).isHoliday == CalendarView.Holiday.HOLIDAY){
                    var holiday = item.value.findViewById<TextView>(R.id.calendarHolidayStatus)
                    holiday.setText("休")
                    holiday.setTextColor(/*holidayTipTextColor*/Color.BLACK)
                    holiday.setTextSize(/*holidayTipTextSize.toFloat()*/8f)
                    holiday.visibility = View.VISIBLE
                }else if (dateList!!.get(item.index).isHoliday == CalendarView.Holiday.WORK){
                    var holiday = item.value.findViewById<TextView>(R.id.calendarHolidayStatus)
                    holiday.setText("班")
                    holiday.setTextColor(/*holidayTipTextColor*/Color.BLUE)
                    holiday.setTextSize(/*holidayTipTextSize.toFloat()*/8f)
                    holiday.visibility = View.VISIBLE

                }else{
                    var holiday = item.value.findViewById<TextView>(R.id.calendarHolidayStatus)
                    holiday.setText("班")
                    holiday.visibility = View.GONE
                }
                item.value.setOnClickListener(OnClickListener {

                })

                item.value.setOnClickListener {
                    clickListener?.onDateItemClickListener(item.value,dateList!!.get(item.index),dateList!!,item.index)
                }

                setFestival(item.index,dateList,festival)
            }
        }
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