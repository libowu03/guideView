package com.test.guide

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.kit.calendar.CalendarView
import com.kit.calendar.bean.DateInfo
import kotlinx.android.synthetic.main.calendar_fragment.*

class CalendarFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.calendar_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        calendar.setOnDateItemClickListener(object : CalendarView.OnDateItemClickListener{
            override fun dateItemClickListener(index: Int, currentView: View, dateInfo: DateInfo) {
                Log.e("日志",dateInfo.weekCn[0]+"农历月份为："+dateInfo.lunar[1]+",农历日为："+dateInfo.lunar[2])
            }
        })
        Log.e("日志","时间戳为："+calendar.getTodayDateInfo()?.timeMillis)
    }

}