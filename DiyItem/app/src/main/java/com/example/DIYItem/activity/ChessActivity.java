package com.example.DIYItem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.DIYItem.R;

import butterknife.ButterKnife;

public class ChessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);
        ButterKnife.bind(this);
    }
}