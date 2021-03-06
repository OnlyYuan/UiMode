package com.example.uimode.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 */

public class TreeModeNode {


    private String name;//名字

    private String picString;//头像

    //兄弟
    private List<TreeBotherMode > botherList = new ArrayList<TreeBotherMode >();


    //孩子节点
    private List<TreeModeNode > childNodeList = new ArrayList<TreeModeNode >();

    //层级
    private int mCount;

    public TargetPostion getPostion() {
        return postion;
    }

    public void setPostion(TargetPostion postion) {
        this.postion = postion;
    }

    //位置
    private TargetPostion postion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicString() {
        return picString;
    }

    public void setPicString(String picString) {
        this.picString = picString;
    }

    public List<TreeBotherMode> getBotherList() {
        return botherList;
    }

    public void setBotherList(List<TreeBotherMode> botherList) {
        this.botherList = botherList;
    }

    public List<TreeModeNode> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<TreeModeNode> childNodeList) {
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
        return "TreeModeNode{" +
                "name='" + name + '\'' +
                ", picString='" + picString + '\'' +
                ", botherList=" + botherList +
                ", childNodeList=" + childNodeList +
                ", mCount=" + mCount +
                '}';
    }

}
