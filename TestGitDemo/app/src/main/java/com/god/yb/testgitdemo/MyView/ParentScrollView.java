package com.god.yb.testgitdemo.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by yaobing on 2018/3/15.
 * Description xxx
 */

public class ParentScrollView extends ScrollView {
    public ParentScrollView(Context context) {
        super(context);
    }

    public ParentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
