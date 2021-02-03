package com.example.uimode.wight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.ViewGroup;

public class MyView extends ViewGroup {



    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        //TreeModeView treeModeView = new TreeModeView(getContext());
//        TreeModeView treeModeView = (TreeModeView) getChildAt(0);
//        measureChild(treeModeView,widthMeasureSpec,heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

//        Point point = new Point(300,300);
////
//        if (getChildAt(0)!=null){
//
//        TreeModeView treeModeView = (TreeModeView)getChildAt(0);
//        int height = treeModeView.getMeasuredHeight();
//        int width = treeModeView.getMeasuredWidth();
//        layout(point.x-width/2,point.y-height/2,point.x+width/3,point.y+height/2);
////        treeModeView.setNameView("zhangsan");
//        }

    }

    /**
     * 绘制ui
     */
    public  void showUI(){

        TreeModeView treeModeView = new TreeModeView(getContext());
        treeModeView.setNameView("firstView");
        addView(treeModeView);
    }
}
