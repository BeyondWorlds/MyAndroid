package com.example.utillibrary.system

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings

object JumpUtil {
    /**
     * 跳到系统设置界面
     */
    fun JumpToSettingActivity(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    /**
     * 跳转到浏览器
     */
    fun jumpToBroser(context: Context) {
        val content_url = Uri.parse("https://www.baidu.com")
        val intent = Intent(Intent.ACTION_VIEW, content_url)
        context.startActivity(intent)
    }

    /**
     * 跳转到Media
     */
    fun jumpToVideo(context: Activity, requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        context.startActivityForResult(intent, requestCode)
    }


}