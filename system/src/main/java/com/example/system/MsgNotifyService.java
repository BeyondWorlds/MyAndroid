package com.example.system;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.example.utillibrary.LogUtil;


@SuppressLint("OverrideAbstract")
public class MsgNotifyService extends NotificationListenerService {

    private String TAG = "MsgNotifyService";

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        String packageName = sbn.getPackageName();
        Bundle extras = sbn.getNotification().extras;

        /**
         *  防止service保活， application销毁后的空指针异常
         */
        if(Utils.getApp() == null || packageName == null){
            return;
        }

        /**
         * onePlus7 手机会监听到自己刷新通知栏的消息
         * 屏蔽haylou fun自己刷新通知栏的消息通知
         */
        if (!TextUtils.isEmpty(packageName) && packageName.equals(getPackageName())) {
            return;
        }

        /**
         * 有些手机系统会自动发出类似 “短信正在运行” 的通知，需要把这类通知过滤掉
         * 目前只适配的小米系统，其它机型可能判断条件不一样，后续持续更新
         *
         * 通过日志发现系统自动发出的通知里只包含下以下4个key
         * EXTRA_TITLE
         * EXTRA_TEXT
         * EXTRA_BUILDER_APPLICATION_INFO
         * EXTRA_REDUCED_IMAGES
         * */
        if (android.os.Build.BRAND.toLowerCase().contains("xiaomi") || android.os.Build.BRAND.toLowerCase().contains("redmi")) {
            if ("com.android.mms".equals(packageName) && !extras.keySet().contains(Notification.EXTRA_BIG_TEXT)) {
                return;
            }
        }

/*
        //调试代码，勿删
        for (String key: extras.keySet()){
            Object o = extras.get(key);
            String value = o==null?"null":o.toString()+"   -------------------------------------------";
            LogUtil.d(TAG,"key = "+key+",value="+value);
        }
*/

        String content = "";    //通知内容
        String title = extras.getString(Notification.EXTRA_TITLE, ""); //通知标题
        try {
            CharSequence charSequence = extras.getCharSequence(Notification.EXTRA_TEXT);
            if (charSequence != null) {
                content = charSequence.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            content = extras.getString(Notification.EXTRA_TEXT, "");
        }

        LogUtil.d(TAG, "packageName = " + packageName + "，title = " + title + "，content = " + content);

//        if ("com.android.mms".equals(packageName) && TextUtils.isEmpty(content)){//短信的通知信息中content是空的，自己拼接一下
//            content = Utils.getString(R.string.watch_msg_mms);
//        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
