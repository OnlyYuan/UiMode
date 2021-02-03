package com.example.uimode.activity.tree.mode;


import com.example.uimode.activity.tree.wight.TreePoint;

import java.util.List;

public class FamilyMemberModel {

    //内容
    public TreeNodeEntity treeNodeEntity;

    //子节点
    public List<FamilyMemberModel> childs;

    //兄弟节点
    public List<FamilyMemberModel> borthers;

    //所属层次
    public  int  level =0;

    // 显示位置的中心点坐标
    public TreePoint centerPoint = null;


    public TreeNodeEntity getTreeNodeEntity() {
        return treeNodeEntity;
    }

    public void setTreeNodeEntity(TreeNodeEntity treeNodeEntity) {
        this.treeNodeEntity = treeNodeEntity;
    }

    public List<FamilyMemberModel> getChilds() {
        return childs;
    }

    public void setChilds(List<FamilyMemberModel> childs) {
        this.childs = childs;
    }

    public List<FamilyMemberModel> getBorther() {
        return borthers;
    }

    public void setBorther(List<FamilyMemberModel> borthers) {
        this.borthers = borthers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TreePoint getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(TreePoint centerPoint) {
        this.centerPoint = centerPoint;
    }

}

