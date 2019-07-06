package com.wq.view.viewpager.vptransformer.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wq.allandroid.R;
import com.wq.view.viewpager.vptransformer.transformer.CubeOutTransformer;

import java.util.ArrayList;

public class VPTransformerActivity extends FragmentActivity implements
        OnPageChangeListener, OnCheckedChangeListener {
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragments;
    private RadioGroup mRGTab;
    private RadioButton mRBtn1, mRBtn2, mRBtn3, mRBtn4;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 100) {

            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vp_transformer);
        initFragments();
        initView();
        initViewPager();
    }

    private void initView() {
        mRGTab = (RadioGroup) findViewById(R.id.rg_tab);
        mRGTab.setOnCheckedChangeListener(this);
        mRBtn1 = (RadioButton) findViewById(R.id.rbtn1);
        mRBtn2 = (RadioButton) findViewById(R.id.rbtn2);
        mRBtn3 = (RadioButton) findViewById(R.id.rbtn3);
        mRBtn4 = (RadioButton) findViewById(R.id.rbtn4);
    }

    private void initFragments() {
        mFragments = new ArrayList<Fragment>();
        FirstTransFragment homeFragment = new FirstTransFragment();
        SecondTransFragment childFragment = new SecondTransFragment();
        ThirdTransFragment localFragment = new ThirdTransFragment();
        FourFragment fourFragment = new FourFragment();
        mFragments.add(homeFragment);
        mFragments.add(childFragment);
        mFragments.add(localFragment);
        mFragments.add(fourFragment);
    }

    private void initViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(fragmentPagerAdapter);
        setViewPagerDuration(mViewPager, VPTransformerActivity.this, 1000);
        CubeOutTransformer cubeTransformer = new CubeOutTransformer();
        mViewPager.setPageTransformer(true, cubeTransformer);
        fragmentPagerAdapter.setFragments(mFragments);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    @SuppressWarnings("unused")
    private void setViewPagerDuration(ViewPager viewpager, Context context,
                                      int duration) {
        ViewPagerScroller scroller = new ViewPagerScroller(VPTransformerActivity.this);

        scroller.setScrollDuration(0);

        scroller.initViewPagerScroll(viewpager); // 这个是设置切换过渡时间为0毫秒

        viewpager.setCurrentItem(0);

        ViewPagerScroller scroller1 = new ViewPagerScroller(context);

        scroller.setScrollDuration(duration);

        scroller.initViewPagerScroll(viewpager);// 这个是设置切换过渡时间为2秒

    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private FragmentManager fm;
        private ArrayList<Fragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
            // TODO Auto-generated constructor stub
        }

        public MyFragmentPagerAdapter(FragmentManager fm,
                                      ArrayList<Fragment> mFragments) {
            super(fm);
            this.fm = fm;
            this.fragments = mFragments;
        }

        @Override
        public int getCount() {
            if (fragments == null) {
                return 0;
            }
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public void setFragments(ArrayList<Fragment> fragments) {
            if (this.fragments != null) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.fragments) {
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            Object obj = super.instantiateItem(container, position);
            return obj;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub

        switch (arg0) {
            case 0:
                mRGTab.check(R.id.rbtn1);
                break;
            case 1:
                mRGTab.check(R.id.rbtn2);
                break;
            case 2:
                mRGTab.check(R.id.rbtn3);
                break;
            case 3:
                mRGTab.check(R.id.rbtn4);
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        // TODO Auto-generated method stub
        switch (arg1) {
            case R.id.rbtn1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rbtn2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rbtn3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rbtn4:
                mViewPager.setCurrentItem(3);
                break;

            default:
                break;
        }
    }

}
