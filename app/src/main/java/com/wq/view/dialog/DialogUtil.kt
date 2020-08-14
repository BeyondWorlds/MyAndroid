package com.wq.view.dialog

import android.content.Context
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.wq.allandroid.R

/**
 *  Created by BeyondWorlds
 *  on 2020/7/28
 */
object DialogUtil {

    private fun getAlertBuilder(context: Context): AlertDialog.Builder = AlertDialog.Builder(context)

    /**
     * 默认dialog,快速简单
     */
    fun showDefaultDialog(context: Context, message: String, confirm: () -> Unit, cancel: () -> Unit = {}, title: String = "") {
        getAlertBuilder(context).apply {
            if (!TextUtils.isEmpty(title)) {
                setTitle(title)
            }
            setMessage(message).setPositiveButton("确认"
            ) { dialog, which -> confirm() }.setNegativeButton("取消") { dialog, which ->
                dialog.dismiss()
                cancel()
            }.create().show()
        }
    }

    /**
     * 标准dialog
     */
    fun showStandardDialog(context: Context, title: String = "", message: String, positiveStr: String, negativeStr: String,
                           confirm: () -> Unit, cancel: () -> Unit = {}, iconRes: Int = 0) {
        getAlertBuilder(context).apply {
            if (!TextUtils.isEmpty(title)) {
                setTitle(title)
            }
            if (iconRes != 0) {
                setIcon(iconRes)
            }
            setMessage(message)
                    .setPositiveButton(positiveStr) { dialog, which -> confirm() }
                    .setNegativeButton(negativeStr) { dialog, which ->
                        dialog.dismiss()
                        cancel()
                    }.create().show()
        }
    }


    fun showConfirmDialog(context: Context, title: String, message: String, confirm: () -> Unit) {
        getAlertBuilder(context).setIcon(R.mipmap.ic_launcher).setTitle(title)
                .setMessage(message).setPositiveButton("确认"
                ) { dialog, which -> confirm() }.create().show()
    }


    /**
     * 显示有三个button的dialog
     */
    fun showThirdBtnDialog(context: Context, iconRes: Int = R.mipmap.ic_launcher, title: String, message: String,
                           positiveStr: String = "确认", negativeStr: String = "取消", neutralStr: String = "再想想",
                           confirm: () -> Unit, cancel: () -> Unit, neutral: () -> Unit) {
        getAlertBuilder(context).setIcon(iconRes).setTitle(title)
                .setMessage(message).setPositiveButton(positiveStr) { dialog, which -> confirm() }
                .setNegativeButton(negativeStr) { dialog, which ->
                    cancel()
                }.setNeutralButton(neutralStr) { dialog, which -> neutral() }.create().show()
    }
}