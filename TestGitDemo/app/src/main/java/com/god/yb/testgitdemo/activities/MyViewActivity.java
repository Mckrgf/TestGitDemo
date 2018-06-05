package com.god.yb.testgitdemo.activities;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.KeyUtils;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyViewActivity extends BaseActivity {

    @BindView(R.id.et_test)
    EditText etTest;
    @BindView(R.id.my_view)
    KeyboardView myView;
    @BindView(R.id.hide)
    Button hide;
    private boolean aaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);

        ButterKnife.bind(this);
        final KeyUtils keyUtils = new KeyUtils(this,etTest);
        etTest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                ((Activity)getContext()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                try {
                    Class<EditText> cls = EditText.class;
                    Method setSoftInputShownOnFocus;
                    setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                    setSoftInputShownOnFocus.setAccessible(true);
                    setSoftInputShownOnFocus.invoke(etTest, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                aaa = hasFocus;
                int type = etTest.getInputType();
                if (type== 8194) {
                    if (hasFocus) {
                        keyUtils.show();
                    } else {
                        keyUtils.hide();
                    }
                }
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aaa) {
                    keyUtils.show();
                } else {
                    keyUtils.hide();
                }
                aaa = !aaa;
            }
        });

    }
}
