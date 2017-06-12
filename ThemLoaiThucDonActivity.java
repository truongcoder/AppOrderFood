package com.example.pctruong.apporderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pctruong.apporderfood.DAO.LoaiMonAnDAO;
import com.example.pctruong.apporderfood.R;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class ThemLoaiThucDonActivity extends AppCompatActivity {
    EditText edttenloai;
    Button btndongy;
    LoaiMonAnDAO loaiMonAnDAO;
    TextInputLayout textInputLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloai_monan);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        edttenloai = (EditText) findViewById(R.id.edtThemLoaiThucDon);
        btndongy = (Button) findViewById(R.id.btnthemLoaiTD);
        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edttenloai.getText().toString();
                if (ten!=null||!ten.equals("")) {
                    boolean kt = loaiMonAnDAO.ThemLoaiMonAn(ten);
                    Intent intent = new Intent();
                    intent.putExtra("data", kt);
                    setResult(Activity.RESULT_OK, intent);
                    if (kt) {
                        Toast.makeText(ThemLoaiThucDonActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ThemLoaiThucDonActivity.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThemLoaiThucDonActivity.this, "Nhập thiếu thông tin", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
