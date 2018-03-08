package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.god.yb.testgitdemo.Fragments.FragmentA;
import com.god.yb.testgitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 包含Fragment、菜单按钮
 */

public class FragmentActivity extends BaseActivity {
    private static final String TAG = "FragmentActivity";
    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initView();
        ButterKnife.bind(this);
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, FragmentA.newInstense("测试数据"),"fragmentA").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_a, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Log.i(TAG, "Add item");
                break;
            case R.id.delete_item:
                Log.i(TAG, "Detele item");
                break;
        }
        return true;
    }
}
