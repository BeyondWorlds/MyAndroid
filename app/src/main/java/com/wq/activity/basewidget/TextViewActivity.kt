package com.wq.activity.basewidget

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_textview.*

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
        //SPAN_EXCLUSIVE_EXCLUSIVE,SPAN_INCLUSIVE_INCLUSIVE
        // 背景色
        string.setSpan(BackgroundColorSpan(Color.RED), 2, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        // 粗体
        string.setSpan(StyleSpan(Typeface.BOLD), 6, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        // 字体大小
        val relativeSizeSpan = RelativeSizeSpan(0.8f)//相对字体大小
        val absoluteSizeSpan = AbsoluteSizeSpan(30)
        string.setSpan(absoluteSizeSpan, 11, 13, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        // 斜体加粗（中文斜体好像没有什么效果）
        string.setSpan(StyleSpan(Typeface.BOLD_ITALIC), 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //设置字体样式
        string.setSpan(TypefaceSpan("family"), 0, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE)


        // 字体颜色
        string.setSpan(ForegroundColorSpan(Color.RED), 2, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //下划线
        string.setSpan(URLSpan(""), 1, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //字体宽度
        string.setSpan(ScaleXSpan(2.0f), 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        string.setSpan(ScaleXSpan(0.5f), 7, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        //删除线
        string.setSpan(StrikethroughSpan(), 2, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        tv_effect.text = string

        //上标
        val spannableString = SpannableString("如果我是陈奕迅")

        val superscriptSpan = SuperscriptSpan()
        spannableString.setSpan(relativeSizeSpan, 4, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(superscriptSpan, 4, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_effect.setText(spannableString)

        //下标
        val subscriptSpan = SubscriptSpan()
        spannableString.setSpan(relativeSizeSpan, 4, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(subscriptSpan, 4, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv_effect.setText(spannableString)

        //部分点击事件
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                //处理
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                //粗体，比bold瘦
                ds.isFakeBoldText = true
            }
        }, 4, 7, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        tv_effect.setText(spannableString)
    }
}
