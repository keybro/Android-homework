package com.example.individualview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.individualview.activity.ChessActivity;
import com.example.individualview.activity.helloChartsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toChessBtn)
    Button toChessBtn;

    @BindView(R.id.toChartBtn)
    Button toChartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick({R.id.toChessBtn,R.id.toChartBtn})
    public void click(View view){
        switch (view.getId()){
            case R.id.toChessBtn:
                Intent intent = new Intent(MainActivity.this, ChessActivity.class);
                startActivity(intent);
                break;
            case R.id.toChartBtn:
                Intent intent1 = new Intent(MainActivity.this, helloChartsActivity.class);
                startActivity(intent1);
                break;
        }
    }
}