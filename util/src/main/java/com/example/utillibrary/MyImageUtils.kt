package com.example.utillibrary

import android.app.Activity
import android.graphics.*
import android.util.DisplayMetrics
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.ScrollView
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.ArrayList

/**
 *created by wq on 2019/4/16
 */
class MyImageUtils {

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    fun activityShot(activity: Activity): Bitmap {
        /*获取windows中最顶层的view*/
        val view = activity.window.decorView
        //允许当前窗口保存缓存信息
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        //获取状态栏高度
        val rect = Rect()
        view.getWindowVisibleDisplayFrame(rect)
        val statusBarHeight = rect.top
        val windowManager = activity.windowManager
        //获取屏幕宽和高
        val outMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        val height = outMetrics.heightPixels
        //去掉状态栏
        val bitmap = Bitmap.createBitmap(view.drawingCache, 0, statusBarHeight, width, height - statusBarHeight)
        //销毁缓存信息
        view.destroyDrawingCache()
        view.isDrawingCacheEnabled = false
        return bitmap
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView
     * @return
     */
    fun getBitmapByView(scrollView: ScrollView): Bitmap {
        var h = 0
        var bitmap: Bitmap? = null
        // 获取listView实际高度
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white)
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.width, h,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap!!)
        scrollView.draw(canvas)
        // 测试输出
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream("/sdcard/screen_test.png")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
            }
        } catch (e: IOException) {
            // TODO: handle exception
        }

        return bitmap
    }

    /**
     * 截图listview
     */
    fun getbBitmap(listView: ListView): Bitmap {
        var h = 0
        var bitmap: Bitmap? = null
        // 获取listView实际高度
        for (i in 0 until listView.childCount) {
            h += listView.getChildAt(i).height
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.width, h,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap!!)
        listView.draw(canvas)
        // 测试输出
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream("/sdcard/screen_test.png")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
            }
        } catch (e: IOException) {
            // TODO: handle exception
        }

        return bitmap
    }

    /**
     * 截取RelativeLayout
     */
    fun getRelativeLayoutBitmap(relativeLayout: RelativeLayout): Bitmap {
        var h = 0
        val bitmap: Bitmap
        for (i in 0 until relativeLayout.childCount) {
            h += relativeLayout.getChildAt(i).height
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(relativeLayout.width, h,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        relativeLayout.draw(canvas)
        return bitmap
    }

    /**
     * 截取LinearLayout
     */
    fun getLinearLayoutBitmap(linearLayout: LinearLayout): Bitmap {
        var h = 0
        val bitmap: Bitmap
        for (i in 0 until linearLayout.childCount) {
            h += linearLayout.getChildAt(i).height
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(linearLayout.width, h,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        linearLayout.draw(canvas)
        return bitmap
    }

    /**
     * 截取除了导航栏之外的整个屏幕
     */
    fun screenShotWholeScreen(activity: Activity): Bitmap {
        val dView = activity.window.decorView
        dView.isDrawingCacheEnabled = true
        dView.buildDrawingCache()
        return Bitmap.createBitmap(dView.drawingCache)
    }

    /**
     * 截图view的任意图片
     */
    fun getAnyViewShot(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        //        bitmap.setConfig(Bitmap.Config.ARGB_4444);
        //        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), left, top, right, bottom);
        view.isDrawingCacheEnabled = false
        view.destroyDrawingCache()
        return bitmap
    }

    /**
     * 截图view的任意图片
     */
    fun getAnyViewShot(view: View, left: Int, top: Int, right: Int, bottom: Int): Bitmap {

        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(view.drawingCache, left, top, right, bottom)
        view.isDrawingCacheEnabled = false
        view.destroyDrawingCache()
        return bitmap
    }

    /**
     * 水平方向拼接多张图片
     *
     * @param bitmaps
     * @return
     */
    fun mergeBitmap(bitmaps: ArrayList<Bitmap>): Bitmap {
        val paint = Paint()
        paint.color = Color.WHITE
        var width = 0
        var currentWidth = 0
        for (bitmap in bitmaps) {
            width += bitmap.width
        }
        val bitmap = Bitmap.createBitmap(width, bitmaps[0].height, bitmaps[0].config)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bitmaps[0], Matrix(), paint)
        for (i in 1 until bitmaps.size) {
            currentWidth += bitmaps[i - 1].width
            canvas.drawBitmap(bitmaps[i], currentWidth.toFloat(), 0f, paint)
        }
        return bitmap
    }


    //合成三张图片
    private fun mergeBitmap(firstBitmap: Bitmap, secondBitmap: Bitmap, threeBitmap: Bitmap): Bitmap {
        val bitmap = Bitmap.createBitmap(firstBitmap.width, firstBitmap.height + secondBitmap.height + threeBitmap.height, firstBitmap.config)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(firstBitmap, Matrix(), null)
        canvas.drawBitmap(secondBitmap, 0f, firstBitmap.height.toFloat(), null)
        canvas.drawBitmap(threeBitmap, secondBitmap.width.toFloat(), firstBitmap.height.toFloat(), null)
        return bitmap
    }

    fun mergeBitmap(firstBitmap: Bitmap, secondBitmap: Bitmap): Bitmap {
        val bitmap = Bitmap.createBitmap(firstBitmap.width, firstBitmap.height, firstBitmap.config)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(firstBitmap, Matrix(), null)
        canvas.drawBitmap(secondBitmap, 0f, 0f, null)
        return bitmap

    }

}