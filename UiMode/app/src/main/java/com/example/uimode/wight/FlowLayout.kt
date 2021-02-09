package com.example.uimode.wight

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.uimode.mode.TargetPostion

class FlowLayout : ViewGroup {

    constructor(context: Context):super(context)

    //constructor(context: Context， attrs:AttributeSet):super(context,attrs)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var count = childCount

        for (i in 0 until count)
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var left = 0
        var right = 0
        var top = 0
        var maxWidth = r

        var count = childCount

        if(count!=0) {

            for (i in 0 until count) {
                var view = getChildAt(i)
                right = left + view.measuredWidth
                if (right > maxWidth) {//超过当前行
                    left = 0
                   // right = left + view.measuredWidth
                    top += view.measuredHeight
                }

                right = left + view.measuredWidth

                getChildAt(i).layout(left, top, right, top + view.measuredHeight)
                left += view.measuredWidth+20

            }
        }
    }

    /**
     * 添加view
     */
     fun showUI(list: ArrayList<String>){
        for (i in 0 until list.size-1){
            var textView = TreeModeView(context)
            textView.setNameView(list[i])
            addView(textView)
            textView.setOnClickListener {
                listener.onClick(i)
            }
        }
        postInvalidate()
    }

    /**
     * 接口
     */
    interface OnChildItemListener {
            fun onClick(postion:Int)
    }

    lateinit var listener:OnChildItemListener
    /**
     * 暴露点击函数
     */
    public fun setOnChildItemListener(listener:OnChildItemListener){

        this.listener =listener

    }

}