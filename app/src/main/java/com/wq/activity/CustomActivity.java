package com.wq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.allandroid.R;
import com.wq.base.BaseActivity;
import com.wq.view.customview.AutoScrollTextView;
import com.wq.view.dialog.MyDialog;
import com.wq.view.dialog.MyDialogCallback;


public class CustomActivity extends BaseActivity implements View.OnClickListener {

    private AutoScrollTextView mScrollText;
    private Button mBtnCallback, mBtnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mScrollText = (AutoScrollTextView) findViewById(R.id.auto_textview);
        mScrollText.init(getWindowManager());
        mScrollText.startScroll();
        initView();
    }

    private void initView() {
        mBtnCallback = (Button) findViewById(R.id.btn_callback);
        mBtnFragment = (Button) findViewById(R.id.btn_fragment);
        mBtnCallback.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_callback:
                new MyDialogCallback(this, new MyDialogCallback.onCheckListener() {

                    @Override
                    public void onYes() {
                    }

                    @Override
                    public void onNo() {
                    }
                }).show();
                ;
                break;
            case R.id.btn_fragment:
                new MyDialog(this).showMyCode("heheh");
                break;

            default:
                break;
        }
    }
}
