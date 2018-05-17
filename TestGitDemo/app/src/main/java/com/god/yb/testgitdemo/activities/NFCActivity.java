package com.god.yb.testgitdemo.activities;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.DataTransform;
import com.god.yb.testgitdemo.Utils.ToastUtil;


public class NFCActivity extends BaseActivity {


    //nfc控制器
    private NfcAdapter mNfcAdapter;
    //读取出来的id
    private String mId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        Intent intent = getIntent();
        int card = intent.getIntExtra("card",0);
        if (1!=card) {
            Toast.makeText(getApp(),"请从审核页面签名项中刷卡按钮点击进入",Toast.LENGTH_SHORT).show();
            finish();
        }
    }



    @Override
    protected void onNewIntent(Intent intent) {
        try {
            if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
                //处理该intent
                processIntent(intent);
                ParseID();
            }
        } catch (Exception e) {

        }
    }

    private static final String TAG = "NFCActivity";
    private void ParseID(){
        Log.i(TAG,"mid: " + mId);
        ToastUtil.showToast(this,"数字为: " + mId);
        finish();
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    private String processIntent(Intent intent) {
        //取出封装在intent中的TAG
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        byte[] uidBytes = tagFromIntent.getId();
           mId = DataTransform.bytesToHexString(uidBytes);

        return mId;
    }

}
