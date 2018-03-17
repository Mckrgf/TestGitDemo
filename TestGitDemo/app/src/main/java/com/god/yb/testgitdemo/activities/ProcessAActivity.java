package com.god.yb.testgitdemo.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.god.yb.testgitdemo.App;
import com.god.yb.testgitdemo.Book;
import com.god.yb.testgitdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * 公有进程，包名为随便写的，别的app也可以访问
 */

public class ProcessAActivity extends BaseActivity {
    private static final String TAG = "ProcessAActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_a);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
//                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApp(),"申请成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getApp(),"申请失败",Toast.LENGTH_SHORT).show();
//                }
                Book book = new Book("gaga", 001);
                File file = new File(App.commonPath);
                if (!file.exists()) {
                    try {
                        file.mkdirs();
                        File file1 = new File(App.cachePath);
                        write(book, file1);

                    } catch (Exception e) {
                        Log.i(TAG, e.toString());
                    }

                } else {
                    try {
                        file = new File(App.cachePath);
                        write(book, file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    private void write(Book book, File file1) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file1, true);
        fileOutputStream.write(book.BookName.getBytes());
        fileOutputStream.write(97);
        fileOutputStream.write(98);
        fileOutputStream.write(99);
        fileOutputStream.close();
    }
}
