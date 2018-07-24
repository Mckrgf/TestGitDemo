package com.god.yb.testgitdemo.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.god.yb.testgitdemo.Receiver.HomeReceiver;
import com.god.yb.testgitdemo.Receiver.LockReceiver;

public class LockService extends Service {

    private LockReceiver lockReceiver;
    private HomeReceiver homeReceiver;

    public LockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lockReceiver = new LockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.EXTRA_KEY_EVENT);
        filter.addAction(Intent.ACTION_MEDIA_BUTTON);
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        filter.addAction("com.agold.hy.ptt.down");
        filter.addAction("com.agold.hy.ptt");
        filter.addAction("com.agold.hy.sos.down");
        filter.addAction("com.agold.hy.sos");
        filter.addAction("SEND_RECEIVER");
        filter.addAction("SEND_RECEIVER");
        registerReceiver(lockReceiver,filter);

        homeReceiver = new HomeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeReceiver,intentFilter);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(lockReceiver);
        unregisterReceiver(homeReceiver);
    }
}
