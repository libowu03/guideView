package com.kit.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kit.guide.R
import kotlinx.android.synthetic.main.calendar_view.view.*

/**
 * 日历控件
 * @author libowu
 * @date 2019/12/22
 * 冬至撸代码，别有一番风味
 */
class CalendarView : LinearLayout {

    constructor(context:Context) : this(context,null)

    constructor(context: Context?, nothing: AttributeSet?) : this(context,nothing,0)

    constructor(context: Context?, nothing: AttributeSet?,def:Int?) : super(context,nothing,0) {
        initView()
    }


    /**
     * 初始化布局
     */
    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.calendar_view,this,true)
        calendarHead.addView(LayoutInflater.from(context).inflate(R.layout.calendar_head,this,false))
        for(index in 0..6){
            calendarLineOne.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
        for(index in 0..6){
            calendarLineTwo.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
        for(index in 0..6){
            calendarLineThree.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
        for(index in 0..6){
            calendarLineFour.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
        for(index in 0..6){
            calendarLineFive.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
        for(index in 0..6){
            calendarLineSix.addView(LayoutInflater.from(context).inflate(R.layout.calendar_view_item_date,this,false))
        }
    }
}