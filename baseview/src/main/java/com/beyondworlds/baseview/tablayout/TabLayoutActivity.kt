package com.beyondworlds.baseview.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.beyondworlds.baseview.BlankFragment
import com.beyondworlds.baseview.R
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : AppCompatActivity() {

    private val mFragments: Array<Fragment> = arrayOf<Fragment>(BlankFragment(), BlankFragment())

    private val mTitles = arrayOf("Fragment1", "Fragment2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)
        initViewPager()
    }

    fun initViewPager() {
        viewpager.setAdapter(object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mFragments.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mTitles[position]
            }
        })

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })

        tablayout.setupWithViewPager(viewpager)
    }

    override fun onResume() {
        super.onResume()
        //布局中   app:tabIndicator="@drawable/layer_tab_indicator"  设置固定宽度
        //设置跟随ItemText的宽度变化
//        ViewUtil.setTabWidth(tablayout, 30)
    }
}