package com.god.yb.testgitdemo.FallTest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.god.yb.testgitdemo.activities.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class FallDetectionService extends Service {

    private FallSensorManager fallSensorManager;
    public Fall fall;
    private final int FELL = 0;
//    private final int TIME = 1;
    private boolean running = false;
//    private TextView countingView;
//    private Dialog dialog;
//    private Timer timer;
    private final String TAG = "liuweixiang";
    private DetectThread detectThread;
    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private FallLocalReceiver fallLocalReceiver;
    private Timer timer;
    private Dialog dialog;

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
        Log.d(TAG, "FallDetectionService.onCreate()");

        fallSensorManager = new FallSensorManager(this);
        fallSensorManager.initSensor();
        fallSensorManager.registerSensor();
        fall = new Fall();
        fall.setThresholdValue(25,5);
        running = true;
        //在通知栏上显示服务运行
        showInNotification();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.broadcast.FALL_LOCAL_BROADCAST");
        fallLocalReceiver = new FallLocalReceiver();
        localBroadcastManager.registerReceiver(fallLocalReceiver, intentFilter);
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
        localBroadcastManager.unregisterReceiver(fallLocalReceiver);
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

                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showToast(FallDetectionService.this,"跌倒检测到啦");
                            showAlertDialog();
                        }
                    };
                    new Handler(Looper.getMainLooper()).post(runnable);
                    fall.setFell(false);
                    fall.cleanData();
//                    stopSelf();

                }
            }
        }
    }



    /*
弹窗报警
 */
    private void showAlertDialog() {
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
                timer.cancel();
                dialog.dismiss();
//                if(isVibrate){
//                    stopVibrate();
//                }
//                stopAlarm();
//                Intent startIntent = new Intent(context, FallDetectionService.class);
//                context.startService(startIntent);
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        countDown();
        dialog.show();
        Log.d(TAG, "dialog.create()");
    }

    /*
倒计时
 */
    private void countDown() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            int countTime = 10;
            @Override
            public void run() {
                if(countTime > 0){
                    countTime --;
                }
                Message msgTime = handler.obtainMessage();
                msgTime.arg1 = countTime;
                handler.sendMessage(msgTime);
            }
        };
        timer.schedule(timerTask, 50, 1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case FELL:
                    Log.e(TAG, "FELL");
                    //报警
//                    showAlertDialog();
                    Intent intent = new Intent("com.broadcast.FALL_LOCAL_BROADCAST");
                    localBroadcastManager.sendBroadcast(intent);

                    break;

//                case TIME:
//                    if(msg.arg1 > 0){
//                        //动态显示倒计时
//                        countingView.setText("                          "
//                                + msg.arg1 + "秒后自动报警");
//                    }else{
//                        //倒计时结束自动关闭
//                        if(dialog != null){
//                            dialog.dismiss();
//                        }
//                        timer.cancel();
//                    }
//
//                    break;
            }

        }
    };

//    /*
//    弹窗报警
//     */
//    private void showAlertDialog() {
//        countingView = new TextView(getApplicationContext());
//        AlertDialog.Builder builder = new AlertDialog.Builder(
//                getApplicationContext());
//        builder.setTitle("跌倒警报");
//        builder.setView(countingView);
//        builder.setMessage("检测到跌倒发生，是否发出警报？");
//        builder.setIcon(R.drawable.ic_warning);
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                timer.cancel();
//                dialog.dismiss();
//                running = true;
//                Log.d(TAG, running + "");
//                detectThread.interrupt();
//                detectThread = null;
//                if(detectThread == null){
//                    detectThread = new DetectThread();
//                    detectThread.start();
//                }
//            }
//        });
//        dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        countDown();
//        dialog.show();
//        Log.d(TAG, "dialog.create()");
//    }
//
//    /*
//    倒计时
//     */
//    private void countDown() {
//        timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            int countTime = 6;
//            @Override
//            public void run() {
//                if(countTime > 0){
//                    countTime --;
//                }
//                Message msgTime = handler.obtainMessage();
//                msgTime.what = TIME;
//                msgTime.arg1 = countTime;
//                handler.sendMessage(msgTime);
//            }
//        };
//        timer.schedule(timerTask, 100, 1000);
//    }

    /*
    在通知栏上显示服务运行
     */
    private void showInNotification() {
        Intent intent = new Intent(this,HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("老人跌到检测")
                .setContentText("老人跌倒检测正在运行")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.app_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

}
