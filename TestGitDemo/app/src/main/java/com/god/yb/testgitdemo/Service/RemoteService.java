package com.god.yb.testgitdemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        android.os.Debug.waitForDebugger();
        Log.i(TAG,TAG+": " + "onCreate");
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,TAG+": " + "onStartCommand");
        Toast.makeText(this,"onStartCommand",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,TAG+": " + "onDestroy");
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT);
        super.onDestroy();
    }
}
