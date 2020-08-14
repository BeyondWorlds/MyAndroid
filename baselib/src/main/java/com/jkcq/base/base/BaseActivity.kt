package com.jkcq.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.FileUtils
import com.example.utillibrary.PermissionUtil
import com.example.utillibrary.ui.StatusBarUtil
import com.jkcq.base.app.Preference

/**
 *  Created by BeyondWorlds
 *  on 2020/7/23
 */
abstract class BaseActivity() : AppCompatActivity() {

    var mUserId: String by Preference(Preference.USER_ID, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomContentView()
        initHeander()
        initView()
        initEvent()
        setStatusBar()
//        setSupportActionBar(mToolbar)
        initData()
    }

    /**
     * 添加公共标题时，重写该方法
     */
    open fun setCustomContentView() {
        setContentView(getLayoutResId())
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    open fun initHeander() {}
    open fun initEvent() {}

    override fun onDestroy() {
        super.onDestroy()
    }

    open fun setStatusBar() {
        //无标题栏的沉浸式
        StatusBarUtil.setTransparentForImageView(this, null)
        StatusBarUtil.setLightMode(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}