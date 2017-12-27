package com.example.administrator;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.filedownload.R;

import java.io.IOException;


/**
 * created on 2017/10/24.
 * 邮箱:76681287@qq.com
 * @author libohan
 */
public class MyJpushService extends Service implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor ad;
    private boolean flag;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        ad=this.getResources().openRawResourceFd(R.raw.alarm_rooster);
        onPreaere();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean result=intent.getBooleanExtra("result",false);
        if (result)
        {
            Log.e("LBH","if:mediaplayer="+mediaPlayer+"//"+"if:isplaying="+mediaPlayer.isPlaying());
            if (mediaPlayer!=null&&!mediaPlayer.isPlaying()) {

                try {
                    if (!flag) {
                        this.mediaPlayer.setOnPreparedListener(this);
                        this.mediaPlayer.prepareAsync();
                    }
                    if (flag) {
                        Log.e("LBH","MyJpushService="+ Thread.currentThread().getName());
                        new Thread()
                        {
                            @Override
                            public void run() {
                                mediaPlayer.start();
                            }
                        }.start();

                    }
                    flag=true;
                } catch (Exception e) {
                    Log.e("LBH", "出现异常了");
                    Log.e("LBH","e="+e.getMessage());
                }
            }
        }else
        {
            Log.e("LBH","else:mediaplayer="+mediaPlayer+"//"+"else:isplaying="+mediaPlayer.isPlaying());
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
                new Thread()
                {
                    @Override
                    public void run() {
                        mediaPlayer.pause();
                    }
                }.start();
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void onPreaere() {
        mediaPlayer=new MediaPlayer();
        if (ad==null)
        {
            return;
        }
        try {
            mediaPlayer.setDataSource(ad.getFileDescriptor(),ad.getStartOffset(),ad.getLength());
        } catch (IOException e) {
            Log.e("LBH","报错了="+e.getMessage());
        }
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnCompletionListener(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LBH","you are can not kill me ");
        Intent intent=new Intent(this,MyJpushService.class);
        intent.putExtra("result",false);
        startService(intent);
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.seekTo(0);
        mediaPlayer.stop();
    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("LBH","开始播放了");
       this.mediaPlayer.start();
    }
}
