package com.example.tabandbnv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.tabandbnv.adapter.FragmentPagersAdapter;
import com.example.tabandbnv.data.EditViewModel;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toBnvBtn)
    Button toBnvBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        tableLayout.addTab(tableLayout.newTab().setText("头条").setIcon(R.mipmap.ic_launcher));
        tableLayout.addTab(tableLayout.newTab().setText("娱乐").setIcon(R.mipmap.ic_launcher));
        tableLayout.addTab(tableLayout.newTab().setText("体育").setIcon(R.mipmap.ic_launcher));

        viewPager.setAdapter(new FragmentPagersAdapter(getSupportFragmentManager()));
        tableLayout.setupWithViewPager(viewPager);

    }

    @OnClick(R.id.toBnvBtn)
    public void click(View view){
        switch (view.getId()){
            case R.id.toBnvBtn:
                Intent intent = new Intent(this, BnvActivity.class);
                startActivity(intent);
                break;
        }
    }
}