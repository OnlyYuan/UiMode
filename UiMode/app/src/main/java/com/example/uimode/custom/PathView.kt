package com.example.uimode.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View

class PathView: View {

    constructor(context: Context):super(context){
        initData()
    }

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){
        initData()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr:Int):super(context,attributeSet,defStyleAttr){
        initData()
    }

    private fun initData() {


    }
}