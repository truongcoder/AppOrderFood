package com.example.pctruong.apporderfood.Adapter_Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pctruong.apporderfood.DTO.ThanhToanDTO;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PVTruong on 05/04/2017.
 */

public class Adapter_ThanhToan extends BaseAdapter {
    Context context;
    ArrayList<ThanhToanDTO> thanhToanDTOs;

    public Adapter_ThanhToan(Context context, ArrayList<ThanhToanDTO> thanhToanDTOs) {
        this.context=context;
        this.thanhToanDTOs = thanhToanDTOs;
    }

    @Override
    public int getCount() {
        return thanhToanDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhToanDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_layout_thanhtoan,parent,false);
        TextView txtTenMonAn= (TextView) view.findViewById(R.id.txtTenMonAnThanhToan);
        TextView txtSoLuong= (TextView) view.findViewById(R.id.txtSoLuongThanhToan);
        TextView txtGiaTien= (TextView) view.findViewById(R.id.txtGiaTienThanhToan);

        txtTenMonAn.setText(thanhToanDTOs.get(position).getTenMonAn());
        txtSoLuong.setText(thanhToanDTOs.get(position).getSoLuong()+"");
        txtGiaTien.setText(thanhToanDTOs.get(position).getGiaTien()+"");
        return view;
    }
}
