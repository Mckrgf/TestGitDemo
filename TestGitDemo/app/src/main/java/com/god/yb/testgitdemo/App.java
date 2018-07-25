package com.god.yb.testgitdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.god.yb.testgitdemo.DBBean.DaoMaster;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.Helper;
import com.god.yb.testgitdemo.Utils.AppUtils;
import com.god.yb.testgitdemo.Utils.CrashHandler;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.greenrobot.greendao.database.Database;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by tfhr on 2018/2/1.
 */

public class App extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;
    private static final String TAG = "App";
    private DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();

        initHttp();
//        CrashHandler.getInstance().init(this);
        ZXingLibrary.initDisplayOpinion(this);
        //MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true
        Helper helper = new Helper(this, "test.db",
                null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        initDB();
        Log.i(TAG,"当前的进程名字是：" + AppUtils.getCurProcessName(this));
        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn
        // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5b58418c");
//        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5b58418c"+","+SpeechConstant.ENGINE_MODE+"="+"msc");
    }



    private void initDB() {
        //初始化数据库框架
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
//        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = daoMaster.newSession();
    }

    private void initHttp() {
        //初始化网络框架
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //非必要情况，不建议使用，第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));
        //全局的读取超时时间(60s)
        builder.readTimeout(10*1000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间(60s)
        builder.writeTimeout(10*1000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间(60s)
        builder.connectTimeout(10*1000, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        //-------------------------------------------------------------------------------------//
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    //测试文件的输入输出（以流的方式）所使用的路径
    public static String commonPath = Environment.getExternalStorageDirectory() + "/testgitdemo";
    public static String cachePath = commonPath + "/cachefile.txt";
    /**
     * 实现android 程序退出
     */
    private List<Activity> mList = new LinkedList<Activity>();

    //获取mList列表
    public List<Activity> getActivitylists(){
        return mList;
    }

    /**
     * 在BaseActivity的onCreate方法中调用 ，维护一个activity队列
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    /**
     * 在BaseActivity的onDestroy方法中调用 如果一个activity已经销毁了
     * 从队列中删除
     * @param activity
     */
    public void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    /**
     * 程序退出
     *
     * @param code
     */
    public final void exit(int code) {
        System.exit(code);
    }

    /**
     * 释放资源，退出程序时候调用
     */
    public final void release(){
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean screenIsLock = false;
}
