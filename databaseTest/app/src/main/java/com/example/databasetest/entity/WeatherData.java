package com.example.databasetest.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "weatherData")
public class WeatherData {
    @PrimaryKey(autoGenerate = true)
    private int weatherId;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "low")
    private String low;
    @ColumnInfo(name = "high")
    private String high;

    @Ignore
    public WeatherData(int weatherId, String date, String low, String high) {
        this.weatherId = weatherId;
        this.date = date;
        this.low = low;
        this.high = high;
    }

    public WeatherData() {
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "weatherId=" + weatherId +
                ", date='" + date + '\'' +
                ", low='" + low + '\'' +
                ", high='" + high + '\'' +
                '}';
    }
}
