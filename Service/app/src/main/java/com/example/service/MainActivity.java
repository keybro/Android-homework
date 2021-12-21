package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.openUpdateBtn)
    Button updateButton;

    @BindView(R.id.closeUpdateBtn)
    Button closeUpdateBtn;

    @BindView(R.id.openMusicBtn)
    Button  openMusicBtn;

    @BindView(R.id.closeMusicBtn)
    Button  closeMusicBtn;

    @BindView(R.id.IntentServiceBtn)
    Button IntentServiceBtn;

    Intent updateIntent;

    ServiceConnection sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }




    @OnClick({R.id.openUpdateBtn,R.id.closeUpdateBtn,R.id.openMusicBtn,R.id.closeMusicBtn,R.id.IntentServiceBtn})
    public void click(View view){
        switch (view.getId()){
            case R.id.openUpdateBtn:
                System.out.println("电路");
                updateIntent = new Intent();
                updateIntent.setClass(this,UpdateService.class);
                startService(updateIntent);
                break;
            case R.id.closeUpdateBtn:
                System.out.println("关闭更新");
                stopService(updateIntent);
                break;
            case R.id.openMusicBtn:
                Intent musicInten = new Intent();
                musicInten.setClass(this,MusicService.class);
                sc = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        MusicService musicService =((MusicService.MusicBinder)service).getService();
                        if (musicService != null){
                            musicService.PlayMusic();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(musicInten,sc,BIND_AUTO_CREATE);

                break;
            case R.id.closeMusicBtn:
//                System.out.println("关闭音乐");
                MusicService musicService = new MusicService();
                musicService.StopMusic();
                unbindService(sc);
                break;
            case R.id.IntentServiceBtn:
                System.out.println("打开了IntentService");
                Intent IntentServiceIntent = new Intent(this,MyIntentService.class);
                startService(IntentServiceIntent);
        }
    }
}