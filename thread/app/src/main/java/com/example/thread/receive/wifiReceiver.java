package com.example.thread.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Parcelable;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.thread.utils.ToastUtil;

public class wifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String hint = "收到wifi连接广播";
        //wifi是否连接
        if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
             NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
             if (info.getState().equals(NetworkInfo.State.CONNECTED)){
                 ToastUtil.showToast(context,hint+"wifi已连接", Toast.LENGTH_LONG);
             }else if (info.getState().equals(NetworkInfo.State.DISCONNECTED)){
                 ToastUtil.showToast(context,hint+"wifi已断开",Toast.LENGTH_LONG);
             }
        }

        //wifi是否打开
        else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED){
                ToastUtil.showToast(context,hint+"已关闭wifi",Toast.LENGTH_LONG);
            }else if (wifistate == WifiManager.WIFI_STATE_ENABLED){
                ToastUtil.showToast(context,hint+"已开启wifi",Toast.LENGTH_LONG);
            }
        }
    }
}