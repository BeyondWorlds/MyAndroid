package com.wq.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.wq.allandroid.R
import kotlinx.android.synthetic.main.activity_bar.*
import java.util.*

class BarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar)
        //4.4
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //5.1
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////注意要清除 FLAG_TRANSLUCENT_STATUS flag
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));

//        val option = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.decorView.setSystemUiVisibility(option)
        //6.0

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //透明状态栏，黑色字体，且内容占用系统状态栏空间
//            window.statusBarColor = Color.TRANSPARENT
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//        }

        btn_no_actionbar.setOnClickListener {
            //全屏
            val option1 = View.SYSTEM_UI_FLAG_FULLSCREEN
            val option = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE//全屏但显示StatusBar，且内容占用系统状态栏空间
            window.decorView.setSystemUiVisibility(option)
            //隐藏ActionBar
            val actionBar = getSupportActionBar()
            actionBar?.hide();
        }
        btn_hide_navigation.setOnClickListener {  //内容占用系统状态栏空间
            val option1 = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            val option = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.decorView.setSystemUiVisibility(option)
        }
        //状态栏透明
//            window.statusBarColor = Color.TRANSPARENT
        //导航栏透明
//            window.navigationBarColor = Color.TRANSPARENT}
        //半透明
        btn_translucent_statusbar.setOnClickListener {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        btn_alpha.setOnClickListener {
//            window.statusBarColor = Color.BLUE
            window.statusBarColor = Color.TRANSPARENT
        }
        //全屏，状态栏悬浮在内容上
        btn_full_screen.setOnClickListener {

           window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //此种全屏状态栏不被内容覆盖，为白屏
//            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        }
        btn_statusbar_black_text.setOnClickListener {
            //黑色状态栏图标和字体
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        btn_change_bg.setOnClickListener {
            //移除半透明Flag,否则设置不起效果
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val random = Random()
            val mColor = -0x1000000 or random.nextInt(0xffffff)
            window.statusBarColor = mColor
        }

    }
}
