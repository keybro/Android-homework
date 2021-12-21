package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class callActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button callButton;

    @BindView(R.id.telInput)
    EditText telInput;

    @BindView(R.id.usernameInput)
    EditText userNameInput;

    @BindView(R.id.passwordInput)
    EditText passwordInput;

    @BindView(R.id.youchoice)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);

        textView.setText("你选择的item是:"+getIntent().getStringExtra("chose"));
    }

    @OnClick({R.id.button,R.id.login1,R.id.login2})
    public void onViewClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.button:
                String telnumber = telInput.getText().toString();
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+telnumber));
                startActivity(intent);
                break;
            case R.id.login1:
                Bundle bundle = new Bundle();
                bundle.putString("username",userNameInput.getText().toString());
                bundle.putString("password",passwordInput.getText().toString());

                intent= new Intent();
                intent.putExtras(bundle);
                intent.setClass(callActivity.this,SubActivity1.class);
                startActivity(intent);
                break;
            case R.id.login2:
                intent = new Intent(this,SubActivity2.class);
                startActivity(intent);

        }
    }
}