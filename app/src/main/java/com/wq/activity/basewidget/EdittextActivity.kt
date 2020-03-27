package com.wq.activity.basewidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import com.blankj.utilcode.util.KeyboardUtils
import com.wq.allandroid.R
import kotlinx.android.synthetic.main.activity_edittext.*

class EdittextActivity : AppCompatActivity() {

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
                if (s.toString().length > 0) {
                } else {
                }
                Log.e("test", "afterTextChanged=$s")
            }
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
