package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.thread.ThreadCollect.MultiThread;
import com.example.thread.receive.BatteryReceiver;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.singleThread)
    Button singleThreadBtn;

    @BindView(R.id.multiThread)
    Button multiThreadBtn;

    @BindView(R.id.endThread)
    Button endThreadBtn;

    @BindView(R.id.input)
    EditText input;

    @BindView(R.id.toBroadCast)
    Button toBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


   Thread multiThread = new MultiThread();


    @OnClick({R.id.singleThread,R.id.multiThread,R.id.endThread,R.id.toBroadCast})
    public void click(View view){
        switch (view.getId()){
            case R.id.singleThread:
                //制造卡顿
                for (int i = 0;i<5000000;i++){
                    for (int j = 0;j<5000000;j++){
                    }
                }

                break;
            case R.id.multiThread:
                multiThread.start();
                break;
            case R.id.endThread:
                multiThread.interrupt();
                break;
            case R.id.toBroadCast:
                Intent intent = new Intent(this, BroadCast.class);
                startActivity(intent);
        }
    }
}