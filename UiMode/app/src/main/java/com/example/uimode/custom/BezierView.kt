package com.example.uimode.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * 贝塞尔曲线
 */
class BezierView :View {

    var paint = Paint()
    var mWidth = 0.0f
    var mHeight = 0.0f

    constructor(context: Context):super(context){
        initData()

    }

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){
        initData()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initData()
    }

    private fun initData() {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5.0f

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var path = Path()
        canvas?.translate(mWidth/2,mHeight/2)
        canvas?.scale(1.0f,-1.0f);
        path.cubicTo(-10.0f,300.0f, 150.0f,-100.0f,300.0f,150.0f)
        canvas?.drawPath(path,paint)

    }

}