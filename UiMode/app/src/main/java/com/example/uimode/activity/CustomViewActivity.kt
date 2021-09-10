package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityCustomViewBinding

/**
 * 自定义view
 *
 */
class CustomViewActivity : AppCompatActivity() {

    lateinit var binding:ActivityCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_custom_view)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
}