package com.example.uimode.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.alibaba.fastjson.JSON
import com.example.uimode.R
import com.example.uimode.mode.treemode.TreeGroupNode
import com.example.uimode.mode.treemode.TreeNode
import com.example.uimode.wight.TreeView
import com.google.gson.Gson
import com.wanggang.familytree.dp

class TreeActivity : Activity() {

    var mName = arrayListOf<String>("团队1")
//    private var twoNodes = arrayOf(arrayOf("张三", "张三兄弟"), arrayOf("李四"), arrayOf("王五", "王五兄弟"), arrayOf("狗蛋"))
//    private var twoNodes2 = arrayOf(arrayOf("张三1", "李四1", "王五1"), arrayOf("王五2", "李四2", "王五2", "niuniu"),arrayOf("张三3", "李四3"), arrayOf("张三4", "李四4", "王五4"))
    var twoNodes = arrayOf(arrayOf("张三", "张三兄弟"), arrayOf("李四"))
    var twoNodes2 = arrayOf(arrayOf("张三1"), arrayOf("王五2", "李四2") )

    //节点的list
    private var groupNodeList: MutableList<TreeGroupNode> = ArrayList()
    lateinit var treeView: TreeView
    lateinit var treeNode: TreeNode
    lateinit var treeGroupNode: TreeGroupNode
    var screenWidth = 0  //屏幕的宽
    var screenHeight = 0 //屏幕的高


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)

        initData()
        groupNodeList.clear()
        setCoordinate(treeGroupNode)
        for (element in groupNodeList){

            Log.i("11"," x = ${element.treeGroupPoint.x}  y = ${element.treeGroupPoint.y}")
        }
        Log.i("11"," ---> list大小${groupNodeList.size}")
        treeView = findViewById<TreeView>(R.id.treeView)
        treeView.showUI(groupNodeList,screenWidth,screenHeight)
    }

    private fun initData() {
        treeGroupNode = TreeGroupNode()
        treeGroupNode.groupLevel = 0
        treeGroupNode.groupLevelNum = "0"

        treeNode = TreeNode()
        treeNode.name = "团队1"
        treeNode.level = 0
        treeNode.levelNum = "0"

        treeGroupNode.treeNodeList.add(treeNode)

        for (i in twoNodes.indices) {
            var childGroupNode = TreeGroupNode()
            childGroupNode.groupLevel = 1
            childGroupNode.groupLevelNum = "$i"

            for (j in twoNodes[i].indices) {
                var bodyNode = TreeNode()
                bodyNode.name = twoNodes[i][j]
                bodyNode.level = 1
                bodyNode.levelNum = "$i"
                childGroupNode.treeNodeList.add(bodyNode)
            }

            for (k in twoNodes2.indices) {
                var mChildGroupNode = TreeGroupNode()
                mChildGroupNode.groupLevel = 2
                mChildGroupNode.groupLevelNum = "$k"
                for (l in twoNodes2[k].indices) {
                    var mBodyNode = TreeNode()
                    mBodyNode.name = twoNodes2[k][l]
                    mBodyNode.level = 2
                    mBodyNode.levelNum = "$k、$l"
                    mChildGroupNode.treeNodeList.add(mBodyNode)
                }
                childGroupNode.groupNodeChildren.add(mChildGroupNode)
            }

            treeGroupNode.groupNodeChildren.add(childGroupNode)
        }
        var json = JSON.toJSONString(treeGroupNode)
        Log.i("2222","1--->${json}")
    }


    private val nodeWidth = 30.dp
    private val nodeHeight = 80.dp
    //加号的所占的宽度
    private val cWidth = 40.dp
    private val nodeSpace = 30.dp
    var drawMinX = 0
    //单个叶子group的宽
    var cMaxX = 0
    /**
     * 计算节点坐标
     */
    private fun setCoordinate(treeGroupNode: TreeGroupNode) {

        if (treeGroupNode.groupNodeChildren.size > 0) {
            var maxX = 0;
            //存在子叶子group，遍历子叶子group
            for (i in 0 until treeGroupNode.groupNodeChildren.size) {

                //递归 进入下一个叶子 group
                setCoordinate(treeGroupNode.groupNodeChildren[i])

                maxX = treeGroupNode.groupNodeChildren[i].treeGroupPoint.x
            }
            var minX = treeGroupNode.groupNodeChildren[0].treeGroupPoint.x

            //记录当前group的坐标（x,y） x的值为最右边的值，y为底部y
            treeGroupNode.treeGroupPoint.x = (minX + maxX + 10.dp) / 2
            treeGroupNode.treeGroupPoint.y = (treeGroupNode.groupLevel + 1) * nodeHeight
            groupNodeList.add(treeGroupNode)

        } else {
            //计算单个叶子group 的宽度
            cMaxX = cWidth + (nodeWidth +1)*treeGroupNode.treeNodeList.size
            drawMinX += cMaxX + nodeSpace
            screenWidth += drawMinX
            var mCountHeight = (treeGroupNode.groupLevel + 1) * nodeHeight
            if (screenHeight<mCountHeight){
                screenHeight = mCountHeight
            }

            treeGroupNode.treeGroupPoint.x = drawMinX
            treeGroupNode.treeGroupPoint.y = mCountHeight
            groupNodeList.add(treeGroupNode)
        }

    }


}




