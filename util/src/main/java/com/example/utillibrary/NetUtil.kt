package com.jkcq.util

import android.content.Context
import android.net.ConnectivityManager

/**
 *  Created by BeyondWorlds
 *  on 2020/7/28
 */
object NetUtil {

    /**
     * check NetworkConnected
     *
     * @param context
     * @return
     */
    fun isNetworkConnected(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return !(null == info || !info.isConnected)
    }
}