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

            //??????????????????
            case R.id.searchBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("??????????????????");

                        String city = "??????";
                        if (!cityInput.getText().toString().equals(city)){
                            city = cityInput.getText().toString().trim();
                        }
                        GetRequestServiceImpl getRequestService = new GetRequestServiceImpl();
                        Call<ResponseBody> call = getRequestService.getWeather(city);
                        System.out.println("?????????????????????");
                        String jsonString = null;
                        try {
                            Response<ResponseBody> response = call.execute();
                            jsonString = response.body().string();
                            System.out.println("?????????jsonString");
                            System.out.println(jsonString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //???????????????handle???????????????
                        Message message = Message.obtain();
                        Bundle bundle = new Bundle();
                        bundle.putString("jsonStr",jsonString);
                        message.setData(bundle);
                        handlerWeather.sendMessage(message);

                    }
                }).start();
                break;

            //??????bing??????????????????
            case R.id.bingPictureBtn:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = null;
                        String copyright = null;
                        //???????????????HttpUtil?????????
                        String searchUrl = "https://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=5&mkt=zh-CN";
                        //?????????json?????????url????????????????????????headUrl
                        String headUrl = "http://www.bing.com";
                        String response = HttpUtil.get(searchUrl, null);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //images??????????????????bing???????????????????????????
                            JSONArray images = jsonObject.getJSONArray("images");

                            //?????????????????????object
                            JSONObject image1 = images.getJSONObject(1);
                            //????????????
                            copyright = image1.getString("copyright");
                            //???????????????????????????????????????????????????head
                            String url = image1.getString("url");
                            String URLPath = "http://www.bing.com"+url;
                            try {
                                //????????????
                                URL pictureURL = new URL(URLPath);
                                HttpURLConnection connection;
                                connection = (HttpURLConnection) pictureURL.openConnection();
                                connection.setDoInput(true);
                                InputStream inputStream = connection.getInputStream();
                                //???????????????Bitmap
                                bitmap = BitmapFactory.decodeStream(inputStream);
                                inputStream.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }

                            //????????????handle??????
                            Message message = Message.obtain();
                            //????????????
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
                System.out.println("???????????????");
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
            //json??????
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject data = jsonObject.getJSONObject("data");

                String city = data.getString("city");
                JSONArray forecastArray = data.getJSONArray("forecast");
                List<Weather> weatherList = new Gson().fromJson(forecastArray.toString(),
                        new TypeToken<List<Weather>>(){}.getType());
                //list??????
                ArrayAdapter<Weather> weatherArrayAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_list_item_1,weatherList);
                weatherListView.setAdapter(weatherArrayAdapter);
                ToastUtil.showToast(getApplicationContext(),"????????????"+city+"??????", Toast.LENGTH_LONG);

            } catch (JSONException e) {
                e.printStackTrace();
                ToastUtil.showToast(getApplicationContext(), "?????????????????????????????????", Toast.LENGTH_SHORT);

            }
            super.handleMessage(msg);

        }
    };


    @SuppressLint("HandlerBingLink")
    private Handler handlerBingData = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle msgData = msg.getData();
            //????????????
            String copyright = msgData.getString("copyright");
            picDiscribe.setText(copyright);

            Bitmap bitmap = (Bitmap)msg.obj;
            //UI??????
            bingPicture.setVisibility(View.VISIBLE);
            bingPicture.setImageBitmap(bitmap);
            ToastUtil.showToast(getApplicationContext(),"??????????????????",Toast.LENGTH_LONG);
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