package com.example.pctruong.apporderfood.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pctruong.apporderfood.FragmentApp.FragmentNhanVien;
import com.example.pctruong.apporderfood.FragmentApp.HienThiBanAn_Fragment;
import com.example.pctruong.apporderfood.FragmentApp.HienThiThucDonF;
import com.example.pctruong.apporderfood.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   TextView txttendn;
   DrawerLayout drawerLayout;NavigationView navigationView;
   FragmentManager fragmentManager;
   SharedPreferences sharedPreferences;
   int maquyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  void init(){
        fragmentManager=getSupportFragmentManager();
        Toolbar toolbar= (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView= (NavigationView) findViewById(R.id.navigationview);
        sharedPreferences=getSharedPreferences("luuquyen",MODE_PRIVATE);
        View  view=navigationView.inflateHeaderView(R.layout.header_layout);
        txttendn= (TextView) view.findViewById(R.id.txttennv_navigavion);
        Intent intent=getIntent();
        String tendn=intent.getStringExtra("tendangnhap");
        txttendn.setText(tendn);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);//nút suy ra
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView.setItemIconTintList(null);//lấy màu trong itemmenu
        FragmentTransaction   fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,new HienThiBanAn_Fragment());
        fragmentTransaction.commit();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_trangchu :
                FragmentTransaction  fragmentTransaction=fragmentManager.beginTransaction();
                HienThiBanAn_Fragment hienThiBanAn_fragment=new HienThiBanAn_Fragment();
                fragmentTransaction.replace(R.id.content,new HienThiBanAn_Fragment());
                fragmentTransaction.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.item_thucdon :
                FragmentTransaction   fragmentHienThiThuDon=fragmentManager.beginTransaction();
                fragmentHienThiThuDon.replace(R.id.content,new HienThiThucDonF());
                fragmentHienThiThuDon.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.item_nhanvien :
                    maquyen=sharedPreferences.getInt("maquyen",0);
                    if(maquyen==1) {
                        FragmentTransaction fragment_nhanvien = fragmentManager.beginTransaction();
                        fragment_nhanvien.replace(R.id.content, new FragmentNhanVien()).addToBackStack("FragmentNhanVien");
                        fragment_nhanvien.commit();
                        item.setCheckable(true);
                        drawerLayout.closeDrawers();
                    }

                break;
            case R.id.item_thongke  : break;
            case R.id.item_caidat   :break;
        }
        return true;
    }
}
