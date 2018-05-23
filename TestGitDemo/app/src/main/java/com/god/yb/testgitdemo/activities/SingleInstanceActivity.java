package com.god.yb.testgitdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.god.yb.testgitdemo.R;

public class SingleInstanceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
    }
}
