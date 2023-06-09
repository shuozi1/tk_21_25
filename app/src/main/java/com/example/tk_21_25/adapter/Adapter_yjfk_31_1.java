package com.example.tk_21_25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.YJFK_31;

import java.util.List;

import kotlin.LateinitKt;

public class Adapter_yjfk_31_1 extends ArrayAdapter<YJFK_31> {
    public Adapter_yjfk_31_1(@NonNull Context context, List<YJFK_31> resource) {
        super(context, 0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wdyj_31_1,null);
            holder.bt = convertView.findViewById(R.id.tv_wdyj_31_1_adapter_bt);
            holder.time=convertView.findViewById(R.id.tv_wdyj_31_1_adapter_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        YJFK_31 yjfk = getItem(position);
        holder.bt.setText("标题："+yjfk.getBt());
        holder.time.setText("提交时间："+yjfk.getTime());
        return convertView;
    }
    private class ViewHolder{
        TextView bt;
        TextView time;
    }
}
