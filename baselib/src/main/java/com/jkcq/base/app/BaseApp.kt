package com.jkcq.base.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import com.beyondworlds.ktx.toast
import com.example.utillibrary.AppUtil
import kotlin.properties.Delegates


/**
 *  Created by BeyondWorlds
 *  on 2020/7/23
 */
open class BaseApp : Application() {
    companion object {
        var sApplicaton: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        sApplicaton = this
        //注册activity生命周期回调
        registerActivityLifecycleCallbacks(ActivityLifecycleController())
        registerNetwork()
        AppUtil.init(sApplicaton)
    }

    /**
     * 注册监听网路
     */
    fun registerNetwork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            manager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    toast("网络可用")
                }

                override fun onUnavailable() {
                    toast("网络不可用")
                }

                override fun onLost(network: Network) {
                    toast("网络已断开，请检查网络")
                }
            })
        }
    }
}