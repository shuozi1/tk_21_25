package com.example.tk_21_25.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;

public class Activity_clwz_30 extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIgClwz30Ig1;
    private ImageView mIgClwz30Ig2;
    private ImageView mIgClwz30Ig3;
    private ImageView mIgClwz30Ig4;
    private ImageView mIgClwz30Ig5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clwz_30);
        initView();

    }

    private void initView() {
        mIgClwz30Ig1 = (ImageView) findViewById(R.id.ig_clwz_30_ig1);
        mIgClwz30Ig2 = (ImageView) findViewById(R.id.ig_clwz_30_ig2);
        mIgClwz30Ig3 = (ImageView) findViewById(R.id.ig_clwz_30_ig3);
        mIgClwz30Ig4 = (ImageView) findViewById(R.id.ig_clwz_30_ig4);
        mIgClwz30Ig5 = (ImageView) findViewById(R.id.ig_clwz_30_ig5);
        mIgClwz30Ig1.setOnClickListener(this);
        mIgClwz30Ig2.setOnClickListener(this);
        mIgClwz30Ig3.setOnClickListener(this);
        mIgClwz30Ig4.setOnClickListener(this);
        mIgClwz30Ig5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this,Activity_clwz_30_2.class);
        switch (view.getId()){
            case R.id.ig_clwz_30_ig1:
                intent.putExtra("info",1);
                break;
            case R.id.ig_clwz_30_ig2:
                intent.putExtra("info",2);
                break;
            case R.id.ig_clwz_30_ig3:
                intent.putExtra("info",3);
                break;
            case R.id.ig_clwz_30_ig4:
                intent.putExtra("info",4);
                break;
            case R.id.ig_clwz_30_ig5:
                intent.putExtra("info",5);
                break;

        }
        startActivity(intent);
    }
}
