package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.servicetest.UpdateService;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.openUpdateBtn)
    Button openUpdateBtn;

    @BindView(R.id.closeUpdateBtn)
    Button closeUpdateBtn;

    @BindView(R.id.openMusicBtn)
    Button openMusicBtn;

    @BindView(R.id.closeMusicBtn)
    Button closeMusicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.openUpdateBtn,R.id.closeUpdateBtn,R.id.openMusicBtn,R.id.closeMusicBtn})
    public void click(View view){
        switch (view.getId()){
            case R.id.openUpdateBtn:
                Toast.makeText(this,"hhhhh",Toast.LENGTH_LONG).show();
                //updateServiceIntent.setClass(this,UpdateService.class);
                //startService(updateServiceIntent);
                break;
            case R.id.closeUpdateBtn:
                System.out.println("点击停止");
                //stopService(updateServiceIntent);
                break;
            case R.id.openMusicBtn:
                System.out.println("开始播放音乐");
                break;
            case R.id.closeMusicBtn  :
                System.out.println("停止播放音乐");

        }
    }


}