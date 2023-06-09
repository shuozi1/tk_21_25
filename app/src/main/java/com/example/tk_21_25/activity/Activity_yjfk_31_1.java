package com.example.tk_21_25.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tk_21_25.R;
import com.example.tk_21_25.adapter.Adapter_yjfk_31_1;
import com.example.tk_21_25.bean.YJFK_31;

import java.util.List;

public class Activity_yjfk_31_1 extends AppCompatActivity {
    private ListView mListviewYjfk311;
    List<YJFK_31> lists;
    Adapter_yjfk_31_1 adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk_31_1);
        initView();
        lists = Activity_yjfk_31.list;
        adapter = new Adapter_yjfk_31_1(this,lists);
        mListviewYjfk311.setAdapter(adapter);
    }

    private void initView() {
        mListviewYjfk311 = (ListView) findViewById(R.id.listview_yjfk_31_1);
    }
}
