package com.wq.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.wq.allandroid.R;
import com.wq.view.customview.IndicatorViewPager;

public class TestListActivity extends AppCompatActivity {
    private IndicatorViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        viewPager = (IndicatorViewPager) findViewById(R.id.viewPager);
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestListActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });
    }
}
