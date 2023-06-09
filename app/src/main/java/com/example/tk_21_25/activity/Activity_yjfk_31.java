package com.example.tk_21_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.YJFK_31;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_yjfk_31 extends AppCompatActivity {
    private TextView mTvYjfk31Wdyj;
    private EditText mEtYjfk31Bt;
    private EditText mEtYjfk31Yj;
    private Button mBtnYjfk31Tj;
    private EditText mEtYjfk31Num;
    public static List<YJFK_31> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk_31);
        initView();
    }


    private void initView() {
        mTvYjfk31Wdyj = (TextView) findViewById(R.id.tv_yjfk_31_wdyj);
        mEtYjfk31Bt = (EditText) findViewById(R.id.et_yjfk_31_bt);
        mEtYjfk31Yj = (EditText) findViewById(R.id.et_yjfk_31_yj);
        mBtnYjfk31Tj = (Button) findViewById(R.id.btn_yjfk_31_tj);
        mBtnYjfk31Tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list ==null){
                    list = new ArrayList<>();
                }
                String bt = mEtYjfk31Bt.getText().toString();
                String yj = mEtYjfk31Yj.getText().toString();
                String sj = mEtYjfk31Num.getText().toString();
                YJFK_31 yjfk = new YJFK_31();
                yjfk.setBt(bt);
                yjfk.setNum(sj);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
                String date = format.format(new Date());
                yjfk.setTime(date);
                yjfk.setYj(yj);
                list.add(yjfk);
                Toast.makeText(Activity_yjfk_31.this, "意见提交成功", Toast.LENGTH_SHORT).show();
                mEtYjfk31Yj.setText("");
                mEtYjfk31Bt.setText("");
                mEtYjfk31Num.setText("");
            }
        });
        mEtYjfk31Num = (EditText) findViewById(R.id.et_yjfk_31_num);
        mTvYjfk31Wdyj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size()<=0){
                    Toast.makeText(Activity_yjfk_31.this, "当前没有提交意见哦", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(Activity_yjfk_31.this,Activity_yjfk_31_1.class);
                startActivity(intent);
            }
        });
    }
}
