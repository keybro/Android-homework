package com.example.thread.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.thread.BroadCast;
import com.example.thread.utils.ToastUtil;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String action = intent.getAction();
        if (action.compareTo(BroadCast.MY_ACTION)==0){
            String msg = intent.getStringExtra("msg");
            System.out.println(msg);
            ToastUtil.showToast(context,"收到自定义广播"+msg,Toast.LENGTH_LONG);
        }

    }
}