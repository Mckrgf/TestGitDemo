package com.god.yb.testgitdemo.activities;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.ToastUtil;

import java.io.File;

public class BaseActivity extends AppCompatActivity {

    private App app;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent = null;

    private IntentFilter tagDetected = null;
    private IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    protected String TAG;
    public String pic_path = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/pic/";
    public Uri uriForFile;
    protected File file_pic;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        ToastUtil.showToast(this,"调用了onNewIntent");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        app = (App) getApp();
        app.addActivity(this);
        String activities = "";
        for (int i = 0; i < ((App) getApp()).getActivitylists().size(); i++) {
            activities = activities + "\n" + ((App) getApp()).getActivitylists().get(i).toString();
        }
        TAG = this.getClass().getSimpleName();
        Log.d(TAG, activities);
    }


    @Override
    protected void onStart() {
        super.onStart();
        initNFC();
    }

    private void initNFC() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, NFCActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        tagDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);//ACTION_TECH_DISCOVERED
        try {
            tagDetected.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        intentFiltersArray = new IntentFilter[]{tagDetected,};
        techListsArray = new String[][]{
                new String[]{NfcF.class.getName()},
                new String[]{NfcA.class.getName()},
                new String[]{NfcB.class.getName()},
                new String[]{NfcV.class.getName()},
                new String[]{Ndef.class.getName()},
                new String[]{IsoDep.class.getName()},
                new String[]{MifareClassic.class.getName()},
                new String[]{MifareUltralight.class.getName()}};
    }

    //获得application
    public App getMyApplication() {
        Application application = getApplication();
        return (App) application;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, intentFiltersArray, techListsArray);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //恢复默认状态
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != app) app.removeActivity(this);
    }

    public Context getContext() {
        return this;
    }

    public Application getApp() {
        return getApplication();
    }

    public void openActivity(Class<?> cls) {
        Intent i = new Intent(this, cls);
        startActivity(i);
    }

    public void openCamera(int requestCode,String file_name) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file_pic = new File(pic_path+file_name);
        file_pic.getParentFile().mkdirs();

        //改变Uri
        uriForFile = FileProvider.getUriForFile(this, "com.god.yb.testgitdemo.fileprovider", file_pic);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);

        startActivityForResult(intent, requestCode);
    }
}
