package com.god.yb.testgitdemo.activities;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public Context getContext() {
        return this;
    }

    public Application getApp() {
        return (App)getApplication();
    }
}
