package com.god.yb.testgitdemo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps.MapView;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.activities.NavigationActivity;
import com.god.yb.testgitdemo.activities.TabViewpagerActivity;

/**
 * Created by yaobing on 2018/3/8.
 * Description xxx
 */

public class FragmentD extends Fragment {
    private String mPara;
    private Activity mActivity;
    private static String Fragment_key = "test_key";
    private MapView mMapView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mPara = getArguments().getString(Fragment_key);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_4, container, false);
        mMapView = root.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        return root;
    }

    public static FragmentD newInstense(String mPara) {
        FragmentD fragmentA = new FragmentD();
        Bundle bundle = new Bundle();
        bundle.putString(Fragment_key, mPara);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
