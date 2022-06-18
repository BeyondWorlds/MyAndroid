package com.example.system

import android.app.Instrumentation
import android.content.Intent
import android.hardware.input.InputManager
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import com.beyondworlds.ktx.TAG
import kotlinx.android.synthetic.main.activity_system_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SystemMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_main)
        //                    adb shell input keyevent 24
        btn_send_volume_up_event.setOnClickListener {
            Handler().postDelayed({
                GlobalScope.launch(Dispatchers.IO) {
//                    val ins = Instrumentation()
//                    ins.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP)
                    Log.e(TAG, "KEYCODE_MEDIA_PLAY ");

                    MusicControl.getInstance(this@SystemMainActivity)
                        .controlVolumeUpDown(33)

                }
            }, 1000 * 10)

        }
        btn_send_media_event.setOnClickListener {
            Handler().postDelayed({
//                GlobalScope.launch(Dispatchers.IO) {
////                    val ins = Instrumentation()
////                    ins.sendKeyDownUpSync(KeyEvent.KEYCODE_VOLUME_UP)
//                    MusicControl.getInstance(this@SystemMainActivity)
//                        .doControl(KeyEvent.KEYCODE_MEDIA_PLAY)
//
//                }
                dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA))

            }, 1000 * 6)


        }
        btn_adb.setOnClickListener {
            MusicControl.getInstance(this).execShell("adb shell input keyevent 24")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.e(TAG, "onKeyDown : " + keyCode);

        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {

            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {

            }
            KeyEvent.KEYCODE_CAMERA -> {

            }
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.e(TAG, "onKeyUp : " + keyCode);
        return super.onKeyUp(keyCode, event)
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

}