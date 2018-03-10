package com.god.yb.testgitdemo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.god.yb.testgitdemo.R;

/**
 * Created by yaobing on 2018/3/8.
 * Description xxx
 */

public class FragmentC extends Fragment {
    private String mPara;
    private Activity mActivity;
    private static String Fragment_key = "test_key";
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
        view.setText(mPara); return root;
    }

    public static FragmentC newInstense(String mPara) {
        FragmentC fragmentA = new FragmentC();
        Bundle bundle = new Bundle();
        bundle.putString(Fragment_key,mPara);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }
}
