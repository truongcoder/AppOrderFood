package com.example.pctruong.apporderfood.Adapter_Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pctruong.apporderfood.DTO.NhanVienDTO;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PVTruong on 01/04/2017.
 */

public class Adapter_NhanVien extends BaseAdapter{
    Context context;
    ArrayList<NhanVienDTO> listnhanvien;

    public Adapter_NhanVien(Context context, ArrayList<NhanVienDTO> listnhanvien) {
        this.context = context;
        this.listnhanvien = listnhanvien;
    }

    @Override
    public int getCount() {
        return listnhanvien.size();
    }

    @Override
    public Object getItem(int position) {
        return listnhanvien.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_nhanvien,parent,false);
        TextView txtten= (TextView) view.findViewById(R.id.txt_tennv);
        TextView txtmanv= (TextView) view.findViewById(R.id.txt_manv);
        txtmanv.setText("Mã NV :" +listnhanvien.get(position).getMANV());
        txtten.setText("Tên NV :" +listnhanvien.get(position).getTENDN());
        return view;
    }
}
