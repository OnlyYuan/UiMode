package com.example.uimode.wight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import com.example.uimode.R
import com.example.uimode.mode.treemode.TreeGroupNode
import com.example.uimode.mode.treemode.TreeNode
import com.wanggang.familytree.dp

/**
 * @author cpf
 * 关系树自定义view
 */

class TreeView : ViewGroup {
    //treeView的宽高
    private var screenWidth =0
    private var screenHeight =0
    var groupNodeList: MutableList<TreeGroupNode> = ArrayList()
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
        setMeasuredDimension(screenWidth, screenHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        var left = 0
        var right = 0
        var top = 10
        var bottom = 0

        for (i in 0 until childCount){

            left = groupNodeList[i].treeGroupPoint.x - getChildAt(i).measuredWidth
            right = groupNodeList[i].treeGroupPoint.x
            top = groupNodeList[i].treeGroupPoint.y - (getChildAt(i).measuredHeight+15.dp)
            bottom = groupNodeList[i].treeGroupPoint.y

            if (viewMaxWidth<right) {
                viewMaxWidth = right
            }

           (getChildAt(i) as GroupLayoutView).layout(left,top,right,bottom)

        }
        Log.i("11"," childcount------->")
    }

    /**
     * 添加view
     */

    fun showUI(mGroupNodeList: MutableList<TreeGroupNode>, mWidth:Int ,mHeight:Int){
        screenWidth = mWidth
        screenHeight = mHeight
        groupNodeList.clear()
        groupNodeList.addAll(mGroupNodeList)
        //清屏
        removeAllViews()
        Log.i("11","------->xunhuan的大小${mGroupNodeList.size} ")
        for (i in 0 until groupNodeList.size){

            var view = GroupLayoutView(context)
            view.setView(groupNodeList[i])
            addView(view)
        }
        postInvalidate()

      // requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.i("11","--->执行了ondraw")
        drawLine( canvas,paint,groupNodeList[groupNodeList.size-1]);
    }

    /**`
     * 画线
     */
    private fun drawLine(mCanvas: Canvas?,mPaint: Paint,treeGroupNode: TreeGroupNode?){
        treeGroupNode?.let {

            if (treeGroupNode.groupNodeChildren.size>0){
                for (element in treeGroupNode.groupNodeChildren) {
                    mCanvas?.drawLine((treeGroupNode.treeGroupPoint.x).toFloat() - getChildAt(0).measuredWidth/2-10,
                            (treeGroupNode.treeGroupPoint.y).toFloat()-30,
                            (element.treeGroupPoint.x).toFloat() - getChildAt(0).measuredWidth/2,
                            (element.treeGroupPoint.y).toFloat() - getChildAt(0).measuredHeight-30,
                            paint)
                    drawLine(mCanvas, mPaint, element)
                }
            }
        }

    }




}