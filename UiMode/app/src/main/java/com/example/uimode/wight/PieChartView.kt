package com.example.uimode.wight

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wanggang.familytree.dp

/**
 * 饼状图view
 * @author cpf
 */
class PieChartView : View {
    private var paint:Paint = Paint()
    private var paint1:Paint = Paint()
    private var paint2:Paint = Paint()
    private var sweepAngle1 = 0.0f
    private var sweepAngle2 = 0.0f
    private var sweepAngle3 = 0.0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr:Int) : super(context, attributeSet,defStyleAttr)


    init {
        paint.color = Color.RED
        paint.strokeWidth = 50.0f
        //填充
        paint.style = Paint.Style.FILL


        paint1.color = Color.BLUE
        paint1.strokeWidth =  50.0f
        //填充
        paint1.style = Paint.Style.FILL

        paint2.color = Color.GREEN
        paint2.strokeWidth =  50.0f
        //填充
        paint2.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var rect = RectF(100.0f,100.0f,500.0f,500.0f)
        canvas?.drawArc(rect,0.0f,sweepAngle1,true,paint)
        canvas?.drawArc(rect,sweepAngle1+10,sweepAngle2,true,paint1)
        canvas?.drawArc(rect,sweepAngle2+sweepAngle1+10,sweepAngle3,true,paint2)

    }

    /**
     * 更新數據
     */
    public  fun update1(mSweepAngle:Float){
        sweepAngle1 = mSweepAngle
        invalidate()
    }

    /**
     * 更新數據
     */
    public  fun update2(mSweepAngle:Float){
        sweepAngle2 = mSweepAngle
        invalidate()
    }

    /**
     * 更新數據
     */
    public  fun update3(mSweepAngle:Float){
        sweepAngle3 = mSweepAngle
        invalidate()
    }


}