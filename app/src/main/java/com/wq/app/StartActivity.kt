package com.wq.app

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.wq.allandroid.R
import com.wq.animation.StartAnimationActivity
import com.wq.manager.ui.ManagerFunctionActivity
import com.wq.view.dialog.DialogActivity
import com.wq.view.viewpager.vptransformer.ui.VPTransformerActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {
    var mKnowledgeList: Array<String>? = null

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initData()
        initListView()
        Log.e("test","onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e("test","onstart")
    }
    override fun onPause() {
        super.onPause()
        Log.e("test","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.e("test","onresume")
    }
    override fun onStop() {
        super.onStop()
        Log.e("test","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("test","onDestroy")
    }
    fun initData() {
        mKnowledgeList = resources.getStringArray(R.array.knowledge_list)
    }

    fun initListView() {
        lv_start_list.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mKnowledgeList)
        lv_start_list.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                1 -> {
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
                2 -> startActivity(Intent(this@StartActivity, VPTransformerActivity::class.java))
                3 -> startActivity(Intent(this@StartActivity, StartAnimationActivity::class.java))
                4 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                5 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                6 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                7 -> startActivity(Intent(this@StartActivity, DialogActivity::class.java))
                8 -> startActivity(Intent(this@StartActivity, ManagerFunctionActivity::class.java))
            }
        }
    }
}
