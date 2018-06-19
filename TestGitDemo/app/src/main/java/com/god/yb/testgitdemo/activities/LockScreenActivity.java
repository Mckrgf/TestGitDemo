package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LockScreenActivity extends BaseActivity {

    @BindView(R.id.bt_close)
    Button btClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_lock_screen);
        ButterKnife.bind(this);
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        App.screenIsLock = true;
    }

    //监听系统的物理按键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("tag", "===BACK====");
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            Log.i("tag", "===HOME====");
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            Log.i("tag", "===KEYCODE_MENU====");
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Log.i("tag", "===KEYCODE_MENU====");
            finish();
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.screenIsLock = false;
    }
}
