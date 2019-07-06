package com.wq.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


/**
 * @author 动画使用xml和插值器
 * @functions AccelerateDecelerateInterpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
 *            AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速
 *            AnticipateInterpolator 开始的时候向后然后向前甩
 *            AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
 *            BounceInterpolator 动画结束的时候弹起 CycleInterpolator
 *            动画循环播放特定的次数，速率改变沿着正弦曲线 DecelerateInterpolator 在动画开始的地方快然后慢
 *            LinearInterpolator 以常量速率改变 OvershootInterpolator 向前甩一定值后再回到原来位置
 * @author Administrator
 *
 */
public class AnimationUtilActivity extends Activity {
	private Button mBtnStart;
	private ImageView mImg;
	private Animation mAnimation;

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
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
	}
}
