package com.example.uimode.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形节点
 */

public class ChildTreeModeNode {


    private String name;//名字

    private String picString;//头像

    //兄弟
    private List<TreeBotherMode > botherList = new ArrayList<TreeBotherMode >();


    //孩子节点
    private List<ChildTreeModeNode> childNodeList = new ArrayList<ChildTreeModeNode>();

    //层级
    private int mCount;



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

    public List<ChildTreeModeNode> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<ChildTreeModeNode> childNodeList) {
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
