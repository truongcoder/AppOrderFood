package com.example.pctruong.apporderfood.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pctruong.apporderfood.DAO.NhanVienDAO;
import com.example.pctruong.apporderfood.DAO.QuyenDAO;
import com.example.pctruong.apporderfood.DTO.NhanVienDTO;
import com.example.pctruong.apporderfood.DTO.QuyenDTO;
import com.example.pctruong.apporderfood.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PhamTruong on 16/05/2017.
 */

public class DangKiActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    EditText edTenDK, edMatKhauDK, edHoTen, edCMND, edNgaySinh;
    Button btnDongYDK, btnHuy;
    String gioitinh, ngaysinh;
    RadioGroup rgGT;
    NhanVienDAO nhanVienDAO;
    QuyenDAO quyenDAO;
    Spinner spinner;
    List<QuyenDTO> quyenDTOList;
    List<String> stringList;
    TextView txtNgaySinh;
    int dangkilandau;
    SharedPreferences preferences;
    DatePickerDialog pickerDialog;
    Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnki);
        init();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.sp_NhanVien);
        edCMND = (EditText) findViewById(R.id.edtCMND);
        edNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edTenDK = (EditText) findViewById(R.id.edtTenDN);
        edMatKhauDK = (EditText) findViewById(R.id.edtMatKhauDN);
        btnDongYDK = (Button) findViewById(R.id.btnDongY);
        btnHuy = (Button) findViewById(R.id.btnThoat);
        rgGT = (RadioGroup) findViewById(R.id.rgGT);
        btnHuy.setOnClickListener(this);
        btnDongYDK.setOnClickListener(this);
        edNgaySinh.setOnFocusChangeListener(this);
        nhanVienDAO = new NhanVienDAO(this);
        quyenDAO = new QuyenDAO(this);
        Intent intent = getIntent();
        stringList = new ArrayList<>();
        dangkilandau = intent.getIntExtra("dangkilandau", 0);
        quyenDTOList = quyenDAO.LayDanhSachQuyen();
        for (int i = 0; i < quyenDTOList.size(); i++) {
            stringList.add(quyenDTOList.get(i).getTenQuyen());
        }
        if (dangkilandau == 0) {
            quyenDAO.ThemQuyen("Quản lý");
            quyenDAO.ThemQuyen("Nhân viên");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stringList);
            spinner.setAdapter(adapter);

        } else {
            spinner.setVisibility(View.GONE);
        }
        NgaySinh();


    }

    private void SuaNhanVien() {
        Intent isuaNhanVien = getIntent();
        if (isuaNhanVien != null) {
            btnDongYDK.setText("Sửa Nhân Viên");
            Bundle bundle = isuaNhanVien.getBundleExtra("data");
            NhanVienDTO nhanVienDTO = (NhanVienDTO) bundle.getSerializable("data");
            edTenDK.setText(nhanVienDTO.getTENDN());
            edMatKhauDK.setText(nhanVienDTO.getMATKHAU());
            edNgaySinh.setText(nhanVienDTO.getNGAYSINH());
            edCMND.setText(nhanVienDTO.getCMND());

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDongY:
                DongY();
                break;
            case R.id.btnThoat:
                edHoTen.setText("");
                edTenDK.setText("");
                edMatKhauDK.setText("");
                edNgaySinh.setText("");
                break;
        }
    }

    private void NgaySinh() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                date = c.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edNgaySinh.setText(simpleDateFormat.format(date));
                pickerDialog.dismiss();
            }
        }, day, month, year);
    }

    private void DongY() {
        String tenDK = edTenDK.getText().toString();
        String matkhau = edMatKhauDK.getText().toString();
        String cmnd = edCMND.getText().toString();
        switch (rgGT.getCheckedRadioButtonId()) {
            case R.id.radiNam:
                gioitinh = "Nam";
                break;
            case R.id.radiNu:
                gioitinh = "Nữ";
                break;
        }
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.setTENDN(tenDK);
        nhanVienDTO.setMATKHAU(matkhau);
        nhanVienDTO.setNGAYSINH(String.valueOf(date));
        nhanVienDTO.setCMND(Integer.parseInt(cmnd));
        if (dangkilandau != 0) {
            nhanVienDTO.setMAQUYEN(1);
        } else {
            int vitri = spinner.getSelectedItemPosition();
            int maquyen = quyenDTOList.get(vitri).getMaQuyen();
            nhanVienDTO.setMAQUYEN(maquyen);
        }
        int kt = nhanVienDAO.themnhanvien(nhanVienDTO);
        if (kt != 0) {
            Toast.makeText(this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
