package com.god.yb.testgitdemo.Utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.god.yb.testgitdemo.R;

/**
 * Created by yaobing on 2018/6/5.
 * Description xxx
 */

public class KeyUtils {
    private Context mContext;
    private KeyboardView keyboardView;
    private Keyboard keyboard;
    private EditText editText;
    private String t;

    public KeyUtils(Context context, EditText editText) {
        mContext = context;
        this.editText = editText;
        keyboard = new Keyboard(mContext, R.xml.number);
        keyboardView = (KeyboardView) ((Activity) context).findViewById(R.id.my_view);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);
        keyboardView.setVisibility(View.GONE);
    }

    public void show() {
        keyboardView.setVisibility(View.VISIBLE);
    }
    public void hide() {
        keyboardView.setVisibility(View.GONE);
    }

    private static final String TAG = "KeyUtils";
    //监听
    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
            Log.e(TAG, "onPress=======:" + primaryCode);
        }

        @Override
        public void onRelease(int primaryCode) {
            t = String.valueOf(editText.getText());
            switch (primaryCode) {
                case 10:
                    editText.setText(String.valueOf(editText.getText())+1);
                    break;
                case 20:
                    editText.setText(String.valueOf(editText.getText())+2);
                    break;
                case 30:
                    editText.setText(String.valueOf(editText.getText())+3);
                    break;
                case 40:
                    editText.setText(String.valueOf(editText.getText())+4);
                    break;
                case 50:
                    editText.setText(String.valueOf(editText.getText())+5);
                    break;
                case 60:
                    editText.setText(String.valueOf(editText.getText())+6);
                    break;
                case 70:
                    editText.setText(String.valueOf(editText.getText())+7);
                    break;
                case 80:
                    editText.setText(String.valueOf(editText.getText())+8);
                    break;
                case 90:
                    editText.setText(String.valueOf(editText.getText())+9);
                    break;
                case 5:
                    if (!TextUtils.isEmpty(t)&&!t.contains(".")) {
                        int i = Integer.parseInt(String.valueOf(editText.getText()));
                        i++;
                        editText.setText(i+"");
                    }else if (t.contains(".")) {
                        double d = Double.parseDouble(String.valueOf(editText.getText()));
                        d++;
                        editText.setText(d+"");
                    }
                    break;
                case 6:
                    if (!TextUtils.isEmpty(t)&&!t.contains(".")) {
                        int i = Integer.parseInt(String.valueOf(editText.getText()));
                        i--;
                        editText.setText(i+"");

                    }else if (t.contains(".")) {
                        double d = Double.parseDouble(String.valueOf(editText.getText()));
                        d--;
                        editText.setText(d+"");
                    }
                    break;
                case 7:
                    if (!TextUtils.isEmpty(t)) {
                        editText.setText(String.valueOf(editText.getText())+".");
                    }else {
                        editText.setText(String.valueOf(editText.getText())+".");
                    }
                    break;
            }
            editText.setSelection(editText.getText().length());
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Log.e(TAG, "onkey=====primaryCode:" + primaryCode + "");
        }

        @Override
        public void onText(CharSequence text) {
            Log.e(TAG, String.valueOf(text));
        }

        @Override
        public void swipeLeft() {
            Log.e(TAG, "swipeLeft");
        }

        @Override
        public void swipeRight() {
            Log.e(TAG, "swipeRight");
        }

        @Override
        public void swipeDown() {
            Log.e(TAG, "swipeDown");
        }

        @Override
        public void swipeUp() {
            Log.e(TAG, "swipeUp");
        }
    };

}
