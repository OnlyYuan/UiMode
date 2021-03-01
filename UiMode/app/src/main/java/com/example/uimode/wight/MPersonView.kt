package com.example.uimode.wight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.uimode.R
import com.example.uimode.activity.tree.wight.CombinedBaseView

class MPersonView: LinearLayout {

   lateinit var textView:TextView

    constructor(context: Context) : super(context){
        var view = View.inflate(context,R.layout.activity_member_item,this)
        textView = view.findViewById<TextView>(R.id.member_status)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    /**
     * 设置文字
     */
    fun setText(text:String){

        textView.text = text
    }

}