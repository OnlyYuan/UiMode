package com.example.uimode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.uimode.R;
import com.example.uimode.adapter.MAdapter;
import com.example.uimode.adapter.MainMenuAdapter;
import com.example.uimode.wight.CustomSDCardLoader;

import skin.support.SkinCompatManager;

public class MainActivity extends Activity {


    private RecyclerView listView;
    private MainMenuAdapter mAdapter;
    private String[] mtitile={"拖拉效果","缩放","拖拉添加","树形画图","完整效果","相机","三级菜单","获取通讯录"
            ,"换肤green","换肤dark","默认主题","FlowLayout","treeView","dialogFragment","餅狀圖","图片动态"
    ,"雷达图"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SkinCompatManager.getInstance().loadSkin("night.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
    }

    private void init() {
        mAdapter =  new MainMenuAdapter(this,mtitile);
        //menu
        listView = findViewById(R.id.listView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        listView.setLayoutManager(gridLayoutManager);
        listView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MainMenuAdapter.OnItemListener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this,DragActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,ScaleActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,DragAndAddActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,DrawTreeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,CombineActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(MainActivity.this,CameraActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this,ThreeMenuActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this,PhoneLIstActivity.class));
                        break;

                    case 8://green
                        SkinCompatManager.getInstance().loadSkin("green.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
                        break;
                    case 9://dark
                        SkinCompatManager.getInstance().loadSkin("dark.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
                        break;
                    case 10://默认
                        SkinCompatManager.getInstance().loadSkin("default.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
                        break;

                    case 11://折叠式标题栏FlowLayoutActivity
                        startActivity(new Intent(MainActivity.this,FlowLayoutActivity.class));
                        break;

                    case 12:
                        startActivity(new Intent(MainActivity.this,TreeActivity.class));
                        break;

                    case 13: //dialogFragment
                        startActivity(new Intent(MainActivity.this,DialogFragmentActivity.class));
                        break;

                    case 14:
                        startActivity(new Intent(MainActivity.this,PieChartActivity.class));
                        break;

                    case 15:
                        startActivity(new Intent(MainActivity.this,CheckImgActivity.class));
                        break;
                    case 16:
                        startActivity(new Intent(MainActivity.this,SpiderActivity.class));
                        break;
                }
            }
        });

    }

}
