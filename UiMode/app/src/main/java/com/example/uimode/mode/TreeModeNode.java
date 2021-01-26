package com.example.uimode.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 */

public class TreeModeNode {

    private TreeModeBody treeModeBody;


    //兄弟
    private List<TreeBotherMode > botherList = new ArrayList<TreeBotherMode >();


    //孩子
    private List<TreeModeNode > childNodeList = new ArrayList<TreeModeNode >();

    //层级
    private int mCount;


    public TreeModeBody getTreeModeBody() {
        return treeModeBody;
    }

    public void setTreeModeBody(TreeModeBody treeModeBody) {
        this.treeModeBody = treeModeBody;
    }

    public List<TreeBotherMode > getBotherNodeList() {
        return botherList;
    }

    public void setBotherNodeList(List<TreeBotherMode > botherNodeList) {
        this.botherList = botherNodeList;
    }

    public List<TreeModeNode > getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<TreeModeNode > childNodeList) {
        this.childNodeList = childNodeList;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    @Override
    public String toString() {
        return "TreeMode{" +
                "treeModeBody=" + treeModeBody +
                ", botherNodeList=" + botherList +
                ", childNodeList=" + childNodeList +
                ", mCount=" + mCount +
                '}';
    }


}
