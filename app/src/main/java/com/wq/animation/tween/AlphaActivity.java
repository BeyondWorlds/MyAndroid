package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


public class AlphaActivity extends Activity {

	private Button mBtnStart;
	private ImageView mImg;
	private AlphaAnimation mAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_animation);
		initView();
		setAnimation();
	}

	private void initView() {
		mBtnStart = (Button) findViewById(R.id.btn_start);
		mBtnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mImg.startAnimation(mAnimation);
			}
		});
		mImg = (ImageView) findViewById(R.id.img);
	}

	private void setAnimation() {
		// 两个参数分别表示起始和结束时的透明度
		mAnimation = new AlphaAnimation(0.1f, 1.0f);
		mAnimation.setDuration(3000);// 动画运行时间
		mAnimation.setFillAfter(true);// 保持动画结束时的状态
	}
}
