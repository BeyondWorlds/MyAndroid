package com.wq.view.dialog;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wq.allandroid.R;
import com.wq.base.BaseActivity;


public class DialogActivity extends BaseActivity {

    private TextView tv_size;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog(DialogActivity.this).showLocateDialog();
            }
        });
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();
        int large = activityManager.getLargeMemoryClass();
        tv_size = (TextView) findViewById(R.id.tv_size);
        tv_size.setText("currentMemory=" + memClass + "  largeMemory=" + large);
    }


}
