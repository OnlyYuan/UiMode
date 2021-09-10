package com.example.uimode.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * 蜘蛛网格图(数据按顺时针方向)
 * @author cpf
 *
 */
class SpiderView : View {

    private lateinit var paint:Paint
    private lateinit var scorePaint:Paint
    private lateinit var scoreStrokePaint:Paint
    private lateinit var textPaint:Paint
    //宽度
    private  var mWidth = 0
    //高度
    private  var mHeight = 0
    //分栏的个数
    private  var count = 6

    //一条线的长度
    private  var maxLine = 300
    //层数
    private  var circleNum = 3
    //每个角度
    private var mAngle = Math.PI*2/count
    //间距
    private var mDivideLine = 0.0f
    //当前的距离
    private  var mDistance = 0.0f

    private var mTags = arrayListOf("击杀","生存","助攻","防御","金钱","物理")

    //最大分数，用于计算能力值的大小的参照
    private var maxScore = 10

    //能力值分数list  :和Tags对应
    private var pourScoreList = arrayListOf(8.0f,5.0f,10.0f,8.0f,6.0f,1.0f)

    constructor(context: Context):super(context){
        initData()
    }

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){
        initData()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initData()
    }


    private fun initData(){

        count = mTags.size
        mAngle = Math.PI*2/count

        paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 3.0f
        paint.style = Paint.Style.STROKE
        paint.alpha = 300

        scorePaint = Paint()
        scorePaint.color = Color.BLUE
        scorePaint.strokeWidth = 3.0f
        scorePaint.alpha = 100
        scorePaint.style = Paint.Style.FILL_AND_STROKE

        scoreStrokePaint = Paint()
        scoreStrokePaint.color = Color.RED
        scoreStrokePaint.strokeWidth = 3.0f
        scoreStrokePaint.style = Paint.Style.STROKE

        textPaint = Paint()
        textPaint.color = Color.BLACK
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 30.0f

        mDivideLine = maxLine/circleNum.toFloat()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(mWidth/2.toFloat(),mHeight/2.toFloat())
        //canvas?.scale(1.0f,-1.0f);
        val path = Path()
        Log.i("111","-->xmm")

        for (i in 1..circleNum){

            path.reset()
            path.moveTo(mDivideLine*i,0.0f)
            for (j in 0..count){

                path.lineTo(mDivideLine*i* cos(mAngle*j).toFloat(),mDivideLine*i*sin(mAngle*j).toFloat())
            }
            canvas?.drawPath(path,paint)
        }
        drawDiagonal(canvas)
        drawTextTag(canvas)
        drawPourImg(canvas)
    }


    /**
     * 画对角线
     */
    private fun drawDiagonal(canvas: Canvas?){

        for ( i in 0..count){
            canvas?.drawLine(0.0f,0.0f,maxLine* cos(mAngle*i).toFloat(),maxLine*sin(mAngle*i).toFloat(),paint)
        }

    }


    /**
     * 画角的定点的文字
     */
    private  fun drawTextTag(canvas: Canvas?){

        var fontMetrics = textPaint.fontMetrics
        var fontHeight = fontMetrics.descent - fontMetrics.ascent
        for (i in 0 until mTags.size){

            val dis = textPaint.measureText(mTags[i]) //文本长度
            if(mAngle*i>=0 && mAngle*i<= PI/2){ //第一象限
                canvas?.drawText(mTags[i], (fontHeight+maxLine)* cos(mAngle*i).toFloat(),(fontHeight+maxLine)*sin(mAngle*i).toFloat(),textPaint)

            }else if (mAngle*i> PI/2 && mAngle*i<= PI) {//第二象限
                canvas?.drawText(mTags[i], (fontHeight+maxLine)* cos(mAngle*i).toFloat() - dis,(fontHeight+maxLine)*sin(mAngle*i).toFloat(),textPaint)

            }else if(mAngle*i>PI && mAngle*i<= PI*3/2){//第三象限
                canvas?.drawText(mTags[i], (fontHeight+maxLine)* cos(mAngle*i).toFloat() - dis,(fontHeight+maxLine)*sin(mAngle*i).toFloat(),textPaint)

            }else{
                canvas?.drawText(mTags[i], (fontHeight+maxLine)* cos(mAngle*i).toFloat() ,(fontHeight+maxLine)*sin(mAngle*i).toFloat(),textPaint)
            }


        }

    }


    /**
     * 画能力图
     */
    private  fun drawPourImg(canvas: Canvas?){
        var path1 = Path()

        path1.moveTo(maxLine*(pourScoreList[0]/maxScore)* cos(mAngle*0).toFloat()
                ,maxLine*(pourScoreList[0]/maxScore)* sin(mAngle*0).toFloat())

        for (i in 0 until pourScoreList.size ){
            path1.lineTo(maxLine*(pourScoreList[i]/maxScore)* cos(mAngle*i).toFloat()
                    ,maxLine*(pourScoreList[i]/maxScore)* sin(mAngle*i).toFloat())
            Log.i("11","---> maxLine${maxLine} score${pourScoreList[i]}  角度${cos(mAngle*i)}")
            Log.i("11","----> ${maxLine*(pourScoreList[i]/maxScore)* cos(mAngle*i)} ${maxLine*(pourScoreList[i]/maxScore)* kotlin.math.sin(mAngle * i)}")
        }
        path1.close()
        canvas?.drawPath(path1,scorePaint)
        canvas?.drawPath(path1,scoreStrokePaint)
    }


    /**
     * 设置能力值
     * @param tPourScoreList 能力的list
     */
    fun setAbilityValue(tPourScoreList:ArrayList<Float>){
        pourScoreList.clear()
        pourScoreList.addAll(tPourScoreList)
        invalidate()
    }

    /**
     * 设置指标的值
     * @param tags 指标值的list
     */
    fun  setTags(tags:ArrayList<String>){
        count = tags.size
        mAngle = Math.PI*2/count
        mTags.clear()
        mTags.addAll(tags)
        invalidate()
    }

    /**
     * 设置能力的最大值的参考
     * @param score 参照的最大能力值
     */
    fun setMaxScore(score:Int){
        maxScore = score
        invalidate()
    }

    /**
     * 设置最大的圈数层级
     * @circleNum  圈数的层级 （有多少圈）
     */
    fun  setCircleLayerNum(circleNum:Int){
        this.circleNum = circleNum
        invalidate()
    }

    /**
     * 设置单个能力项的最大长度
     * @param maxLine 单个能力线的最大长度
     */
    fun setMaxLine(maxLine:Int){
        this.maxLine = maxLine
        invalidate()
    }

}