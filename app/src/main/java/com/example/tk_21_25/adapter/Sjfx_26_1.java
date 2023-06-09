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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

public class Sjfx_26_1 extends Fragment {
    private PieChart mPcSjfx261;
    private List<SJFX_26> listAll;
    private float nowzNum;
    private float wzNum;
    private float zongNum;
    private List<SJFX_26> yes;
    private List<SJFX_26> no;
    private Map<String,Integer> mapChePai;
    private List<Map.Entry<String,Integer>> typeList;
    public static int two;
    public static int one;
    public static int three;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sjfx_26_1, container, false);
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
                    getb();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void getb(){
        System.out.println("总："+listAll.size());
        System.out.println("有违章"+yes.size());
        System.out.println("没有违章"+no.size());

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
        wzNum=0;
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getValue()!=1){
                wzNum+=1;
            }
            if (typeList.get(i).getValue()<=2){
                one++;
            } else if (typeList.get(i).getValue()<=5) {
                two++;
            } else  {
                three++;
            }
        }
        System.out.println("重复违章数："+wzNum);
        System.out.println("1-2："+one);
        System.out.println("3-5："+two);
        System.out.println("5以上："+three);

        List<PieEntry> pieEntries = new ArrayList<>();
        nowzNum= typeList.size()-wzNum;
        pieEntries.add(new PieEntry((wzNum/typeList.size())*100,"有重复"));
        pieEntries.add(new PieEntry((nowzNum/typeList.size())*100,"无重复违章"));

        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        //设置图饼颜色
        dataSet.setColors(0xff4572a7,0xffaa4643);
        //设置图饼上占比数显示在外部
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //不显示图饼里的文字
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //设置图饼上文字颜色
        dataSet.setValueTextColor(Color.BLACK);

        final String[] s =new String[]{"有重复违章","无重复违章"};
        dataSet.setValueFormatter(new ValueFormatter() {
            private int a = 0;
            @SuppressLint("DefaultLocale")
            @Override
            public String getPieLabel(float value, PieEntry pieEntry) {
                a++;
                if (a>=s.length){
                    a=0;
                }
                return s[a]+String.format("%.1f",value)+"%";
            }
        });

        //设置线上文字大小
        dataSet.setValueTextSize(35f);
        dataSet.setValueLinePart1Length(.8f);
        dataSet.setValueLinePart2Length(.8f);
        //设置两饼之间距离
        dataSet.setSliceSpace(10f);

        PieData data = new PieData(dataSet);
        mPcSjfx261.setData(data);

        mPcSjfx261.getLegend().setEnabled(false);
        mPcSjfx261.getDescription().setEnabled(false);
        mPcSjfx261.invalidate();
        //设置初始旋转角度
        mPcSjfx261.setRotationAngle(10f);
        //设置是否绘制空心圆
        mPcSjfx261.setDrawHoleEnabled(false);
        //设置不可进行旋转
        mPcSjfx261.setRotationEnabled(false);

    }


    private void initView() {
        mPcSjfx261 = (PieChart) getActivity().findViewById(R.id.pc_sjfx_26_1);
    }
}
