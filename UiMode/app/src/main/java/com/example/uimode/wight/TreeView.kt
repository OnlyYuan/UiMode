package com.example.uimode.wight

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.uimode.R
import com.example.uimode.activity.tree.ui.FamilyMemberView

class TreeView : ViewGroup {
    //treeView的宽高
    private var viewWidth =0
    private var viewHeight =0


    constructor(context: Context):super(context)

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    )


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var count = childCount

        for (i in 0 until count) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }
        //先用view的宽高来代替
//        viewWidth = widthMeasureSpec
//        viewHeight = heightMeasureSpec
        Log.i("11","宽：$viewWidth 高：$viewHeight")
        //设置view的大小
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var left = 0
        var right = 0
        var top = 10
        var bottom = 0

        for (i in 0 until childCount){
            left = r/2 - getChildAt(i).measuredWidth/2
            right = r/2 + getChildAt(i).measuredWidth/2
            top = 10
            bottom = top + getChildAt(i).measuredHeight
            (getChildAt(i) as MPersonView).layout(left,top,right,bottom)
        }
    }

    /**
     * 添加view
     */
    fun showUI(list: ArrayList<String>){
        //清屏
        removeAllViews()
        for (i in 0 until list.size){

            var view=MPersonView (context)
            view.setText(list[0])
            addView(view)
        }
        //postInvalidate()
        requestLayout()
    }


}