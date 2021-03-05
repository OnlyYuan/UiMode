package com.example.uimode.wight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.uimode.R
import com.wanggang.familytree.dp

class GroupLayoutView :LinearLayout{
   private lateinit var mLi:LinearLayout

    constructor(context: Context) : super(context){
        var view = View.inflate(context, R.layout.group_layout_view,this)
        mLi = view.findViewById<LinearLayout>(R.id.m_li)



        // view.setOnClickListener()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet,defStyleAttr:Int) : super(context, attributeSet,defStyleAttr)

     fun setView(){
         var view2 = MPersonView(context)
         var view3 = MPersonView(context)

         val lp = LayoutParams(40.dp,60.dp)
         lp.setMargins(10.dp, 0, 0, 0) // 设置间距

         view2.setText("2")
         view3.setText("3")
         view2.layoutParams = lp
         view3.layoutParams = lp

         mLi.addView(view2)
         mLi.addView(view3)

     }

}