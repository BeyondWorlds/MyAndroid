package com.wq.view.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.wq.allandroid.R
import java.util.*

//import android.app.AlertDialog;

/**
 * Created by ${wq} on 2017/5/26.
 */

class DialogFactory {


    private fun someDialogFunction(context: Context) {
        val dialog = Dialog(context)
        //点击返回不会取消对话框
        dialog.setCancelable(false)
        //点击外部不会取消对话框
        dialog.setCanceledOnTouchOutside(false)
    }

    companion object {

        val ONLY = 0
        val P_NEGATIVE = 1
        val PN_NEUTRAL = 2

        /**
         * common dialog
         *
         * @param context
         * @param icon
         * @param title
         * @param message
         * @param positive
         * @param negative
         * @param neutral
         */
        fun showDialog(context: Context, icon: Drawable, title: String, message: String,
                       positive: DialogInterface.OnClickListener,
                       negative: DialogInterface.OnClickListener,
                       neutral: DialogInterface.OnClickListener, type: Int) {
            val builder = AlertDialog.Builder(context).setIcon(icon).setTitle(title).setMessage(message)
            when (type) {
                ONLY -> builder.setPositiveButton("ok", positive)
                P_NEGATIVE -> builder.setPositiveButton("ok", positive)
                        .setNegativeButton("no", negative)
                PN_NEUTRAL -> builder.setPositiveButton("ok", positive)
                        .setNegativeButton("no", negative)
                        .setNeutralButton("center", neutral)
                else -> builder.setPositiveButton("ok", positive)
                        .setNegativeButton("no", negative)
            }
            builder.create().show()
            //有些系统，按钮的颜色和背景色重合
//            val btnPos = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
//            val btnNeg = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
//            btnPos.setTextColor(Color.BLACK)
//            btnNeg.setTextColor(Color.BLACK)
        }

        fun showStandardDialog(context: Context) {
            AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).setTitle("StandardDialog")
                    .setMessage("HelloWorld").setPositiveButton("确认"
            ) { dialog, which -> }.setNegativeButton("取消") { dialog, which -> }.setNeutralButton("hehe") { dialog, which -> }.create().show()
        }

        fun showSingleChoiceDialog(context: Context) {
            AlertDialog.Builder(context).setTitle("SingleChoice").setSingleChoiceItems(arrayOf("one", "two"), 0) { dialog, which -> }.setPositiveButton("ok") { dialog, which -> }.create().show()
        }

        fun showMultiChoice(context: Context) {
            AlertDialog.Builder(context).setIcon(android.R.drawable.btn_star).setTitle("MultiChoice").setMultiChoiceItems(arrayOf("what", "how"), booleanArrayOf(false, false)) { dialog, which, isChecked -> }.setNegativeButton("ok") { dialog, which -> }.create().show()
        }

        fun showSimpleListDialog(context: Context) {
            AlertDialog.Builder(context).setTitle("showSimpleDialog").setItems(arrayOf("go", "goto")) { dialog, which -> }.setNeutralButton("center") { dialog, which -> }.create().show()
        }


        fun showCustomDialog(context: Context) {
            AlertDialog.Builder(context).setTitle("CustomDialog").create().show()
        }

        fun showProgressDialog(context: Context) {
            val dialog = ProgressDialog(context)
            //旋转框
            // dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //水平进度框
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            dialog.setTitle("ProgressDialog")
            dialog.setMessage("waitting data")
            dialog.max = 100
            dialog.progress = 50
            dialog.show()
        }

        fun showDataPickerDialog(context: Context) {
            val instance = Calendar.getInstance()
            val year = instance.get(Calendar.YEAR)
            val month = instance.get(Calendar.MONTH) // 该方法month 从0 开始
            val day = instance.get(Calendar.DAY_OF_MONTH)
            // 构造dialog
            val dialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // 获取到的month 需要+1 获取正确的月份
                Toast.makeText(context, year.toString() + "-" + monthOfYear + 1 + "-" + dayOfMonth, Toast.LENGTH_SHORT).show()
            }, year, month, day)
            dialog.show()
        }

        fun showTimePickerDialog(context: Context) {
            val instance = Calendar.getInstance()
            val hour = instance.get(Calendar.HOUR_OF_DAY)
            val minute = instance.get(Calendar.MINUTE)
            // 时间对话框
            val dialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> Toast.makeText(context, hourOfDay.toString() + ":" + minute, Toast.LENGTH_SHORT).show() }, hour, minute, true)

            //显示
            dialog.show()
        }
    }
}
