package com.example.uimode.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uimode.R


class MainMenuAdapter(val context: Context, var array: Array<String>): RecyclerView.Adapter<MainMenuAdapter.ViewHolder>() {


    //第一步 定义接口
    interface OnItemListener {
        fun onClick(position: Int)
    }

    private var listener: OnItemListener? = null

    //第二步， 写一个公共的方法
    fun setOnItemClickListener(listener: OnItemListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return array.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = array[position]
        holder.itemView.setOnTouchListener { _, event ->
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> listener!!.onClick(position)
            }
            false
        }

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemName:TextView = itemView.findViewById(R.id.itemName)


    }

}