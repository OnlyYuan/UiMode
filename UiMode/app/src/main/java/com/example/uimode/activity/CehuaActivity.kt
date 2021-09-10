package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.uimode.R
import com.example.uimode.databinding.ActivityCehuaBinding

/**
 * 侧滑菜单
 */
class CehuaActivity : AppCompatActivity() {

    private lateinit var mBinding:ActivityCehuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_cehua)

       // mBinding.drawer.openDrawer(GravityCompat.START)
    }
}