package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.god.yb.testgitdemo.Adapter.MyNewVpAdapter;
import com.god.yb.testgitdemo.Fragments.FragmentA;
import com.god.yb.testgitdemo.Fragments.FragmentB;
import com.god.yb.testgitdemo.Fragments.FragmentC;
import com.god.yb.testgitdemo.Fragments.FragmentD;
import com.god.yb.testgitdemo.Fragments.FragmentE;
import com.god.yb.testgitdemo.Fragments.FragmentF;
import com.god.yb.testgitdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabViewpagerActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_nav)
    ViewPager vpNav;
    private ArrayList<String> tabtexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager);
        ButterKnife.bind(this);
        tabtexts = new ArrayList<>();
        tabtexts.add("AAAAAAAAAAA");
        tabtexts.add("BBBBBBBBBBB");
        tabtexts.add("CCCCCCCCCCC");
        initView();
    }

    private void initView() {
        initViewPager();
        initTab();
    }

    private void initTab() {
        tab.setupWithViewPager(vpNav);
        tab.setTabMode(TabLayout.MODE_FIXED);
        for (int i=0;i<tab.getTabCount();i++) {
            tab.getTabAt(i).setText(tabtexts.get(i));
        }
    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentD.newInstense("A"));
        fragments.add(FragmentE.newInstense("B"));
        fragments.add(FragmentF.newInstense("C"));
        MyNewVpAdapter adapter = new MyNewVpAdapter(getSupportFragmentManager());
        adapter.setData(fragments);
        vpNav.setAdapter(adapter);
    }
}
