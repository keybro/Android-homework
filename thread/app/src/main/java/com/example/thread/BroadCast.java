package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thread.receive.BatteryReceiver;
import com.example.thread.receive.MyReceiver;
import com.example.thread.receive.wifiReceiver;
import com.example.thread.receive.BootReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BroadCast extends AppCompatActivity {

    @BindView(R.id.sendBtn)
    Button SendBtn;

    @BindView(R.id.broadCastInput)
    EditText BroadCastInput;

    public static final String MY_ACTION = "com.example.thread.LR_Action";

    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_voltage)
    TextView tvVoltage;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_health)
    TextView tvHealth;
    @BindView(R.id.tv_tech)
    TextView tvTech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        ButterKnife.bind(this);
        //动态注册广播接收器
        initRegister();
    }

    public void initRegister(){
        //自定义
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver,new IntentFilter(MY_ACTION));

        //wifi
        wifiReceiver wifiReceiver = new wifiReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver,intentFilter);

        //电池
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //开机启动服务
        BootReceiver bootReceiver = new BootReceiver();
        registerReceiver(bootReceiver,new IntentFilter(Intent.ACTION_BOOT_COMPLETED));

    }

    @OnClick({R.id.sendBtn})
    public void click(View view){
        switch (view.getId()){
            case R.id.sendBtn:
                String broadCastContent = BroadCastInput.getText().toString();
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);

                intent.putExtra("msg",broadCastContent);
                sendBroadcast(intent);
                break;
        }
    }

    BatteryReceiver batteryReceiver = new BatteryReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            // 温度
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            tvTemperature.setText("温度：" + temperature / 10 + "." + temperature % 10 + "℃");
            if (temperature >= 300) {
                tvTemperature.setTextColor(Color.RED);
            } else {
                tvTemperature.setTextColor(Color.BLUE);
            }

            // 电压
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            tvVoltage.setText("电压：" + voltage / 1000 + "." + voltage % 1000 + "V");
            tvVoltage.setTextColor(Color.BLUE);

            // 电量
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int levelPercent = (int) (((float) level / scale) * 100);
            tvLevel.setText("电量：" + levelPercent + "%");
            if (level <= 10) {
                tvLevel.setTextColor(Color.RED);
            } else {
                tvLevel.setTextColor(Color.BLUE);
            }

            // 充电状态
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
            String strStatus = "未知状态";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    strStatus = "充电中……";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    strStatus = "放电中……";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    strStatus = "未充电";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    strStatus = "充电完成";
                    break;
            }
            tvStatus.setText("充电状态：" + strStatus);

            // 健康状况
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
            String strHealth = "未知";
            switch (health) {
                case BatteryManager.BATTERY_HEALTH_GOOD:
                    strHealth = "好";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                    strHealth = "过热";
                    break;
                case BatteryManager.BATTERY_HEALTH_DEAD: // 未充电时就会显示此状态，这是什么鬼？
                    strHealth = "良好";
                    break;
                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                    strHealth = "电压过高";
                    break;
                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                    strHealth = "未知";
                    break;
                case BatteryManager.BATTERY_HEALTH_COLD:
                    strHealth = "过冷";
                    break;
            }
            tvHealth.setText("健康状况：" + strHealth);

            //电池技术
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
            tvTech.setText("电池技术：" + technology);
        }
    };
}