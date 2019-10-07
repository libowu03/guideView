package com.test.guide

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.kit.superTab.SuperTab
import kotlinx.android.synthetic.main.activity_super_tab_test.*

class SuperTabTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_tab_test)
        this.a.addTab(SuperTab().setText("dqq"))
                .addTab(SuperTab().setText("dy"))
                .addTab(SuperTab().setText("dy"))
                .addTab(SuperTab().setText("dqq"))

        var fragments = mutableListOf<Fragment>()
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())
        fragments.add(CustomFragment())


        this.pager.adapter = FslpVpAdapter(supportFragmentManager, arrayOf("测试", "测试", "测试", "测试"),fragments)
        this.a.setupWithViewPager(this.pager)
    }
}
