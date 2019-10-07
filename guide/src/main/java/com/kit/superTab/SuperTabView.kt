package com.kit.superTab

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kit.guide.R
import com.kit.guide.utils.GuideViewUtils
import kotlinx.android.synthetic.main.view_super_tab.view.*
import kotlinx.android.synthetic.main.view_tab.view.*

class SuperTabView : LinearLayout, ViewPager.OnPageChangeListener {
    private var tabTextMargin: Float = 0f
    private var selectTextStyle: Int = 0
    private var selectTextSize: Float = 0f
    private var unselectTextSize: Float = 0f
    private var unselectTextColor: Int = Color.GRAY
    private var selectTextColor: Int = Color.BLACK

    private var tabList:MutableList<SuperTab>?=null
    private var tabViewList:MutableList<View>?=null

    constructor(context: Context):this(context,null){
    }

    constructor(context: Context,attr:AttributeSet?):this(context,attr,0){
    }

    constructor(context: Context, attr: AttributeSet?, defStyleAttr:Int):super(context,attr,defStyleAttr){
        LayoutInflater.from(context).inflate(R.layout.view_super_tab,this,true)
        initArray(attr,defStyleAttr)
    }

    /**
     * 获取界面参数
     */
    private fun initArray(attr: AttributeSet?, defStyleAttr:Int) {
        //获取view基本属性
        var typeArray = context.theme.obtainStyledAttributes(attr,R.styleable.SuperTabView,defStyleAttr,0)
        selectTextColor = typeArray.getColor(R.styleable.SuperTabView_tabSelectTextColor,Color.BLACK)
        unselectTextColor = typeArray.getColor(R.styleable.SuperTabView_tabUnselectTextColor,Color.GRAY)
        unselectTextSize = typeArray.getDimension(R.styleable.SuperTabView_tabUnselectTextSize, 16f)
        selectTextSize = typeArray.getDimension(R.styleable.SuperTabView_tabSelectTextSize,16f)
        selectTextStyle = typeArray.getInt(R.styleable.SuperTabView_tabSelectTextStyle,0)
        tabTextMargin = typeArray.getDimension(R.styleable.SuperTabView_tabTextMargin,GuideViewUtils.dip2px(context,5f).toFloat())
        typeArray.recycle()
    }

    /**
     * 添加tab信息
     * @tab tab的基本信息
     */
    open fun addTab(tab:SuperTab):SuperTabView{
        if (tabViewList == null){
            tabViewList = mutableListOf()
            tabList = mutableListOf()
        }

        var v = LayoutInflater.from(context).inflate(R.layout.view_tab,null)
        var llp = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1f)
        v.layoutParams = llp
        this.superTab.addView(v)
        //添加到集合中去，方便后面viewpager选中时改变选中颜色
        tabViewList!!.add(v)
        tabList!!.add(tab)

        return this
    }

    /**
     * 添加tab
     * @tabList tab集合
     */
    open fun addTabList(tabList:MutableList<SuperTab>){
    if (this.tabList != null && this.tabList!!.size != 0){
            this.tabList!!.clear()
        }
        this.tabList = tabList
        setContent()
    }

    /**
     * 执行界面构造
     */
    open fun buildTab(){
        setContent()
    }

    /**
     * 绘制界面
     */
    private fun setContent() {
        this.superTab.removeAllViews()
        for (tab in tabList!!){
            //设置控件平分
            var v = LayoutInflater.from(context).inflate(R.layout.view_tab,null)
            var llp = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1f)
            v.layoutParams = llp
            this.superTab.addView(v)
            //添加到集合中去，方便后面viewpager选中时改变选中颜色
            tabViewList!!.add(v)
        }
    }

    open fun setupWithViewPager(viewpager:ViewPager){
        //绑定viewpager后执行一遍初始化操作
        for ((index,tab) in tabViewList!!.withIndex()){
            if (viewpager.currentItem == index){
                //设置默认选中tab的样式
                var view = tabViewList!!.get(viewpager.currentItem)
                view.superTabText.setTextColor(selectTextColor)
                view.superTabText.setTextSize(selectTextSize)
                view.superTabText.setText(tabList!!.get(viewpager.currentItem).getText())
                if (selectTextStyle == 0){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
                }else if(selectTextStyle == 1){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
                }else if (selectTextStyle == 2){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC))
                }
                ((view.superTabText.layoutParams)as LinearLayout.LayoutParams).topMargin = tabTextMargin.toInt()
            }else{
                var view = tabViewList!!.get(index)
                view.superTabText.setTextColor(unselectTextColor)
                view.superTabText.setTextSize(unselectTextSize)
                view.superTabText.setText(tabList!!.get(index).getText())
                ((view.superTabText.layoutParams)as LinearLayout.LayoutParams).topMargin = tabTextMargin.toInt()
            }
        }

        viewpager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {
        for ((index,tab) in tabViewList!!.withIndex()){
            if (p0 == index){
                var view = tabViewList!!.get(p0)
                view.superTabText.setTextColor(selectTextColor)
                view.superTabText.setTextSize(selectTextSize)
                if (selectTextStyle == 0){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
                }else if(selectTextStyle == 1){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
                }else if (selectTextStyle == 2){
                    view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC))
                }
            }else{
                var view = tabViewList!!.get(index)
                view.superTabText.setTextColor(unselectTextColor)
                view.superTabText.setTextSize(unselectTextSize)
                view.superTabText.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
            }
        }
    }
}