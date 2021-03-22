package com.example.version_adapter.android_q

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.utillibrary.picture.BitmapUtil
import com.example.utillibrary.picture.PictureUtil
import com.example.version_adapter.R
import kotlinx.android.synthetic.main.activity_android_q.*

class AndroidQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_q)
        btn_save.setOnClickListener {
            var bitmap = BitmapUtil.activityShot(this@AndroidQActivity)
//            iv_show.setImageBitmap(bitmap)
          PictureUtil.putImage2Photo(this@AndroidQActivity, bitmap)
//            PictureUtil.saveBitmap(this@AndroidQActivity, bitmap)

        }
    }
}