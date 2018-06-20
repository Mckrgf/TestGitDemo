package com.god.yb.testgitdemo.MyView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.god.yb.testgitdemo.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by yaobing on 2018/6/21.
 * Description xxx
 */

public class SuperSearchView extends LinearLayout {
    public ArrayList<String> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    //所有数据
    private ArrayList<String> datas;
    //查找后的数据
    private ArrayList<String> datas_search;

    public SuperSearchView(Context context) {
        super(context);
    }

    private static final String TAG = "SuperSearchView";

    public SuperSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View root = LayoutInflater.from(context).inflate(R.layout.view_super_search, this);
        EditText editText = root.findViewById(R.id.et_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                datas_search = null;
                datas_search = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    String data = datas.get(i);
                    if (data.contains(s)) {
                        datas_search.add(data);
                    }
                }
                Log.d(TAG, "获取到的数据大小: " + datas_search.size());
                // TODO: 2018/6/21 显示获取到的数据的列表
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
