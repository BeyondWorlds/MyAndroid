package com.wq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.system.SystemMainActivity
import com.wq.allandroid.R
import com.wq.app.StartActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btn_system.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    SystemMainActivity::class.java
                )
            )
        }
        btn_other.setOnClickListener {
            startActivity(
                Intent(
                    this@HomeActivity,
                    StartActivity::class.java
                )
            )
        }

    }
}