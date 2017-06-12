package com.example.pctruong.apporderfood.Adapter_Custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pctruong.apporderfood.Activity.ActivityThanhToan;
import com.example.pctruong.apporderfood.DAO.BanAnDAO;
import com.example.pctruong.apporderfood.DAO.GoiMonDAO;
import com.example.pctruong.apporderfood.DTO.BanAnDTO;
import com.example.pctruong.apporderfood.DTO.GoiMonDTO;
import com.example.pctruong.apporderfood.FragmentApp.HienThiThucDonF;
import com.example.pctruong.apporderfood.Activity.MainActivity;
import com.example.pctruong.apporderfood.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by PCTRUONG on 12/31/2016.
 */

public class HienThi_BanAn_Adapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    ArrayList<BanAnDTO> listbanan;
    int layout;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;
    FragmentManager fragmentManager;
    public HienThi_BanAn_Adapter(Context context, int layout, ArrayList<BanAnDTO> listbanan) {
        this.context = context;
        this.layout = layout;
        this.listbanan = listbanan;
        banAnDAO=new BanAnDAO(context);
        goiMonDAO=new GoiMonDAO(context);
        fragmentManager=((MainActivity)context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return listbanan.size();
    }

    @Override
    public Object getItem(int position) {
        return listbanan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class ViewHolder {
        TextView txt_tenbanan;
        ImageView img_banan, img_goimon, img_thanhtoan, img_anbtn;
    }

    ViewHolder viewHolder;
    private void HienBanAn(){
        viewHolder.img_goimon.setVisibility(View.VISIBLE);
        viewHolder.img_thanhtoan.setVisibility(View.VISIBLE);
        viewHolder.img_anbtn.setVisibility(View.VISIBLE); 
    } 
    private void AnBanBan(){
        viewHolder.img_goimon.setVisibility(View.INVISIBLE);
        viewHolder.img_thanhtoan.setVisibility(View.INVISIBLE);
        viewHolder.img_anbtn.setVisibility(View.INVISIBLE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        viewHolder = new ViewHolder();
        if (view == null) {
            view = inflater.inflate(R.layout.custom_ht_banan, parent, false);
            viewHolder.txt_tenbanan = (TextView) view.findViewById(R.id.txtTenbanan);
            viewHolder.img_anbtn = (ImageView) view.findViewById(R.id.img_An_BTN);
            viewHolder.img_goimon = (ImageView) view.findViewById(R.id.img_goimon);
            viewHolder.img_thanhtoan = (ImageView) view.findViewById(R.id.img_thanhtoan);
            viewHolder.img_banan = (ImageView) view.findViewById(R.id.img_BanAn);
            view.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) view.getTag();

        }
        if (listbanan.get(position).isDuocChon()) {
           HienBanAn();
        } else {
           AnBanBan();
        }
        BanAnDTO banAnDTO = listbanan.get(position);
        String kiemtra=banAnDAO.LayTinhTrangBanTheoma(banAnDTO.getMaBan());
        if(kiemtra.equals("true")){
            viewHolder.img_banan.setImageResource(R.drawable.banantrue);
        }
        else {
            viewHolder.img_banan.setImageResource(R.drawable.banan);
        }
        viewHolder.img_banan.setTag(position);
        viewHolder.txt_tenbanan.setText(banAnDTO.getTenBan().toString());
        viewHolder.img_banan.setOnClickListener(this);
        viewHolder.img_goimon.setOnClickListener(this);
        viewHolder.img_thanhtoan.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolder = (ViewHolder) ((View) v.getParent()).getTag();
        switch (id) {

            case R.id.img_BanAn:
                int vitri = (int) v.getTag();
                listbanan.get(vitri).setDuocChon(true);
                HienBanAn();
                break;
            case R.id.img_goimon :
                Intent intent=((MainActivity)context).getIntent();
                int manhanvien=intent.getIntExtra("manv",0);
                int vitri1= (int) viewHolder.img_banan.getTag();
                int maban=listbanan.get(vitri1).getMaBan();
                String tinhtrang=banAnDAO.LayTinhTrangBanTheoma(maban);
                if(tinhtrang.equals("false")){
                    Calendar c=Calendar.getInstance();
                    SimpleDateFormat fs=new SimpleDateFormat("dd-MM-yyyy");
                    String ngaythanhtoan=fs.format(c.getTime());
                    GoiMonDTO goiMonDTO=new GoiMonDTO();
                    goiMonDTO.setMaBan(maban);
                    goiMonDTO.setMaNV(manhanvien);
                    goiMonDTO.setNgayGoi(ngaythanhtoan);
                    goiMonDTO.setTinhTrang("false");
                    long kt=goiMonDAO.ThemGoiMonBanAn(goiMonDTO);
                    banAnDAO.CapNhapLaiTinhTrangban(maban,"true");
                }
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                HienThiThucDonF thucDonF=new HienThiThucDonF();
                Bundle bundle=new Bundle();
                bundle.putInt("maban",maban);
                thucDonF.setArguments(bundle);
                fragmentTransaction.replace(R.id.content,thucDonF).addToBackStack("hienthibanan");
                fragmentTransaction.commit();
                break;
            case R.id.img_thanhtoan :
                Intent itThanhToan=new Intent(context, ActivityThanhToan.class);
                int vitri2= (int) viewHolder.img_banan.getTag();
                int ma_ban=listbanan.get(vitri2).getMaBan();
                itThanhToan.putExtra("maban",ma_ban);
                context.startActivity(itThanhToan);
                break;

        }
    }
}
