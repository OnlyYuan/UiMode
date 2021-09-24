package com.example.uimode.activity.tree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uimode.R
import com.example.uimode.wight.CircleColorView
import kotlinx.coroutines.newFixedThreadPoolContext
import java.util.concurrent.Executors.newFixedThreadPool

class CircleViewActivity : AppCompatActivity() {

    var colorStringsList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_view)
        colorStringsList.add("#FFB6C1")
        colorStringsList.add("#0000FF")
        colorStringsList.add("#6495ED")
        var mview = findViewById<CircleColorView>(R.id.mView);
        mview.setColorString(colorStringsList);
        val thread = newFixedThreadPool(6)
    }
}