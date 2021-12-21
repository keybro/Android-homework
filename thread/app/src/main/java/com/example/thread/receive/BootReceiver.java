package com.example.thread.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.thread.service.BootService;

public class BootReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.R)

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //开机启动，开启服务
        Intent bootIntent = new Intent(context, BootService.class);
        context.startForegroundService(bootIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            context.startForegroundService(bootIntent);
        }else {
            context.startForegroundService(bootIntent);
        }
    }
}