package com.example.tk_21_25.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.util.H;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Activity_zhsz_23 extends AppCompatActivity {
    private TextView mTvZhsz23Money;
    private EditText mEtZhsz23Money;
    private Button mBtnZhsz23Sz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhsz_23);
        initView();
        getyz();
    }
    private void getyz(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("get_car_threshold", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject  = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    int a =jsonObject1.getInt("threshold");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvZhsz23Money.setText("我的1-4号车账户余额告警阈值为"+a+"元");
                            System.out.println(a+"");
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private boolean setyz(){
        int a = Integer.parseInt(mEtZhsz23Money.getText().toString());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserName","user1");
            jsonObject.put("threshold",a);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }new H().SendResutil("set_car_threshold", jsonObject.toString(), "POST", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
        return true;
    }

    private void initView() {
        mTvZhsz23Money = (TextView) findViewById(R.id.tv_zhsz_23_money);
        mEtZhsz23Money = (EditText) findViewById(R.id.et_zhsz_23_money);
        mBtnZhsz23Sz = (Button) findViewById(R.id.btn_zhsz_23_sz);
        mBtnZhsz23Sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtZhsz23Money.getText().toString().length()<1){
                    Toast.makeText(Activity_zhsz_23.this, "请输入金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (setyz()){
                    Toast.makeText(Activity_zhsz_23.this, "设置成功", Toast.LENGTH_SHORT).show();
                    getyz();
                    mEtZhsz23Money.setText("");
                }else {
                    Toast.makeText(Activity_zhsz_23.this, "设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
