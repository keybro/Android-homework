package com.example.json.service.impl;

import com.example.json.entity.LoginBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRequestServiceImpl implements GetRequestService{

    @Override
    public Call<ResponseBody> getWeather(String city) {

        //设置路由地址
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wthrcdn.etouch.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //设置参数
        Call<ResponseBody> call = retrofit.create(GetRequestService.class)
                .getWeather(city);

        return call;
    }

    @Override
    public Call<LoginBean> login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://levant.top:9090")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //设置参数
        Call<LoginBean> call = retrofit.create(GetRequestService.class)
                .login();
        return call;
    }

}
