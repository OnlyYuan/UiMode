package com.example.uimode.activity.tree.mode;

/**
 * 节点内容
 */
public class TreeNodeEntity {

    //name
    public  String Name;

    public   int sex;

    //pic
    public  String picUrlString;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicUrlString() {
        return picUrlString;
    }

    public void setPicUrlString(String picUrlString) {
        this.picUrlString = picUrlString;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TreeNodeEntity{" +
                "Name='" + Name + '\'' +
                ", picUrlString='" + picUrlString + '\'' +
                '}';
    }


}
