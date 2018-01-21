package com.god.yb.testgitdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.god.yb.testgitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;

    private static final String TAG = "HomeActivity";
    private Intent intent;//跳转下一个页面,不用每次都new了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt1, R.id.bt2,R.id.bt3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Log.i(TAG,"Service模块");
                intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
                break;
            case R.id.bt2:
                Log.i(TAG,"Activity模块");
//                intent = new Intent(getContext(),HomeActivity.class);
//                startActivity(intent);
                break;
            case R.id.bt3:
                break;
        }
    }
}
