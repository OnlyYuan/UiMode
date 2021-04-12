package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import com.example.uimode.R
import com.example.uimode.fragment.MyDialogFragment

class DialogFragmentActivity : AppCompatActivity() {

    val list = arrayListOf("1234","2334","12567","67")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_fragment)
        val  mBtn = findViewById<Button>(R.id.mBtn)
//        mBtn.setOnClickListener {
//            initDialog()
//        }
       var mList =  list.filter {

            "12" in it
        }
        Log.i("11","大小-->${mList.size}")
        for (e in mList){

            Log.i("11","-->${e}")
        }

    }

//    private fun initDialog() {
//        val dialog = MyDialogFragment()
//        dialog.show(supportFragmentManager,"dialog")
//    }


}