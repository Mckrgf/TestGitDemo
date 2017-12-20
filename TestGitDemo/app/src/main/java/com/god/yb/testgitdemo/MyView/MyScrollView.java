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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        int x = (int) event.getRawX();
//        int y = (int) event.getRawY();
//        mLastX = x;
//        mLastY = y;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                Log.i(TAG,"move x:" + deltaX + "move y: " + deltaY);
//                int transX = (int) (ViewHelper.getTranslationX(this) + deltaX);
//                int transY = (int) (ViewHelper.getTranslationY(this) + deltaY);
//                ViewHelper.setTranslationX(this,transX);
//                ViewHelper.setTranslationY(this,transY);
//                break;
//        }
//        return true;
        //获取手指当前的坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                //现在手指的坐标 - 上次view的坐标
                int deltaX= x - mLastX;
                int deltaY = y- mLastY;

                Log.d("shj--","move ,deltax:"+deltaX+" deltay:"+deltaY);

                //用nineoldandroids实现
                int translationx = (int) (ViewHelper.getTranslationX(this)+deltaX);
                int translationy = (int) (ViewHelper.getTranslationY(this)+deltaY);
//                ViewHelper.setTranslationX(this,translationx);
                ViewHelper.setTranslationY(this,translationy);

                break;
        }
        mLastY = y;
        mLastX = x;
        return true;
    }
}
