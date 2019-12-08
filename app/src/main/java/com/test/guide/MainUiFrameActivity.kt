package com.test.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kit.ActivityUiFrame.MainUiDefaultFragment
import com.kit.ActivityUiFrame.MainUiFrameActivity
import com.kit.ActivityUiFrame.TabContent
import kotlinx.android.synthetic.main.activity_main_ui_frame.*

class MainUiFrameActivity : AppCompatActivity() {

    private var list: MutableList<TabContent> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui_frame)
        list = mutableListOf()
        list?.add(TabContent(MainUiDefaultFragment(),"0"))
        list?.add(TabContent(MainUiDefaultFragment(),"1"))
        list?.add(TabContent(MainUiDefaultFragment(),"2"))
        list?.add(TabContent(MainUiDefaultFragment(),"3"))
        list?.add(TabContent(MainUiDefaultFragment(),"4"))
        mainui.setFragmentsList(list)
    }
}
