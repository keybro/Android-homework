package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public void onCreate() {

        Runnable myThread = new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()){
                    try {
                        Thread.sleep(10);
                        Log.i("Thread","有更新了.....");
                        System.out.println("开始更新了");
                    }catch (InterruptedException e){
                        Log.i("Thread","线程被中断");
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;

        }
}