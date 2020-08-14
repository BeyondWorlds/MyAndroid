package com.jkcq.util.ktx

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.utillibrary.AppUtil

/**
 *  Created by BeyondWorlds
 *  on 2020/7/29
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, content, duration).apply {
        show()
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun Context.longToast(content: String) {
    toast(content, Toast.LENGTH_LONG)
}

fun Context.longToast(@StringRes id: Int) {
    toast(id, Toast.LENGTH_LONG)
}

fun Any.toast(context: Context= AppUtil.getApp(), content: String, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(content, duration)
}

fun Any.toast(context: Context=AppUtil.getApp(), @StringRes id: Int, duration: Int=Toast.LENGTH_SHORT) {
    context.toast(id, duration)
}

fun Any.longToast(context: Context=AppUtil.getApp(), content: String) {
    context.longToast(content)
}

fun Any.longToast(context: Context=AppUtil.getApp(), @StringRes id: Int) {
    context.longToast(id)
}


