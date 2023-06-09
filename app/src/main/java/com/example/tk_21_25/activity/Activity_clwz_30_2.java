package com.example.tk_21_25.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;

public class Activity_clwz_30_2 extends AppCompatActivity {
    private VideoView mVvClwz301;
    String uri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clwz_30_1);
        initView();
        int a = getIntent().getIntExtra("info",1);
        switch (a){
            case 1:
                uri="android.resource://"+getPackageName()+"/"+R.raw.car1;break;
            case 2:
                uri="android.resource://"+getPackageName()+"/"+R.raw.car2;break;
            case 3:
                uri="android.resource://"+getPackageName()+"/"+R.raw.car3;break;
            case 4:
                uri="android.resource://"+getPackageName()+"/"+R.raw.car4;break;
            case 5:
                uri="android.resource://"+getPackageName()+"/"+R.raw.car1;break;
        }
        mVvClwz301.setVideoURI(Uri.parse(uri));
        mVvClwz301.start();
    }

    private void initView() {
        mVvClwz301 = (VideoView) findViewById(R.id.vv_clwz_30_1);
    }
}
