package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.project5.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toCall)
    Button toCallButton;

    @BindView(R.id.toLogin)
    Button toLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //增加生命周期监听者
        getLifecycle().addObserver(new MyObserver());
        System.out.println("开始了Main的onCreate");
    }

    @OnClick({R.id.toCall,R.id.toLogin})
    public void toCall(View view){
        switch (view.getId()){
            case R.id.toCall:
                Intent intent = new Intent(MainActivity.this, callActivity.class);
                startActivity(intent);
                break;
            case R.id.toLogin:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("OriLifeCycle","Activity_LifeCycle：onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("OriLifeCycle","Activity_LifeCycle：onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("OriLifeCycle","Activity_LifeCycle：onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("OriLifeCycle","Activity_LifeCycle：onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("OriLifeCycle","Activity_LifeCycle：onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("OriLifeCycle","Activity_LifeCycle：onResume");
    }

}