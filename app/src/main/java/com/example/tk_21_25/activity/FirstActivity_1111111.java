package com.example.tk_21_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;

public class FirstActivity_1111111 extends AppCompatActivity implements View.OnClickListener {
    private TextView mFirst21;
    private TextView mFirst22;
    private TextView mFirst23;
    private TextView mFirst24;
    private TextView mFirst25;
    private TextView mFirst26;
    private TextView mFirst27;
    private TextView mFirst28;
    private TextView mFirst29;
    private TextView mFirst30;
    private TextView mFirst31;
    private TextView mFirst32;
    private TextView mFirst33;
    private TextView mFirst34;
    private TextView mFirst35;
    private TextView mFirst36;
    private TextView mFirst37;
    private TextView mFirst38;
    private TextView mFirst39;
    private TextView mFirst40;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_1111111);
        initView();
    }

    private void initView() {
        mFirst21 = (TextView) findViewById(R.id.first_21);
        mFirst22 = (TextView) findViewById(R.id.first_22);
        mFirst23 = (TextView) findViewById(R.id.first_23);
        mFirst24 = (TextView) findViewById(R.id.first_24);
        mFirst25 = (TextView) findViewById(R.id.first_25);
        mFirst26 = (TextView) findViewById(R.id.first_26);
        mFirst27 = (TextView) findViewById(R.id.first_27);
        mFirst28 = (TextView) findViewById(R.id.first_28);
        mFirst29 = (TextView) findViewById(R.id.first_29);
        mFirst30 = (TextView) findViewById(R.id.first_30);

        mFirst21.setOnClickListener(this);
        mFirst22.setOnClickListener(this);
        mFirst23.setOnClickListener(this);
        mFirst24.setOnClickListener(this);
        mFirst25.setOnClickListener(this);
        mFirst26.setOnClickListener(this);
        mFirst27.setOnClickListener(this);
        mFirst28.setOnClickListener(this);
        mFirst29.setOnClickListener(this);
        mFirst30.setOnClickListener(this);
        mFirst31 = (TextView) findViewById(R.id.first_31);
        mFirst32 = (TextView) findViewById(R.id.first_32);
        mFirst33 = (TextView) findViewById(R.id.first_33);
        mFirst34 = (TextView) findViewById(R.id.first_34);
        mFirst35 = (TextView) findViewById(R.id.first_35);
        mFirst36 = (TextView) findViewById(R.id.first_36);
        mFirst37 = (TextView) findViewById(R.id.first_37);
        mFirst38 = (TextView) findViewById(R.id.first_38);
        mFirst39 = (TextView) findViewById(R.id.first_39);
        mFirst40 = (TextView) findViewById(R.id.first_40);
        mFirst31.setOnClickListener(this);
        mFirst32.setOnClickListener(this);
        mFirst33.setOnClickListener(this);
        mFirst34.setOnClickListener(this);
        mFirst35.setOnClickListener(this);
        mFirst36.setOnClickListener(this);
        mFirst37.setOnClickListener(this);
        mFirst38.setOnClickListener(this);
        mFirst39.setOnClickListener(this);
        mFirst40.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.first_21:
                intent.setClass(this, Activity_hldgl_21.class);
                break;
            case R.id.first_22:
                intent.setClass(this, Activity_zhgl_22.class);
                break;
            case R.id.first_23:
                intent.setClass(this, Activity_zhsz_23.class);
                break;
            case R.id.first_24:
                intent.setClass(this, Activity_shzs_24.class);
                break;
            case R.id.first_25:
                intent.setClass(this, Activity_lkcx_25.class);
                break;
            case R.id.first_26:
                intent.setClass(this, Activity_sjfx_26.class);
                break;
            case R.id.first_27:
                intent.setClass(this, Activity_shzs_27.class);
                break;
            case R.id.first_28:
                intent.setClass(this, Activity_gjcx_28.class);
                break;
            case R.id.first_29:
                intent.setClass(this, Activity_sshj_29.class);
                break;
            case R.id.first_30:
                intent.setClass(this, Activity_clwz_30.class);
                break;
            case R.id.first_31:
                intent.setClass(this,Activity_yjfk_31.class);
                break;
            case R.id.first_37:
                intent.setClass(this,Activity_ewm_37.class);
                break;
        }
        startActivity(intent);
    }
}
