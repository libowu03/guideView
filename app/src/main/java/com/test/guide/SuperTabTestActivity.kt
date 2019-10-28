package com.test.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kit.superTab.SuperTab
import com.kit.superTab.TabSelectListener
import kotlinx.android.synthetic.main.activity_super_tab_test.*

class SuperTabTestActivity : AppCompatActivity(), TabSelectListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_tab_test)
        this.a.addTab(SuperTab().setText("dqq"))
                .addTab(SuperTab().setText("dy"))
                .addTab(SuperTab().setText("dy"))
                .addTab(SuperTab().setText("dqq"))

        val fragments = mutableListOf<Fragment>()
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())
        this.pager.adapter = FslpVpAdapter(supportFragmentManager, arrayOf("测试", "测试", "测试", "测试"),fragments)
        this.pager.currentItem = 2
        this.a.setupWithViewPager(this.pager)
        this.a.setTabSelectListener(this)
    }

    override fun onTabSelect(tab: SuperTab, view: View) {
        Toast.makeText(this,tab.getText()+","+tab.getPosition(),Toast.LENGTH_SHORT).show()
    }
}
