package com.example.thread.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.thread.utils.ToastUtil;

public class BootService extends Service {
    public BootService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ToastUtil.showToast(getApplicationContext(), "系统开机，服务已启动。", Toast.LENGTH_SHORT);
        System.out.println("系统开机");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
