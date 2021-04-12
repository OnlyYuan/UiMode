package com.example.uimode.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 *
 */

public class ZoomScrollLayout2 extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener {

    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureDetector;
    private static final float MIN_ZOOM = 0.1f;
    private static final float MAX_ZOOM = 5.0f;

    private int mLeft, mTop, mRight, mBottom;
    private int centerX, centerY;
    private float mLastScale = 2.0f;
    private float totleScale = 1.0f;
    TreeView treeView ;

    // childview
    private View mChildView;

    // 拦截滑动事件
    float mDistansX, mDistansY, mTouchSlop;

    private enum MODE {
        ZOOM, DRAG, NONE
    }

    private MODE mode;

    public ZoomScrollLayout2(Context context) {
        super(context);
        init(context);
    }

    public ZoomScrollLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZoomScrollLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mChildView = getChildAt(0);
        centerX = getWidth()/ 2;
        centerY = getHeight() / 2;
        mChildView.layout(0, 0, mChildView.getMeasuredWidth()-centerX, mChildView.getMeasuredHeight());
        Log.i("11","---->centerx"+centerX +"---->mChildView.getTranslationX()"+mChildView.getTranslationX()+"--->childView"+mChildView.getMeasuredWidth());

        mChildView.setTranslationX( - mChildView.getMeasuredWidth()/20.0f);
    }
    boolean isFirst = true;
    public void init(Context context) {

        treeView = new TreeView(context);
        mScaleDetector = new ScaleGestureDetector(context, this);
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (mode == MODE.DRAG) {
                    if (mChildView == null) {
                        mChildView = getChildAt(0);
                        centerX = getWidth() / 2;
                        centerY = getHeight() / 2;
                    }

                    if (isFirst){
                        isFirst = false;
                    }else {
                        mChildView.setTranslationX(mChildView.getTranslationX() - distanceX);
                        mChildView.setTranslationY(mChildView.getTranslationY() - distanceY);
                    }
                }
               return true;
            }
        });

        // 系统最小滑动距离
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getActionMasked();
        int currentX = (int) e.getX();
        int currentY = (int) e.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //记录上次滑动的位置
                mDistansX = currentX;
                mDistansY = currentY;
                isFirst = true;
                //将当前的坐标保存为起始点
                mode = MODE.DRAG;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mDistansX - currentX) >= mTouchSlop || Math.abs(mDistansY - currentY) >= mTouchSlop) {//父容器拦截
                    return true;
                }
                break;
            //指点杆保持按下，并且进行位移
            //有手指抬起，将模式设为NONE
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_POINTER_UP:
                mode = MODE.NONE;
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        Log.i("aaa","---> onScale");

        if (mode == MODE.ZOOM) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            float mx =  scaleGestureDetector.getFocusX();
            float my =  scaleGestureDetector.getFocusY();
            float tempScale = mLastScale * scaleFactor;
            if (tempScale <= MAX_ZOOM && tempScale >= MIN_ZOOM) {
                totleScale = tempScale;
                applyScale(totleScale,mx,my);
            }

        }

        return false;
    }

    /**
     * 执行缩放操作
     */
    public void applyScale(float scale,float fx, float fy) {
        mChildView.setPivotX(fx);
        mChildView.setPivotY(fy);
        mChildView.setScaleX(scale);
        mChildView.setScaleY(scale);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Log.i("aaa","---> onScaleBegin");
        mode = MODE.ZOOM;
        if (mode == MODE.ZOOM) {
            if (mChildView == null) {
                mChildView = getChildAt(0);

                centerX = getWidth() / 2;
                centerY = getHeight() / 2;

                mLeft = mChildView.getLeft();
                mTop = mChildView.getTop();
                mRight = mChildView.getRight();
                mBottom = mChildView.getBottom();
            }

        }

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        mLastScale = totleScale;
    }

}
