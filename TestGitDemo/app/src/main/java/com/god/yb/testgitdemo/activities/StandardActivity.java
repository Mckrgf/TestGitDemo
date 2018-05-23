package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.god.yb.testgitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandardActivity extends BaseActivity {

    @BindView(R.id.bt_singleInstance)
    Button btSingleInstance;
    @BindView(R.id.bt_singleTask)
    Button btSingleTask;
    @BindView(R.id.bt_singleTop)
    Button btSingleTop;
    @BindView(R.id.bt_standard)
    Button btStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_singleInstance, R.id.bt_singleTask, R.id.bt_singleTop, R.id.bt_standard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_singleInstance:
                openActivity(SingleInstanceActivity.class);
                break;
            case R.id.bt_singleTask:
                openActivity(SingleTaskActivity.class);
                break;
            case R.id.bt_singleTop:
                openActivity(SingleTopActivity.class);
                break;
            case R.id.bt_standard:
                openActivity(StandardActivity.class);
                break;
        }
    }
}
