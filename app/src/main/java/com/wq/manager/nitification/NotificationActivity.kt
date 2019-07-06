package com.wq.manager.nitification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.widget.RemoteViews
import com.wq.allandroid.R
import com.wq.app.MainActivity
import com.wq.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initView()
    }

    fun initView() {
        btn_base_notification.setOnClickListener {
            setBaseNotification()

        }
        btn_costom_notification.setOnClickListener { setCustomNotify() }
    }

    fun setBaseNotification() {
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var mainIntent = Intent(this@NotificationActivity, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        var builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.test1)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_test1))
                .setAutoCancel(true)
                .setContentTitle("基本通知")
                .setContentText("这是一个基本的通知")
//                    .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
        notificationManager.notify(3, builder.build())
    }

    fun setCustomNotify() {
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var mainIntent = Intent(this@NotificationActivity, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 1, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT)
        var remoteViews = RemoteViews(packageName, R.layout.notify_custom)
        var builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.test1)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_test1))
                .setAutoCancel(true)
                .setContent(remoteViews)
//                    .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
        notificationManager.notify(2, builder.build())
    }
}
