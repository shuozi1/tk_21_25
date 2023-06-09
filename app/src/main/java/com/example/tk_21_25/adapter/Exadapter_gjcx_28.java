package com.example.tk_21_25.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tk_21_25.R;
import com.example.tk_21_25.bean.GJCX_28;

import java.util.List;
import java.util.Map;

public class Exadapter_gjcx_28 extends BaseExpandableListAdapter {
    String[] arr = {"一号站台","二号站台"};
    String[] arr1 = {"一号公交车","二号公交车"};
    Map<String,List<GJCX_28>> map;

    public Exadapter_gjcx_28(Map<String, List<GJCX_28>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return arr.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return arr1.length;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        viewHloder hloder = new viewHloder();
        if (view==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gjcx_28_group,null);
            hloder.ig =view.findViewById(R.id.ig_gjcx_28_group);
            hloder.tvGroup = view.findViewById(R.id.tv_gjcx_28_group);
            view.setTag(hloder);
        }else{
            hloder = (viewHloder) view.getTag();
        }
        if (b){
            hloder.ig.setBackgroundResource(R.drawable.jiantou1);
        }else {
            hloder.ig.setBackgroundResource(R.drawable.jiantou2);
        }
        hloder.tvGroup.setText(arr[i]);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        viewHloder hloder = new viewHloder();
        if (view==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gjcx_28_child,null);
            hloder.tvChild = view.findViewById(R.id.tv_gjcx_28_child);
            hloder.tv2Child = view.findViewById(R.id.tv2_gjcx_28_child);
            view.setTag(hloder);
        }else {
            hloder = (viewHloder) view.getTag();
        }
        GJCX_28 gjcxs = map.get(arr[i]).get(i1);

        hloder.tvChild.setText(gjcxs.getBus()+"号公交车");
        hloder.tv2Child.setText(gjcxs.getDistance()+"米");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class viewHloder{
        ImageView ig;
        TextView tvGroup;

        TextView tvChild;
        TextView tv2Child;
    }
}
