package com.god.yb.testgitdemo.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.User;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.FallTest.FallDetectionService;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.MyDateUtils;
import com.god.yb.testgitdemo.Utils.ServiceUtils;
import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.nineoldandroids.animation.ObjectAnimator;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

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
    @BindView(R.id.bt8)
    Button bt8;
    @BindView(R.id.bt9)
    Button bt9;
    @BindView(R.id.bt10)
    Button bt10;
    private Intent intent = new Intent();
    //跳转下一个页面,不用每次都new了

    private boolean bottom_ststus = true; //true:bottom  false:up
    private int height;
    private boolean serviceRunning;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,100);
        }

        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llBottom.measure(w, h);
        height = llBottom.getMeasuredHeight();
        int width = llBottom.getMeasuredWidth();
        if (ContextCompat.checkSelfPermission(getApp(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限，就申请，然后走回调方法，在回调成功的时候调用拍照方法
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE}, 1);
        }

        ObjectAnimator.ofFloat(llBottom, "translationY", 0, height).setDuration(1200).start();
        //检测跌落服务是否开启
        serviceRunning = ServiceUtils.isServiceRunning(this, "com.god.yb.testgitdemo.FallTest.FallDetectionService");
        if (serviceRunning) {
            bt10.setText("关闭跌落检测服务");
            Log.i(TAG, "跌落服务开启了");
        } else {
            bt10.setText("打开跌落检测服务");
            Log.i(TAG, "跌落服务关闭了");
        }
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt10})
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
                } else {
                    //放下去
                    ObjectAnimator.ofFloat(llBottom, "translationY", 0, height).setDuration(200).start();
                }
                bottom_ststus = !bottom_ststus;
                break;
            case R.id.bt8:
                Intent intent = new Intent(HomeActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.bt9:
                Intent intent1 = new Intent(HomeActivity.this, SensorActivity.class);
                startActivityForResult(intent1, 101);
                break;
            case R.id.bt10:
                if (serviceRunning) {
                    bt10.setText("打开跌落检测服务");
                    Intent stopIntent = new Intent(getContext(), FallDetectionService.class);
                    getContext().stopService(stopIntent);
                    serviceRunning = false;
                } else {
                    bt10.setText("关闭跌落检测服务");
                    Intent startIntent = new Intent(getContext(), FallDetectionService.class);
                    getContext().startService(startIntent);
                    serviceRunning = true;
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Log.i(TAG,"不能悬浮");
                    ToastUtil.showToast(this,"请设置app可以悬浮");
                }else {
                    Log.i(TAG,"可以悬浮");
                }
            }
        } else {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(HomeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
