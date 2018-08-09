package com.god.yb.testgitdemo.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.User;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.Event.FallEvent;
import com.god.yb.testgitdemo.FallTest.FallDetectionService;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.ServiceUtils;
import com.god.yb.testgitdemo.Utils.StringUtil;
import com.god.yb.testgitdemo.Utils.ToBase64;
import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.nineoldandroids.animation.ObjectAnimator;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    @BindView(R.id.bt11)
    Button bt11;
    @BindView(R.id.bt12)
    Button bt12;
    @BindView(R.id.bt13)
    Button bt13;
    @BindView(R.id.bt14)
    Button bt14;
    @BindView(R.id.bt15)
    Button bt15;
    @BindView(R.id.bt16)
    Button bt16;
    @BindView(R.id.et_search_name)
    EditText etSearchName;
    @BindView(R.id.bt17)
    Button bt17;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    private Intent intent = new Intent();
    //跳转下一个页面,不用每次都new了

    private boolean bottom_ststus = true; //true:bottom  false:up
    private int height;
    private boolean serviceRunning;
    private Intent intent_service;
    private List<User> users;
    private DaoSession daoSession;
    private UserDao userDao;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        daoSession = ((App) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        //rx excute
        excuteRX();
        ButterKnife.bind(this);

        //开启锁屏
//        Intent lock_intent = new Intent(this, LockService.class);
//        startService(lock_intent);

        intent_service = new Intent(getContext(), FallDetectionService.class);
//        if (!Settings.canDrawOverlays(this)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, 100);
//        }

        //首字母转换
        final ArrayList<String> names = new ArrayList<>();
        String name = "费振华";
        names.add(name);
        name = "赵蔚文";
        names.add(name);
        name = "杨晶";
        names.add(name);
        name = "张雪吟";
        names.add(name);
        name = "姚冰";
        names.add(name);
        name = "江宏晖";
        names.add(name);
        name = "陈雨超";
        names.add(name);
        name = "12456";
        names.add(name);
        name = "abc";
        names.add(name);
        name = "DEF";
        names.add(name);
        name = "abcDEF123456";
        names.add(name);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                long time_s = System.currentTimeMillis();
//                users = userDao.queryBuilder().build().list();

                StringBuilder result = new StringBuilder();
                s = s.toString().toLowerCase();
                Log.d(TAG, "用户列表数量为： " + names.size());
                if (!TextUtils.isEmpty(s)) {
                    for (int i = 0; i < names.size(); i++) {
                        String name = names.get(i).toString().toLowerCase();
                        boolean a = StringUtil.getFirstSpell(name).toLowerCase().contains(s);
                        boolean b = StringUtil.getFullSpell(name).toLowerCase().contains(s);
                        boolean c = name.toLowerCase().contains(s);
                        if (a | b | c) {
                            Log.d(TAG, "这个名字： " + name + "包含" + s);
                            result.append(name).append(" , ");
                        }
                    }
                    long time_e = System.currentTimeMillis();
                    if (result.toString().equals("")) {
                        ToastUtil.showToast(getApp(), "耗时（毫秒）：" + (time_e - time_s) + "  " + "根据 ：" + s + "查询不到结果");
                    } else {
                        ToastUtil.showToast(getApp(), "耗时（毫秒）：" + (time_e - time_s) + "  " + "根据 ：" + s + "查询到如下结果：" + result);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etSearchName.addTextChangedListener(watcher);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llBottom.measure(w, h);
        height = llBottom.getMeasuredHeight();
//        int width = llBottom.getMeasuredWidth();
        if (ContextCompat.checkSelfPermission(getApp(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限，就申请，然后走回调方法，在回调成功的时候调用拍照方法
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE}, 1);
        }

        ObjectAnimator.ofFloat(llBottom, "translationY", 0, height).setDuration(1200).start();
        //检测跌落服务是否开启
        serviceRunning = ServiceUtils.isServiceRunning(this, "com.god.yb.testgitdemo.FallTest.FallDetectionService");
        if (serviceRunning) {
            bt10.setText("关闭跌落检测服务,当前状态:开启中");
            Log.i(TAG, "跌落服务开启了");
        } else {
            bt10.setText("打开跌落检测服务,当前状态:关闭中");
            Log.i(TAG, "跌落服务关闭了");
        }
    }

    private void excuteRX() {

        Observable.just(111).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                ToastUtil.showToast(getApp(), "-------------------" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.iv_pic, R.id.bt17, R.id.bt4, R.id.bt16, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt10, R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                Log.i(TAG, "Service模块");
                intent.setClass(this, ServiceDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.bt2:
                Log.i(TAG, "Activity模块");
                intent.setClass(getContext(), LaunchModeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt3:


                users = userDao.queryBuilder().build().list();
                //如果有数据就不再添加了 影响调试效率
                boolean a = users.size() > 999;
                if (!a) {
                    for (int i = 0; i < 1000; i++) {
                        User user = new User();
                        user.setPassword("123");
                        user.setUsername(StringUtil.getRandomString(5));
                        userDao.insert(user);
                    }
                }
//                for (int i = 0; i < users.size(); i++) {
//                    Log.i(TAG, i + "用户" + users.get(i).getUsername() + "\n");
//                }
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
                    bt10.setText("打开跌落检测服务,当前状态:关闭中");
                    getContext().stopService(intent_service);
                    serviceRunning = false;
                } else {
                    bt10.setText("关闭跌落检测服务,当前状态:开启中");
                    getContext().stopService(intent_service);
                    getContext().startService(intent_service);
                    serviceRunning = true;
                }
                break;
            case R.id.bt11:
                Intent intent2 = new Intent(HomeActivity.this, NFCActivity.class);
                intent2.putExtra("card", 1);
                startActivity(intent2);
                break;
            case R.id.bt12:
                openActivity(TabViewpagerActivity.class);
                break;
            case R.id.bt13:
                openActivity(MyViewActivity.class);
                break;
            case R.id.bt14:
                openActivity(NetRertofitActivity.class);
                break;
            case R.id.bt15:
                openActivity(IatDemo.class);
                break;
            case R.id.bt16:
                openActivity(WebviewActivity.class);
                break;
            case R.id.bt17:
                openCamera(12345, +System.currentTimeMillis() + ".jpg");
                break;
            case R.id.iv_pic:
                ivPic.setVisibility(View.GONE);
                // TODO: 2018/8/9 在这里把图片文件转换为bitmap，再转换为base64，即可上传
                String base64 = ToBase64.fileToBase64(file_pic);
                Log.d(TAG,base64);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FallEvent event) {
        int service_state = event.getService_state();
        switch (service_state) {
            case 0:
                //要重启服务
                Log.i(TAG, "收到信息重启跌落检测服务");
                bt10.setText("关闭跌落检测服务,当前状态:开启中");
                getContext().stopService(intent_service);
                getContext().startService(intent_service);
                serviceRunning = true;
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        ToastUtil.showToast(getApp(), keyCode + "");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Log.i(TAG, "不能悬浮");
                    ToastUtil.showToast(this, "请设置app可以悬浮");
                } else {
                    Log.i(TAG, "可以悬浮");
                }
            }
        } else if (requestCode == 12345 && resultCode == -1) {
            ivPic.setVisibility(View.VISIBLE);
            if (null != uriForFile) {
                ToastUtil.showToast(this, uriForFile.toString());
                ivPic.setImageURI(uriForFile);
            } else if (null != data) {
                ToastUtil.showToast(this, data.toString());
                Bitmap bitmap = data.getParcelableExtra("data");
                ivPic.setImageBitmap(bitmap);
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
