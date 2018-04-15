package com.god.yb.testgitdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.User;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.MyDateUtils;
import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.nineoldandroids.animation.ObjectAnimator;

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
    @BindView(R.id.bt5)
    Button bt5;
    @BindView(R.id.bt6)
    Button bt6;

    private static final String TAG = "HomeActivity";
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.bt7)
    Button bt7;
    private Intent intent = new Intent();
    //跳转下一个页面,不用每次都new了

    private boolean bottom_ststus = true; //true:bottom  false:up
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llBottom.measure(w, h);
        height = llBottom.getMeasuredHeight();
        int width = llBottom.getMeasuredWidth();


        ObjectAnimator.ofFloat(llBottom, "translationY", 0, height).setDuration(1200).start();
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7})
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
            case R.id.bt5:
                Log.i(TAG, "Fragment模块");
                intent.setClass(getContext(), NavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.bt6:
                Log.i(TAG, "线程池");
                intent.setClass(getContext(), ThreadPoolsActivity.class);
                startActivity(intent);
                break;
            case R.id.bt7:
                if (bottom_ststus) {
                    //弹出来
                    ObjectAnimator.ofFloat(llBottom, "translationY", height, 0).setDuration(200).start();
                }else {
                    //放下去
                    ObjectAnimator.ofFloat(llBottom, "translationY", 0, height).setDuration(200).start();
                }
                bottom_ststus = !bottom_ststus;
                break;
        }
    }

    private long lastMillion;


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastMillion >= 2000) {
            //大于2秒，不退出
            Log.i(TAG, "大于两秒");
            ToastUtil.showToast(this, "再点一次退出！");
        } else {
            System.exit(0);
        }

        lastMillion = System.currentTimeMillis();
    }
}
