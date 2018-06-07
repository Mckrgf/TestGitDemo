package com.god.yb.testgitdemo.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.god.yb.testgitdemo.R;

/**
 * Created by yaobing on 2018/6/1.
 * Description 自定义view
 */

public class MyTestView extends View {

    private static final String TAG = "MyTestView";

    private int zero_x = 0;//原点坐标
    private int zero_y = 0;//原点坐标
    private int max_x = 0;//x最大值
    private int max_y = 0;//y最大值
    private int padding = 20;
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

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"onDraw");

        Paint paint = new Paint();
        paint.setColor(R.color.black);
        paint.setStrokeWidth(3);
        int width = getMeasuredWidth();
        int heigh = getMeasuredHeight();
        //设置
        zero_x = 0+padding;
        zero_y = heigh-padding;
        max_x = width-padding;
        max_y = heigh-padding;
        canvas.drawLine(zero_x,zero_y,max_x,max_y,paint);//x轴
        canvas.drawLine(zero_x,zero_y,padding,padding,paint);//y轴
    }
}
