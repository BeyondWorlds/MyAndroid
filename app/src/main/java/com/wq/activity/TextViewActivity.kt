package com.wq.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_textview.*
import android.graphics.Paint


class TextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wq.allandroid.R.layout.activity_textview)
        tv_effect.setText(getString(com.wq.allandroid.R.string.more_text))
        //下划线
        tv_effect.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG)
       //抗锯齿
        tv_effect.getPaint().setAntiAlias(true)
       //中划线
        tv_effect.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG)
       // 设置中划线并加清晰
        tv_effect.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG)
    }
}
