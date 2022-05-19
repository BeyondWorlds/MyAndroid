package com.example.version_adapter.android_q

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.beyondworlds.ktx.TAG
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.example.utillibrary.LogUtil
import com.example.utillibrary.PermissionUtil
import com.example.utillibrary.picture.BitmapUtil
import com.example.utillibrary.picture.PictureUtil
import com.example.version_adapter.R
import com.example.version_adapter.android_n.FileUtil
import kotlinx.android.synthetic.main.activity_android_q.*
import retrofit2.http.Url
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class AndroidQActivity : AppCompatActivity() {
    val REQUEST_CODE_PHOTO = 0
    val REQUEST_CODE_CAMERA = 1
    var mImgUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_q)
        btn_save.setOnClickListener {
            var bitmap = BitmapUtil.activityShot(this@AndroidQActivity)
//            iv_show.setImageBitmap(bitmap)
            //            PictureUtil.saveBitmap(this@AndroidQActivity, bitmap)
            PictureUtil.putImage2Photo(this@AndroidQActivity, bitmap)

            //utilcode类中的方法
//            ImageUtils.save2Album(bitmap,Bitmap.CompressFormat.JPEG)

        }
        btn_get_camera.setOnClickListener {
            getPictureByCamera()
        }

        PermissionUtil.REQUEST_CAMERA

        checkPermission()
        btn_get_photo.setOnClickListener { getPictureByPhoto() }
        btn_get_picture.setOnClickListener { getPictureByAllFile() }

        btn_get_photo_or_camera.setOnClickListener {

        }
    }


    fun checkPermission() {
        PermissionUtil.checkPermission(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ),
            permissonCallback = object : PermissionUtil.OnPermissonCallback {
                override fun isGrant(grant: Boolean) {
                    if (grant) {
                        Toast.makeText(
                            this@AndroidQActivity,
                            "permission success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this@AndroidQActivity, "failed", Toast.LENGTH_SHORT).show()

                    }
                }
            })
    }

    /**
     * 从相册选取图片
     */
    fun getPictureByPhoto() {
        val PhotoIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(PhotoIntent, REQUEST_CODE_PHOTO)
    }

    /**
     * 查询所有的图片文件
     */
    fun getPictureByAllFile() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = "android.intent.action.GET_CONTENT"
        intent.addCategory("android.intent.category.OPENABLE")
        startActivityForResult(intent, REQUEST_CODE_PHOTO)
    }

    /**
     * 获取拍照图片
     */
    fun getPictureByCamera() {

//        // 指定拍照存储位置的方式调起相机,系统图片文件夹
//        val filePath = Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "camera_" + System.currentTimeMillis() + ".jpg"
//        //自定义图片文件夹
//        val picFile = File(Environment.getExternalStorageDirectory()?.path + "/pics")
//        FileUtils.createOrExistsDir(picFile)
//        val realFile = File(picFile.absolutePath + "/camera_" + System.currentTimeMillis() + ".jpg")

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //Android11拍照的存储地址必须是应用私有存储空间，否则存不进去
//        val saveFilepath = externalCacheDir?.path + "/camera_" + System.currentTimeMillis() + ".jpg"
        val saveFilepath =
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.path + "/camera_" + System.currentTimeMillis() + ".jpg"
        //相机拍照返回后，拿到的uri为null,所以这里生成uri,必须使用7.0以后的方式获取uri
        mImgUri = FileUtil.getUriFromFile(this, File(saveFilepath))
        //android11以后强制分区存储，外部资源无法访问，所以添加一个输出保存位置，然后取值操作
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImgUri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_CAMERA)

    }

    /**
     * 从相册或拍照选取图片
     */
    fun getPictureBySelect() {

    }

    /**
     * 从返回的uri中取出图片并显示
     */
    fun parseUri(imgUri: Uri?) {
        LogUtil.e(" imgUri=" + imgUri)
        var inputStream: InputStream? = null
        try {
            imgUri?.let {
                inputStream = contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                iv_show.setImageBitmap(bitmap)
                //保存到相册
                ImageUtils.save2Album(bitmap, Bitmap.CompressFormat.JPEG, 100, true)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        LogUtil.e(" onActivityResult=")
        super.onActivityResult(requestCode, resultCode, data)
        LogUtil.e("resultCode=" + resultCode + "  requestCode=" + requestCode + " uri=" + data?.data?.toString())
        if (resultCode == Activity.RESULT_OK) {
            //Content类型的Uri
            val pictureUri = data?.data

            //相册
            when (requestCode) {
                REQUEST_CODE_PHOTO -> {
                    parseUri(pictureUri)
                }
                REQUEST_CODE_CAMERA -> {
                    parseUri(mImgUri)
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
      when(keyCode){
          KeyEvent.KEYCODE_CAMERA->{
              Log.e(TAG," KEYCODE_CAMERA")
          }
      }
        return super.onKeyDown(keyCode, event)
    }
}