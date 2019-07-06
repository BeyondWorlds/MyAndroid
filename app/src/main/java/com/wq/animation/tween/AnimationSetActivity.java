package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


/**
 * AnimationSet能让多个动画同时进行
 * 
 * @author Administrator
 *
 */
public class AnimationSetActivity extends Activity {

	private Button mBtnStart;
	private ImageView mImg;
	private RotateAnimation mRotateAnimation;
	private ScaleAnimation mScaleAnimation;
	private TranslateAnimation mTranslateAnimation;
	private AlphaAnimation mAlphaAnimation;
	private AnimationSet mAnimationSet;

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
				mImg.startAnimation(mAnimationSet);
			}
		});
		mImg = (ImageView) findViewById(R.id.img);
	}

	private void setAnimation() {
		// 前两个参数分别表示起始和结束时的角度，后面表示中心点在控件的的哪个位置
		mRotateAnimation = new RotateAnimation(0f, 1080f);
		// 前面四个参数分别表示横纵坐标的初始结束大小，后面表示中心点在控件的的哪个位置
		mScaleAnimation = new ScaleAnimation(0.2f, 2.0f, 0.5f, 2.0f);

		mTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 2.0f);

		mAlphaAnimation = new AlphaAnimation(1.0f, 0.2f);
		// true表示使用animation的interpolator，false表示使用自己的
		mAnimationSet = new AnimationSet(true);
		mAnimationSet.addAnimation(mRotateAnimation);
		mAnimationSet.addAnimation(mScaleAnimation);
		// 常用公共属性
		mAnimationSet.setDuration(3000);// 动画运行时间
		mAnimationSet.setRepeatCount(3);// 设置重复次数
		mAnimationSet.setInterpolator(new BounceInterpolator());
		mAnimationSet.setStartOffset(1000);// 每次动画间隔时间
		mAnimationSet.setFillAfter(true);// 动画保持在结束时的画面

	}
}
