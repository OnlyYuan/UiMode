package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivitySpiderBinding

class SpiderActivity : AppCompatActivity() {

    var tags = arrayListOf("星期一","星期二,","星期三","星期四","星期五","星期六","星期天")
    private var pourScoreList = arrayListOf(8.0f,5.0f,10.0f,8.0f,6.0f,1.0f,11.0f)

    private lateinit var mBinding:ActivitySpiderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_spider)

        mBinding.spider.setTags(tags)
        mBinding.spider.setAbilityValue(pourScoreList)

    }
}