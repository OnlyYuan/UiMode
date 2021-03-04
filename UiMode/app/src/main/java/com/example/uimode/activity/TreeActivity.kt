package com.example.uimode.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.uimode.R
import com.example.uimode.mode.treemode.TreeNode
import com.example.uimode.mode.treemode.TreePoint
import com.example.uimode.wight.TreeView

class TreeActivity : AppCompatActivity() {
    var mName = arrayListOf<String>("团队1")
    var twoNodes = arrayOf("张三", "李四", "王五", "狗蛋", "麻子" ,"小明")
    var twoNodes2 = arrayOf(arrayOf("张三1", "李四1", "王五1"), arrayOf("王五2", "李四2", "王五2", "niuniu"),
            arrayOf("张三3", "李四3"), arrayOf("张三4", "李四4","王五4"), arrayOf("张三5", "李四5"), arrayOf("张三6", "6"))
//    var twoNodes2 = arrayOf(arrayOf("张三1", ), arrayOf("王五2", "李四2"),
//            arrayOf("张三3"), arrayOf("张三4","王五4"), arrayOf("张三5", "李四5"), arrayOf("张三6", "6"))

    //节点的list
    var nodeList:MutableList<TreeNode> = ArrayList()

    lateinit var treeView: TreeView
    lateinit var treeNode: TreeNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        initData()
        nodeList.clear()
        setCoordinate(treeNode)
        for (element in nodeList){

            Log.i("11"," x = ${element.point.x}  y = ${element.point.y}")
        }
        treeView = findViewById<TreeView>(R.id.treeView)
        treeView.showUI(nodeList)
    }

    private fun initData() {
        treeNode = TreeNode()
        treeNode.name = "团队1"
        treeNode.level = 1

        for (i in twoNodes.indices) {
            var childNode = TreeNode()
            childNode.level = 2
            childNode.name = twoNodes[i]
            for (element in twoNodes2[i]) {
                var mChildNode = TreeNode()
                mChildNode.level = 3
                mChildNode.name = element
                childNode.children.add(mChildNode)
            }

            treeNode.children.add(childNode)
        }

    }

     val nodeWidth = 200
     val nodeHeight = 220
     var drawMinX = 0

    /**
     * 计算节点坐标
     */
    private fun setCoordinate(treeNode: TreeNode) {

        if (treeNode.children.size>0){
            var maxX =0;
            for (i in 0 until treeNode.children.size){
                setCoordinate(treeNode.children[i])
                maxX = treeNode.children[i].point.x
            }
            var minX = treeNode.children[0].point.x
            treeNode.point.x = (minX + maxX+40)/2
            treeNode.point.y = treeNode.level * nodeHeight
            nodeList.add(treeNode)
        }else{
            drawMinX += nodeWidth
            treeNode.point.x = drawMinX
            treeNode.point.y = treeNode.level*nodeHeight
            nodeList.add(treeNode)
        }

    }

 }


