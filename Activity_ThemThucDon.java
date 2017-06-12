package com.example.pctruong.apporderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pctruong.apporderfood.Adapter_Custom.AdapterHienThiLoaiMonAn;
import com.example.pctruong.apporderfood.DAO.LoaiMonAnDAO;
import com.example.pctruong.apporderfood.DAO.MonAnDAO;
import com.example.pctruong.apporderfood.DTO.LoaiMonAnDTO;
import com.example.pctruong.apporderfood.DTO.MonAnDTO;
import com.example.pctruong.apporderfood.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class Activity_ThemThucDon extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner;
    ImageButton img_themTD, img_chonhinh;
    EditText edttenmonan, edtgiatien;
    TextInputLayout inputLayoutTenMon, inputLayoutGia;
    Button btnthemmonan, btnthoat;
    AdapterHienThiLoaiMonAn adapter;
    ArrayList<LoaiMonAnDTO> list;
    LoaiMonAnDAO loaiMonAnDAO;
    public static int request_code_them_thucdon = 2;
    public static int request_code_mo_hinhanh = 3;
    String getduongdanhinh = "";
    MonAnDAO monAnDAO;
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);
        spinner = (Spinner) findViewById(R.id.sp_loaimonan);
        img_themTD = (ImageButton) findViewById(R.id.imgThemLoaiTD);
        img_chonhinh = (ImageButton) findViewById(R.id.img_chonhinh);
        edtgiatien = (EditText) findViewById(R.id.edtgiatien);
        edttenmonan = (EditText) findViewById(R.id.edttenmonan);
        btnthemmonan = (Button) findViewById(R.id.btnthemmonan);
        inputLayoutGia = (TextInputLayout) findViewById(R.id.inputGiaMonAn);
        inputLayoutTenMon = (TextInputLayout) findViewById(R.id.inputTenMonAn);
        btnthoat = (Button) findViewById(R.id.btnthoat);
        btnthemmonan.setOnClickListener(this);
        btnthoat.setOnClickListener(this);
        img_themTD.setOnClickListener(this);
        img_chonhinh.setOnClickListener(this);
        loaddata();

    }

    public void loaddata() {
        list = new ArrayList<>();
        loaiMonAnDAO = new LoaiMonAnDAO(Activity_ThemThucDon.this);
        monAnDAO = new MonAnDAO(Activity_ThemThucDon.this);
        list = loaiMonAnDAO.loadLoaiMonAn();
        adapter = new AdapterHienThiLoaiMonAn(Activity_ThemThucDon.this, R.layout.custom_spinner, list);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnthemmonan:
                int vitri = spinner.getSelectedItemPosition();
                int maloai = list.get(vitri).getMaLoai();
                String ten = edttenmonan.getText().toString();
                String gia = edtgiatien.getText().toString();
                MonAnDTO monAnDTO = new MonAnDTO();
                monAnDTO.setTenMonAn(ten);
                monAnDTO.setMaLoai(maloai);
                monAnDTO.setGiaTien(gia);
                monAnDTO.setHinhAnh(bitmap);
                boolean kt = monAnDAO.them_MonAn(monAnDTO);
                if(kt){
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnthoat:
                finish();
                break;
            case R.id.imgThemLoaiTD:
                Intent intent = new Intent(Activity_ThemThucDon.this, ThemLoaiThucDonActivity.class);
                startActivityForResult(intent, request_code_them_thucdon);
                break;
            case R.id.img_chonhinh:
                Intent it = new Intent();
                it.setType("image/*");
                it.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(it, "Chọn hình"), request_code_mo_hinhanh);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code_them_thucdon) {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent = data;
                boolean kt = data.getBooleanExtra("data", false);
                if (kt) {
                    loaddata();
                    Toast.makeText(Activity_ThemThucDon.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity_ThemThucDon.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == request_code_mo_hinhanh) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    getduongdanhinh = data.getData().toString();
                    //img_chonhinh.setImageBitmap(bitmap);
                    img_chonhinh.setImageURI(data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
