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

import java.math.BigDecimal;

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
    private final DoubleUtil doubleUtil;

    public KeyUtils(Context context, EditText editText) {
        mContext = context;
        this.editText = editText;
        keyboard = new Keyboard(mContext, R.xml.number);
        keyboardView = (KeyboardView) ((Activity) context).findViewById(R.id.my_view);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);
        keyboardView.setVisibility(View.GONE);
        doubleUtil = new DoubleUtil();
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
                    editText.setText(String.valueOf(editText.getText()) + 1);
                    break;
                case 20:
                    editText.setText(String.valueOf(editText.getText()) + 2);
                    break;
                case 30:
                    editText.setText(String.valueOf(editText.getText()) + 3);
                    break;
                case 40:
                    editText.setText(String.valueOf(editText.getText()) + 4);
                    break;
                case 50:
                    editText.setText(String.valueOf(editText.getText()) + 5);
                    break;
                case 60:
                    editText.setText(String.valueOf(editText.getText()) + 6);
                    break;
                case 70:
                    editText.setText(String.valueOf(editText.getText()) + 7);
                    break;
                case 80:
                    editText.setText(String.valueOf(editText.getText()) + 8);
                    break;
                case 90:
                    editText.setText(String.valueOf(editText.getText()) + 9);
                    break;
                case 1:
                    editText.setText("正常");
                    break;
                case 2:
                    editText.setText("隐患");
                    break;
                case 3:
                    keyboardView.setVisibility(View.GONE);
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(true);
                    break;
                case 4:
                    t = t.substring(0,t.length()-1);
                    editText.setText(t);
                    break;
                case 5://正号
                    if (t.contains("-") || t.contains("+")) {
                        t = t.replace("-", "");
                        t = t.replace("+", "");
                    }
                    editText.setText("+" + t);
                    break;
                case 6://负号
                    if (t.contains("-") || t.contains("+")) {
                        t = t.replace("-", "");
                        t = t.replace("+", "");
                    }
                    editText.setText("-" + t);
                    break;
                case 7:
                    if (!TextUtils.isEmpty(t)) {
                        editText.setText(String.valueOf(editText.getText()) + ".");
                    } else {
                        editText.setText(String.valueOf(editText.getText()) + ".");
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

    class DoubleUtil {
        /**
         * double 相加
         *
         * @param d1
         * @param d2
         * @return
         */
        public double sum(double d1, double d2) {
            BigDecimal bd1 = new BigDecimal(Double.toString(d1));
            BigDecimal bd2 = new BigDecimal(Double.toString(d2));
            return bd1.add(bd2).doubleValue();
        }


        /**
         * double 相减
         *
         * @param d1
         * @param d2
         * @return
         */
        public double sub(double d1, double d2) {
            BigDecimal bd1 = new BigDecimal(Double.toString(d1));
            BigDecimal bd2 = new BigDecimal(Double.toString(d2));
            return bd1.subtract(bd2).doubleValue();
        }

        /**
         * double 乘法
         *
         * @param d1
         * @param d2
         * @return
         */
        public double mul(double d1, double d2) {
            BigDecimal bd1 = new BigDecimal(Double.toString(d1));
            BigDecimal bd2 = new BigDecimal(Double.toString(d2));
            return bd1.multiply(bd2).doubleValue();
        }


        /**
         * double 除法
         *
         * @param d1
         * @param d2
         * @param scale 四舍五入 小数点位数
         * @return
         */
        public double div(double d1, double d2, int scale) {
            //  当然在此之前，你要判断分母是否为0，
            //  为0你可以根据实际需求做相应的处理
            BigDecimal bd1 = new BigDecimal(Double.toString(d1));
            BigDecimal bd2 = new BigDecimal(Double.toString(d2));
            return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        /**
         * double 转 string 去掉后面锝0
         *
         * @param i
         * @return
         */
        public String getString(double i) {
            String s = String.valueOf(i);
            if (s.indexOf(".") > 0) {
                //正则表达
                s = s.replaceAll("0+?$", "");//去掉后面无用的零
                s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
            }
            return s;
        }


    }

}
