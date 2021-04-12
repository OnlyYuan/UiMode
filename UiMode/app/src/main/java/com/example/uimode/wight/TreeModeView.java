package com.example.uimode.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.uimode.R;

public class TreeModeView extends LinearLayout {

    private ImageView treePicView;


    private TextView nameView;

    public TreeModeView(Context context) {
        this(context,null);   //**注意
    }

    public TreeModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view= LayoutInflater.from(context).inflate(R.layout.tree_mode_layout,this,true); //添加组件

        //初始化组件
        treePicView = view.findViewById(R.id.treePicView);
        nameView = view.findViewById(R.id.nameView);
    }

    //设置头像
    public void setTreePicView(String url){


//       x.image().bind(treePicView,url);
    }

    //设置名字
    public void setNameView(String  name) {
        this.nameView.setText(name);
    }

    //点击按钮
    public void onClick(){


   }

}
