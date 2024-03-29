package com.example.version_adapter.android_n;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.example.utillibrary.LogUtil;

import java.io.File;

public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * 获取各种文件,android11后，基本只有getExternalCacheDir和getExternalFilesDir有用了
     *
     * @param context
     */
    public static void showAllFunctionFilepath(Context context) {

        // /storage/emulated/0/Android/data/com.beyondworlds.baseview/cache
        File externalCacheDir = context.getExternalCacheDir();
        Log.d(TAG, "getExternalCacheDir=" + externalCacheDir.getPath() + " fileCacheAbsolutePath=" + externalCacheDir.getAbsolutePath());
        //测试发现只有一个目录，就是上面那个
        File[] files = context.getExternalCacheDirs();
        if (files != null) {
            for (File fileItem : files) {
                Log.d(TAG, "getExternalCacheDirs=" + fileItem.getAbsolutePath());
            }
        }

        // /storage/emulated/0/Android/data/com.beyondworlds.baseview/files
        File fileExternalDir = context.getExternalFilesDir(null);
        Log.d(TAG, "fileExternalDir=" + fileExternalDir.getPath());
        File[] fileExternalDirs = context.getExternalFilesDirs(null);
        if (files != null) {
            for (File fileItem : fileExternalDirs) {
                Log.d(TAG, "fileCachesPath=" + fileItem.getAbsolutePath());
            }
        }

        // sdcard 路径 ：/storage/emulated/0
        File sdPath = Environment.getExternalStorageDirectory();
        Log.d(TAG, "sdPath=" + sdPath.getAbsolutePath());

        Log.d(TAG, "sdPath=" + sdPath.getAbsolutePath());
        //根目录  路径：/system
        File rootPath = Environment.getRootDirectory();
        Log.d(TAG, "rootPath=" + rootPath.getAbsolutePath());
        //数据目录  路径：/data
        File dataPath = Environment.getDataDirectory();
        Log.d(TAG, "dataPath=" + dataPath.getAbsolutePath());
        //下载目录 路径：/data/cache
        File downLoadPath = Environment.getDownloadCacheDirectory();
        Log.d(TAG, "downLoadPath=" + downLoadPath.getAbsolutePath());


        // /data/user/0/com.beyondworlds.baseview/cache,应用内部存储，要root后才能看到
        File CacheDir = context.getCacheDir();
        Log.d(TAG, "getCacheDir=" + CacheDir.getPath() + " getCacheDir=" + CacheDir.getAbsolutePath());
        // /data/user/0/com.beyondworlds.baseview/files
        File fileDir = context.getFilesDir();
        Log.d(TAG, "getFilesDir=" + fileDir.getAbsolutePath());
    }

    /**
     * 当前应用下的文件,保留长久文件，随着应用删除消失，手机设置-应用-清除数据可删除
     * 路径：/storage/emulated/0/Android/data/包名/files
     */
    public static File getPackageFile(Context context) {
        //外部存储是否可用
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.isExternalStorageRemovable()) {
            file = context.getExternalFilesDir(null);
        } else {
            file = context.getFilesDir();
        }
        return file;
    }

    /**
     * 获取当前应用下的文件路径
     *
     * @param context
     * @return
     */
    public static String getPackageFilePath(Context context) {
        return getPackageFile(context).getAbsolutePath();
    }

    /**
     * 当前应用下的缓存文件，保存临时文件，手机设置-应用-清除缓存可删除
     * 路径：/storage/emulated/0/Android/data/包名/cache
     */
    public static File getPackageCacheFile(Context context) {
        //外部存储是否可用
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && !Environment.isExternalStorageRemovable()) {
            file = context.getExternalCacheDir();
        } else {
            file = context.getCacheDir();
        }
        return file;
    }

    /**
     * 获取当前应用下的缓存文件路径
     *
     * @param context
     * @return
     */
    public static String getPackageCachePath(Context context) {
        return getPackageCacheFile(context).getAbsolutePath();
    }

    /**
     * 获取外存储卡目录
     */
    public static void getSDCardDir() {

    }

    /**
     * Android7.0获取文件Uri
     * @param context
     * @param file
     * @return
     */
    public static Uri getUriFromFile(Context context, File file) {
        Uri fileUri = null;
        //authority对应清单文件的配置
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(context,
                    context.getPackageName() + ".android7.fileprovider",
                    file);
            LogUtil.e("packageName="+context.getPackageName());
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

}
