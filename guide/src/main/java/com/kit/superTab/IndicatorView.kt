package com.kit.superTab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.kit.guide.utils.GuideViewUtils

class IndicatorView : View{
    private lateinit var paint:Paint
    private var everyIndicatorWidth:Float = 0f
    private var everyIndicatorHeight:Float = 0f
    private var indicatorLeft:Float = 0f

    constructor(context: Context):this(context,null){

    }

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0){

    }

    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
       initView()
    }

    //初始化布局
    private fun initView() {
        //初始化画笔
        paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = GuideViewUtils.dip2px(context,10f).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawRect(indicatorLeft,0f,everyIndicatorWidth,everyIndicatorHeight.toFloat(),paint)
    }

    /**
     * 设置指示器宽度，设置完后重新绘制界面
     * @width 指示器宽度
     * @height 指示器高度
     */
    open fun setEveryIndicatorWidth(width:Float,height:Float){
        this.everyIndicatorWidth = width
        this.everyIndicatorHeight = height
        invalidate()
    }

    /**
     * 更新指示器的位置
     * @left 指示器左边距离
     */
    open fun updateIndicatorPosition(left:Float){
        this.indicatorLeft = left
        invalidate()
    }

}