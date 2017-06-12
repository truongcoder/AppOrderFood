package com.example.pctruong.apporderfood.Adapter_Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pctruong.apporderfood.DAO.LoaiMonAnDAO;
import com.example.pctruong.apporderfood.DTO.LoaiMonAnDTO;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class Adpter_HienThiLoaiMonAnThucDon extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<LoaiMonAnDTO> list;
    LoaiMonAnDAO loaiMonAnDAO;
    ViewHolder_MonAn viewHolder_monAn;
    public Adpter_HienThiLoaiMonAnThucDon(Context context, int layout, ArrayList<LoaiMonAnDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        loaiMonAnDAO=new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  list.get(position).getMaLoai();
    }

    public class  ViewHolder_MonAn{
        ImageView imageView;
        TextView txt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder_monAn=new ViewHolder_MonAn();
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_hienthi_loaimonan,parent,false);
            viewHolder_monAn.txt= (TextView) convertView.findViewById(R.id.txtcustom);
            viewHolder_monAn.imageView= (ImageView)convertView.findViewById(R.id.img_ht_MonAn);
            convertView.setTag(viewHolder_monAn);
        }
        else {
            viewHolder_monAn= (ViewHolder_MonAn) convertView.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO=list.get(position);
        int maloai=loaiMonAnDTO.getMaLoai();
        viewHolder_monAn.txt.setText(list.get(position).getTenLoai().toString());
        Bitmap bitmap=loaiMonAnDAO.load_LoaiMonAn_CoHinh(maloai);
        viewHolder_monAn.imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
