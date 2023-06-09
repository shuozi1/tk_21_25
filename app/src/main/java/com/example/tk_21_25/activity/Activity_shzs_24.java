package com.example.tk_21_25.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.SHZS_24;
import com.example.tk_21_25.util.H;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_shzs_24 extends AppCompatActivity {
    private TextView mTvShzsWd;
    private TextView mTvShzsTodaywd;
    private ImageView mIgShzsFinish;
    private LineChart mLcShzs;
    private int today;
    private String[] weeks;
    private List<SHZS_24> shzs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shzs_24);
        initView();
        setfinsh();
    }
    private void setfinsh(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_weather_info", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    String wd = jsonObject.getString("temperature");
                    shzs = new Gson().fromJson(jsonArray.toString(),new TypeToken<List<SHZS_24>>(){}.getType());
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("EEEE");
                    String time =format.format(new Date());
                    System.out.println(time+"");
                    timeAA(time);
                    runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            mTvShzsWd.setText(wd+"°");
                            mTvShzsTodaywd.setText("今天："+jsonArray.optJSONObject(1).optString("interval")+"℃");
                            setDate();
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void timeAA(String time) {
        System.out.println(time+"当前时间当前星期");
        switch (time){
            case "星期一":
                weeks = new String[]{"昨天","今天","明天","周三","周四","周五"};
                break;
            case "星期二":
                  weeks=new String[]{"昨天","今天","明天","周四","周五","周六"};
                break;
            case "星期三":
                  weeks=new String[]{"昨天","今天","明天","周五","周六","周天"};
                break;
            case "星期四":
                weeks=new String[]{"昨天","今天","明天","周六","周天","周一"};
                break;
            case "星期五":
                weeks=new String[]{"昨天","今天","明天","周天","周一","周二"};
                break;
            case "星期六":
                weeks=new String[]{"昨天","今天","明天","周一","周二","周三"};
                break;
            case "星期天":
                weeks=new String[]{"昨天","今天","明天","周二","周三","周四"};
                break;
        }
    }
    private void setDate() {
        List<Entry> maxEntry = new ArrayList<>();
        List<Entry> minEntry = new ArrayList<>();

        for (int i = 0; i < shzs.size(); i++) {
            String[] arr = shzs.get(i).getInterval().split("~");
            maxEntry.add(new Entry(i, Integer.parseInt(arr[1])));
            minEntry.add(new Entry(i, Integer.parseInt(arr[0])));
        }
        LineDataSet dataSet = new LineDataSet(maxEntry, "");
        LineDataSet dataSet1 = new LineDataSet(minEntry, "");

        dataSet.setValueTextSize(15);
        dataSet.setLineWidth(3);
        dataSet.setCircleColor(Color.RED);
        dataSet.setCircleSize(5);
        dataSet.setDrawCircleHole(false);
        dataSet.setColor(Color.RED);

        dataSet1.setValueTextSize(15);
        dataSet1.setLineWidth(3);
        dataSet1.setCircleColor(Color.BLUE);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setColor(Color.BLUE);


        //设置图例和标题为不可见
        mLcShzs.getLegend().setEnabled(false);
        //设置右下角小字不可见
        mLcShzs.getDescription().setEnabled(false);
        //设置右为不可见
        mLcShzs.getAxisRight().setEnabled(false);


        LineData lineData = new LineData(dataSet, dataSet1);


        mLcShzs.setData(lineData);
        mLcShzs.invalidate();
        //设置边距
        mLcShzs.setExtraTopOffset(30);
        mLcShzs.setExtraLeftOffset(30);
        mLcShzs.setExtraRightOffset(30);
        setXAxis();
        setYAxis();

    }

    private void setXAxis() {
        XAxis xAxis = mLcShzs.getXAxis();
        xAxis.setTextSize(80f);
        xAxis.setTextColor(Color.BLUE);
        xAxis.setLabelCount(weeks.length);
        //设置间距 为1(不设置会乱)
        xAxis.setGranularity(1);
        //设置x轴的显示
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weeks));
        //隐藏全部线
        xAxis.setDrawGridLines(false);

    }

    private void setYAxis() {
        YAxis yAxis = mLcShzs.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        //y轴线条宽度
        yAxis.setGridLineWidth(1.5f);
        yAxis.setDrawLabels(false);
    }

    private void initView() {
        mTvShzsWd = (TextView) findViewById(R.id.tv_shzs_wd);
        mTvShzsTodaywd = (TextView) findViewById(R.id.tv_shzs_todaywd);
        mIgShzsFinish = (ImageView) findViewById(R.id.ig_shzs_finish);
        mLcShzs = (LineChart) findViewById(R.id.lc_shzs);
        mIgShzsFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setfinsh();
            }
        });
    }

}
