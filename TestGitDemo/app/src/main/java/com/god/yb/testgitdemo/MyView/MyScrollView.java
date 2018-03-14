package com.god.yb.testgitdemo.MyView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by yaobing on 2017/12/17.
 */

public class MyScrollView extends View {

    int mLastX;
    int mLastY;
    private static final String TAG = "MyScrollView";

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private interface OnBottomListener {
        void setOnBottomListener();
    }

    public void setOnButtomListener(OnBottomListener onButtomListener) {
        onButtomListener.setOnBottomListener();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指当前的坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Log.i(TAG, "现在的Y轴坐标:" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //现在手指的坐标 - 上次view的坐标
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;

                Log.d(TAG, "move ,deltax:" + deltaX + " deltay:" + deltaY);

                int translationy = (int) (ViewHelper.getTranslationY(this) + deltaY);
                ViewHelper.setTranslationY(this, translationy);

                //x轴不可滑动
//                int translationx = (int) (ViewHelper.getTranslationX(this)+deltaX);
//                ViewHelper.setTranslationX(this,translationx);
                break;
        }
        mLastY = y;
        mLastX = x;
        return true;
    }
}
