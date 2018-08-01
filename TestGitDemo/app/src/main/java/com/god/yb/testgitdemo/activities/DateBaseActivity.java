package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.god.yb.testgitdemo.Adapter.UserListAdapter;
import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.User;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.OkHttp.CommonCallBack;
import com.god.yb.testgitdemo.R;
import com.god.yb.testgitdemo.Utils.MyDateUtils;
import com.god.yb.testgitdemo.Utils.StringUtil;
import com.god.yb.testgitdemo.Utils.ToastUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateBaseActivity extends BaseActivity {
    private static final String TAG = "DateBaseActivity";

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;
    private UserListAdapter userListAdapter;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_base);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvUserList.setLayoutManager(manager);
        userListAdapter = new UserListAdapter(getContext());
    }

    private void initData() {

        for (int i = 0; i < 1000; i++) {
            //add一个bean
            daoSession = ((App) getApplication()).getDaoSession();
            userDao = daoSession.getUserDao();
            User user = new User();
            user.setPassword("123");
            user.setUsername(StringUtil.getRandomString(4));
            userDao.insert(user);
            Log.i(TAG, "Add a User bean to Datebase" + user.getUsername());

        }





        List user_list = userDao.queryBuilder().build().list();

        userListAdapter.setData(user_list);
        rvUserList.setAdapter(userListAdapter);

//        OkGo.<HashMap>get("https://news-at.zhihu.com/api/4/news/latest")
//                .tag(this)
//                .execute(new CommonCallBack<HashMap>(true,getContext()) {
//                    @Override
//                    public void onSuccess(Response<HashMap> response) {
//                        HashMap map =  response.body();
//                        Log.i(TAG,map.toString());
//                        Toast.makeText(getContext(),String.valueOf(map),Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        super.onFinish();
//                        Log.i(TAG,"完成");
//                    }
//
//                    @Override
//                    public void onError(Response<HashMap> response) {
//                        super.onError(response);
//                        Log.e(TAG, String.valueOf(response.getException()));
//                    }
//                });
    }
}
