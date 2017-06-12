package com.example.pctruong.apporderfood.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pctruong.apporderfood.DAO.NhanVienDAO;
import com.example.pctruong.apporderfood.R;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    View btndangki, btndangnhap;
    EditText edttenDN, edtmatkhauDN;
    NhanVienDAO nhanVienDAO;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        nhanVienDAO = new NhanVienDAO(this);
        sharedPreferences = getSharedPreferences("luuquyen", MODE_PRIVATE);
        btndangki = findViewById(R.id.btnDangKiDN);
        btndangnhap = findViewById(R.id.btnDongYDN);
        edttenDN = (EditText) findViewById(R.id.edttenDangNhap);
        edtmatkhauDN = (EditText) findViewById(R.id.edtmatkhauDangNhap);
        btndangnhap.setOnClickListener(this);
        btndangki.setOnClickListener(this);
        HienThiDangKi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiDangKi();
    }

    private void HienThiDangKi() {
        boolean kt = nhanVienDAO.KiemTraNhanVien();
        if (kt) {
            btndangki.setVisibility(View.GONE);
            btndangnhap.setVisibility(View.VISIBLE);
        } else {
            btndangki.setVisibility(View.VISIBLE);
            btndangnhap.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangKiDN:
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                intent.putExtra("dangkilandau", 1);
                intent.putExtra("kiemtra",1);
                startActivity(intent);
                break;
            case R.id.btnDongYDN:
                dangnhap();
                break;
        }
    }

    private void dangnhap() {
        String ten = edttenDN.getText().toString();
        String matkhau = edtmatkhauDN.getText().toString();
        int kt = nhanVienDAO.KiemTraDangNhap(ten, matkhau);
        int maquyen = nhanVienDAO.KiemTraQuyen(kt);

        if (kt != 0) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maquyen", maquyen);
            editor.commit();
            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
            intent.putExtra("tendangnhap", edttenDN.getText().toString());
            intent.putExtra("manv", kt);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
        }
    }


}
