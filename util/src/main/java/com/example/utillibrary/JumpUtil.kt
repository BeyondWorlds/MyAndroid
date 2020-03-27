package com.example.utillibrary

import android.content.Context
import android.content.Intent
import android.provider.Settings

object JumpUtil {
    /**
     * 跳到系统设置界面
     */
    fun JumpToSettingActivity(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }
}