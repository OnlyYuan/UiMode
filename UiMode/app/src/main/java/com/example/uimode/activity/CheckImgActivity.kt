package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityCheckImgBinding

class CheckImgActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityCheckImgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_check_img)

        mBinding.btn.setOnClickListener{

            mBinding.checkView.onCheck()
        }

    }
}