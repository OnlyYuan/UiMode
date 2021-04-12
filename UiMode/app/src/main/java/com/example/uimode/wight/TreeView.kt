package com.example.uimode.wight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.children
import com.example.uimode.R
import com.example.uimode.mode.TreeItemPosition
import com.example.uimode.mode.treemode.TreeGroupNode
import com.example.uimode.mode.treemode.TreeNode
import com.wanggang.familytree.dp
import kotlin.math.max

/**
 * @author cpf
 * 关系树自定义view
 */

class TreeView : ViewGroup {
    //treeView的宽高
    private var screenWidth =0
    private var screenHeight =0
    private var groupNodeList: MutableList<TreeGroupNode> = ArrayList()
    //屏幕最大宽度
    private var viewMaxWidth =0
    private var paint:Paint = Paint()
    var currentScale = 0.0f
    var listSize =0

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

        //currentScale = max(1.0f*screenWidth/widthMeasureSpec,1.0f*screenHeight/heightMeasureSpec)
        Log.i("11","屏幕的宽度$viewMaxWidth ---->currentScale${currentScale}")
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
        listSize = groupNodeList.size
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

    /**
     * 画线
     */
    private fun drawLine(mCanvas: Canvas?,mPaint: Paint,treeGroupNode: TreeGroupNode?){
        treeGroupNode?.let {

            if (treeGroupNode.groupNodeChildren.size>0){
                for (i in treeGroupNode.groupNodeChildren.indices) {
                    treeGroupNode.groupNodeChildren.size
                   // mCanvas?.drawLine((treeGroupNode.treeGroupPoint.x).toFloat() - getChildAt(0).measuredWidth/2-15.dp,
                    mCanvas?.drawLine((treeGroupNode.treeGroupPoint.x).toFloat() -  (treeGroupNode.groupNodeChildren.size*25.dp/2),
                            (treeGroupNode.treeGroupPoint.y).toFloat()-15.dp,
                            (treeGroupNode.groupNodeChildren[i].treeGroupPoint.x).toFloat() - getChildAt(0).measuredWidth/2,
                            (treeGroupNode.groupNodeChildren[i].treeGroupPoint.y).toFloat() - getChildAt(0).measuredHeight-15.dp,
                            paint)
                    drawLine(mCanvas, mPaint, treeGroupNode.groupNodeChildren[i])
                }
            }
        }
    }


    /**
     * 获取当前子view的
     */
    fun  getChildrenList():MutableList<GroupLayoutView>{
        var childrenList = ArrayList<GroupLayoutView>()
        childrenList.clear()
        for (i in 0 until childCount){
            childrenList.add(getChildAt(i) as GroupLayoutView)
        }
        return childrenList
    }


    /**
     * 获取当前子view的坐标
     */
    fun getChildPositionList():MutableList<TreeItemPosition>{
        var mItemPositionList = ArrayList<TreeItemPosition>()
        for (i in 0 until childCount){
            var treeItemPosition = TreeItemPosition()
            treeItemPosition.text = "-->当前坐标是${(getChildAt(i) as TreeGroupNode).groupLevel}  ${(getChildAt(i) as TreeGroupNode).groupLevelNum}"
            treeItemPosition.left = getChildAt(i).left
            treeItemPosition.right = getChildAt(i).right
            treeItemPosition.top = getChildAt(i).top
            treeItemPosition.bottom = getChildAt(i).bottom

            mItemPositionList.add(treeItemPosition)
        }

        return mItemPositionList
    }

}