package com.example.uimode.mode.treemode

/**
 *  结构组的节点（类比部门）
 */
class TreeGroupNode {

    //子节点
    var groupNodeChildren : MutableList<TreeGroupNode> = ArrayList()

    //内部成员节点
    var treeNodeList : MutableList<TreeNode> = ArrayList()

    //当前层级
    var groupLevel = 0

    //当前编号
    var groupLevelNum = "0"

    //节点
    var treeGroupPoint = TreeGroupPoint()

}