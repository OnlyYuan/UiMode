package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.uimode.R;
import com.example.uimode.mode.TreeMode;
import com.example.uimode.mode.TreeModePostion;
import com.example.uimode.wight.TreeModeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 画树形
 */
public class DrawTreeActivity extends AppCompatActivity {

//    LinearLayout.LayoutParams[] layoutParams = new LinearLayout.LayoutParams[3];
//    private TreeModeView[] treeModeView= new TreeModeView[3];
    String [] names ={"小明","小王","小赵"};

    private List<String> listx = new ArrayList<String>();//x 坐标
    private List<String> listy = new ArrayList<String>();//y 坐标
    RelativeLayout insertLayout ; //被插入画布
    List<TreeModePostion> postionList = new ArrayList<TreeModePostion>();//位置坐标list
    List<TreeModeView> treeModeViewList = new ArrayList<TreeModeView>();//节点图list
    List<LinearLayout.LayoutParams> layoutParamList = new ArrayList<LinearLayout.LayoutParams>();//布局list

//    private RelativeLayout.LayoutParams[] layoutParams = new RelativeLayout.LayoutParams[15];
//    private RelativeLayout.LayoutParams[] layoutParams1 = new RelativeLayout.LayoutParams[15];
//    private BLzgView[] bLzgViews = new BLzgView[15];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tree);

        initView();
        //获取屏幕的大小和坐标
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        //计算坐标位置的list
        calPostion(width,height);



     //   initData();
        System.out.println("-=------>进入树");
    }


    private void initData() {

//    for (int i=0;i<names.length;i++){
//            treeModeView[i] = new TreeModeView(this);
//            treeModeView[i].setNameView(names[i]);
//        System.out.println("----->宽"+names[i]);
//        }
//    //获取屏幕的大小和坐标
//
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        int width = metrics.widthPixels;
//        int height = metrics.heightPixels;
//        System.out.println("----->宽"+width+"\n----->高"+height);
//        for (int i=0;i<layoutParams.length;i++){
//            layoutParams[i]= new LinearLayout.LayoutParams(200,200);
//            layoutParams[i].leftMargin =i*200;
//            layoutParams[i].topMargin=i*200;
//            insertLayout.addView(treeModeView[i],layoutParams[i]);
//        }
    }




    /**
     * 计算坐标  模拟6个点
     * @param maxX 屏幕宽
     * @param maxY  屏幕高
     *
     */

    public void calPostion(int maxX,int maxY){



    }


    /**
     * 分组画图
     */
    public void  drawPicByGroup( List<TreeModePostion> postionList, List<TreeModeView> treeModeViewList){

        for (int i=0;i<treeModeViewList.size();i++){

            layoutParamList.get(i).leftMargin =i*200;
            layoutParamList.get(i).topMargin=i*200;
            insertLayout.addView(treeModeViewList.get(i),layoutParamList.get(i));
        }
    }

    /**
     * 初始化treeModeViewList
     */
    private void treeModeViewList(){



    }


    public void initView(){
        insertLayout = findViewById(R.id.m);


    //    layoutParams[i] = new RelativeLayout.LayoutParams(120, 70); // 大小
//        layoutParams[i].topMargin = topMargin;
//        layoutParams[i].leftMargin = leftMargin; // 设置的按钮位置
//        insertLayout.addView(bLzgViews[i], layoutParams[i])

    }
}
