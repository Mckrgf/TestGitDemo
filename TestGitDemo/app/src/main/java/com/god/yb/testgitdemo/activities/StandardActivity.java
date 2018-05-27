package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.MapView;
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
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);
        ButterKnife.bind(this);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
