package com.test.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.listener.DateItemClickListener
import com.kit.calendar.listener.DatePagerChangeListener
import com.kit.calendar.listener.DateSetListener
import com.kit.calendar.utils.CalendarNetUtils
import kotlinx.android.synthetic.main.calendar_fragment.*

class CalendarFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calendar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         CalendarNetUtils.getHolidayAndFestival(activity?.application,false)

        //42宫格点击事件监听
        calendar.setItemClickListener(object : DateItemClickListener {
            override fun onDateItemClickListener(currentView: View, dateItem: DateInfo, dateList: MutableList<DateInfo>, index: Int, oldView: View?) {

            }
        })

        //日期切换时的监听器
        calendar.setDatePagerChangeListener(object : DatePagerChangeListener {
            override fun onDatePagerChange(year: Int, month: Int, dateList: MutableList<DateInfo>, pagerIndex: Int) {

            }
        })

        //42宫格设置数据时的监听，每个宫格设置完数据后会调用回调此监听器，可在这个方法中进行一些自定义样式设置。
        // 如果已经设置了42宫格自定义样式，这个方法一定要调用，调用此监听器填充数据给自定义布局
        calendar.setDateSetListener(object : DateSetListener {
            override fun onDateSetListener(view: View, dateItem: DateInfo, dateList: MutableList<DateInfo>, index: Int) {

            }
        })
       /*   //跳转到某一个日期，这里是跳转到2150年的6月份
          calendar.jumpToDate(2150,6)
          //跳转到下一个月
          calendar.nextMonth()
          //跳转到上一个月
          calendar.preMonth()
          //跳转到下一年
          calendar.nextYear()
          //跳转到上一年
          calendar.preYear()
          //获取头部布局
          calendar.getHeadView()
          //获取尾部布局
          calendar.getFootView()
          //获取今日的日期信息
          calendar.getTodayDateInfo()*/
    }

}