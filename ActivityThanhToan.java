package com.example.pctruong.apporderfood.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pctruong.apporderfood.Adapter_Custom.Adapter_ThanhToan;
import com.example.pctruong.apporderfood.DAO.BanAnDAO;
import com.example.pctruong.apporderfood.DAO.GoiMonDAO;
import com.example.pctruong.apporderfood.DTO.ThanhToanDTO;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PVTruong on 05/04/2017.
 */

public class ActivityThanhToan extends AppCompatActivity implements View.OnClickListener {
    GridView gridView;
    Button btnThanhToan , btnThoat;
    Adapter_ThanhToan adapterThanhToan;
    ArrayList<ThanhToanDTO> arrayList;
    GoiMonDAO goiMonDAO;
    int tongtien=0;
    TextView txtTongTien;
    int maban;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        gridView= (GridView) findViewById(R.id.gvThanhToan);
        txtTongTien= (TextView) findViewById(R.id.txtTongTien);
        btnThanhToan= (Button) findViewById(R.id.btnThanhToan);
        btnThoat= (Button) findViewById(R.id.btnThoatThanhToan);
        btnThanhToan.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        goiMonDAO=new GoiMonDAO(this);
        banAnDAO=new BanAnDAO(this);
        maban=getIntent().getIntExtra("maban",0);
        if(maban!=0){
            int magoimon=goiMonDAO.LayMaGoiMonMaBan(maban,"false");
            arrayList=goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(magoimon);
            adapterThanhToan=new Adapter_ThanhToan(this,arrayList);
            gridView.setAdapter(adapterThanhToan);
            adapterThanhToan.notifyDataSetChanged();

            for(int i=0 ; i<arrayList.size();i++){
                int soluong=arrayList.get(i).getSoLuong();
                int giatien=arrayList.get(i).getGiaTien();
                tongtien+=soluong*giatien;
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThanhToan :
               txtTongTien.setText(tongtien+"");
               boolean kiemtra= banAnDAO.CapNhapLaiTinhTrangban(maban,"false");
               boolean kt=    goiMonDAO.CapNhatTrangThaiGoiMonTheoMaBan(maban,"true");
              break;
            case R.id.btnThoatThanhToan :
                finish();
                break;
        }
    }

    private void ThanhToan() {

    }
}
