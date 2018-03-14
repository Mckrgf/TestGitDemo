package com.god.yb.testgitdemo.activities;

import android.os.Bundle;

import com.god.yb.testgitdemo.R;

/**
 * 公有进程，包名为随便写的，别的app也可以访问
 */

public class ProcessAActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_a);
    }
}
