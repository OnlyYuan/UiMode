package com.example.uimode.activity.tree.ui

import android.view.View
import com.example.uimode.activity.tree.mode.FamilyMemberModel
import com.example.uimode.activity.tree.wight.TreePoint
import com.wanggang.familytree.dp
import com.wanggang.familytree.familytree.LeftRightBoard

import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class FamilyTreeAdapter {

    companion object {
        val itemWidth = 60.dp
        val itemHeight = 80.dp
        val lineSpace = 40.dp
        val colSpace = 40.dp
        val radius = 6.dp
//
//        val itemWidth = 60.dp
//        val itemHeight = 80.dp
//        val lineSpace = 40.dp
//        val colSpace = 40.dp
//        val radius = 6.dp
    }

    // 保存所有节点数据
    var dataList: ArrayList<FamilyMemberModel>? = null

    // 记录每一代的左右边界
    var boardMap: TreeMap<Int, LeftRightBoard>? = null

    // 记录四周边界
    var left = 0
    var top = 0
    var right = 0
    var bottom = 0

    // 控件真实宽度
    var realWidth = 0
    // 控件真实高度
    var realHeight = 0

    init {
        dataList = ArrayList()
        boardMap = TreeMap()
    }

    // 家族树根节点代数
    private val firstLevel = 0

    /** 数据处理
     *  1. 计算添加基线数据节点
     *  2.
     */
    fun dealWithData(familyMemberModel: FamilyMemberModel, call: (() -> Unit)) {
        clear()
        Single.create(SingleOnSubscribe<String> {

            // 计算各个节点的坐标系坐标
            addBaseLineData(familyMemberModel)
            addBaseChildData(familyMemberModel)
            changeCoord()
            it.onSuccess("success")

        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println(dataList)
                call.invoke()
            }, {

            })
    }

    // 坐标转换
    private fun changeCoord() {
        for (familyModel in dataList!!) {
            familyModel.centerPoint!!.calculateCoord(itemWidth, itemHeight, lineSpace, colSpace, left, top)
        }
        realWidth = (right - left + 8) * (itemWidth + colSpace) / 2
        realHeight = (top - bottom + 8) * (itemHeight + lineSpace)
    }

    // 添加基线数据节点
    private fun addBaseLineData(familyMemberModel: FamilyMemberModel) {
        addToList(familyMemberModel)
        familyMemberModel.centerPoint = TreePoint(0, firstLevel - familyMemberModel.level)

        if (familyMemberModel.bothers != null&&familyMemberModel.bother.size>0) {
            var count = familyMemberModel.bother.size
            familyMemberModel.centerPoint!!.hasSpouseNode = true
            familyMemberModel.centerPoint!!.connectType = familyMemberModel.treeNodeEntity.sex
            familyMemberModel.centerPoint!!.botherNum = count
            boardMap?.put(familyMemberModel.level, LeftRightBoard(-count, count))
            if (left > -1) {
                left = -count
            }
            if (right < 1) {
                right += count
            }
        } else {
            familyMemberModel.centerPoint!!.hasSpouseNode = false
            boardMap?.put(familyMemberModel.level, LeftRightBoard(0, 0))
        }

    }

    private fun addBaseChildData(familyMemberModel: FamilyMemberModel) {

        var centerChildModel = getCenterChild(familyMemberModel)
        if (centerChildModel != null) {
            addBaseLineData(centerChildModel)
            centerChildModel.centerPoint?.parentPoint = familyMemberModel.centerPoint
            addBaseChildData(centerChildModel)

            var childSize = familyMemberModel.childs!!.size
            val childIndex = childSize / 2
            // 当前结点左边的兄弟节点
            for (i in childIndex - 1 downTo 0) {
                addLeftChilds(familyMemberModel, familyMemberModel.childs!![i])
            }
            // 当前结点右边的兄弟节点
            for (i in childIndex + 1 until childSize) {
                addRightChilds(familyMemberModel, familyMemberModel.childs!![i])
            }
        }
    }

    // 添加左边的孩子节点
    private fun addLeftChilds(parentModel: FamilyMemberModel, childModel: FamilyMemberModel) {
        if (childModel?.childs == null || childModel.childs!!.isEmpty()) {
            val board = getLevelPonit(childModel.level)
            board.left -= 2
            childModel.centerPoint = TreePoint(board.left, firstLevel - childModel.level)
            if (bottom < childModel.level) {
                bottom = childModel.level
            }
            if (childModel.childs != null) {
                childModel.centerPoint!!.hasSpouseNode = true
                childModel.centerPoint!!.connectType = childModel.treeNodeEntity.sex
                board.left -=  childModel.centerPoint.botherNum
                childModel.centerPoint!!.coordinateX = board.left
                board.left-=childModel.centerPoint.botherNum
            } else {
                childModel.centerPoint!!.hasSpouseNode = false
            }
            if (left > board.left) {
                left = board.left
            }
            boardMap?.put(childModel.level, board)
        } else {
            val childSize = childModel.childs!!.size
            for (i in childSize - 1 downTo 0) {
                addLeftChilds(childModel, childModel.childs!![i])
            }
            val board = getLevelPonit(childModel.level)
            var left =
                (childModel.childs!![0].centerPoint!!.coordinateX + childModel.childs!![childSize - 1].centerPoint!!.coordinateX) / 2
            if (childModel.centerPoint == null) {
                childModel.centerPoint = TreePoint(left, firstLevel - childModel.level)
            } else {
                childModel.centerPoint!!.coordinateX = left
            }

            // 这个地方需要矫正，怎么矫正呢？
            var levelLeft = boardMap?.get(childModel.level)?.left

            if (levelLeft != null) {
                levelLeft -= if (childModel.childs != null) 3 else 2
            }

            if (levelLeft != null && levelLeft < left) {
                // 将当前节点所有孩子节点往左移动 left - levelLeft - 2 位
                moveChilds(childModel, levelLeft - left)
                board.left = levelLeft
            }

            board.left = left
            if (childModel.childs != null) {
                childModel.centerPoint!!.hasSpouseNode = true
                childModel.centerPoint!!.connectType = childModel.treeNodeEntity.sex
                board.left-=childModel.centerPoint.botherNum
            } else {
                childModel.centerPoint!!.hasSpouseNode = false
            }
            if (this.left > board.left) {
                this.left = board.left
            }
            boardMap?.put(childModel.level, board)
        }
        if (parentModel.centerPoint == null) {
            parentModel.centerPoint = TreePoint(0, firstLevel - parentModel.level)
            if (parentModel.childs != null) {
                parentModel.centerPoint!!.hasSpouseNode = true
                parentModel.centerPoint!!.connectType = parentModel.treeNodeEntity.sex
//                boardMap?.put(parentModel.level, LeftRightBoard(-1, 1))
            } else {
                parentModel.centerPoint!!.hasSpouseNode = false
//                boardMap?.put(parentModel.level, LeftRightBoard(0, 0))
            }
        }
        childModel.centerPoint?.parentPoint = parentModel.centerPoint
        addToList(childModel)
    }

    // 添加右边的孩子节点
    private fun addRightChilds(parentModel: FamilyMemberModel, childModel: FamilyMemberModel) {
        if (childModel?.childs == null || childModel.childs!!.isEmpty()) {
            val board = getLevelPonit(childModel.level)

            board.right += 2
            childModel.centerPoint = TreePoint(board.right, firstLevel - childModel.level)
            if (bottom < childModel.level) {
                bottom = childModel.level
            }
            if (childModel.childs != null) {
                childModel.centerPoint!!.hasSpouseNode = true
                childModel.centerPoint!!.connectType = childModel.treeNodeEntity.sex
                board.right+=childModel.centerPoint.botherNum
                childModel.centerPoint!!.coordinateX = board.right
                board.right++
            } else {
                childModel.centerPoint!!.hasSpouseNode = false
            }

            if (right < board.right) {
                right = board.right
            }
            boardMap?.put(childModel.level, board)
        } else {
            val childSize = childModel.childs!!.size
            for (i in 0 until childSize) {
                addRightChilds(childModel, childModel.childs!![i])
            }
            val board = getLevelPonit(childModel.level)
            var right =
                (childModel.childs!![0].centerPoint!!.coordinateX + childModel.childs!![childSize - 1].centerPoint!!.coordinateX) / 2
            if (childModel.centerPoint == null) {
                childModel.centerPoint = TreePoint(right, firstLevel - childModel.level)
            } else {
                childModel.centerPoint!!.coordinateX = right
            }

            // 这个地方需要矫正，怎么矫正呢？
            var levelRight = boardMap?.get(childModel.level)?.right

            if (levelRight != null) {
                levelRight += if (childModel.childs != null) 3 else 2
            }

            if (levelRight != null && levelRight > right) {
                // 将当前节点所有孩子节点往右移动 levelRight + right 位
                moveChilds(childModel, levelRight - right)
                right = levelRight
            }

            board.right = right
            if (childModel.childs != null) {
                childModel.centerPoint!!.hasSpouseNode = true
                childModel.centerPoint!!.connectType = childModel.treeNodeEntity.sex
                board.right+=childModel.centerPoint.botherNum
            } else {
                childModel.centerPoint!!.hasSpouseNode = false
            }
            if (this.right < board.right) {
                this.right = board.right
            }
            boardMap?.put(childModel.level, board)
        }
        if (parentModel.centerPoint == null) {
            parentModel.centerPoint = TreePoint(0, firstLevel - parentModel.level)
            if (parentModel.childs != null) {
                parentModel.centerPoint!!.hasSpouseNode = true
                parentModel.centerPoint!!.connectType = parentModel.treeNodeEntity.sex
            } else {
                parentModel.centerPoint!!.hasSpouseNode = false
            }
        }
        childModel.centerPoint?.parentPoint = parentModel.centerPoint
        addToList(childModel)
    }

    /**
     * 移动当前节点以及所有子节点
     * */
    private fun moveChilds(parentModel: FamilyMemberModel, step: Int) {
        parentModel.centerPoint!!.coordinateX += step
        var board = boardMap?.get(parentModel.level)
        if (board == null) {
            board = LeftRightBoard(0, 0)
        }
        if (step < 0) {
            board?.left = parentModel.centerPoint!!.coordinateX
            if (left > board.left) {
                left = board.left
            }
        } else {
            board?.right = parentModel.centerPoint!!.coordinateX
            if (right < board.right) {
                right = board.right
            }
        }
        boardMap?.put(parentModel.level, board)
        if (parentModel.childs != null && parentModel.childs!!.isNotEmpty()) {
            for (member in parentModel.childs!!) {
                moveChilds(member, step)
            }
        }
    }

    private fun addToList(memberModel: FamilyMemberModel) {
        dataList?.add(memberModel)
    }

    /**
     * 通过代数获取左右边界
     * */
    private fun getLevelPonit(level: Int): LeftRightBoard {
        var board: LeftRightBoard? = boardMap?.get(level)
        if (board == null) {
            board = LeftRightBoard(0, 0)
        }
        return board
    }

    /**
     * 获取中间孩子节点
     * */
    private fun getCenterChild(familyMemberModel: FamilyMemberModel): FamilyMemberModel? {
        if (familyMemberModel?.childs != null && familyMemberModel.childs!!.isNotEmpty()) {
            var size = familyMemberModel.childs!!.size
            return familyMemberModel.childs!![size / 2]
        }
        return null
    }

    fun getPersonView(personView: PersonView?, parent: View, familyMemberModel: FamilyMemberModel): PersonView {
        var pView = personView
        if (pView == null) {
            pView = PersonView.getPersonView(parent.context)
        }
        pView!!.familyMemberModel = familyMemberModel
        return pView
    }

    fun clear() {
        dataList?.clear()
        boardMap?.clear()
        left = 0
        top = 0
        right = 0
        bottom = 0
    }
}