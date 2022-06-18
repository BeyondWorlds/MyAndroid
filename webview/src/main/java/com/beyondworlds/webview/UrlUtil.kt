package com.beyondworlds.webview

import android.text.TextUtils
import com.blankj.utilcode.util.AppUtils


/**
 * author: wq
 * date:  2021/12/28
 */

object UrlUtil {


    /**
     * web统一添加如下参数
     */
    fun getWebUrl(url: String): String {
        val realUrl = parseUrl(getValidUrl(url))
        return realUrl
    }

    /**
     * 统一变成https
     * @param url
     * @return
     */
    fun getValidUrl(originUrl: String): String {
        var url = originUrl
        url = if (url.startsWith("https")) {
            return url
        } else if (url.startsWith("http")) {
            url.replaceFirst("http://".toRegex(), "https://")
        } else {
            "https://$url"
        }
        return url
    }

    /**
     * 解析重组Url
     */
    private fun parseUrl(url: String): String {
        val paramsMap = HashMap<String, String>()
        val originUrl = url.split("?")
        if (originUrl.size > 1) {
            //解析原本的参数
            val params = originUrl[1].split("&")
            for (param in params) {
                val item = param.split("=")
                if (item.size > 1) {
                    paramsMap.put(item[0], item[1])
                }
            }

        }

        paramsMap["appId"] = AppUtils.getAppPackageName()
//        paramsMap.put("accessToken", "")

        val sb = StringBuffer()
        sb.append(originUrl[0])
        sb.append("?")
        for (urlParams in paramsMap.keys) {
            sb.append(urlParams + "=" + paramsMap[urlParams])
            sb.append("&")
        }

        val realUrl = sb.toString()
        return realUrl.substring(0, realUrl.length - 1)
    }
}