package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubActivity2 extends AppCompatActivity {

    @BindView(R.id.sub2ListView)
    ListView listView;

    @BindView(R.id.sub2MakeSure)
    Button makeSureBtn;

    private String itemArray [] = {"aoc","dell","logi"};

    private String choseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        ButterKnife.bind(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.parseColor("#DCDCDC"));
                choseItem =parent.getItemAtPosition(position).toString();
            }
        });
    }

    @OnClick({R.id.sub2MakeSure})
    public void makeSure(){
        Intent intent = new Intent();
        intent.putExtra("chose",choseItem);
        intent.setClass(SubActivity2.this,callActivity.class);
        startActivity(intent);
    }
}