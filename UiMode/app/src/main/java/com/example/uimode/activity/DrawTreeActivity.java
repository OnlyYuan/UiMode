package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.uimode.R;
import com.example.uimode.mode.ChildTreeModeNode;
import com.example.uimode.mode.TargetPostion;
import com.example.uimode.mode.TreeModeNode;
import com.example.uimode.mode.TreeModePostion;
import com.example.uimode.wight.TreeHelper;
import com.example.uimode.wight.TreeModeView;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 画树形
 */
public class DrawTreeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout.LayoutParams[] layoutParams = new LinearLayout.LayoutParams[3];
    private TreeModeView[] treeModeView= new TreeModeView[3];
    String [] names ={"小明","小王","小赵"};

    private List<String> listx = new ArrayList<String>();//x 坐标
    private List<String> listy = new ArrayList<String>();//y 坐标

    RelativeLayout insertLayout ; //被插入画布

    List<TreeModePostion> postionList = new ArrayList<TreeModePostion>();//位置坐标list
    List<TreeModeView> treeModeViewList = new ArrayList<TreeModeView>();//节点图list
    List<LinearLayout.LayoutParams> layoutParamList = new ArrayList<LinearLayout.LayoutParams>();//布局list

    // private RelativeLayout.LayoutParams[] layoutParams = new RelativeLayout.LayoutParams[15];
    private RelativeLayout.LayoutParams[] layoutParams1 = new RelativeLayout.LayoutParams[15];

    private  TreeModeNode treeModeNode; //节点
    private Button btn;

    //  private BLzgView[] bLzgViews = new BLzgView[15];

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
       // calPostion(width,height);


       // initData();

        initDataNode();

       // calPostion();

        initLinsner();
        System.out.println("-=------>进入树");
    }

    private void initLinsner() {
        btn.setOnClickListener(this);
    }

    //初始化节点
    private void initDataNode() {

        //起始点
        int x= insertLayout.getWidth()/2;
        int y = 0;

        TargetPostion postion = new TargetPostion();
        postion.setX(x);
        postion.setY(y);

        treeModeNode = new TreeModeNode();
        treeModeNode.setName("团队一");
        treeModeNode.setmCount(1);
        treeModeNode.setPostion(postion);

        String [] twoNodes ={"张三","李四","王五"};
        String [] [] twoNodes2 ={{"张三1","李四1","王五2"},{"张三2","李四2","王五2"},{"张三3","李四3","王五3"}};

        //初始化数组
        List<TreeModeNode> chlidList1 = new ArrayList<TreeModeNode>();
        for (int i=0;i<twoNodes.length;i++){//第二层 子节点

            TreeModeNode childTreeModeNode = new TreeModeNode();

            childTreeModeNode.setmCount(2);
            childTreeModeNode.setName(twoNodes[i]);

            List<TreeModeNode> chlidList = new ArrayList<TreeModeNode>();
            for (int j=0;j<twoNodes2[i].length;j++){//第三层节点
                TreeModeNode mChildTreeModeNode = new TreeModeNode();
                mChildTreeModeNode.setName(twoNodes2[i][j]);
                mChildTreeModeNode.setmCount(3);
                chlidList.add(mChildTreeModeNode);
            }
            childTreeModeNode.setChildNodeList(chlidList);
            chlidList1.add(childTreeModeNode);
        }

        treeModeNode.setChildNodeList(chlidList1);

        drawTree(treeModeNode);


        int num =  getTreeLeafNum(treeModeNode);
        int totalNum = getTotalNodeNum(treeModeNode);
        Log.i("11","--------->num"+num+"  totalNum:"+totalNum);
        Log.i("11","--------->11"+treeModeNode.toString());
    }


    /**
     * 计算坐标
     *
     */

    public void calPostion(){

        //起始点
        int x= 0;
        int y =insertLayout.getWidth()/2;


        Log.i("1","x="+x+" ,y="+y);

        TargetPostion targetPostion  = new TargetPostion();
        targetPostion.setX(x);
        targetPostion.setY(y);
        treeModeNode.setPostion(targetPostion);
    }


    private void initData() {

        for (int i=0;i<names.length;i++){
            treeModeView[i] = new TreeModeView(this);
            treeModeView[i].setNameView(names[i]);
            System.out.println("----->宽"+names[i]);
        }
        //获取屏幕的大小和坐标

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        System.out.println("----->宽"+width+"\n----->高"+height);


        for (int i=0;i<layoutParams.length;i++){
            layoutParams[i]= new LinearLayout.LayoutParams(200,200);
            layoutParams[i].leftMargin =i*200;
            layoutParams[i].topMargin=i*200;
            insertLayout.addView(treeModeView[i],layoutParams[i]);
        }

       // insertLayout.addView( treeModeView[0]);

    }



    /**
     * 分组画图
     */
    public void  drawTree( TreeModeNode treeModeNode){
        layoutParams[0]= new LinearLayout.LayoutParams(200,200);
        layoutParams[0].leftMargin =treeModeNode.getPostion().getX();
        layoutParams[0].topMargin = treeModeNode.getPostion().getY();

        treeModeView[0] = new TreeModeView(this);
        treeModeView[0].setNameView(treeModeNode.getName());
        insertLayout.addView(treeModeView[0],layoutParams[0]);

    }


    /**
     * 获取子节点
     */
    private int getTreeLeafNum(TreeModeNode node ){

        TreeHelper treeHelper = new TreeHelper();
        return treeHelper.getTreeLeafNum(node);

    }

    /**
     * 获取总子节点数
     * @param node
     * @return
     */
    private int getTotalNodeNum(TreeModeNode node ){
        TreeHelper treeHelper = new TreeHelper();
        return treeHelper.getTotalNodeNum(node);

    }



    public void initView(){
        insertLayout = findViewById(R.id.m_layout);
        btn = findViewById(R.id.btn);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:



                break;


        }
    }
}



