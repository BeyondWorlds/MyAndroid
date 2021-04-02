package com.example.version_adapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.version_adapter.android_n.AndroidNActivity
import com.example.version_adapter.android_q.AndroidQActivity
import kotlinx.android.synthetic.main.activity_version_adapter.*

class VersionAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version_adapter)
        btn_6.setOnClickListener { }
        btn_7.setOnClickListener {
            startActivity(Intent(this@VersionAdapterActivity, AndroidNActivity::class.java))
        }
        btn_8.setOnClickListener { }
        btn_9.setOnClickListener {}
        btn_10.setOnClickListener {
            startActivity(Intent(this@VersionAdapterActivity, AndroidQActivity::class.java))
        }
    }
}