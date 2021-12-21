package com.example.project4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class complex_dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_dialog);
        /**
         * 选课按钮
         */
        Button btnCourse = findViewById(R.id.courseButton);

        /**
         * 下一个按钮
         */
        Button btnNext = findViewById(R.id.nextButton);

        final CharSequence[] items = {" 数学 "," 英语 "," 物理 "};

        final ArrayList selectItems=new ArrayList();

        /**
         * 对话框的构造方式，传入上下文参数
         */
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("这是一个标题").setIcon(R.drawable.button2);

        /**
         * 确定按钮
         */
        dialog.setPositiveButton("确定",null);


        dialog.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //如果当期那复选框被选中
                String selectAnswer="";
                if (isChecked){
                    selectItems.add(items[which]);
                }
                else {
                    //复选框没有选中就从list中删除
                    selectItems.remove(which);
                }
                for (int i = 0;i<selectItems.size();i++){
                    if (i!=selectItems.size()-1){
                        selectAnswer+=selectItems.get(i)+",";
                    }
                    else {
                        selectAnswer+=selectItems.get(i);
                    }
                }
                Toast.makeText(complex_dialog.this,"你选择了"+selectAnswer,Toast.LENGTH_LONG).show();

            }
        });
        /**
         *取消按钮
         */
        dialog.setNegativeButton("取消",null);

        dialog.create();

        class choseCourse implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                dialog.show();
            }
        }
        btnCourse.setOnClickListener(new choseCourse());

        class next implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(complex_dialog.this, DatePicker_work.class);
                startActivity(intent);
            }
        }

        btnNext.setOnClickListener(new next());



    }
}