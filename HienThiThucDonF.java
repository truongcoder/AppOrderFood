package com.example.pctruong.apporderfood.FragmentApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pctruong.apporderfood.Activity.Activity_ThemThucDon;
import com.example.pctruong.apporderfood.Adapter_Custom.Adpter_HienThiLoaiMonAnThucDon;
import com.example.pctruong.apporderfood.DAO.LoaiMonAnDAO;
import com.example.pctruong.apporderfood.DTO.LoaiMonAnDTO;
import com.example.pctruong.apporderfood.Activity.MainActivity;
import com.example.pctruong.apporderfood.R;

import java.util.ArrayList;

/**
 * Created by PCTRUONG on 1/2/2017.
 */

public class HienThiThucDonF extends Fragment {
    private final int resquest_code = 1;
    GridView gridView;
    ArrayList<LoaiMonAnDTO> list;
    FragmentManager fragmentManager;
    LoaiMonAnDAO loaiMonAnDAO;
    Adpter_HienThiLoaiMonAnThucDon adapter;
    int maban;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthi_thucdon, container, false);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Hiển Thị Thực Đơn");
        fragmentManager = getFragmentManager();
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        gridView = (GridView) view.findViewById(R.id.GVHienThiThucDon);
        getdata();
        Bundle bundle = getArguments();
        if (bundle != null) {
            maban = bundle.getInt("maban");
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int maloai = list.get(position).getMaLoai();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai", maloai);
                bundle.putInt("maban", maban);
                Fragment_DanhSachMonAn fragment_danhSachMonAn = new Fragment_DanhSachMonAn();
                fragment_danhSachMonAn.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, fragment_danhSachMonAn).addToBackStack("Fragment_DanhSachMonAn");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    public void getdata() {
        list = loaiMonAnDAO.loadLoaiMonAn();
        adapter = new Adpter_HienThiLoaiMonAnThucDon(getActivity(), R.layout.custom_hienthi_loaimonan, list);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getdata();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.add(1, R.id.item_themthucdon, 1, R.string.themthucdon);
        item.setIcon(R.drawable.logodangnhap);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_themthucdon:
                Intent intent = new Intent(getActivity(), Activity_ThemThucDon.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
