package com.example.uimode.wight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.uimode.R
import com.example.uimode.mode.treemode.TreeGroupNode
import com.example.uimode.mode.treemode.TreeNode
import com.wanggang.familytree.dp

class GroupLayoutView :LinearLayout{
   private lateinit var mLi:LinearLayout
   private lateinit var btn: ImageView

    constructor(context: Context) : super(context){
        var view = View.inflate(context, R.layout.group_layout_view,this)
        mLi = view.findViewById<LinearLayout>(R.id.m_li)
        btn = view.findViewById(R.id.btn)

        // view.setOnClickListener()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet,defStyleAttr:Int) : super(context, attributeSet,defStyleAttr)

     fun setView(treeGroupNode : TreeGroupNode){

         val lp = LayoutParams(40.dp,60.dp)
         lp.setMargins(10.dp, 0, 0, 0) // 设置间距

         for (element in treeGroupNode.treeNodeList){
             var view = MPersonView(context)
             view.setText(element.name)
             view.layoutParams = lp
             mLi.addView(view)
             view.setOnClickListener(){
                 Toast.makeText(context,element.name,Toast.LENGTH_SHORT).show()
             }
             btn.setOnClickListener(){
                 Toast.makeText(context,"添加 位置${treeGroupNode.groupLevel} , ${treeGroupNode.groupLevelNum}",Toast.LENGTH_SHORT).show()
             }
         }

     }

}