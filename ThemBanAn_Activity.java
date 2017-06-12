package com.example.pctruong.apporderfood.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pctruong.apporderfood.DAO.BanAnDAO;
import com.example.pctruong.apporderfood.R;

public class ThemBanAn_Activity extends AppCompatActivity implements View.OnClickListener{
   EditText edttenbanan;
   Button btnthembanan;
   BanAnDAO banAnDAO;
   TextInputLayout textInputLayout;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thembanan);
        edttenbanan= (EditText) findViewById(R.id.edttenbanan);
        textInputLayout= (TextInputLayout) findViewById(R.id.inputTenBanAn);
        btnthembanan= (Button) findViewById(R.id.btnthembanan);
        banAnDAO=new BanAnDAO(this);
        btnthembanan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String tenbanan=edttenbanan.getText().toString();

        if(tenbanan.length()>0){
         textInputLayout.setErrorEnabled(false);
         textInputLayout.setError("");
         boolean kt=   banAnDAO.them_BanAn(tenbanan);
             Intent intent=new Intent();
             intent.putExtra("tenbanan",kt);
             setResult(Activity.RESULT_OK,intent);
             finish();
        }
        else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Bạn chưa nhập dữ liệu");
        }
    }
}
