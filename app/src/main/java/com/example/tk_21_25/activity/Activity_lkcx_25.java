package com.example.tk_21_25.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.LKCX_25;
import com.example.tk_21_25.util.H;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_lkcx_25 extends AppCompatActivity {
    private TextView mTvLkcxPm25;
    private TextView mTvLkcxWd;
    private TextView mTvLkcxSd;
    private TextView mTvLkcx1h;
    private TextView mTvLkcx2h;
    private TextView mTvLkcx3h;
    private TextView mTvLkcx1color;
    private TextView mTvLkcx2color;
    private TextView mTvLkcx3color;
    private Timer timer;
    private List<LKCX_25> lkcx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lkcx_25);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seta();
                setb();
            }
        },0,3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void seta(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_all_sense", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s =response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String pm25 = jsonObject.getString("pm25");
                    String temperature = jsonObject.getString("temperature");
                    String humidity = jsonObject.getString("humidity");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvLkcxPm25.setText("PM2.5："+pm25);
                            mTvLkcxWd.setText("温 度："+temperature);
                            mTvLkcxSd.setText("湿 度："+humidity);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void setb(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("RoadId",0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_road_status", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s =response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    lkcx = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(),new TypeToken<List<LKCX_25>>(){}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (lkcx.get(1).getState()){
                                case 1:
                                    mTvLkcx1color.setBackgroundColor(getResources().getColor(R.color.通畅));
                                    mTvLkcx1h.setText("1号道路：通畅");break;
                                case 2:
                                    mTvLkcx1color.setBackgroundColor(getResources().getColor(R.color.较通畅));
                                    mTvLkcx1h.setText("1号道路：较通畅");break;
                                case 3:
                                    mTvLkcx1color.setBackgroundColor(getResources().getColor(R.color.拥挤));
                                    mTvLkcx1h.setText("1号道路：拥挤");break;
                                case 4:
                                    mTvLkcx1color.setBackgroundColor(getResources().getColor(R.color.堵塞));
                                    mTvLkcx1h.setText("1号道路：堵塞");break;
                                case 5:
                                    mTvLkcx1color.setBackgroundColor(getResources().getColor(R.color.爆表));
                                    mTvLkcx1h.setText("1号道路：爆表");break;
                            }
                            switch (lkcx.get(2).getState()){
                                case 1:
                                    mTvLkcx2color.setBackgroundColor(getResources().getColor(R.color.通畅));
                                    mTvLkcx2h.setText("2号道路：通畅");break;
                                case 2:
                                    mTvLkcx2color.setBackgroundColor(getResources().getColor(R.color.较通畅));
                                    mTvLkcx2h.setText("2号道路：较通畅");break;
                                case 3:
                                    mTvLkcx2color.setBackgroundColor(getResources().getColor(R.color.拥挤));
                                    mTvLkcx2h.setText("2号道路：拥挤");break;
                                case 4:
                                    mTvLkcx2color.setBackgroundColor(getResources().getColor(R.color.堵塞));
                                    mTvLkcx2h.setText("2号道路：堵塞");break;
                                case 5:
                                    mTvLkcx2color.setBackgroundColor(getResources().getColor(R.color.爆表));
                                    mTvLkcx2h.setText("2号道路：爆表");break;
                            }
                            switch (lkcx.get(3).getState()){
                                case 1:
                                    mTvLkcx3color.setBackgroundColor(getResources().getColor(R.color.通畅));
                                    mTvLkcx3h.setText("3号道路：通畅");break;
                                case 2:
                                    mTvLkcx3color.setBackgroundColor(getResources().getColor(R.color.较通畅));
                                    mTvLkcx3h.setText("3号道路：较通畅");break;
                                case 3:
                                    mTvLkcx3color.setBackgroundColor(getResources().getColor(R.color.拥挤));
                                    mTvLkcx3h.setText("3号道路：拥挤");break;
                                case 4:
                                    mTvLkcx3color.setBackgroundColor(getResources().getColor(R.color.堵塞));
                                    mTvLkcx3h.setText("3号道路：堵塞");break;
                                case 5:
                                    mTvLkcx3color.setBackgroundColor(getResources().getColor(R.color.爆表));
                                    mTvLkcx3h.setText("3号道路：爆表");break;
                            }
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void initView() {
        mTvLkcxPm25 = (TextView) findViewById(R.id.tv_lkcx_pm25);
        mTvLkcxWd = (TextView) findViewById(R.id.tv_lkcx_wd);
        mTvLkcxSd = (TextView) findViewById(R.id.tv_lkcx_sd);
        mTvLkcx1h = (TextView) findViewById(R.id.tv_lkcx_1h);
        mTvLkcx2h = (TextView) findViewById(R.id.tv_lkcx_2h);
        mTvLkcx3h = (TextView) findViewById(R.id.tv_lkcx_3h);
        mTvLkcx1color = (TextView) findViewById(R.id.tv_lkcx_1color);
        mTvLkcx2color = (TextView) findViewById(R.id.tv_lkcx_2color);
        mTvLkcx3color = (TextView) findViewById(R.id.tv_lkcx_3color);
    }
}
