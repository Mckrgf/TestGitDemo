package com.god.yb.testgitdemo.activities;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {

    private App app;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent = null;

    private IntentFilter tagDetected = null;
    private IntentFilter[] intentFiltersArray;
    String[][] techListsArray;

    private static final String TAG = "BaseActivity";

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
}
