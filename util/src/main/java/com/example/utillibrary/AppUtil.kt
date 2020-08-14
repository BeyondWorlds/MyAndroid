package com.example.utillibrary

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 *  Created by BeyondWorlds
 *  on 2020/7/29
 */
object AppUtil {
    lateinit var mApp: Application

    /**
     * application中初始化，util中需要用到context的地方都可以从这里拿
     */
    fun init(app: Application) {
        mApp = app
    }

    fun getApp(): Application {
        if (mApp == null) {
            throw NullPointerException("u should init first")
        }
        return mApp
    }

    fun isUSA(): Boolean {
        return !isCN()
    }

    fun isES(): Boolean {
        return getLocale().language == "es"
    }

    fun isCN(): Boolean {
        return getLocale().language == "zh"
    }

    private fun getLocale(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.getDefault()[0]
        } else {
            Locale.getDefault()
        }
    }
}