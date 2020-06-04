package com.beyondworlds.appupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import android.util.Log;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * created by wq on 2019/6/25
 */
public class ApkDownLoadManager {
    private static final String TAG=ApkDownLoadManager.class.getSimpleName();
    DownloadTask task;
    private long fileTotalLength = 0;
    private long increaseLength = 0;
    private String mFileName = "MyAndroidTest.apk";

    UpdateDialog updateDialog;
    private Activity mActivity;

    public ApkDownLoadManager(Activity activity) {
        this.mActivity = activity;
    }

    public void startDownLoad(String apkDownloadUrl) {
        updateDialog = new UpdateDialog(mActivity, 1, mActivity.getResources().getString(R.string.downloading));
        updateDialog.setOnDialogClickListener(updateDialogListener);
        updateDialog.show();
        downloadAPK(apkDownloadUrl, mFileName);
    }

    private OnDialogClickListener updateDialogListener = new OnDialogClickListener() {
        @Override
        public void dialogClickType(int type) {
            updateDialog.dismiss();
            task.cancel();
        }
    };

    /**
     * 下载apk
     * @param url
     * @param fileName
     */
    public void downloadAPK(String url, final String fileName) {

        task = new DownloadTask.Builder(url, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
                .setFilename(fileName)
                // the minimal interval millisecond for callback progress
                .setMinIntervalMillisCallbackProcess(30)
                // do re-download even if the task has already been completed in the past.
                .setPassIfAlreadyCompleted(false)
                .build();
        task.enqueue(new DownloadListener() {
            @Override
            public void taskStart( DownloadTask task) {
                Log.e(TAG, "-------taskStart----");
            }

            @Override
            public void connectTrialStart( DownloadTask task,  Map<String, List<String>> requestHeaderFields) {
                Log.e(TAG, "-------connectTrialStart----");
            }

            @Override
            public void connectTrialEnd( DownloadTask task, int responseCode, Map<String, List<String>> responseHeaderFields) {
                Log.e(TAG, "-------connectTrialEnd----");
            }

            @Override
            public void downloadFromBeginning( DownloadTask task,  BreakpointInfo info,  ResumeFailedCause cause) {
                Log.e(TAG, "-------downloadFromBeginning----");
                fileTotalLength = info.getTotalLength();
                updateDialog.setTvPackgeSize(AppUpdateUtils.div(fileTotalLength, 1024 * 1024, 2));
            }

            @Override
            public void downloadFromBreakpoint( DownloadTask task, BreakpointInfo info) {
                Log.e(TAG, "-------downloadFromBreakpoint----");
            }

            @Override
            public void connectStart( DownloadTask task, int blockIndex, Map<String, List<String>> requestHeaderFields) {
                Log.e(TAG, "-------connectStart----");
            }

            @Override
            public void connectEnd( DownloadTask task, int blockIndex, int responseCode,  Map<String, List<String>> responseHeaderFields) {
                Log.e(TAG, "-------connectEnd----");
            }

            @Override
            public void fetchStart( DownloadTask task, int blockIndex, long contentLength) {
                Log.e(TAG, "-------fetchStart----");
            }

            @Override
            public void fetchProgress( DownloadTask task, int blockIndex, long increaseBytes) {
                Log.e(TAG, "-------fetchProgress----" + increaseBytes);
                increaseLength = increaseLength + increaseBytes;
                updateDialog.updateProgress((float) (increaseLength / (double) fileTotalLength));
//                updateDialog.updateProgress((float) Arith.div(increaseLength,fileTotalLength,0));
            }

            @Override
            public void fetchEnd( DownloadTask task, int blockIndex, long contentLength) {
                Log.e(TAG, "-------fetchEnd----");
            }

            @Override
            public void taskEnd( DownloadTask task,  EndCause cause, @Nullable Exception realCause) {
                Log.e(TAG, "-------taskEnd----");
                //下载完成
                if (increaseLength == fileTotalLength) {
                    increaseLength = 0;
                    fileTotalLength = 0;
                    updateDialog.dismiss();
                    // 下载完成后，开启系统安装apk功能！
                    File file = new File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            , mFileName);
                    Log.e("ApkDownLoadManager",file.getAbsolutePath());
                    install(mActivity, file);
//                    Intent intent = new Intent();
//                    intent.setAction("android.intent.action.VIEW");
//                    intent.addCategory("android.intent.category.DEFAULT");
//                    intent.setDataAndType(
//                            Uri.parse("file:" + new File(MyFileUtil.getVideoDir() + "/" + fileName).getAbsolutePath()),
//                            "application/vnd.android.package-archive");
//                    startActivityForResult(intent, 1);
                }
            }
        });

    }

    /**
     * 通过隐式意图调用系统安装程序安装APK
     */
    public static void install(Context context, File file) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
