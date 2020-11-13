package com.example.utillibrary.system

import android.os.Build

object SystemUtil {

    /**
     * 获取系统的cpu架构
     */
    fun getCPUAbi(): String {
        return Build.CPU_ABI
    }

    /**
     * 获取系统支持的cpu架构列表,第一个元素就是设备的cpu架构
     */
    fun  getSupportAbi():Array<String>{
        return Build.SUPPORTED_ABIS
    }
}