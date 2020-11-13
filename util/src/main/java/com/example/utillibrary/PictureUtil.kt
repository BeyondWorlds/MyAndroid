package com.example.utillibrary

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.webkit.WebView
import com.blankj.utilcode.util.ImageUtils
import java.io.File
import java.io.FileNotFoundException

object PictureUtil {

    /**
     * 此方法经测试，无法截长图，已经添加 WebView.enableSlowWholeDocumentDraw();方法
     */
    fun screenShotWebView2(webView: WebView) {
        webView.setDrawingCacheEnabled(true)
        Log.e("test", "screenShot")
        val bitmap: Bitmap = webView.getDrawingCache()
        val fileName = webView.context.getExternalFilesDir(null)?.path + "/webview_jietu" + System.currentTimeMillis() + ".jpg"

        ImageUtils.save(bitmap, fileName, Bitmap.CompressFormat.JPEG, true)
//        try {
//            val fileName = Environment.getExternalStorageDirectory().path + "/webview_jietu.jpg"
//            val fos = FileOutputStream(fileName)
//            //压缩bitmap到输出流中
//            bitmap?.compress(Bitmap.CompressFormat.JPEG, 70, fos)
//            bitmap?.recycle()
//            fos.close()
//            Log.e("test", "截图成功")
//
//        } catch (e: Exception) {
//            Log.e("test", e.message!!)
//        } finally {
//            bitmap?.recycle()
//        }
    }

    /**
     *webView截图
     */
    fun screenShotWebView(webView: WebView) {
        var scale = webView.getScale();
        var width = webView.getWidth();
        var height = (webView.getContentHeight() * scale + 0.5).toInt()
        var bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        var canvas = Canvas(bitmap);
        webView.draw(canvas)
        val fileName = webView.context.getExternalFilesDir(null)?.path + "/webview_jietu" + System.currentTimeMillis() + ".jpg"
        ImageUtils.save(bitmap, fileName, Bitmap.CompressFormat.JPEG, true)
        putImage2Photo(webView.context, fileName)
    }

    /**
     * 把本地图片放入相册
     */
    fun putImage2Photo(context: Context, imgFilePath: String) {
        Log.e("test", "putImage2Photo")
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    imgFilePath, "img", null);
        } catch (e: FileNotFoundException) {
            Log.e("test", "exception=" + e.message)
            e.printStackTrace();
        }
        // 最后通知图库更新
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(File(imgFilePath))));
    }
}