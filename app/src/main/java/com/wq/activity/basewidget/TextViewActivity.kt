package com.wq.activity.basewidget

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_textview.*
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import kotlinx.android.synthetic.main.content_main.*
import android.R
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import android.text.style.URLSpan
import android.text.style.ScaleXSpan
import android.text.style.StrikethroughSpan
import android.R.string








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
        var text = "同一个控件，粗体，斜体，大小，%"
        //
        var string = SpannableString("修改背景色、粗体、字体大小");
        // 背景色
        string.setSpan(BackgroundColorSpan(Color.RED), 2, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        // 粗体
        string.setSpan(StyleSpan(Typeface.BOLD), 6, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        // 字体大小
        string.setSpan(AbsoluteSizeSpan(50), 11, 13, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

       // 斜体加粗（中文斜体好像没有什么效果）
        string.setSpan(StyleSpan(Typeface.BOLD_ITALIC), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 字体颜色
        string.setSpan( ForegroundColorSpan(Color.RED), 2, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //下划线
        string.setSpan(URLSpan(""), 1, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        //字体宽度
        string.setSpan(ScaleXSpan(2.0f), 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        string.setSpan(ScaleXSpan(0.5f), 7, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        //删除线
        string.setSpan(StrikethroughSpan(), 2, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = string
    }
}
