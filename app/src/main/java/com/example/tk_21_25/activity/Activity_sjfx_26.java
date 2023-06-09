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
import com.example.tk_21_25.adapter.Sjfx_26_1;
import com.example.tk_21_25.adapter.Sjfx_26_2;

import java.util.ArrayList;
import java.util.List;

public class Activity_sjfx_26 extends AppCompatActivity {
    private ViewPager mVpSjfx26;
    private RadioGroup mRgSjfx26;
    private RadioButton mRbSjfx261;
    private RadioButton mRbSjfx262;
    private Sjfx_26_1 sjfx_26_1;
    FragmentAdapter fragmentAdapter;
    List<Fragment> fragments = new ArrayList<>();
    private Sjfx_26_2 sjfx_26_2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sjfx_26);
        initView();
        sjfx_26_1 = new Sjfx_26_1();
        sjfx_26_2 = new Sjfx_26_2();
        fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),fragments);
        fragments.add(sjfx_26_1);
        fragments.add(sjfx_26_2);
        mVpSjfx26.setCurrentItem(2);
        mVpSjfx26.setAdapter(fragmentAdapter);
        setvp(0);
        mRbSjfx261.setChecked(true);
        mVpSjfx26.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            case 0:mRbSjfx261.setChecked(true);break;
            case 1:mRbSjfx262.setChecked(true);break;
        }
    }

    private void initView() {
        mVpSjfx26 = (ViewPager) findViewById(R.id.vp_sjfx_26);
        mRgSjfx26 = (RadioGroup) findViewById(R.id.rg_sjfx_26);
        mRbSjfx261 = (RadioButton) findViewById(R.id.rb_sjfx_26_1);
        mRbSjfx262 = (RadioButton) findViewById(R.id.rb_sjfx_26_2);
        mRgSjfx26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_sjfx_26_1:
                        mVpSjfx26.setCurrentItem(0);
                        break;
                    case R.id.rb_sjfx_26_2:
                        mVpSjfx26.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}

