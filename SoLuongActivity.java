package com.example.pctruong.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pctruong.apporderfood.DAO.GoiMonDAO;
import com.example.pctruong.apporderfood.DTO.ChiTietGoiMonDTO;
import com.example.pctruong.apporderfood.R;

/**
 * Created by PVTruong on 07/03/2017.
 */

public class SoLuongActivity extends AppCompatActivity {
    EditText edt;
    Button btnThemSoLuong;
    GoiMonDAO goiMonDAO;
    int maban, mamonan;
    TextInputLayout textInputLayout;
    int magoimon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsoluong);
        goiMonDAO = new GoiMonDAO(this);
        edt = (EditText) findViewById(R.id.edtSoLuongMonAn);
        textInputLayout = (TextInputLayout) findViewById(R.id.inputSoLuong);
        btnThemSoLuong = (Button) findViewById(R.id.btnthemSLMonAn);
        Intent intent = getIntent();
        maban = intent.getIntExtra("maban", 0);
        mamonan = intent.getIntExtra("mamonan", 0);
        btnThemSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                magoimon = goiMonDAO.LayMaGoiMonMaBan(maban, "false");
                boolean kt = goiMonDAO.KiemTraMonAnTonTai(magoimon, mamonan);
                if (kt) {
                    // cập nhật món ăn đã tồn tại
                    int soluongcu = goiMonDAO.LaySoLuongTheoMaGoiMon(magoimon, mamonan);
                    int soluongmoi = Integer.parseInt(edt.getText().toString());
                    int tongsl = soluongcu + soluongmoi;
                    ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
                    chiTietGoiMonDTO.setMaGoiMon(magoimon);
                    chiTietGoiMonDTO.setMaMonAn(mamonan);
                    chiTietGoiMonDTO.setSoLuong(tongsl);
                    boolean k_t = goiMonDAO.CapNhatLaiSoLuong(chiTietGoiMonDTO);
                    if (k_t) {
                        Toast.makeText(SoLuongActivity.this, R.string.CapNhatSLThanhCong, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SoLuongActivity.this, R.string.CapNhatSLKThanhCong, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // thêm món ăn
                    int soluong = Integer.parseInt(edt.getText().toString());
                    ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
                    chiTietGoiMonDTO.setMaGoiMon(magoimon);
                    chiTietGoiMonDTO.setMaMonAn(mamonan);
                    chiTietGoiMonDTO.setSoLuong(soluong);
                    boolean k_t = goiMonDAO.ThemChiTietGoiMon(chiTietGoiMonDTO);
                    if (kt) {

                        Toast.makeText(SoLuongActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(SoLuongActivity.this, getResources().getString(R.string.themkhongthanhcong), Toast.LENGTH_SHORT).show();

                    }
                }
                finish();
            }
        });


    }
}
