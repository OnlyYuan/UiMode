package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.uimode.R;
import com.example.uimode.activity.tree.mode.FamilyMemberModel;
import com.example.uimode.activity.tree.mode.TreeNodeEntity;
import com.example.uimode.activity.tree.ui.FamilyMemberLayout;
import com.example.uimode.activity.tree.ui.FamilyTreeAdapter;
import com.example.uimode.mode.TreeModePostion;
import com.example.uimode.wight.TreeModeView;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * 画树形
 */
public class DrawTreeActivity extends AppCompatActivity  {

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

    FamilyMemberModel familyMemberModel; //节点
    private Button btn;

    FamilyMemberLayout familyMemberLayout;
    FamilyTreeAdapter familyTreeAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tree);

        initView();
        initData();
        familyTreeAdapter = familyMemberLayout.getFamilyTreeAdapter();
        Function0 function0 = null;


        familyTreeAdapter.dealWithData(familyMemberModel,()->{
            /************** 刷新显示 ***********************/
            familyMemberLayout.displayUI();
            return Unit.INSTANCE;
        });
    }


    public void initView(){

        familyMemberLayout = findViewById(R.id.myview);


        familyMemberLayout.setFamilyTreeAdapter(new FamilyTreeAdapter());





    }

    private void initLinsner() {
//        btn.setOnClickListener(this);
    }

    //初始化节点
    private void initData() {
        familyMemberModel  = new FamilyMemberModel();
        TreeNodeEntity treeNodeEntity1 = new TreeNodeEntity();
        treeNodeEntity1.setName("团队一");
        treeNodeEntity1.setPicUrlString(" ");
        familyMemberModel.setTreeNodeEntity(treeNodeEntity1);
       // familyMemberModel.treeNodeEntity.setPicUrlString("");

        familyMemberModel.level =0;

        String [] twoNodes ={"张三","李四","王五"};
        String [] [] twoNodes2 ={{"张三1","李四1","王五2"},{"张三2","李四2","王五2"},{"张三3","李四3","王五3"}};

        //初始化数组
        List<FamilyMemberModel> chlidList1 = new ArrayList<FamilyMemberModel>();
        TreeNodeEntity treeNodeEntity2 = new TreeNodeEntity();
        for (int i=0;i<twoNodes.length;i++){//第二层 子节点

            FamilyMemberModel childTreeModeNode = new FamilyMemberModel();
            treeNodeEntity2 = new TreeNodeEntity();
            treeNodeEntity2.setName(twoNodes[i]);
            treeNodeEntity2.setPicUrlString("");
            childTreeModeNode.setLevel(2);
            childTreeModeNode.setTreeNodeEntity(treeNodeEntity2);

            List<FamilyMemberModel> chlidList = new ArrayList<FamilyMemberModel>();
            for (int j=0;j<twoNodes2[i].length;j++){//第三层节点
                FamilyMemberModel mChildTreeModeNode = new FamilyMemberModel();
                TreeNodeEntity treeNodeEntity3 = new TreeNodeEntity();
                treeNodeEntity3.setName(twoNodes2[i][j]);
                treeNodeEntity3.setPicUrlString("");
                mChildTreeModeNode.setTreeNodeEntity(treeNodeEntity3);
                mChildTreeModeNode.setLevel(3);
                chlidList.add(mChildTreeModeNode);
            }
            childTreeModeNode.setChilds(chlidList);
            chlidList1.add(childTreeModeNode);
        }

        familyMemberModel.setChilds(chlidList1);

    }

}



