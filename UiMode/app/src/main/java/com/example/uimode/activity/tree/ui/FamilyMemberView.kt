package com.example.uimode.activity.tree.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.uimode.R
import com.example.uimode.activity.tree.mode.FamilyMemberModel
import com.example.uimode.activity.tree.wight.CombinedBaseView


class FamilyMemberView : CombinedBaseView {

    var familyMemberModel: FamilyMemberModel? = null

    override fun layoutResource(): Int {
        return R.layout.layout_member_view
    }

    override fun onCreate(context: Context?) {

    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    private fun init() {

    }

    /**
     * 设置自己在父控件中的位置
     * 男左女右
     * */
    fun layoutSelf() {
        if (familyMemberModel == null) {
            return
        }
        var x = familyMemberModel!!.centerPoint!!.screenX
        var y = familyMemberModel!!.centerPoint!!.screenY
        var t = y - FamilyTreeAdapter.itemHeight / 2
        var b = y + FamilyTreeAdapter.itemHeight / 2
        if (familyMemberModel!!.centerPoint!!.hasSpouseNode) {

            var l = x - FamilyTreeAdapter.itemWidth - FamilyTreeAdapter.colSpace / 2
            var r = x + FamilyTreeAdapter.itemWidth + FamilyTreeAdapter.colSpace / 2

            layout(l, t, r, b)

            val manOptions = RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(100, 100)
                .centerCrop()
            val womanOptions = RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(100, 100)
                .centerCrop()

            if (familyMemberModel!!.treeNodeEntity!!.sex == 1) {
                findViewById<LinearLayout>(R.id.memberLayout).findViewById<TextView>(R.id.tvTitle).text = familyMemberModel!!.treeNodeEntity.name
                Glide.with(context).load(familyMemberModel!!.treeNodeEntity.picUrlString)
                    .apply(manOptions)
                    .into(findViewById<LinearLayout>(R.id.memberLayout).findViewById(R.id.ivHead))

                findViewById<LinearLayout>(R.id.spouseLayout).findViewById<TextView>(R.id.tvTitle).text = familyMemberModel!!.treeNodeEntity!!.name
                Glide.with(context).load(familyMemberModel!!.borthers[0].treeNodeEntity.picUrlString).apply(womanOptions)
                    .into(findViewById<LinearLayout>(R.id.spouseLayout).findViewById(R.id.ivHead))
            } else {
                findViewById<LinearLayout>(R.id.spouseLayout).findViewById<TextView>(R.id.tvTitle).text = familyMemberModel!!.treeNodeEntity.name
                Glide.with(context).load(familyMemberModel!!.treeNodeEntity.picUrlString).apply(womanOptions)
                    .into(findViewById<LinearLayout>(R.id.spouseLayout).findViewById(R.id.ivHead))

                findViewById<LinearLayout>(R.id.memberLayout).findViewById<TextView>(R.id.tvTitle).text = familyMemberModel!!.treeNodeEntity!!.name
                Glide.with(context).load(familyMemberModel!!.borthers[0].treeNodeEntity.picUrlString).apply(manOptions)
                    .into(findViewById<LinearLayout>(R.id.memberLayout).findViewById(R.id.ivHead))
            }
        } else {
            var l = x - FamilyTreeAdapter.itemWidth / 2
            var r = x + FamilyTreeAdapter.itemWidth / 2
            findViewById<LinearLayout>(R.id.spouseLayout).visibility = View.GONE
            findViewById<View>(R.id.lineView).visibility = View.GONE

            layout(l, t, r, b)

            findViewById<LinearLayout>(R.id.memberLayout).findViewById<TextView>(R.id.tvTitle).text = familyMemberModel!!.treeNodeEntity.name
            var defaultRes = if (familyMemberModel!!.treeNodeEntity.sex == 1) R.mipmap.ic_launcher else R.mipmap.ic_launcher
            val options = RequestOptions()
                .placeholder(defaultRes)
                .error(defaultRes)
                .override(100, 100)
                .centerCrop()
            Glide.with(context).load(familyMemberModel!!.treeNodeEntity.picUrlString).apply(options)
                .into(findViewById<LinearLayout>(R.id.memberLayout).findViewById(R.id.ivHead))
        }
    }

    // 绘制路径
    fun drawPath(canvas: Canvas, paint: Paint, percent: Float) {
        var centerPoint = familyMemberModel!!.centerPoint
        if (centerPoint!!.parentPoint != null) {
            centerPoint.drawConnection()
            canvas.drawPath(centerPoint.getSegment(percent)!!, paint)
        }
    }

}