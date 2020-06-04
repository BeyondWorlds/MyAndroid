package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


public class RotateActivity extends Activity {
	private Button mBtnStart;
	private ImageView mImg,img_point;
	private RotateAnimation mAnimation;

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
//				mImg.startAnimation(mAnimation);
				img_point.startAnimation(mAnimation);

			}
		});
		mImg = (ImageView) findViewById(R.id.img);
		img_point=findViewById(R.id.img_point);
	}

	private void setAnimation() {
		// 两个参数分别表示起始和结束时的角度
//		mAnimation = new RotateAnimation(0f, 270f);
		mAnimation = new RotateAnimation(0f, 90f,20,200);
		mAnimation.setDuration(3000);// 动画运行时间
		mAnimation.setFillAfter(true);
	}
}
