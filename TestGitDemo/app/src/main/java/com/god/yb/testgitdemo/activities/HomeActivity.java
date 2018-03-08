package com.god.yb.testgitdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.User;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.MyDateUtils;
import com.god.yb.testgitdemo.Utils.ToastUtil;

import java.util.List;

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
    @BindView(R.id.bt4)
    Button bt4;

    private static final String TAG = "HomeActivity";
    private Intent intent = new Intent();
    //跳转下一个页面,不用每次都new了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Log.i(TAG, "Service模块");
                intent.setClass(this, ServiceDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.bt2:
                Log.i(TAG, "Activity模块");
                intent.setClass(getContext(), HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt3:
                DaoSession daoSession = ((App) getApplication()).getDaoSession();
                UserDao userDao = daoSession.getUserDao();
                User user = new User();
                user.setPassword("123");
                user.setUsername(MyDateUtils.getCurTime(""));
                userDao.insert(user);
                Log.i(TAG, "Add a User bean to Datebase" + user.getUsername());

                List<User> users = userDao.queryBuilder().build().list();
                for (int i = 0; i < users.size(); i++) {
                    Log.i(TAG, i + "用户" + users.get(i).getUsername() + "\n");
                }
                intent.setClass(getContext(), DateBaseActivity.class);
                startActivity(intent);

                break;
            case R.id.bt4:
                Log.i(TAG, "Fragment模块");
                intent.setClass(getContext(), FragmentActivity.class);
                startActivity(intent);
                break;
        }
    }

    private long lastMillion;


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastMillion >= 2000)  {
            //大于2秒，不退出
            Log.i(TAG,"大于两秒");
            ToastUtil.showToast(this, "再点一次退出！");
        }else {
            System.exit(0);
        }

        lastMillion = System.currentTimeMillis();
    }
}
