package com.wq.view.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wq.allandroid.R;

import java.util.ArrayList;


/**
 * Created by ${wq} on 2017/6/13.
 * 开始继承ViewGroup发现视图显示不出来，ViewGroup必须重写onLayout方法
 */

public class IndicatorViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private ViewPager vp_guide;
    private LinearLayout ll_dot;
    private ArrayList<ImageView> mImageDots = new ArrayList<ImageView>();
    private int mCurrentItem = 0;
    private Context mContext;
    private Button btn_start_app;

    public IndicatorViewPager(Context context) {
        super(context);
    }

    public IndicatorViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.custom_indicator_viewpager, this, true);
        initView();
    }

    public void initView() {
        btn_start_app = (Button) findViewById(R.id.btn_start_app);
        btn_start_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(GuideActivity.this, SelectActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
        initViewPager();
    }

    private void initViewPager() {
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        vp_guide.setAdapter(new GuideAdapter(mContext));
        vp_guide.setCurrentItem(mCurrentItem);
        vp_guide.addOnPageChangeListener(this);
        initDotView();
    }

    private void initDotView() {
        ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();
        for (int i = 0; i < 4; i++) {
            ImageView image = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 12;
            if (i == mCurrentItem) {
                image.setImageResource(R.mipmap.circle_selected);
            } else {
                image.setImageResource(R.mipmap.circle);
            }
            ll_dot.addView(image, params);
            mImageDots.add(image);
        }
    }

    private void refreshDot() {
        for (int i = 0; i < 4; i++) {
            if (i == mCurrentItem) {
                mImageDots.get(i).setImageResource(R.mipmap.circle_selected);
            } else {
                mImageDots.get(i).setImageResource(R.mipmap.circle);
            }
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentItem = position;
        if (position == 3) {
            btn_start_app.setVisibility(View.VISIBLE);
        } else {
            btn_start_app.setVisibility(View.INVISIBLE);
        }
        refreshDot();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public IndicatorViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IndicatorViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * Created by Administrator on 2017/6/12.
     */

    class GuideAdapter extends PagerAdapter {
        private int mDrawables[] = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
        private Context mContext;

        @Override
        public int getCount() {
            return 4;
        }

        public GuideAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mDrawables[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
