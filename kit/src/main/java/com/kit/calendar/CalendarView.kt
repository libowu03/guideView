package com.kit.calendar

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.utils.CalendarUtils
import com.kit.calendar.utils.LunarCalendar
import com.kit.guide.R
import com.kit.guide.utils.GuideViewUtils
import kotlinx.android.synthetic.main.calendar_foot.view.*
import kotlinx.android.synthetic.main.calendar_head.view.*
import kotlinx.android.synthetic.main.calendar_view.view.*
import kotlinx.android.synthetic.main.calendar_week.view.*
import java.util.*

/**
 * 日历控件
 * @author libowu
 * @date 2019/12/22
 * 冬至撸代码，别有一番风味
 */
class CalendarView : LinearLayout, View.OnClickListener {
    private var footLayout: Int = 0
    private var headLayout: Int = 0
    private var dateViewItem: MutableList<View>? = null
    //当前年份
    private var currentYear: Int = 0
    //当前月份
    private var currentMonth: Int = 0
    //当前日期
    private var currentDay: Int = 0
    //当前日期，初始化后就确定为今天所在的月份了，后面不会再变动。
    private var todayMonth: Int = 0
    //点击监听
    private var dateItemClickListener: OnDateItemClickListener? = null
    //日期信息，里面记录了年，月，日，农历，节日，是否是假期等信息。
    private var dateList: MutableList<DateInfo>? = null
    //是否默认选中今天
    private var selectToday: Boolean = true
    //上一次点击的view
    private var oldDateItem: View? = null
    //今天的日期
    private var todayDateInfo:DateInfo ?= null


    //日期的文字大小
    private var dateDayTextSize: Int = 0
    //日期下面的节日或农历文字大小
    private var dateFestivalTextSize: Int = 0
    //非当前月份日期的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
    private var notCurrentMonthDayTextColor: Int = 0
    //非当前月份农历或节日的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
    private var notCurrentMonthFestivalTextColor: Int = 0
    //当前月份日期的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
    private var currentMonthDayTextColor: Int = 0
    //非当前月份农历或节日的文字颜色，此日历插件分为三个部分，前面部分为上个月日期，当前日期和下一个月的日期
    private var currentMonthFestivalTextColor: Int = 0
    //日历的顶部周一至周日的字体颜色
    private var headWeekTextColor: Int = 0
    //日历顶部周一至周六的字体大小
    private var headWeekTextSize: Int = 0
    //是否打开对头部的支持
    private var enableHeadLayout: Boolean = true
    //是否打开对尾部的支持
    private var enableFootLayout: Boolean = true
    //dateItem的view id
    private var dateItemLayout: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context?, nothing: AttributeSet?) : this(context, nothing, 0)

    constructor(context: Context?, nothing: AttributeSet?, def: Int?) : super(context, nothing, 0) {
        initArr(context, nothing, def)
        initView()
        initListener()
    }

    /**
     * 初始化界面属性
     */
    private fun initArr(context: Context?, nothing: AttributeSet?, def: Int?) {
        val typedArray = context!!.theme.obtainStyledAttributes(nothing, R.styleable.CalendarView, def!!, 0)
        dateDayTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_dateDayTextSize, 16)
        dateFestivalTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_dateFestivalTextSize, 13)
        notCurrentMonthDayTextColor = typedArray.getColor(R.styleable.CalendarView_notCurrentMonthDayTextColor, context.resources.getColor(R.color.notCurrentMonthColor))
        notCurrentMonthFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_notCurrentMonthFestivalTextColor, context.resources.getColor(R.color.notCurrentMonthColor))
        currentMonthDayTextColor = typedArray.getColor(R.styleable.CalendarView_currentMonthDayTextColor, context.resources.getColor(R.color.currentMonthColor))
        currentMonthFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_currentMonthDayTextColor, context.resources.getColor(R.color.currentMonthColor))
        headWeekTextColor = typedArray.getColor(R.styleable.CalendarView_headWeekTextColor, context.resources.getColor(R.color.weekBarTextColor))
        headWeekTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_headWeekTextSize, 16)
        selectToday = typedArray.getBoolean(R.styleable.CalendarView_selectToday, true)
        headLayout = typedArray.getResourceId(R.styleable.CalendarView_calendarHeadLayout, 0)
        footLayout = typedArray.getResourceId(R.styleable.CalendarView_calendarFootLayout, 0)
        enableFootLayout = typedArray.getBoolean(R.styleable.CalendarView_enableFootLayout, true)
        enableHeadLayout = typedArray.getBoolean(R.styleable.CalendarView_enableHeadLayout, true)
        dateItemLayout = typedArray.getResourceId(R.styleable.CalendarView_dateItemLayout, R.layout.calendar_view_item_date)
    }

    /**
     * 初始化监听器
     */
    private fun initListener() {
        calendarMonthNext?.setOnClickListener(this)
        calendarMonthPre?.setOnClickListener(this)
        calendarYearPre?.setOnClickListener(this)
        calendarYearNext?.setOnClickListener(this)
        calendarHeadBackToTodayTv?.setOnClickListener(this)
    }


    /**
     * 初始化布局
     */
    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.calendar_view, this, true)

        //设置周一至周日的字体颜色及大小
        for (index in 0..calendarWeekBar.childCount) {
            if (calendarWeekBar.getChildAt(index) is TextView) {
                (calendarWeekBar.getChildAt(index) as TextView).setTextColor(headWeekTextColor!!)
                if (headWeekTextSize != 16) {
                    (calendarWeekBar.getChildAt(index) as TextView).setTextSize(GuideViewUtils.px2dip(context, headWeekTextSize!!.toFloat()).toFloat())
                }
            }
        }

        //日历默认值(当前时间)
        var cal = Calendar.getInstance()
        currentMonth = cal.get(Calendar.MONTH) + 1
        todayMonth = cal.get(Calendar.MONTH) + 1
        currentYear = cal.get(Calendar.YEAR)
        currentDay = cal.get(Calendar.DAY_OF_MONTH)
        dateList = CalendarUtils.getDayOfMonthList(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
        dateViewItem = mutableListOf()

        //设置头部
        if (headLayout != 0) {
            calendarHead.addView(LayoutInflater.from(context).inflate(headLayout, this, false))
        } else {
            calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head, this, false))
            //设置当前头部的日期
            calendarMonthTextTv.setText("${currentMonth}")
            calendarYearTextTv.setText("${currentYear}")
            calendarHeadTime.setText("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH) + 1}-${cal.get(Calendar.DAY_OF_MONTH)}")
            //设置日期下面的农历或节日
            calendarHeadFestival.setText("农历："+getTodayDateInfo()!!.lunar[0]+"-"+ getTodayDateInfo()!!.lunar[1]+"-"+ getTodayDateInfo()?.lunar!![2])
            var festivalInfo = getTodayDateInfo()?.getFesitval((context as Activity).application)
            if (festivalInfo != null){
                if (festivalInfo.getImportantFestival() != null){
                    calendarHeadFestival.setText(festivalInfo.getImportantFestival()[0])
                }
                if (festivalInfo.getLunarFestival() != null){
                    calendarHeadFestival.setText(festivalInfo.getLunarFestival()[0])
                }
                if (festivalInfo.getSolaTerms() != null){
                    calendarHeadFestival.setText(festivalInfo.getSolaTerms().name)
                }
            }
        }

        //设置尾部
        if (footLayout == 0) {
            calendarFoot.addView(LayoutInflater.from(context).inflate(R.layout.calendar_foot, this, false))
            setDefaultCalendarFootInfo(getTodayDateInfo()!!)
        } else {
            calendarFoot.addView(LayoutInflater.from(context).inflate(footLayout, this, false))
        }

        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineOne, view, 0, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineOne.addView(view)
                dateViewItem?.add(view)
            }
        }
        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineTwo, view, 7, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineTwo.addView(view)
                dateViewItem?.add(view)
            }
        }
        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineThree, view, 14, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineThree.addView(view)
                dateViewItem?.add(view)
            }
        }
        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineFour, view, 21, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineFour.addView(view)
                dateViewItem?.add(view)
            }
        }
        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineFive, view, 28, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineFive.addView(view)
                dateViewItem?.add(view)
            }

        }
        for (index in 0..6) {
            if (R.layout.calendar_view_item_date == dateItemLayout) {
                var view = LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date, this, false)
                setDateData(calendarLineSix, view, 35, dateList, index)
            } else {
                var view = LayoutInflater.from(context).inflate(dateItemLayout, this, false)
                var llp = view.layoutParams as LinearLayout.LayoutParams
                llp.weight = 1f
                llp.width = 0
                view.layoutParams = llp
                calendarLineSix.addView(view)
                dateViewItem?.add(view)
            }
        }

        if (!enableHeadLayout) {
            hideHeadView()
        }
        if (!enableFootLayout) {
            hideFootView()
        }
    }

    private fun setDefaultCalendarFootInfo(dateInfo:DateInfo){
        var festivalList = dateInfo?.getFesitval( (context as Activity).application)
        calendarFootDate.setText(""+dateInfo!!.lunar[0]+"-"+ dateInfo!!.lunar[1]+"-"+ dateInfo?.lunar!![2])
        festivalList?.let {
            calendarFootFestival.removeAllViews()
            //设置农历节日
            var lunarFestival = festivalList.lunarFestival
            lunarFestival?.let {
                for (item in lunarFestival){
                    if (item.contains(",")){
                        var festivalChild = item.split(",")
                        for (itemChild in festivalChild){
                            var festival = TextView(context)
                            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                            festival.layoutParams = lp
                            festival.setText(itemChild)
                            calendarFootFestival.addView(festival)
                        }
                    }else{
                        var festival = TextView(context)
                        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                        lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                        festival.layoutParams = lp
                        festival.setText(item)
                        calendarFootFestival.addView(festival)
                    }
                }
            }

            //设置重要节日
            var importantFestival = festivalList.importantFestival
            importantFestival?.let {
                for (item in importantFestival){
                    if (item.contains(",")){
                        var festivalChild = item.split(",")
                        for (itemChild in festivalChild){
                            var festival = TextView(context)
                            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                            festival.layoutParams = lp
                            festival.setText(itemChild)
                            festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                            calendarFootFestival.addView(festival)
                        }
                    }else{
                        var festival = TextView(context)
                        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                        lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                        festival.layoutParams = lp
                        festival.setText(item)
                        festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                        calendarFootFestival.addView(festival)
                    }
                }
            }

            //设置其他节日
            var otherFestival = festivalList.otherFestival
            otherFestival?.let {
                for (item in otherFestival){
                    if (item.contains(",")){
                        var festivalChild = item.split(",")
                        for (itemChild in festivalChild){
                            var festival = TextView(context)
                            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                            festival.layoutParams = lp
                            festival.setText(itemChild)
                            calendarFootFestival.addView(festival)
                        }
                    }else{
                        var festival = TextView(context)
                        var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                        lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                        festival.layoutParams = lp
                        festival.setText(item)
                        calendarFootFestival.addView(festival)
                    }
                }
            }

            if (calendarFootFestival.childCount == 0){
                calendarFootFestivalBox.visibility = View.GONE
            }else{
                calendarFootFestivalBox.visibility = View.VISIBLE
            }

            //设置节气
            if (festivalList.solaTerms != null){
                calendarFootSolarTerms.setText(festivalList.solaTerms.name)
                calendarFootSolarTermsBox.visibility = View.VISIBLE
            }else{
                calendarFootSolarTermsBox.visibility = View.GONE
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
        festival.setText("${CalendarUtils.lunarCn.get(dateList?.get(startIndex+index)?.lunar?.get(2))}")

        if (dateDayTextSize != 16) {
            day.setTextSize(GuideViewUtils.px2dip(context, dateDayTextSize.toFloat()).toFloat())
        }
        if (dateFestivalTextSize != 16) {
            festival.setTextSize(GuideViewUtils.px2dip(context, dateFestivalTextSize.toFloat()).toFloat())
        }

        //设置字体颜色
        if (!dateList?.get(startIndex + index)?.isCurrentMonth!!) {
            day.setTextColor(notCurrentMonthDayTextColor!!)
            festival.setTextColor(notCurrentMonthFestivalTextColor!!)
        } else {
            day.setTextColor(currentMonthDayTextColor!!)
            festival.setTextColor(currentMonthFestivalTextColor!!)
        }

        view.setOnClickListener(OnClickListener {
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
            setDefaultCalendarFootInfo(dateList.get(startIndex+index))
        })
        //设置今天日期的样式
        if (dateList?.get(startIndex + index).day == currentDay && selectToday && dateList?.get(startIndex + index).isCurrentMonth) {
            view.setBackgroundColor(context.resources.getColor(R.color.colorTitle))
            day.setTextColor(context.resources.getColor(R.color.white))
            festival.setTextColor(context.resources.getColor(R.color.white))
        }
        setFestival(startIndex+index,dateList,festival)
        parentView.addView(view)
    }

    /**
     * 设置节日或节气
     */
    fun setFestival(index:Int,dateList:MutableList<DateInfo>?,festival:TextView){
        var item = dateList?.get(index)
        var festivalResult = item?.getFesitval( (context as Activity).application)
        if (festivalResult != null){
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

    fun setOnDateItemClickListener(listener: OnDateItemClickListener) {
        this.dateItemClickListener = listener
    }

    /**
     * 获取当前时间的view
     * @day 日期
     */
    fun getDayViewByDate(day: Int): View? {
        if (day > 32 || day <= 0) {
            return null
        }
        if (dateList != null) {
            for (item in dateList!!.withIndex()) {
                if (item.value.day == day && item.value.isCurrentMonth) {
                    return dateViewItem!!.get(item.index)
                }
            }
        }
        return null
    }

    /**
     * 获取当前时间的dateInfo
     * @day 日期
     */
    fun getDateInfoByDate(day: Int): DateInfo? {
        if (day > 32 || day <= 0) {
            return null
        }
        if (dateList != null) {
            for (item in dateList!!.withIndex()) {
                if (item.value.day == day && item.value.isCurrentMonth) {
                    return item.value!!
                }
            }
        }
        return null
    }

    /**
     * 获取当前时间的dateInfo
     */
    fun getTodayDateInfo(): DateInfo? {
        if (todayDateInfo != null){
            return todayDateInfo
        }
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1
        var year = Calendar.getInstance().get(Calendar.YEAR)
        if (day > 32 || day <= 0) {
            return null
        }
        var dateList = CalendarUtils.getDayOfMonthList(year,month)
        if (dateList != null) {
            for (item in dateList!!.withIndex()) {
                if (item.value.day == day && item.value.isCurrentMonth) {
                    return item.value!!
                }
            }
        }
        return null
    }

    /**
     * 获取当前时间的dateInfo
     * @day 日期
     */
    fun getTodayDateView(): View? {
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        if (day > 32 || day <= 0) {
            return null
        }
        if (dateList != null) {
            for (item in dateList!!.withIndex()) {
                if (item.value.day == day && item.value.isCurrentMonth) {
                    return dateViewItem!!.get(item.index)
                }
            }
        }
        return null
    }

    /**
     * 通过按钮触发改变日历界面数据
     */
    private fun setNewData(year: Int, month: Int) {
        dateList = CalendarUtils.getDayOfMonthList(year, month)
        for (index in 0..41) {
            var view = dateViewItem?.get(index)
            var day = view?.findViewById<TextView>(R.id.calendarDay)
            var festival = view?.findViewById<TextView>(R.id.calendarFestivalOrLunar)
            day?.setText("${dateList?.get(index)?.day}")

            if (!dateList?.get(index)?.isCurrentMonth!!) {
                day?.setTextColor(notCurrentMonthDayTextColor!!)
                festival?.setTextColor(notCurrentMonthFestivalTextColor!!)
                view?.setBackgroundColor(context.resources.getColor(R.color.transparent))
                festival?.setText("${CalendarUtils.lunarCn.get(dateList?.get(index)?.lunar?.get(2))}")
            } else {
                if ((dateList?.get(index)!!.day == currentDay) && dateList?.get(index)!!.month == todayMonth && selectToday) {
                    view?.setBackgroundColor(context.resources.getColor(R.color.colorTitle))
                    day?.setTextColor(context.resources.getColor(R.color.white))
                    festival?.setTextColor(context.resources.getColor(R.color.white))
                } else {
                    day?.setTextColor(currentMonthDayTextColor!!)
                    festival?.setTextColor(currentMonthFestivalTextColor!!)
                    festival?.setText("${CalendarUtils.lunarCn.get(dateList?.get(index)?.lunar?.get(2))}")
                }
            }

            view?.setOnClickListener(OnClickListener {
                festival!!.setTextColor(Color.WHITE)
                day!!.setTextColor(Color.WHITE)
                if (oldDateItem != null && getTodayDateView() != oldDateItem){
                    if (dateList!!.get(dateViewItem!!.indexOf(oldDateItem!!)).isCurrentMonth){
                        oldDateItem!!.findViewById<TextView>(R.id.calendarFestivalOrLunar).setTextColor(currentMonthFestivalTextColor)
                        oldDateItem!!.findViewById<TextView>(R.id.calendarDay).setTextColor(currentMonthDayTextColor)
                    }else{
                        oldDateItem!!.findViewById<TextView>(R.id.calendarFestivalOrLunar).setTextColor(notCurrentMonthFestivalTextColor)
                        oldDateItem!!.findViewById<TextView>(R.id.calendarDay).setTextColor(notCurrentMonthDayTextColor)
                    }
                }
                dateItemClickListener?.dateItemClickListener(index, view, dateList?.get(index)!!)
                oldDateItem = view
                setDefaultCalendarFootInfo(dateList!!.get(index))
            })

            setFestival(index,dateList,festival!!)
        }

        //设置日期和时间
        calendarMonthTextTv.setText("${currentMonth}")
        calendarYearTextTv.setText("${currentYear}")
    }


    /**
     * 更新到当前时间
     */
    fun updateDate() {
        var cal = Calendar.getInstance()
        currentMonth = cal.get(Calendar.MONTH) + 1
        currentYear = cal.get(Calendar.YEAR)
        dateList = CalendarUtils.getDayOfMonthList(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
        setNewData(currentYear, currentMonth)
    }

    /**
     * 获取当前日期的42宫格内容
     */
    fun getDateInfoList(): MutableList<DateInfo>? {
        return dateList
    }

    /**
     * 获取42宫格的view
     */
    fun getDateViewList(): MutableList<View>? {
        return dateViewItem
    }

    /**
     * 获取头部信息
     * @return 头部的view
     */
    fun getHeadView(): View {
        return calendarHead
    }

    /**
     * 隐藏头部内容
     */
    fun hideHeadView() {
        if (calendarHead != null) {
            calendarHead?.visibility = View.GONE
        }
        if (calendarHeadLine != null) {
            calendarHeadLine?.visibility = View.GONE
        }
    }

    /**
     * 隐藏尾部
     */
    fun hideFootView() {
        calendarFoot.visibility = View.GONE
    }

    fun getFootView(): View {
        return calendarFoot
    }

    override fun onClick(p0: View?) {
        when (p0) {
            calendarMonthNext -> {
                if (currentMonth == 12) {
                    if (currentYear + 1 > LunarCalendar.MAX_YEAR) {
                        return
                    }
                    currentYear++
                    currentMonth = 1
                } else {
                    ++currentMonth
                }
                setNewData(currentYear, currentMonth)
            }
            calendarMonthPre -> {
                if (currentMonth == 1) {
                    if (currentYear - 1 < LunarCalendar.MIN_YEAR) {
                        return
                    }
                    currentYear--
                    currentMonth = 12
                } else {
                    --currentMonth
                }
                setNewData(currentYear, currentMonth)
            }
            calendarYearPre -> {
                if (currentYear - 1 < LunarCalendar.MIN_YEAR) {
                    return
                }
                setNewData(--currentYear, currentMonth)
            }
            calendarYearNext -> {
                if (currentYear + 1 > LunarCalendar.MAX_YEAR) {
                    return
                }
                setNewData(++currentYear, currentMonth)
            }
            calendarHeadBackToTodayTv -> {
                //日历默认值(当前时间)
                updateDate()
            }
        }
    }

    interface OnDateItemClickListener {
        fun dateItemClickListener(index: Int, currentView: View, dateInfo: DateInfo)
    }
}