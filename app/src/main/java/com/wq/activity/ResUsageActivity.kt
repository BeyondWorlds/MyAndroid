package com.wq.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.wq.allandroid.R
import com.wq.base.BaseActivity

class ResUsageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_usage)
        initView()
    }

    fun initView() {
        //获取字符串数组
        var stringList = resources.getStringArray(R.array.knowledge_list)
        //获取资源中的string
        var text = resources.getString(R.string.think)
        //获取资源中的drawable
        var drawable = resources.getDrawable(R.drawable.selector_bg_color)
        //获取资源中的bitmap
        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.selector_bg_color)
        //获取asset中的资源
    }
}
