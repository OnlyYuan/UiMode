package com.example.uimode.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.uimode.R


class MyDialogFragment() : DialogFragment() {
    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mView = View.inflate(context,R.layout.dialog_fragment,null)
        return mView
    }

    override fun onStart() {
        super.onStart()
        val win = dialog?.window
        win?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dm = resources.displayMetrics
        val params = win?.attributes
        params?.gravity = Gravity.CENTER
        params?.width = dm.widthPixels*2/3
        params?.height =  dm.heightPixels*2/3
        win?.attributes= params
    }

}