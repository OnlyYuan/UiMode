package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.VelocityTracker
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.uimode.R
import com.example.uimode.databinding.ActivityJiaZiGuBinding

/**
 * SpringAnimation 弹簧动画
 */
class JiaZiGuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityJiaZiGuBinding
    private lateinit var springAnimation:SpringAnimation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_jia_zi_gu)
      //  initAnimation()
        initLinsener()
    }

    private fun initLinsener() {

        binding.imgBtn.setOnClickListener {

            //translationAnimation()
            //scaleInit()
            //rotaAnimation()
            alphaFun()
        }
    }

    private fun initAnimation() {
         springAnimation = SpringAnimation(binding.imgBtn,DynamicAnimation.TRANSLATION_Y)
        val iconForce = SpringForce(binding.imgBtn.translationY+100.0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        springAnimation.spring = iconForce
        springAnimation.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_PIXELS
    }

    //平移
    private fun translationAnimation(){
        var mX = binding.imgBtn.x
        var mY = binding.imgBtn.y
        Log.i("11","--->点击前mX：${mX} mY：${mY}")
        initAnimation()
        springAnimation.start()
        Log.i("11","--->点击后mX：${mX} mY：${mY}")
    }

    private fun scaleInit(){
       val scaleAnimation1 = SpringAnimation(binding.imgBtn,DynamicAnimation.SCALE_X)
        val iconForce1 = SpringForce(binding.imgBtn.scaleX+2.0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        scaleAnimation1.spring = iconForce1
        scaleAnimation1.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE

        val scaleAnimation2 = SpringAnimation(binding.imgBtn,DynamicAnimation.SCALE_Y)
        val iconForce2 = SpringForce(binding.imgBtn.scaleY+2.0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_LOW
        }
        scaleAnimation2.spring = iconForce2
        scaleAnimation2.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE

        scaleAnimation1.start()
        scaleAnimation2.start()
    }


    private fun rotaAnimation(){

        val rotationAnimation = SpringAnimation(binding.imgBtn,DynamicAnimation.ROTATION)
        val iconForce1 = SpringForce(binding.imgBtn.rotation+10.0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_VERY_LOW
        }
        rotationAnimation.spring = iconForce1
        rotationAnimation.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_ROTATION_DEGREES
        rotationAnimation.start()
    }

    private fun alphaFun(){

        val alphaAnimation = SpringAnimation(binding.imgBtn,DynamicAnimation.ALPHA)
        val iconForce = SpringForce(binding.imgBtn.rotation+0.0f).apply {
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            stiffness = SpringForce.STIFFNESS_VERY_LOW
        }
        alphaAnimation.spring = iconForce
        alphaAnimation.minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_ALPHA
        alphaAnimation.start()
    }

}