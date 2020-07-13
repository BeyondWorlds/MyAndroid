package com.wq;

import android.Manifest;
import android.os.Bundle;

import com.example.utillibrary.PermissionUtil;
import com.wq.allandroid.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        PermissionUtil.INSTANCE.checkPermission(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 0, new PermissionUtil.OnPermissonCallback() {
            @Override
            public void isGrant(boolean grant) {

            }
        });
    }
}
