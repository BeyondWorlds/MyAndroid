package com.wq.animation.property;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.allandroid.R;


/**
 * 属性动画
 * 
 * @author Administrator
 *
 */
public class BasePropertyActivity extends Activity implements OnClickListener {

	private Button mBtnTranslate, mBtnAlpha, mBtnScale, mBtnRotate;
	private ImageView mImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_property);
		initView();
		initAnimation();
	}

	private void initView() {

		mBtnTranslate = (Button) findViewById(R.id.btn_translate);
		mBtnAlpha = (Button) findViewById(R.id.btn_alpha);
		mBtnScale = (Button) findViewById(R.id.btn_scale);
		mBtnRotate = (Button) findViewById(R.id.btn_rotate);

		mBtnTranslate.setOnClickListener(this);
		mBtnAlpha.setOnClickListener(this);
		mBtnScale.setOnClickListener(this);
		mBtnRotate.setOnClickListener(this);

		mImg = (ImageView) findViewById(R.id.iv);
	}

	private void initAnimation() {
		// ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
		// anim.setDuration(300);
		// anim.start();

	}

	/**
	 * 一般只有一个值。就是指最终值。而有多个，第一个一般为初始值
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_translate:
			// 水平移动，向左移出屏幕，再平移回来，若第三个参数改成其他数，则表示在原来的位置上，再向右移动的距离
			float curTranslationX = mImg.getTranslationX();

			ObjectAnimator AnimatorTranslate = ObjectAnimator.ofFloat(mImg, "translationX", curTranslationX + 100,
					-500f, curTranslationX);
			AnimatorTranslate.setDuration(5000);  
			AnimatorTranslate.start();
			break;
		case R.id.btn_alpha:
			// 从不透明到完全透明，再回到不透明
			ObjectAnimator AnimatorAlpha = ObjectAnimator.ofFloat(mImg, "alpha", 1f, 0f, 1f);
			AnimatorAlpha.setDuration(5000);
			AnimatorAlpha.start();
			break;
		case R.id.btn_scale:
			// 垂直方向，放大三倍，再回来
			ObjectAnimator AnimatorScale = ObjectAnimator.ofFloat(mImg, "scaleY", 1f, 3f, 1f);
			AnimatorScale.setDuration(5000);
			AnimatorScale.start();
			break;
		case R.id.btn_rotate:
			// 旋转360度
			ObjectAnimator AnimatorRotate = ObjectAnimator.ofFloat(mImg, "rotation", 0f, 360f);
			AnimatorRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					Log.e("animatin", animation.getAnimatedValue() + "");
				}

			});
			AnimatorRotate.setDuration(5000);
			AnimatorRotate.start();

			break;

		default:
			break;
		}
	}
}
