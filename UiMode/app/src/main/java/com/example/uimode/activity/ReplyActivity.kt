package com.example.uimode.activity

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.annotation.RequiresApi
import com.example.uimode.R

class ReplyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply)


    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initData() {
        val htmlString = ""

    }
}