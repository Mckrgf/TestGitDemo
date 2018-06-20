package com.god.yb.testgitdemo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.viewSearch.model.Bean;
import com.god.yb.testgitdemo.viewSearch.widge.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaobing on 2018/3/8.
 * Description xxx
 */

public class FragmentF extends Fragment implements SearchView.SearchViewListener {
    private String mPara;
    private Activity mActivity;
    private static String Fragment_key = "test_key";

    private SearchView searchView;

    private ArrayAdapter<String> autoCompleteAdapter;

    private List<Bean> dbData;

    private List<String> autoCompleteData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mPara = getArguments().getString(Fragment_key);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_3, container, false);
        TextView view = root.findViewById(R.id.tv_fragment_a_content_a);

        initData();
        initViews(root);

        view.setText(mPara);
        return root;
    }

    /**
     * 初始化视图
     */
    private void initViews(View view) {
        searchView = view.findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //从数据库获取数据
        getDbData();
        //初始化自动补全数据
        getAutoCompleteData(null);
    }


    /**
     * 获取db 数据
     */
    private void getDbData() {
        int size = 100;
        dbData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dbData.add(new Bean(R.drawable.icon, "android开发必备技能" + (i + 1), "Android自定义view——自定义搜索view", i * 20 + 2 + ""));
        }
    }


    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>();
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    autoCompleteData.add(dbData.get(i).getTitle());
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    public static FragmentF newInstense(String mPara) {
        FragmentF fragmentA = new FragmentF();
        Bundle bundle = new Bundle();
        bundle.putString(Fragment_key, mPara);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }


    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }
}
