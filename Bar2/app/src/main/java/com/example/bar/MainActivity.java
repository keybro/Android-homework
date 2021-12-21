package com.example.bar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("大标题");
        toolbar.setSubtitle("小标题");

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.option_normal_4:
                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });


    }


    /**
     * W为toolBar添加menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }

    /**
     * 解决menu显示不了icon的问题
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if(menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod( "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true); m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }
}