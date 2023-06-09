package com.example.tk_21_25.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.adapter.ZHGL_adapter_22;
import com.example.tk_21_25.bean.ZHGL_22;
import com.example.tk_21_25.util.H;
import com.example.tk_21_25.util.Myclick;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_zhgl_22 extends AppCompatActivity {
    private TextView mTvZhglPlcz;
    private TextView mTvZhglCzjl;
    private ListView mListviewZhgl;
    ZHGL_adapter_22 adapter;
    List<ZHGL_22> zhgl;
    private TextView cznum;
    List<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhgl_22);
        initView();
        seta();
    }
    private void seta(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_vehicle", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject =new JSONObject(s);
                    zhgl = new Gson().fromJson(jsonObject.getJSONArray("ROWS_DETAIL").toString(),new TypeToken<List<ZHGL_22>>(){}.getType());
                    adapter=new ZHGL_adapter_22(Activity_zhgl_22.this,zhgl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListviewZhgl.setAdapter(adapter);
                            adapter.setMyclick(new Myclick() {
                                @Override
                                public void onClick(int num) {
                                    clickBtn(num);
                                }
                                @Override
                                public void onClicks(int num) {
                                    for (int i = 0; i < list.size(); i++) {
                                        if (num==list.get(i)){
                                            list.remove(i);
                                            return;
                                        }
                                    }
                                    list.add(num);
                                }
                            });
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void clickBtn(int num){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        View v=inflater.inflate(R.layout.zhgl_dialog,null);
        builder.setView(v);
        TextView chenum =v.findViewById(R.id.tv_zhgl_dialog_chenum);
        cznum = v.findViewById(R.id.tv_zhgl_dialog_cznum);
        TextView btncz =v.findViewById(R.id.btn_zhgl_dialog_cz);
        TextView btnqx =v.findViewById(R.id.btn_zhgl_dialog_qx);
        chenum.setText(num+"");
        AlertDialog dialog = builder.create();
        dialog.show();
        btncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean c =czz(num);
                if (c){
                    Toast.makeText(Activity_zhgl_22.this, "充值成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_zhgl_22.this, "充值失败", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void clickCb(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater =getLayoutInflater();
        View v=inflater.inflate(R.layout.zhgl_dialog,null);
        builder.setView(v);
        TextView chenum =v.findViewById(R.id.tv_zhgl_dialog_chenum);
        cznum = v.findViewById(R.id.tv_zhgl_dialog_cznum);
        TextView btncz =v.findViewById(R.id.btn_zhgl_dialog_cz);
        TextView btnqx =v.findViewById(R.id.btn_zhgl_dialog_qx);
        chenum.setText(list+"");
        AlertDialog dialog = builder.create();
        dialog.show();
        btncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    boolean c =czz(list.get(i));
                    if (c){
                        Toast.makeText(Activity_zhgl_22.this, "充值成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(Activity_zhgl_22.this, "充值失败", Toast.LENGTH_SHORT).show();
                    }
                }
                list.clear();
            }
        });
        btnqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private boolean czz(int num){
        String s =zhgl.get(num-1).getPlate();
        boolean c =cz(s, Integer.parseInt(cznum.getText().toString()));
        if (c){
            return true;
        }else {
            return false;
        }
    }
    private boolean cz(String plate,int money){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("plate",plate);
            jsonObject.put("balance",money);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("set_balance", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                seta();
            }
        });
        return true;
    }


    private void initView() {
        mTvZhglPlcz = (TextView) findViewById(R.id.tv_zhgl_plcz);
        mTvZhglCzjl = (TextView) findViewById(R.id.tv_zhgl_czjl);
        mListviewZhgl = (ListView) findViewById(R.id.listview_zhgl);
        mTvZhglPlcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size()<1){
                    Toast.makeText(Activity_zhgl_22.this, "请至少选择一辆车", Toast.LENGTH_SHORT).show();
                    return;
                }
                clickCb();
            }
        });
    }
}
