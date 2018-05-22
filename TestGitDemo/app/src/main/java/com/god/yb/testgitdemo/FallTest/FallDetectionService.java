package com.god.yb.testgitdemo.FallTest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.god.yb.testgitdemo.Event.FallEvent;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.activities.HomeActivity;

import org.greenrobot.eventbus.EventBus;

public class FallDetectionService extends Service {

    private FallSensorManager fallSensorManager;
    public Fall fall;
    private boolean running = true;
    private LocalBroadcastManager localBroadcastManager;
    private FallLocalReceiver fallLocalReceiver;


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fallSensorManager = new FallSensorManager(this);
        fallSensorManager.initSensor();
        fallSensorManager.registerSensor();
        fall = new Fall();
        fall.setThresholdValue(25, 5);
        showInNotification();
        DetectThread detectThread = new DetectThread("thread_fall_reflect");
        detectThread.start();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.broadcast.FALL_LOCAL_BROADCAST");
        fallLocalReceiver = new FallLocalReceiver();
        localBroadcastManager.registerReceiver(fallLocalReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fallSensorManager.unregisterSensor();
        localBroadcastManager.unregisterReceiver(fallLocalReceiver);
        fall.setStatus(false);
        running = false;
    }

    //开一个线程用于检测跌倒
    class DetectThread extends HandlerThread {
        DetectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            fall.fallDetection();
            String TAG = "FallDetectionService";
            Log.d(TAG, "DetectThread.start()");
            while (running) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (fall.isFell()) {
                    running = false;
                    fall.setFell(false);
                    //负责重启服务
                    FallEvent fallEvent = new FallEvent();
                    fallEvent.setService_state(0);
                    EventBus.getDefault().post(fallEvent);

                    Intent intent = new Intent("com.broadcast.FALL_LOCAL_BROADCAST");
                    localBroadcastManager.sendBroadcast(intent);
                    stopSelf();
                    break;
                }
            }
        }
    }

    /**
     * 在通知栏上显示服务运行
     */
    private void showInNotification() {
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("跌到检测")
                .setContentText("跌倒检测正在运行")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

}
