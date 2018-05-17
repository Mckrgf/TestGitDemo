package com.god.yb.testgitdemo.FallTest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.god.yb.testgitdemo.Event.FallEvent;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.activities.HomeActivity;

import org.greenrobot.eventbus.EventBus;

public class FallDetectionService extends Service {

    private FallSensorManager fallSensorManager;
    public Fall fall;
    private boolean running = false;
    private final String TAG = "FallDetectionService";
    private DetectThread detectThread;
    private Dialog dialog;
    private Vibrator vibrator;
    private int a;

    public FallDetectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
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
        running = true;
        //在通知栏上显示服务运行
        showInNotification();
        a = 0;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "FallDetectionService.onStartCommand");
        detectThread = new DetectThread();
        detectThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        fallSensorManager.unregisterSensor();
        super.onDestroy();
    }

    //开一个线程用于检测跌倒
    class DetectThread extends Thread {
        @Override
        public void run() {
            fall.fallDetection();
            Log.d(TAG, "DetectThread.start()");
            while (running) {
                if (fall.isFell()) {
                    Log.e(TAG, "跌倒了");
                    running = false;
                    if (a==0) {
                        new Handler(Looper.getMainLooper()).post(runnable);
                        fall.setFell(false);
                        fall.cleanData();
                        //负责重启服务
                        FallEvent fallEvent = new FallEvent();
                        fallEvent.setService_state(0);
                        EventBus.getDefault().post(fallEvent);
                    }
                    break;
                }
            }
        }
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startVibrate();
            a++;
            showAlertDialog();
        }
    };

    /*
开始震动
 */
    private void startVibrate() {
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 500, 100, 500};
        vibrator.vibrate(pattern, 2);
    }

    /*
    停止震动
     */
    private void stopVibrate() {
        vibrator.cancel();
    }

    /*
弹窗报警
 */
    private void showAlertDialog() {
        try {
            TextView countingView = new TextView(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    this.getApplicationContext());
            builder.setTitle("跌倒警报");
            builder.setView(countingView);
            builder.setMessage("检测到跌倒发生，是否发出警报？");
            builder.setIcon(R.mipmap.app_icon);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    stopVibrate();
                    dialog.dismiss();
                }
            });
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }

    }

    /*
    在通知栏上显示服务运行
     */
    private void showInNotification() {
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("老人跌到检测")
                .setContentText("老人跌倒检测正在运行")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

}
