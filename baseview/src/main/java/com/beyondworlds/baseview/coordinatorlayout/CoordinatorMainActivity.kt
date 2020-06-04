package com.beyondworlds.baseview.coordinatorlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beyondworlds.baseview.R
import com.beyondworlds.filelibrary.FileUtil
import kotlinx.android.synthetic.main.activity_coordinator_main.*

class CoordinatorMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_main)
        btn_scroll.setOnClickListener {  startActivity(Intent(this@CoordinatorMainActivity,AppbarLayoutActivity::class.java))}
        btn_enterAlways.setOnClickListener {  }
        btn_enterAlwaysCollapsed.setOnClickListener {  }
        btn_exitUntilCollapsed.setOnClickListener {  }
        btn_snap.setOnClickListener {  }

        btn_test.setOnClickListener {     FileUtil.testFile(this@CoordinatorMainActivity);}

    }
}
