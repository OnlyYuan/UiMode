package com.example.uimode.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uimode.R
import com.example.uimode.wight.TreeView

class TreeActivity : AppCompatActivity() {
    var mName = arrayListOf<String>("团队1")
    lateinit  var treeView:TreeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        treeView = findViewById<TreeView>(R.id.treeView)
        treeView.showUI(mName)
    }
}