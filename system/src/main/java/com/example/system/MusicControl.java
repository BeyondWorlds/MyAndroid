package com.example.system;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.utillibrary.LogUtil;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicControl {
    private final static String TAG = "MusicControl";
    private static MusicControl instance;
    private AudioManager mAudioManager;
    private Context mContext;
    //    01表示开始，02表示暂停，03表示手环在音乐界面，00表示收到
//    private final int CONTROL_MEDIA_SUCCESS = 0;
    private final int CONTROL_MEDIA_PLAYING = 1;
    private final int CONTROL_MEDIA_PAUSE = 2;
    //    private final int CONTROL_MEDIA_MUSICVIEW = 3;
    public final static int VOLUME_UP = 0;
    public final static int VOLUME_DOWN = 1;
    public static int MUSIC_VOLUME_STATUS = VOLUME_UP;
    boolean isMusicActive = false;
    private String musicTitle = "";

    public static MusicControl getInstance(Context paramContext) {
        if (instance == null) {
            instance = new MusicControl(paramContext);
        }
        return instance;
    }

    public MusicControl(Context paramContext) {
        mContext = paramContext;
        mAudioManager = (AudioManager) paramContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public void doControl(int param) {
        if (Build.VERSION.SDK_INT >= 19) {
            api19(param);
            return;
        }
        api18(param);
    }

    private final String SERVICECMD = "com.android.music.musicservicecommand";
    private final String CMDNAME = "command";
    private final String CMDPLAY = "play";
    private final String CMDPAUSE = "pause";
    private final String CMDPREVIOUS = "previous";
    private final String CMDNEXT = "next";

    @RequiresApi(api = 19)
    private void api19(int paramInt) {
        long l1 = SystemClock.uptimeMillis() - 1L;
        Object localObject = new KeyEvent(l1, l1, 0, paramInt, 0);
        mAudioManager.dispatchMediaKeyEvent((KeyEvent) localObject);
        l1 += 1L;
        localObject = new KeyEvent(l1, l1, 1, paramInt, 0);
        mAudioManager.dispatchMediaKeyEvent((KeyEvent) localObject);

        Intent intent = new Intent(SERVICECMD);
        switch (paramInt) {
            case KeyEvent.KEYCODE_MEDIA_PLAY:
                intent.putExtra(CMDNAME, CMDPLAY);
                break;
            case KeyEvent.KEYCODE_MEDIA_PAUSE:
                intent.putExtra(CMDNAME, CMDPAUSE);
                break;
            case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                intent.putExtra(CMDNAME, CMDPREVIOUS);
                break;
            case KeyEvent.KEYCODE_MEDIA_NEXT:
                intent.putExtra(CMDNAME, CMDNEXT);
                break;
        }
        mContext.sendBroadcast(intent);
    }


    private void api18(final int paramInt) {
        new Thread() {
            @Override
            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(paramInt);
                    return;
                } catch (Exception localException) {
                    Log.d(TAG, "Instrumentation error: " + localException.toString());
                }
            }
        }.start();
    }

    public void musicControl(int type) {
        switch (type) {
            case 1:
                doControl(KeyEvent.KEYCODE_CAMERA);
                isMusicActive = true;
                return;
            case 6:
                doControl(KeyEvent.KEYCODE_MEDIA_PLAY);
                isMusicActive = true;
                return;
            case 7:
                doControl(KeyEvent.KEYCODE_MEDIA_PAUSE);
                isMusicActive = false;
                return;
            case 9:
                doControl(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
                isMusicActive = true;
                return;
            case 8:
                doControl(KeyEvent.KEYCODE_MEDIA_NEXT);
                isMusicActive = true;
                return;
            default:
                return;
        }

    }

    /**
     * 音量+-
     *
     * @param volumeStatus
     */
    public void controlVolumeUpDown(int volumeStatus) {
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.d(TAG, "currentVolume: " + currentVolume);
        if (volumeStatus % 2 == VOLUME_UP) {
            MUSIC_VOLUME_STATUS = VOLUME_UP;
            int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            if (currentVolume < maxVolume){
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume + 1, 0);

            }
        } else {
            MUSIC_VOLUME_STATUS = VOLUME_DOWN;
            if (currentVolume > 0){
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume - 1, 0);

            }
        }
    }

    private MediaSessionManager mMediaSessionManager;
    private List<MediaController> tmpControllersList;
    private List<Integer> stateList = new ArrayList<>();
    private int lastStatus = -1;
    private boolean isInit = false;
    private MediaController cMediaController; //当前音乐播放器
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initMediaController() {
        Log.i(TAG, "初始化 initMediaController:"+isInit);
        try {
            if(isInit){
                return;
            }

            if (mMediaSessionManager == null){
                mMediaSessionManager = (MediaSessionManager) mContext.getSystemService(Context.MEDIA_SESSION_SERVICE);

            }

            mMediaSessionManager.addOnActiveSessionsChangedListener(new MediaSessionManager.OnActiveSessionsChangedListener() {
                @Override
                public void onActiveSessionsChanged(@Nullable List<MediaController> controllers) {
                    LogUtil.d(TAG, "---onActiveSessionsChanged--");
                    if(controllers!= null){
                        if(tmpControllersList!=null){
                            if(controllers.size() != tmpControllersList.size()){
                                registerMediaControllerAll(controllers);
                            }
                        }else{
                            registerMediaControllerAll(controllers);
                        }
                    }
                }
            }, new ComponentName(mContext, MsgNotifyService.class));
            List<MediaController> list = mMediaSessionManager.getActiveSessions(new ComponentName(mContext, MsgNotifyService.class));
            registerMediaControllerAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void removeMediaController() {
        if (tmpControllersList != null) {
            for (int i = 0; i < tmpControllersList.size(); i++) {
                tmpControllersList.get(i).unregisterCallback(mediaControllerCallback);
                LogUtil.d(TAG, "取消注册PackageName 1=" + tmpControllersList.get(i).getPackageName());
            }
            tmpControllersList = new ArrayList<>();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void registerMediaControllerAll(List<MediaController> list) {
        if (list.size() == 0){
            return;
        }
        isInit = true;
        if (tmpControllersList != null) {
            for (int i = 0; i < tmpControllersList.size(); i++) {
                tmpControllersList.get(i).unregisterCallback(mediaControllerCallback);
                LogUtil.d(TAG, "取消注册PackageName 2=" + tmpControllersList.get(i).getPackageName());
            }
        }
        tmpControllersList = new ArrayList<>();
        tmpControllersList.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            registerMediaController(list.get(i));
        }
    }


    /**
     * @param mMediaController
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void registerMediaController(MediaController mMediaController) {
        Log.i(TAG, "当前注册PackageName =" + mMediaController.getPackageName());
        mMediaController.registerCallback(mediaControllerCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private MediaController.Callback mediaControllerCallback = new MediaController.Callback() {
        @Override
        public void onSessionDestroyed() {
            super.onSessionDestroyed();
            Log.i(TAG, "onSessionDestroyed");
            isInit = false;
            mHandler.removeMessages(0);
            tmpControllersList.clear();
            stateList.clear();
            stateList.add(PlaybackState.STATE_PAUSED);
            mHandler.sendEmptyMessageDelayed(0, 500);
        }

        @Override
        public void onSessionEvent(@NonNull String event, @Nullable Bundle extras) {
            super.onSessionEvent(event, extras);
            LogUtil.d(TAG, "onSessionEvent  event =" + event + ",extras =" + extras);
        }

        @Override
        public void onPlaybackStateChanged(@Nullable PlaybackState state) {
            super.onPlaybackStateChanged(state);
            try {
                LogUtil.d(TAG, "onPlaybackStateChanged  state =" + state.getState() + "," + state);
                synMusicInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }

//            if (state.getState() == PlaybackState.STATE_PAUSED || state.getState() == PlaybackState.STATE_PLAYING) {
//                mHandler.removeMessages(0);
//                stateList.clear();
//                stateList.add(state.getState());
//                mHandler.sendEmptyMessageDelayed(0, 500);
//            }
        }

        @Override
        public void onMetadataChanged(@Nullable MediaMetadata metadata) {
            super.onMetadataChanged(metadata);
            String artist = "";
            String album = "";
            String title = "";
            long duration = 0L;
            if (metadata != null) {
                artist = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
                album = metadata.getString(MediaMetadata.METADATA_KEY_ALBUM);
                title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
                duration = metadata.getLong(MediaMetadata.METADATA_KEY_DURATION);
            }
            musicTitle = title;
            LogUtil.d(TAG, "artist =" + artist + ",album =" + album + ",title =" + title + ",duration =" + duration);
            // 同步APP音乐数据到设备
        }

        @Override
        public void onQueueChanged(@Nullable List<MediaSession.QueueItem> queue) {
            super.onQueueChanged(queue);
            LogUtil.d(TAG, "onQueueChanged  queue =" + queue);
        }

        @Override
        public void onQueueTitleChanged(@Nullable CharSequence title) {
            super.onQueueTitleChanged(title);
            LogUtil.d(TAG, "onQueueChanged  title =" + title);
        }

    };

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (stateList != null && stateList.size() > 0) {
//                        if (lastStatus == stateList.get(stateList.size() - 1)) {
//                            LogUtil.d("wl", "两次播放状态一致："+lastStatus);
//                            return;
//                        }
//                        lastStatus = stateList.get(stateList.size() - 1);
                        int status = 0;
                        if (lastStatus == PlaybackState.STATE_PAUSED) {
                            status = CONTROL_MEDIA_PAUSE;
                            isMusicActive = false;
                        } else if (lastStatus == PlaybackState.STATE_PLAYING) {
                            status = CONTROL_MEDIA_PLAYING;
                            isMusicActive = true;
                        }
                    }
                    break;
                default:
                    break;
            }

        }
    };


    /**
     * 获取当前媒体音量
     *
     * @return
     */
    private int getCurrentMusicVolume() {
        return mAudioManager != null ? mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) : -1;
    }

    private int getMusicVolumePercent(int curV, int maxV) {
        float p = (float) curV / (float) maxV;
        int percent = (int) (p * 100);
        percent = Math.min(percent, 100);
        return percent;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void synMusicInfo(){
        if(tmpControllersList!=null && tmpControllersList.size()>0){
            LogUtil.d(TAG, "播放器list:"+tmpControllersList.size());
            for(int i=0; i<tmpControllersList.size(); i++){
                MediaController mediaController = tmpControllersList.get(i);
                if(mediaController.getPlaybackState().getState() == PlaybackState.STATE_PLAYING){
                    cMediaController = mediaController;
                    break;
                }
                cMediaController = mediaController;
            }
            String title = "";
            MediaMetadata metadata = cMediaController.getMetadata();
            if (metadata != null) {
                title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE);
            }
            musicTitle = title;
            LogUtil.d("wl", "获取当前播放音乐信息："+title+", 播放状态："+cMediaController.getPlaybackState().getState());
            if (cMediaController.getPlaybackState().getState() == PlaybackState.STATE_PAUSED) {
                isMusicActive = false;
            } else if (cMediaController.getPlaybackState().getState() == PlaybackState.STATE_PLAYING) {
                isMusicActive = true;
            }
        }
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public void execShell(String cmd){
        try{
            //权限设置
            Process p = Runtime.getRuntime().exec("su");
            //获取输出流
            OutputStream outputStream = p.getOutputStream();
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            //将命令写入
            dataOutputStream.writeBytes(cmd);
            //提交命令
            dataOutputStream.flush();
            //关闭流操作
            dataOutputStream.close();
            outputStream.close();
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }
}