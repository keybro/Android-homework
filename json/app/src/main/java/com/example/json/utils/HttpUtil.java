package com.example.json.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    /**
     * 发送http请求
     *
     * @param url    地址
     * @param params 参数
     * @return 请求结果
     */
    public static String get(String url, Map<String, String> params) {

        //httpBuilder对象
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        //往httpBuilder中添加参数
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        //创建request对象
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .get()
                .build();

        try {
            //创建okHttp对象
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Response response = client.newCall(request).execute();


            //以下是一种写法，但是我不想这么写，偷懒就是玩儿~
//            Response response = client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//
//                }
//            });
            return response.body().string();
        } catch (IOException e) {
            return null;
        }
    }

}