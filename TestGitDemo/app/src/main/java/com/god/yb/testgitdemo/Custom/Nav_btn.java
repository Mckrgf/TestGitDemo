package com.god.yb.testgitdemo.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by yaobing on 2018/3/10.
 * Description 自定义控件，导航页面最下边的那几个
 */

public class Nav_btn extends RelativeLayout {
    public Nav_btn(Context context) {
        super(context);
        initLayout();
    }

    public Nav_btn(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public Nav_btn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    @Override
    public boolean isSelected() {
        return super.isSelected();
    }

    private void initLayout() {

    }
}
