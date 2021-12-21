package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.example.service.utils.ToastUtil;

public class UpdateService extends Service {
    Thread thread;

    public UpdateService() {
    }

    @Override
    public void onCreate() {
//        System.out.println("执行了onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        System.out.println("执行了onStartCommand");
        Runnable myThread = new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()){
                    try {
                        Thread.sleep(2000);
                        Log.i("Thread","有更新了.....");
                        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                                .setWhen(System.currentTimeMillis())
                                .setContentText("系统可以更新了")
                                .setContentTitle("更新通知")
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
                    }catch (InterruptedException e){
                        Log.i("Thread","更新线程被中断");
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        thread = new Thread(myThread);
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("执行了销毁函数");
        thread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}