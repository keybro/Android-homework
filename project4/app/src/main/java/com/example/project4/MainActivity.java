package com.example.project4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.clickButton);

        Button btnNext = findViewById(R.id.Next);

        /**
         * 对话框的构造方式，传入上下文参数
         */
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("这是一个标题").setIcon(R.drawable.button2);

        /**
         * 确定按钮
         */
        dialog.setPositiveButton("确定",null);


        class DialogInfo implements DialogInterface.OnClickListener{

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"我需要帮助",Toast.LENGTH_LONG).show();
            }
        }

        /**
         * 查看详情按钮
         */
        dialog.setNeutralButton("查看详情",new DialogInfo());

        /**
         *取消按钮
         */
        dialog.setNegativeButton("取消",null);

        dialog.create();


        class clickButton implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                dialog.show();
            }
        }

        button.setOnClickListener(new clickButton());

        class Next implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,complex_dialog.class);
                startActivity(intent);
            }
        }

        btnNext.setOnClickListener(new Next());

    }
}