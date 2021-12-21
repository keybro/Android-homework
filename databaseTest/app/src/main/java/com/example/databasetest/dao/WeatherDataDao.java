package com.example.databasetest.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.databasetest.entity.WeatherData;

@Dao
public interface WeatherDataDao {
    @Insert
    void insertAll(WeatherData... weatherData);

}
