package com.example.json.service.impl;

import com.example.json.entity.LoginBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequestService {
    @GET("weather_mini")
    Call<ResponseBody>  getWeather(@Query("city") String city);

    @GET("/api/user/json/android/soeasy")
    Call<LoginBean> login();


}
