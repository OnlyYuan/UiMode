package com.example.uimode.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityAnimatorSpringBinding
import com.example.uimode.wight.interpolator.SpringInterpolator

/**
 * animator 实现spring（弹簧）效果
 * 核心公式：pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1
 */
class AnimatorSpringActivity : AppCompatActivity() {

    lateinit var binding:ActivityAnimatorSpringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_animator_spring)
        myListener()

    }

    private fun myListener() {

        binding.imgBtn.setOnClickListener{
            //alphaAnimationFun()
            tranAnimationFun()
        }

    }

    private fun alphaAnimationFun() {

        var animator  = ObjectAnimator.ofFloat(binding.imgBtn,"alpha",1.0f,0.0f)
        animator.interpolator = SpringInterpolator(0.5f)
        animator.duration = 3000
        animator.start()
    }

    private fun tranAnimationFun() {
        var animator  = ObjectAnimator.ofFloat(binding.imgBtn,"TranslationY",binding.imgBtn.translationY+100f)
        animator.interpolator = SpringInterpolator(0.1f)
        animator.duration = 3000
        animator.start()
    }

}