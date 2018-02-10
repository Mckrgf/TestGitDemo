package com.god.yb.testgitdemo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.god.yb.testgitdemo.R;

public class OtherActivity extends BaseActivity {
    private static final String TAG = "OtherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_a,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Log.i(TAG,"Add item");
                break;
            case R.id.delete_item:
                Log.i(TAG,"Detele item");
                break;
        }
        return true;
    }
}
