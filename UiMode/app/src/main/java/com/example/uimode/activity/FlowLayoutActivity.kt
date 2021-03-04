package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uimode.R
import com.example.uimode.wight.FlowLayout

class FlowLayoutActivity : AppCompatActivity() {

    lateinit var flowLayout:FlowLayout
    var tips = arrayListOf("ni hao  a "," jintian zhen","1111111111111111111",":121231231231","123213213213","0000000000000000000000"
            ,"2222222222222222222","33333333333333333","444444444444444444")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhedie)
        flowLayout = findViewById(R.id.flowLayout)

        flowLayout.showUI(tips)
        linsener()
    }

    private fun linsener() {
        flowLayout.setOnChildItemListener( object :FlowLayout.OnChildItemListener{
            override fun onClick(postion: Int) {
                Log.i("111","点击的位置 ${postion} 当前的值：${tips[postion]}")
            }

        })

    }
}