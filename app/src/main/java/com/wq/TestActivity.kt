package com.wq

import android.icu.text.TimeZoneNames
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wq.allandroid.R
import java.util.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val decorView = window.decorView
        TimeZone.getAvailableIDs().forEach {
            TimeZone.getTimeZone(it)
        }
        TimeZone.getTimeZone("8").displayName
//        TimeZoneNames.getInstance(Locale.CHINA).getAvailableMetaZoneIDs()
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}