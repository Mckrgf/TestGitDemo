package com.god.yb.testgitdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.god.yb.testgitdemo.R;

/**
 * 私有进程，process名为包名+：+remote
 */
public class ProcessBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_b);
    }
}
