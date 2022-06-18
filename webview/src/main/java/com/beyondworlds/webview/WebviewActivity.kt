package com.beyondworlds.webview

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.activity.result.contract.ActivityResultContracts
import com.beyondworlds.ktx.TAG
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.GsonUtils
import com.example.utillibrary.LogUtil
import kotlinx.android.synthetic.main.activity_webview.*
import java.io.File
import java.lang.Exception
import java.util.*

class WebviewActivity : AppCompatActivity() {

    //第一次打开界面传一次参数给H5
    private var isSaveParams = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        init()
    }

    val mWebUrl: String = ""
    private fun init() {
        val mWebUrl = intent.getStringExtra("url")
        initWebSetting()
    }

    private fun initWebSetting() {
        val webSettings = webview.settings
        webSettings.run {
            javaScriptEnabled = true
//            setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//混合模式
//            setBlockNetworkImage(false);
            //js调用Android
            webSettings.javaScriptCanOpenWindowsAutomatically = true //允许弹窗
            webSettings.loadsImagesAutomatically = true
            webSettings.allowFileAccess = true
            domStorageEnabled = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                webSettings.allowFileAccessFromFileURLs = true
                webSettings.allowUniversalAccessFromFileURLs = true
            }

        }
        webview.addJavascriptInterface(JsInterface(), "android")
        webview.loadUrl(mWebUrl ?: "")
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (!isSaveParams) {
                    isSaveParams = true
                    saveParams()
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress >= 100) {

                } else {

                }

            }

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                //打开本地相册或者拍照，把uri传给H5显示图片
                mUploadCallback = filePathCallback
                openAlbum()
//                openCamera()
                return true
            }
        }

    }

    private fun setWebTitle(title: String) {

    }

    /**
     * js调用Android
     */
    inner class JsInterface() {
        @JavascriptInterface
        fun setDocTitle(title: String?) {
            if (this@WebviewActivity.isDestroyed || (this@WebviewActivity.isFinishing)) {
                return
            }
            runOnUiThread { setWebTitle(title ?: "") }
        }

        @JavascriptInterface
        fun toBgPermissionSet(message: String?) {
        }

    }

    /**
     * android 调用js,script中就是js中的代码
     */
    fun saveParams() {
        val phoneBrand = Build.BRAND
        val appVersion = AppUtils.getAppVersionName()
        val deviceType = "watch"
        val accessToken = "getShotToken()"
        val refreshToken = "getLongToken()"
        val oemId = "LIESHENG"
        val domain = ""
        var deviceCode = ""
        var versionNo = ""
        var projectNo = ""
        val script = "window.sessionStorage.setItem(\"phoneBrand\", '" + phoneBrand + "')," +
                "window.sessionStorage.setItem(\"appVersion\", '" + appVersion + "')," +
                "window.sessionStorage.setItem(\"deviceCode\", '" + deviceCode + "')," +
                "window.sessionStorage.setItem(\"versionNo\", '" + versionNo + "')," +
                "window.sessionStorage.setItem(\"deviceType\", '" + deviceType + "')," +
                "window.sessionStorage.setItem(\"projectNo\", '" + projectNo + "')," +
                "window.sessionStorage.setItem(\"oemId\", '" + oemId + "')," +
                "window.sessionStorage.setItem(\"accessToken\", '" + accessToken + "')," +
                "window.sessionStorage.setItem(\"refreshToken\", '" + refreshToken + "')," +
                "window.sessionStorage.setItem(\"domain\", '" + domain + "')"
        LogUtil.d(TAG, script)
        runOnUiThread {
            webview.evaluateJavascript(script) {
                //此处为 js 返回的结果
            }
        }
    }


    private var mUploadCallback: ValueCallback<Array<Uri>>? = null
    private var imageUri: Uri? = null
    private var imagePath: String? = null

    private fun openAlbum() {
        val photo = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        //        photo.setAction(Intent.ACTION_GET_CONTENT);
        //允许多选
        photo.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        photoLauncher.launch(photo)
    }

    private fun openCamera() {
        // 指定拍照存储位置的方式调起相机
        val filePath =
            Objects.requireNonNull(getExternalFilesDir(Environment.DIRECTORY_PICTURES))?.path
        val fileName = "IMG_" + DateFormat.format(
            "yyyyMMdd_hhmmss",
            Calendar.getInstance(Locale.CHINA)
        ) + ".jpg"
        imagePath = filePath + File.separator + fileName
        //android11 必须使用内部私有空间地址，并且必须使用Content:协议形式获取uri
        imageUri = getUriFromFile(this, File(imagePath))
        //相机
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        cameraLauncher.launch(captureIntent)

    }


    private var photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.let {
                    // 这里是针对从文件中选图片的处理
                    var results: Array<Uri>
                    val resultUri = ArrayList<Uri>()

                    val clipData = it.clipData
                    if (clipData != null) {
                        results = arrayOf()
                        for (i in 0 until clipData.itemCount) {
                            resultUri.add(clipData.getItemAt(i).uri)
                        }
                        results = resultUri.toArray(results)
                        Log.e(TAG, "size=" + results.size)
                        mUploadCallback?.onReceiveValue(results)
                    } else {
                        //多选失败，可能只有一张图片
                        var uriData = it.getData();
                        uriData?.let {
                            mUploadCallback?.onReceiveValue(arrayOf(it))
                        } ?: let {
                            mUploadCallback?.onReceiveValue(null)
                        }
                    }
                } ?: let {
                    mUploadCallback?.onReceiveValue(null)
                }
            } else {
                mUploadCallback?.onReceiveValue(null)
            }
            mUploadCallback = null
        }

    private var cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                try {
                    mUploadCallback?.onReceiveValue(arrayOf<Uri>(imageUri!!))
                    //保存到相册
//                    if (imageUri != null) {
//                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
//                        ImageUtils.save2Album(BitmapFactory.decodeStream(inputStream), Bitmap.CompressFormat.JPEG, 100, true);
//                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                mUploadCallback?.onReceiveValue(null)
            }
            mUploadCallback = null
        }
}