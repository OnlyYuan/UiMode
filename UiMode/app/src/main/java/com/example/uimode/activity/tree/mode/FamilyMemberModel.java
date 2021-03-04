package com.example.uimode.activity.tree.mode;


import com.example.uimode.activity.tree.wight.TreePoint;

import java.util.List;

public class FamilyMemberModel {

    //内容
    public TreeNodeEntity treeNodeEntity;

    //子节点
    public List<FamilyMemberModel> childs;

    //兄弟节点
    public List<FamilyMemberModel> bothers;

    //所属层次
    public  int  level =0;

    //所属兄弟组
    public  int  botherGroup =0;


    //所属兄弟节点个数
    public  int  botherNum =0;

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

    public List<FamilyMemberModel> getBother() {
        return bothers;
    }

    public void setBother(List<FamilyMemberModel> bothers) {
        this.bothers = bothers;
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
    public List<FamilyMemberModel> getBothers() {
        return bothers;
    }

    public void setBothers(List<FamilyMemberModel> bothers) {
        this.bothers = bothers;
    }

    public int getBotherGroup() {
        return botherGroup;
    }

    public void setBotherGroup(int botherGroup) {
        this.botherGroup = botherGroup;
    }

    public int getBotherNum() {
        return botherNum;
    }

    public void setBotherNum(int botherNum) {
        this.botherNum = botherNum;
    }
}

