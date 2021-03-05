package com.example.uimode.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.uimode.R
import com.example.uimode.mode.treemode.TreeGroupNode
import com.example.uimode.mode.treemode.TreeNode
import com.example.uimode.mode.treemode.TreePoint
import com.example.uimode.wight.TreeView
import com.wanggang.familytree.dp

class TreeActivity : AppCompatActivity() {
    var mName = arrayListOf<String>("团队1")
    var twoNodes = arrayOf(arrayOf("张三", "张三兄弟"), arrayOf("李四"), arrayOf("王五", "王五兄弟"), arrayOf("狗蛋"))
    var twoNodes2 = arrayOf(arrayOf("张三1", "李四1", "王五1"), arrayOf("王五2", "李四2", "王五2", "niuniu"),
            arrayOf("张三3", "李四3"), arrayOf("张三4", "李四4", "王五4"))
//    var twoNodes2 = arrayOf(arrayOf("张三1", ), arrayOf("王五2", "李四2"),
//            arrayOf("张三3"), arrayOf("张三4","王五4"), arrayOf("张三5", "李四5"), arrayOf("张三6", "6"))
    //节点的list
    //  var nodeList:MutableList<TreeNode> = ArrayList()

    //节点的list
    private var groupNodeList: MutableList<TreeGroupNode> = ArrayList()

    lateinit var treeView: TreeView
    lateinit var treeNode: TreeNode
    lateinit var treeGroupNode: TreeGroupNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree)
        initData()
        groupNodeList.clear()
        setCoordinate(treeGroupNode)
        for (element in groupNodeList){

            Log.i("11"," x = ${element.treeGroupPoint.x}  y = ${element.treeGroupPoint.y}")
        }

        treeView = findViewById<TreeView>(R.id.treeView)
        treeView.showUI(groupNodeList)
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
    }


//        for (i in twoNodes.indices) {
//            var childNode = TreeNode()
//            childNode.level = 1
//            childNode.levelNum = "$i"
//            childNode.name = twoNodes[i]
//            if (i==1){
//                for (k in bothers.indices){
//                    var bother = TreeNode()
//                    bother.level = 1
//                    bother.levelNum = "$i,bother=$k"
//                    bother.name = bothers[k]
//                    childNode.bothers.add(bother)
//                }
//            }
//
//            for (j in twoNodes2[i].indices) {
//                var mChildNode = TreeNode()
//                mChildNode.level = 2
//                mChildNode.levelNum = "$i、$j"
//                mChildNode.name = twoNodes2[i][j]
//                childNode.children.add(mChildNode)
//            }
//
//            treeNode.children.add(childNode)
//        }
//
//    }

    private val nodeWidth = 40
    private val nodeHeight = 60
    //加号的所占的宽度
    private val cWidth = 40
    private val nodeSpace = 10
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

                //计算单个叶子group 的宽度
                // cMaxX = cWidth + (nodeWidth +1)*treeGroupNode.groupNodeChildren[i].treeNodeList.size
                maxX = treeGroupNode.groupNodeChildren[i].treeGroupPoint.x
            }
            var minX = treeGroupNode.groupNodeChildren[0].treeGroupPoint.x

            //记录当前group的坐标（x,y） x的值为最右边的值，y为底部y
            treeGroupNode.treeGroupPoint.x = (minX + maxX + 20) / 2
            treeGroupNode.treeGroupPoint.y = (treeGroupNode.groupLevel + 10) * nodeHeight
            groupNodeList.add(treeGroupNode)

        } else {
            //计算单个叶子group 的宽度
            cMaxX = cWidth + (nodeWidth +10)*treeGroupNode.treeNodeList.size
            drawMinX += cMaxX + nodeSpace
            treeGroupNode.treeGroupPoint.x = drawMinX
            treeGroupNode.treeGroupPoint.y = (treeGroupNode.groupLevel + 10) * nodeHeight
            groupNodeList.add(treeGroupNode)
        }

    }
}



//    /**
//     * 计算节点坐标
//     */
//    private fun  setCoordinate(treeNode: TreeNode) {
//       if (treeNode.children.size>0){
//            var maxX =0;
//            for (i in 0 until treeNode.children.size){
//                setCoordinate(treeNode.children[i])
//                maxX = treeNode.children[i].point.x
//            }
//            var minX = treeNode.children[0].point.x
//            treeNode.point.x = (minX + maxX+40)/2
//            treeNode.point.y = (treeNode.level+1) * nodeHeight
//            nodeList.add(treeNode)
//        }else{
//            drawMinX += nodeWidth
//            treeNode.point.x = drawMinX
//            treeNode.point.y = (treeNode.level+1)*nodeHeight
//            nodeList.add(treeNode)
//        }
//
//    }




