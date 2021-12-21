package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;

import com.example.databasetest.dao.WeatherDataDao;
import com.example.databasetest.db.DBWeather;
import com.example.databasetest.entity.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    DBWeather dbWeather;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void click(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBWeather dbWeather = Room.databaseBuilder(getApplicationContext(), DBWeather.class, "weather.db").allowMainThreadQueries().build();

                System.out.println("开始插入");
                WeatherDataDao weatherDataDao = dbWeather.weatherDataDao();
                WeatherData weatherData1 = new WeatherData(20, "asdf","asdf", "asdf");
                weatherDataDao.insertAll(weatherData1);


//                WeatherData weatherData = new WeatherData(19, "2021-12-09", "18.0C", "22.0C");
//                DatabaseTestDao databaseTestDao = MainActivity.this.dbWeather.databaseTestDao();
//                databaseTestDao.insertData(weatherData);

                System.out.println("插入成功");

            }
        }).start();


    }
}