package com.example.pctruong.apporderfood.Adapter_Custom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pctruong.apporderfood.DTO.MonAnDTO;
import com.example.pctruong.apporderfood.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by PCTRUONG on 2/6/2017.
 */

public class Adapter_HienThi_DanhSach_MonAn extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<MonAnDTO> monAnDTOs;

    public Adapter_HienThi_DanhSach_MonAn(LayoutInflater inflater, ArrayList<MonAnDTO> monAnDTOs) {
        this.inflater = inflater;
        this.monAnDTOs = monAnDTOs;
    }

    @Override
    public int getCount() {
        return monAnDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return monAnDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.layout_custom_ds_monan,parent,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.img_hienthi_ds_monan);
        TextView txt= (TextView) view.findViewById(R.id.txt_tenmonan);
        TextView txtgia= (TextView) view.findViewById(R.id.txt_gia);
        MonAnDTO monAnDTO=monAnDTOs.get(position);
        imageView.setImageBitmap(monAnDTO.getHinhAnh());
        txt.setText(monAnDTO.getTenMonAn().toString());
        DecimalFormat decimalFormat = new DecimalFormat();
        txtgia.setText("Giá Tiền "+monAnDTO.getGiaTien()+ " VND ");
        return view;
    }
}
