package com.god.yb.testgitdemo.Receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.god.yb.testgitdemo.activities.LockScreenActivity;

/**
 * Created by yaobing on 2018/6/15.
 * Description xxx
 */

public class LockReceiver extends BroadcastReceiver {

    private static final String TAG = "LockReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG,action);
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i(TAG,"lock_on_screen_off");
            Intent lockscreen = new Intent(context,LockScreenActivity.class);
            lockscreen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(lockscreen);
        }else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.i(TAG,"lock_off_screen_on");
        }else if (action.contains("com.agold.hy.ptt")) {
            Log.i(TAG,"282的按键广播");
        }else if (action.contains("com.agold.hy.sos")) {
            Log.i(TAG,"281的按键广播");
        }else if (action.contains("SEND_RECEIVER")) {
            Log.i(TAG,"281的按键广播");
        }else if (action.contains(Intent.EXTRA_KEY_EVENT)) {
            Log.i(TAG,"耳机按键的按键广播");
        }else if (action.contains(Intent.ACTION_MEDIA_BUTTON)) {
            Log.i(TAG,"耳机按键的按键广播");
        }else if (action.contains(Intent.ACTION_CONFIGURATION_CHANGED)) {
            Log.i(TAG,"dscsdvsdvsdvsvdv");
        }
    }
}
