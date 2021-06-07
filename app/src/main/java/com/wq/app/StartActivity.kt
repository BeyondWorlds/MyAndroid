package com.wq.app

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.beyondworlds.appupdate.ApkDownLoadManager
import com.example.utillibrary.LogUtil
import com.example.utillibrary.activity.PermissonActivity
import com.example.version_adapter.VersionAdapterActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.wq.activity.BarActivity
import com.wq.allandroid.R
import com.wq.animation.StartAnimationActivity
import com.wq.activity.ResUsageActivity
import com.wq.manager.ui.ManagerFunctionActivity
import com.wq.view.dialog.DialogActivity
import com.wq.view.viewpager.vptransformer.ui.VPTransformerActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    var mStartPosition=1

    var mKnowledgeList: Array<String>? = null
//    var mApkUrl ="https://isportcloud.oss-cn-shenzhen.aliyuncs.com/manager/shikongzhiyiH5_10664.apk"
    var mApkUrl = "https://isportcloud.oss-cn-shenzhen.aliyuncs.com/manager/JKCQ_V[1.1.6]_2020-03-05-1510_release.apk"
    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initPermission()
        initData()
        initListView()
        LogUtil.e("test", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("test", "onstart")
    }

    override fun onPause() {
        super.onPause()
        Log.e("test", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.e("test", "onresume")
    }

    override fun onStop() {
        super.onStop()
        Log.e("test", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("test", "onDestroy")
    }

    fun initData() {
        mKnowledgeList = resources.getStringArray(R.array.knowledge_list)
    }

    fun initListView() {
        lv_start_list.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mKnowledgeList!!)
        lv_start_list.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                mStartPosition-1 ->startActivity(Intent(this@StartActivity, VersionAdapterActivity::class.java))
                mStartPosition -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                mStartPosition+1 -> {
                    //跳转到其他应用的activity，如果这个activity不是入口activity，则需要添加export：true即允许其他应用调用
                    val intent = Intent()
                    intent.component = ComponentName(
                            "com.tlf.medical.ytk",
                            "com.tlf.medical.ui.activity.MainActivity")
                    //除了setComponent还可以setClassName
//                    intent.setClassName("包名,
//                            "类名全路径")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    this.startActivity(intent)
                }
                mStartPosition+ 2 -> startActivity(Intent(this@StartActivity, VPTransformerActivity::class.java))
                mStartPosition+3 -> startActivity(Intent(this@StartActivity, StartAnimationActivity::class.java))
                mStartPosition+4 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                mStartPosition+ 5 -> startActivity(Intent(this@StartActivity, ManagerFunctionActivity::class.java))
                mStartPosition+6 -> startActivity(Intent(this@StartActivity, ResUsageActivity::class.java))
                mStartPosition+ 7 -> startActivity(Intent(this@StartActivity, PermissonActivity::class.java))
                mStartPosition+ 8 -> ApkDownLoadManager(this).startDownLoad(mApkUrl)
                mStartPosition+ 9 -> startActivity(Intent(this@StartActivity, BarActivity::class.java))
//                10 -> startActivity(Intent(this@StartActivity, CoordinatorMainActivity::class.java))
            }
        }
    }

    fun initPermission() {

        RxPermissions(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.REQUEST_INSTALL_PACKAGES)
                .subscribe { aBoolean ->
                    if (aBoolean!!) {

                    } else {

                    }
                }
    }
}
