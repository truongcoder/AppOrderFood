package com.example.pctruong.apporderfood.Adapter_Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pctruong.apporderfood.DTO.LoaiMonAnDTO;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 2/6/2017.
 */

public class AdapterHienThiLoaiMonAn extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<LoaiMonAnDTO>arrayList;

    public AdapterHienThiLoaiMonAn(Context context, int layout, ArrayList<LoaiMonAnDTO> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_spinner,parent,false);
        TextView txt= (TextView) view.findViewById(R.id.sptxttenloai);
        txt.setText(arrayList.get(position).getTenLoai());
        txt.setTag(arrayList.get(position).getMaLoai());
        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_spinner,parent,false);
        TextView txt= (TextView) view.findViewById(R.id.sptxttenloai);
        txt.setText(arrayList.get(position).getTenLoai());
        txt.setTag(arrayList.get(position).getMaLoai());
        return view;
    }
}
