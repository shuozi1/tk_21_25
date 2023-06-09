package com.example.tk_21_25.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.util.H;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_shzs_27 extends AppCompatActivity {
    private TextView mTvShzs27Pm25;
    private TextView mTvShzs27Wd;
    private TextView mTvShzs27Sd;
    private TextView mTvShzs27Gzqd;
    private TextView mTvShzs27Gzts;
    private TextView mTvShzs27Pbqd;
    private TextView mTvShzs27Pbts;
    private Timer timer;
    private int temperature;
    private int pm25;
    private int humidity;
    private int illumination;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shzs_27);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                geta();
            }
        },0,3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void geta(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_all_sense", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    temperature = jsonObject.getInt("temperature");
                    pm25 = jsonObject.getInt("pm25");
                    humidity = jsonObject.getInt("humidity");
                    illumination = jsonObject.getInt("illumination");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setText();
                        }
                    });


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void setText(){
        mTvShzs27Wd.setText("温度："+temperature);
        mTvShzs27Sd.setText("湿度："+humidity);
        mTvShzs27Pm25.setText("PM2.5："+pm25);
        if (pm25>=0&&pm25<100){
            mTvShzs27Gzqd.setText("良好");
            mTvShzs27Gzqd.setTextColor(Color.BLACK);
            mTvShzs27Gzts.setText("气象条件会对晨练影响不大");
        }else if (pm25>=100&&pm25<200){
            mTvShzs27Gzqd.setText("轻度");
            mTvShzs27Gzqd.setTextColor(Color.RED);
            mTvShzs27Gzts.setText("受天气影响，较不宜晨练");
        }else if (pm25>=200&&pm25<300){
            mTvShzs27Gzqd.setText("重度");
            mTvShzs27Gzqd.setTextColor(Color.RED);
            mTvShzs27Gzts.setText("减少外出，出行注意戴口罩");
        }else {
            mTvShzs27Gzqd.setText("爆表");
            mTvShzs27Gzqd.setTextColor(Color.RED);
            mTvShzs27Gzts.setText("停止一切外出活动");
        }

        if (illumination>0&&illumination<1500){
            mTvShzs27Pbqd.setText("非常弱");
            mTvShzs27Pbqd.setTextColor(Color.BLACK);
            mTvShzs27Pbts.setText("您无须担心紫外线");
        }else if (illumination>=150&&illumination<=3000){
            mTvShzs27Pbqd.setText("弱");
            mTvShzs27Pbqd.setTextColor(Color.BLACK);
            mTvShzs27Pbts.setText("外出适当涂抹低倍数防晒霜");
        }else {
            mTvShzs27Pbqd.setText("强");
            mTvShzs27Pbqd.setTextColor(Color.RED);
            mTvShzs27Pbts.setText("外出需要涂抹中倍数防晒霜");
        }
    }

    private void initView() {
        mTvShzs27Pm25 = (TextView) findViewById(R.id.tv_shzs_27_pm25);
        mTvShzs27Wd = (TextView) findViewById(R.id.tv_shzs_27_wd);
        mTvShzs27Sd = (TextView) findViewById(R.id.tv_shzs_27_sd);
        mTvShzs27Gzqd = (TextView) findViewById(R.id.tv_shzs_27_gzqd);
        mTvShzs27Gzts = (TextView) findViewById(R.id.tv_shzs_27_gzts);
        mTvShzs27Pbqd = (TextView) findViewById(R.id.tv_shzs_27_pbqd);
        mTvShzs27Pbts = (TextView) findViewById(R.id.tv_shzs_27_pbts);
    }
}
