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
        list?.add(TabContent(MainUiDefaultFragment(),"首页").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(MainUiDefaultFragment(),"运势").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(MainUiDefaultFragment(),"测算").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(MainUiDefaultFragment(),"大师").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        list?.add(TabContent(MainUiDefaultFragment(),"我的").setTabIcon(R.mipmap.my_off).setTabSelectIcon(R.mipmap.my_on))
        mainui.setFragmentsList(list)
    }
}
