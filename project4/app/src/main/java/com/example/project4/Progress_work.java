package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Progress_work extends AppCompatActivity {
    @BindView(R.id.raise)
    Button buttonRaise;

    @BindView(R.id.increase)
    Button buttonIncrease;

    @BindView(R.id.pbSmall)
    ProgressBar smallPb;

    @BindView(R.id.Visual)
    Button VisualButton;

    @BindView(R.id.seekBar)
    SeekBar seekBar;

    @BindView(R.id.SeekText)
    TextView seekTextView;

    @BindView(R.id.pbHro)
    ProgressBar pbhro;

    @BindView(R.id.programDialog)
    Button programDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_work);
        //初始化
        ButterKnife.bind(this);
        seekBar.setProgress(50);

        /**
         * seekBar事件
         */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekTextView.setText("当前位置:"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekTextView.setText("拖动滑块设置");

            }
        });
    }

    /**
     * btnVisual
     */
    @OnClick(R.id.Visual)
    public void setVisual(View view){
        if (smallPb.getVisibility() == View.VISIBLE) {
            smallPb.setVisibility(View.GONE);
        } else {
            smallPb.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.raise)
    public void increase(View v) {
        pbhro.incrementProgressBy(2);
    }

    //btnReduce
    @OnClick(R.id.increase)
    public void decrease(View v) {
        pbhro.incrementProgressBy(-2);
    }

    @OnClick(R.id.programDialog)
    public void dialogProgram(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("进度条进度:");
        progressDialog.setMessage("当前进度为：50%");
        progressDialog.setCancelable(true);
        progressDialog.incrementProgressBy(50);
        progressDialog.setMax(100);
        progressDialog.show();
    }
}