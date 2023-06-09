package com.example.tk_21_25.activity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.adapter.Exadapter_gjcx_28;
import com.example.tk_21_25.bean.GJCX_28;
import com.example.tk_21_25.util.H;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_gjcx_28 extends AppCompatActivity {
    private ExpandableListView mExlistviewGjcx28;
    Exadapter_gjcx_28 adapter;
    Map<String,List<GJCX_28>> map;
    private Timer timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gjcx_28);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getA();
            }
        },0,3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void getA(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_bus_stop_distance", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s =response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    List<GJCX_28> list1 = new Gson().fromJson(jsonObject.getJSONArray("中医院站").toString(),new TypeToken<List<GJCX_28>>(){}.getType());
                    list1.sort(new Comparator<GJCX_28>() {
                        @Override
                        public int compare(GJCX_28 t0, GJCX_28 t1) {
                            return t0.getDistance()-t1.getDistance();
                        }
                    });
                    map = new HashMap<>();
                    map.put("一号站台",list1);

                    List<GJCX_28> list2 = new Gson().fromJson(jsonObject.getJSONArray("联想大厦站").toString(),new TypeToken<List<GJCX_28>>(){}.getType());
                    list2.sort(new Comparator<GJCX_28>() {
                        @Override
                        public int compare(GJCX_28 t0, GJCX_28 t1) {
                            return t0.getDistance()-t1.getDistance();
                        }
                    });
                    map.put("二号站台",list2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                adapter = new Exadapter_gjcx_28(map);
                                mExlistviewGjcx28.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void initView(){
        mExlistviewGjcx28 = (ExpandableListView) findViewById(R.id.exlistview_gjcx_28);
    }
}
