package com.wq.activity.basewidget

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.blankj.utilcode.util.KeyboardUtils
import com.wq.allandroid.R
import kotlinx.android.synthetic.main.activity_edittext.*

class EdittextActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext)
        //监听输入框变化
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.e("test", "s=$s start=$start count=$count after=$after")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.e("test", "s=$s start=$start count=$count before=$before")
            }

            override fun afterTextChanged(s: Editable) {
                var content=s.toString()
                if (content.length > 0) {
                    if(content.length>20){
                        //控制输入框字体个数
                        content=content.substring(0,20)
                    }
                } else {
                }
                Log.e("test", "afterTextChanged=$s")
            }
        })
        //软键盘Enter键监听
        et.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if(event!=null){
                Log.e(TAG, "actionID=" + actionId + "event=" + event.action)
            }
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //
            }
            false
        })
    }

    /**
     * 点击外部隐藏软甲盘
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                KeyboardUtils.hideSoftInput(et)
            }
        }
        return super.onTouchEvent(event)
    }
}
