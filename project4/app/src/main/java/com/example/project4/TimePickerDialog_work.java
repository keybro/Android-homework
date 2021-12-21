package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimePickerDialog_work extends AppCompatActivity {

    @BindView(R.id.nextButton)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog_work);
        ButterKnife.bind(this);

        Button TPDiaglogBtn = findViewById(R.id.TPDialogBtn);

        AnalogClock analogClock = findViewById(R.id.AnalogClock);

        DigitalClock digitalClock = findViewById(R.id.DigitalClock);

        TPDiaglogBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        calendar.set(Calendar.SECOND,0);
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(),
                        timeSetListener,
                        hour,
                        minute,
                        false);
                timePickerDialog.setTitle("当前时间:"+hour+":"+minute);
                timePickerDialog.show();
            }
        });

    }

    @OnClick(R.id.nextButton)
    public void Next(){
        Intent intent = new Intent(TimePickerDialog_work.this,Progress_work.class);
        startActivity(intent);
    }
}