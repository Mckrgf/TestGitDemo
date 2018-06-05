package com.god.yb.testgitdemo.Utils;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
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
            Log.e(TAG, "onRelease====:" + primaryCode);
            switch (primaryCode) {
                case 10:
                    editText.setText(String.valueOf(editText.getText())+1);
                    editText.setSelection(editText.getText().length());
                    break;
                case 20:
                    editText.setText(String.valueOf(editText.getText())+2);
                    editText.setSelection(editText.getText().length());
                    break;
                case 30:
                    editText.setText(String.valueOf(editText.getText())+3);
                    editText.setSelection(editText.getText().length());
                    break;
                case 40:
                    editText.setText(String.valueOf(editText.getText())+4);
                    editText.setSelection(editText.getText().length());
                    break;
                case 50:
                    editText.setText(String.valueOf(editText.getText())+5);
                    editText.setSelection(editText.getText().length());
                    break;
                case 60:
                    editText.setText(String.valueOf(editText.getText())+6);
                    editText.setSelection(editText.getText().length());
                    break;
                case 70:
                    editText.setText(String.valueOf(editText.getText())+7);
                    editText.setSelection(editText.getText().length());
                    break;
                case 80:
                    editText.setText(String.valueOf(editText.getText())+8);
                    editText.setSelection(editText.getText().length());
                    break;
                case 90:
                    editText.setText(String.valueOf(editText.getText())+9);
                    editText.setSelection(editText.getText().length());
                    break;
            }
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
