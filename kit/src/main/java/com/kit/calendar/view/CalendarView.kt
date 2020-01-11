package com.kit.calendar.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.kit.calendar.adapter.CalendarRecAdapter
import com.kit.calendar.bean.CalendarAttribute
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.listener.DateItemClickListener
import com.kit.calendar.listener.DatePagerChangeListener
import com.kit.calendar.listener.DateSetListener
import com.kit.calendar.listener.PagerListener
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
    private lateinit var pager: PagerSnapHelper
    private lateinit var adapter: CalendarRecAdapter
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
    private var enableCalendarScroll : Boolean = true


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
    private var footDefaultFestivalTextSize = 12
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
    //点击日期时的背景
    private var itemClickBackgroundColor:Int = 0
    private var itemClickBackground:Drawable ?= null

    //点击监听器
    var clickListener:DateItemClickListener ?= null
    //滑动监听器
    var pagerChangeListener:DatePagerChangeListener ?= null
    //今天日期的index
    private var currentDateIndex : Int = 0
    //日期组件的属性
    var attrubute:CalendarAttribute ?= null
    //每项日期设置完后的监听器
    var dateItemSetListener:DateSetListener ?= null
    //上一次的点击view
    var oldClickView:View ?= null
    //普通dateitem的background
    var itemBackground:Drawable ?= null

    //当前页面的月份
    var currentPagerMonth = 0
    var currentPagerYear = 0

    object Holiday{
        //节日
        const val HOLIDAY : Int = 1
        //放假后的补班
        const val WORK : Int = 0
        //普通日期，即不放假也不补班
        const val COMMON_DAY : Int = -1
        //日期属性
        var ATTRIBUTE : CalendarAttribute ?= null
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
        footDefaultFestivalTextSize = GuideViewUtils.dip2px(context,12f)
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
        dateItemLayout = typedArray.getResourceId(R.styleable.CalendarView_dateItemLayout, R.layout.calendar_view_item_date)
        holidayTipTextSize = typedArray.getDimensionPixelSize(R.styleable.CalendarView_holidayTipTextSize, 8)
        holidayTipTextColor = typedArray.getColor(R.styleable.CalendarView_holidayTipTextColor, Color.RED)
        selectTodayDayTextColor = typedArray.getColor(R.styleable.CalendarView_selectTodayDayTextColor, Color.WHITE)
        selectTodayFestivalTextColor = typedArray.getColor(R.styleable.CalendarView_selectTodayFestivalTextColor, Color.WHITE)
        enableItemClick = typedArray.getBoolean(R.styleable.CalendarView_enableItemClick, true)
        var workDayTipTextColor = typedArray.getColor(R.styleable.CalendarView_workDayTipTextColor, Color.GREEN)
        enableCalendarScroll = typedArray.getBoolean(R.styleable.CalendarView_enableCalendarScroll, true)
        itemClickBackground = typedArray.getDrawable(R.styleable.CalendarView_enableCalendarScroll)
        if (itemBackground == null){
            itemClickBackgroundColor = typedArray.getColor(R.styleable.CalendarView_enableCalendarScroll,resources.getColor(R.color.colorAccent))
        }
        var weekBarLayout = typedArray.getResourceId(R.styleable.CalendarView_weekBarLayout,R.layout.calendar_week)

        typedArray.recycle()
        attrubute = CalendarAttribute(dateDayTextSize,
                dateFestivalTextSize,
                notCurrentMonthDayTextColor,
                notCurrentMonthFestivalTextColor,
                currentMonthDayTextColor,
                currentMonthFestivalTextColor,
                headWeekTextColor,
                headWeekTextSize,
                dateItemLayout,
                holidayTipTextSize,
                holidayTipTextColor,
                selectTodayDayTextColor,
                selectTodayFestivalTextColor,enableItemClick,workDayTipTextColor,weekBarLayout)
        Holiday.ATTRIBUTE = attrubute
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

        calendarViewContent.addOnScrollListener(PagerListener(pager,object : PagerListener.OnPageChangeListener {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            }

            override fun onPageSelected(position: Int) {
                var date = adapter.title.get(position).split("-")
                calendarYearTextTv?.text = "${date[0]}"
                calendarMonthTextTv?.text = "${date[1]}"

                var maxYear = adapter.title.get(adapter.title.size-1).split("-")[1].toInt()
                var minYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
                //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
                if( ((maxYear - date[0].toInt()) < 30 || (date[0].toInt() - minYear) < 30)) {
                    jumpToDate(date[0].toInt(),date[1].toInt())
                }

                currentPagerMonth = date[1].toInt()
                currentPagerYear = date[0].toInt()
                pagerChangeListener?.let {
                    pagerChangeListener!!.onDatePagerChange(date[0].toInt(),date[1].toInt(),CalendarUtils.getDayOfMonthList(date[0].toInt(), date[1].toInt()),position)
                }

                oldClickView?.background = itemBackground
            }

        }))
    }

    /**
     * 初始化适配器
     */
    fun initAdapter(){
        adapter = CalendarRecAdapter(attrubute)
        adapter.setClickListener(object : DateItemClickListener{
            override fun onDateItemClickListener(currentView: View, dateItem: DateInfo, dateList: MutableList<DateInfo>, index: Int,oldView: View?) {
                setDefaultCalendarFootInfo(dateItem)
                if (oldClickView != currentView){
                    oldClickView?.background = currentView.background
                    itemBackground = currentView.background
                    if (itemClickBackground != null){
                        currentView.background = itemBackground
                    }else{
                        //如果itemClickBackground为空，默认使用drawable为背景而不是纯色作为背景，如果在itemClickBackgroundColor不等于默认颜色时，说明调用者已经设置过颜色，则使用纯色作为背景
                        if (itemClickBackgroundColor == resources.getColor(R.color.colorAccent)){
                            currentView.setBackgroundResource(R.drawable.calendar_select_bg)
                        }else{
                            currentView.setBackgroundColor(itemClickBackgroundColor)
                        }
                    }
                }
                clickListener?.onDateItemClickListener(currentView,dateItem,dateList,index,oldClickView)
                oldClickView = currentView
            }
        })
        adapter.setDateSetListener(object : DateSetListener{
            override fun onDateSetListener(custonView: View, dateItem:DateInfo, dateList: MutableList<DateInfo>, index: Int) {
                dateItemSetListener?.onDateSetListener(custonView,dateItem,dateList,index)
            }
        })

        var calendarViewTitle = mutableListOf<String>()
        for (year in 1900..2100){
            for (month in 1..12){
                if (year == cal!!.get(Calendar.YEAR) && month == cal!!.get(Calendar.MONTH)+1){
                    currentDateIndex = (year - 1900)*12 + month-1
                }
                calendarViewTitle.add("${year}-${month}")
            }
        }
        adapter.setTitle(calendarViewTitle)

        var manager: LinearLayoutManager
        if (enableCalendarScroll){
            manager = LinearLayoutManager(context)
        }else{
            manager = object : LinearLayoutManager(context){
                override fun canScrollHorizontally(): Boolean {
                    return false
                }

                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        }
        manager.orientation = LinearLayoutManager.HORIZONTAL
        calendarViewContent.layoutManager = manager
        calendarViewContent.adapter = adapter
        pager = PagerSnapHelper()
        pager.attachToRecyclerView(calendarViewContent)
        calendarViewContent.scrollToPosition(currentDateIndex)
    }

    /**
     * 初始化布局
     */
    private fun initView() {
        currentPagerMonth = cal!!.get(Calendar.MONTH)+1
        currentPagerYear = cal!!.get(Calendar.YEAR)
        LayoutInflater.from(context).inflate(R.layout.calendar_view, this, true)
        initAdapter()

        //设置周一至周日的字体颜色及大小
        calendarWeekBar?.let{
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
            var headView = LayoutInflater.from(context).inflate(headLayout, this, false);
            if (headView == null){
                calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head, this, false))
                headLayout = R.layout.calendar_head
            }else{
                calendarHead.addView(headView)
            }

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
            var footView = LayoutInflater.from(context).inflate(R.layout.calendar_foot, this, false)
            if (footView == null){
                calendarFoot.addView(LayoutInflater.from(context).inflate(footLayout, this, false))
                footLayout = R.layout.calendar_foot
            }else{
                calendarFoot.addView(LayoutInflater.from(context).inflate(R.layout.calendar_foot, this, false))
                setDefaultCalendarFootInfo(getTodayDateInfo()!!)
            }

        } else {
            calendarFoot.addView(LayoutInflater.from(context).inflate(footLayout, this, false))
        }

        if (!enableHeadLayout) {
            hideHeadView()
        }
        if (!enableFootLayout) {
            hideFootView()
        }
    }


    /**
     * 设置点击监听
     */
    fun setItemClickListener(clickListener: DateItemClickListener){
        this.clickListener = clickListener
    }

    /**
     * 设置点击监听
     */
    fun setDateSetListener(dateSetListener: DateSetListener){
        this.dateItemSetListener = dateSetListener
    }

    /**
     * 设置日历滑动监听器
     */
    fun setDatePagerChangeListener(pagerChangeListener: DatePagerChangeListener){
        this.pagerChangeListener = pagerChangeListener
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            //标准情况下满屏宽度时使用16的字体是合适的，就用16在全宽下的比例来计算最合适的字体大小(只在屏幕宽度与当前画布宽度不同时调用)
            if (context.resources.displayMetrics.widthPixels == canvas.width || isInEditMode){
                isAuthorSetTextSize = true
                return
            }
            var percentage = GuideViewUtils.dip2px(context,16f) / SUITABLE_WIDTH
            var percentageFestival = GuideViewUtils.dip2px(context,8f) / SUITABLE_WIDTH
            var percentageHoliday = GuideViewUtils.dip2px(context,8f) / SUITABLE_WIDTH
            var percentageWidth = GuideViewUtils.dip2px(context,8f) / SUITABLE_WIDTH
            if (!isAuthorSetTextSize && headLayout ==0 && dateItemLayout == 0 && footLayout == 0){
                isAuthorSetTextSize = true
                if (dateDayTextSize ==16 && dateFestivalTextSize == 10 && headWeekTextSize == 16){
                   dateDayTextSize = (canvas.width * percentage).toInt()
                   dateFestivalTextSize = (canvas.width * percentageFestival).toInt()
                   headWeekTextSize = (canvas.width * percentage).toInt()
                   holidayTipTextSize = (canvas.width * percentageHoliday).toInt()
                }

                //设置头部字体
                if (headLayout == 0 || isInEditMode){
                    var percentage = resources.getDimensionPixelSize(R.dimen.titleTwo_16) / SUITABLE_WIDTH
                    var percentage10 = resources.getDimensionPixelSize(R.dimen.titleOne_10) / SUITABLE_WIDTH
                    var headDate = (canvas.width * percentage).toInt()
                    var headLunarDate = (canvas.width * percentage10).toInt()
                    var headLayout10 = (canvas.width * percentage10).toInt()
                    calendarHeadTime.setTextSize(TypedValue.COMPLEX_UNIT_PX,headDate.toFloat())
                    calendarHeadFestival.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarHeadBackToTodayTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarYearTextTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarMonthTextTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarYearPre.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarYearNext.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarMonthNext.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarMonthPre.setTextSize(TypedValue.COMPLEX_UNIT_PX,headLunarDate.toFloat())
                    calendarBox.setPadding(headLayout10,headLayout10,headLayout10,headLayout10)
                }


                //设置尾部
                if (footLayout == 0 || isInEditMode){
                    var percentageFootTitle = resources.getDimensionPixelSize(R.dimen.titleTwo_12) / SUITABLE_WIDTH
                    var percentageFootContent = resources.getDimensionPixelSize(R.dimen.titleTwo_12) / SUITABLE_WIDTH
                    var percentageFootBox = GuideViewUtils.dip2px(context,110f) / SUITABLE_HEIGHT
                    var title12 = (canvas.width * percentageFootTitle).toInt()
                    var title16 = (canvas.width * percentageFootContent).toInt()
                    var titleBox = (canvas.width * percentageFootBox).toInt()
                    footDefaultFestivalTextSize = title16
                    calendarFootLunarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,title12.toFloat())
                    calendarFootFestivalTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,title12.toFloat())
                    calendarFootSolaTerms.setTextSize(TypedValue.COMPLEX_UNIT_PX,title12.toFloat())
                    calendarFootDate.setTextSize(TypedValue.COMPLEX_UNIT_PX,title16.toFloat())
                    calendarFootSolarTerms.setTextSize(TypedValue.COMPLEX_UNIT_PX,title16.toFloat())
                    for (index in 0..calendarFootFestival.childCount-1){
                        (calendarFootFestival.getChildAt(index) as TextView).setTextSize(TypedValue.COMPLEX_UNIT_PX,title16.toFloat())
                    }
                    calendarFootBox.layoutParams = calendarFootBox.layoutParams
                    //设置尾部的高度到最适合的大小
                    var lp = calendarFootBox.layoutParams
                    lp.height = titleBox
                    calendarFootBox.layoutParams = lp
                }

                attrubute?.dateDayTextSize = dateDayTextSize
                attrubute?.dateFestivalTextSize = dateFestivalTextSize
                attrubute?.holidayTipTextSize = holidayTipTextSize
                attrubute?.headWeekTextSize = headWeekTextSize
                Log.e("日志","holidayTipTextSize大小为："+holidayTipTextSize)
                adapter.setAttribute(attrubute)
                calendarViewContent.scrollToPosition(currentDateIndex)

                //设置整个日历的padding
                var paddingPrecentage = 10 / (context.resources.displayMetrics.widthPixels*1.0)
                var calendarBoxPadding = (canvas.width * paddingPrecentage).toInt()
                calendarBox.setPadding(calendarBoxPadding,calendarBoxPadding,calendarBoxPadding,calendarBoxPadding)
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
            if (dateInfo?.lunar == null){
                calendarLunar.visibility = View.GONE
            }else{
                calendarLunar.visibility = View.VISIBLE
                calendarFootDate?.setText(dateInfo?.lunar.toString())
            }
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
                                festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
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
                                festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
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
                                festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
                                var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                                lp.marginEnd = GuideViewUtils.dip2px(context,5f)
                                festival.layoutParams = lp
                                festival.setText(itemChild)
                                festival.setTextColor(context.resources.getColor(R.color.colorTitle))
                                calendarFootFestival.addView(festival)
                            }
                        }else{
                            var festival = TextView(context)
                            festival.setTextSize(TypedValue.COMPLEX_UNIT_PX,footDefaultFestivalTextSize.toFloat())
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
     * 获取头部信息
     * @return 头部的view
     */
    fun getHeadView(): View {
        if (calendarHead.childCount != 0){
            return calendarHead.getChildAt(0)
        }else{
            return calendarHead
        }
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
                nextMonth()
                calendarMonthTextTv.setText("${currentPagerMonth}")
                calendarYearTextTv.setText("${currentPagerYear}")
            }
            calendarMonthPre -> {
                preMonth()
                calendarMonthTextTv.setText("${currentPagerMonth}")
                calendarYearTextTv.setText("${currentPagerYear}")
            }
            calendarYearPre -> {
                preYear()
                calendarMonthTextTv.setText("${currentPagerMonth}")
                calendarYearTextTv.setText("${currentPagerYear}")
            }
            calendarYearNext -> {
                nextYear()
                calendarMonthTextTv.setText("${currentPagerMonth}")
                calendarYearTextTv.setText("${currentPagerYear}")
            }
            calendarHeadBackToTodayTv -> {
                //日历默认值(当前时间)
               /* updateDate()*/
                calendarMonthTextTv.setText("${cal!!.get(Calendar.MONTH)+1}")
                calendarYearTextTv.setText("${cal!!.get(Calendar.YEAR)}")
                backToToday()
            }
        }
    }

    /**
     * 返回到今天的日期
     */
    public fun backToToday(){
        jumpToDate(cal!!.get(Calendar.YEAR),cal!!.get(Calendar.MONTH)+1)
        pagerChangeListener?.let {
            pagerChangeListener!!.onDatePagerChange(cal!!.get(Calendar.YEAR),cal!!.get(Calendar.MONTH),CalendarUtils.getDayOfMonthList(cal!!.get(Calendar.YEAR), cal!!.get(Calendar.MONTH)),adapter.title.indexOf("${cal!!.get(Calendar.YEAR)}-${cal!!.get(Calendar.MONTH)}"))
        }
    }

    /**
     * 跳转到下一年
     */
    public fun nextYear(){
        currentPagerYear++

        var maxYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
        var minYear = adapter.title.get(0).split("-")[0].toInt()
        //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
        if( ((maxYear - currentPagerYear) < 30 || (currentPagerYear - minYear) < 30)) {
            jumpToDate(currentPagerYear,currentPagerMonth)
        }else{
            calendarViewContent.scrollToPosition( adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}") )
        }
        pagerChangeListener?.let {
            pagerChangeListener!!.onDatePagerChange(currentPagerYear,currentPagerMonth,CalendarUtils.getDayOfMonthList(currentPagerYear, currentPagerMonth),adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}"))
        }
    }

    /**
     * 跳转到上一年
     */
    public fun preYear(){
        currentPagerYear--
        var maxYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
        var minYear = adapter.title.get(0).split("-")[0].toInt()
        //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
        if( ((maxYear - currentPagerYear) < 30 || (currentPagerYear - minYear) < 30)) {
            jumpToDate(currentPagerYear,currentPagerMonth)
        }else{
            calendarViewContent.scrollToPosition( adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}") )
        }
        pagerChangeListener?.let {
            pagerChangeListener!!.onDatePagerChange(currentPagerYear,currentPagerMonth,CalendarUtils.getDayOfMonthList(currentPagerYear, currentPagerMonth),adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}"))
        }
    }

    /**
     * 跳转到上一个月
     */
    public fun preMonth(){
        if (currentPagerMonth-1<1){
            currentPagerMonth = 12
            currentPagerYear--
        }else{
            currentPagerMonth--
        }
        var maxYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
        var minYear = adapter.title.get(0).split("-")[0].toInt()
        //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
        if( ((maxYear - currentPagerYear) < 30 || (currentPagerYear - minYear) < 30)) {
            jumpToDate(currentPagerYear,currentPagerMonth)
        }else{
            calendarViewContent.scrollToPosition( adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}") )
        }
        pagerChangeListener?.let {
            pagerChangeListener!!.onDatePagerChange(currentPagerYear,currentPagerMonth,CalendarUtils.getDayOfMonthList(currentPagerYear, currentPagerMonth),adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}"))
        }

    }

    /**
     * 跳转到下一个月
     */
    public fun nextMonth(){
        if (currentPagerMonth+1>12){
            currentPagerMonth = 1
            currentPagerYear++
        }else{
            currentPagerMonth++
        }
        var maxYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
        var minYear = adapter.title.get(0).split("-")[0].toInt()
        //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
        if( ((maxYear - currentPagerYear) < 30 || (currentPagerYear - minYear) < 30)) {
            jumpToDate(currentPagerYear,currentPagerMonth)
        }else{
            calendarViewContent.scrollToPosition( adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}") )
        }
        pagerChangeListener?.let {
            pagerChangeListener!!.onDatePagerChange(currentPagerYear,currentPagerMonth,CalendarUtils.getDayOfMonthList(currentPagerYear, currentPagerMonth),adapter.title.indexOf("${currentPagerYear}-${currentPagerMonth}"))
        }
    }

    /**
     * 跳转到指定年月
     */
    public fun jumpToDate(year:Int,month:Int){
        var maxYear = adapter.title.get(adapter.title.size-1).split("-")[0].toInt()
        var minYear = adapter.title.get(0).split("-")[0].toInt()
        //如果该年份已经存在于适配器中且年份不处于边缘值时，直接使用现有的数据，否则重新构造
        if( adapter.title.contains("${year}-${month}") && !((maxYear - year) < 30 || (year - minYear) < 30)){
            var index = adapter.title.indexOf("${year}-${month}")
            calendarViewContent.scrollToPosition(index)
        }else{
            var calendarViewTitle = mutableListOf<String>()
            for (y in year-100..year+100){
                for (m in 1..12){
                    if (y == year && m == month){
                        currentDateIndex = (y - (year-100))*12 + m-1
                        currentPagerMonth = m
                        currentPagerYear = y
                    }
                    calendarViewTitle.add("${y}-${m}")
                }
            }
            adapter.setTitle(calendarViewTitle)
            calendarViewContent.scrollToPosition(currentDateIndex)
        }
    }

    interface OnDateItemClickListener {
        fun dateItemClickListener(index: Int, currentView: View, dateInfo: DateInfo,oldView:View)
    }
}