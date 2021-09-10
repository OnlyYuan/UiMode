package com.example.uimode.wight.interpolator

import android.view.animation.Interpolator
import java.lang.Math.*
import kotlin.math.pow

/**
 * spring 的Interpolator
 */
class SpringInterpolator(var factor: Float):Interpolator {

    override fun getInterpolation(input: Float): Float {
        val data =  2.0.pow(-10 * input.toDouble()) * kotlin.math.sin((input - factor / 4) * (2 * PI) / factor) + 1
        return  data.toFloat()
    }
}