package com.god.yb.testgitdemo.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.activities.NavigationActivity;

/**
 * Created by yaobing on 2018/3/8.
 * Description xxx
 */

public class FragmentA extends Fragment {
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
        final NavigationActivity navigationActivity = (NavigationActivity) getActivity();
        View root = inflater.inflate(R.layout.fragment_1, container, false);
        TextView view = root.findViewById(R.id.tv_fragment_a_content_a);
        view.setText(mPara);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button_1 = navigationActivity.findViewById(R.id.bt_nav_1);
                button_1.setText("通过F改变A");
            }
        });
        return root;
    }

    public static FragmentA newInstense(String mPara) {
        FragmentA fragmentA = new FragmentA();
        Bundle bundle = new Bundle();
        bundle.putString(Fragment_key, mPara);
        fragmentA.setArguments(bundle);
        return fragmentA;
    }
}
