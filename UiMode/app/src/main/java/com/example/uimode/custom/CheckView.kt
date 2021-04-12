package com.example.uimode.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.uimode.R

class CheckView : View {

    var mWidth = 0
    var mHeight = 0
    private lateinit var okBitmap:Bitmap
    private lateinit var paint: Paint
    //当前页码
    private var animCurrentPage = -1
    // 总页数
    private var animMaxPage = 13
    // 动画时长
    private var animDuration = 500


    private var handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.i("111","--->222")
            when(msg.what){

                1 -> {
                    Log.i("111","--->333")
                    if ( animCurrentPage< animMaxPage){
                        invalidate()
                        animCurrentPage++
                        Log.i("111","--->")
                        this.sendEmptyMessageDelayed(1,animDuration/animMaxPage.toLong())
                    }

                }

            }
        }
    }


    constructor(context:Context):super(context){
        initData()

    }

    constructor(context:Context , attrs: AttributeSet):super(context,attrs){
        initData()
    }

    constructor(context: Context,attributeSet: AttributeSet,  defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initData()
    }

    private fun initData(){
        okBitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.zombie)

        paint = Paint()
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //移动到画布中央
        canvas?.translate(mWidth/2.toFloat(),mHeight/2.toFloat())
        //背景
        canvas?.drawCircle(0.0f,0.0f,200.0f,paint)

        //得出图像的宽高
        var sideLength = okBitmap.height
        var sideWidth = okBitmap.width

        //得到图像选区
        var src = Rect(sideLength*animCurrentPage,0,sideLength*(animCurrentPage+1),sideLength)
        //实际绘制位置
        var dst = Rect(-200,-200,200,200)

        //绘制
        canvas?.drawBitmap(okBitmap,src,dst,null)

    }

    /**
     * 选择中的时候
     */
    public fun onCheck(){

        animCurrentPage = 0
       handler.sendEmptyMessageDelayed(1,animDuration/animMaxPage.toLong())
        Log.i("111","--->onCheck")
    }

    /**
     * 取消时特效
     */
    public fun unCheck(){


    }

}