package com.example.version_adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.webkit.WebView
import com.blankj.utilcode.util.ImageUtils
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object PictureUtil {

    /**
     * 此方法经测试，无法截长图，已经添加 WebView.enableSlowWholeDocumentDraw();方法
     */
    fun screenShotWebView2(webView: WebView) {
        webView.setDrawingCacheEnabled(true)
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
        putImage2Photo(webView.context, bitmap)
    }

    /**
     * 把本地图片放入相册
     */
    fun putImage2Photo(context: Context, bitmap: Bitmap?, imgFilePath: String = ""): Boolean {
        var fileName = imgFilePath
        if (TextUtils.isEmpty(fileName)) {
            fileName = context.getExternalFilesDir(null)?.path + "/webview_jietu" + System.currentTimeMillis() + ".jpg"
        }
        //保存图片到本地
        ImageUtils.save(bitmap, fileName, Bitmap.CompressFormat.JPEG, true)
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    fileName, "img", null);
        } catch (e: FileNotFoundException) {
            e.printStackTrace();
            return false
        }
        // 最后通知图库更新
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(File(fileName))));
        return true
    }

    /**
     * 保存图片到相册
     */
    fun saveBitmap2Gallery(context: Context, bitmap: Bitmap): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //返回出一个URI
            val insert = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    /*
                    这里如果不写的话 默认是保存在 /sdCard/DCIM/Pictures
                     */
                    ContentValues()//这里可以啥也不设置 保存图片默认就好了
            ) ?: return false //为空的话 直接失败返回了

            //这个打开了输出流  直接保存图片就好了
            context.contentResolver.openOutputStream(insert).use {
                it ?: return false
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
            return true
        } else {
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "title", "desc")
            return true
        }
    }

    fun saveFile2Gallery(context: Context, url: String): Boolean {
        if (true) {
            //返回出一个URI
            val insert = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    /*
                    这里可以默认不写 默认保存在
                     */
                    ContentValues()
            ) ?: return false //为空的话 直接失败返回了
            //这个打开了输出流  直接保存图片就好了
            context.contentResolver.openOutputStream(insert).use { os ->
                os ?: return false
                var x = download(url, os)
                return x
            }
            return false
        } else {
            val externalFilesDir =
                    context.getExternalFilesDir(Environment.DIRECTORY_DCIM) ?: return false
            var name = "${System.currentTimeMillis()}.jpg"
            val file = File(externalFilesDir, name)
            //下载文件到应用目录
            download(url, file.outputStream())
            MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    file.absolutePath,
                    name,
                    "desc"
            )
            //刷新相册
            context.sendBroadcast(
                    Intent(
                            Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(File(file.getPath()))
                    )
            )
            return true
        }
    }


    fun saveFile2Gallery2(context: Context, url: String): Boolean {
        val name = System.currentTimeMillis().toString()
        val photoPath = Environment.DIRECTORY_DCIM + "/Camera"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, photoPath)//保存路径
                put(MediaStore.MediaColumns.IS_PENDING, true)
            }
        }
        //返回出一个URI
        val insert = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
        ) ?: return false
        //这个打开了输出流  直接保存图片就好了
        context.contentResolver.openOutputStream(insert).use { os ->
            os ?: return false
            var x = download(url, os)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, false)
            }
            return x
        }
        return false
    }


    fun saveBitmap2Gallery2(context: Context, bitmap: Bitmap): Boolean {
        val name = "IMG_" + System.currentTimeMillis().toString()
//        val photoPath = Environment.DIRECTORY_DCIM + "/Camera"
        val photoPath = Environment.DIRECTORY_DCIM
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, photoPath)//保存路径
                put(MediaStore.MediaColumns.IS_PENDING, true)
            }
        }
        val insert = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
        ) ?: return false //为空的话 直接失败返回了
        //这个打开了输出流  直接保存图片就好了
        context.contentResolver.openOutputStream(insert).use {
            it ?: return false
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.MediaColumns.IS_PENDING, false)
        }
        //刷新相册
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, insert));
        return true
    }


    private fun download(url: String, os: OutputStream): Boolean {
        val url = URL(url)
        (url.openConnection() as HttpURLConnection).also { conn ->
            conn.requestMethod = "GET"
            conn.connectTimeout = 5 * 1000
            if (conn.responseCode == 200) {
                conn.inputStream.use { ins ->
                    val buf = ByteArray(2048)
                    var len: Int
                    while (ins.read(buf).also { len = it } != -1) {
                        os.write(buf, 0, len)
                    }
                    os.flush()
                }
                return true
            } else {
                return false
            }
        }
    }


    /*
    * 保存文件，文件名为当前日期
    */
    fun saveBitmap(context: Context, bitmap: Bitmap): Boolean {
        var fileName = ""
        var bitName = "IMG_" + System.currentTimeMillis()
        var file: File
        val brand = Build.BRAND
        fileName = if (brand == "xiaomi") { // 小米手机brand.equals("xiaomi")
            Environment.getExternalStorageDirectory().path + "/DCIM/Camera/" + bitName
        } else if (brand.equals("Huawei", ignoreCase = true)) {
            Environment.getExternalStorageDirectory().path + "/DCIM/Camera/" + bitName
        } else { // Meizu 、Oppo
            Environment.getExternalStorageDirectory().path + "/DCIM/" + bitName
        }
        //        fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        file = if (Build.VERSION.SDK_INT >= 29) {
//            boolean isTrue = saveSignImage(bitName, bitmap);
            saveSignImage(context, bitName, bitmap)
            return true
            //            file= getPrivateAlbumStorageDir(NewPeoActivity.this, bitName,brand);
//            return isTrue;
        } else {
            Log.v("saveBitmap brand", "" + brand)
            File(fileName)
        }
        if (file.exists()) {
            file.delete()
        }
        val out: FileOutputStream
        try {
            out = FileOutputStream(file)
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush()
                out.close()
                // 插入图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.absolutePath, bitName, null)
            }
        } catch (e: FileNotFoundException) {
            Log.e("FileNotFoundException", "FileNotFoundException:" + e.message.toString())
            e.printStackTrace()
            return false
        } catch (e: IOException) {
            Log.e("IOException", "IOException:" + e.message.toString())
            e.printStackTrace()
            return false
        } catch (e: Exception) {
            Log.e("IOException", "IOException:" + e.message.toString())
            e.printStackTrace()
            return false

// 发送广播，通知刷新图库的显示
        }
        //        if(Build.VERSION.SDK_INT >= 29){
//            copyPrivateToDownload(this,file.getAbsolutePath(),bitName);
//        }
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://$fileName")))
        return true
    }


    //将文件保存到公共的媒体文件夹
    //这里的filepath不是绝对路径，而是某个媒体文件夹下的子路径，和沙盒子文件夹类似
    //这里的filename单纯的指文件名，不包含路径
    /*String filePath,*/
    fun saveSignImage(context: Context, fileName: String?, bitmap: Bitmap) {
        try {
            //设置保存参数到ContentValues中
            val contentValues = ContentValues()
            //设置文件名
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            //兼容Android Q和以下版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
                //RELATIVE_PATH是相对路径不是绝对路径
                //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
                contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/")
                //contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Music/signImage");
            } else {
                contentValues.put(MediaStore.Images.Media.DATA, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path)
            }
            //设置文件类型
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG")
            //执行insert操作，向系统文件夹中添加文件
            //EXTERNAL_CONTENT_URI代表外部存储器，该值不变
            val uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            uri?.let {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                val outputStream = context.getContentResolver().openOutputStream(it)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                    outputStream.flush()
                    outputStream.close()
                }
            }
        } catch (e: java.lang.Exception) {
        }
    }


}