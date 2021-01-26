package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import com.example.uimode.R;
import com.example.uimode.mode.TreeModeBody;
import com.example.uimode.mode.TreeModeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 画树形
 */
public class DrawTreeActivity extends AppCompatActivity {

    private RelativeLayout insertLayout;
    private TreeModeNode mtreeMode;
    private TreeModeBody mChildTreeModeBody;
    private TreeModeBody mTreeModeBody;
    private List<TreeModeNode> childListNode;
    private List<TreeModeNode> mNodeListNode;

    //{1{1,2,3},2{1,2,3},3{1,2,3}}
    private String[] node1Strings ={ "小王","小李","小陈"};

    private String [][] node2Strings ={ { "王一","王二","王三"} ,{ "李一","李二","李三"},{ "陈一","陈二","陈三"}};

//    private String[] node11Strings={ "王一","王二","王三"};
//    private String[] node12Strings={ "李一","李二","李三"};
//    private String[] node13Strings={ "陈一","陈二","陈三"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tree);

        initView();
        //获取屏幕的大小和坐标
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        System.out.println("-=------>进入树");
    }


    public void initView(){
        insertLayout = findViewById(R.id.m);

    }

//    /**
//     * 添加节点
//     * @param treeMode
//     */
//    private TreeModeBody addTreeData( TreeModeNode treeMode) {
//
//        mtreeMode = new TreeModeNode();
//        mTreeModeBody = new TreeModeBody();
//
//        for (int i = 0; i < node1Strings.length; i++) {//一级节点
//
//            mTreeModeBody = new TreeModeBody();
//            mTreeModeBody.setName(node1Strings[i]);
//            mTreeModeBody.setPicString("url");
//
//            childListNode = new ArrayList<TreeModeNode>();
//            for (int j = 0; j < node2Strings[i].length; j++) { //添加子节点
//
//                mTreeModeBody.setName(node2Strings[i][j]);
//                mTreeModeBody.setPicString("url");
//
//
//            }
//
//            return null;
//        }


//    /**
//     * 添加子节点
//      */
//    private List<TreeModeBody> addChild(){
//
//
//
//        return
//
//   }


    }
