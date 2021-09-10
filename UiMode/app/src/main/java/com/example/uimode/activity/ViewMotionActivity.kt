package com.example.uimode.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import android.widget.Scroller
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityViewMotionBinding
import com.wanggang.familytree.dp

/**
 * view事件体系
 *      1.VelocityTracker 速度追踪
 *      2.手势监听 GestureDetector
 *      3.Scroller 弹性滑动
 */

class ViewMotionActivity : AppCompatActivity() {
    lateinit var velocityTracker:VelocityTracker
//    var scroller = Scroller(this)
    lateinit var binding:ActivityViewMotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_motion)

        binding.btn.setOnClickListener {
            scrollFun()
        }

        binding.animatorBtn.setOnClickListener {
            animatorFun()
        }
    }

    /**
     * VelocityTracker 速度追踪
     */
    fun  velocityTrackerFun(event: MotionEvent?){
        //1.初始化
        var velocityTracker = VelocityTracker.obtain()
        velocityTracker.addMovement(event)

        //2.获取速度前需要设置时间
        velocityTracker.computeCurrentVelocity(1000)
        var mX = velocityTracker.xVelocity
        var mY = velocityTracker.yVelocity

        Log.i("11","---->x方向得速度：$mX ,Y方向得速度：$mY")

        //3.最后不需要得时候记得回收内存

    }

    /**
     * 回收 VelocityTracker
     */
    fun clearVelocityTracker(){
        velocityTracker.clear()
        velocityTracker.recycle()

    }


    /**
     * GestureDetector手势监听
     */
    fun gestureDetectorFun(){
        var gestureDetector = GestureDetector(this,object :GestureDetector.OnGestureListener{
            override fun onDown(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onShowPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onLongPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

        })
        //解决长按屏幕无法拖动
        gestureDetector.setIsLongpressEnabled(false)

        gestureDetector.setOnDoubleTapListener(object :GestureDetector.OnDoubleTapListener{
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

        })
    }


    /**
     * 弹性滑动
     */
    fun scrollerFun(){




    }


    /**
     *  使用 scrollTo/scrollBy
     */
    fun scrollFun(){
        binding.img.scrollBy(10.dp,10.dp)

    }

    /**
     * 使用属性动画
     */
    fun  animatorFun(){

        var objectSet = AnimatorSet()

        var objectAnimator = ObjectAnimator.ofFloat(binding.img,"translationX",100.0f)
        var objectAnimator2 = ObjectAnimator.ofFloat(binding.img,"translationY",100.0f)
        var objectAnimator3 = ObjectAnimator.ofFloat(binding.img,"alpha",0.0f,1.0f)

        objectSet.play(objectAnimator).with(objectAnimator2).with(objectAnimator3)
        objectSet.duration = 1000
        objectSet.start()

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_DOWN ->{

            }

            MotionEvent.ACTION_MOVE ->{
                velocityTrackerFun(event)
            }

            MotionEvent.ACTION_UP ->{

            }

        }



        return super.onTouchEvent(event)
    }

}