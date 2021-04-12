package com.example.uimode.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.uimode.R;
import com.example.uimode.adapter.DragAdapter;
import com.example.uimode.adapter.DragListViewdapter;
import com.example.uimode.mode.TargetPostion;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片跟着手指拖动行进
 */
public class DragActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView image_move;
    private ImageView image;
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private DragListViewdapter dragListViewdapter;


    private int lastX, lastY;    //保存手指点下的点的坐标
    private int mx, my,mHeight,mWidth;    //保存手指点下的点的坐标
    private int IMAGE_SIZE = 60;
    private int[] mPics ={R.mipmap.ic_carwashing,R.mipmap.ic_clear_night,R.mipmap.ic_cloudy};
    private int mPostion=0;
    private String TGA ="DragActivity";
    //目标的坐标
    private ArrayList<TargetPostion> targetPostions = new ArrayList<TargetPostion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        image_move = findViewById(R.id.image_move);
        image = findViewById(R.id.image);
        relativeLayout =  findViewById(R.id.layout);
        recyclerView = findViewById(R.id.listview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DragAdapter adapter = new DragAdapter(this,mPics);
        adapter.setOnItemPostionClickListener(new DragAdapter.OnItemPostionListener() {
            @Override
            public void onClick(int position) {
                image_move.setImageResource(mPics[position]);
                mPostion =position;
                Log.i(TGA,"===========>>postion"+position);
            }
        });
        recyclerView.setAdapter(adapter);

        //初始设置一个layoutParams
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(IMAGE_SIZE,IMAGE_SIZE);
        image_move.setLayoutParams(layoutParams);

        recyclerView.setOnTouchListener(this);
        getTargetPostions();
    }


    /**
     * 获取目标的位置
     */
    private void  getTargetPostions(){
       // TargetPostion postion = new TargetPostion();

        int[] location = new int[2];
        image.getLocationInWindow(location);
        mx =location[0];
        my =location[1];
        mHeight =image.getHeight();
        mWidth =image.getWidth();
        IMAGE_SIZE =mHeight;
        Log.i(TGA,"目標的位置：x="+mx+" y=" +my +" width="+ mHeight +" height="+mWidth);


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TGA,"----------------> 主按下");
                //将点下的点的坐标保存
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                image_move.setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) image_move
                        .getLayoutParams();
                layoutParams1.height = IMAGE_SIZE;
                layoutParams1.width = IMAGE_SIZE;
                layoutParams1.leftMargin = lastX-IMAGE_SIZE;
                layoutParams1.topMargin = lastY-2*IMAGE_SIZE;
                image_move.setLayoutParams(layoutParams1);
                break;

            case MotionEvent.ACTION_MOVE:
                image_move.setVisibility(View.VISIBLE);
                //计算出需要移动的距离
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                //将移动距离加上，现在本身距离边框的位置
                int left = image_move.getLeft() + dx;
                int top = image_move.getTop() + dy;
                //获取到layoutParams然后改变属性，在设置回去
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) image_move
                        .getLayoutParams();
                layoutParams.height = IMAGE_SIZE;
                layoutParams.width = IMAGE_SIZE;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                image_move.setLayoutParams(layoutParams);
                //记录最后一次移动的位置
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                getTargetPostions();
                image_move.setVisibility(View.GONE);
                Log.i(TGA,"===========》x= "+mx);
                if ((mx<lastX) &&(lastX<mx+mWidth) &&(my<lastY)&&(lastY<my+mHeight)){

                    Log.i(TGA,"---------->拖到了圖片位置 ");
                    image.setImageResource(mPics[mPostion]);
                 }

                Log.i(TGA,"最后的位置x= "+lastX+"  y="+lastY);
                break;

        }
        //刷新界面
        relativeLayout.invalidate();
        return false;

    }


}
