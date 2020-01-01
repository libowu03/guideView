package com.test.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.kit.ActivityUiFrame.fragment.MainUiDefaultFragment
import com.kit.ActivityUiFrame.listener.MainUiMenuItemClickListener
import com.kit.ActivityUiFrame.listener.TabClickListener
import com.kit.ActivityUiFrame.bean.TabContent
import kotlinx.android.synthetic.main.activity_main_ui_frame.*

class MainUiFrameActivity : AppCompatActivity() {

    private var list: MutableList<TabContent> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui_frame)
        list = mutableListOf()
        list?.add(TabContent(MainUiDefaultFragment(), "冬月").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(CustomFragment(), "腊月").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(CalendarFragment(), "日历").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        mainui.setFragmentsList(list)
        mainui.setMainUiMenuItemClickListener(object: MainUiMenuItemClickListener {
            override fun onClick(item: MenuItem?): Boolean {
                Toast.makeText(applicationContext,"点击",Toast.LENGTH_SHORT).show()
                return false
            }

        })
        mainui.setTabClickListener(object : TabClickListener {


            override fun onPageScrollStateChanged(i: Int) {

            }

            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                if (i1 == 0) {
                    if (i == 1){
                        Log.e("日志","掘金")
                        (list!!.get(1).fragment as CustomFragment).showDialog()
                    }
                }
            }

            override fun onPageSelected(i: Int, tabContent: TabContent?, currentView: View?, tabContents: MutableList<TabContent>?) {
                //Log.e("日志","内容为："+tabContent?.tabName)
            }

        })
        mainui.setCurrentIndex(2)
    }
}
