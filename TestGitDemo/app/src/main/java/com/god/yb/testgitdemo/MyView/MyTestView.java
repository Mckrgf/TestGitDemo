package com.god.yb.testgitdemo.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yaobing on 2018/6/1.
 * Description 自定义view
 */

public class MyTestView extends View {

    private static final String TAG = "MyTestView";
    public MyTestView(Context context) {
        super(context);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG,"width_mode: " + width_mode);
        Log.i(TAG,"height_mode: " + height_mode);
        Log.i(TAG,"width: " + width);
        Log.i(TAG,"height: " + height);
        Log.i(TAG,"onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"onDraw");
    }
}
