package com.wq.manager.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.wq.allandroid.R
import com.wq.manager.ManagerFunction

class ManagerFunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_function)
        Handler().postDelayed(Runnable {
            ManagerFunction.wakeUpAndUnlock(this)
        }, 10000)
    }
}
