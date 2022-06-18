package com.beyondworlds.webview

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File


/**
 * Android7.0获取文件Uri
 *
 * @param context
 * @param file
 * @return
 */
fun getUriFromFile(context: Context?, file: File?): Uri? {
    var fileUri: Uri? = null
    //authority对应清单文件的配置
    fileUri = if (Build.VERSION.SDK_INT >= 24) {
        FileProvider.getUriForFile(
                context!!,
                context.packageName + ".provider",
                file!!
        )
    } else {
        Uri.fromFile(file)
    }
    return fileUri
}