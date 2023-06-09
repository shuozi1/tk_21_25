package com.example.tk_21_25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.ZHGL_22;
import com.example.tk_21_25.util.Myclick;

import org.w3c.dom.Text;

import java.util.List;

public class ZHGL_adapter_22 extends ArrayAdapter<ZHGL_22> {
    public ZHGL_adapter_22(@NonNull Context context, List<ZHGL_22> resource) {
        super(context, 0,resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.zhgl_22_adapter,parent,false);
            holder.num=convertView.findViewById(R.id.tv_zhgl_adapter_num);
            holder.yue=convertView.findViewById(R.id.tv_zhgl_adapter_money);
            holder.cb=convertView.findViewById(R.id.cb_zhgl_adapter);
            holder.btn=convertView.findViewById(R.id.btn_zhgl_adapter);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ZHGL_22 z = getItem(position);
        holder.num.setText(z.getNumber()+"");
        holder.yue.setText(z.getBalance()+"");
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myclick.onClick(z.getNumber());
            }
        });
        holder.cb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                myclick.onClicks(z.getNumber());
            }
        });
        return convertView;
    }

    public Myclick getMyclick() {
        return myclick;
    }

    public void setMyclick(Myclick myclick) {
        this.myclick = myclick;
    }

    Myclick myclick;
    class ViewHolder{
        TextView num;
        TextView yue;
        CheckBox cb;
        Button btn;
    }
}
