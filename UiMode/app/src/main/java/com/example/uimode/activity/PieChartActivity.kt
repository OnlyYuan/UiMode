package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityPieChartBinding

/**
 * 饼状图
 * @author cpf
 */
class PieChartActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityPieChartBinding
    private var text:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_pie_chart)

        mBinding.btn.setOnClickListener(){

            text =  mBinding.edit.text.toString()
            mBinding.pieView.update1(text.toFloat())
        }

        mBinding.btn1.setOnClickListener(){

            text =  mBinding.edit1.text.toString()
            mBinding.pieView.update2(text.toFloat())
        }

        mBinding.btn2.setOnClickListener(){

            text =  mBinding.edit2.text.toString()
            mBinding.pieView.update3(text.toFloat())
        }


    }
}