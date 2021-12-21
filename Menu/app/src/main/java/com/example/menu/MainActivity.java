package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.view.ContextMenu.ContextMenuInfo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button Button1;

    @BindView(R.id.button2)
    Button Button2;

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.listView)
    ListView listView;

    private String chose="";

    private String preChose="";

    private String[] info  = {"我喜欢","我讨厌","我不在乎"};

    private ArrayAdapter<String> adapter;

    //默认选第一个
    private int checkId = 0;

    /**
     * 创建contextMenu
     * @param menu
     * @param view
     * @param contextMenuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,ContextMenuInfo contextMenuInfo){
        getMenuInflater().inflate(R.menu.listview_menu,menu);
        super.onCreateContextMenu(menu,view,contextMenuInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, info);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

    }

    /**
     * 上下文响应,修改textView
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item){
        String color = item.getTitle().toString();

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int select = 0;
        for (int i = 0; i < listView.getCount(); i++) {
            System.out.println("listView.getItemAtPosition(i)="+listView.getItemAtPosition(i));
            System.out.println(" menuInfo.id="+ menuInfo.id);
            if (listView.getItemIdAtPosition(i) == menuInfo.id) {
                select = i;
                break;
            }
        }
        String strItem = listView.getItemAtPosition(select).toString();
        textView.setText(strItem+color);
        return super.onContextItemSelected(item);

    }


    /**
     * 重写menu打开时候的函数，让图片和选项一起生成
     * @param featureId
     * @param menu
     * @return
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    /**
     * 用于创建menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @OnClick({R.id.button1,R.id.button2})
    public void click(View view){
        switch (view.getId()){

            //第一个按钮的响应事件
            case R.id.button1:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, Button1);
                getMenuInflater().inflate(R.menu.pop_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.red:
                                textView.setTextColor(Color.RED);
                                break;
                            case R.id.blue:
                                textView.setTextColor(Color.BLUE);
                                break;
                            case R.id.green:
                                textView.setTextColor(Color.GREEN);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                break;

                //第二个按钮的响应事件
            case R.id.button2:
                PopupMenu popupMenu2 = new PopupMenu(MainActivity.this, Button2);
                getMenuInflater().inflate(R.menu.pop_check_menu,popupMenu2.getMenu());
                //获取指定位置的menuItem
                MenuItem item = popupMenu2.getMenu().getItem(checkId);
                //默认选中第一个,index = 0
                if (item!=null){
                    item.setChecked(true);
                }

                popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Traffic:
                                if (item.isChecked()){
                                    System.out.println("Traffic is check");
                                    item.setChecked(false);
                                    checkId = 0;
                                }
                                else{
                                    System.out.println("Traffic is  notecheck");
                                    item.setChecked(true);
                                    checkId = 0;
                                }
                                return true;

                            case R.id.Map:
                                if (item.isChecked()){
                                    System.out.println("map is check");
                                    item.setChecked(false);
                                    checkId = 0;
                                }
                                else{
                                    System.out.println("map is not check");
                                    item.setChecked(true);
                                    checkId = 1;
                                }
                                return true;

                            case R.id.Satellite:
                                if (item.isChecked()){
                                    System.out.println("Satellite is check");
                                    item.setChecked(false);
                                    checkId = 0;
                                }
                                else{
                                    System.out.println("Satellite is not check");
                                    item.setChecked(true);
                                    checkId = 2;
                                }
                                return true;
                            default:
                                return MainActivity.super.onOptionsItemSelected(item);

                        }
                    }
                });

                popupMenu2.show();
                break;
        }
    }

    /**
     * 顶部菜单的选择响应函数
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.option1:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
                break;
            case  R.id.option2:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
                break;
            //以下是记忆单选menu选项
            case R.id.Map:
                Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

}