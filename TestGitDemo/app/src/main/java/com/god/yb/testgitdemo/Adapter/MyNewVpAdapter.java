package com.god.yb.testgitdemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yaobing on 2018/5/25.
 * Description xxx
 */

public class MyNewVpAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> datas;

    public MyNewVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(ArrayList<Fragment> fragments) {
        datas = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return datas.get(i);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
