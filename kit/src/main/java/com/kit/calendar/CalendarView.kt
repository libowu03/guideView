package com.kit.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.utils.CalendarUtils
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
    //是否执行过自动设置字体大小的步骤了
    private var isAuthorSetTextSize : Boolean = false
    //今天的日期
    private var cal:Calendar ?= null
    //比较合适的屏幕尺寸
    private val SUITABLE_WIDTH : Float= 1080f
    //合适的高度
    private val SUITABLE_HEIGHT : Float = 1313f


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
    private var enableFootLayout: Boolean = false
    //dateItem的view id
    private var dateItemLayout: Int = 0
    //默认尾部节日的字体大小
    private var footDefaultFestivalTextSize = 16
    //节假日提示文字大小
    private var holidayTipTextSize : Int= 8
    //节假日文字颜色
    private var holidayTipTextColor : Int = Color.RED
    //默认选中日期的字体颜色
    private var selectTodayDayTextColor : Int = Color.WHITE
    //默认选中日期的节日字体颜色
    private var selectTodayFestivalTextColor : Int = Color.WHITE
    //是否允许日期点击
    private var enableItemClick : Boolean = true

    object Holiday{
        //节日
        const val HOLIDAY : Int = 1
        //放假后的补班
        const val WORK : Int = 0
        //普通日期，即不放假也不补班
        const val COMMON_DAY : Int = -1
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context?, nothing: AttributeSet?) : this(context, nothing, 0)

    constructor(context: Context?, nothing: AttributeSet?, def: Int?) : super(context, nothing, 0) {
        cal = Calendar.getInstance()
        initArr(context, nothing, def)
        initView()
        initListener()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 初始化界面属性
     */
    private fun initArr(context: Context?, nothing: AttributeSet?, def: Int?) {
        val typedArray = context!!.theme.obtainStyledAttributes(nothing, R.styleable.CalendarView, def!!, 0)
        dateDayTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_dateDayTextSize, 16)
        dateFestivalTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_dateFestivalTextSize, 10)
        notCurrentMonthDayTextColor = typedArray.getColor(R.styleable.CalendarView_notCurrentMonthDayTextColor, context.resources.getColor(R.color.notCurrentMonthColor))
        notCurrentMonthFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_notCurrentMonthFestivalTextColor, context.resources.getColor(R.color.notCurrentMonthColor))
        currentMonthDayTextColor = typedArray.getColor(R.styleable.CalendarView_currentMonthDayTextColor, context.resources.getColor(R.color.currentMonthColor))
        currentMonthFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_currentMonthDayTextColor, context.resources.getColor(R.color.currentMonthColor))
        headWeekTextColor = typedArray.getColor(R.styleable.CalendarView_headWeekTextColor, context.resources.getColor(R.color.weekBarTextColor))
        headWeekTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_headWeekTextSize, 16)
        selectToday = typedArray.getBoolean(R.styleable.CalendarView_selectToday, true)
        headLayout = typedArray.getResourceId(R.styleable.CalendarView_calendarHeadLayout, 0)
        footLayout = typedArray.getResourceId(R.styleable.CalendarView_calendarFootLayout, 0)
        enableFootLayout = typedArray.getBoolean(R.styleable.CalendarView_enableFootLayout, false)
        enableHeadLayout = typedArray.getBoolean(R.styleable.CalendarView_enableHeadLayout, true)
        dateItemLayout = typedArray.getResourceId(R.styleable.CalendarView_dateItemLayout, 0)
        holidayTipTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_holidayTipTextSize, 8)
        holidayTipTextColor = typedArray.getColor(R.styleable.CalendarView_holidayTipTextColor, Color.RED)
        selectTodayDayTextColor = typedArray.getColor(R.styleable.CalendarView_selectTodayDayTextColor, Color.WHITE)
        selectTodayFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_selectTodayFestivalTextColor, Color.WHITE)
        enableItemClick = typedArray.getBoolean(R.styleable.CalendarView_enableItemClick, true)
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
                }else{
                    (calendarWeekBar.getChildAt(index) as TextView).setTextSize(headWeekTextSize.toFloat())
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
            calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head, this, false))
        } else {
            calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head, this, false))
            //设置当前头部的日期
            calendarMonthTextTv.setText("${currentMonth}")
            calendarYearTextTv.setText("${currentYear}")
            calendarHeadTime.setText("${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH) + 1}-${cal.get(Calendar.DAY_OF_MONTH)}")
            //设置日期下面的农历或节日
            calendarHeadFestival.setText("农历："+getTodayDateInfo()?.lunar.toString())
            var festivalInfo = getTodayDateInfo()?.getFesitval()
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
            if (0 == dateItemLayout) {
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
            if (0 == dateItemLayout) {
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
            if (0 == dateItemLayout) {
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
            if (0 == dateItemLayout) {
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
            if (0 == dateItemLayout) {
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
            if (0 == dateItemLayout) {
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            //标准请款下满屏宽度时使用16的字体是合适的，就用16在全宽下的比例来计算最合适的字体大小(只在屏幕宽度与当前画布宽度不同时调用)
            if (context.resources.displayMetrics.widthPixels == canvas.width || isInEditMode){
                isAuthorSetTextSize = true
                return
            }
            var percentage = 16 / SUITABLE_WIDTH
            var percentageHoliday = 8 / SUITABLE_WIDTH
            if (!isAuthorSetTextSize && headLayout ==0 && dateItemLayout == 0 && footLayout == 0){
                if (dateDayTextSize ==16 && dateFestivalTextSize == 10 && headWeekTextSize == 16){
                   var dateDayTextSize = (canvas.width * percentage)
                   var dateFestivalTextSize = (canvas.width * percentage)
                   var headWeekTextSize = (canvas.width * percentage)
                   var holidayTextSize = (canvas.width * percentageHoliday)

                    var dateItemView = getDateViewList()
                    dateItemView?.let {
                        for (item in dateItemView){
                            var day = item.findViewById<TextView>(R.id.calendarDay)
                            var festival = item.findViewById<TextView>(R.id.calendarFestivalOrLunar)
                            var holiday = item.findViewById<TextView>(R.id.calendarHolidayStatus)
                            day.textSize = dateDayTextSize
                            festival.textSize = dateFestivalTextSize
                            holiday.textSize = holidayTextSize
                        }
                    }

                    for (index in 0..calendarWeekBar.childCount) {
                        if (calendarWeekBar.getChildAt(index) is TextView) {
                            (calendarWeekBar.getChildAt(index) as TextView).setTextColor(headWeekTextColor!!)
                            if (headWeekTextSize != 16f) {
                                (calendarWeekBar.getChildAt(index) as TextView).setTextSize(headWeekTextSize.toFloat())
                            }
                        }
                    }
                }

                //设置头部字体
                if (headLayout == 0 || isInEditMode){
                    var percentage = GuideViewUtils.px2dip(context,resources.getDimension(R.dimen.titleOne_20)) / SUITABLE_WIDTH
                    Log.e("日志","resour资源："+GuideViewUtils.px2dip(context,resources.getDimension(R.dimen.titleOne_20)))
                    var percentage10 = 10 / SUITABLE_WIDTH
                    var headDate = (canvas.width * percentage).toInt()
                    var headLunarDate = (canvas.width * percentage).toInt()
                    var headLayout10 = (canvas.width * percentage10).toInt()
                    calendarHeadTime.setTextSize(headDate.toFloat())
                    calendarHeadFestival.setTextSize(headLunarDate.toFloat())
                    calendarHeadBackToTodayTv.setTextSize(headLunarDate.toFloat())
                    calendarYearTextTv.setTextSize(headDate.toFloat())
                    calendarMonthTextTv.setTextSize(headDate.toFloat())
                    calendarYearPre.setTextSize(headDate.toFloat())
                    calendarYearNext.setTextSize(headDate.toFloat())
                    calendarMonthNext.setTextSize(headDate.toFloat())
                    calendarMonthPre.setTextSize(headDate.toFloat())

                    calendarBox.setPadding(headLayout10,headLayout10,headLayout10,headLayout10)
                }

                //设置尾部
                if (footLayout == 0 || isInEditMode){
                    var percentageFootTitle = GuideViewUtils.px2dip(context,resources.getDimension(R.dimen.titleTwo_12)) / SUITABLE_WIDTH
                    var percentageFootContent = GuideViewUtils.px2dip(context,resources.getDimension(R.dimen.titleTwo_16)) / SUITABLE_WIDTH
                    var percentageFootBox = GuideViewUtils.dip2px(context,110f) / SUITABLE_HEIGHT
                    var title12 = (canvas.width * percentageFootTitle).toInt()
                    var title16 = (canvas.width * percentageFootContent).toInt()
                    var titleBox = (canvas.width * percentageFootBox).toInt()
                    footDefaultFestivalTextSize = title16
                    calendarFootLunarTitle.setTextSize(title12.toFloat())
                    calendarFootFestivalTitle.setTextSize(title12.toFloat())
                    calendarFootSolaTerms.setTextSize(title12.toFloat())
                    calendarFootDate.setTextSize(title16.toFloat())
                    calendarFootSolarTerms.setTextSize(title16.toFloat())
                    for (index in 0..calendarFootFestival.childCount-1){
                        (calendarFootFestival.getChildAt(index) as TextView).setTextSize(title16.toFloat())
                    }

                    //设置尾部的高度到最适合的大小
                    var lp = calendarFootBox.layoutParams
                    lp.height = GuideViewUtils.dip2px(context,titleBox.toFloat())
                    calendarFootBox.layoutParams = lp
                }

                //设置整个日历的padding
                var paddingPrecentage = 10 / (context.resources.displayMetrics.widthPixels*1.0)
                var calendarBoxPadding = (canvas.width * paddingPrecentage).toInt()
                calendarBox.setPadding(calendarBoxPadding,calendarBoxPadding,calendarBoxPadding,calendarBoxPadding)
                isAuthorSetTextSize = true
            }
        }
    }

    /**
     * 设置底部默认布局的文案，比如农历，节日，节气
     * @param dateInfo 日期详情
     */
    private fun setDefaultCalendarFootInfo(dateInfo:DateInfo){
        var festivalList = dateInfo?.getFesitval()
        if (footLayout == 0){
            calendarFootDate?.setText(dateInfo?.lunar.toString())
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
                                festival.setTextSize(footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(footDefaultFestivalTextSize.toFloat())
                            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                            festival.layoutParams = lp
                            festival.setText(item)
                            festival.setTextColor(context.resources.getColor(R.color.colorTitle))
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
                                festival.setTextSize(footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(footDefaultFestivalTextSize.toFloat())
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
                                festival.setTextSize(footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(footDefaultFestivalTextSize.toFloat())
                            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                            lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                            festival.layoutParams = lp
                            festival.setText(item)
                            festival.setTextColor(context.resources.getColor(R.color.colorTitle))
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

        if (dateDayTextSize != 16) {
            day.setTextSize(GuideViewUtils.px2dip(context, dateDayTextSize.toFloat()).toFloat())
        }else{
            day.setTextSize(dateDayTextSize.toFloat())
        }
        if (dateFestivalTextSize != 10) {
            festival.setTextSize(GuideViewUtils.px2dip(context, dateFestivalTextSize.toFloat()).toFloat())
        }else{
            festival.setTextSize(dateFestivalTextSize.toFloat())
        }

        //设置字体颜色
        if (!dateList?.get(startIndex + index)?.isCurrentMonth!!) {
            day.setTextColor(notCurrentMonthDayTextColor!!)
            festival.setTextColor(notCurrentMonthFestivalTextColor!!)
        } else {
            day.setTextColor(currentMonthDayTextColor!!)
            festival.setTextColor(currentMonthFestivalTextColor!!)
            //是今天，则设置选中状态
//            Log.e("日志","状态："+(dateList?.get(startIndex+index).year == cal?.get(Calendar.YEAR))+","+(dateList?.get(startIndex+index).month == (cal?.get(Calendar.MONTH)!!))+","+(dateList?.get(startIndex+index).day == cal?.get(Calendar.DAY_OF_MONTH)))
            if (dateList?.get(startIndex+index).year == cal?.get(Calendar.YEAR) && dateList?.get(startIndex+index).month == (cal?.get(Calendar.MONTH)!! +1) && dateList?.get(startIndex+index).day == cal?.get(Calendar.DAY_OF_MONTH)){
                day.setTextColor(selectTodayDayTextColor!!)
                festival.setTextColor(selectTodayFestivalTextColor!!)
            }
        }

        if (dateList.get(startIndex+index).isHoliday == Holiday.HOLIDAY){
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("休")
            holiday.setTextColor(holidayTipTextColor)
            holiday.setTextSize(holidayTipTextSize.toFloat())
            holiday.visibility = View.VISIBLE
        }else if (dateList.get(startIndex+index).isHoliday == Holiday.WORK){
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("班")
            holiday.setTextColor(holidayTipTextColor)
            holiday.setTextSize(holidayTipTextSize.toFloat())
            holiday.visibility = View.VISIBLE

        }else{
            var holiday = view.findViewById<TextView>(R.id.calendarHolidayStatus)
            holiday.setText("班")
            holiday.visibility = View.GONE
        }
        view.setOnClickListener(OnClickListener {
            if (!enableItemClick){
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
            setDefaultCalendarFootInfo(dateList.get(startIndex+index))
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
            //Log.e("日志","农历情况为："+Gson().toJson(dateList?.get(index)))

            var view = dateViewItem?.get(index)
            var day = view?.findViewById<TextView>(R.id.calendarDay)
            var festival = view?.findViewById<TextView>(R.id.calendarFestivalOrLunar)
            day?.setText("${dateList?.get(index)?.day}")
            setFestival(index,dateList,festival!!)

            //设置字体颜色
            if (!dateList?.get(index)?.isCurrentMonth!!) {
                day?.setTextColor(notCurrentMonthDayTextColor!!)
                festival.setTextColor(notCurrentMonthFestivalTextColor!!)
            } else {
                day?.setTextColor(currentMonthDayTextColor!!)
                festival.setTextColor(currentMonthFestivalTextColor!!)
                if (dateList?.get(index)!!.year == cal?.get(Calendar.YEAR) && dateList?.get(index)!!.month == (cal?.get(Calendar.MONTH)!! +1) && dateList?.get(index)!!.day == cal?.get(Calendar.DAY_OF_MONTH)){
                    day?.setTextColor(selectTodayDayTextColor!!)
                    festival.setTextColor(selectTodayFestivalTextColor!!)
                }
            }

            if (dateList!!.get(index).isHoliday == Holiday.HOLIDAY){
                var holiday = view?.findViewById<TextView>(R.id.calendarHolidayStatus)
                holiday?.setText("休")
                holiday?.setTextColor(holidayTipTextColor)
                holiday?.visibility = View.VISIBLE
            }else if (dateList!!.get(index).isHoliday == Holiday.WORK){
                var holiday = view?.findViewById<TextView>(R.id.calendarHolidayStatus)
                holiday?.setText("班")
                holiday?.setTextColor(holidayTipTextColor)
                holiday?.visibility = View.VISIBLE

            }else{
                var holiday = view?.findViewById<TextView>(R.id.calendarHolidayStatus)
                holiday?.visibility = View.GONE
            }

            view?.setOnClickListener(OnClickListener {
                Log.e("日志","农历情况为："+Gson().toJson(dateList?.get(index)))

                if (!enableItemClick){
                    return@OnClickListener
                }
                if (oldDateItem == view){
                    return@OnClickListener
                }
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