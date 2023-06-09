package com.example.tk_21_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;

public class Activity_ewm_37 extends AppCompatActivity {
    private ImageView mIgEwmzf37Sx;
    private Spinner mSpEwmzf37Sp;
    private EditText mEtEwmzf37Money;
    private EditText mEtEwmzf37Timenum;
    private Button mBtnEwmzf37Sc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewmzf_37);
        initView();
    }

    private void initView() {
        mIgEwmzf37Sx = (ImageView) findViewById(R.id.ig_ewmzf_37_sx);
        mSpEwmzf37Sp = (Spinner) findViewById(R.id.sp_ewmzf_37_sp);
        mEtEwmzf37Money = (EditText) findViewById(R.id.et_ewmzf_37_money);
        mEtEwmzf37Timenum = (EditText) findViewById(R.id.et_ewmzf_37_timenum);
        mBtnEwmzf37Sc = (Button) findViewById(R.id.btn_ewmzf_37_sc);
        mBtnEwmzf37Sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtEwmzf37Money.getText().toString().equals("")){
                    Toast.makeText(Activity_ewm_37.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mEtEwmzf37Timenum.getText().toString().equals("")){
                    Toast.makeText(Activity_ewm_37.this, "刷新时间不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("bh",mSpEwmzf37Sp.getSelectedItem().toString());
                intent.putExtra("je",mEtEwmzf37Money.getText().toString());
                intent.putExtra("time",mEtEwmzf37Timenum.getText().toString());
                intent.setClass(Activity_ewm_37.this,Activity_ewm_37_1.class);
                startActivity(intent);
            }
        });
    }
}
