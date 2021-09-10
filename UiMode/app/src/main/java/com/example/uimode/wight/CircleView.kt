package com.example.uimode.wight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.uimode.R
import com.wanggang.familytree.dp
import kotlin.math.min

/**
 * 简单的自定义view
 * 功能：1. 实现wrap_content
 *      2. 实现padding
 *      3. 支持自定义属性： atts_circle_view.xml  circle_color属性（color）
 */
class CircleView:View {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mR = 0.0f
    var mColor= Color.YELLOW

    constructor(context: Context):super(context){
        initData()
    }

    constructor(context: Context, attrs: AttributeSet):this(context,attrs,0)


    constructor(context: Context, attrs: AttributeSet, defStyleAttr:Int):super(context,attrs,defStyleAttr){
        //TypeArray
        var a = context.obtainStyledAttributes(attrs, R.styleable.CircleView)
        mColor = a.getColor(R.styleable.CircleView_circle_color,Color.YELLOW)
        a.recycle()
        initData()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

        var heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        //设置wrap_content
        if (widthSpecMode == MeasureSpec.AT_MOST&&heightMeasureSpec == MeasureSpec.AT_MOST){
            setMeasuredDimension(200.dp,200.dp)

        }else if (widthSpecMode ==MeasureSpec.AT_MOST){

            setMeasuredDimension(200.dp,heightSpecSize)
        }else if (heightSpecMode == MeasureSpec.AT_MOST){

            setMeasuredDimension(widthSpecSize,200.dp)
        }

    }

    fun initData(){
        paint.color = mColor
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val width = width
        val height = height

        //增加padding功能
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        val paddingTop = paddingTop
        val paddingBottom = paddingBottom

        var mWidth = width-paddingLeft-paddingRight
        var mHeight = height-paddingBottom-paddingTop
        mR = min(mHeight,mWidth)*1.0f

        canvas?.drawCircle(paddingLeft+width/2.0f,paddingTop+height/2.0f,mR/2,paint)

    }

}