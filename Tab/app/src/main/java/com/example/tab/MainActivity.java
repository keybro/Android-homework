package com.example.tab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);






    }
}