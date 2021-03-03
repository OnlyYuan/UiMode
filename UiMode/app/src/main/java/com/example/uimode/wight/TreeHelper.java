package com.example.uimode.wight;

import android.util.Log;

import com.example.uimode.mode.TreeModeNode;

public class TreeHelper {

    private int mCount = 0;
    private int mTotalCount = 0;


    /**
     * 返回叶子节点个数
     * @param node
     * @return
     */
    public int getTreeLeafNum(TreeModeNode node){
        mCount =0;
        mCount = getTreeLeafFun(node);
        return mCount;
    }


    /**
     * 获取叶子节点个数的递归函数
     */

    private int getTreeLeafFun(TreeModeNode node){

        for (int i=0;i<node.getChildNodeList().size();i++){
            if (node.getChildNodeList().get(i).getChildNodeList()!=null&&node.getChildNodeList().get(i).getChildNodeList().size()!=0){
                getTreeLeafFun(node.getChildNodeList().get(i));

            }else {
                mCount++;
                Log.i("1222","----------->ppp"+mCount);
            }
        }
        return mCount;
    }


    /**
     * 左边的叶子节点
     */
    private int getLeftTreeLeafFun(TreeModeNode node){

        for (int i=0;i<node.getChildNodeList().size()/2;i++){
            if (node.getChildNodeList().get(i).getChildNodeList()!=null&&node.getChildNodeList().get(i).getChildNodeList().size()!=0){
                getLeftTreeLeafFun(node.getChildNodeList().get(i));
                mCount++;
            }
        }
        return mCount;

    }


    /**
     * 获取总节点数
     * @param node
     * @return
     */
    public int getTotalNodeNum(TreeModeNode node){

        mTotalCount =0;

       return getTotalNodeFun(node);
    }



    /**
     * 节点总数
     */

    private int getTotalNodeFun(TreeModeNode node) {

        for (int i = 0; i < node.getChildNodeList().size(); i++) {
          //  if (node.getChildNodeList().get(i).getChildNodeList() != null && node.getChildNodeList().get(i).getChildNodeList().size() != 0) {

            getTotalNodeFun(node.getChildNodeList().get(i));
            Log.i("1122","=========>"+node.getChildNodeList().get(i).getName());
                mTotalCount++;
           // }
        }
        return mTotalCount;
    }

}
