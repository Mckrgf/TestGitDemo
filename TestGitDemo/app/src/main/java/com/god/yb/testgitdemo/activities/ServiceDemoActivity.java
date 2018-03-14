package com.god.yb.testgitdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.god.yb.testgitdemo.MyView.MyScrollView;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Service.LocalService;
import com.god.yb.testgitdemo.Service.RemoteService;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceDemoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;

    private static final String TAG = "ServiceDemoActivity";
    @BindView(R.id.my_view)
    MyScrollView myView;
    @BindView(R.id.activity_main)
    ScrollView activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyScrollView myScrollView = findViewById(R.id.my_view);
        myScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        ButterKnife.bind(this);
        List<String> mdata = new ArrayList<>();
        mdata.add("1");
        mdata.add("2");
        mdata.add("3");
        mdata.add("4");

        //设置布局类型
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(manager);
        rvList.setAdapter(new CommonAdapter(this, R.layout.item_rv_test, mdata) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                holder.setText(R.id.tv, "zuowei" + position);
            }
        });

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Log.i(TAG, "开启本地服务");
                Intent intent0 = new Intent(this, LocalService.class);
                startService(intent0);
                break;
            case R.id.bt2:
                Log.i(TAG, "开启远程服务");
                Intent intent1 = new Intent(this, RemoteService.class);
                startService(intent1);
                break;
            case R.id.bt3:
                Log.i(TAG, "停止本地服务");
                Intent intent2 = new Intent(this, LocalService.class);
                stopService(intent2);
                break;
            case R.id.bt4:
                Log.i(TAG, "停止远程服务");
                Intent intent3 = new Intent(this, RemoteService.class);
                stopService(intent3);
                break;
            case R.id.bt5:
                Log.i(TAG, "开启进程A（app私有进程）");
                Intent intent4 = new Intent(this, ProcessAActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt6:
                Log.i(TAG, "开启进程A（app非私有进程）");
                Intent intent5 = new Intent(this, ProcessBActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
