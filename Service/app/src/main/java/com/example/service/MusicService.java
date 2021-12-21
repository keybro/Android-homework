package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }
    public final IBinder m_binder = new MusicBinder();
    private static MediaPlayer m_mp = null;

    public void PlayMusic() {
        if (m_mp == null) {
            m_mp = MediaPlayer.create(this, R.raw.hhh);
            m_mp.setLooping(false);
        }
        if (!m_mp.isPlaying()) {
            m_mp.start();
        }

        // 前台方式打开
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext())
                .setWhen(System.currentTimeMillis())
                .setContentText("正在播放:Burn it all down")
                .setContentTitle("来自MusicService的通知")
                .setSmallIcon(R.mipmap.ic_launcher);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_TWO_ID", "CHANNEL_TWO_NAME", NotificationManager.IMPORTANCE_MIN);
            notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(true);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
            builder.setChannelId("CHANNEL_TWO_ID");
        }
        Notification build = builder.build();
        nm.notify(1,build);

//        startForeground(10, build);


    }




    public void StopMusic() {
        if (m_mp != null && m_mp.isPlaying()) {
            m_mp.pause();
        }

    }


    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return m_binder;
    }
}