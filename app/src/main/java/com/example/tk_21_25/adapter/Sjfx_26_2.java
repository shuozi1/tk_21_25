package com.example.tk_21_25.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.SJFX_26;
import com.example.tk_21_25.util.H;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Sjfx_26_2 extends Fragment {
    private HorizontalBarChart mPcSjfx261;
    private  int one;
    private  int two;
    private  int three;
    private List<Map.Entry<String,Integer>> typeList;
    private Map<String,Integer> mapChePai;
    private List<SJFX_26> listAll;
    private List<SJFX_26> yes;
    private List<SJFX_26> no;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sjfx_26_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        geta();
    }
    private void geta(){
        JSONObject jsonObject  =new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_peccancy", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    listAll = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(),new TypeToken<List<SJFX_26>>(){}.getType());
                    yes = new ArrayList<>();
                    no = new ArrayList<>();
                    for (int i = 0; i < listAll.size(); i++) {
                        if (!listAll.get(i).getPaddr().equals("")){
                            yes.add(listAll.get(i));
                        }else {
                            no.add(listAll.get(i));
                        }
                    }
                    seta();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void seta(){

        mapChePai = new HashMap<>();
        for (int i = 0; i < yes.size(); i++) {
            String id = yes.get(i).getCarnumber();
            Integer count1=mapChePai.get(id);
            mapChePai.put(id,(count1==null)? 1 : count1 + 1 );
        }

        typeList = new ArrayList<>(mapChePai.entrySet());
        one=0;
        two=0;
        three=0;
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getValue()<=2){
                one++;
            } else if (typeList.get(i).getValue()<=5) {
                two++;
            } else  {
                three++;
            }
        }
        Float zong = Float.valueOf(one+two+three);
        Float o1 =one/zong;
        Float o2 =two/zong;
        Float o3 =three/zong;
        Float[] a = new Float[]{o1,o2,o3};
        mPcSjfx261.getLegend().setEnabled(false);
        mPcSjfx261.getDescription().setEnabled(false);
        mPcSjfx261.invalidate();
        mPcSjfx261.setExtraBottomOffset(33f);
        mPcSjfx261.setExtraRightOffset(20f);
        mPcSjfx261.setExtraLeftOffset(90f);
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            entries.add(new BarEntry(i,(a[i])*100));
        }
        BarDataSet dataSet = new BarDataSet(entries,"");
        dataSet.setValueTextSize(26f);
        dataSet.setValueTextColor(Color.RED);
        dataSet.setColors(Color.GREEN,Color.BLUE,Color.RED);
        dataSet.setValueFormatter(new ValueFormatter() {
            @SuppressLint("DefaultLocale")
            @Override
            public String getFormattedValue(float value) {
                return String.format("%.2f",value)+"%";
            }
        });
        BarData data = new BarData(dataSet);
        mPcSjfx261.setData(data);
        setXAxis();
        setYAxis();
    }
    private void setXAxis(){
        XAxis xAxis =mPcSjfx261.getXAxis();
        xAxis.setTextSize(20f);
        xAxis.setAxisMaximum(3f);
        xAxis.setAxisMinimum(-0.6f);
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        final String[] ss ={"五条以上违章","3-5条违章","1-2条违章"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ss));
    }
    private void setYAxis(){
        YAxis left =mPcSjfx261.getAxisLeft();
        left.setEnabled(false);

        YAxis yAxis = mPcSjfx261.getAxisRight();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setTextSize(30f);

//        yAxis.setGridLineWidth(2f);
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value+"%";
            }
        });
    }


    private void initView() {
        mPcSjfx261 = (HorizontalBarChart) getActivity().findViewById(R.id.pc_sjfx_26_2);
    }
}
