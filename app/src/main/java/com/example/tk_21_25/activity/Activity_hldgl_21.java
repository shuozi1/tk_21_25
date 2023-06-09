package com.example.tk_21_25.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.adapter.HLDGL_adapter_21;

import com.example.tk_21_25.bean.HLDGL_21;
import com.example.tk_21_25.util.H;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_hldgl_21 extends AppCompatActivity {
    private Spinner mSpHldgl21;
    private ListView mListviewHldgl21;
    ArrayAdapter<String> spadapter;
    List<HLDGL_21> hldgl = new ArrayList<>();
    HLDGL_adapter_21 adapter;
    private ImageView mIgHldgl21;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hldgl_21);
        initView();
        setsp();
        for (int i = 1; i < 6; i++) {
            seta(i);
        }
    }

    private void seta(int a) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName", "user1");
            jsonObject.put("number", a);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        new H().SendResutil("get_traffic_light", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s + "");
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    hldgl.add(new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").optJSONObject(0).toString(), HLDGL_21.class));
                    if (hldgl.size() == 5) {
                        PaiXu();
                        adapter = new HLDGL_adapter_21(Activity_hldgl_21.this, hldgl);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListviewHldgl21.setAdapter(adapter);
                            }
                        });
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void PaiXu() {
        int a = mSpHldgl21.getSelectedItemPosition();
        hldgl.sort(new Comparator<HLDGL_21>() {
            @Override
            public int compare(HLDGL_21 t1, HLDGL_21 t2) {
                switch (a) {
                    case 1:
                        return t2.getNumber() - t1.getNumber();
                    case 2:
                        return t1.getRed() - t2.getRed();
                    case 3:
                        return t2.getRed() - t1.getRed();
                    case 4:
                        return t1.getGreen() - t2.getGreen();
                    case 5:
                        return t2.getGreen() - t1.getGreen();
                    case 6:
                        return t1.getYellow() - t2.getYellow();
                    case 7:
                        return t2.getYellow() - t1.getYellow();
                    default:
                        return t1.getNumber() - t2.getNumber();
                }
            }
        });
    }

    private void PaiXu1(int a) {
        hldgl.sort(new Comparator<HLDGL_21>() {
            @Override
            public int compare(HLDGL_21 t1, HLDGL_21 t2) {
                switch (a) {
                    case 1:
                        return t2.getNumber() - t1.getNumber();
                    case 2:
                        return t1.getRed() - t2.getRed();
                    case 3:
                        return t2.getRed() - t1.getRed();
                    case 4:
                        return t1.getGreen() - t2.getGreen();
                    case 5:
                        return t2.getGreen() - t1.getGreen();
                    case 6:
                        return t1.getYellow() - t2.getYellow();
                    case 7:
                        return t2.getYellow() - t1.getYellow();
                    default:
                        return t1.getNumber() - t2.getNumber();
                }
            }
        });
    }

    private void setsp() {
        spadapter = new ArrayAdapter<String>(this, R.layout.spinner_size, getResources().getStringArray(R.array.hldgl));
        spadapter.setDropDownViewResource(R.layout.spinner_size);
        mSpHldgl21.setAdapter(spadapter);
    }

    private void initView() {
        mSpHldgl21 = (Spinner) findViewById(R.id.sp_hldgl_21);
        mListviewHldgl21 = (ListView) findViewById(R.id.listview_hldgl_21);
        mSpHldgl21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PaiXu1(i);
                if (adapter!=null){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mIgHldgl21 = (ImageView) findViewById(R.id.ig_hldgl_21);
        AnimationDrawable dw;
        dw= (AnimationDrawable) getResources().getDrawable(R.drawable.hlddh);
        mIgHldgl21.setBackgroundDrawable(dw);
        dw.start();

    }
}
