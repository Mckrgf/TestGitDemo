package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.god.yb.testgitdemo.Adapter.UserListAdapter;
import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.DBBean.DaoSession;
import com.god.yb.testgitdemo.DBBean.UserDao;
import com.god.yb.testgitdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateBaseActivity extends BaseActivity {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;
    private UserListAdapter userListAdapter;

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
        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        UserDao userDao = daoSession.getUserDao();
        List user_list = userDao.queryBuilder().build().list();

        userListAdapter.setData(user_list);
        rvUserList.setAdapter(userListAdapter);
    }
}
