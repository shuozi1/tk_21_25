package com.example.tk_21_25.activity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.tk_21_25.R;
import com.example.tk_21_25.adapter.FragmentAdapter;
import com.example.tk_21_25.adapter.Sshj_29_1;
import com.example.tk_21_25.adapter.Sshj_29_2;
import com.example.tk_21_25.adapter.Sshj_29_3;

import java.util.ArrayList;
import java.util.List;


public class Activity_sshj_29 extends AppCompatActivity {
    private ViewPager mVpSshj29;
    private RadioGroup mRgSshj29;
    private RadioButton mRbSshj291;
    private RadioButton mRbSshj292;
    private RadioButton mRbSshj293;
    private Sshj_29_1 sshj_29_1;
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private Sshj_29_2 sshj_29_2;
    private Sshj_29_3 sshj_29_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sshj_29);
        initView();
        sshj_29_1 = new Sshj_29_1();
        sshj_29_2 = new Sshj_29_2();
        sshj_29_3 = new Sshj_29_3();
        fragments.add(sshj_29_1);
        fragments.add(sshj_29_2);
        fragments.add(sshj_29_3);
        fragmentAdapter= new FragmentAdapter(this.getSupportFragmentManager(),fragments);
        mVpSshj29.setCurrentItem(3);
        mVpSshj29.setAdapter(fragmentAdapter);
        setvp(0);
        mVpSshj29.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
setvp(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setvp(int a){
        switch (a){
            case 0:mRbSshj291.setChecked(true);break;
            case 1:mRbSshj292.setChecked(true);break;
            case 2:mRbSshj293.setChecked(true);break;
        }
    }


    private void initView() {
        mVpSshj29 = (ViewPager) findViewById(R.id.vp_sshj_29);
        mRgSshj29 = (RadioGroup) findViewById(R.id.rg_sshj_29);
        mRbSshj291 = (RadioButton) findViewById(R.id.rb_sshj_29_1);
        mRbSshj292 = (RadioButton) findViewById(R.id.rb_sshj_29_2);
        mRbSshj293 = (RadioButton) findViewById(R.id.rb_sshj_29_3);
        mRgSshj29.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_sshj_29_1:
                        mVpSshj29.setCurrentItem(0);break;
                    case R.id.rb_sshj_29_2:
                        mVpSshj29.setCurrentItem(1);break;
                    case R.id.rb_sshj_29_3:
                        mVpSshj29.setCurrentItem(2);break;

                }
            }
        });
    }
}
