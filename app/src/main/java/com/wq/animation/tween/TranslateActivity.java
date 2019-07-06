package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


public class TranslateActivity extends Activity {
	private Button mBtnStart;
	private ImageView mImg;
	private TranslateAnimation mAnimation;

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
		// 第一个参数fromXDelta ,第二个参数toXDelta:分别是动画起始、结束时X坐标。
		// 第三个参数fromYDelta ,第四个参数toYDelta:分别是动画起始、结束时Y坐标。
		// 注意，这时他的坐标都是以控件最左上角为原点的
		// mAnimation = new TranslateAnimation(0, 100, 0, -100);
		/**
		 * 当X方向或者Y方向上的Type选择为Animation.ABSOLUTE时候，表示为绝对像素，和四个参数的构造函数意义一样
		 * 此时XValue和YValue参数的含义和四个参数的构造函数相同。
		 * 而当X方向或者Y方向上的Type选择为Animation.RELATIVE_TO_SELF或者
		 * Animation.RELATIVE_TO_PARENT时候，则表示相位移量了,前面的表示初始偏移量，后面的表示最终偏移量
		 */

		mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 2.0f);
		mAnimation.setDuration(3000);// 动画运行时间
	}
}
