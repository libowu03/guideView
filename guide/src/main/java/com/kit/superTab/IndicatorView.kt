package com.kit.superTab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.kit.guide.utils.GuideViewUtils

/**
 * tab的指示器
 * @author libowu
 * @date 2019/10/10
 */
class IndicatorView : View{
    private lateinit var paint:Paint
    private var everyIndicatorWidth:Float = 0f
    private var everyIndicatorHeight:Float = 0f
    private var indicatorLeft:Float = 0f
    private var isToRight:Boolean = false
    private var viewWidth:Float = 0f
    private var currentPager:Int = 0
    private var indicatorDiatance:Float = 0f
    private var currentPercent:Float = 0f

    constructor(context: Context):this(context,null)

    constructor(context: Context,attributeSet: AttributeSet?):this(context,attributeSet,0)

    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
       initView()
    }

    //初始化布局
    private fun initView() {
        //初始化画笔
        paint = Paint()
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = GuideViewUtils.dip2px(context,10f).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isToRight){
            canvas!!.drawRect(indicatorLeft + indicatorDiatance * currentPercent,0f,(indicatorLeft + indicatorDiatance * currentPercent)+everyIndicatorWidth,height.toFloat(),paint)
        }else{
            if (currentPercent == 0f){
                canvas!!.drawRect(indicatorLeft,0f,indicatorLeft+everyIndicatorWidth,height.toFloat(),paint)
            }else{
                canvas!!.drawRect(indicatorLeft - indicatorDiatance * (1-currentPercent),0f,(indicatorLeft - indicatorDiatance * (1-currentPercent))+everyIndicatorWidth,height.toFloat(),paint)
            }
        }
        //canvas!!.drawLine(indicatorLeft,0f,indicatorLeft+everyIndicatorWidth,0f,paint)
    }

    /**
     * 设置指示器宽度，设置完后重新绘制界面
     * @indicatorWidth 指示器宽度
     * @viewWidth 指示器将要指示view的宽度
     * @indicatorHeight 指示器高度
     * @currentPager 当前页码位置
     */
    open fun setEveryIndicatorWidth(indicatorWidth:Float,viewWidth:Float,currentPager:Int){
        this.currentPager = currentPager + 1
        //如果传入的指示器长度大于view本身，则直接使用view的长度
        var tempIndicatorWidth =  indicatorWidth
        if (tempIndicatorWidth > viewWidth){
            tempIndicatorWidth = viewWidth
        }

        this.everyIndicatorWidth = tempIndicatorWidth
        this.viewWidth = viewWidth

        //计算指示器左边距距离
        indicatorLeft = viewWidth * (this.currentPager) - viewWidth/2 - tempIndicatorWidth/2
        //计算两个指示器之间的距离
        indicatorDiatance = tempIndicatorWidth + (viewWidth-tempIndicatorWidth)
        Log.e("日志","setEveryIndicatorWidth当前页面："+this.currentPager)
        invalidate()
    }

    /**
     * 设置当前指示器位置
     */
    open fun setCurrentPager(currentPager:Int){
        this.currentPager = currentPager + 1
        //计算指示器左边距距离
        indicatorLeft = viewWidth * (this.currentPager) - viewWidth/2 - everyIndicatorWidth/2
        Log.e("日志","setCurrentPager当前页面："+this.currentPager)
        invalidate()
    }

    /**
     * 更新指示器的位置
     * @left 指示器左边距离
     */
    open fun updateIndicatorPosition(position:Float,isToRight:Boolean,currentPager: Int){
        if (position == 0f){
            this.currentPager = currentPager +1
            indicatorLeft = viewWidth * (this.currentPager) - viewWidth/2 - everyIndicatorWidth/2
            currentPercent = 0f
            return
        }
        currentPercent = position
        this.isToRight = isToRight
        invalidate()
    }

}