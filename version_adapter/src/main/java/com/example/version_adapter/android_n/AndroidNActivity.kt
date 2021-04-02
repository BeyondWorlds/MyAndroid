package com.example.version_adapter.android_n

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.blankj.utilcode.util.ImageUtils
import com.example.utillibrary.LogUtil
import com.example.utillibrary.picture.BitmapUtil
import com.example.version_adapter.R
import kotlinx.android.synthetic.main.activity_android_n.*

class AndroidNActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_n)
        btn_get_all_filepath.setOnClickListener {
            FileUtil.showAllFunctionFilepath(this)
        }

        btn_save_pic2file.setOnClickListener {
            //应用私有文件
            var fileName = getExternalFilesDir(null)?.path + "/test_" + System.currentTimeMillis() + ".jpg"
            LogUtil.e(fileName)
            var bitmap = BitmapUtil.activityShot(this@AndroidNActivity)
            //保存图片到本地
            ImageUtils.save(bitmap, fileName, Bitmap.CompressFormat.JPEG, true)
        }
        btn_save_pic2cachefile.setOnClickListener {
            var cacheFileName = externalCacheDir?.path + "/test_" + System.currentTimeMillis() + ".jpg"
            LogUtil.e(cacheFileName)
            var bitmap = BitmapUtil.activityShot(this@AndroidNActivity)
            ImageUtils.save(bitmap, cacheFileName, Bitmap.CompressFormat.JPEG, true)
        }
        btn_save_pic2sdcard.setOnClickListener {
            var sdcardFileName = Environment.getExternalStorageDirectory()?.path + "/test_" + System.currentTimeMillis() + ".jpg"
            LogUtil.e(sdcardFileName)
            var bitmap = BitmapUtil.activityShot(this@AndroidNActivity)
            ImageUtils.save(bitmap, sdcardFileName, Bitmap.CompressFormat.JPEG, true)
        }
    }
}