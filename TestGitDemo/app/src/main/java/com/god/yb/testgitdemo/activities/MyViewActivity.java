package com.god.yb.testgitdemo.activities;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.KeyUtils;

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
                aaa = hasFocus;
                int type = etTest.getInputType();
                if (type== InputType.TYPE_NUMBER_VARIATION_NORMAL) {
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
