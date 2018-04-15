package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.god.yb.testgitdemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreadPoolsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_thread_a)
    Button btThreadA;
    @BindView(R.id.bt_thread_b)
    Button btThreadB;
    @BindView(R.id.bt_thread_c)
    Button btThreadC;
    @BindView(R.id.bt_thread_d)
    Button btThreadD;
    @BindView(R.id.bt_execute)
    Button btExecute;
    private ExecutorService executorService;
    private static final String TAG = "ThreadPoolsActivity";
    private String thread_tag;
    private Runnable task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pools);
        ButterKnife.bind(this);
        btThreadA.setOnClickListener(this);
        btThreadB.setOnClickListener(this);
        btThreadC.setOnClickListener(this);
        btThreadD.setOnClickListener(this);
        btExecute.setOnClickListener(this);

        task = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,thread_tag);
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_thread_a:
                executorService = Executors.newFixedThreadPool(2);
                thread_tag = "FixedThreadPool";
                break;
            case R.id.bt_thread_b:
                executorService = Executors.newCachedThreadPool();
                thread_tag = "CachedThreadPool";
                break;
            case R.id.bt_thread_c:
                executorService = Executors.newScheduledThreadPool(2);
                thread_tag = "ScheduledThreadPool";
                break;
            case R.id.bt_thread_d:
                executorService = Executors.newSingleThreadExecutor();
                thread_tag = "SingleThreadExecutor";
                break;
            case R.id.bt_execute:
//                executorService.execute(task);
                System.exit(0);
                break;
        }
    }
}
