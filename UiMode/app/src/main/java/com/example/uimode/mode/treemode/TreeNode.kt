package com.example.uimode.mode.treemode

/**
 * 树的节点
 */
 class TreeNode {

    //名字
    var name:String = ""

    //图片地址
     var urlString:String = ""

    //兄弟节点
     var bothers :MutableList<TreeNode> = ArrayList()

    //子节点
     var children :MutableList<TreeNode> = ArrayList()

    //当前层级
     var level:Int = 0

    //当前层级的编号
     var levelNum = "0"

    //节点
     var point = TreePoint()

    //下面节点的个数
     var pointCount = 0


}