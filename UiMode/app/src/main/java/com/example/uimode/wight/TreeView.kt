package com.example.uimode.wight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.uimode.R
import com.example.uimode.activity.tree.ui.FamilyMemberView
import com.example.uimode.mode.treemode.TreeNode
import com.wanggang.familytree.dp

class TreeView : ViewGroup {
    //treeView的宽高
    private var viewWidth =0
    private var viewHeight =0
    var mNodeList:MutableList<TreeNode> = ArrayList()
    //屏幕最大宽度
    private var viewMaxWidth =0
    var paint:Paint = Paint()

    constructor(context: Context):super(context){
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
            context,
            attributeSet,
            defStyleAttr
    ){
        init()
    }


    private fun init(){
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1f.dp
        paint.color = resources.getColor(R.color.black)
        paint.isAntiAlias = true
        setWillNotDraw(false);
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var count = childCount

        for (i in 0 until count) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }

        Log.i("11","屏幕的宽度$viewMaxWidth")
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var left = 0
        var right = 0
        var top = 10
        var bottom = 0

        for (i in 0 until childCount){

            left = mNodeList[i].point.x - getChildAt(i).measuredWidth
            right = mNodeList[i].point.x
            top = mNodeList[i].point.y - (getChildAt(i).measuredHeight+30)
            bottom = mNodeList[i].point.y

            if (viewMaxWidth<right) {
                viewMaxWidth = right
            }
            (getChildAt(i) as MPersonView).layout(left,top,right,bottom)

        }
    }

    /**
     * 添加view
     */
   // fun showUI(list: ArrayList<String>){
    fun showUI(nodeList:MutableList<TreeNode>){
        mNodeList.clear()
        mNodeList.addAll(nodeList)
        //清屏
        removeAllViews()
        for (i in 0 until nodeList.size){

            var view=MPersonView (context)
            view.setText(nodeList[i].name)

            addView(view)
        }
        postInvalidate()

       //requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.i("11","--->执行了ondraw")
        drawLine( canvas,paint,mNodeList[mNodeList.size-1]);
        //Log.i("11","--->${mNodeList[mNodeList.size-1]}")
    }

    /**
     * 画线
     */
    private fun drawLine(mCanvas: Canvas?,mPaint: Paint,treeNode: TreeNode?){
        treeNode?.let {

            if (treeNode.children.size>0){
                for (element in treeNode.children) {
                    mCanvas?.drawLine((treeNode.point.x).toFloat() - getChildAt(0).measuredWidth/2-10,
                            (treeNode.point.y).toFloat(),
                            (element.point.x).toFloat() - getChildAt(0).measuredWidth/2,
                            (element.point.y).toFloat() - getChildAt(0).measuredHeight-30,
                            paint)
                    drawLine(mCanvas, mPaint, element)
                }
            }
        }

    }

}