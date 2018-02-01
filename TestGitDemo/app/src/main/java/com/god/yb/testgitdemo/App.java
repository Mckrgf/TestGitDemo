package com.god.yb.testgitdemo;

import android.app.Application;

import com.god.yb.testgitdemo.DBBean.DaoMaster;
import com.god.yb.testgitdemo.DBBean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tfhr on 2018/2/1.
 */

public class App extends Application{
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
