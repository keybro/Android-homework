package com.example.bar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bar.utils.ToastUtil;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Toolbar toolbar = (Toolbar)findViewById(R.id.subToolbar);

        toolbar.setTitle("第二页大标题");
        toolbar.setSubtitle("第二页小标题");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    /**
     * 为toolbar添加菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.submenu,menu);
        Button button = (Button) menu.findItem(R.id.toolbarBtn).getActionView();
        button.setText("按钮");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(getApplicationContext(),"点击了toolbar按钮", Toast.LENGTH_LONG);
            }
        });
        return true;
    }

}