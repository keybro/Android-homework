package com.example.json;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.json.entity.LoginBean;
import com.example.json.entity.Weather;
import com.example.json.service.impl.GetRequestServiceImpl;
import com.example.json.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import com.example.json.utils.HttpUtil;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cityInput)
    EditText cityInput;

    @BindView(R.id.searchBtn)
    Button searchBtn;

    @BindView(R.id.weatherListView)
    ListView weatherListView;

    @BindView(R.id.bingPicture)
    ImageView bingPicture;

    @BindView(R.id.bingPictureBtn)
    Button bingPictureBtn;

    @BindView(R.id.picDiscribe)
    TextView picDiscribe;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.loginTextView)
    TextView loginTextView;

    @BindView(R.id.loginPic)
    ImageView loginPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.searchBtn,R.id.bingPictureBtn,R.id.loginBtn})
    public void click(View view){
        switch (view.getId()){

            //查询天气按钮
            case R.id.searchBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("开始执行线程");

                        String city = "杭州";
                        if (!cityInput.getText().toString().equals(city)){
                            city = cityInput.getText().toString().trim();
                        }
                        GetRequestServiceImpl getRequestService = new GetRequestServiceImpl();
                        Call<ResponseBody> call = getRequestService.getWeather(city);
                        System.out.println("已经发送了参数");
                        String jsonString = null;
                        try {
                            Response<ResponseBody> response = call.execute();
                            jsonString = response.body().string();
                            System.out.println("这里是jsonString");
                            System.out.println(jsonString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //将结果传到handle中进行操作
                        Message message = Message.obtain();
                        Bundle bundle = new Bundle();
                        bundle.putString("jsonStr",jsonString);
                        message.setData(bundle);
                        handlerWeather.sendMessage(message);

                    }
                }).start();
                break;

            //查询bing每日一图按钮
            case R.id.bingPictureBtn:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        String copyright = null;
                        //使用写好的HttpUtil来调用
                        String searchUrl = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=5&mkt=zh-CN";
                        //获取的json字段中url字段前面需要加上headUrl
                        String headUrl = "http://www.bing.com";
                        String response = HttpUtil.get(searchUrl, null);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //images对象是所有的bing每日一图的所有信息
                            JSONArray images = jsonObject.getJSONArray("images");

                            //默认获取第一个object
                            JSONObject image1 = images.getJSONObject(1);
                            //图片描述
                            copyright = image1.getString("copyright");
                            //获取图片对应的链接，但是前面要加上head
                            String url = image1.getString("url");
                            String URLPath = "http://www.bing.com"+url;
                            try {
                                //图片下载
                                URL pictureURL = new URL(URLPath);
                                HttpURLConnection connection;
                                connection = (HttpURLConnection) pictureURL.openConnection();
                                connection.setDoInput(true);
                                InputStream inputStream = connection.getInputStream();
                                //将流转换为Bitmap
                                bitmap = BitmapFactory.decodeStream(inputStream);
                                inputStream.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }

                            //信息传入handle操作
                            Message message = Message.obtain();
                            //图片对象
                            message.obj = bitmap;
                            Bundle bundle = new Bundle();
                            bundle.putString("copyright",copyright);
                            message.setData(bundle);
                            handlerBingData.sendMessage(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.loginBtn:
                System.out.println("进行了点击");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        GetRequestServiceImpl getRequestService2 = new GetRequestServiceImpl();
                        Call<LoginBean> call = getRequestService2.login();
                        String jsonString = null;
                        int id=0;
                        try {
                            Response<LoginBean> response = call.execute();
                            id = response.body().getPictureId();
                            String data = response.body().getData();
                            byte[] bitmapArray;
                            bitmapArray  = Base64.decode(data,Base64.DEFAULT);
                            bitmap = BitmapFactory.decodeByteArray(bitmapArray,0,bitmapArray.length);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println(jsonString);
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        Bundle bundle = new Bundle();
                        bundle.putString("userLogin",String.valueOf(id));
                        message.setData(bundle);
                        handlerLoginData.sendMessage(message);


                    }
                }).start();


        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handlerWeather = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle msgData = msg.getData();
            String jsonString  =  msgData.getString("jsonStr");
            System.out.println(jsonString);
            //json处理
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject data = jsonObject.getJSONObject("data");

                String city = data.getString("city");
                JSONArray forecastArray = data.getJSONArray("forecast");
                List<Weather> weatherList = new Gson().fromJson(forecastArray.toString(),
                        new TypeToken<List<Weather>>(){}.getType());
                //list处理
                ArrayAdapter<Weather> weatherArrayAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,weatherList);
                weatherListView.setAdapter(weatherArrayAdapter);
                ToastUtil.showToast(getApplicationContext(),"已更新为"+city+"天气", Toast.LENGTH_LONG);

            } catch (JSONException e) {
                e.printStackTrace();
                ToastUtil.showToast(getApplicationContext(), "城市名无效，请重新输入", Toast.LENGTH_SHORT);

            }
            super.handleMessage(msg);

        }
    };


    @SuppressLint("HandlerBingLink")
    private Handler handlerBingData = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle msgData = msg.getData();
            //图片描述
            String copyright = msgData.getString("copyright");
            picDiscribe.setText(copyright);

            Bitmap bitmap = (Bitmap)msg.obj;
            //UI处理
            bingPicture.setVisibility(View.VISIBLE);
            bingPicture.setImageBitmap(bitmap);
            ToastUtil.showToast(getApplicationContext(),"图片更新成功",Toast.LENGTH_LONG);
            super.handleMessage(msg);
        }
    };

    @SuppressLint("HandlerLoginLink")
    private Handler handlerLoginData = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle msgData = msg.getData();
            Bitmap bitmap = (Bitmap)msg.obj;
            String jsonString = msgData.getString("userLogin");
            loginTextView.setText("pictureId:"+jsonString);
            loginPicture.setVisibility(View.VISIBLE);
            loginPicture.setImageBitmap(bitmap);
            super.handleMessage(msg);
        }
    };



}