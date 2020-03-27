package com.beyondworlds.appupdate

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.dialog_update_layout.*


/**
 * @author WuJianhua
 */
class UpdateDialog(private val mContext: Context, type: Int, values: String) : Dialog(mContext, R.style.SimpleDialogStyle) {

    private var clickListener: OnDialogClickListener? = null

    init {
        setContentView(R.layout.dialog_update_layout)
    }

    override fun show() {
        super.show()
        val layoutParams = window!!.attributes
        layoutParams.gravity = Gravity.CENTER
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        layoutParams.width = (AppUpdateUtils.getScreenWidth(mContext) * 0.5).toInt()
//        layoutParams.height =(ScreenUtils.getScreenHeight()*0.5).toInt()
        window!!.decorView.setPadding(0, 0, 0, 0)
        window!!.attributes = layoutParams

    }

    fun updateProgress(progress: Float) {
        tv_progress_value.setText((progress * 100).toInt().toString() + "%")
        view_progress.setProgress(progress)
    }

    fun setTvPackgeSize(size: Double) {
        val tips = String.format(mContext.resources.getString(R.string.package_size), size.toString())
        tv_packge.setText(tips)
    }

    fun setOnDialogClickListener(clickListener: OnDialogClickListener) {
        this.clickListener = clickListener
    }

    fun onClick() {
        clickListener?.dialogClickType(1)
    }
}
