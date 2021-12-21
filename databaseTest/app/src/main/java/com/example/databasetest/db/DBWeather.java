package com.example.databasetest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.databasetest.dao.WeatherDataDao;
import com.example.databasetest.entity.WeatherData;

@Database(entities = {WeatherData.class},version = 1)
public abstract class DBWeather extends RoomDatabase {
    public abstract WeatherDataDao weatherDataDao();

}
