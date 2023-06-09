package com.example.tk_21_25.adapter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.tk_21_25.R;
import com.example.tk_21_25.util.H;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Sshj_29_3 extends Fragment {
    private LineChart mLcSshj291;
    private List<Integer>list;
    private List<String>time;
    private Timer timer;
    private int a;
    private List<Integer> colors = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sshj_29_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=new ArrayList<>();
        time=new ArrayList<>();
        a=0;
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seta();
            }
        },0,3000);
    }

    @Override
    public void onStop() {
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
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int pm25 = jsonObject.getInt("illumination");
                    getyz(pm25);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void getyz(int pm25){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_threshold", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject  jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    int yz =jsonObject1.getInt("pm25");
                    setdata(pm25,yz);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void setdata(int pm25,int yz){
        if (list.size()==20){
            list.remove(0);
            colors.remove(0);
        }
        list.add(pm25);
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Entry(i,list.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries,"");
        dataSet.setDrawValues(false);
        dataSet.setColor(Color.GRAY);
        dataSet.setCircleSize(7f);
        dataSet.setDrawCircleHole(false);
        dataSet.setLineWidth(4f);
//        System.out.println("pm2.5:"+pm25);
//        System.out.println("阈值:"+yz);
       if (list.get(list.size()-1)>2000){
           colors.add(Color.RED);
           TongZhi("当前空气温度:",pm25);
       }else {
           colors.add(Color.GREEN);
       }
       dataSet.setCircleColors(colors);
        LineData data = new LineData(dataSet);
        mLcSshj291.setExtraOffsets(50,0,50,100);
        mLcSshj291.setData(data);
        mLcSshj291.invalidate();
        mLcSshj291.getLegend().setEnabled(false);
        mLcSshj291.getDescription().setEnabled(false);
        setXAxis();
        setYAxis();
    }


    //发送警告
    public void TongZhi(String leixing, int a) {
        //通知管理器
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //开辟一个通道
            NotificationChannel mChannel = new NotificationChannel(leixing, leixing, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            Notification.Builder builder = new Notification.Builder(getActivity());
            //设置属性
            builder.setSmallIcon(R.mipmap.ic_launcher);
//            builder.setContentTitle("通知");
            builder.setContentText(leixing+a);
            //这个要和创建通道的ID一致
            builder.setChannelId(leixing);
            //创建对象,发送的就是这个对象
            Notification build = builder.build();
            notificationManager.notify(1, build);
        }

    }

    private void setXAxis(){

        if (a<=57){
            a=a+3;
            time.add(a+"");
        }
        XAxis xAxis = mLcSshj291.getXAxis();
        xAxis.setTextSize(30f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1);
        xAxis.setLabelCount(20);//设置显示数量 time.size()
        xAxis.setValueFormatter(new IndexAxisValueFormatter(time));
        xAxis.setAxisLineWidth(1f);
        xAxis.setDrawGridLines(false);
    }
    private void setYAxis(){
        mLcSshj291.getAxisRight().setEnabled(false);
        YAxis yAxis =mLcSshj291.getAxisLeft();
        yAxis.setAxisLineWidth(1f);
        yAxis.setTextSize(30f);
        yAxis.setDrawGridLines(false);
    }


    private void initView() {
        mLcSshj291 = (LineChart) getActivity().findViewById(R.id.lc_sshj_29_3);
    }
}
