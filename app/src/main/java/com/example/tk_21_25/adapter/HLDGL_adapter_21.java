package com.example.tk_21_25.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.HLDGL_21;

import java.util.List;

public class HLDGL_adapter_21 extends ArrayAdapter<HLDGL_21> {
    public HLDGL_adapter_21(@NonNull Context context, List<HLDGL_21> resource) {
        super(context, 0,resource);
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.hldgl_21_adapter,parent,false);
            holder.lk=convertView.findViewById(R.id.tv_hldgl_adapter_lk);
            holder.red=convertView.findViewById(R.id.tv_hldgl_adapter_red);
            holder.yellow=convertView.findViewById(R.id.tv_hldgl_adapter_yellow);
            holder.green=convertView.findViewById(R.id.tv_hldgl_adapter_green);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        HLDGL_21 h =getItem(position);

        holder.lk.setText(h.getNumber()+"");
        holder.red.setText(h.getRed()+"");
        holder.yellow.setText(h.getYellow()+"");
        holder.green.setText(h.getGreen()+"");
        return convertView;
    }
    private class ViewHolder{
        TextView lk;
        TextView red;
        TextView yellow;
        TextView green;
    }
}
