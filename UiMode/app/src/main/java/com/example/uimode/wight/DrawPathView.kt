package com.example.uimode.wight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.uimode.R

/**
 * drawPath画图
 */
class DrawPathView : View {

     private val paint = Paint()

    constructor(context: Context):super(context){
        initData()
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        initData()
    }

    constructor(context: Context,attributeSet: AttributeSet,  defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initData()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    /**
     * 初始化数据
     */
    private fun initData() {
        paint.style = Paint.Style.STROKE
        paint.color = resources.getColor(R.color.black)
        paint.strokeWidth = 2.0f
        paint.isAntiAlias = true

        var path = Path()
        //path.rLineTo()
    }

}