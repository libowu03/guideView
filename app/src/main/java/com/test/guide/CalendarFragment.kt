package com.test.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.kit.calendar.view.CalendarView
import com.kit.calendar.bean.DateInfo
import com.kit.calendar.listener.DateItemClickListener
import com.kit.calendar.listener.DatePagerChangeListener
import com.kit.calendar.listener.DateSetListener
import com.kit.calendar.utils.Lunar
import kotlinx.android.synthetic.main.calendar_fragment.*

class CalendarFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calendar_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        calendar.setItemClickListener(object : DateItemClickListener{
            override fun onDateItemClickListener(currentView: View, dateItem: DateInfo, dateList: MutableList<DateInfo>, index: Int) {
                Log.e("日志","时间戳为："+dateItem.year)
            }
        })
       /* calendar.getHeadView().findViewById<Button>(R.id.jump).setOnClickListener {
            calendar.jumpToDate(2150,2)
        }*/
        calendar.setDatePagerChangeListener(object : DatePagerChangeListener{
            override fun onDatePagerChange(year: Int, month: Int, dateList: MutableList<DateInfo>, pagerIndex: Int) {
                Log.e("日志","滑动-year:"+year+",month-:"+month)
            }
        })
     /*   calendar.setDateSetListener(object : DateSetListener{
            override fun onDateSetListener(view: View, dateItem:DateInfo ,dateList: MutableList<DateInfo>, index: Int) {
                var day = view.findViewById<TextView>(R.id.day)
                var festival = view.findViewById<TextView>(R.id.holiday)
                day.setText("${dateItem.day}")
                festival.setText("${dateItem.month}")
            }
        })*/

    }

}