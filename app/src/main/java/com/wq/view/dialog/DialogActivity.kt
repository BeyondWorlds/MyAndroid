package com.wq.view.dialog

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.Utils
import com.wq.allandroid.R
import com.wq.base.BaseActivity

class DialogActivity : BaseActivity() {
    private var tv_size: TextView? = null

    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        findViewById<View>(R.id.btn_show_dialog).setOnClickListener { MyDialog(this@DialogActivity).showLocateDialog() }
        val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memClass = activityManager.memoryClass
        val large = activityManager.largeMemoryClass
        tv_size = findViewById<View>(R.id.tv_size) as TextView
        tv_size!!.text = "currentMemory=$memClass  largeMemory=$large"

        DialogUtil.showConfirmDialog(this, "title", "hello world") { null }
        DialogUtil.showDefaultDialog(this, "",  {})

        Utils.getApp()
    }
}