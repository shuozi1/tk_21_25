package com.example.tk_21_25.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;
import java.util.Random;

public class Activity_ewm_37_1 extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIgEwmzf371Sx;
    private TextView mTvEwmzf371Ewmxx;
    private ImageView mIgEwmzf371Ig1;
    private ImageView mIgEwmzf371Ig2;
    private String tsinfo;
    private LinearLayout mLlEwmzf371Linelayout;
    private boolean isLoop = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewmzf_37_1);
        initView();
        tsinfo = "车辆编号=" + getIntent().getStringExtra("bh") + ",付费金额=" + getIntent().getStringExtra("je") + "元";
        int looptime = Integer.parseInt(getIntent().getStringExtra("time"))*1000;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(looptime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Random random = new Random();
            drawImage(tsinfo + random.nextInt(100));
            return false;
        }
    });

    @Override
    protected void onStop() {
        super.onStop();
        isLoop=false;
    }

    private void initView() {
        mIgEwmzf371Sx = (ImageView) findViewById(R.id.ig_ewmzf_37_1_sx);
        mTvEwmzf371Ewmxx = (TextView) findViewById(R.id.tv_ewmzf_37_1_ewmxx);
        mIgEwmzf371Ig1 = (ImageView) findViewById(R.id.ig_ewmzf_37_1_ig1);
        mIgEwmzf371Ig2 = (ImageView) findViewById(R.id.ig_ewmzf_37_1_ig2);
        mLlEwmzf371Linelayout = (LinearLayout) findViewById(R.id.ll_ewmzf_37_1_linelayout);
        mIgEwmzf371Ig1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mTvEwmzf371Ewmxx.setText(tsinfo);
                return true;
            }
        });
        mIgEwmzf371Ig1.setOnClickListener(this);
        mIgEwmzf371Ig2.setOnClickListener(this);
    }
    private void drawImage(String uri) {
        Hashtable<EncodeHintType,String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET,"utf-8");
        try {
            //五个参数分别为    二维码扫描后提示的内容  QR码表示标准的二维码格式    宽     高
            BitMatrix bitMatrix = new QRCodeWriter().encode(uri,BarcodeFormat.QR_CODE,300,300,hashtable);
            int[] pix = new int[300*300];
            for (int i = 0; i < 300; i++) {
                for (int j = 0; j < 300; j++) {
                    if (bitMatrix.get(i,j)){
                        pix[j*300+i] =0xff000000;
                    }else {
                        pix[j*300+i]=0xffffffff;
                    }
                }
            }
            //生成宽高都为300的对象   最后一个参数为每个像素存储格式                 ARGB_8888为最精致
            Bitmap bitmap = Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
            //依次为：       像素组   偏移量  每行像素数   开始时的x和y坐标   宽        高
            bitmap.setPixels(pix,0,300,0,0,300,300);
            mIgEwmzf371Ig2.setImageBitmap(bitmap);
            mIgEwmzf371Ig1.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ig_ewmzf_37_1_ig1:
                mLlEwmzf371Linelayout.setVisibility(View.GONE);
                mIgEwmzf371Ig2.setVisibility(View.VISIBLE);
                break;
            case R.id.ig_ewmzf_37_1_ig2:
                mIgEwmzf371Ig2.setVisibility(View.GONE);
                mLlEwmzf371Linelayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}
