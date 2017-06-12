package com.example.pctruong.apporderfood.FragmentApp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.pctruong.apporderfood.Adapter_Custom.Adapter_HienThi_DanhSach_MonAn;
import com.example.pctruong.apporderfood.DAO.MonAnDAO;
import com.example.pctruong.apporderfood.DTO.MonAnDTO;
import com.example.pctruong.apporderfood.R;
import com.example.pctruong.apporderfood.Activity.SoLuongActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DanhSachMonAn extends Fragment {
    Adapter_HienThi_DanhSach_MonAn adapter;
    GridView gridView;
    MonAnDAO monAnDAO;
    ArrayList<MonAnDTO> listMonAn;
    int maloai, maban=0;
    int mamonan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthi_thucdon, container, false);
        gridView = (GridView) view.findViewById(R.id.GVHienThiThucDon);
        monAnDAO = new MonAnDAO(getActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban");
            listMonAn = monAnDAO.Lay_DanhSach_MonAn(maloai);

            adapter = new Adapter_HienThi_DanhSach_MonAn(getActivity().getLayoutInflater(), listMonAn);
            gridView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(maban!=0){
                        Intent intent=new Intent(getActivity(), SoLuongActivity.class);
                        mamonan= listMonAn.get(position).getMaMonAn();
                        intent.putExtra("maban",maban);
                        intent.putExtra("mamonan",mamonan);
                        startActivity(intent);
                    }
                }
            });
        }

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    getFragmentManager().popBackStack("Fragment_DanhSachMonAn", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listMonAn = monAnDAO.Lay_DanhSach_MonAn(maloai);
        adapter = new Adapter_HienThi_DanhSach_MonAn(getActivity().getLayoutInflater(), listMonAn);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
