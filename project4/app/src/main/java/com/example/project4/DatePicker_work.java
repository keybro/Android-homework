package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;


public class DatePicker_work extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_work);

        Button btnNext = findViewById(R.id.nextButton);

        DatePicker datePicker = findViewById(R.id.datePicker);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

        /**
         * 获得当前年月日
         */
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = "";
                date = String.valueOf(year)+"年"+String.valueOf(monthOfYear)+"月"+String.valueOf(dayOfMonth)+"日";
                Toast.makeText(DatePicker_work.this,date,Toast.LENGTH_LONG).show();
            }
        });

        class Next implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatePicker_work.this,TimePickerDialog_work.class);
                startActivity(intent);
            }
        }

        btnNext.setOnClickListener(new Next());
    }

}