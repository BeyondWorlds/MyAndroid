package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


public class ScaleActivity extends Activity {

	private Button mBtnStart;
	private ImageView mImg;
	private ScaleAnimation mAnimation;

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
		// 四个坐标分别表示起始、结束x坐标上的伸缩尺寸，起始、结束y坐标上的伸缩尺寸
		mAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f);
		mAnimation.setDuration(3000);// 动画运行时间
	}
}
