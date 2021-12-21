package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubActivity1 extends AppCompatActivity {

    @BindView(R.id.sub1UsernameInput)
    EditText userNameInput;

    @BindView(R.id.sub1PasswordInput)
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        ButterKnife.bind(this);

        Bundle extras = this.getIntent().getExtras();
        String userName = extras.getString("username");
        String password = extras.getString("password");

        userNameInput.setText(userName);
        passwordInput.setText(password);
    }
}