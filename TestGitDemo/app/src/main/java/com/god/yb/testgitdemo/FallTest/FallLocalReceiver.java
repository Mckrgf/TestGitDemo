package com.god.yb.testgitdemo.FallTest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

public class FallLocalReceiver extends BroadcastReceiver{

    private TextView countingView;
    private Dialog dialog;
    private Timer timer;
    private SharedPreferences sharedPreferences;
    private Vibrator vibrator;
    private boolean isVibrate;
    private Context context;
    private final String TAG = "liuweixiang";


    public FallLocalReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "FallLocalReceiver.onReceive()");
        this.context = context;
//        showAlertDialog();

        ToastUtil.showToast(context,"监测到跌落");

        sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        isVibrate = sharedPreferences.getBoolean("pre_key_vibrate", true);
        if(isVibrate){
//            startVibrate();
        }
    }


    /*
    弹窗报警
     */
    private void showAlertDialog() {
        countingView = new TextView(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(
                context.getApplicationContext());
        builder.setTitle("跌倒警报");
        builder.setView(countingView);
        builder.setMessage("检测到跌倒发生，是否发出警报？");
        builder.setIcon(R.mipmap.app_icon);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
                dialog.dismiss();
                if(isVibrate){
                    stopVibrate();
                }
                Intent startIntent = new Intent(context, FallDetectionService.class);
                context.startService(startIntent);
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY );
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
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 > 0){
                //动态显示倒计时
                countingView.setText("                         "
                        + msg.arg1 + "秒后自动报警");
            }else{
                //倒计时结束自动关闭
                if(dialog != null){
                    dialog.dismiss();
                    if(isVibrate){
                        stopVibrate();
                    }
                }
                timer.cancel();
            }
        }
    };

    /*
    开始震动
     */
    private void startVibrate(){
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {100, 500, 100, 500};
        vibrator.vibrate(pattern, 2);
    }
    /*
    停止震动
     */
    private void stopVibrate(){
        vibrator.cancel();
    }
}
