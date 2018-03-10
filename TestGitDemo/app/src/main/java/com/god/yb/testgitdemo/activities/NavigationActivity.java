package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.god.yb.testgitdemo.Adapter.MyViewPagerAdapter;
import com.god.yb.testgitdemo.Fragments.FragmentA;
import com.god.yb.testgitdemo.Fragments.FragmentB;
import com.god.yb.testgitdemo.Fragments.FragmentC;
import com.god.yb.testgitdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vp_nav)
    ViewPager vpNav;
    @BindView(R.id.bt_nav_1)
    Button btNav1;
    @BindView(R.id.bt_nav_2)
    Button btNav2;
    @BindView(R.id.bt_nav_3)
    Button btNav3;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        btNav1.setOnClickListener(this);
        btNav2.setOnClickListener(this);
        btNav3.setOnClickListener(this);
        vpNav.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setBtnBack(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initData() {
        FragmentA fragmentA = FragmentA.newInstense("点击以达到通过Fragment改变Activity的功能，即改变Activity中第一个button的文字内容");
        fragmentB = FragmentB.newInstense("我是FB");
        FragmentC fragmentC = FragmentC.newInstense("我是FC");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragmentA);
        fragmentList.add(fragmentB);
        fragmentList.add(fragmentC);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.setFragments(fragmentList);
        vpNav.setAdapter(myViewPagerAdapter);
        btNav1.setSelected(true);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.bt_nav_1:
                vpNav.setCurrentItem(0);
                setBtnBack(0);
                break;
            case R.id.bt_nav_2:
                vpNav.setCurrentItem(1);
                setBtnBack(1);
                fragmentB.changeContent("通过A改变F");
                break;
            case R.id.bt_nav_3:
                vpNav.setCurrentItem(2);
                setBtnBack(2);
                break;
        }
    }

    private void setBtnBack(int pos) {
        switch (pos) {
            case 0:
                btNav1.setSelected(true);
                btNav2.setSelected(false);
                btNav3.setSelected(false);
                break;
            case 1:
                btNav1.setSelected(false);
                btNav2.setSelected(true);
                btNav3.setSelected(false);
                break;
            case 2:
                btNav1.setSelected(false);
                btNav2.setSelected(false);
                btNav3.setSelected(true);
                break;
        }
    }
}
